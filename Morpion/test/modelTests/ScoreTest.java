package modelTests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.Score;

public class ScoreTest {

    @Test
    public void testScoreInitialization() {
        String username = "JeanneDupond";
        int scoreValue = 42;
        Score score = new Score(username, scoreValue);

        assertEquals(username, score.getUsername());
        assertEquals(scoreValue, score.getScore());
        assertEquals(username, score.getUsername());
    }

}
