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

/**
 * ViewCoordinatePreferencesPluginView
 * 
 * @author <a href="mailto:joel.kniaz@gmail.com">Joel Kniaz</a>
 */
package gov.va.isaac.gui.preferences.plugins;

import gov.va.isaac.AppContext;
import gov.va.isaac.ExtendedAppContext;
import gov.va.isaac.config.generated.StatedInferredOptions;
import gov.va.isaac.config.profiles.UserProfile;
import gov.va.isaac.config.profiles.UserProfileDefaults;
import gov.va.isaac.config.profiles.UserProfileManager;
import gov.va.isaac.config.users.InvalidUserException;
import gov.va.isaac.interfaces.gui.views.commonFunctionality.PreferencesPluginViewI;
import gov.va.isaac.util.OCHREUtility;
import gov.va.isaac.util.ValidBooleanBinding;
import gov.vha.isaac.ochre.api.component.concept.ConceptVersion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Callback;

import javax.inject.Singleton;

import org.ihtsdo.otf.tcc.api.concept.ConceptChronicleBI;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionException;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EditCoordinatePreferencesPluginView
 * 
 * @author <a href="mailto:joel.kniaz@gmail.com">Joel Kniaz</a>
 *
 */

@Service
@Singleton
public class EditCoordinatePreferencesPluginView  implements PreferencesPluginViewI {
	private Logger logger = LoggerFactory.getLogger(EditCoordinatePreferencesPluginView.class);

	// BEGIN CoordinatePreferencesPluginView
	private HBox hBox = null;
	private ValidBooleanBinding allValid_ = null;
	
	private final ObjectProperty<UUID> currentPathProperty = new SimpleObjectProperty<>();

	/**
	 * 
	 */
	public EditCoordinatePreferencesPluginView() {
		super();
	}

	/* (non-Javadoc)
	 * @see gov.va.isaac.interfaces.gui.views.commonFunctionality.PreferencesPluginViewI#getRegion()
	 */
	@Override
	public Region getContent() {
		if (hBox == null) {
			allValid_ = new ValidBooleanBinding() {
				{
					bind(currentPathProperty);
					setComputeOnInvalidate(true);
				}
				
				@Override
				protected boolean computeValue() {
					if (currentPathProperty.get() == null) {
						this.setInvalidReason("Null/unset/unselected path");

						return false;
					}

					this.clearInvalidReason();
					return true;
				}
			};
			
			ComboBox<UUID> pathComboBox = new ComboBox<>();
			pathComboBox.setCellFactory(new Callback<ListView<UUID>, ListCell<UUID>> () {
				@Override
				public ListCell<UUID> call(ListView<UUID> param) {
					final ListCell<UUID> cell = new ListCell<UUID>() {
						@Override
						protected void updateItem(UUID c, boolean emptyRow) {
							super.updateItem(c, emptyRow);

							if(c == null) {
								setText(null);
							}else {
								String desc = OCHREUtility.getDescription(c);
								setText(desc);
							}
						}
					};

					return cell;
				}
			});
			pathComboBox.setButtonCell(new ListCell<UUID>() {
				@Override
				protected void updateItem(UUID c, boolean emptyRow) {
					super.updateItem(c, emptyRow); 
					if (emptyRow) {
						setText("");
					} else {
						String desc = OCHREUtility.getDescription(c);
						setText(desc);
					}
				}
			});
			pathComboBox.getItems().addAll(getPathOptions());
			currentPathProperty.bind(pathComboBox.getSelectionModel().selectedItemProperty());
			
			// ComboBox
			final UUID storedPath = getStoredPath();
			pathComboBox.getSelectionModel().select(storedPath);
			//pathComboBox.setTooltip(new Tooltip("Default path is \"" + OTFUtility.getDescription(getDefaultPath()) + "\""));

			hBox = new HBox();
			hBox.getChildren().addAll(pathComboBox);
		}
		
		return hBox;
	}

	/* (non-Javadoc)
	 * @see gov.va.isaac.interfaces.gui.views.commonFunctionality.PreferencesPluginViewI#getName()
	 */
	@Override
	public String getName() {
		return "Edit Coordinate";
	}

	/* (non-Javadoc)
	 * @see gov.va.isaac.interfaces.gui.views.commonFunctionality.PreferencesPluginViewI#save()
	 */
	@Override
	public void save() throws IOException {
		logger.debug("Saving EditCoordinatePreferencesPluginView data");
		UserProfile loggedIn = ExtendedAppContext.getCurrentlyLoggedInUserProfile();
		logger.debug("Setting stored EC path (currently \"{}\") to {}", loggedIn.getEditCoordinatePath(), currentPathProperty().get()); 
		loggedIn.setEditCoordinatePath(currentPathProperty().get());
		try {
			AppContext.getService(UserProfileManager.class).saveChanges(loggedIn);
		} catch (InvalidUserException e) {
			String msg = "Caught " + e.getClass().getName() + " " + e.getLocalizedMessage() + " attempting to save UserProfile for " + getName();
			
			logger.error(msg, e);
			throw new IOException(msg, e);
		}
	}

	protected Collection<UUID> getPathOptions() {
		List<UUID> list = new ArrayList<>();

		try {
			Set<ConceptVersion<?>> pathConcepts = OCHREUtility.getPathConcepts();
			for (ConceptVersion<?> cv : pathConcepts) {
				list.add(cv.getChronology().getPrimordialUuid());
			}
		} catch (IOException | ContradictionException e) {
			logger.error("Failed loading path concepts. Caught {} {}", e.getClass().getName(), e.getLocalizedMessage());
			e.printStackTrace();
		}
		// Add currently-stored value to list of options, if not already there
		UUID storedPath = getStoredPath();
		if (storedPath != null && ! list.contains(storedPath)) {
			list.add(storedPath);
		}

		//Collections.sort(list);
		return list;
	}

	protected UUID getStoredPath() {
		UserProfile loggedIn = ExtendedAppContext.getCurrentlyLoggedInUserProfile();
		return loggedIn.getEditCoordinatePath();
	}
	
	protected UUID getDefaultPath() {
		return UserProfileDefaults.getDefaultEditCoordinatePath();
	}
	/**
	 * @see gov.va.isaac.interfaces.gui.views.commonFunctionality.PreferencesPluginViewI#getTabOrder()
	 */
	@Override
	public int getTabOrder()
	{
		return 20;
	}

	/* (non-Javadoc)
	 * @see gov.va.isaac.interfaces.gui.views.commonFunctionality.PreferencesPluginViewI#getValidationFailureMessage()
	 */
	@Override
	public ReadOnlyStringProperty validationFailureMessageProperty() {
		return allValid_.getReasonWhyInvalid();
	}
	
	public ReadOnlyObjectProperty<UUID> currentPathProperty() {
		return currentPathProperty;
	}
}
