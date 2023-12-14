package NMCS;

import model.Line;

/**
 * La classe NmcsSearch effectue une recherche pour trouver le meilleur état lors de l'utilisation de Monte Carlo Tree Search (MCTS) avec Nested Monte Carlo Search (NMCS).
 */
public class NmcsSearch { 

	/**
	 * Effectue une recherche pour trouver le meilleur état en utilisant NMCS.
	 *
	 * @param state L'état actuel de la recherche.
	 * @param level Le niveau de profondeur de la recherche.
	 * @return Le meilleur état trouvé.
	 */
	public static NmcsState searchBestState(NmcsState state, final int level) {
		if (level <= 0) {
			state.simulationToTheEnd();
			return state;
		}
		NmcsState BestState = new NmcsState(state.getGrid());
		for (Line move : state.getPossibleLines()) {
			final NmcsState currentState = state.nextState(move);
			NmcsState simulationState = searchBestState(currentState, level - 1);
			if (simulationState.getScore() >= BestState.getScore()) {
				BestState = simulationState;
				BestState.setAddedLine(move);
			}
		}
		return BestState;
	}
}