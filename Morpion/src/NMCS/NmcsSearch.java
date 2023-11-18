package NMCS;

import java.util.List;

import model.Grid;
import model.Line;

public class NmcsSearch { 
	
	public static NmcsState searchBestState(NmcsState state, final int level) {
// Maybe change name of state to branch, because it a branch of exploration in the tree of possible moves

		// terminating case, we do random moves until it terminated to get the score
		if (level <= 0) {
			state.simulationToTheEnd();
			return state;
		}
		NmcsState globalBestState = new NmcsState(state.getGrid());
		final List<Line> movesDone;

		while (!state.isTerminalPosition()) {
			
			NmcsState currentBestState = new NmcsState(state.getGrid(), state.getScore());
			Line currentBestMove = null;
			
			for (Line move : state.getPossibleLines()) {
				
				final NmcsState currentState = state.newState(move);
				// recursion
				NmcsState simulationState = searchBestState(currentState, level - 1);
	
				if (simulationState.getScore() >= currentBestState.getScore()) {
					currentBestMove = move;
					currentBestState = simulationState;
				}
			}
	
			if (currentBestState.getScore() >= globalBestState.getScore()) {
				movesDone.add(currentBestMove);
				globalBestState = currentBestState;
				globalBestState.getAddedLines().addAll(0, movesDone);
			} else {
				currentBestMove = globalBestState.getAddedLines().get(movesDone.size());
				movesDone.add(currentBestMove);
			}
	
			state = state.getGrid().makeMove(currentBestMove);
		}		
		return globalBestResult;
	}
	
}
