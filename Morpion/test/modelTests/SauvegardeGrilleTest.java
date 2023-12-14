package modelTests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.Grid;
import model.Mode;
import model.SauvegardeGrille;

import java.io.*;

class SauvegardeGrilleTest {

    @Test
    public void testSaveAndLoadGrid() {
        Grid originalGrid = new Grid(14, 14, Mode.FT);
        String filename = "testGrid.ser";

        try {
            SauvegardeGrille.saveGrid(originalGrid, filename);

            Grid loadedGrid = SauvegardeGrille.loadGrid(filename);
            assertNotNull(loadedGrid);
            assertEquals(originalGrid.lines().size(), loadedGrid.lines().size());
            assertEquals(originalGrid.points(), loadedGrid.points());
            // Add more assertions as needed

        } catch (IOException | ClassNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

//    @Test
//    public void testLoadNonExistentFile() {
//    }

}

