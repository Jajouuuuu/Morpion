package NMCSTests;

import static org.junit.Assert.*;
import org.junit.Test;
import NMCS.NmcsSearch;
import NMCS.NmcsState;
import model.Grid;
import model.Line;
import model.Mode;

public class NmcsSearchTest {

    @Test
    public void testSearchBestState() {
    	Grid grid = new Grid(14, 14, Mode.FD);
		grid.init();
		// NmcsState nmcsState = new NmcsState(grid);

        // Set up a mock implementation of NmcsState for simulation
        NmcsState simulatedState = new NmcsState(grid);
        simulatedState.setScore(10);  

        // Mock the nextState method to return the simulated state
        NmcsState mockState = new NmcsState(grid) {
            @Override
            public NmcsState nextState(Line line) {
                return simulatedState;
            }
        };

        // Mock the getPossibleLines method to return a list of moves
        //mockState.setPossibleLines(List.of(new Line(/* define the line parameters here */)));

        // Call the searchBestState method
        NmcsState resultState = NmcsSearch.searchBestState(mockState, 3);

        // Verify that the resultState has the expected properties
        assertEquals(simulatedState, resultState);
        assertEquals(simulatedState.getScore(), resultState.getScore());
        assertEquals(simulatedState.getAddedLine(), resultState.getAddedLine());
    }
}