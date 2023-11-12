package model;

import java.io.Serializable;
import java.util.*;


public class Line implements Serializable {
	
    private static final long serialVersionUID = 1L;
	private final List<Point> points;
    private Point pointNew;
    private int n;
    private Direction direction;
    private int hash;

    public Line() {
        points = new ArrayList<>();
    }

    public Line(Collection<Point> points, Direction direction) {
        this.points = new ArrayList<>();
        this.points.addAll(points);
        this.direction = direction;
    }

    public Line(Collection<Point> points, Point newPoint, Direction direction, int number) {
        this.points = new ArrayList<>();
        this.points.addAll(points);
        this.pointNew = newPoint;
        this.direction = direction;
        this.n = number;
    }

    public Line(Collection<Point> points) {
        this.points = new ArrayList<>();
        this.points.addAll(points);
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public String toString() {
        return pointNew + "-" + direction + ": " + points.toString();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Point> points() {
        return Collections.unmodifiableList(points);
    }

    public void setNewPoint(Point newPoint) {
        this.pointNew = newPoint;
    }

    public Point getNewPoint() {
        return pointNew;
    }

    public int getNumber() {
        return n;
    }

    public void setNumber(int number) {
        this.n = number;
    }

    public Line copy() {
        return new Line(points, pointNew, direction, n);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return n == line.n && points.equals(line.points) && pointNew.equals(line.pointNew) && direction == line.direction;
    }

    @Override
    public int hashCode() {
        System.out.println("hashing");
        return Objects.hash(points.get(0), direction);
    }

	public int getHash() {
		return hash;
	}

	public void setHash(int hash) {
		this.hash = hash;
	}
}
