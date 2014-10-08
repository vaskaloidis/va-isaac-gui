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
package gov.va.isaac.config.profiles;

import gov.va.isaac.config.changesets.ChangesetConfiguration;
import gov.va.isaac.config.generated.NewUserDefaults;
import gov.va.isaac.config.generated.RoleOption;
import gov.va.isaac.config.generated.User;
import gov.va.isaac.config.users.GenerateUsers;
import gov.va.isaac.config.users.InvalidUserException;
import gov.va.isaac.interfaces.utility.ServicesToPreloadI;
import gov.va.isaac.util.Utility;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.inject.Singleton;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link UserProfileManager}
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */
@Service
@Singleton
public class UserProfileManager implements ServicesToPreloadI
{
	private Logger logger = LoggerFactory.getLogger(UserProfileManager.class);

	private final File profilesFolder_ = new File("profiles");
	private final String prefsFileName_ = "Preferences.xml";

	//protected, rather than private, to allow the mock code to bypass this
	protected CountDownLatch cdl = new CountDownLatch(2);

	private ObservableList<String> userNamesWithProfiles_ = FXCollections.observableList(new ArrayList<>());
	private UserProfile loggedInUser_;

	protected UserProfileManager()
	{
		//For HK2 to construct, and for the MockUserProfileManager
	}

	/**
	 * Returns a clone of the UserProfile - changes made to the profile will be lost unless you call {@link #saveChanges()}
	 */
	public UserProfile getCurrentlyLoggedInUserProfile()
	{
		return loggedInUser_.clone();
	}
	
	/**
	 * Returns a clone of the user logon name
	 */
	public String getCurrentlyLoggedInUser()
	{
		return new String(loggedInUser_.getUserLogonName());
	}
	
	public void saveChanges(UserProfile userProfile) throws InvalidUserException, IOException
	{
		UserProfile temp = userProfile.clone();
		if (!temp.getUserLogonName().equals(loggedInUser_.getUserLogonName()))
		{
			throw new InvalidUserException("Not allowed to change the user login name!");
		}
		temp.store(new File(new File(profilesFolder_, temp.getUserLogonName()), prefsFileName_));
		loggedInUser_ = temp;
	}

	public boolean authenticateBoolean(String userLogonName, String password)
	{
		try
		{
			authenticate(userLogonName, password);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Throws an exception if authentication fails, otherwise, logs in the user.
	 * @throws InvalidUserException
	 */
	public void authenticate(String userLogonName, String password) throws InvalidUserException
	{
		checkInit();
		if (!userNamesWithProfiles_.contains(userLogonName))
		{
			throw new InvalidUserException("The specified user name is not allowed to login");
		}
		else
		{
			if (loggedInUser_ != null)
			{
				throw new InvalidUserException("We don't currently support logging out and logging in within a session at this point");
			}
			try
			{
				UserProfile up = UserProfile.read(new File(new File(profilesFolder_, userLogonName), prefsFileName_));
				if (!up.isCorrectPassword(password))
				{
					throw new InvalidPasswordException("Incorrect password");
				}
				else
				{
					loggedInUser_ = up;
					Files.write(new File(profilesFolder_, "lastUser.txt").toPath(), userLogonName.getBytes(), 
							StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
					ChangesetConfiguration.configureChangeSetWriter();
				}
			}
			catch (IOException e)
			{
				logger.warn("Couldn't read user profile!", e);
				throw new InvalidUserException("The profile for the user is unreadable");
			}
		}
	}

	/**
	 * The defaults parameter is optional - if not present, uses hardcoded defaults in {@link UserProfile} for fields that 
	 * don't come from the {@link User} object.
	 */
	public void createUserProfile(User user, NewUserDefaults defaults) throws IOException
	{
		logger.info("Creating user profile for " + user.getUniqueLogonName());

		//don't need to checkInit - doesProfileExist does that for us

		if (doesProfileExist(user.getUniqueLogonName()))
		{
			throw new IOException("User Profile already exists for " + user.getUniqueLogonName() + "!");
		}

		File profileFolder = new File(profilesFolder_, user.getUniqueLogonName());
		profileFolder.mkdir();
		new File(profileFolder, "changesets").mkdir();
		File prefFile = new File(profileFolder, prefsFileName_);

		UserProfile up = new UserProfile(user.getUniqueLogonName(), user.getPassword());
		if (defaults != null)
		{
			if (defaults.getStatedInferredPolicy() != null)
			{
				up.setStatedInferredPolicy(defaults.getStatedInferredPolicy());
			}
			if (defaults.isDisplayFSN() != null)
			{
				up.setDisplayFSN(defaults.isDisplayFSN().booleanValue());
			}
		}
		up.setConceptUUID(UUID.fromString(user.getUUID()));
		
		String temp = (user.getSyncUserName() != null && user.getSyncUserName().length() > 0 ? user.getSyncUserName() : user.getUniqueLogonName());
		
		up.setSyncUsername(temp);
		up.setSyncPassword(temp);  //Just a guess
		
		temp = (user.getWorkflowUserName() != null && user.getWorkflowUserName().length() > 0 ? user.getWorkflowUserName() : user.getUniqueLogonName()); 
		
		up.setWorkflowUsername(temp);
		up.setWorkflowPassword(temp);  //Just a guess
		
		up.store(prefFile);
		Platform.runLater(() ->
		{
			userNamesWithProfiles_.add(user.getUniqueLogonName());
			FXCollections.sort(userNamesWithProfiles_);
		});
		
	}

	public boolean doesProfileExist(String userLogonName)
	{
		checkInit();
		return userNamesWithProfiles_.contains(userLogonName);
	}

	private void checkInit()
	{
		try
		{
			if (cdl.getCount() == 2)
			{
				cdl.await(5, TimeUnit.SECONDS);
				if (cdl.getCount() == 2)
				{
					throw new RuntimeException("API misuse - someone forgot to call loadRequested....");
				}
			}
			cdl.await();
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException("Interrupted during await for init... not expected");
		}
	}

	/**
	 * @see gov.va.isaac.interfaces.utility.ServicesToPreloadI#loadRequested()
	 */
	@Override
	public void loadRequested()
	{
		cdl.countDown();  //2 to 1 tells us that loadRequested was called.

		Utility.execute(() -> {
			logger.debug("Configuring UserProfileManager, using the path " + profilesFolder_.getAbsolutePath());
			profilesFolder_.mkdirs();

			if (!profilesFolder_.exists() || !profilesFolder_.isDirectory())
			{
				logger.error("The user profile folder could not be created!");
			}

			for (File f : profilesFolder_.listFiles())
			{
				if (f.isDirectory())
				{
					File prefFile = new File(f, prefsFileName_);
					if (prefFile.exists() && prefFile.isFile())
					{
						userNamesWithProfiles_.add(f.getName());
					}
				}
			}
			FXCollections.sort(userNamesWithProfiles_);

			cdl.countDown();  //0 tells us init is complete
		});
	}
	
	/**
	 * Returns a sorted list of all user names that could potentially log on, based on 
	 * the existence of their profiles directory.
	 */
	public ObservableList<String> getUsersWithProfiles()
	{
		checkInit();
		return FXCollections.unmodifiableObservableList(userNamesWithProfiles_);
	}
	
	/**
	 * Returns null, if the last logged in user isn't known.
	 * @return
	 */
	public String getLastLoggedInUser()
	{
		checkInit();
		try
		{
			File temp = new File(profilesFolder_, "lastUser.txt");
			if (temp.exists())
			{
				String u = new String(Files.readAllBytes(temp.toPath()));
				if (userNamesWithProfiles_.contains(u))
				{
					return u;
				}
			}
		}
		catch (IOException e)
		{
			// noop
		}
		return null;
	}
	
	/**
	 * A convenience method for GUI use which will create an entirely new user, which wasn't declared as part of the 
	 * initial users set.  Only callable by users with admin permissions.
	 * 
	 * This method will fail with an exception if either the {@link User#getUniqueFullName()} or the {@link User#getUniqueLogonName()} are 
	 * in fact, not unique.
	 */
	public User createNewUser(User user) throws InvalidUserException, IOException
	{
		if (!getCurrentlyLoggedInUserProfile().hasRole(RoleOption.ADMIN))
		{
			throw new IOException("You do not have the necessary permissions to create new users");
		}
		//fill in any missing fields in the user object, check if the concept exists in the DB
		if (!GenerateUsers.alreadyExists(user))
		{
			if (doesProfileExist(user.getUniqueLogonName()))
			{
				throw new IOException("A user with that logon name already exists");
			}
			try
			{
				GenerateUsers.createUserConcept(user);
			}
			catch (Exception e)
			{
				logger.error("Unexpeted error creating user concept", e);
				throw new IOException("Unexpected error creating user concept", e);
			}
			createUserProfile(user, null);
		}
		else
		{
			throw new IOException("That user concept already exists");
		}
		return user;
	}
}