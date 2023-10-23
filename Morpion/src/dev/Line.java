package dev;

import java.util.ArrayList;
import java.util.List;

public class Line {

	private Point addedPoint;
	private LineDirection direction;
	private List<Point> points;

	public Line(Point addedPoint, LineDirection direction) {
		this.addedPoint = addedPoint;
		this.direction = direction;
		this.points = new ArrayList<>();
		points.add(addedPoint);
		switch (direction) {
		case VERTICAL:
			for (int y = addedPoint.getY() + 1; y < addedPoint.getY() + 5; y++) {
				points.add(new Point(addedPoint.getX(), y));		
				/* Ca fait quoi en fait ? Ou cherche à faire quoi ? 
				 * Il faut pas une condition sur est occupé avant de l'ajouter à points ?
				 * Ou sinon utuliser ce que j'ai écrit dans isMoveXXXValid pour savir quelle direction a été donnéee ? */
			}
			break;
		case HORIZONTAL:
			for (int x = addedPoint.getX() + 1; x < addedPoint.getX() + 5; x++) {
				points.add(new Point(x, addedPoint.getY()));
			}
			break;
		case DIAGONAL_UP_RIGHT:
			for (int x = addedPoint.getX() + 1, y = addedPoint.getY() + 1; x < addedPoint.getX() + 5 && y < addedPoint.getY() + 5; x++, y++) {
				points.add(new Point(x, y));
			}
			break;
		case DIAGONAL_DOWN_RIGHT:
			for (int x = addedPoint.getX() + 1, y = addedPoint.getY() - 1; x < addedPoint.getX() + 5 && y >= addedPoint.getY() - 4; x++, y--) {
				points.add(new Point(x, y));
			}
			break;
		}
	}

	public Point getAddedPoint() {
		return addedPoint;
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
	
	public void clear() {
	    this.points.clear();
	  }

}