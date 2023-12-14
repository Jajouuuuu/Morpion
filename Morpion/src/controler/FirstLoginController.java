package controler;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserManager;

/**
 * Contrôleur pour la création du premier utilisateur lors de la première connexion.
 */
public class FirstLoginController {

	@FXML
	private TextField newUsernameField;
	@FXML
	private PasswordField newPasswordField;
	private UserManager userManager = new UserManager();
	private App mainApp;

	 /**
     * Définit l'application principale.
     *
     * @param mainApp Application principale.
     */
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}

	/**
     * Gère l'action du bouton de création d'utilisateur.
     */
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

	/**
     * Affiche une boîte de dialogue d'erreur avec le message spécifié.
     *
     * @param message Message d'erreur à afficher.
     */
	private void showError(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erreur");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
     * Affiche une boîte de dialogue d'information avec le message spécifié.
     *
     * @param message Message d'information à afficher.
     */
	private void showInfo(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}