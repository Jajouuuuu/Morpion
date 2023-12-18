package NMCSTests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.Grid;
import model.Line;
import model.Mode;
import NMCS.NmcsState;

import java.util.ArrayList;
import java.util.List;

public class NmcsStateTest {

	@Test
	public void testInitialState() {

		Grid grid = new Grid(14, 14, Mode.FD);
		grid.init();
		NmcsState nmcsState = new NmcsState(grid);

		assertEquals(grid, nmcsState.getGrid());
		assertEquals(grid.lines().size(), nmcsState.getScore());
		assertFalse(nmcsState.isTerminalPosition());
		assertNull(nmcsState.getAddedLine());
		assertNotNull(nmcsState.getPossibleLines());
	}

	@Test
	public void testCustomScore() {
		Grid grid = new Grid(14, 14, Mode.FD);
		grid.init();
		int customScore = 10;
		NmcsState nmcsState = new NmcsState(grid, customScore);

		assertEquals(grid, nmcsState.getGrid());
		assertEquals(customScore, nmcsState.getScore());
		assertFalse(nmcsState.isTerminalPosition());
		assertNull(nmcsState.getAddedLine());
		assertNotNull(nmcsState.getPossibleLines());
	}

	@Test
	public void testNextState() {
		Grid grid = new Grid(14, 14, Mode.FD);
		grid.init();
		NmcsState nmcsState = new NmcsState(grid);
		Line line = nmcsState.getPossibleLines().get(0);

		NmcsState nextState = nmcsState.nextState(line);

		// Verify that the original state is not modified
		assertEquals(grid, nmcsState.getGrid());
		assertEquals(grid.lines().size(), nmcsState.getScore());

		// Verify that the new state is modified correctly
		assertNotEquals(grid, nextState.getGrid());
		assertEquals(grid.lines().size() + 1, nextState.getScore());
		assertEquals(line, nextState.getGrid().lines().get(nextState.getGrid().lines().size()-1));
		assertNotNull(nextState.getPossibleLines());
	}

	
	  @Test 
	  public void testSimulationToTheEnd() { 
		  Grid grid = new Grid(14, 14,
		  Mode.FD); grid.init(); 
		  NmcsState nmcsState = new NmcsState(grid);
		  
		  // Mocking no possibleLines for simulation 
		  List<Line> mockPossibleLines = new
		  ArrayList<>(); 
		  mockPossibleLines.add(new Line());
		  nmcsState.setPossibleLines(mockPossibleLines);
		  
		  nmcsState.simulationToTheEnd();
		  
		  // Verify that the simulation results in a terminal position
		  assertTrue(nmcsState.isTerminalPosition()); 
		  assertEquals(grid.lines().size(),
		  nmcsState.getScore()); 
	  } 
}
