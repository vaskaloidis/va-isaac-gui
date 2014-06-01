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
package gov.va.isaac.gui.listview;

import gov.va.isaac.gui.util.Images;
import gov.va.isaac.interfaces.gui.ApplicationMenus;
import gov.va.isaac.interfaces.gui.MenuItemI;
import gov.va.isaac.interfaces.gui.views.DockedViewI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Window;
import javax.inject.Singleton;
import org.jvnet.hk2.annotations.Service;

/**
 * ListView
 * 
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */

@Service
@Singleton
public class ListBatchView implements DockedViewI
{
	ListBatchViewController lbvc_;

	private ListBatchView() throws IOException
	{
		// created by HK2
		lbvc_ = ListBatchViewController.init();
	}

	/**
	 * @see gov.va.isaac.interfaces.gui.views.DockedViewI#getView()
	 */
	@Override
	public Region getView()
	{
		return lbvc_.getRoot();
	}

	/**
	 * @see gov.va.isaac.interfaces.gui.views.IsaacViewI#getMenuBarMenus()
	 */
	@Override
	public List<MenuItemI> getMenuBarMenus()
	{
		// We don't currently have any custom menus with this view
		return new ArrayList<MenuItemI>();
	}

	/**
	 * @see gov.va.isaac.interfaces.gui.views.DockedViewI#getMenuBarMenuToShowView()
	 */
	@Override
	public MenuItemI getMenuBarMenuToShowView()
	{
		MenuItemI menuItem = new MenuItemI()
		{
			@Override
			public void handleMenuSelection(Window parent)
			{
				// noop
			}

			@Override
			public int getSortOrder()
			{
				return 6;
			}

			@Override
			public String getParentMenuId()
			{
				return ApplicationMenus.PANELS.getMenuId();
			}

			@Override
			public String getMenuName()
			{
				return "List View Panel";
			}

			@Override
			public String getMenuId()
			{
				return "listViewPanelMenuItem";
			}

			@Override
			public boolean enableMnemonicParsing()
			{
				return false;
			}
			
			/**
			 * @see gov.va.isaac.interfaces.gui.MenuItemI#getImage()
			 */
			@Override
			public Image getImage()
			{
				return Images.LIST_VIEW.getImage();
			}
		};
		return menuItem;
	}

	/**
	 * @see gov.va.isaac.interfaces.gui.views.DockedViewI#getViewTitle()
	 */
	@Override
	public String getViewTitle()
	{
		return "List";
	}
}
