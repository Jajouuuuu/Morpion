package NMCS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Grid;
import model.Line;

// Faire heriter de MorpioSolitaireModel ? et rajouter juste addadLines comme variable de classe ? 
// Et meme addedLines est deja inclu dans grid car la variable lines est une liste donc on utilise la position.
// Donc juste rajouter une variable aui dit a quelle position est la premiere ligne de la sinmulatioin en nmcs.

public class NmcsState {
	private Grid grid;
	private int score;
	private List<Line> addedLines = new ArrayList<Line>();
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
	
	public void simulationToTheEnd() {
		Random rd = new Random(); 
		while (! this.isTerminalPosition) {
			int index = rd.nextInt(possibleLines.size());
			grid.addLine(possibleLines.get(index));
			possibleLines.clear();
			checkTerminalPosition();
		}
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

	public List<Line> getAddedLines() {
		return addedLines;
	}

	public void setAddedLines(List<Line> addedLines) {
		this.addedLines = addedLines;
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
