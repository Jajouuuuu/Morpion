package modelTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Grid;
import model.Line;
import model.Mode;
import model.Point;
import model.SauvegardeGrille;

public class SauvegardeGrilleTest {

    private Grid testGrid;
    private List<Point> testPoints;
    private List<Line> testLines;
    private SauvegardeGrille testSauvegardeGrille;
    private String testFilename;

    @Before
    public void setUp() {
        // Initialisation des données de test
    	testGrid = new Grid(14, 14, Mode.FT);
    	testGrid.init();
        Line line = testGrid.possibleLines().get(0);
        testGrid.addLine(line);
        testLines = testGrid.lines();
        testSauvegardeGrille = new SauvegardeGrille(testGrid, testPoints, testLines);
        testFilename = "testSauvegardeGrille.sav";
    }

    @Test
    public void testSaveAndLoad() {
        try {
            // Test de sauvegarde
            SauvegardeGrille.save(testSauvegardeGrille, testFilename);

            // Test de chargement
            SauvegardeGrille loadedSauvegardeGrille = SauvegardeGrille.load(testFilename);

            // Vérification des données chargées
            assertNotNull(loadedSauvegardeGrille);
            assertEquals(testGrid.points(), loadedSauvegardeGrille.getGrid().points());
            assertEquals(testGrid.lines(), loadedSauvegardeGrille.getGrid().lines());
            assertEquals(testPoints, loadedSauvegardeGrille.getPlayedPoints());
            assertEquals(testLines, loadedSauvegardeGrille.getPlayedLines());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            assertNull("Une exception ne devrait pas être levée ici", e);
        }
    }

}

