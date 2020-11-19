package rihab.beji.test.rihab.beji.test;

import java.util.ArrayList;
import java.util.List;

import rihab.beji.test.rihab.beji.test.controller.UsersController;
import rihab.beji.test.rihab.beji.test.model.UserModel;
import rihab.beji.test.rihab.beji.test.util.Constants;
import view.CreateView;

/**
 * Hello world!
 *
 */
public class App {

	static List<UserModel> allUsersDetails;
	static UsersController usersController;

	public static void main(String[] args) {

		usersController = new UsersController();
		allUsersDetails = new ArrayList();

		usersController.returnFormattedUsersDetailFromFile(Constants.PATH_TO_TWITTER_FILE,
				Constants.DELIMETER_FOLLOWERS, allUsersDetails);

		usersController.returnFormattedUsersDetailFromFile(Constants.PATH_TO_USER_FILE, Constants.DELIMETER_USERS,
				allUsersDetails);

		new CreateView().createViewFor(allUsersDetails.toString());

	}

}
