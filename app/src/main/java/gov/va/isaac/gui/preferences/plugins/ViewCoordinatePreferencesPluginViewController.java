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
package gov.va.isaac.gui.preferences.plugins;

import gov.va.isaac.AppContext;
import gov.va.isaac.ExtendedAppContext;
import gov.va.isaac.config.generated.StatedInferredOptions;
import gov.va.isaac.config.profiles.UserProfile;
import gov.va.isaac.config.profiles.UserProfileDefaults;
import gov.va.isaac.config.profiles.UserProfileManager;
import gov.va.isaac.config.users.InvalidUserException;
import gov.va.isaac.gui.util.TextErrorColorHelper;
import gov.va.isaac.util.OTFUtility;
import gov.va.isaac.util.Utility;
import gov.va.isaac.util.ValidBooleanBinding;
import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlySetProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.concurrent.Task;
import javafx.application.Platform;

import org.apache.commons.lang3.time.DateUtils;
import org.ihtsdo.otf.tcc.api.concept.ConceptChronicleBI;
import org.ihtsdo.otf.tcc.api.concept.ConceptVersionBI;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionException;
import org.ihtsdo.otf.tcc.api.coordinate.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.javafx.collections.ObservableSetWrapper;

/**
 * {@link ViewCoordinatePreferencesPluginViewController}
 *
 * @author <a href="mailto:joel.kniaz@gmail.com">Joel Kniaz</a>
 */

public class ViewCoordinatePreferencesPluginViewController {
	private final static Logger log = LoggerFactory.getLogger(ViewCoordinatePreferencesPluginViewController.class);

	/**
	 * @author <a href="mailto:joel.kniaz@gmail.com">Joel Kniaz</a>
	 *
	 * For populating dateSelectorComboBox
	 */
	private enum DateSelectionMethod {
		SPECIFY("Specify Date"),
		USE_LATEST("Use Latest Date");

		private final String displayName;

		private DateSelectionMethod(String dn) {
			displayName = dn;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	@FXML GridPane gridPaneInTab;
	@FXML GridPane topGridPane;
	@FXML GridPane bottomGridPane;
	@FXML DatePicker datePicker;
	@FXML ComboBox<DateSelectionMethod> dateSelectionMethodComboBox;
	@FXML ComboBox<UUID> pathComboBox;
	@FXML ListView<SelectableModule> selectableModuleListView;
	@FXML VBox statusesToggleGroupVBox;
	@FXML VBox statedInferredToggleGroupVBox;

	private boolean contentLoaded = false;

	private ToggleGroup statusesToggleGroup = null;
	private ToggleGroup statedInferredToggleGroup = null;

	private ObservableList<SelectableModule> selectableModules = null;

	private ValidBooleanBinding allValid_ = null;

	// ValidBooleanBinding allValid_ dependencies
	private final ObjectProperty<StatedInferredOptions> currentStatedInferredOptionProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<UUID> currentPathProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Long> currentTimeProperty = new SimpleObjectProperty<>();
	private final SimpleSetProperty<Status> currentStatusesProperty = new SimpleSetProperty<>(new ObservableSetWrapper<Status>(new HashSet<Status>()));
	private final SelectableModule allModulesMarker = new SelectableModule();
	private final ObservableSet<UUID> selectedModules = FXCollections.observableSet(new HashSet<UUID>());

	private final List<RadioButton> statedInferredOptionButtons = new ArrayList<>();

	private RadioButton activeStatusButton;
	private RadioButton inactiveStatusButton;
	private RadioButton activeAndInactiveStatusButton;

	protected static ViewCoordinatePreferencesPluginViewController construct() throws IOException {
		// Load from FXML.
		URL resource = ViewCoordinatePreferencesPluginViewController.class.getResource("ViewCoordinatePreferencesPluginView.fxml");
		log.debug("Loaded URL {}", resource);
		FXMLLoader loader = new FXMLLoader(resource);
		loader.load();
		return loader.getController();
	}

	@FXML
	void initialize() {
		assert gridPaneInTab != null : "fx:id=\"gridPaneInTab\" was not injected: check your FXML file 'ViewCoordinatePreferencesPluginView.fxml'.";
		assert topGridPane != null : "fx:id=\"topGridPane\" was not injected: check your FXML file 'ViewCoordinatePreferencesPluginView.fxml'.";
		assert bottomGridPane != null : "fx:id=\"bottomGridPane\" was not injected: check your FXML file 'ViewCoordinatePreferencesPluginView.fxml'.";
		assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'ViewCoordinatePreferencesPluginView.fxml'.";
		assert dateSelectionMethodComboBox != null : "fx:id=\"dateSelectionMethodComboBox\" was not injected: check your FXML file 'ViewCoordinatePreferencesPluginView.fxml'.";
		assert pathComboBox != null : "fx:id=\"pathComboBox\" was not injected: check your FXML file 'ViewCoordinatePreferencesPluginView.fxml'.";
		assert selectableModuleListView != null : "fx:id=\"selectableModuleListView\" was not injected: check your FXML file 'ViewCoordinatePreferencesPluginView.fxml'.";
		assert statusesToggleGroupVBox != null : "fx:id=\"statusesToggleGroupVBox\" was not injected: check your FXML file 'ViewCoordinatePreferencesPluginView.fxml'.";
		assert statedInferredToggleGroupVBox != null : "fx:id=\"statedInferredToggleGroupVBox\" was not injected: check your FXML file 'ViewCoordinatePreferencesPluginView.fxml'.";

		RowConstraints gridPaneRowConstraints = new RowConstraints();
		gridPaneRowConstraints.setVgrow(Priority.NEVER);

		addGridPaneRowConstraintsToAllRows(gridPaneInTab, gridPaneRowConstraints);
		addGridPaneRowConstraintsToAllRows(topGridPane, gridPaneRowConstraints);
		addGridPaneRowConstraintsToAllRows(bottomGridPane, gridPaneRowConstraints);

		//		currentPathProperty.addListener((observable, oldValue, newValue) -> {
		//			log.debug("currentPathProperty changed from {} to {}", oldValue, newValue);
		//		});
		//		currentStatedInferredOptionProperty.addListener((observable, oldValue, newValue) -> {
		//			log.debug("currentStatedInferredOptionProperty changed from {} to {}", oldValue, newValue);
		//		});
		//		currentTimeProperty.addListener((observable, oldValue, newValue) -> {
		//			log.debug("currentTimeProperty changed from {} to {}", oldValue, newValue);
		//		});
		//		currentStatusesProperty.addListener((observable, oldValue, newValue) -> {
		//			log.debug("currentStatusesProperty changed from {} to {}", Arrays.toString(oldValue.toArray()), Arrays.toString(newValue.toArray()));
		//		});

		initializeDatePicker();
		initializeDateSelectionMethodComboBox();
		initializePathComboBox();
		initializeSelectableModuleListView();
		initializeStatusesToggleGroup();
		initializeStatedInferredToggleGroup();
		initializeValidBooleanBinding();
	}

	private void setCurrentTimePropertyFromDatePicker() {
		Long dateSelected = null;
		if (datePicker.getValue() != null) {
			Instant instant = Instant.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()));
			dateSelected = getStartOfNextDay(Date.from(instant)).getTime();
		} else {
			dateSelected = Long.MAX_VALUE;
		}
		currentTimeProperty.set(dateSelected);
	}
	private void setDatePickerFromCurrentTimeProperty() {
		if (currentTimeProperty.get() != Long.MAX_VALUE) {
			datePicker.setValue(getPriorDay(new Date(currentTimeProperty.get())).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		} else {
			datePicker.setValue(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}
	}

	private void initializeDatePicker() {
		datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (dateSelectionMethodComboBox.getSelectionModel().getSelectedItem() == DateSelectionMethod.SPECIFY) {
				setCurrentTimePropertyFromDatePicker();
			}
		});
		//		datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
		//			log.debug("datePicker value changed from {} to {} while DateSelectionMethod={}", oldValue, newValue, dateSelectionMethodComboBox.getSelectionModel().getSelectedItem());
		//		});
		//		datePicker.setOnAction((event) -> {
		//			log.debug("DatePicker activated with value=" + datePicker.getValue() + ", selectionMode=" + dateSelectionMethodComboBox.getSelectionModel().getSelectedItem() + " and current time=" + currentTimeProperty.get());
		//		});
		datePicker.setTooltip(new Tooltip("Enter valid date or click to select date from calendar representing\nan historical snapshot version of the database"));
	}

	private void initializeDateSelectionMethodComboBox() {
		dateSelectionMethodComboBox.setCellFactory((param) -> {
			final ListCell<DateSelectionMethod> cell = new ListCell<DateSelectionMethod>() {
				@Override
				protected void updateItem(DateSelectionMethod selectionMethod, boolean emptyRow) {
					super.updateItem(selectionMethod, emptyRow);
					if(selectionMethod == null) {
						setText(null);
					} else {
						setText(selectionMethod.getDisplayName());
					}
				}
			};
			return cell;
		});

		dateSelectionMethodComboBox.setButtonCell(new ListCell<DateSelectionMethod>() {
			@Override
			protected void updateItem(DateSelectionMethod selectionMethod, boolean emptyRow) {
				super.updateItem(selectionMethod, emptyRow); 
				if (emptyRow) {
					setText("");
				} else {
					switch (selectionMethod) {
					case SPECIFY:
						datePicker.setVisible(true);
						setText(selectionMethod.getDisplayName());
						//						log.debug("dateSelectorComboBox button cell set to " + getText() + ", datePicker has value=" + datePicker.getValue() + ", selectionMode=" + dateSelectionMethodComboBox.getSelectionModel().getSelectedItem() + " and current time=" + currentTimeProperty.get());

						// This should change if default time ever changes
						dateSelectionMethodComboBox.setTooltip(new Tooltip(getText() + " is selected.  Use date picker control to specify a date\nin the past representing an historical snapshot version of the database\nor click and select " + DateSelectionMethod.USE_LATEST.getDisplayName() + " to always use latest.\nDefault is " + DateSelectionMethod.USE_LATEST.getDisplayName() + "."));
						break;
					case USE_LATEST:
						datePicker.setVisible(false);
						setText(selectionMethod.getDisplayName());
						//						log.debug("dateSelectorComboBox button cell set to " + getText() + ", datePicker has value=" + datePicker.getValue() + ", selectionMode=" + dateSelectionMethodComboBox.getSelectionModel().getSelectedItem() + " and current time=" + currentTimeProperty.get());

						dateSelectionMethodComboBox.setTooltip(new Tooltip(getText() + " is selected, so latest (most recent) date will always be used.\nClick and select " + DateSelectionMethod.SPECIFY.getDisplayName() + " to use date picker control to specify a date\nin the past representing an historical snapshot version of the database.\nDefault is " + DateSelectionMethod.USE_LATEST.getDisplayName() + "."));
						break;
					default:
						// Should never happen
						throw new IllegalArgumentException("Failed setting dateSelectorComboBox ButtonCell. Unsupported "+ selectionMethod.getClass().getName() + " value " + selectionMethod.name() + ".  Must be " + DateSelectionMethod.SPECIFY.name() + " or " + DateSelectionMethod.USE_LATEST.name() + ".");
					}
				}
			}
		});
		dateSelectionMethodComboBox.setOnAction((event)-> {	
			//			log.debug("dateSelectorComboBox activated. datePicker has value=" + datePicker.getValue() + ", selectionMode=" + dateSelectionMethodComboBox.getSelectionModel().getSelectedItem() + " and current time=" + currentTimeProperty.get());

			switch (dateSelectionMethodComboBox.getSelectionModel().getSelectedItem()) {
			case SPECIFY:
				if (currentTimeProperty.get() == Long.MAX_VALUE) {
					setCurrentTimePropertyFromDatePicker();
				} else {
					setDatePickerFromCurrentTimeProperty();
				}
				break;
			case USE_LATEST:
				currentTimeProperty.set(Long.MAX_VALUE);
				break;
			default:
				// Should never happen
				throw new IllegalArgumentException("Unsupported "+ dateSelectionMethodComboBox.getSelectionModel().getSelectedItem().getClass().getName() + " value " + dateSelectionMethodComboBox.getSelectionModel().getSelectedItem().name() + ".  Must be " + DateSelectionMethod.SPECIFY.name() + " or " + DateSelectionMethod.USE_LATEST.name() + ".");
			}
		});
		dateSelectionMethodComboBox.getItems().addAll(DateSelectionMethod.values());
		dateSelectionMethodComboBox.getSelectionModel().select(DateSelectionMethod.USE_LATEST);
	}

	private void initializePathComboBox() {
		pathComboBox.setCellFactory((param) -> {
			final ListCell<UUID> cell = new ListCell<UUID>() {
				@Override
				protected void updateItem(UUID c, boolean emptyRow) {
					super.updateItem(c, emptyRow);
					if(c == null) {
						setText(null);
					} else {
						String desc = OTFUtility.getDescription(c);
						setText(desc);
					}
				}
			};
			return cell;
		});
		pathComboBox.setButtonCell(new ListCell<UUID>() {
			@Override
			protected void updateItem(UUID c, boolean emptyRow) {
				super.updateItem(c, emptyRow); 
				if (emptyRow) {
					setText("");
				} else {
					String desc = OTFUtility.getDescription(c);
					//					log.debug("Setting path button cell to \"" + desc + "\"");
					setText(desc);
				}
			}
		});
		//		pathComboBox.getButtonCell().textProperty().addListener((obs, oldValue, newValue) -> {
		//			if (newValue != null) {
		//				try {
		//					UUID testUuid = UUID.fromString(newValue);
		//					log.warn("Setting path button cell to a UUID string \"" + testUuid + "\"!");
		//				} catch (Exception e) {
		//					// ignore
		//				}
		//			}
		//		});
		currentPathProperty.bind(pathComboBox.getSelectionModel().selectedItemProperty());
	}

	private void initializeSelectableModuleListView() {
		allModulesMarker.selectedProperty().set(true); // default only.  may be changed in getContent()
		selectableModuleListView.setCellFactory(CheckBoxListCell.forListView(SelectableModule::selectedProperty, new StringConverter<SelectableModule>() {
			@Override
			public String toString(SelectableModule object) {
				return object.getDescription();
			}

			@Override
			public SelectableModule fromString(String string) {
				return null;
			}
		}));
		selectableModuleListView.setTooltip(new Tooltip("Select one or more modules to enable filtering for selected modules\nor deselect all to disable filtering by module.\nDefault module list is " + Arrays.toString(getDefaultModules().toArray(new UUID[getDefaultModules().size()]))));
	}

	private void initializeStatusesToggleGroup() {
		statusesToggleGroup = new ToggleGroup();
		Tooltip statusButtonsTooltip = new Tooltip("Default Status(es) is " + getDefaultStatuses());
		activeStatusButton = new RadioButton();
		activeStatusButton.setText("Active");
		activeStatusButton.setTooltip(statusButtonsTooltip);
		statusesToggleGroup.getToggles().add(activeStatusButton);
		activeStatusButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				currentStatusesProperty.get().clear();
				currentStatusesProperty.add(Status.ACTIVE);
			}
		});
		statusesToggleGroupVBox.getChildren().add(activeStatusButton);

		inactiveStatusButton = new RadioButton();
		inactiveStatusButton.setText("Inactive");
		inactiveStatusButton.setTooltip(statusButtonsTooltip);
		statusesToggleGroup.getToggles().add(inactiveStatusButton);
		inactiveStatusButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				currentStatusesProperty.get().clear();
				currentStatusesProperty.add(Status.INACTIVE);
			}
		});
		statusesToggleGroupVBox.getChildren().add(inactiveStatusButton);

		activeAndInactiveStatusButton = new RadioButton();
		activeAndInactiveStatusButton.setText("All");
		activeAndInactiveStatusButton.setTooltip(statusButtonsTooltip);
		statusesToggleGroup.getToggles().add(activeAndInactiveStatusButton);
		activeAndInactiveStatusButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				currentStatusesProperty.get().clear();
				currentStatusesProperty.add(Status.ACTIVE);
				currentStatusesProperty.add(Status.INACTIVE);
			}
		});
		statusesToggleGroupVBox.getChildren().add(activeAndInactiveStatusButton);
	}

	private void initializeStatedInferredToggleGroup() {
		statedInferredToggleGroup = new ToggleGroup(); //Stated / Inferred

		for (StatedInferredOptions option : StatedInferredOptions.values()) {
			RadioButton optionButton = new RadioButton();
			if (option == StatedInferredOptions.STATED) {
				optionButton.setText("Stated");
			}
			else if (option == StatedInferredOptions.INFERRED) {
				optionButton.setText("Inferred");
			}
			else {
				throw new RuntimeException("oops");
			}
			optionButton.setUserData(option);
			optionButton.setTooltip(new Tooltip("Default StatedInferredOption is " + getDefaultStatedInferredOption()));
			statedInferredToggleGroup.getToggles().add(optionButton);
			statedInferredToggleGroupVBox.getChildren().add(optionButton);
			statedInferredOptionButtons.add(optionButton);
		}
		statedInferredToggleGroup.selectedToggleProperty().addListener(
				(observable, oldValue, newValue) -> currentStatedInferredOptionProperty.set((StatedInferredOptions)newValue.getUserData()));
	}

	private void initializeValidBooleanBinding() {
		allValid_ = new ValidBooleanBinding() {
			{
				bind(
						currentStatedInferredOptionProperty,
						currentPathProperty,
						currentTimeProperty,
						currentStatusesProperty,
						selectedModules,
						allModulesMarker.selectedProperty(),
						dateSelectionMethodComboBox.getSelectionModel().selectedItemProperty()
						);
				setComputeOnInvalidate(true);
			}

			@Override
			protected boolean computeValue() {
				if (currentStatedInferredOptionProperty.get() == null) {
					this.setInvalidReason("Null/unset/unselected StatedInferredOption");
					for (RadioButton button : statedInferredOptionButtons) {
						TextErrorColorHelper.setTextErrorColor(button);
					}
					return false;
				} else {
					for (RadioButton button : statedInferredOptionButtons) {
						TextErrorColorHelper.clearTextErrorColor(button);
					}
				}
				if (currentPathProperty.get() == null) {
					this.setInvalidReason("Null/unset/unselected path");
					TextErrorColorHelper.setTextErrorColor(pathComboBox);

					return false;
				} else {
					TextErrorColorHelper.clearTextErrorColor(pathComboBox);
				}
				if (OTFUtility.getConceptVersion(currentPathProperty.get()) == null) {
					this.setInvalidReason("Invalid path");
					TextErrorColorHelper.setTextErrorColor(pathComboBox);

					return false;
				} else {
					TextErrorColorHelper.clearTextErrorColor(pathComboBox);
				}
				if (currentStatusesProperty.get() == null || currentStatusesProperty.get().size() < 1) {
					this.setInvalidReason("Status unset");
					TextErrorColorHelper.setTextErrorColor(activeStatusButton);
					TextErrorColorHelper.setTextErrorColor(inactiveStatusButton);
					TextErrorColorHelper.setTextErrorColor(activeAndInactiveStatusButton);

					return false;
				} else {
					TextErrorColorHelper.clearTextErrorColor(activeStatusButton);
					TextErrorColorHelper.clearTextErrorColor(inactiveStatusButton);
					TextErrorColorHelper.clearTextErrorColor(activeAndInactiveStatusButton);
				}
				if (currentTimeProperty.get() == null)
				{
					this.setInvalidReason("View Coordinate Time is unset");
					TextErrorColorHelper.setTextErrorColor(dateSelectionMethodComboBox);
					TextErrorColorHelper.setTextErrorColor(datePicker);
					return false;
				} else {
					TextErrorColorHelper.clearTextErrorColor(dateSelectionMethodComboBox);
					TextErrorColorHelper.clearTextErrorColor(datePicker);
				}
				if (currentTimeProperty.get() == Long.MAX_VALUE && dateSelectionMethodComboBox.getSelectionModel().selectedItemProperty().get() == DateSelectionMethod.SPECIFY)
				{
					this.setInvalidReason("View Coordinate Time is unselected while selection method is not Latest");
					TextErrorColorHelper.setTextErrorColor(dateSelectionMethodComboBox);
					TextErrorColorHelper.setTextErrorColor(datePicker);
					return false;
				} else {
					TextErrorColorHelper.clearTextErrorColor(dateSelectionMethodComboBox);
					TextErrorColorHelper.clearTextErrorColor(datePicker);
				}

				if (allModulesMarker.selectedProperty().get()
						&& selectedModules.size() > 0) {
					this.setInvalidReason("ALL module cannot be selected while any (currently " + selectedModules.size() + ") specific module selected");
					TextErrorColorHelper.setTextErrorColor(selectableModuleListView);
					return false;
				} else if (! allModulesMarker.selectedProperty().get()
						&& selectedModules.size() == 0) {
					this.setInvalidReason("ALL module must be selected if no specific module(s) selected");
					TextErrorColorHelper.setTextErrorColor(selectableModuleListView);
					return false;
				} else {
					TextErrorColorHelper.clearTextErrorColor(selectableModuleListView);
				}

				this.clearInvalidReason();
				return true;
			}
		};
	}

	public Region getContent() {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				log.debug("initializing content");

				try {
					if (! contentLoaded) {
						contentLoaded = true;

						// Populate selectableModules
						final ConceptVersionBI moduleRootConcept = OTFUtility.getConceptVersion(IsaacMetadataAuxiliaryBinding.MODULE.getPrimodialUuid());
						final Set<ConceptVersionBI> moduleConcepts = new HashSet<>();
						try {
							moduleConcepts.addAll(OTFUtility.getAllChildrenOfConcept(moduleRootConcept.getNid(), false));
						} catch (IOException | ContradictionException e1) {
							// TODO add error dialog
							log.error("Failed loading module concepts as children of " + moduleRootConcept, e1);
							e1.printStackTrace();
						}
						List<SelectableModule> modules = new ArrayList<>();
						for (ConceptVersionBI cv : moduleConcepts) {
							modules.add(new SelectableModule(cv.getNid()));
						}
						selectableModules = FXCollections.observableArrayList(modules);

						allModulesMarker.selected.addListener((observable, oldValue, newValue) -> {
							if (newValue) {
								for (SelectableModule module : selectableModules) {
									module.selectedProperty().set(false);
								}
							}
						});
						selectableModules.forEach(selectableModule -> selectableModule.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
							if (isSelected) {
								if (! wasSelected) {
									log.debug("Adding module nid={}, uuid={}, desc={}", selectableModule.getNid(), selectableModule.getUuid(), selectableModule.getDescription());
									selectedModules.add(selectableModule.getUuid());
									allModulesMarker.selectedProperty().set(false);
								}
							} else {
								if (wasSelected) {
									log.debug("Removing module nid={}, uuid={}, desc={}", selectableModule.getNid(), selectableModule.getUuid(), selectableModule.getDescription());
									selectedModules.remove(selectableModule.getUuid());

									if (selectedModules.size() == 0) {
										allModulesMarker.selectedProperty().set(true);
									}
								}
							}
						}));
						selectableModuleListView.getItems().addAll(selectableModules);
						selectableModuleListView.getItems().add(allModulesMarker);

						Runnable work = new Runnable() {
							@Override
							public void run() {
								pathComboBox.setTooltip(new Tooltip("Default path is \"" + OTFUtility.getDescription(getDefaultPath()) + "\""));
							}
						};
						if (Platform.isFxApplicationThread()) {
							work.run();
						}
						else {
							Platform.runLater(work);
						}
						
						pathComboBox.getItems().clear();
						pathComboBox.getItems().addAll(getPathOptions());
					}

					// Reload persisted values every time

					UserProfile loggedIn = ExtendedAppContext.getCurrentlyLoggedInUserProfile();
					pathComboBox.getSelectionModel().select(loggedIn.getViewCoordinatePath());

					// Reload storedStatedInferredOption
					final StatedInferredOptions storedStatedInferredOption = getStoredStatedInferredOption();
					for (Toggle toggle : statedInferredToggleGroup.getToggles()) {
						if (toggle.getUserData() == storedStatedInferredOption) {
							toggle.setSelected(true);
						}
					}

					// Reload storedStatuses
					final Set<Status> storedStatuses = getStoredStatuses();
					if (storedStatuses.contains(Status.ACTIVE) && storedStatuses.contains(Status.INACTIVE)) {
						statusesToggleGroup.selectToggle(activeAndInactiveStatusButton);
					} else if (storedStatuses.contains(Status.ACTIVE)) {
						statusesToggleGroup.selectToggle(activeStatusButton);
					} else if (storedStatuses.contains(Status.INACTIVE)) {
						statusesToggleGroup.selectToggle(inactiveStatusButton);
					} else if (storedStatuses.size() == 0) {
						log.warn("UserProfile does not contain any view coordinate Status values");
					} else {
						log.error("UserProfile contains unsupported view coordinate Status values: {}", storedStatuses.toArray());
					}

					// Reload storedModules
					final Set<UUID> storedModuleUuids = getStoredModules();
					if (storedModuleUuids.size() == 0) {
						allModulesMarker.setSelected(true);
					} else {
						// Check to make sure that stored UUID refers to an existing, known module
						for (UUID storedModuleUuid : storedModuleUuids) {
							boolean foundStoredUuidModuleInSelectableModules = false;
							for (SelectableModule selectableModule : selectableModules) {
								if (storedModuleUuid.equals(selectableModule.getUuid())) {
									foundStoredUuidModuleInSelectableModules = true;
									break;
								}
							}

							if (! foundStoredUuidModuleInSelectableModules) {
								log.error("Loaded module (uuid={}) from user preferences that does not currently exist", storedModuleUuid);
								// TODO add error or warning dialog
							}
						}
						for (SelectableModule module : selectableModules) {
							if (storedModuleUuids.contains(module.getUuid())) {
								module.setSelected(true);
							} else {
								module.setSelected(false);
							}
						}
					}

					Long storedTime = getStoredTime();
					if (storedTime.equals(Long.MAX_VALUE)) {
						dateSelectionMethodComboBox.getSelectionModel().select(DateSelectionMethod.USE_LATEST);
						currentTimeProperty.set(Long.MAX_VALUE);
						datePicker.setValue(LocalDate.now());
					} else {
						dateSelectionMethodComboBox.getSelectionModel().select(DateSelectionMethod.SPECIFY);
						currentTimeProperty.set(storedTime);
						setDatePickerFromCurrentTimeProperty();
					}

					return null;
				}
				catch (Exception e) {
					log.error("initContent() task caught " + e.getClass().getName() + " " + e.getLocalizedMessage(), e);
					e.printStackTrace();
					throw e;
				}
			}

			@Override
			protected void succeeded()
			{
				log.debug("Content initialization succeeded");
			}

			@Override
			protected void failed() {
				Throwable ex = getException();
				String title = "Unexpected error initializing content";
				String msg = ex.getClass().getName();
				log.error(title, ex);
				AppContext.getCommonDialogs().showErrorDialog(title, msg, ex.getMessage());
			}
		};

		Utility.execute(task);

		return gridPaneInTab;
	}

	public ReadOnlyStringProperty validationFailureMessageProperty() {
		return allValid_.getReasonWhyInvalid();
	}

	public void save() throws IOException {
		log.debug("Saving ViewCoordinatePreferencesPluginView data");
		UserProfile loggedIn = ExtendedAppContext.getCurrentlyLoggedInUserProfile();

		//Path Property
		log.debug("Setting stored VC path (currently \"{}\") to {}", loggedIn.getViewCoordinatePath(), currentPathProperty().get()); 
		loggedIn.setViewCoordinatePath(currentPathProperty().get());

		//Stated/Inferred Policy
		log.debug("Setting stored VC StatedInferredPolicy (currently \"{}\") to {}", loggedIn.getStatedInferredPolicy(), currentStatedInferredOptionProperty().get()); 
		loggedIn.setStatedInferredPolicy(currentStatedInferredOptionProperty().get());

		//Time Property
		log.debug("Setting stored VC time to :" + currentTimeProperty().get());
		loggedIn.setViewCoordinateTime(currentTimeProperty().get());

		//Statuses Property
		log.debug("Setting stored VC statuses to :" + currentStatusesProperty().get());
		loggedIn.setViewCoordinateStatuses(currentStatusesProperty().get());

		//Modules Property
		log.debug("Setting stored VC modules to :" + selectedModules);
		loggedIn.setViewCoordinateModules(selectedModules);

		try {
			AppContext.getService(UserProfileManager.class).saveChanges(loggedIn);
		} catch (InvalidUserException e) {
			String msg = "Caught " + e.getClass().getName() + " " + e.getLocalizedMessage() + " attempting to save UserProfile for " + getClass().getName();

			log.error(msg, e);
			throw new IOException(msg, e);
		}
	}

	public static Date getEndOfDay(Date date) {
		return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
	}

	public static Date getStartOfDay(Date date) {
		return DateUtils.truncate(date, Calendar.DATE);
	}
	public static Date getStartOfNextDay(Date date) {
		return getStartOfDay(DateUtils.addDays(date, 1));
	}
	public static Date getPriorDay(Date date) {
		return DateUtils.addDays(date, -1);
	}

	protected Collection<UUID> getPathOptions() {
		List<UUID> list = new ArrayList<>();

		try {
			List<ConceptChronicleBI> pathConcepts = OTFUtility.getPathConcepts();
			for (ConceptChronicleBI cc : pathConcepts) {
				list.add(cc.getPrimordialUuid());
			}
		} catch (IOException | ContradictionException e) {
			log.error("Failed loading path concepts. Caught {} {}", e.getClass().getName(), e.getLocalizedMessage());
			e.printStackTrace();
		}
		// Add currently-stored value to list of options, if not already there
		UUID storedPath = getStoredPath();
		if (storedPath != null && ! list.contains(storedPath)) {
			list.add(storedPath);
		}

		return list;
	}

	protected Long getStoredTime() {
		UserProfile loggedIn = ExtendedAppContext.getCurrentlyLoggedInUserProfile();
		return loggedIn.getViewCoordinateTime();
	}

	protected UUID getStoredPath() {
		UserProfile loggedIn = ExtendedAppContext.getCurrentlyLoggedInUserProfile();
		return loggedIn.getViewCoordinatePath();
	}

	protected StatedInferredOptions getStoredStatedInferredOption() {
		UserProfile loggedIn = ExtendedAppContext.getCurrentlyLoggedInUserProfile();
		return loggedIn.getStatedInferredPolicy();
	}

	protected Set<Status> getStoredStatuses() {
		UserProfile loggedIn = ExtendedAppContext.getCurrentlyLoggedInUserProfile();
		return loggedIn.getViewCoordinateStatuses();
	}

	protected Set<UUID> getStoredModules() {
		UserProfile loggedIn = ExtendedAppContext.getCurrentlyLoggedInUserProfile();
		return loggedIn.getViewCoordinateModules();
	}
	protected Set<UUID> getDefaultModules() {
		return UserProfileDefaults.getDefaultViewCoordinateModules();
	}

	protected UUID getDefaultPath() {
		return UserProfileDefaults.getDefaultViewCoordinatePath();
	}

	protected Long getDefaultTime() {
		return UserProfileDefaults.getDefaultViewCoordinateTime();
	}

	protected StatedInferredOptions getDefaultStatedInferredOption() {
		return UserProfileDefaults.getDefaultStatedInferredPolicy();
	}
	protected Set<Status> getDefaultStatuses() {
		return UserProfileDefaults.getDefaultViewCoordinateStatuses();
	}

	public ReadOnlySetProperty<Status> currentStatusesProperty() {
		return currentStatusesProperty;
	}
	public ReadOnlyObjectProperty<Long> currentTimeProperty() {
		return currentTimeProperty;
	}

	public ReadOnlyObjectProperty<StatedInferredOptions> currentStatedInferredOptionProperty() {
		return currentStatedInferredOptionProperty;
	}

	public ReadOnlyObjectProperty<UUID> currentPathProperty() {
		return currentPathProperty;
	}

	private class SelectableModule {
		private final IntegerProperty nid = new SimpleIntegerProperty();
		private final BooleanProperty selected = new SimpleBooleanProperty(false);
		private final String description;
		private final UUID uuid;

		/**
		 * Constructor for returning SelectableModule representing ALL modules
		 */
		private SelectableModule() {
			nid.set(0);
			description = "ALL";
			uuid = null;
		}

		public SelectableModule(Integer nid) {
			this.nid.set(nid);

			String desc = null;
			try {
				desc = OTFUtility.getDescription(nid);
			} catch (Exception e) {
				log.error("Failed to set description for concept with nid={}", nid);
			}
			description = desc;

			UUID aUuid = null;
			try {
				ConceptVersionBI cv = OTFUtility.getConceptVersion(nid);
				aUuid = cv.getPrimordialUuid();
			} catch (Exception e) {
				log.error("Failed to set uuid for concept with nid={} and desc={}", nid, description);
			}
			uuid = aUuid;
		}

		public Integer getNid() {
			return nid.get();
		}
		public BooleanProperty selectedProperty() {
			return selected;
		}
		public void setSelected(boolean selected) {
			this.selected.set(selected);
		}
		public String getDescription() {
			return description;
		}
		public UUID getUuid() {
			return uuid;
		}
	}

	private static int getNumGridPaneRows(GridPane gridPane) {
		int numGridPaneRows = 0;
		for (javafx.scene.Node node : gridPane.getChildren()) {
			if (node != null) {
				Integer rowIndex = GridPane.getRowIndex(node);
				if (rowIndex != null && rowIndex >= numGridPaneRows) {
					++numGridPaneRows;
				}
			}
		}

		return numGridPaneRows;
	}
	private static void addGridPaneRowConstraintsToAllRows(GridPane gridPane, RowConstraints rowConstraints) {
		final int numGridPaneRows = getNumGridPaneRows(gridPane);
		for (int i = 0; i < numGridPaneRows; ++i) {
			gridPane.getRowConstraints().add(i, rowConstraints);
		}
	}
}
