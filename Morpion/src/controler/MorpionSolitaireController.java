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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Mode;
import model.MorpionSolitaireModel;
import model.SauvegardeGrille;
import view.MorpionSolitaireView;

/**
 * Contrôleur pour la gestion du jeu Morpion Solitaire.
 */
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

	/**
	 * Initialise le contrôleur.
	 */
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
		view = new MorpionSolitaireView(canvaJeu, app);
		view.setRoot(canvaJeu.getParent());
		view.setApp(app);
	}

	/**
	 * Définit l'application principale.
	 *
	 * @param app Application principale.
	 */
	public void setApp(App app) {
		this.app = app;
	}

	/**
	 * Gère l'action du clic sur le canvas.
	 *
	 * @param me MouseEvent généré lors du clic.
	 */
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
		checkGameOver();
	}

	/**
	 * Initialise le jeu.
	 */
	public void start() {
		System.out.println("Contrôleur MorpionSolitaire initialisé.");
		view = new MorpionSolitaireView(canvaJeu, app);
		canvaJeu.setFocusTraversable(true);
		model.addGameObserver(view);
		view.setTheme(MorpionSolitaireView.M_THEME);
		canvaJeu.getScene().getRoot().setStyle("-fx-base:  #cce6ff");
		setupOptions();
		isInitialization = false;
		model.init();
	}

	/**
	 * Configure les options du jeu.
	 */
	private void setupOptions() {
		view.setTheme(MorpionSolitaireView.M_THEME);
		mode.getItems().removeAll(mode.getItems());
		mode.getItems().addAll("5T", "5D");
		mode.getSelectionModel().select("5D");
	}

	/**
	 * Gère l'action du bouton de réinitialisation.
	 */
	@FXML
	private void reset() {
		String selectedMode = this.mode.getSelectionModel().getSelectedItem();
		if (selectedMode != null) {
			Mode mode = selectedMode.equalsIgnoreCase("5T") ? Mode.FT : Mode.FD;
			model.setGameMode(mode);
		}
	}

	/**
	 * Gère l'action du bouton d'annulation de coup.
	 */
	@FXML
	private void undoMove() {
		model.undoMove();
	}

	/**
	 * Gère le changement de mode de jeu.
	 */
	@FXML
	private void gameModeChanged() {
		Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmationAlert.setTitle("Confirmation");
		confirmationAlert.setHeaderText("Changement de mode de jeu");
		confirmationAlert.setContentText("Êtes-vous sûr de vouloir changer le mode de jeu?");
		ButtonType confirmButton = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
		ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
		confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton);
		Optional<ButtonType> result = confirmationAlert.showAndWait();
		if (result.isPresent() && result.get() == confirmButton) {
			model.init();
			reset();
		} else {

		}
	}

	/**
	 * Gère l'action de déconnexion.
	 */
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

	/**
	 * Gère l'action de sauvegarde de la partie.
	 */
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

	/**
	 * Gère l'action de chargement de la partie.
	 */
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

	/**
	 * Gère l'action d'affichage des meilleurs scores.
	 *
	 * @throws IOException En cas d'erreur d'entrée/sortie.
	 */
	@FXML
	private void mesMeilleursScores() throws IOException {
		app.loadMeilleursScoresPage();
	}

	/**
	 * Gère l'action de consultation des informations du joueur.
	 */
	@FXML 
	private void consulterInfos() {
		String username = model.getPlayerName();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Informations du joueur");
		alert.setContentText(username);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	/**
	 * Gère l'action de modification des informations du joueur.
	 */
	@FXML 
	private void changerInfos() {

	}

	/**
	 * Définit le modèle du jeu.
	 *
	 * @param model Modèle de Morpion Solitaire.
	 */
	public void setModel(MorpionSolitaireModel model) {
		this.model = model;
	}

	/**
	 * Définit le modèle et la vue du jeu.
	 *
	 * @param model Modèle de Morpion Solitaire.
	 * @param view  Vue de Morpion Solitaire.
	 */
	public void setModelAndView(MorpionSolitaireModel model, MorpionSolitaireView view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * Obtient le canvas du jeu.
	 *
	 * @return Canvas du jeu.
	 */
	private void checkGameOver() {
		if (model.isGameOver()) {
			int finalScore = model.getScore();

			Alert gameOverAlert = new Alert(Alert.AlertType.CONFIRMATION);
			gameOverAlert.setTitle("Fin de partie");
			gameOverAlert.setHeaderText("Vous avez fini avec un score de " + finalScore);
			gameOverAlert.setContentText("Que souhaitez-vous faire?");
			ButtonType deconnecterButton = new ButtonType("Se déconnecter", ButtonBar.ButtonData.OK_DONE);
			ButtonType recommencerButton = new ButtonType("Recommencer", ButtonBar.ButtonData.APPLY);
			gameOverAlert.getButtonTypes().setAll(deconnecterButton, recommencerButton);
			gameOverAlert.initModality(Modality.APPLICATION_MODAL);
			gameOverAlert.showAndWait().ifPresent(response -> {
				if (response == deconnecterButton) {
					deconnexion();
				} else if (response == recommencerButton) {
					model.init();

				}
			});
		}
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
		this.model.handleRandomMove();
	}

	@FXML
	private void handleRandomPartieButton() {
		this.model.handleRandomGame();
	}
}