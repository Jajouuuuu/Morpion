package NMCS;

import java.util.List;
import java.util.Random;

import model.Grid;
import model.Line;

/**
 * La classe NmcsState représente un état dans la recherche utilisant Nested Monte Carlo Search (NMCS).
 */
public class NmcsState {

	private Grid grid;
	private int score;
	private Line addedLine;
	private List<Line> possibleLines;
	private boolean isTerminalPosition;

	/**
	 * Constructeur de la classe NmcsState.
	 *
	 * @param grid La grille associée à l'état.
	 */
	public NmcsState(Grid grid) {
		this.grid = grid;
		this.score = grid.lines().size();
		this.possibleLines = grid.possibleLines();
		this.isTerminalPosition = (this.possibleLines.size() == 0);
	}

	/**
	 * Constructeur de la classe NmcsState avec un score initial.
	 *
	 * @param grid  La grille associée à l'état.
	 * @param score Le score initial.
	 */
	public NmcsState(Grid grid, int score) {
		this.grid = grid;
		this.score = score;
		this.possibleLines = grid.possibleLines();
		this.isTerminalPosition = (this.possibleLines.size() == 0);
	}

	 /**
     * Crée un nouvel état en effectuant le mouvement spécifié.
     *
     * @param line Le mouvement à effectuer.
     * @return Le nouvel état après le mouvement.
     */
	public NmcsState nextState(Line line) {
		Grid copyGrid = this.grid.copy();
		copyGrid.addLine(line);
		copyGrid.possibleLines().clear();
		NmcsState newState = new NmcsState(copyGrid);
		return newState;
	}

	 /**
     * Effectue une simulation jusqu'à la fin en choisissant des mouvements aléatoires.
     */
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

	/**
     * Vérifie si l'état est une position terminale.
     */
	public void checkTerminalPosition() {
		this.isTerminalPosition = (this.possibleLines.size() == 0);
	}

	/**
     * Récupère la grille associée à l'état.
     *
     * @return La grille associée à l'état.
     */
	public Grid getGrid() {
		return grid;
	}

	/**
     * Modifie la grille associée à l'état.
     *
     * @param grid La nouvelle grille à associer à l'état.
     */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
     * Récupère le score de l'état.
     *
     * @return Le score de l'état.
     */
	public int getScore() {
		return score;
	}

	/**
     * Modifie le score de l'état.
     *
     * @param score Le nouveau score à associer à l'état.
     */
	public void setScore(int score) {
		this.score = score;
	}

	/**
     * Récupère la ligne ajoutée à l'état.
     *
     * @return La ligne ajoutée à l'état.
     */
	public Line getAddedLine() {
		return addedLine;
	}

	/**
     * Modifie la ligne ajoutée à l'état.
     *
     * @param addedLine La nouvelle ligne à associer à l'état.
     */
	public void setAddedLine(Line addedLine) {
		this.addedLine = addedLine;
	}

	/**
     * Récupère la liste des lignes possibles à partir de l'état.
     *
     * @return La liste des lignes possibles à partir de l'état.
     */
	public List<Line> getPossibleLines() {
		return possibleLines;
	}

	/**
     * Modifie la liste des lignes possibles à partir de l'état.
     *
     * @param possibleLines La nouvelle liste des lignes possibles à associer à l'état.
     */
	public void setPossibleLines(List<Line> possibleLines) {
		this.possibleLines = possibleLines;
	}

	/**
     * Vérifie si l'état est une position terminale.
     *
     * @return True si l'état est une position terminale, sinon False.
     */
	public boolean isTerminalPosition() {
		return isTerminalPosition;
	}

	/**
     * Modifie la propriété indiquant si l'état est une position terminale.
     *
     * @param isTerminalPosition La nouvelle valeur de la propriété.
     */
	public void setTerminalPosition(boolean isTerminalPosition) {
		this.isTerminalPosition = isTerminalPosition;
	}
}