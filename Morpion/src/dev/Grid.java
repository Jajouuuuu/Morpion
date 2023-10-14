package dev;

public class Grid {
	private int width;
	private int height;
	private Point[][] points;

	public Grid() {
		this.width = 14;
		this.height = 14;
		this.points = new Point[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				points[x][y] = new Point(x, y);
			}
		}
		for (int x = 0; x < width; x++) {
			points[x][0].setState(PointState.UNOCCUPIED);
		}
		for (int x = 0; x < width; x++) {
			points[x][1].setState(PointState.UNOCCUPIED);
		}
		for (int x = 0; x < width; x++) {
			if (x < 5 || x >= 9) {
				points[x][2].setState(PointState.UNOCCUPIED);
			} else {
				points[x][2].setState(PointState.OCCUPIED);
			}
		}
		for (int x = 0; x < width; x++) {
			if (x < 5 || (x != 5 && x != 8) || x >= 13) {
				points[x][3].setState(PointState.UNOCCUPIED);
			} else {
				points[x][3].setState(PointState.OCCUPIED);
			}
		}
		for (int x = 0; x < width; x++) {
			if (x < 5 || (x != 5 && x != 8) || x >= 13) {
				points[x][4].setState(PointState.UNOCCUPIED);
			} else {
				points[x][4].setState(PointState.OCCUPIED);
			}
		}
		for (int x = 0; x < width; x++) {
			if ((x < 2 || (x >= 6 && x < 8) || x >= 12)) {
				points[x][5].setState(PointState.UNOCCUPIED);
			} else {
				points[x][5].setState(PointState.OCCUPIED);
			}
		}
		for (int x = 0; x < width; x++) {
			if ((x < 2 || x >= 3) && (x < 11 || x >= 12)) {
				points[x][6].setState(PointState.UNOCCUPIED);
			} else {
				points[x][6].setState(PointState.OCCUPIED);
			}
		}
		for (int x = 0; x < width; x++) {
			if ((x < 2 || x >= 3) && (x < 11 || x >= 12)) {
				points[x][7].setState(PointState.UNOCCUPIED);
			} else {
				points[x][7].setState(PointState.OCCUPIED);
			}
		}
		for (int x = 0; x < width; x++) {
			if ((x < 2 || (x >= 6 && x < 8) || x >= 12)) {
				points[x][8].setState(PointState.UNOCCUPIED);
			} else {
				points[x][8].setState(PointState.OCCUPIED);
			}
		}
		for (int x = 0; x < width; x++) {
			if (x < 5 || (x != 5 && x != 8) || x >= 13) {
				points[x][9].setState(PointState.UNOCCUPIED);
			} else {
				points[x][9].setState(PointState.OCCUPIED);
			}
		}
		for (int x = 0; x < width; x++) {
			if (x < 5 || (x != 5 && x != 8) || x >= 13) {
				points[x][10].setState(PointState.UNOCCUPIED);
			} else {
				points[x][10].setState(PointState.OCCUPIED);
			}
		}
		for (int x = 0; x < width; x++) {
			if (x < 5 || (x >= 9)) {
				points[x][11].setState(PointState.UNOCCUPIED);
			} else {
				points[x][11].setState(PointState.OCCUPIED);
			}
		}
		for (int x = 0; x < width; x++) {
			points[x][12].setState(PointState.UNOCCUPIED);
		}
		for (int x = 0; x < width; x++) {
			points[x][13].setState(PointState.UNOCCUPIED);
		}
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Point getPoint(int x, int y) {
		return points[x][y];
	}

	public boolean isValidMove(int x, int y) {
		Point point = points[x][y];
		if (point.getState() != PointState.UNOCCUPIED) {
			return false;
		}
		int adjacentOccupiedCount = 0;
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				int nx = x + dx;
				int ny = y + dy;
				if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
					if (points[nx][ny].getState() == PointState.OCCUPIED) {
						adjacentOccupiedCount++;
					}
				}
			}
		}
		return adjacentOccupiedCount >= 4;
	}

	public void placePoint(int x, int y, int moveNumber) {
		Point point = points[x][y];
		point.setState(PointState.OCCUPIED);
		point.setMoveNumber(moveNumber);
		for (LineDirection direction : LineDirection.values()) {
			Line line = new Line(point, direction);
			if (line.isComplete()) {
				drawLine(line);
			}
		}
	}

	private void drawLine(Line line) {
		for (Point point : line.getPoints()) {
			point.setState(PointState.LINE);
		}
	}

	public boolean hasPossibleMoves() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Point point = points[x][y];
				if (point.getState() == PointState.UNOCCUPIED && isValidMove(x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	public int getScore() {
		int score = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Point point = points[x][y];
				if (point.getState() == PointState.LINE) {
					score++;
				}
			}
		}
		return score;
	}
}
