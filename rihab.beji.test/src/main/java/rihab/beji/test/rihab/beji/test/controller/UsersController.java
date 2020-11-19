package rihab.beji.test.rihab.beji.test.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import rihab.beji.test.rihab.beji.test.model.TweetsModel;
import rihab.beji.test.rihab.beji.test.model.UserModel;

public class UsersController implements UsersControllerInterface {

	@Override
	public List<UserModel> returnFormattedUsersDetailFromFile(String pathToFile, String delimeter,
			List<UserModel> allUsersDetails) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)))) {
			String s;
			while ((s = br.readLine()) != null) {

				String substr = delimeter;
				String before = s.substring(0, s.indexOf(substr));
				String after = s.substring(s.indexOf(substr) + substr.length());

				UserModel user = checkIfUserExistOrReturnNewOne(before, allUsersDetails);
				////////////////////////////////////////////////////////////////////////////////
				if (delimeter.equalsIgnoreCase("follows")) {
					String[] out = after.split(",");
					List<UserModel> followrs = user.getFollowers_of_user();
					for (String nameuseers : out) {
						UserModel usertest = getUserByName(nameuseers, allUsersDetails);
						if (followrs.contains(usertest) != true && allUsersDetails.contains(usertest)) {
							followrs.add(getUserByName(nameuseers, allUsersDetails));

						}

						if (followrs.contains(usertest) != true && allUsersDetails.contains(usertest) != true) {

							UserModel user_followrs = checkIfUserExistOrReturnNewOne(nameuseers.trim(),
									allUsersDetails);
							allUsersDetails = checkIfUserExistOrAddNewOne(user_followrs, allUsersDetails);

							followrs.add(user_followrs);

						}
					}
					user.setFollowers_of_user(followrs);
				} else if (delimeter.equalsIgnoreCase(">")) {
					user.getTweets_of_user().add(new TweetsModel(after));

				}
				////////////////////////////////////////////////////////////////////////////////

				allUsersDetails = checkIfUserExistOrAddNewOne(user, allUsersDetails);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return allUsersDetails;

	}

	@Override
	public List<UserModel> checkIfUserExistOrAddNewOne(UserModel user2, List<UserModel> allusers) {

		for (UserModel user : allusers) {

			if (user2.getName_of_user().trim().equalsIgnoreCase(user.getName_of_user().trim())) {
				allusers.remove(allusers.indexOf(user));
				allusers.add(user2);

				return allusers;
			}
		}

		allusers.add(user2);
		return allusers;
	}

	@Override
	public UserModel checkIfUserExistOrReturnNewOne(String name, List<UserModel> allusers) {

		for (UserModel user : allusers) {

			if (name.trim().equalsIgnoreCase(user.getName_of_user().trim())) {

				return user;
			}
		}
		UserModel new_user = new UserModel();
		new_user.setName_of_user(name);

		List<TweetsModel> tweets = new ArrayList();
		new_user.setTweets_of_user(tweets);

		List<UserModel> followers = new ArrayList();
		new_user.setFollowers_of_user(followers);
		return new_user;
	}

	@Override
	public UserModel getUserByName(String nameuseers, List<UserModel> allUsers) {
		for (UserModel user : allUsers) {

			if (nameuseers.trim().equalsIgnoreCase(user.getName_of_user().trim())) {
				return user;
			}
		}
		return null;
	}
}
