package NMCSTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import NMCS.NmcsSearch;
import NMCS.NmcsState;
import model.Grid;
import model.Mode;

public class NmcsSearchTest {

	@Test 
	public void testSearchBestState() { 
		System.out.println("test1");
		Grid grid = new Grid(14, 14, Mode.FD); 
		grid.init(); 
		NmcsState initialState = new NmcsState(grid); 
		
		NmcsState nextSearch = NmcsSearch.searchBestState(initialState, 1);
		
		assertEquals(initialState.getScore(), 0);
		assertTrue(nextSearch.getScore()>50);
		assertTrue(nextSearch.getScore()<180);
	}
}