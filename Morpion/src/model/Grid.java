package model;

import static model.Direction.values;

import java.io.Serializable;
import java.util.*;

/**
 * Représente la grille de jeu du Morpion Solitaire.
 * La grille est composée de points, de lignes et de modes de jeu.
 */
public class Grid implements Serializable {

	private static final long serialVersionUID = 1L;
	private final BlockedDirectionPoint[][] grid;
	private final Set<Point> points;
	private final List<Line> lines;
	private Mode mode;

	/**
     * Constructeur de la classe Grid.
     *
     * @param width La largeur de la grille.
     * @param height La hauteur de la grille.
     * @param mode Le mode de jeu (FT ou FD).
     */
	public Grid(int width, int height, Mode mode) {
		this.grid = new BlockedDirectionPoint[width][height];
		lines = new ArrayList<>();
		points = new HashSet<>();
		this.mode = mode;
	}

	/**
     * Renvoie l'ensemble des points de la grille.
     *
     * @return L'ensemble des points de la grille.
     */
	public Set<Point> points() {
		return Collections.unmodifiableSet(points);
	}

	/**
     * Initialise un point dans la grille.
     *
     * @param x Coordonnée x du point.
     * @param y Coordonnée y du point.
     */
	public void initPoint(int x, int y) {
		if (grid[x][y] == null) {
			grid[x][y] = new BlockedDirectionPoint(x, y);
			points.add(grid[x][y]);
		}
	}

	/**
	 * Renvoie la liste des lignes potentielles sur toute la grille.
	 *
	 * @return Liste des lignes potentielles.
	 */
	public List<Line> possibleLines() {
		HashSet<Point> pointsSoFar = new HashSet<>();
		List<Line> possibleLines = new ArrayList<>();
		points.forEach(gridPoint -> getNeighbors(gridPoint).stream()
				.filter(point -> !pointsSoFar.contains(gridPoint))
				.forEach(point -> {
					pointsSoFar.add(point);
					possibleLines.addAll(possibleLines(point.x, point.y));
				}));
		return possibleLines;
	}

	/**
	 * Renvoie la liste des lignes potentielles pour un point spécifique (x, y).
	 *
	 * @param x Coordonnée x du point.
	 * @param y Coordonnée y du point.
	 * @return Liste des lignes potentielles pour le point spécifié.
	 */
	public List<Line> possibleLines(int x, int y) {
		if (!isValidX(x) || !isValidY(y) || grid[x][y] != null) {
			return new ArrayList<>();
		}
		ArrayList<Line> possibleLines = new ArrayList<>();
		for (Direction direction : values()) {
			possibleLines.addAll(possibleLines(x, y, direction));
		}
		return possibleLines;
	}

	/**
	 * Renvoie la liste des lignes potentielles pour un point spécifique (x, y) dans une direction donnée.
	 *
	 * @param x          Coordonnée x du point.
	 * @param y          Coordonnée y du point.
	 * @param direction  Direction dans laquelle rechercher des lignes potentielles.
	 * @return Liste des lignes potentielles pour le point spécifié dans la direction donnée.
	 */
	public List<Line> possibleLines(int x, int y, Direction direction) {
		if (grid[x][y] != null) {
			return new ArrayList<>();
		}
		int dirX = 0, dirY = 0;
		switch (direction) {
		case HORIZONTAL -> dirX = 1;
		case VERTICAL -> dirY = 1;
		case UP -> {
			dirX = 1;
			dirY = -1;
		}
		case DOWN -> {
			dirX = 1;
			dirY = 1;
		}
		}
		ArrayList<Line> possibleLines = new ArrayList<>();
		int numLocksAllowed = 0;
		switch (mode) {
		case FT -> numLocksAllowed = 1;
		case FD -> numLocksAllowed = 0;
		}
		for (int i = -4; i <= 0; i++) {
			Line line = new Line();
			switch (mode) {
			case FT -> numLocksAllowed = 1;
			case FD -> numLocksAllowed = 0;
			}
			for (int j = 0; j < 5; j++) {
				int x2 = x + dirX * (i + j);
				int y2 = y + dirY * (i + j);
				if (!isValidX(x2) || !isValidY(y2)) {
					line = null;
					break;
				}
				if (grid[x2][y2] != null && !grid[x2][y2].isLocked(direction)) {
					line.addPoint(new Point(x2, y2));
				} else if (x2 == x && y2 == y) {
					Point p = new Point(x2, y2);
					line.addPoint(p);
					line.setNewPoint(p);
				} else if (grid[x2][y2] != null) {
					if (numLocksAllowed == 0) {
						line = null;
						break;
					} else {
						line.addPoint(new Point(x2, y2));
						numLocksAllowed--;
					}
				} else {
					line = null;
					break;
				}
			}
			if (line != null) {
				line.setDirection(direction);
				possibleLines.add(line);
			}
		}
		return possibleLines;
	}

	/**
	 * Vérifie si la coordonnée x est valide.
	 *
	 * @param x Coordonnée x à vérifier.
	 * @return true si la coordonnée x est valide, sinon false.
	 */
	public boolean isValidX(int x) {
		return x >= 0 && x < width();
	}

	/**
	 * Vérifie si la coordonnée y est valide.
	 *
	 * @param y Coordonnée y à vérifier.
	 * @return true si la coordonnée y est valide, sinon false.
	 */
	public boolean isValidY(int y) {
		return y >= 0 && y < height();
	}

	/**
     * Renvoie la largeur de la grille.
     *
     * @return La largeur de la grille.
     */
	public int width() {
		return grid.length;
	}

	/**
     * Renvoie la hauteur de la grille.
     *
     * @return La hauteur de la grille.
     */
	public int height() {
		return grid[0].length;
	}

	/**
     * Initialise la grille avec une disposition de points de départ.
     */
	public void init() {
		int startX = (width() - 10) / 2;
		int startY = (height() - 10) / 2;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i < 3 && (j < 3 || j > 6)) continue;
				if (i > 6 && (j < 3 || j > 6)) continue;
				if (i > 0 && i < 9 && j > 3 && j < 6) continue;
				if (j > 0 && j < 9 && i > 3 && i < 6) continue;
				initPoint(startX + i, startY + j);
			}
		}
	}

	/**
	 * Ajoute une nouvelle ligne à la grille à partir des points spécifiés et de la direction.
	 *
	 * @param points     Liste de points formant la ligne.
	 * @param direction  Direction de la ligne.
	 */
	public void addLine(List<Point> points, Direction direction) {
		Line line = new Line(points, direction);
		for (Point point : points) {
			if (grid[point.x][point.y] == null) {
				initPoint(point.x, point.y);
				line.setNewPoint(grid[point.x][point.y]);
				break;
			}
		}
		lines.add(line);
	}

	/**
	 * Ajoute une ligne spécifiée à la grille.
	 *
	 * @param line Ligne à ajouter.
	 */
	public void addLine(Line line) {
		line.setNumber(lines.size() + 1);
		line.points().forEach(point -> {
			if (grid[point.x][point.y] == null) {
				initPoint(point.x, point.y);
				line.setNewPoint(grid[point.x][point.y]);
			}
			grid[point.x][point.y].lock(line.getDirection());
		});
		lines.add(line);
	}

	/**
	 * Supprime un point spécifié de la grille.
	 *
	 * @param point Point à supprimer.
	 */
	public void deletePoint(Point point) {
		grid[point.x][point.y] = null;
		points.remove(point);
	}

	/**
	 * Renvoie une collection de points voisins pour un point donné.
	 *
	 * @param point Point pour lequel trouver les voisins.
	 * @return Collection de points voisins.
	 */
	public Collection<Point> getNeighbors(Point point) {
		List<Point> neighbors = new ArrayList<>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue;
				int x = point.x + i;
				int y = point.y + j;
				if (isValidX(x) && isValidY(y)) {
					neighbors.add(new Point(x, y));
				}
			}
		}
		return neighbors;
	}

	/**
	 * Renvoie une liste immuable des lignes présentes dans la grille.
	 *
	 * @return Liste immuable des lignes.
	 */
	public List<Line> lines() {
		return Collections.unmodifiableList(lines);
	}

	/**
	 * Renvoie le mode de jeu de la grille.
	 *
	 * @param mode Mode de jeu.
	 * @return Le mode de jeu de la grille.
	 */
	public Mode getMode(Mode mode) {
		return mode;
	}

	/**
	 * Définit le mode de jeu de la grille.
	 *
	 * @param mode Mode de jeu à définir.
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
     * Renvoie une copie de la grille.
     *
     * @return Une copie de la grille.
     */
	public Grid copy() {
		Grid copy = new Grid(width(), height(), mode);
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				if (grid[i][j] == null) continue;
				copy.grid[i][j] = grid[i][j].copy();
			}
		}
		copy.lines.addAll(lines.stream().map(Line::copy).toList());
		copy.points.addAll(points);
		return copy;
	}

	/**
     * Annule la dernière ligne ajoutée à la grille.
     */
	public void undoLine() {
		Line line = lines.get(lines.size() - 1);
		deletePoint(line.getNewPoint());
		for (Point point : line.points()) {
			if (grid[point.x][point.y] != null) {
				grid[point.x][point.y].unlock(line.getDirection());
			}
		}
		lines.remove(line);
	}

	/**
     * Renvoie une nouvelle grille enfant résultant de l'ajout d'une ligne.
     *
     * @param line La ligne à ajouter.
     * @return Une nouvelle grille enfant.
     */
	public Grid child(Line line) {
		Grid newGrid = copy();
		newGrid.addLine(line);
		return newGrid;
	}

	/**
     * Renvoie la liste des lignes présentes dans la grille.
     *
     * @return La liste des lignes présentes dans la grille.
     */
	public List<Line> getLines() {
		return lines;
	}
<<<<<<< HEAD
=======

	/**
     * Renvoie le mode de jeu de la grille.
     *
     * @return Le mode de jeu de la grille.
     */
	public Mode getMode() {
		return mode;
	}
>>>>>>> refs/remotes/Morpion/dev/jaj
}
