package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.PlayObserver;
import model.Grid;
import model.Line;
import model.Point;

import java.util.List;

<<<<<<< HEAD
public class MorpionSolitaireView implements PlayObserver {
=======
import app.App;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * La classe MorpionSolitaireView représente la vue graphique du jeu Morpion Solitaire.
 * Elle implémente l'interface PlayObserver pour recevoir les mises à jour du modèle de jeu.
 */
public class MorpionSolitaireView implements PlayObserver, Serializable  {
>>>>>>> refs/remotes/Morpion/dev/jaj

	private static final long serialVersionUID = 1L;
	private Canvas canva;
	private static final int CELL_WIDTH = 25;
	private static final int CELL_HEIGHT = 25;
	public static final int WIDTH = CELL_WIDTH * 25, HEIGHT = CELL_HEIGHT * 25;
	private final int x = CELL_WIDTH;
	private final int y = CELL_HEIGHT;
<<<<<<< HEAD
	private Theme theme;
	
=======
	private transient Theme theme;
	private List<Point> playedPoints;
	private List<Line> playedLines;
	private Parent root; 
	private App app;

	/**
     * Construit un MorpionSolitaireView avec le canvas spécifié et l'application.
     *
     * @param canva Le canvas utilisé pour dessiner la grille de jeu.
     * @param app   L'instance principale de l'application.
     */
>>>>>>> refs/remotes/Morpion/dev/jaj
	@SuppressWarnings("exports")
	public MorpionSolitaireView(Canvas canva, App app) {
		this.canva = canva;
		canva.setWidth(WIDTH);
		canva.setHeight(HEIGHT);
		this.app = app; 
	}

<<<<<<< HEAD
=======
	/**
     * Constructeur par défaut pour MorpionSolitaireView.
     * Crée un nouveau canvas et initialise les listes des points et lignes joués.
     */
	public MorpionSolitaireView() {
		this.canva = new Canvas();
		this.playedPoints = new ArrayList<>();
		this.playedLines = new ArrayList<>();
		canva.setWidth(WIDTH);
		canva.setHeight(HEIGHT);
	}

	 /**
     * Définit le canvas utilisé par la vue.
     *
     * @param canva Le nouveau canvas.
     */
	@SuppressWarnings("exports")
	public void setCanvas(Canvas canva) {
		this.canva = canva;
	}

	/**
     * Thème graphique par défaut utilisé pour l'affichage.
     */
>>>>>>> refs/remotes/Morpion/dev/jaj
	public static final Theme M_THEME = new Theme(
	        Color.web("#03ba00"),
	        Color.web("#d0cdd4"),
	        Color.web("#b5a600"),
	        Color.web("#f6f2fa"),
	        Color.web("#b5a600")  
	);

	/**
     * Obtient le thème graphique actuel utilisé pour l'affichage.
     *
     * @return Le thème actuel.
     */
	public Theme getTheme() {
		return theme;
	}

	/**
     * Définit le thème graphique utilisé pour l'affichage.
     *
     * @param theme Le nouveau thème à appliquer.
     */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	/**
     * Obtient la coordonnée X du décalage pour le dessin.
     *
     * @return La coordonnée X du décalage.
     */
	public int getOffX() {
		return x;
	}

	/**
     * Obtient la coordonnée Y du décalage pour le dessin.
     *
     * @return La coordonnée Y du décalage.
     */
	public int getOffY() {
		return y;
	}

	/**
     * Obtient la largeur d'une cellule dans la grille.
     *
     * @return La largeur d'une cellule.
     */
	public int getCellWidth() {
		return CELL_WIDTH;
	}

	 /**
     * Obtient la hauteur d'une cellule dans la grille.
     *
     * @return La hauteur d'une cellule.
     */
	public int getCellHeight() {
		return CELL_HEIGHT;
	}

	/**
     * Calcule les dimensions du canvas en fonction de la largeur et de la hauteur de la grille.
     *
     * @param grid La grille de jeu actuelle.
     */
	public void calcDimensions(Grid grid) {
		int width = grid.width() * CELL_WIDTH;
		canva.setWidth(width);
		int height = grid.height() * CELL_HEIGHT;
		canva.setHeight(height);
	}

	 /**
     * Met à jour l'affichage en fonction de l'état actuel de la grille, des points et des lignes.
     *
     * @param grid            La grille de jeu actuelle.
     * @param highlightPoints La liste des points à mettre en surbrillance.
     * @param highlightLines  La liste des lignes à mettre en surbrillance.
     */
	public void update(Grid grid, List<Point> highlightPoints, List<Line> highlightLines) {
<<<<<<< HEAD
		GraphicsContext g = canva.getGraphicsContext2D();
		g.clearRect(0, 0, canva.getWidth(), canva.getHeight());
		g.setLineWidth(2.0);
		g.setStroke(Color.valueOf("#34495E"));
		g.strokeRect(0, 0, canva.getWidth(), canva.getHeight());
		System.out.println(canva.getWidth() + ", " + canva.getHeight());
		g.setLineWidth(1);
		g.setStroke(theme.backline());
		g.setLineWidth(1);
		for (double gridX = 0; gridX < grid.width(); gridX++) {
			double x1 = snap(x + gridX * CELL_WIDTH);
			double y1 = snap(y);
			double x2 = snap(x + gridX * CELL_WIDTH);
			double y2 = snap(y + (grid.height() - 1) * CELL_HEIGHT);
			g.strokeLine(x1, y1, x2, y2);
=======
		if (canva != null) {
			GraphicsContext g = canva.getGraphicsContext2D();
			g.clearRect(0, 0, canva.getWidth(), canva.getHeight());
			g.setLineWidth(2.0);
			g.setStroke(Color.valueOf("#cce6ff"));
			g.strokeRect(0, 0, canva.getWidth(), canva.getHeight());
			System.out.println(canva.getWidth() + ", " + canva.getHeight());
			g.setLineWidth(1);
			g.setStroke(theme.backline());
			g.setLineWidth(1);
			for (double gridX = 0; gridX < grid.width(); gridX++) {
				double x1 = snap(x + gridX * CELL_WIDTH);
				double y1 = snap(y);
				double x2 = snap(x + gridX * CELL_WIDTH);
				double y2 = snap(y + (grid.height() - 1) * CELL_HEIGHT);
				g.strokeLine(x1, y1, x2, y2);
			}
			for (double gridY = 0; gridY < grid.height(); gridY++) {
				double x1 = snap(x);
				double y1 = snap(y + gridY * CELL_HEIGHT);
				double x2 = snap(x + (grid.width() - 1) * CELL_WIDTH);
				double y2 = snap(y + gridY * CELL_HEIGHT);
				g.strokeLine(x1, y1, x2, y2);
			}
			g.setFill(theme.line());
			double radius = 5;
			g.setStroke(theme.line());
			grid.lines().forEach(line -> drawLine(line, g));
			g.setFill(theme.point());
			grid.points().forEach(point -> {
				g.fillOval(x + CELL_WIDTH * point.x - radius, y + CELL_HEIGHT * point.y - radius, 2 * radius, 2 * radius);
			});
			g.setStroke(theme.line());
			grid.lines().forEach(line -> drawNumberedPoint(line.getNewPoint(), line.getNumber(), g));
			g.setFill(theme.highlight());
			highlightPoints.forEach(point -> {
				g.fillOval(x + CELL_WIDTH * point.x - radius, y + CELL_HEIGHT * point.y - radius, 2 * radius, 2 * radius);
			});
			g.setStroke(theme.highlight());
			highlightLines.forEach(line -> drawLine(line, g));
			playedPoints.addAll(grid.points());
			playedLines.addAll(grid.lines());
			
			System.out.println("history " + app.getPageHistory());
			System.out.println("root " + root);
			if (root != null) {
				app.getPageHistory().push(this.root);
				System.out.println("history " + app.getPageHistory());
			}
		} else {
			System.err.println("Error: canva is null in MorpionSolitaireView.update()");
>>>>>>> refs/remotes/Morpion/dev/jaj
		}
		for (double gridY = 0; gridY < grid.height(); gridY++) {
			double x1 = snap(x);
			double y1 = snap(y + gridY * CELL_HEIGHT);
			double x2 = snap(x + (grid.width() - 1) * CELL_WIDTH);
			double y2 = snap(y + gridY * CELL_HEIGHT);
			g.strokeLine(x1, y1, x2, y2);
		}
		g.setFill(theme.line());
		double radius = 5;
		g.setStroke(theme.line());
		grid.lines().forEach(line -> drawLine(line, g));
		g.setFill(theme.point());
		grid.points().forEach(point -> {
			g.fillOval(x + CELL_WIDTH * point.x - radius, y + CELL_HEIGHT * point.y - radius, 2 * radius, 2 * radius);
		});
		g.setStroke(theme.line());
		grid.lines().forEach(line -> drawNumberedPoint(line.getNewPoint(), line.getNumber(), g));
		g.setFill(theme.highlight());
		highlightPoints.forEach(point -> {
			g.fillOval(x + CELL_WIDTH * point.x - radius, y + CELL_HEIGHT * point.y - radius, 2 * radius, 2 * radius);
		});
		g.setStroke(theme.highlight());
		highlightLines.forEach(line -> drawLine(line, g));
	}

	/**
	 * Définit l'application associée à cette vue.
	 *
	 * @param app L'application Morpion Solitaire.
	 */
	public void setApp(App app) {
		this.app = app;
	}

	/**
	 * Définit le nœud racine (root) de cette vue.
	 *
	 * @param root Le nœud racine de la vue.
	 */
	@SuppressWarnings("exports")
	public void setRoot(Parent root) {
		this.root = root;
	}

	/**
	 * Applique une correction de demi-pixel à la coordonnée spécifiée.
	 *
	 * @param num La coordonnée à corriger.
	 * @return La coordonnée corrigée.
	 */
	private double snap(double num) {
		return num + 0.5;
	}

<<<<<<< HEAD
=======
	/**
	 * Obtient la liste des points joués.
	 *
	 * @return La liste des points joués.
	 */
	public List<Point> getPlayedPoints() {
		return playedPoints;
	}

	/**
	 * Obtient la liste des lignes jouées.
	 *
	 * @return La liste des lignes jouées.
	 */
	public List<Line> getPlayedLines() {
		return playedLines;
	}

	/**
	 * Dessine une ligne sur le canvas graphique.
	 *
	 * @param line La ligne à dessiner.
	 * @param g    Le contexte graphique.
	 */
>>>>>>> refs/remotes/Morpion/dev/jaj
	private void drawLine(Line line, GraphicsContext g) {
		g.setLineWidth(2);
		Point p1 = line.points().get(0);
		Point p2 = line.points().get(line.points().size() - 1);
		g.strokeLine(x + CELL_HEIGHT * p1.x, y + CELL_HEIGHT * p1.y, x + CELL_WIDTH * p2.x, y + CELL_HEIGHT * p2.y);
	}

	/**
	 * Dessine un point numéroté sur le canvas graphique.
	 *
	 * @param p   Le point à dessiner.
	 * @param num Le numéro du point.
	 * @param g   Le contexte graphique.
	 */
	private void drawNumberedPoint(Point p, int num, GraphicsContext g) {
		double radius = 10;
		double centerX = x + CELL_HEIGHT * p.x - radius;
		double centerY = y + CELL_HEIGHT * p.y - radius;
		double numX = centerX + radius * .65 * 1.0 / (num + "").length();
		double numY = centerY + radius * 1.3;
		g.setFill(theme.point());
		g.fillOval(centerX, centerY, radius * 2, radius * 2);
		g.setLineWidth(1);
		g.setStroke(theme.number());
		g.strokeText(num + "", numX, numY);
	}
}