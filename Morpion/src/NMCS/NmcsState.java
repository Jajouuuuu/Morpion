package NMCS;

import java.util.List;

import model.Grid;
import model.Line;

public class NmcsState {
	private Grid grid;
	private int score;
	private List<Line> addedLines;
	private List<Line> possibleLines;
	private boolean isTerminalPosition;

	public NmcsState(Grid grid) {
		this.grid = grid;
		this.score = grid.lines().size();
		this.possibleLines = grid.possibleLines();
		this.isTerminalPosition = (this.possibleLines.size() == 0);
	}
	
	public NmcsState(Grid grid, int score) {
		this.grid = grid;
		this.score = score;
		this.possibleLines = grid.possibleLines();
		this.isTerminalPosition = (this.possibleLines.size() == 0);
	}
	
	public NmcsState newState(Line line) {
		Grid copyGrid = this.grid.copy();
//		code from makeMove function in MorpionSolitaireModel.java
		copyGrid.addLine(line);
		copyGrid.possibleLines().clear();
		NmcsState newState = new NmcsState(copyGrid);
		return newState;
	}
	
	public NmcsState simulationEndGame() {
		
		return 
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Line> getPossibleLines() {
		return possibleLines;
	}

	public void setPossibleLines(List<Line> possibleLines) {
		this.possibleLines = possibleLines;
	}

	public boolean isTerminalPosition() {
		return isTerminalPosition;
	}

	public void setTerminalPosition(boolean isTerminalPosition) {
		this.isTerminalPosition = isTerminalPosition;
	}

}
