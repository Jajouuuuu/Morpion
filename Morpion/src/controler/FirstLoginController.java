package controler;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserManager;

public class FirstLoginController {

	@FXML
	private TextField newUsernameField;

	@FXML
	private PasswordField newPasswordField;

	private UserManager userManager = new UserManager();
	private App mainApp;

	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void createUserButtonAction() {
		String newUsername = newUsernameField.getText();
		String newPassword = newPasswordField.getText();

		if (newUsername.isEmpty() || newPassword.isEmpty()) {
			showError("Veuillez remplir tous les champs.");
		} else {
			if (userManager.authenticate(newUsername, newPassword)) {
				showError("Cet utilisateur existe déjà.");
			} else {
				userManager.addUser(newUsername, newPassword);
				showInfo("Utilisateur créé avec succès.");
				mainApp.loadLoginPage();
			}
		}
	}

	private void showError(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erreur");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void showInfo(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
