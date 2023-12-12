package model;

import java.io.Serializable;
import java.util.*;


public class Line implements Serializable {
	
    private static final long serialVersionUID = 1L;
	private final List<Point> points;
    private Point pointNew;
    private int n;
    private Direction direction;
    private int hash;

    /**
     * Constructeur par défaut. Initialise une ligne vide.
     */
    public Line() {
        points = new ArrayList<>();
    }
    
    /**
	 * Constructeur avec une collection de points.
	 *
	 * @param points Collection de points constituant la ligne.
	 */
    public Line(Collection<Point> points) {
        this.points = new ArrayList<>();
        this.points.addAll(points);
    }
    
    /**
	 * Constructeur avec une collection de points et une direction.
	 *
	 * @param points     Collection de points constituant la ligne.
	 * @param direction  Direction de la ligne.
	 */
	public Line(Collection<Point> points, Direction direction) {
        this.points = new ArrayList<>();
        this.points.addAll(points);
        this.direction = direction;
    }
	
	/**
	 * Constructeur avec une collection de points, un nouveau point, une direction et un numéro.
	 *
	 * @param points     Collection de points constituant la ligne.
	 * @param newPoint   Nouveau point ajouté à la ligne.
	 * @param direction  Direction de la ligne.
	 * @param number     Numéro de la ligne.
	 */
	public Line(Collection<Point> points, Point newPoint, Direction direction, int number) {
        this.points = new ArrayList<>();
        this.points.addAll(points);
        this.pointNew = newPoint;
        this.direction = direction;
        this.n = number;
    }

	/**
	 * Renvoie la valeur de l'attribut représentant le numéro de la ligne.
	 *
	 * @return Numéro de la ligne.
	 */
    public int getN() {
		return n;
	}

    /**
     * Définit la valeur de l'attribut représentant le numéro de la ligne.
     *
     * @param n Nouveau numéro de la ligne.
     */
	public void setN(int n) {
		this.n = n;
	}

	/**
	 * Ajoute un point à la ligne.
	 *
	 * @param point Point à ajouter à la ligne.
	 */
    public void addPoint(Point point) {
        points.add(point);
    }

    /**
     * Définit la direction de la ligne.
     *
     * @param direction Nouvelle direction de la ligne.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Renvoie la direction de la ligne.
     *
     * @return Direction de la ligne.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Renvoie une vue non modifiable de la liste de points.
     *
     * @return Liste de points de la ligne.
     */
    public List<Point> points() {
        return Collections.unmodifiableList(points);
    }

    /**
     * Définit le nouveau point de la ligne.
     *
     * @param newPoint Nouveau point de la ligne.
     */
    public void setNewPoint(Point newPoint) {
        this.pointNew = newPoint;
    }

    /**
     * Renvoie le nouveau point de la ligne.
     *
     * @return Nouveau point de la ligne.
     */
    public Point getNewPoint() {
        return pointNew;
    }

    /**
     * Renvoie le numéro de la ligne.
     *
     * @return Numéro de la ligne.
     */
    public int getNumber() {
        return n;
    }

    /**
     * Définit le numéro de la ligne.
     *
     * @param number Nouveau numéro de la ligne.
     */
    public void setNumber(int number) {
        this.n = number;
    }

    /**
     * Renvoie une copie de la ligne.
     *
     * @return Copie de la ligne.
     */
    public Line copy() {
        return new Line(points, pointNew, direction, n);
    }

    /**
     * Renvoie le code de hachage de la ligne.
     *
     * @return Code de hachage de la ligne.
     */
    @Override
    public int hashCode() {
        System.out.println("hashing");
        return Objects.hash(points.get(0), direction);
    }

    /**
     * Renvoie le hash de la ligne.
     *
     * @return Hash de la ligne.
     */
	public int getHash() {
		return hash;
	}

	/**
	 * Définit le hash de la ligne.
	 *
	 * @param hash Nouveau hash de la ligne.
	 */
	public void setHash(int hash) {
		this.hash = hash;
	}
	
	/**
     * Indique si cette ligne est égale à l'objet spécifié.
     *
     * @param o Objet à comparer.
     * @return true si les lignes sont égales, sinon false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return n == line.n && points.equals(line.points) && pointNew.equals(line.pointNew) && direction == line.direction;
    }
	
	/**
     * Renvoie une représentation sous forme de chaîne de caractères de la ligne.
     *
     * @return Représentation de la ligne en chaîne de caractères.
     */
    public String toString() {
        return pointNew + "-" + direction + ": " + points.toString();
    }
}
