package controler;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserManager;

/**
 * Contrôleur pour la gestion de la connexion utilisateur.
 */
public class ConnexionController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private UserManager userManager = new UserManager();
    private App mainApp;
    private String currentUsername;
    @FXML
    private Button loginButton;
    private Runnable onLoginSuccess;
    private Runnable onFirstLoginRequest;

    /**
     * Définit l'action à effectuer en cas de succès de la connexion.
     *
     * @param onLoginSuccess Runnable à exécuter en cas de succès de la connexion.
     */
    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    /**
     * Définit l'action à effectuer en cas de demande de premier login.
     *
     * @param onFirstLoginRequest Runnable à exécuter en cas de demande de premier login.
     */
    public void setOnFirstLoginRequest(Runnable onFirstLoginRequest) {
        this.onFirstLoginRequest = onFirstLoginRequest;
    }
   
    /**
     * Obtient le nom d'utilisateur actuel.
     *
     * @return Nom d'utilisateur actuel.
     */
    public String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * Gère l'action du bouton de connexion.
     *
     * @param event ActionEvent généré lors du clic sur le bouton de connexion.
     */
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

    /**
     * Gère l'action du bouton de premier login.
     *
     * @param event ActionEvent généré lors du clic sur le bouton de premier login.
     */
    @FXML
    private void handleFirstLoginButtonAction(ActionEvent event) {
        if (onFirstLoginRequest != null) {
            onFirstLoginRequest.run();
        }
    }

    /**
     * Authentifie l'utilisateur en vérifiant le nom d'utilisateur et le mot de passe.
     *
     * @param username Nom d'utilisateur.
     * @param password Mot de passe.
     * @return True si l'authentification est réussie, sinon False.
     */
    private boolean authenticateUser(String username, String password) {
        return userManager.authenticate(username, password);
    }

    /**
     * Gère les actions à effectuer en cas de succès de la connexion.
     */
    private void handleSuccessfulLogin() {
        currentUsername = usernameField.getText();
        if (onLoginSuccess != null) {
        	System.out.println(currentUsername);
            onLoginSuccess.run();
        }
    }

    /**
     * Gère les actions à effectuer en cas d'échec de la connexion.
     */
    private void handleFailedLogin() {
        System.out.println("Échec de la connexion. Veuillez vérifier votre nom d'utilisateur et votre mot de passe.");
    }

    /**
     * Gère l'action du bouton de connexion.
     *
     * @param event ActionEvent généré lors du clic sur le bouton de connexion.
     */
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

    /**
     * Gère l'action du bouton de premier login.
     */
    @FXML
    private void firstLoginButtonAction() {
        mainApp.loadFirstLoginPage();
    }

    /**
     * Définit l'application principale.
     *
     * @param mainApp Application principale.
     */
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
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
}