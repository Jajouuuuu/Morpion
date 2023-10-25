package dev;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MorpionSolitaireModel {
    private static final int GRID_WIDTH = 14;
    private static final int GRID_HEIGHT = 14;
    private AbstractGrid grid;
    private boolean selectingLine;
    private List<Line> possibleLines;
    private boolean gameOver;

    public MorpionSolitaireModel() {
        init();
    }

    public void init() {
        grid = new Grid5T(GRID_WIDTH, GRID_HEIGHT);
        grid.init();
        gameOver = false;
        possibleLines = new ArrayList<>();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void handleHumanMove(int x, int y) {
        if (selectingLine) {
            for (Line line : possibleLines) {
                for (Point point : line.points()) {
                    if (point.x == x && point.y == y) {
                        makeMove(line);
                        selectingLine = false;
                        checkGameOver();
                        return;
                    }
                }
            }
            System.out.println("Mouvement impossibles");
            return;
        }

        List<Line> possibleLines = grid.possibleLines(x, y);
        if (possibleLines.size() == 0) {
            System.out.println("Invalid Move");
        } else if (possibleLines.size() == 1) {
            makeMove(possibleLines.get(0));
            checkGameOver();
        } else {
            selectingLine = true;
            System.out.println("Plusieurs mouvements sont possibles");
            this.possibleLines = possibleLines;
        }
        System.out.println(possibleLines);
    }

    public int getScore() {
        return grid.lines().size();
    }

    public AbstractGrid getGrid() {
        return grid;
    }

    public boolean isSelectingLine() {
        return selectingLine;
    }

    private void checkGameOver() {
        HashSet<Point> pointsSoFar = new HashSet<>();
        List<Line> possibleLines = new ArrayList<>();
        for (Point gridPoint : grid.points()) {
            for (Point point : grid.getNeighbors(gridPoint)) {
                if (pointsSoFar.contains(gridPoint)) continue;
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

    private void gameOver() {
        System.out.println("Fin de partie ! ");
        System.out.println(grid.lines().size());
        gameOver = true;
    }

    private void makeMove(Line line) {
        grid.addLine(line);
        possibleLines.clear();
    }
}
