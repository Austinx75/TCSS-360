package service;

import controller.FileManagementController;
import model.UserProfile;
import objectInterface.ImportExportServiceInterface;

/**
 * Proving the methods for GUI layer to communicate with File Controller about import and export.
 * 
 * @author Anh Tran
 */
public class ImportExportService implements ImportExportServiceInterface {

	/**
	 * This object present the composition relationship between service layer and controller.
	 */
	private FileManagementController fileController;

	public ImportExportService() {
		fileController = new FileManagementController();
	}

	/**
	 * Forwarding import data request from user.
	 */
	@Override
	public void importData(UserProfile userProfile) {
		fileController.importSetting(userProfile);
	}

}
