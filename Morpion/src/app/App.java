package app;


import controler.ConnexionController;
import controler.FirstLoginController;
import controler.MeilleursScoresController;
import controler.MorpionSolitaireController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MorpionSolitaireModel;
import view.MorpionSolitaireView;

import java.io.IOException;

public class App extends Application {

	private Stage primaryStage;

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		loadLoginPage();
	}

	public void loadLoginPage() {
		try {
			FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/Connexion.fxml"));
			Parent loginRoot = loginLoader.load();
			Scene loginScene = new Scene(loginRoot);

			primaryStage.setTitle("Connexion");
			primaryStage.setScene(loginScene);
			primaryStage.show();

			ConnexionController connexionController = loginLoader.getController();
			connexionController.setMainApp(this);
			connexionController.setOnLoginSuccess(this::loadMorpionSolitairePage);
			connexionController.setOnFirstLoginRequest(this::loadFirstLoginPage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMorpionSolitairePage() {
		System.out.println("Chargement de la page MorpionSolitaire");
		try {
			FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/MorpionSolitaire.fxml"));
			Parent mainRoot = mainLoader.load();
			Scene mainScene = new Scene(mainRoot);
			primaryStage.setTitle("Morpion Solitaire");
			primaryStage.setScene(mainScene);
			primaryStage.setOnCloseRequest(e -> System.exit(0));
			primaryStage.show();
			MorpionSolitaireController controller = mainLoader.getController();
			MorpionSolitaireModel gameModel = new MorpionSolitaireModel();
			MorpionSolitaireView gameView = new MorpionSolitaireView(controller.getCanvas());
			controller.setModelAndView(gameModel, gameView);
			controller.setApp(this);
			controller.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMeilleursScoresPage() {
	    System.out.println("Chargement de meilleurs scores");
	    try {
	        FXMLLoader meilleursScoresLoader = new FXMLLoader(getClass().getResource("/MeilleursScores.fxml"));
	        Parent meilleursScoresRoot = meilleursScoresLoader.load();
	        Scene meilleursScoresScene = new Scene(meilleursScoresRoot);

	        primaryStage.setTitle("Meilleurs Scores");
	        primaryStage.setScene(meilleursScoresScene);
	        primaryStage.show();
	        MeilleursScoresController meilleursScoresController = meilleursScoresLoader.getController();
	        MorpionSolitaireModel morpionSolitaireModel = new MorpionSolitaireModel(); 
	        meilleursScoresController.setModelAndInitialize(morpionSolitaireModel);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}



	public void loadFirstLoginPage() {
		try {
			FXMLLoader firstLoginLoader = new FXMLLoader(getClass().getResource("/FirstLogin.fxml"));
			Parent firstLoginRoot = firstLoginLoader.load();
			Scene firstLoginScene = new Scene(firstLoginRoot);
			primaryStage.setTitle("First Login");
			primaryStage.setScene(firstLoginScene);
			primaryStage.show();
			FirstLoginController firstLoginController = firstLoginLoader.getController();
			firstLoginController.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
