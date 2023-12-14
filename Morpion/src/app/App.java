package app;


import controler.ConnexionController;
import controler.FirstLoginController;
import controler.MeilleursScoresController;
import controler.MorpionSolitaireController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MorpionSolitaireModel;
import view.MorpionSolitaireView;

import java.io.IOException;
import java.util.Stack;

/**
 * Classe principale de l'application qui étend la classe Application de JavaFX.
 */
public class App extends Application {

	private Stage primaryStage;
	private static App instance;
	private MorpionSolitaireModel model;
	private Stack<Parent> pageHistory = new Stack<>();

	/**
     * Méthode principale appelée au lancement de l'application.
     *
     * @param primaryStage Stage principal de l'application.
     * @throws Exception Exception levée en cas d'erreur lors du lancement.
     */
    @SuppressWarnings("exports")
	@Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        this.setPrimaryStage(primaryStage);
        model = new MorpionSolitaireModel(); 
        loadLoginPage();
    }

    /**
     * Charge la page de connexion.
     */
	public void loadLoginPage() {
		try {
			FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/Connexion.fxml"));
			Parent loginRoot = loginLoader.load();
			Scene loginScene = new Scene(loginRoot);

			getPrimaryStage().setTitle("Connexion");
			getPrimaryStage().setScene(loginScene);
			getPrimaryStage().show();

			ConnexionController connexionController = loginLoader.getController();
			connexionController.setMainApp(this);
			model.setConnexionController(connexionController);

			connexionController.setOnLoginSuccess(() -> {
				String currentUsername = connexionController.getCurrentUsername();
				System.out.println("currentUsername: " + currentUsername);
				if (currentUsername != null && !currentUsername.isEmpty()) {
					loadMorpionSolitairePage(currentUsername, connexionController);
				} else {
					// Traitement si le nom d'utilisateur est null ou vide
				}
			});

			connexionController.setOnFirstLoginRequest(this::loadFirstLoginPage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * Charge la page du jeu Morpion Solitaire.
     *
     * @param username             Nom d'utilisateur du joueur.
     * @param connexionController Contrôleur de connexion.
     */
	public void loadMorpionSolitairePage(String username, ConnexionController connexionController) {
		System.out.println("Chargement de la page MorpionSolitaire");
		System.out.println(username);
		try {
			FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/MorpionSolitaire.fxml"));
			Parent mainRoot = mainLoader.load();
			Scene mainScene = new Scene(mainRoot);
			getPrimaryStage().setTitle("Morpion Solitaire");
			getPrimaryStage().setScene(mainScene);
			getPrimaryStage().setOnCloseRequest(e -> System.exit(0));
			getPrimaryStage().show();

			MorpionSolitaireController controller = mainLoader.getController();
			MorpionSolitaireModel gameModel = new MorpionSolitaireModel();
			gameModel.setPlayerName(username);
			gameModel.setConnexionController(connexionController); // Ajoutez cette ligne
			MorpionSolitaireView gameView = new MorpionSolitaireView(controller.getCanvas(), this);
			controller.setModelAndView(gameModel, gameView);
			controller.setApp(this);
			controller.start();
			getPageHistory().push(getPrimaryStage().getScene().getRoot());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * Charge la page des meilleurs scores.
     */
	public void loadMeilleursScoresPage() {
		System.out.println("Chargement de meilleurs scores");
		try {
			FXMLLoader meilleursScoresLoader = new FXMLLoader(getClass().getResource("/MeilleursScores.fxml"));
			Parent meilleursScoresRoot = meilleursScoresLoader.load();
			Scene meilleursScoresScene = new Scene(meilleursScoresRoot);

			getPrimaryStage().setTitle("Meilleurs Scores");
			getPrimaryStage().setScene(meilleursScoresScene);
			getPrimaryStage().show();
			MeilleursScoresController meilleursScoresController = meilleursScoresLoader.getController();
			MorpionSolitaireModel morpionSolitaireModel = new MorpionSolitaireModel(); 
			meilleursScoresController.setModelAndInitialize(morpionSolitaireModel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Charge la page précédente dans l'historique.
     */
	public void loadPreviousPage() {
	    if (!pageHistory.isEmpty()) {
	        Parent previousPage = pageHistory.pop();
	        BorderPane borderPane = new BorderPane();
	        borderPane.setCenter(previousPage);

	        Scene scene = new Scene(borderPane);

	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } else {
	        System.out.println("No previous page in history.");
	    }
	}

	/**
     * Charge la page du premier login.
     */
	public void loadFirstLoginPage() {
		try {
			FXMLLoader firstLoginLoader = new FXMLLoader(getClass().getResource("/FirstLogin.fxml"));
			Parent firstLoginRoot = firstLoginLoader.load();
			Scene firstLoginScene = new Scene(firstLoginRoot);
			getPrimaryStage().setTitle("First Login");
			getPrimaryStage().setScene(firstLoginScene);
			getPrimaryStage().show();
			FirstLoginController firstLoginController = firstLoginLoader.getController();
			firstLoginController.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * Méthode principale pour le lancement de l'application.
     *
     * @param args Arguments de la ligne de commande.
     */
	public static void main(String[] args) {
		launch(args);
	}

	/**
     * Obtient l'instance unique de la classe App.
     *
     * @return Instance unique de la classe App.
     */
	public static App getInstance() {
        return instance;
    }

	/**
     * Obtient l'historique des pages.
     *
     * @return Historique des pages.
     */
	@SuppressWarnings("exports")
	public Stack<Parent> getPageHistory() {
		return pageHistory;
	}

	/**
     * Définit l'historique des pages.
     *
     * @param pageHistory Historique des pages à définir.
     */
	@SuppressWarnings("exports")
	public void setPageHistory(Stack<Parent> pageHistory) {
		this.pageHistory = pageHistory;
	}

	/**
     * Obtient le stage principal de l'application.
     *
     * @return Stage principal de l'application.
     */
	@SuppressWarnings("exports")
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
     * Définit le stage principal de l'application.
     *
     * @param primaryStage Stage principal de l'application à définir.
     */
	@SuppressWarnings("exports")
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}