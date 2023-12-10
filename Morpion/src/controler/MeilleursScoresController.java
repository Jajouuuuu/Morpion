
package controler;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MorpionSolitaireModel;
import model.Score;

public class MeilleursScoresController {
    @FXML private TableView<Score> scoresTable;
    @FXML private TableColumn<Score, String> usernameColumn;
    @FXML private TableColumn<Score, Integer> scoreColumn;

    private MorpionSolitaireModel model;
    
    public void initialize() {
        System.out.println(model);
    }

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
