package NMCS;

import java.util.List;
import java.util.Random;

import model.Grid;
import model.Line;


public class NmcsState {
	private Grid grid;
	private int score;
	private Line addedLine;
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
	
	public NmcsState nextState(Line line) {
		Grid copyGrid = this.grid.copy();
//		code from makeMove function in MorpionSolitaireModel.java
		copyGrid.addLine(line);
		copyGrid.possibleLines().clear();
		NmcsState newState = new NmcsState(copyGrid);
		return newState;
	}
	
	public void simulationToTheEnd() {
		Random rd = new Random(); 
		while (!this.isTerminalPosition) {
			int index = rd.nextInt(possibleLines.size());
			grid.addLine(possibleLines.get(index));
			possibleLines.clear();
			this.possibleLines = grid.possibleLines();
			checkTerminalPosition();
		}
		this.score = grid.lines().size();
	}
	
	public void checkTerminalPosition() {
		this.isTerminalPosition = (this.possibleLines.size() == 0);
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

	public Line getAddedLine() {
		return addedLine;
	}

	public void setAddedLine(Line addedLine) {
		this.addedLine = addedLine;
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
