package dev;

public class MorpionSolitaireView {
	private MorpionSolitaireModel model;

	public MorpionSolitaireView(MorpionSolitaireModel model) {
		this.model = model;
	}

	public void displayGrid() {
		Grid grid = model.getGrid();

		for (int y = 0; y < grid.getHeight(); y++) {
			for (int x = 0; x < grid.getWidth(); x++) {
				Point point = grid.getPoint(x, y);
				switch (point.getState()) {
				case OCCUPIED:
					System.out.print("x");
					break;
				case UNOCCUPIED:
					System.out.print(".");
					break;
				case LINE:
					System.out.print(grid.getPoint(x, y).getMoveNumber());
					break;

				}
			}
			System.out.println();
		}
	}

	public void displayScore() {
		int score = model.getGrid().getScore();
		System.out.println("Score: " + score);
	}

	public void displayGameOverMessage() {
		System.out.println("Game over!");
	}
}
