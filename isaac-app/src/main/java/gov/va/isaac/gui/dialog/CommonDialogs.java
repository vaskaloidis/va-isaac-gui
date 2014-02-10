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

import gov.va.isaac.gui.ExtendedAppContext;
import gov.va.isaac.gui.interfaces.ApplicationWindowI;
import gov.va.isaac.gui.interfaces.CommonDialogsI;
import gov.va.isaac.gui.interfaces.SnomedConceptViewI;
import gov.va.isaac.gui.util.FxUtils;
import java.io.IOException;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CommonDialogs
 * 
 * @author ocarlsen
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */

@Service
@Singleton
public class CommonDialogs implements CommonDialogsI
{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private ErrorDialog errorDialog_;
	private InformationDialog informationDialog_;

	@Inject
	private CommonDialogs(ApplicationWindowI mainAppWindow) throws IOException
	{
		// hidden - constructed by HK2
		this.errorDialog_ = new ErrorDialog(mainAppWindow.getPrimaryStage());
		this.informationDialog_ = new InformationDialog(mainAppWindow.getPrimaryStage());
	}

	/**
	 * @see gov.va.isaac.gui.interfaces.CommonDialogsI#showInformationDialog(java.lang.String, java.lang.String)
	 */
	@Override
	public void showInformationDialog(String title, String message)
	{
		// Make sure in application thread.
		FxUtils.checkFxUserThread();

		informationDialog_.setVariables(title, message);
		informationDialog_.showAndWait();
	}

	/**
	 * @see gov.va.isaac.gui.interfaces.CommonDialogsI#showErrorDialog(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void showErrorDialog(String message, Throwable throwable)
	{
		String title = throwable.getClass().getName();
		String details = throwable.getMessage();
		showErrorDialog(title, message, details);
	}

	/**
	 * @see gov.va.isaac.gui.interfaces.CommonDialogsI#showErrorDialog(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void showErrorDialog(String title, String message, String details)
	{
		// Make sure in application thread.
		FxUtils.checkFxUserThread();

		errorDialog_.setVariables(title, message, details);
		errorDialog_.showAndWait();
	}

	/**
	 * @see gov.va.isaac.gui.interfaces.CommonDialogsI#showSnomedConceptDialog(java.util.UUID)
	 */
	@Override
	public void showSnomedConceptDialog(UUID conceptUUID)
	{
		try
		{
			SnomedConceptViewI dialog = ExtendedAppContext.createSnomedConceptViewWindow();
			dialog.showConcept(conceptUUID);
		}
		catch (Exception ex)
		{
			String message = "Unexpected error displaying snomed concept view";
			LOG.warn(message, ex);
			showErrorDialog("Unexpected Error", message, ex.getMessage());
		}
	}
}
