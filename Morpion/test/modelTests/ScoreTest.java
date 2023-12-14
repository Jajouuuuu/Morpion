package modelTests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.Score;

public class ScoreTest {

    @Test
    public void testScoreInitialization() {
        String username = "JeanneDupond";
        double scoreValue = 42.0;

        Score score = new Score(username, scoreValue);

        assertEquals(username, score.getUsername());
        assertEquals(scoreValue, score.getScore(), 0.001);
    }

    @Test
    public void testGetUsername() {
        String username = "JeanDupont";
        double scoreValue = 36.5;

        Score score = new Score(username, scoreValue);

        assertEquals(username, score.getUsername());
    }

    @Test
    public void testGetScore() {
        String username = "Alice";
        double scoreValue = 78.9;

        Score score = new Score(username, scoreValue);

        assertEquals(scoreValue, score.getScore(), 0.001); // Using delta for double comparison
    }

}
