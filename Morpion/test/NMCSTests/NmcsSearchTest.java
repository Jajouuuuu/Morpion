package NMCSTests;

import static org.junit.Assert.assertEquals;

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

		NmcsState simulatedState = new NmcsState(grid); 
		simulatedState.setScore(10);

		NmcsState mockState = new NmcsState(grid) {

			
	@Override 
	public NmcsState nextState(Line line) { 
		return simulatedState; } };
			NmcsState resultState = NmcsSearch.searchBestState(mockState, 3);

			assertEquals(simulatedState, resultState);
			assertEquals(simulatedState.getScore(), resultState.getScore());
			assertEquals(simulatedState.getAddedLine(), resultState.getAddedLine());
	}
}