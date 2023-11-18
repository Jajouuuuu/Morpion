package controler;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Mode;
import model.MorpionSolitaireModel;
import view.MorpionSolitaireView;

public class MorpionSolitaireController {
	
	@FXML
	private Canvas canvaJeu;
	private MorpionSolitaireView view;
	private MorpionSolitaireModel model;
	@FXML
	private TextField user;
	@FXML
	private ComboBox<String> mode;
	@FXML
    private ComboBox<String> theme;

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
		if (gridX == -1 && gridY == -1) {
			model.handleNmcsMove();
		}
		else { 
			model.handleHumanMove(gridX, gridY);
		}
	}

	public void start() {
		view = new MorpionSolitaireView(canvaJeu);
		canvaJeu.setFocusTraversable(true);
		model.addGameObserver(view);
		setupOptions();
		reset();
	}
	
	private void setupOptions() {
		view.setTheme(MorpionSolitaireView.M_THEME);
		theme.getParent().getScene().getRoot().setStyle("-fx-base: #0d3d00");
		mode.getItems().removeAll(mode.getItems());
		mode.getItems().addAll("5T", "5D");
		mode.getSelectionModel().select("5D");
	}

	@FXML
	private void reset() {
		model.init();
		Mode mode = this.mode.getSelectionModel().getSelectedItem().equalsIgnoreCase("5T") ? Mode.FT : Mode.FD;
		model.setGameMode(mode);
	}

	@FXML
	private void undoMove() {
		model.undoMove();
	}

	@FXML
	private void gameModeChanged() {
		Mode mode = this.mode.getSelectionModel().getSelectedItem().equalsIgnoreCase("5T") ? Mode.FT : Mode.FD;
		model.setGameMode(mode);
	}

	public void setModel(MorpionSolitaireModel model) {
		this.model = model;
	}
}