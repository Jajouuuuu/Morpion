package dev;

public class Point {
	private int x;
	private int y;
	private PointState state;
	private int moveNumber;

	public Point() {
		this.x = 0;
		this.y = 0;
		this.state = PointState.UNOCCUPIED;
		this.moveNumber = 0;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.state = PointState.UNOCCUPIED;
		this.moveNumber = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public PointState getState() {
		return state;
	}

	public void setState(PointState state) {
		this.state = state;
	}

	public int getMoveNumber() {
		return moveNumber;
	}

	public void setMoveNumber(int moveNumber) {
		this.moveNumber = moveNumber;
	}

	public String toString() {
		switch (state) {
		case OCCUPIED:
			return String.valueOf(moveNumber);
		case UNOCCUPIED:
			return ".";
		case LINE:
			return "X";
		default:
			return "";
		}
	}
}
