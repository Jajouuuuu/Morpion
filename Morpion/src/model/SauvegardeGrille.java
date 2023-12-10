package model;

import java.io.*;
import java.util.List;

public class SauvegardeGrille implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Grid grid;
    private List<Point> playedPoints;
    private List<Line> playedLines;

    public SauvegardeGrille(Grid grid, List<Point> playedPoints, List<Line> playedLines) {
        this.grid = grid;
        this.playedPoints = playedPoints;
        this.playedLines = playedLines;
    }

    public static void save(SauvegardeGrille sauvegardeGrille, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(sauvegardeGrille);
        }
        System.out.println("La grille a été sérialisée.");
    }

    public static SauvegardeGrille load(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (SauvegardeGrille) in.readObject();
        } catch (EOFException e) {
            e.printStackTrace();
            System.out.println("Unexpected end of file: " + e.getMessage());
            return null;
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public List<Point> getPlayedPoints() {
        return playedPoints;
    }

    public List<Line> getPlayedLines() {
        return playedLines;
    }
}
