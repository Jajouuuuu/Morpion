package model;

import java.io.*;
import java.util.List;

/**
 * La classe SauvegardeGrille permet de sauvegarder et charger l'état d'une grille de jeu.
 */
public class SauvegardeGrille implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Grid grid;
    private List<Point> playedPoints;
    private List<Line> playedLines;

    /**
     * Constructeur pour créer un objet SauvegardeGrille avec une grille, une liste de points joués,
     * et une liste de lignes jouées.
     *
     * @param grid         La grille à sauvegarder/charger.
     * @param playedPoints La liste des points joués.
     * @param playedLines  La liste des lignes jouées.
     */
    public SauvegardeGrille(Grid grid, List<Point> playedPoints, List<Line> playedLines) {
        this.grid = grid;
        this.playedPoints = playedPoints;
        this.playedLines = playedLines;
    }

    /**
     * Sauvegarde l'objet SauvegardeGrille dans un fichier spécifié.
     *
     * @param sauvegardeGrille L'objet SauvegardeGrille à sauvegarder.
     * @param filename         Le nom du fichier de sauvegarde.
     * @throws IOException En cas d'erreur lors de l'écriture du fichier.
     */
    public static void save(SauvegardeGrille sauvegardeGrille, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(sauvegardeGrille);
        }
        System.out.println("La grille a été sérialisée.");
    }

    /**
     * Charge l'objet SauvegardeGrille depuis un fichier spécifié.
     *
     * @param filename Le nom du fichier à charger.
     * @return L'objet SauvegardeGrille chargé depuis le fichier.
     * @throws IOException            En cas d'erreur lors de la lecture du fichier.
     * @throws ClassNotFoundException Si la classe n'a pas pu être trouvée lors de la désérialisation.
     */
    public static SauvegardeGrille load(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (SauvegardeGrille) in.readObject();
        } catch (EOFException e) {
            e.printStackTrace();
            System.out.println("Unexpected end of file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retourne la grille associée à cette sauvegarde.
     *
     * @return La grille associée à cette sauvegarde.
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Retourne la liste des points joués.
     *
     * @return La liste des points joués.
     */
    public List<Point> getPlayedPoints() {
        return playedPoints;
    }

    /**
     * Retourne la liste des lignes jouées.
     *
     * @return La liste des lignes jouées.
     */
    public List<Line> getPlayedLines() {
        return playedLines;
    }
}