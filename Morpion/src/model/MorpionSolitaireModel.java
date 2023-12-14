<<<<<<< HEAD
package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import NMCS.NmcsState;
import NMCS.NmcsSearch;


public class MorpionSolitaireModel {
	
	private static final int GRID_WIDTH = 14;
	private static final int GRID_HEIGHT = 14;
	private Grid grid;

	private boolean choixLigne;
	private List<Line> lignesPossibles;
	private final ArrayList<PlayObserver> playObs;
	private final ArrayList<ScoreObserver> scoreObs;
	private String player;
	private boolean gameOver;
	
	public MorpionSolitaireModel() {
		playObs = new ArrayList<>();
		scoreObs = new ArrayList<>();
		init();
	}

	public boolean isGameOver() {
		System.out.println(gameOver);
		return gameOver;
	}

	private boolean hintVisible;
	public void updateObservers() {
		List<Point> points = new ArrayList<>();
		for (Line line : lignesPossibles) {
			List<Point> otherPoints = new ArrayList<>();
			lignesPossibles.stream().filter(l -> l != line).toList().forEach(l -> otherPoints.addAll(l.points()));
			for (Point point : line.points()) {
				if (!otherPoints.contains(point)) {
					points.add(point);
					break;
				}
			}
		}
		List<Line> hintLines = new ArrayList<>();
        if (hintVisible) {
            hintLines.addAll(grid.possibleLines());
        }
		playObs.forEach(gameObserver -> gameObserver.update(grid, points, hintLines));
		scoreObs.forEach(scoreObserver -> scoreObserver.updateScore(getScore()));
	}

	public void addGameObserver(PlayObserver gameObserver) {
		playObs.add(gameObserver);
	}

	public void addScoreObserver(ScoreObserver scoreObserver) {
		scoreObs.add(scoreObserver);
	}

	public void init() {
		grid = new Grid(GRID_WIDTH, GRID_HEIGHT, Mode.FD);
		grid.init();
		gameOver = false;
		lignesPossibles = new ArrayList<>();
		updateObservers();
	}

	private void gameOver() {
		System.out.println("Partie finie.");
		updateObservers();
		checkAndSaveScore();
		gameOver = true;
	}

	private void checkAndSaveScore() {
		if (player == null) {
			player = "Pas de nom de joueur !";
		}
		try {
			ScoreSauvegarde.saveScore(new Score(player, grid.lines().size()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void handleHumanMove(int x, int y) {
		if (choixLigne) {
			for (Line line : lignesPossibles) {
				for (Point point : line.points()) {
					if (point.x == x && point.y == y) {
						makeMove(line);
						choixLigne = false;
						checkGameOver();
						return;
					}
				}
			}
			System.out.println("Mouvement non valide.");
			return;
		}
		List<Line> possibleLines = grid.possibleLines(x, y);
		if (possibleLines.size() == 0) {
			System.out.println("Mouvement non valide.");
		} else if (possibleLines.size() == 1) {
			makeMove(possibleLines.get(0));
			checkGameOver();
		} else {
			choixLigne = true;
			System.out.println("Il y a plus d'un mouvement possible.");
			this.lignesPossibles = possibleLines;
			updateObservers();
		}
		System.out.println(possibleLines);
	}
	
	public void handleRandomMove() {
		Random rd = new Random(); 
		List<Line> possibleLines = grid.possibleLines();
		if (possibleLines.size() == 0) {
			checkGameOver();
		} else {
			int index = rd.nextInt(possibleLines.size());
			makeMove(possibleLines.get(index));
			System.out.println(possibleLines.get(index));
			checkGameOver();
		}
	}
	
	public void handleRandomGame() {
		while (!gameOver) {
			handleRandomMove();
			System.out.println(grid.getLines().size());
		}
	}
	
	public void handleNmcsMove() {
		int level = 1;
		NmcsState state = new NmcsState(this.grid);
		NmcsState bestState = NmcsSearch.searchBestState(state, level);
		Line line = bestState.getAddedLine();
		makeMove(line);
		System.out.println(line);
		checkGameOver();
	}
	
	public void handleNmcsGame() {
		while (!gameOver) {
			handleNmcsMove();
			System.out.println(grid.getLines().size());
		}
	}
	
	private void checkGameOver() {
		HashSet<Point> pointsSoFar = new HashSet<>();
		List<Line> possibleLines = new ArrayList<>();
		for (Point gridPoint : grid.points()) {
			for (Point point : grid.getNeighbors(gridPoint)) {
				if (pointsSoFar.contains(gridPoint))
					continue;
				pointsSoFar.add(point);
				possibleLines.addAll(grid.possibleLines(point.x, point.y));
				if (possibleLines.size() > 0) {
					gameOver = false;
					return;
				}
			}
		}
		gameOver();
	}

	private void makeMove(Line line) {
		grid.addLine(line);
		lignesPossibles.clear();
		updateObservers();
	}
	
	public void setGameMode(Mode mode) {
		this.grid.setMode(mode);
	}

	public int getScore() {
		return grid.lines().size();
	}

	public void setPlayerName(String playerName) {
		this.player = playerName;
	}

	public String getPlayerName() {
		return player;
	}
	
	public Grid getGrid() {
		return grid;
	}

	public void undoMove() {
		if (grid.lines().size() < 1)
			return;
		grid.undoLine();
		checkGameOver();
		updateObservers();
	}
}
=======
package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import NMCS.NmcsSearch;
import NMCS.NmcsState;
import controler.ConnexionController;

/**
 * Classe représentant le modèle d'un jeu de morpion solitaire.
 * Cette classe gère l'état du jeu, les mouvements des joueurs, les observateurs, etc.
 *
 */
public class MorpionSolitaireModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int GRID_WIDTH = 14;
	private static final int GRID_HEIGHT = 14;
	private Grid grid;
	private boolean choixLigne;
	private List<Line> lignesPossibles;
	private final ArrayList<PlayObserver> playObs;
	private final ArrayList<ScoreObserver> scoreObs;
	private String player;
	private boolean gameOver;
	private List<Point> playedPoints;
	private List<Line> playedLines;
	private User user;
	private ConnexionController connexionController;
	private boolean hintVisible;

	/**
     * Constructeur par défaut de la classe MorpionSolitaireModel.
     * Initialise les observateurs et appelle la méthode d'initialisation.
     */
	public MorpionSolitaireModel() {
		this.playObs = new ArrayList<>();
		this.scoreObs = new ArrayList<>();
		this.playedPoints = new ArrayList<>(); 
        this.playedLines = new ArrayList<>();
		init();
	}

	/**
     * Définit le contrôleur de connexion pour la gestion des connexions.
     *
     * @param connexionController Le contrôleur de connexion
     */
	public void setConnexionController(ConnexionController connexionController) {
        this.connexionController = connexionController;
    }
	
	/**
     * Vérifie si le jeu est terminé.
     *
     * @return true si le jeu est terminé, sinon false
     */
	public boolean isGameOver() {
		System.out.println(gameOver);
		return gameOver;
	}

	/**
	 * Met à jour les observateurs du jeu en fournissant les points, les lignes possibles et les lignes d'indice.
	 */
	public void updateObservers() {
		List<Point> points = new ArrayList<>();
		for (Line line : lignesPossibles) {
			List<Point> otherPoints = new ArrayList<>();
			lignesPossibles.stream().filter(l -> l != line).toList().forEach(l -> otherPoints.addAll(l.points()));
			for (Point point : line.points()) {
				if (!otherPoints.contains(point)) {
					points.add(point);
					break;
				}
			}
		}
		List<Line> hintLines = new ArrayList<>();
		if (hintVisible) {
			hintLines.addAll(grid.possibleLines());
		}
		playObs.forEach(gameObserver -> gameObserver.update(grid, points, hintLines));
		scoreObs.forEach(scoreObserver -> scoreObserver.updateScore(getScore()));
	}

	/**
	 * Ajoute un observateur de jeu à la liste des observateurs de jeu.
	 *
	 * @param gameObserver L'observateur de jeu à ajouter
	 */
	public void addGameObserver(PlayObserver gameObserver) {
		playObs.add(gameObserver);
	}

	/**
	 * Ajoute un observateur de score à la liste des observateurs de score.
	 *
	 * @param scoreObserver L'observateur de score à ajouter
	 */
	public void addScoreObserver(ScoreObserver scoreObserver) {
		scoreObs.add(scoreObserver);
	}

	/**
	 * Initialise le jeu en créant une nouvelle grille, en définissant l'état initial du jeu
	 * et en récupérant le nom du joueur à partir du contrôleur de connexion si disponible.
	 */
	public void init() {
	    grid = new Grid(GRID_WIDTH, GRID_HEIGHT, Mode.FD);
	    grid.init();
	    gameOver = false;
	    lignesPossibles = new ArrayList<>();
	    updateObservers();
	    if (connexionController != null) {
	        player = connexionController.getCurrentUsername();
	        System.out.println();
	    } else {
	        player = "DefaultPlayer";
	    }
	}

	/**
	 * Obtient le mode de jeu actuel de la grille.
	 *
	 * @return Le mode de jeu de la grille
	 */
	public Mode getGameMode() {
		return grid.getMode();
	}

	/**
	 * Gère le cas où le jeu est terminé.
	 * Affiche un message, met à jour les observateurs et sauvegarde le score.
	 */
	private void gameOver() {
		System.out.println("Partie finie.");
		updateObservers();
		checkAndSaveScore();
		gameOver = true;
	}

	/**
	 * Vérifie le score actuel du joueur et le sauvegarde.
	 * Si le nom du joueur est nul, utilise le nom d'utilisateur de l'utilisateur enregistré.
	 *
	 * @throws RuntimeException Si une erreur survient lors de la sauvegarde du score
	 */
	private void checkAndSaveScore() {
		if (player == null) {
			player = user.getUsername();
		}
		try {
			ScoreSauvegarde.saveScore(new Score(player, grid.lines().size()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
     * Gère le mouvement effectué par un joueur humain aux coordonnées spécifiées.
     *
     * @param x La coordonnée x du mouvement
     * @param y La coordonnée y du mouvement
     */
	public void handleHumanMove(int x, int y) {
		if (choixLigne) {
			for (Line line : lignesPossibles) {
				for (Point point : line.points()) {
					if (point.x == x && point.y == y) {
						makeMove(line);
						choixLigne = false;
						checkGameOver();
						return;
					}
				}
			}
			System.out.println("Mouvement non valide.");
			return;
		}		
		List<Line> possibleLines = grid.possibleLines(x, y);
		if (possibleLines.size() == 0) {
			System.out.println("Mouvement non valide.");
		} else if (possibleLines.size() == 1) {
			makeMove(possibleLines.get(0));
			checkGameOver();
		} else {
			choixLigne = true;
			System.out.println("Il y a plus d'un mouvement possible.");
			this.lignesPossibles = possibleLines;
			updateObservers();
		}
		System.out.println(possibleLines);
		playedPoints.add(new Point(x, y));
	}

	/**
	 * Obtient les meilleurs scores, classés par ordre décroissant.
	 *
	 * @param count Le nombre de meilleurs scores à retourner
	 * @return Une liste contenant les meilleurs scores
	 * @throws RuntimeException Si une erreur survient lors du chargement des scores
	 */
	public List<Score> getTopScores(int count) {
	    List<Score> topScores;
	    try {
	        List<Score> allScores = ScoreSauvegarde.loadScores();
	        allScores.sort(Comparator.comparing(Score::getScore).reversed()); 
	        int size = Math.min(count, allScores.size());
	        topScores = allScores.subList(0, size);
	    } catch (IOException e) {
	        throw new RuntimeException("Erreur lors du chargement des scores.", e);
	    }
	    return topScores;
	}
	
	/**
	 * Gère un mouvement aléatoire dans le jeu.
	 */
	public void handleRandomMove() {
		Random rd = new Random(); 
		List<Line> possibleLines = grid.possibleLines();
		if (possibleLines.size() == 0) {
			checkGameOver();
		} else {
			int index = rd.nextInt(possibleLines.size());
			makeMove(possibleLines.get(index));
			System.out.println(possibleLines.get(index));
			checkGameOver();
		}
	}

	/**
	 * Gère un jeu aléatoire jusqu'à la fin.
	 */
	public void handleRandomGame() {
		while (!gameOver) {
			handleRandomMove();
			System.out.println(grid.getLines().size());
		}
	}

	/**
	 * Obtient la liste des points joués.
	 *
	 * @return La liste des points joués
	 */
	public List<Point> getPlayedPoints() {
		return playedPoints;
	}

	/**
	 * Définit la liste des points joués.
	 *
	 * @param playedPoints La nouvelle liste des points joués
	 */
	public void setPlayedPoints(List<Point> playedPoints) {
		this.playedPoints = playedPoints;
	}

	/**
	 * Obtient la liste des lignes jouées.
	 *
	 * @return La liste des lignes jouées
	 */
	public List<Line> getPlayedLines() {
		return playedLines;
	}

	/**
	 * Définit la liste des lignes jouées.
	 *
	 * @param playedLines La nouvelle liste des lignes jouées
	 */
	public void setPlayedLines(List<Line> playedLines) {
		this.playedLines = playedLines;
	}

	/**
	 * Gère un mouvement effectué par l'algorithme NMCS (Nested Monte Carlo Search).
	 */
	public void handleNmcsMove() {
		int level = 1;
		NmcsState state = new NmcsState(this.grid);
		NmcsState bestState = NmcsSearch.searchBestState(state, level);
		Line line = bestState.getAddedLine();
		makeMove(line);
		System.out.println(line);
		checkGameOver();
	}

	/**
	 * Gère un jeu complet en utilisant l'algorithme NMCS (Nested Monte Carlo Search).
	 */
	public void handleNmcsGame() {
		while (!gameOver) {
			handleNmcsMove();
			System.out.println(grid.getLines().size());
		}
	}

	/**
	 * Vérifie si le jeu est terminé en parcourant la grille et en cherchant les lignes possibles.
	 * Met à jour l'état du jeu en conséquence.
	 */
	private void checkGameOver() {
		HashSet<Point> pointsSoFar = new HashSet<>();
		List<Line> possibleLines = new ArrayList<>();
		for (Point gridPoint : grid.points()) {
			for (Point point : grid.getNeighbors(gridPoint)) {
				if (pointsSoFar.contains(gridPoint))
					continue;
				pointsSoFar.add(point);
				possibleLines.addAll(grid.possibleLines(point.x, point.y));
				if (possibleLines.size() > 0) {
					gameOver = false;
					return;
				}
			}
		}
		gameOver();
	}

	/**
	 * Effectue un mouvement en ajoutant une ligne à la grille.
	 * Nettoie la liste des lignes possibles et met à jour les observateurs.
	 *
	 * @param line La ligne à ajouter
	 */
	private void makeMove(Line line) {
		grid.addLine(line);
		lignesPossibles.clear();
		updateObservers();
		playedLines.add(line);
	}

	/**
	 * Définit le mode de jeu de la grille.
	 *
	 * @param mode Le mode de jeu à définir
	 */
	public void setGameMode(Mode mode) {
		this.grid.setMode(mode);
	}

	/**
     * Obtient le score actuel du jeu.
     *
     * @return Le score du jeu
     */
	public int getScore() {
		return grid.lines().size();
	}

	/**
	 * Définit le nom du joueur.
	 *
	 * @param playerName Le nouveau nom du joueur
	 */
	public void setPlayerName(String playerName) {
		this.player = playerName;
	}

	/**
	 * Obtient le nom actuel du joueur.
	 *
	 * @return Le nom du joueur
	 */
	public String getPlayerName() {
		return player;
	}

	/**
     * Annule le dernier mouvement effectué dans le jeu.
     */
	public void undoMove() {
		if (grid.lines().size() < 1)
			return;
		grid.undoLine();
		checkGameOver();
		updateObservers();
	}

	/**
     * Obtient la grille actuelle du jeu.
     *
     * @return La grille du jeu
     */
	public Grid getGrid() {
		return grid;
	}

	/**
     * Définit la grille du jeu en utilisant une grille chargée.
     *
     * @param loadedGrid La grille chargée
     */
	public void setGrid(Grid loadedGrid) {
		this.grid = loadedGrid;
	}
}
>>>>>>> refs/remotes/Morpion/dev/jaj
