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
		int x = 0;
		int y = 0;
		try (Scanner scanner = new Scanner(System.in)) {
			
			//while (true) {
			System.out.print("Enter the x-coordinate: ");
			if(scanner.hasNextInt()) {
				System.out.println("X");
				x = scanner.nextInt();
				x = x -1;
			}
			System.out.print("Enter the y-coordinate: ");
			if(scanner.hasNextInt()) {
				System.out.println("Y");
				y = scanner.nextInt();
				y = y -1;
			}
			System.out.println(x);
			System.out.println(y);
			if (model.getGrid().isValidMove(x, y)) {
				System.out.println("Move Valid ?");
				return new int[] { x, y };
			} else {
				System.out.println("Invalid move. Try again.");
			}
				//}
		}
		return new int[] { x, y };
	}
}
