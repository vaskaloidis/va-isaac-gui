/**
 * Copyright Notice
 * 
 * This is a work of the U.S. Government and is not subject to copyright
 * protection in the United States. Foreign copyrights may apply.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.va.isaac.gui;

import gov.va.isaac.AppContext;
import gov.va.isaac.ExtendedAppContext;
import gov.va.isaac.gui.dialog.CommonDialogs;
import gov.va.isaac.interfaces.gui.ApplicationWindowI;
import gov.va.isaac.interfaces.utility.ShutdownBroadcastListenerI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.ihtsdo.otf.query.lucene.LuceneIndexer;
import org.ihtsdo.otf.tcc.datastore.BdbTerminologyStore;
import org.ihtsdo.otf.tcc.lookup.Hk2Looker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ISAAC {@link Application} class.
 *
 * @author ocarlsen
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a> 
 */
public class App extends Application implements ApplicationWindowI{

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private AppController controller;
    private boolean shutdown = false;
    private Stage primaryStage_;
    private CommonDialogs commonDialog_;
    private ArrayList<ShutdownBroadcastListenerI> shutdownListeners_ = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage_ = primaryStage;
        ServiceLocatorUtilities.addOneConstant(AppContext.getServiceLocator(), this);
        //Set up the CommonDialogs class (which needs a references to primaryStage_ and gets it via injection)
        commonDialog_ = AppContext.getServiceLocator().getService(CommonDialogs.class);

        URL resource = this.getClass().getResource("App.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = (Parent) loader.load();
        this.controller = loader.getController();

        primaryStage.getIcons().add(new Image("/icons/16x16/application-block.png"));
        primaryStage.setTitle("ISAAC App");
        primaryStage.setScene(new Scene(root));

        // Set minimum dimensions.
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(400);

        // Handle window close event.
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                shutdown();
            }
        });

        primaryStage.show();

        // Reduce size to fit in user's screen.
        // (Need to do after stage is shown, because otherwise
        // the primary stage width & height are NaN.)
        Screen screen = Screen.getPrimary();
        double screenW = screen.getVisualBounds().getWidth();
        double screenH = screen.getVisualBounds().getHeight();
        if (primaryStage.getWidth() > screenW) {
            LOG.debug("Resizing width to " + screenW);
            primaryStage.setWidth(screenW);
        }
        if (primaryStage.getHeight() > screenH) {
            LOG.debug("Resizing height to " + screenH);
            primaryStage.setHeight(screenH);
        }

        // Kick off a thread to open the DB connection.
        loadDataStore();
    }

    private void loadDataStore() {

        // Do work in background.
        Task<BdbTerminologyStore> task = new Task<BdbTerminologyStore>() {

            @Override
            protected BdbTerminologyStore call() throws Exception {
                LOG.info("Opening Workbench database");
                BdbTerminologyStore dataStore = AppContext.getServiceLocator().getService(BdbTerminologyStore.class);
                LOG.info("Finished opening Workbench database");

                // Check if user shut down early.
                if (shutdown) {
                    dataStore.shutdown();
                    return null;
                }

                return dataStore;
            }

            @Override
            protected void succeeded() {
                controller.finishInit();
            }

            @Override
            protected void failed() {
                Throwable ex = getException();

                // Display helpful dialog to users.
                if (ex instanceof FileNotFoundException) {
                    String title = "No Snomed Database";
                    String message = "The Snomed Database was not found.";
                    LOG.error(message, ex);
                    String details = "Please download the file\n\n"
                            + "https://mgr.servers.aceworkspace.net/apps/va-archiva/repository/all/org/ihtsdo/otf/tcc-test-data/3.0/tcc-test-data-3.0.zip"
                            + "\n\nand unzip it into\n\n"
                            + System.getProperty("user.dir")
                            + "\n\nand then restart the editor.";
                    commonDialog_.showErrorDialog(title, message, details);

                    // Close app since no DB to load.
                    // (The #shutdown method will be also invoked by
                    // the handler we hooked up with Stage#setOnHiding.)
                    primaryStage_.hide();

                } else {
                    String title = "Unexpected error connecting to workbench database";
                    String msg = ex.getClass().getName();
                    String details = ex.getMessage();
                    LOG.error(title, ex);
                    commonDialog_.showErrorDialog(title, msg, details);
                }
            }
        };

        Thread t = new Thread(task, "SCT_DB_Open");
        t.setDaemon(true);
        t.start();
    }

    private static void configDataStorePaths(File bdbFolder) throws IOException {

        // Default value if null.
        if (bdbFolder == null) {
            //do nothing... let the store use its own default
            //TODO OTF fix: note - the BDB defaults are not in sync with the Lucene defaults...  lucene will init in the wrong place and fail....
        }
        else
        {
            
            if (!bdbFolder.exists())
            {
                throw new FileNotFoundException("Couldn't find specified bdb data store '" + bdbFolder.getAbsolutePath() + "'");
            }
            if (!bdbFolder.isDirectory())
            {
                throw new IOException("The specified bdb data store: '" + bdbFolder.getAbsolutePath() + "' is not a folder");
            }
            
            if (System.getProperty(BdbTerminologyStore.BDB_LOCATION_PROPERTY) == null)
            {
                System.setProperty(BdbTerminologyStore.BDB_LOCATION_PROPERTY, bdbFolder.getCanonicalPath());
            }
            else
            {
                LOG.warn("The application specified '" + bdbFolder.getCanonicalPath() + "' but the system property " + BdbTerminologyStore.BDB_LOCATION_PROPERTY 
                        + "is set to " + System.getProperty(BdbTerminologyStore.BDB_LOCATION_PROPERTY + " this will override the application path"));
            }
            if (System.getProperty(LuceneIndexer.LUCENE_ROOT_LOCATION_PROPERTY) == null)
            {
                System.setProperty(LuceneIndexer.LUCENE_ROOT_LOCATION_PROPERTY, bdbFolder.getCanonicalPath());
            }
            else
            {
                LOG.warn("The application specified '" + bdbFolder.getCanonicalPath() + "' but the system property " + LuceneIndexer.LUCENE_ROOT_LOCATION_PROPERTY 
                        + "is set to " + System.getProperty(LuceneIndexer.LUCENE_ROOT_LOCATION_PROPERTY) + " this will override the application path");
            }
            
        }
    }

    private void shutdown() {
        LOG.info("Shutting down");
        shutdown = true;
        try {
            ExtendedAppContext.getDataStore().shutdown();
            for (ShutdownBroadcastListenerI s : shutdownListeners_)
            {
                if (s != null)
                {
                    s.shutdown();
                }
            }
        } catch (Exception ex) {
            String message = "Trouble shutting down";
            LOG.warn(message, ex);
            commonDialog_.showErrorDialog("Oops!", message, ex.getMessage());
        }

        LOG.info("Finished shutting down");
    }
    
	/**
	 * @see gov.va.isaac.interfaces.gui.ApplicationWindowI#getPrimaryStage()
	 */
	@Override
	public Stage getPrimaryStage()
	{
		return primaryStage_;
	}
	
	/**
	 * @see gov.va.isaac.interfaces.gui.ApplicationWindowI#registerShutdownListener(gov.va.isaac.interfaces.utility.ShutdownBroadcastListenerI)
	 */
	@Override
	public void registerShutdownListener(ShutdownBroadcastListenerI listener)
	{
		shutdownListeners_.add(listener);
	}

    public static void main(String[] args) throws ClassNotFoundException, IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        AppContext.setup();
        // TODO OTF fix: this needs to be fixed so I don't have to hack it with reflection....
        Field f = Hk2Looker.class.getDeclaredField("looker");
        f.setAccessible(true);
        f.set(null, AppContext.getServiceLocator());
        //This has to be done _very_ early, otherwise, any code that hits it via H2K will kick off the init process, on the wrong path
        //Which is made worse by the fact that the defaults in OTF are inconsistent between BDB and lucene...
        configDataStorePaths(new File("berkeley-db"));
        Application.launch(args);
    }
}