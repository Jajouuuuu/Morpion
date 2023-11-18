package NMCS;

import java.util.List;

import model.Grid;
import model.Line;

public class NmcsSearch {
	
	public static NmcsState searchBestMove(NmcsState state, final int level) {

//		terminating case : we do random moves until it terminated to get the score
		if (level <= 0)
			return state.simulation();

		NmcsState   = new NmcsState(state.getGrid());
		final List<Line> lines;

		while (!state.isTerminalPosition()) {
			
			NmcsState currentBestResult = new NmcsState(state.getGrid(), state.getScore());
			Line currentBestMove = null;
			
			for (Line move : state.getPossibleLines()) {
				
				final NmcsState currentState = state.newState(move);
				// recursion
				NmcsState simulationResult = searchBestMove(currentState, level - 1);
	
				if (simulationResult.getScore() >= currentBestResult.getScore()) {
					currentBestMove = move;
					currentBestResult = simulationResult;
				}
			}
	
			if (currentBestResult.getScore() >= globalBestResult.getScore()) {
				lines.add(currentBestMove);
				globalBestResult = currentBestResult;
				globalBestResult.lines.addAll(0, lines);
			} else {
				currentBestAction = globalBestResult.item2.get(visitedNodes.size());
				visitedNodes.add(currentBestAction);
			}
	
			state = state.takeAction(currentBestAction);
		}		
		return globalBestResult;
	}
	
}
