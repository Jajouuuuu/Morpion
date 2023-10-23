package dev;

import java.util.ArrayList;
import java.util.List;

public class Line {

	private Point startPoint;
	private LineDirection direction;
	private List<Point> points;

	public Line(Point startPoint, LineDirection direction) {
		this.startPoint = startPoint;
		this.direction = direction;
		this.points = new ArrayList<>();
		points.add(startPoint);
		switch (direction) {
		case VERTICAL:
			for (int y = startPoint.getY() + 1; y < startPoint.getY() + 5; y++) {		/* Pourquoi +1 ? */
				points.add(new Point(startPoint.getX(), y));		
				/* Ca fait quoi en fait ? Ou cherche à faire quoi ? 
				 * Il faut pas une condition sur est occupé avant de l'ajouter à points ?
				 * Ou sinon utuliser ce que j'ai écrit dans isMoveXXXValid pour savir quelle direction a été donnéee ? */
			}
			break;
		case HORIZONTAL:
			for (int x = startPoint.getX() + 1; x < startPoint.getX() + 5; x++) {
				points.add(new Point(x, startPoint.getY()));
			}
			break;
		case DIAGONAL_UP_RIGHT:
			for (int x = startPoint.getX() + 1, y = startPoint.getY() + 1; x < startPoint.getX() + 5 && y < startPoint.getY() + 5; x++, y++) {
				points.add(new Point(x, y));
			}
			break;
		case DIAGONAL_DOWN_RIGHT:
			for (int x = startPoint.getX() + 1, y = startPoint.getY() - 1; x < startPoint.getX() + 5 && y >= startPoint.getY() - 4; x++, y--) {
				points.add(new Point(x, y));
			}
			break;
		}
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public LineDirection getDirection() {
		return direction;
	}

	public List<Point> getPoints() {
		return points;
	}

	public boolean isComplete() {
		return points.size() == 5;		
	}
}