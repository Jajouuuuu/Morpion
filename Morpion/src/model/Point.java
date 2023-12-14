package model;

import java.io.Serializable;

/**
 * La classe Point représente un point dans un système de coordonnées bidimensionnel.
 * Les objets Point sont immuables.
 */
public class Point implements Serializable {

    private static final long serialVersionUID = 1L;
	public final int x;
	public final int y;
    private int hash;

    /**
     * Constructeur pour créer un objet Point avec les coordonnées spécifiées.
     *
     * @param x La coordonnée x du point.
     * @param y La coordonnée y du point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Détermine si l'objet spécifié est égal à ce point.
     *
     * @param other L'objet à comparer avec ce point.
     * @return true si l'objet spécifié est égal à ce point, sinon false.
     */
    public boolean equals(Object other) {
        if (other.getClass() != getClass())
            return false;
        Point p = (Point) other;
        return x == p.x && y == p.y;
    }

    /**
     * Retourne le code de hachage pour ce point.
     *
     * @return Le code de hachage du point.
     */
    @Override
    public int hashCode() {
        if (hash == 0) {
            long bits = 7L;
            bits = 31L * bits + x;
            bits = 31L * bits + y;
            hash = (int) (bits ^ (bits >> 32));
        }
        return hash;
    }
    
    /**
     * Retourne une représentation sous forme de chaîne de caractères du point.
     *
     * @return Une chaîne de caractères représentant le point dans le format "(x, y)".
     */
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}
