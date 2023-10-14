package dev;

import java.util.Scanner;

public class MorpionSolitaireController {

	private MorpionSolitaireModel model;
	private MorpionSolitaireView view;

	public MorpionSolitaireController(MorpionSolitaireModel model, MorpionSolitaireView view) {
		this.model = model;
		this.view = view;
	}

	public void startGame() {
		view.displayGrid();
		while (!model.isGameOver()) {
			int[] coordinates = getCoordinatesFromUser();
			model.makeMove(coordinates[0], coordinates[1]);
			view.displayGrid();
		}
		view.displayGameOverMessage();
	}

	private int[] getCoordinatesFromUser() {
		try (Scanner scanner = new Scanner(System.in)) {
			int x, y;
			while (true) {
				System.out.print("Enter the x-coordinate: ");
				x = scanner.nextInt();
				System.out.print("Enter the y-coordinate: ");
				y = scanner.nextInt();
				if (model.getGrid().isValidMove(x, y)) {
					return new int[] { x, y };
				} else {
					System.out.println("Invalid move. Try again.");
				}
			}
		}
	}
}
