package NMCS;

import model.Line;

public class NmcsSearch { 
	
	public static NmcsState searchBestState(NmcsState state, final int level) {
// Maybe change name of state to branch, because it a branch of exploration in the tree of possible moves

		// terminating case, we do random moves until it terminated to get the score
		if (level <= 0) {
			state.simulationToTheEnd();
			return state;
		}

		NmcsState currentBestState = new NmcsState(state.getGrid(), state.getScore());
		
		for (Line move : state.getPossibleLines()) {
			
			final NmcsState currentState = state.nextState(move);
			// recursion
			NmcsState simulationState = searchBestState(currentState, level - 1);

			if (simulationState.getScore() >= currentBestState.getScore()) {
				currentBestState = simulationState;
				currentBestState.setAddedLine(move);
			}
		}
		
		return currentBestState;
	}
	
}
