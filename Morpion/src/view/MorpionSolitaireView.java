package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.PlayObserver;
import model.Grid;
import model.Line;
import model.Point;

import java.util.List;

public class MorpionSolitaireView implements PlayObserver {

	private Canvas canva;
	private static final int CELL_WIDTH = 25;
	private static final int CELL_HEIGHT = 25;
	public static final int WIDTH = CELL_WIDTH * 25, HEIGHT = CELL_HEIGHT * 25;
	private final int x = CELL_WIDTH;
	private final int y = CELL_HEIGHT;
	private Theme theme;
	
	@SuppressWarnings("exports")
	public MorpionSolitaireView(Canvas canva) {
		this.canva = canva;
		canva.setWidth(WIDTH);
		canva.setHeight(HEIGHT);
	}

	public static final Theme M_THEME = new Theme(
	        Color.web("#03ba00"),
	        Color.web("#d0cdd4"),
	        Color.web("#b5a600"),
	        Color.web("#f6f2fa"),
	        Color.web("#b5a600")  
	);

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public int getOffX() {
		return x;
	}

	public int getOffY() {
		return y;
	}

	public int getCellWidth() {
		return CELL_WIDTH;
	}

	public int getCellHeight() {
		return CELL_HEIGHT;
	}

	public void calcDimensions(Grid grid) {
		int width = grid.width() * CELL_WIDTH;
		canva.setWidth(width);
		int height = grid.height() * CELL_HEIGHT;
		canva.setHeight(height);
	}

	public void update(Grid grid, List<Point> highlightPoints, List<Line> highlightLines) {
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

	private double snap(double num) {
		return num + 0.5;
	}

	private void drawLine(Line line, GraphicsContext g) {
		g.setLineWidth(2);
		Point p1 = line.points().get(0);
		Point p2 = line.points().get(line.points().size() - 1);
		g.strokeLine(x + CELL_HEIGHT * p1.x, y + CELL_HEIGHT * p1.y, x + CELL_WIDTH * p2.x, y + CELL_HEIGHT * p2.y);
	}

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