package controler;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import app.App;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Mode;
import model.MorpionSolitaireModel;
import model.SauvegardeGrille;
import view.MorpionSolitaireView;

public class MorpionSolitaireController {

	@FXML
	private Canvas canvaJeu;
	private MorpionSolitaireView view;
	private MorpionSolitaireModel model;
	@FXML

	private App app;
	@FXML
	private TextField user;
	@FXML
	private ComboBox<String> mode;

	private boolean isInitialization = true;

	@FXML
	private void initialize() {
		mode.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!isInitialization) {
					gameModeChanged();
				}
			}
		});
	}

	public void setApp(App app) {
		this.app = app;
	}

	@FXML
	private void canvasMousePressed(MouseEvent me) {
		System.out.printf("(%f,%f)", me.getX(), me.getY());
		double tolerance = 0.2;
		double tempX = (me.getX() - view.getOffX()) / view.getCellWidth();
		double tempY = (me.getY() - view.getOffY()) / view.getCellHeight();
		double frX = tempX - (int) tempX;
		double frY = tempY - (int) tempY;
		if ((frX > tolerance && frX < 1 - tolerance) || (frY > tolerance && frY < 1 - tolerance)) {
			System.out.println("Mouvement invalide !");
			return;
		}
		int gridX = (int) Math.round(tempX);
		int gridY = (int) Math.round(tempY);

		model.handleHumanMove(gridX, gridY);

	}

	public void start() {
		System.out.println("Contrôleur MorpionSolitaire initialisé.");
		view = new MorpionSolitaireView(canvaJeu);
		canvaJeu.setFocusTraversable(true);
		model.addGameObserver(view);
		view.setTheme(MorpionSolitaireView.M_THEME);
		canvaJeu.getScene().getRoot().setStyle("-fx-base:  #cce6ff");
		setupOptions();
		isInitialization = false;
		model.init();
	}

	private void setupOptions() {
		view.setTheme(MorpionSolitaireView.M_THEME);
		mode.getItems().removeAll(mode.getItems());
		mode.getItems().addAll("5T", "5D");
		mode.getSelectionModel().select("5D");
	}

	@FXML
	private void reset() {
		String selectedMode = this.mode.getSelectionModel().getSelectedItem();
		if (selectedMode != null) {
			Mode mode = selectedMode.equalsIgnoreCase("5T") ? Mode.FT : Mode.FD;
			model.setGameMode(mode);
		}
	}

	@FXML
	private void undoMove() {
		model.undoMove();
	}

	@FXML
	private void gameModeChanged() {
		model.init();
		Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmationAlert.setTitle("Confirmation");
		confirmationAlert.setHeaderText("Changement de mode de jeu");
		confirmationAlert.setContentText("Êtes-vous sûr de vouloir changer le mode de jeu?");
		ButtonType confirmButton = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
		ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
		confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton);
		Optional<ButtonType> result = confirmationAlert.showAndWait();
		if (result.isPresent() && result.get() == confirmButton) {
			reset();
		} else {

		}
	}

	@FXML
	private void deconnexion() {
		Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmationAlert.setTitle("Confirmation");
		confirmationAlert.setHeaderText("Déconnexion");
		confirmationAlert.setContentText("Êtes-vous sûr de vouloir vous déconnecter?");
		ButtonType confirmButton = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
		ButtonType cancelButton = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
		confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton);
		Optional<ButtonType> result = confirmationAlert.showAndWait();
		if (result.isPresent() && result.get() == confirmButton) {
			if (this.app != null) {
				this.app.loadLoginPage();
			} else {
				System.err.println("Erreur : 'app' est null dans MorpionSolitaireController.deconnexion()");
			}
		}
	}


	@FXML
	private void sauvegarderPartie() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Sauvegarder la partie");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers de sauvegarde", "*.sav"));
		Stage stage = (Stage) canvaJeu.getScene().getWindow();
		File file = fileChooser.showSaveDialog(stage);

		if (file != null) {
			try {
				SauvegardeGrille sauvegardeGrille = new SauvegardeGrille(model.getGrid(), model.getPlayedPoints(), model.getPlayedLines());
				System.out.println(model.getPlayedPoints());
				System.out.println(model.getPlayedLines());
				SauvegardeGrille.save(sauvegardeGrille, file.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void chargerPartie() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Charger une partie");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers de sauvegarde", "*.sav"));
		Stage stage = (Stage) canvaJeu.getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);

		if (file != null) {
			try {
				SauvegardeGrille savedData = SauvegardeGrille.load(file.getAbsolutePath());
				model.setGrid(savedData.getGrid());
				model.setPlayedPoints(savedData.getPlayedPoints());
				model.setPlayedLines(savedData.getPlayedLines());
				view.update(model.getGrid(), model.getPlayedPoints(), model.getPlayedLines());
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Erreur lors du chargement du modèle : " + e.getMessage());
			}
		}
	}

	@FXML
	private void mesMeilleursScores() throws IOException {
	   app.loadMeilleursScoresPage();
	}

	@FXML private void consulterInfos() {
		String username = model.getPlayerName();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Informations du joueur");
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	@FXML private void changerInfos() {

	}

	public void setModel(MorpionSolitaireModel model) {
		this.model = model;
	}

	public void setModelAndView(MorpionSolitaireModel model, MorpionSolitaireView view) {
		this.model = model;
		this.view = view;
	}

	@SuppressWarnings("exports")
	public Canvas getCanvas() {
		return canvaJeu;
	}

	@FXML
	private void handleNmcsCoupButton() {
		this.model.handleNmcsMove();
	}

	@FXML
	private void handleNmcsPartieButton() {
		this.model.handleNmcsGame();
	}

	@FXML
	private void handleRandomCoupButton() {
		this.model.handleRandomGame();
	}

	@FXML
	private void handleRandomPartieButton() {
		this.model.handleRandomGame();
	}
}