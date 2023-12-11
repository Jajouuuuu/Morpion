package model;

import java.io.*;

public class SauvegardeGrille {

    public static void saveGrid(Grid grid, String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(grid);
        out.close();
        file.close();
        System.out.println("La grille a été sérialisé.");
    }

    public static Grid loadGrid(String filename) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file);
        Grid grid = (Grid) in.readObject();
        in.close();
        file.close();
        System.out.println("La grille a été désérialisé.");
        return grid;
    }
}
