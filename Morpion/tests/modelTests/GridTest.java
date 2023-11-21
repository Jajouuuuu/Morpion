package modelTests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.Grid;
import model.Mode;

import java.util.List;

public class GridTest {

    private Grid grid;

    @Before
    public void setUp() {
        grid = new Grid(10, 10, Mode.FT);
        grid.init();
    }

    @Test
    public void testInit() {
        assertEquals(36, grid.points().size());
    }

    @Test
    public void testPossibleLines() {
        List<model.Line> possibleLines = grid.possibleLines();
        assertFalse(possibleLines.isEmpty());
    }

    @Test
    public void testAddLine() {
        List<model.Line> possibleLines = grid.possibleLines();
        assertFalse(possibleLines.isEmpty());
        model.Line line = possibleLines.get(0);
        grid.addLine(line);
        assertEquals(1, grid.lines().size());
    }

    @Test
    public void testCopy() {
        Grid copy = grid.copy();
        assertEquals(grid.points(), copy.points());
        assertNotSame(grid.points(), copy.points());
        assertEquals(grid.lines(), copy.lines());
        assertNotSame(grid.lines(), copy.lines());
    }

    @Test
    public void testUndoLine() {
        List<model.Line> possibleLines = grid.possibleLines();
        assertFalse(possibleLines.isEmpty());
        model.Line line = possibleLines.get(0);
        grid.addLine(line);
        assertEquals(1, grid.lines().size());
        grid.undoLine();
        assertEquals(0, grid.lines().size());
    }

    @Test
    public void testChild() {
        List<model.Line> possibleLines = grid.possibleLines();
        assertFalse(possibleLines.isEmpty());
        model.Line line = possibleLines.get(0);
        Grid newGrid = grid.child(line);
        assertEquals(grid.points().size(), newGrid.points().size() - 1);
        assertEquals(grid.lines().size() + 1, newGrid.lines().size());
    }
}

