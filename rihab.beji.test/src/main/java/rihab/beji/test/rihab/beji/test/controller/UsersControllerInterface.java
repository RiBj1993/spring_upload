package rihab.beji.test.rihab.beji.test.controller;

import java.util.List;

import rihab.beji.test.rihab.beji.test.model.UserModel;

public interface UsersControllerInterface {

	public List<UserModel> returnFormattedUsersDetailFromFile(String pathToFile, String delimeter,
			List<UserModel> allUsersDetails);

	public List<UserModel> checkIfUserExistOrAddNewOne(UserModel user2, List<UserModel> allusers);

	public UserModel checkIfUserExistOrReturnNewOne(String name, List<UserModel> allusers);

	public UserModel getUserByName(String nameuseers, List<UserModel> allUsers);

}
