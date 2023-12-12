
package controler;

import java.util.List;

import app.App;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MorpionSolitaireModel;
import model.Score;

/**
 * Contrôleur pour la gestion des meilleurs scores.
 */
public class MeilleursScoresController {
	
    @FXML 
    private TableView<Score> scoresTable;
    @FXML 
    private TableColumn<Score, String> usernameColumn;
    @FXML 
    private TableColumn<Score, Integer> scoreColumn;
    private MorpionSolitaireModel model;
    
    /**
     * Initialise le contrôleur.
     */
    public void initialize() {
        System.out.println(model);
    }
    
    /**
     * Gère l'action du bouton de retour.
     */
    @FXML
	private void handleRetourButton() {
    	 App.getInstance().loadPreviousPage();
    }

    /**
     * Définit le modèle et initialise le contrôleur.
     *
     * @param model Modèle de Morpion Solitaire.
     */
    public void setModelAndInitialize(MorpionSolitaireModel model) {
        this.model = model;
        System.out.println(this.model);
        if (model != null) {
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
            List<Score> scores = model.getTopScores(10);
            scoresTable.setItems(FXCollections.observableList(scores));
        } else {
            System.err.println("Error: 'model' is null in MeilleursScoresController.initialize()");
        }
    }
}
