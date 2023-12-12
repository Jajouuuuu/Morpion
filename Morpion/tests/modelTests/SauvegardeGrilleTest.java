package modelTests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.Grid;

import java.io.IOException;

class SauvegardeGrilleTest {

    @Test
    void testSaveAndLoadGrid() {
        // Create a Grid for testing
        Grid originalGrid = new Grid(/* provide necessary parameters */);

        // Define a filename for the test
        String filename = "testGrid.ser";

        try {
            // Save the grid
            SauvegardeGrille.saveGrid(originalGrid, filename);

            // Load the grid
            Grid loadedGrid = SauvegardeGrille.loadGrid(filename);

            // Check if the loaded grid is not null
            assertNotNull(loadedGrid);

            // Add more specific assertions based on your Grid class
            // For example, check if relevant properties or methods are equal
            assertEquals(originalGrid.getGridWidth(), loadedGrid.getGridWidth());
            assertEquals(originalGrid.getGridHeight(), loadedGrid.getGridHeight());
            // Add more assertions as needed

        } catch (IOException | ClassNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testLoadNonExistentFile() {
        // Define a filename that does not exist
        String filename = "nonexistent.ser";

        // Attempt to load the grid from a non-existent file
        assertThrows(IOException.class, () -> SauvegardeGrille.loadGrid(filename));
    }

    // Add more tests as needed based on the behavior of your class
}

