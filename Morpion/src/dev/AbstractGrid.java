package dev;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractGrid {
	protected BlockedDirectionPoint[][] grid;
	protected final Set<Point> points;
	protected List<Line> lines;
	protected Mode mode;

	public AbstractGrid(int width, int height, Mode mode) {
		this.grid = new BlockedDirectionPoint[width][height];
		this.lines = new ArrayList<>();
		this.points = new HashSet<>();
		this.mode = mode;
	}
	
	public abstract void initPoint(int x, int y);
	public abstract List<Line> possibleLines(int x, int y, Direction direction);

	public Set<Point> getPoints() {
		return points;
	}
	
	public Set<Point> points() {
		return Collections.unmodifiableSet(points);
	}

	public boolean isValidX(int x) {
		return x >= 0 && x < width();
	}

	public boolean isValidY(int y) {
		return y >= 0 && y < height();
	}

	public int width() {
		return grid.length;
	}

	public int height() {
		return grid[0].length;
	}
	
	public List<Line> lines() {
		return Collections.unmodifiableList(lines);
	}

	public Mode getMode(Mode mode) {
		return mode;
	}

	public Point getPoint(int x, int y) {
		return grid[x][y];
	}

	public void init() {
		int startX = (width() - 10) / 2;
		int startY = (height() - 10) / 2;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i < 3 && (j < 3 || j > 6)) continue;
				if (i > 6 && (j < 3 || j > 6)) continue;
				if ((i > 0 && i < 9) && (j > 3 && j < 6)) continue;
				if ((j > 0 && j < 9) && (i > 3 && i < 6)) continue;
				initPoint(startX + i, startY + j);
			}
		}
	}

	public List<Line> possibleLines() {
		Set<Point> pointsSoFar = new HashSet<>();
		List<Line> possibleLines = new ArrayList<>();
		points.forEach(gridPoint -> getNeighbors(gridPoint).stream()
				.filter(point -> !pointsSoFar.contains(point))
				.forEach(point -> {
					pointsSoFar.add(point);
					possibleLines.addAll(possibleLines(point.x, point.y));
				}));
		return possibleLines;
	}

	public List<Line> possibleLines(int x, int y) {
		if (!isValidX(x) || !isValidY(y) || grid[x][y] != null) {
            System.out.println(grid[x][y]);
			return new ArrayList<>();
		}
		ArrayList<Line> possibleLines = new ArrayList<>();
		for (Direction direction : Direction.values()) {
			possibleLines.addAll(possibleLines(x, y, direction));
		}
		return possibleLines;
	}

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

	public void deletePoint(Point point) {
		grid[point.x][point.y] = null;
		points.remove(point);
	}

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
}
