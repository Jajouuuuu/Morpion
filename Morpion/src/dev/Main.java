package dev;

public class Main {

	public static void main(String[] args) {
		MorpionSolitaireModel model = new MorpionSolitaireModel();
		MorpionSolitaireView view = new MorpionSolitaireView(model);
		MorpionSolitaireController controller = new MorpionSolitaireController(model, view);
		controller.startGame();
	}
}