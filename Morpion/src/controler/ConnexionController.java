package controler;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserManager;

public class ConnexionController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserManager userManager = new UserManager();
    private App mainApp;

    @FXML
    private Button loginButton;

    private Runnable onLoginSuccess;
    private Runnable onFirstLoginRequest;

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    public void setOnFirstLoginRequest(Runnable onFirstLoginRequest) {
        this.onFirstLoginRequest = onFirstLoginRequest;
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String enteredUsername = usernameField.getText();
        String enteredPassword = passwordField.getText();

        System.out.println("Tentative de connexion pour l'utilisateur : " + enteredUsername);

        if (authenticateUser(enteredUsername, enteredPassword)) {
            handleSuccessfulLogin();
        } else {
            handleFailedLogin();
        }
    }

    @FXML
    private void handleFirstLoginButtonAction(ActionEvent event) {
        if (onFirstLoginRequest != null) {
            onFirstLoginRequest.run();
        }
    }

    private boolean authenticateUser(String username, String password) {
        return userManager.authenticate(username, password);
    }

    private void handleSuccessfulLogin() {
        if (onLoginSuccess != null) {
            onLoginSuccess.run();
        }
    }

    private void handleFailedLogin() {
        System.out.println("Échec de la connexion. Veuillez vérifier votre nom d'utilisateur et votre mot de passe.");
    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticateUser(username, password)) {
            handleSuccessfulLogin();
        } else {
            showError("Identifiants incorrects");
        }
    }

    @FXML
    private void firstLoginButtonAction() {
        mainApp.loadFirstLoginPage();
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
