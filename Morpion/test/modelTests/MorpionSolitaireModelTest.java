package modelTests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Grid;
import model.MorpionSolitaireModel;

public class MorpionSolitaireModelTest {

    private MorpionSolitaireModel morpionSolitaireModel;

    @Before
    public void setUp() {
        morpionSolitaireModel = new MorpionSolitaireModel();
    }

	/*
	 * @Test public void testInitialization() {
	 * assertNotNull(morpionSolitaireModel);
	 * assertFalse(morpionSolitaireModel.isGameOver());
	 * assertNull(morpionSolitaireModel.getPlayerName()); }
	 */

	/*
	 * @Test public void testInit() { morpionSolitaireModel.init();
	 * 
	 * assertNotNull(morpionSolitaireModel);
	 * assertFalse(morpionSolitaireModel.isGameOver());
	 * assertNull(morpionSolitaireModel.getPlayerName()); }
	 */

    @Test
    public void testHandleHumanMove() {
    	Grid grid = morpionSolitaireModel.getGrid().copy();
    	
    	// Coup non valide
    	morpionSolitaireModel.handleHumanMove(0, 0);
        assertTrue(morpionSolitaireModel.getScore() == 0);
        assertFalse(morpionSolitaireModel.isGameOver());
        assertEquals(grid.points(), morpionSolitaireModel.getGrid().points());
        assertEquals(grid.lines(), morpionSolitaireModel.getGrid().lines());

    	// Coup valide
        morpionSolitaireModel.handleHumanMove(4, 4);
        assertTrue(morpionSolitaireModel.getScore() == 1);
        assertFalse(morpionSolitaireModel.isGameOver());
        assertNotEquals(grid.points(), morpionSolitaireModel.getGrid().points());
        assertNotEquals(grid.lines(), morpionSolitaireModel.getGrid().lines());
}

    @Test
    public void testHandleRandomMove() {
    	Grid grid = morpionSolitaireModel.getGrid().copy();
    	
    	// Coup valide
        morpionSolitaireModel.handleRandomMove();
        assertTrue(morpionSolitaireModel.getScore() == 1);
        assertFalse(morpionSolitaireModel.isGameOver());
        assertNotEquals(grid.points(), morpionSolitaireModel.getGrid().points());
        assertNotEquals(grid.lines(), morpionSolitaireModel.getGrid().lines());
    }

    @Test
    public void testHandleRandomGame() {
        morpionSolitaireModel.handleRandomGame();
        assertTrue(morpionSolitaireModel.getScore() >= 20);	// score minimal possible
        assertTrue(morpionSolitaireModel.isGameOver());
    }

    @Test
    public void testHandleNmcsMove() {
    	Grid grid = morpionSolitaireModel.getGrid().copy();
    	
    	// Coup valide
        morpionSolitaireModel.handleNmcsMove();
        assertTrue(morpionSolitaireModel.getScore() == 1);
        assertFalse(morpionSolitaireModel.isGameOver());
        assertNotEquals(grid.points(), morpionSolitaireModel.getGrid().points());
        assertNotEquals(grid.lines(), morpionSolitaireModel.getGrid().lines());
    }

    @Test
    public void testHandleNmcsGame() {
    	morpionSolitaireModel.handleNmcsGame();
        assertTrue(morpionSolitaireModel.getScore() >= 20);	// score minimal possible
        assertTrue(morpionSolitaireModel.isGameOver());
    }

    @Test
    public void testUndoMove() {
    	Grid grid = morpionSolitaireModel.getGrid().copy();

        morpionSolitaireModel.handleHumanMove(4, 4);
        
        morpionSolitaireModel.undoMove();
        assertTrue(morpionSolitaireModel.getScore() == 0);
        assertFalse(morpionSolitaireModel.isGameOver());
        assertEquals(grid.points(), morpionSolitaireModel.getGrid().points());
        assertEquals(grid.lines(), morpionSolitaireModel.getGrid().lines());

    }

}

