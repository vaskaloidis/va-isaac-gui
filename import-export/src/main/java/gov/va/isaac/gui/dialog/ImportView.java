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
package gov.va.isaac.gui.dialog;

import gov.va.isaac.AppContext;
import gov.va.isaac.gui.util.FxUtils;
import gov.va.isaac.gui.util.GridPaneBuilder;
import gov.va.isaac.ie.ImportHandler;
import gov.va.isaac.model.InformationModelType;
import gov.va.isaac.models.InformationModel;
import gov.va.isaac.models.cem.importer.CEMImporter;
import gov.va.isaac.models.fhim.importer.FHIMImporter;
import gov.va.isaac.models.hed.importer.HeDImporter;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import javax.xml.transform.TransformerConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * A GUI for handling imports.
 *
 * @author ocarlsen
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 * @author bcarlsenca
 */
@SuppressWarnings("restriction")
public class ImportView extends GridPane {

  /** The Constant LOG. */
  static final Logger LOG = LoggerFactory.getLogger(ImportView.class);

  /** The model type label. */
  private final Label modelTypeLabel = new Label();

  /** The file name label. */
  private final Label fileNameLabel = new Label();

  /** The progress bar. */
  final javafx.scene.control.ProgressBar progressBar = new ProgressBar(0);

  /** The status label. */
  final Label statusLabel = new Label();

  /** The result label. */
  final Label resultLabel = new Label();

  /** The cancel button. */
  final Button cancelButton = new Button("Cancel");

  /** The importer task. */
  private Task<InformationModel> task = null;

  /** The request cancel. */
  boolean requestCancel = false;

  /**
   * Instantiates an empty {@link ImportView}.
   */
  public ImportView() {
    super();

    // GUI placeholders.
    this.setHgap(10);
    this.setVgap(10);
    this.setPadding(new javafx.geometry.Insets(10,10,10,10));
    GridPaneBuilder builder = new GridPaneBuilder(this);
    builder.addRow("Information Model: ", modelTypeLabel);
    builder.addRow("File Name: ", fileNameLabel);
    builder.addRow("Progress: ", progressBar);
    progressBar.setMinWidth(500);
    builder.addRow("Status: ", statusLabel);
    builder.addRow("Result: ", resultLabel);
    @SuppressWarnings("deprecation")
    javafx.scene.layout.HBox hbox = javafx.scene.layout.HBoxBuilder.create()
    .alignment(javafx.geometry.Pos.TOP_CENTER).children(cancelButton).build();

    builder.addRow(hbox);
    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        doCancel();
      }
    });
    setConstraints();

    // Set minimum dimensions.
    setMinHeight(100);
    setMinWidth(600);
  }

  /**
   * Do import for the specified type and file name.
   *
   * @param modelType the model type
   * @param fileName the file name
   * @throws TransformerConfigurationException
   */
  public void doImport(InformationModelType modelType, final String fileName)
    throws TransformerConfigurationException {
    Preconditions.checkNotNull(modelType);
    Preconditions.checkNotNull(fileName);

    // Make sure in application thread.
    FxUtils.checkFxUserThread();

    // Update UI.
    modelTypeLabel.setText(modelType.getDisplayName());
    fileNameLabel.setText(fileName);

    // Instantiate appropriate importer class.
    ImportHandler importHandler = null;
    switch (modelType) {
      case CEM:
        importHandler = new CEMImporter();
        break;
      case HeD:
        importHandler = new HeDImporter();
        break;
      case FHIM:
        importHandler = new FHIMImporter();
        break;
      default:
        throw new UnsupportedOperationException(modelType.getDisplayName()
            + " import not yet supported in ISAAC.");
    }

    // Do work in background.
    task = new ImporterTask(fileName, importHandler);

    // Bind cursor to task state.
    ObjectBinding<Cursor> cursorBinding =
        Bindings.when(task.runningProperty()).then(Cursor.WAIT)
            .otherwise(Cursor.DEFAULT);
    this.getScene().cursorProperty().bind(cursorBinding);

    Thread t = new Thread(task, "Importer_" + modelType);
    t.setDaemon(true);
    t.start();
  }

  /**
   * Do cancel.
   */
  public void doCancel() {
    LOG.info("Cancel import.");
    if (requestCancel) {
      // complete the process
      ((Stage) getScene().getWindow()).close();
      return;
    }

    // Set requestCancel to true and cancel task
    requestCancel = true;
    if (task.isRunning()) {
      task.cancel(true);
    }
    cancelButton.setText("Close");
    requestCancel = true;
    resultLabel.setText("Successfully cancelled import.");
  }

  /**
   * Sets the FX constraints.
   */
  private void setConstraints() {

    // Column 1 has empty constraints.
    this.getColumnConstraints().add(new ColumnConstraints());

    // Column 2 should grow to fill space.
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setHgrow(Priority.ALWAYS);
    this.getColumnConstraints().add(column2);

    // Rows 1-4 have empty constraints.
    this.getRowConstraints().add(new RowConstraints());
    this.getRowConstraints().add(new RowConstraints());
    this.getRowConstraints().add(new RowConstraints());
    this.getRowConstraints().add(new RowConstraints());

    // Row 5 should
    RowConstraints row5 = new RowConstraints();
    row5.setVgrow(Priority.ALWAYS);
    this.getRowConstraints().add(row5);
  }

  /**
   * Concrete {@link Task} for executing the import.
   *
   * @author ocarlsen
   * @author bcarlsenca
   */
  class ImporterTask extends Task<InformationModel> {

    /** The file name. */
    private final String fileName;

    /** The import handler. */
    private final ImportHandler importHandler;

    /**
     * Instantiates a {@link ImporterTask} from the specified parameters.
     *
     * @param fileName the file name
     * @param importHandler the import handler
     */
    ImporterTask(String fileName, ImportHandler importHandler) {
      this.fileName = fileName;
      this.importHandler = importHandler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javafx.concurrent.Task#call()
     */
    @Override
    protected InformationModel call() throws Exception {
      InformationModel returnValue = null;
      // Do work - loop if .zip file case
      boolean errorFound = false;
      SimpleDateFormat df = new SimpleDateFormat("YYYYmmDD");
      File f =
          new File(System.getenv("user.home"), 
              "importError." + df.format(new Date()) + ".txt");
      if (f.exists()) {
        f.delete();
      }
      if (this.fileName.endsWith(".zip")) {
        FileWriter out = new FileWriter(f);
        ZipFile zipFile = new ZipFile(new File(this.fileName));
        int progress = 0;
        int maxProgress = Collections.list(zipFile.entries()).size();
        for (final ZipEntry entry : Collections.list(zipFile.entries())) {
          if (requestCancel) {
            break;
          }
          Platform.runLater(() -> {
            statusLabel.setText("Processing " + entry.getName());
          });
          final int progressFinal = progress;
          Platform.runLater(() -> {
            progressBar.setProgress((progressFinal * 1.0) / maxProgress);
          });
          // Process each .zip or .uml file
          if (entry.getName().endsWith(".xml")
              || entry.getName().endsWith(".uml")) {
            InputStream stream = zipFile.getInputStream(entry);
            try {
              returnValue = importHandler.importModel(stream);
            } catch (Exception e) {
              // Add case to errors file
              if (!errorFound) {
                out.write("Problems importing:" + System.lineSeparator());
              }
              errorFound = true;
              out.write(entry.getName() + " - " + e.getMessage()
                  + System.lineSeparator());
            }
            stream.close();
          } else {
            Platform.runLater(() -> {
              statusLabel.setText("Skipping" + entry.getName() + ", "
                  + " wrong file type");
            });
          }
          progress++;
        }
        zipFile.close();
        out.flush();
        out.close();
      } else {
        Platform.runLater(() -> {
          statusLabel.setText("Processing " + fileName);
        });
        Platform.runLater(() -> {
          progressBar.setProgress(.3);
        });
        returnValue = importHandler.importModel(new File(fileName));
      }
      if (errorFound) {
        Platform.runLater(() -> {
          statusLabel.setText("Error loading models, see: "
              + f.getAbsolutePath());
        });
      } else {
        Platform.runLater(() -> {
          statusLabel.setText("done");
        });
      }
      Platform.runLater(() -> {
        progressBar.setProgress(1);
      });

      // return value
      return returnValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javafx.concurrent.Task#succeeded()
     */
    @Override
    protected void succeeded() {
      // Update UI.
      progressBar.setProgress(1);
      cancelButton.setText("Close");
      if (requestCancel) {
        resultLabel.setText("Successfully cancelled imported.");
      } else {
        resultLabel.setText("Successfully imported model(s).");
      }
      requestCancel = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javafx.concurrent.Task#failed()
     */
    @Override
    protected void failed() {
      Throwable ex = getException();

      // Update UI.
      progressBar.setProgress(1);
      cancelButton.setText("Close");
      // leave last status value
      resultLabel.setText("Failed to import model.");

      // Show dialog.
      String title = ex.getClass().getName();
      String msg =
          String
              .format("Unexpected error importing from file \"%s\"", fileName);
      LOG.error(msg, ex);
      AppContext.getCommonDialogs()
          .showErrorDialog(title, msg, ex.getMessage());
    }
  }
}
