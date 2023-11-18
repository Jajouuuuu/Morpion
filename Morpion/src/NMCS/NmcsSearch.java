package NMCS;

import model.Line;

public class NmcsSearch { 
	
	public static NmcsState searchBestState(NmcsState state, final int level) {
// Maybe change name of state to branch, because it a branch of exploration in the tree of possible moves

		// terminating case, we do random moves until the end to get the score
		if (level <= 0) {
			state.simulationToTheEnd();
			return state;
		}
		
		NmcsState BestState = new NmcsState(state.getGrid());
		
		for (Line move : state.getPossibleLines()) {
			
			final NmcsState currentState = state.nextState(move);
			// recursion
			NmcsState simulationState = searchBestState(currentState, level - 1);

			if (simulationState.getScore() >= BestState.getScore()) {
				BestState = simulationState;
				BestState.setAddedLine(move);
			}
		}
		
		return BestState;
	}
	
}
