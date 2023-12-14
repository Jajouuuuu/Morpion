package modelTests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.Score;
import model.ScoreSauvegarde;

import java.io.*;
import java.util.List;

class ScoreSauvegardeTest {

    @Test
    public void testLoadScores() {
        try {
            List<Score> scores = ScoreSauvegarde.loadScores();
            assertNotNull(scores);
            assertFalse(scores.isEmpty());
        } catch (IOException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSaveScore() {
        // Create a temporary score entry for testing
        Score testScore = new Score("TestUser", 50.0);

        try {
            // Save the score
            ScoreSauvegarde.saveScore(testScore);

            // Load scores and check if the test score is present
            List<Score> scores = ScoreSauvegarde.loadScores();
            assertTrue(scores.contains(testScore));
        } catch (IOException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    } 	

   
    @Test
    void testLoadScoresForPlayer() {
        String playerName = "TestPlayer";

        try {
            List<Double> scores = ScoreSauvegarde.loadScores(playerName);
            assertNotNull(scores);
            assertFalse(scores.isEmpty());
        } catch (IOException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }



    // Add more tests as needed based on the behavior of your class
}
