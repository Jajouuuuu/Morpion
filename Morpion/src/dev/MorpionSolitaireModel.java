package dev;

public class MorpionSolitaireModel {
	private Grid grid;
	private int currentMoveNumber;

	public MorpionSolitaireModel() {
		this.grid = new Grid();
		this.currentMoveNumber = 1;
	}

	public Grid getGrid() {
		return grid;
	}

	public int getCurrentMoveNumber() {
		return currentMoveNumber;
	}

	public void makeMove(int x, int y) {
		if (!grid.isValidMove(x, y)) {
			throw new IllegalStateException("Invalid move!");
		}
		grid.placePoint(x, y, currentMoveNumber);
		currentMoveNumber++;
	}

	public boolean isGameOver() {
		return !grid.hasPossibleMoves();
	}
}
