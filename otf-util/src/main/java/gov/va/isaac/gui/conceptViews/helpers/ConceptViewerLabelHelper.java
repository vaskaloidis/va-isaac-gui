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
 *	 http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.va.isaac.gui.conceptViews.helpers;

import gov.va.isaac.AppContext;
import gov.va.isaac.gui.conceptViews.EnhancedConceptView;
import gov.va.isaac.gui.conceptViews.helpers.ConceptViewerHelper.ComponentType;
import gov.va.isaac.gui.conceptViews.modeling.ConceptModelingPopup;
import gov.va.isaac.gui.util.CustomClipboard;
import gov.va.isaac.gui.util.Images;
import gov.va.isaac.interfaces.gui.views.PopupConceptViewI;
//import gov.va.isaac.workflow.gui.ConceptDetailWorkflow;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import org.ihtsdo.otf.tcc.api.chronicle.ComponentVersionBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
* @author <a href="jefron@apelon.com">Jesse Efron</a>
*/
public class ConceptViewerLabelHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConceptViewerLabelHelper.class);
	
	private AnchorPane pane;
	private boolean isWindow = false;
	private ObservableList<Integer> previousConceptStack;
	
	private ConceptViewerTooltipHelper tooltipHelper = new ConceptViewerTooltipHelper();

	private PopupConceptViewI conceptView = null;

	public ConceptViewerLabelHelper(PopupConceptViewI conceptView) {
		this.conceptView = conceptView;
	}

	// Create Labels
	public Label createLabel(ComponentVersionBI comp, String txt, ComponentType type, int refConNid) {
		Label label = new Label();
		label.setFont(new Font(18));

		initializeLabel(label, comp, type, txt, refConNid);
		
		return label;
	}

	public void initializeLabel(Label label, ComponentVersionBI comp, ComponentType type, String txt, int refConNid) {
		label.setText(txt);
		
		if (refConNid != 0) {
			label.setTextFill(Color.BLUE);
		} else {
			label.setTextFill(Color.BLACK);
		}
		
		createContextMenu(label, txt, comp, refConNid, type);

		// Tooltip Handling
		tooltipHelper.setDefaultTooltip(label, comp, type);
		label.addEventHandler(MouseEvent.MOUSE_ENTERED, tooltipHelper.getCompTooltipEnterHandler(comp, type));
		label.addEventHandler(MouseEvent.MOUSE_EXITED, tooltipHelper.getCompTooltipExitHandler(comp, type));
	}

	
	
	// Create Context Menus
	private void createContextMenu(Label label, String txt, ComponentVersionBI comp, int refConNid, ComponentType type) {
		final ContextMenu rtClickMenu = new ContextMenu();

		Menu copytoClipboardItem = new Menu("Copy to Clipboard");
		MenuItem copyTextItem = new MenuItem("Text");
		copyTextItem.setGraphic(Images.COPY.createImageView());
		copyTextItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				CustomClipboard.set(txt);
			}
		});
				
		MenuItem copyContentItem = new MenuItem("Full Component Content");
		copyContentItem.setGraphic(Images.COPY.createImageView());
		copyContentItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				CustomClipboard.set(label.getTooltip().getText());
			}
		});
		
		copytoClipboardItem.getItems().addAll(copyTextItem, copyContentItem);
		rtClickMenu.getItems().add(copytoClipboardItem);

		
		// Enable copying of component's various Ids
		if (comp != null) {
			if (type != ComponentType.CONCEPT) {
				Menu modifyComponentMenu = addModifyMenus(comp, type);
				rtClickMenu.getItems().add(modifyComponentMenu);
			}

			Menu copyIdMenu = addIdMenus(comp);
			copytoClipboardItem.getItems().add(copyIdMenu);
		}

		// Enable changing concept to Reference Concept
		if (refConNid != 0) {
			assert(comp != null);
			
			if (isWindow) {
				MenuItem viewItem = new MenuItem("Open Concept");
				viewItem.setGraphic(Images.CONCEPT_VIEW.createImageView());
				viewItem.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						previousConceptStack.add(comp.getConceptNid());
						if (conceptView == null) {
							AppContext.getService(EnhancedConceptView.class).setConcept(refConNid);
						} else {
							conceptView.setConcept(refConNid);
						}
					}
				});
				
				rtClickMenu.getItems().add(0, viewItem);
			}
	
			MenuItem viewNewItem = new MenuItem("Open Concept in New Panel");
			viewNewItem.setGraphic(Images.CONCEPT_VIEW.createImageView());
			viewNewItem.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					EnhancedConceptView cv = AppContext.getService(EnhancedConceptView.class);
				
					cv.setConcept(refConNid);
					cv.showView(pane.getScene().getWindow());
				}
			});
			
			rtClickMenu.getItems().add(1, viewNewItem);
		}

		label.setContextMenu(rtClickMenu);
	}

	Menu addModifyMenus(ComponentVersionBI comp, ComponentType type) {
		Menu modifyComponentMenu = new Menu("Modify Component");
		MenuItem editComponentMenu = new MenuItem("Edit");
		MenuItem retireComponentMenu = new MenuItem("Retire");
		modifyComponentMenu.getItems().addAll(editComponentMenu, retireComponentMenu);

		editComponentMenu.setGraphic(Images.EDIT.createImageView());
		editComponentMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				if (type == ComponentType.CONCEPT) {
					ConceptModelingPopup popup = AppContext.getService(ConceptModelingPopup.class);
					popup.finishInit(comp, type, conceptView);
					popup.showView(pane.getScene().getWindow());
				}
			}
		});

		retireComponentMenu.setGraphic(Images.DELETE.createImageView());
		retireComponentMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				if (type == ComponentType.CONCEPT) {
					// TODO: Retire Concept Wizard
				}
			}
		});

		return modifyComponentMenu;
	}

	Menu addIdMenus(ComponentVersionBI comp) {
		Menu copyIdMenu = new Menu("Copy Ids");
		MenuItem sctIdItem = new MenuItem("SctId");
		MenuItem uuidItem = new MenuItem("UUID");
		MenuItem nidItem = new MenuItem("Native Id");
		copyIdMenu.getItems().addAll(sctIdItem, uuidItem, nidItem);
		
		sctIdItem.setGraphic(Images.COPY.createImageView());
		sctIdItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				CustomClipboard.set(ConceptViewerHelper.getSctId(comp));
			}
		});

		uuidItem.setGraphic(Images.COPY.createImageView());
		uuidItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				CustomClipboard.set(comp.getPrimordialUuid().toString());
			}
		});

		nidItem.setGraphic(Images.COPY.createImageView());
		nidItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				CustomClipboard.set(Integer.toString(comp.getNid()));
			}
		});
		
		return copyIdMenu;		
	}

	// Setters&Getters
	public void setPane(AnchorPane simpleConceptPane) {
		pane = simpleConceptPane;
	}

	public void setIsWindow(boolean isWindow) {
		this.isWindow = isWindow;
	}

	public ObservableList<Integer> getPreviousConceptStack() {
		return previousConceptStack;
	}

	public void setPrevConStack(ObservableList<Integer> conceptHistoryStack) {
		previousConceptStack = conceptHistoryStack;
	}

}
