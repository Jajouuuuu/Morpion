package dev;

import java.util.ArrayList;
import java.util.List;

public class Grid5D extends AbstractGrid {
	public Grid5D(int width, int height) {
		super(width, height, Mode.FD);
	}

	@Override
	public void initPoint(int x, int y) {
		if (grid[x][y] == null) {
			grid[x][y] = new BlockedDirectionPoint(x, y);
			points.add(grid[x][y]);
		}
	}

	@Override
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
		for (int i = -4; i <= 0; i++) {
			Line line = new Line();
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
}
