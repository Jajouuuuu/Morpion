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

	public void undoMove() {
		if (grid.lines().size() < 1)
			return;
		grid.undoLine();
		checkGameOver();
		updateObservers();
	}
}
