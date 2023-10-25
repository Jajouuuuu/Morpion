package dev;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private final List<Point> points;
	private Point newPoint;
	private int number;
	private Direction direction;

	public Line() {
		points = new ArrayList<>();
	}

	public Line(List<Point> points, Direction direction) {
		this.points = new ArrayList<>();
		this.points.addAll(points);
		this.direction = direction;
	}

	public Line(List<Point> points, Point newPoint, Direction direction, int number) {
		this.points = new ArrayList<>();
		this.points.addAll(points);
		this.newPoint = newPoint;
		this.direction = direction;
		this.number = number;
	}

	public void addPoint(Point point) {
		points.add(point);
	}

	public String toString() {
		return newPoint + "-" + direction + ": " + points.toString();
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public List<Point> points() {
		return points;
	}

	public void setNewPoint(Point newPoint) {
		this.newPoint = newPoint;
	}

	public Point getNewPoint() {
		return newPoint;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
