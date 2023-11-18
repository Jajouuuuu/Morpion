package NMCS;

import java.util.List;

import model.Grid;
import model.Line;

public class Pair {
	public Grid grid;
	public List<Line> lines;
	
	public Pair() {
	}
	
	public Pair(Grid grid, List<Line> lines) {
		this.grid = grid;
		this.lines = lines;
	}
	
	public Pair(Grid grid) {
		this.grid = grid;
	}
	
	@Override
	public String toString() {
		return String.format("{ %s; %s}", grid.toString(), lines.toString());
	}

	public Grid getGrid() {
		return this.grid;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public List<Line> getLines() {
		return this.lines;
	}
	
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
}
