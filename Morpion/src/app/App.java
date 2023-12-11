 package app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MorpionSolitaireModel;
import view.MorpionSolitaireView;

import java.io.IOException;
import java.net.URL;

import controler.MorpionSolitaireController;

public class App extends Application {
		@SuppressWarnings("exports")
		@FXML
	    @Override
	    public void start(Stage stage) throws IOException {
			URL url = getClass().getResource("/MorpionSolitaire.fxml");
			if (url != null) {
				FXMLLoader fxmlLoader = new FXMLLoader(url);
		        Scene scene = new Scene(fxmlLoader.load(), MorpionSolitaireView.WIDTH, MorpionSolitaireView.HEIGHT);
		        MorpionSolitaireController controller = fxmlLoader.getController();

		        MorpionSolitaireModel gameModel = new MorpionSolitaireModel();
		        controller.setModel(gameModel);
		        controller.start();
		        stage.setTitle("Morpion Solitaire");
		        stage.setScene(scene);
		        stage.setOnCloseRequest(e -> System.exit(0));
		        stage.show();
			} else {
			   System.err.println("Le fichier FXML n'a pas été trouvé.");
			}        
	    }

	    public static void main(String[] args) {
	        launch();
	    }
	}