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
package gov.va.isaac;

import gov.va.isaac.config.profiles.UserProfile;
import gov.va.isaac.config.profiles.UserProfileManager;
import org.ihtsdo.otf.tcc.api.store.TerminologyStoreDI;

/**
 * 
 * Note - this just contains convenience methods at this point... callers can go
 * directly to HK2 if they prefer.
 * 
 * This 'extended' class provides access to methods that require APIs that aren't available (due to dependencies)
 * in the parent 'AppContext' which is purposefully packaged in a project with the bare minimum of external dependencies.
 * 
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */
public class ExtendedAppContext extends AppContext
{
	//A nasty little hack, so that I can do some trickery during junit testing / mock testing
	//to substitute a different UserProfileManager - without having to go through the effort to create all of the interfaces that would require
	//I tweak this with reflection....
	private static Class<UserProfileManager> userProfileManagerClass = UserProfileManager.class;
	
	public static TerminologyStoreDI getDataStore()
	{
		return getService(TerminologyStoreDI.class);
	}
	
	/**
	 * @see UserProfileManager#getCurrentlyLoggedInUserProfile()
	 */
	public static UserProfile getCurrentlyLoggedInUserProfile()
	{
		return getService(userProfileManagerClass).getCurrentlyLoggedInUserProfile();
	}
	
	/**
	 * @see UserProfileManager#getCurrentlyLoggedInUser()
	 */
	public static String getCurrentlyLoggedInUser()
	{
		return getService(userProfileManagerClass).getCurrentlyLoggedInUser();
	}
}
