package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.UserProfile;
import objectInterface.FileOperationInterface;
import utilities.Constants;

public class FileManagementController implements FileOperationInterface {

	/**
	 * The path of UserProfile file.
	 */

	
	@Override
	public UserProfile exportSetting(String userName) {
		return null;
	}

	/**
	 * Import the setting information from user profile.
	 */
	@Override
	public void importSetting(UserProfile userProfile) {
		BufferedWriter bw = null;
		try {
			List<UserProfile> lstOfUser = getAllUser();
			bw = new BufferedWriter(new FileWriter(Constants.USER_INFO_PATH));
			for (UserProfile user : lstOfUser) {
				if (user.getUserName().equalsIgnoreCase(userProfile.getUserName())) {
					bw.write(userProfile.toString());
				} else {
					bw.write(user.toString());
				}
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	/**
	 * Get all the user profiles and push into the list.
	 * 
	 * @return List<UserProfile>
	 * @throws IOException
	 */
	private List<UserProfile> getAllUser() throws IOException {
		List<UserProfile> lstOfUser = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.USER_INFO_PATH));
			String row = br.readLine();
			while (row != null) {
				String data[] = row.split(",");
				UserProfile user = new UserProfile();
				user.setUserName(data[0]);
				user.setPassword(data[1]);
				user.setName(data[2]);
				user.setEmail(data[3]);
				lstOfUser.add(user);
				row = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstOfUser;
	}
	
	/**
	 * Return a UserProfile based on its username.
	 */
	@Override
	public UserProfile getUserBasedOnUserName(String userName) {
		List<UserProfile> lstOfUser;
		try {
			lstOfUser = getAllUser();
			for (UserProfile user : lstOfUser) {
				if (user.getUserName().equalsIgnoreCase(userName)) {
					return user;
				}
			}
		} catch (IOException e) {}
		return null;
	}

	@Override
	public File[] getListOfFileByFolder(String path) {
		return new File(path).listFiles();
	}
	
	

}
