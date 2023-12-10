package modelTests;
import static org.junit.Assert.*;
import org.junit.Test;
import model.Direction;
import model.Line;
import model.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LineTest {

    @Test
    public void testLineInitialization() {
        Line line = new Line();

        assertNotNull(line.points());
        assertTrue(line.points().isEmpty());
        assertNull(line.getNewPoint());
        assertEquals(0, line.getNumber());
        assertNull(line.getDirection());
    }

    @Test
    public void testLineWithPointsAndDirection() {
        List<Point> points = Arrays.asList(new Point(1, 1), new Point(2, 2), new Point(3, 3));
        Direction direction = Direction.HORIZONTAL;

        Line line = new Line(points, direction);

        assertEquals(points, line.points());
        assertNull(line.getNewPoint());
        assertEquals(0, line.getNumber());
        assertEquals(direction, line.getDirection());
    }

    @Test
    public void testLineWithPointsNewPointDirectionAndNumber() {
        List<Point> points = Arrays.asList(new Point(1, 1), new Point(2, 2), new Point(3, 3));
        Point newPoint = new Point(4, 4);
        Direction direction = Direction.VERTICAL;
        int number = 42;

        Line line = new Line(points, newPoint, direction, number);

        assertEquals(points, line.points());
        assertEquals(newPoint, line.getNewPoint());
        assertEquals(number, line.getNumber());
        assertEquals(direction, line.getDirection());
    }

    @Test
    public void testAddPoint() {
        Line line = new Line();
        Point point = new Point(1, 1);

        line.addPoint(point);

        assertEquals(Collections.singletonList(point), line.points());
    }

    @Test
    public void testToString() {
        List<Point> points = Arrays.asList(new Point(1, 1), new Point(2, 2), new Point(3, 3));
        Point newPoint = new Point(4, 4);
        Direction direction = Direction.HORIZONTAL;
        int number = 42;

        Line line = new Line(points, newPoint, direction, number);

        assertEquals("(4,4)-HORIZONTAL:[(1,1),(2,2),(3,3)]", line.toString());
    }

    @Test
    public void testSetDirection() {
        Line line = new Line();
        Direction direction = Direction.VERTICAL;

        line.setDirection(direction);

        assertEquals(direction, line.getDirection());
    }

    @Test
    public void testCopy() {
        List<Point> points = Arrays.asList(new Point(1, 1), new Point(2, 2), new Point(3, 3));
        Point newPoint = new Point(4, 4);
        Direction direction = Direction.VERTICAL;
        int number = 42;

        Line originalLine = new Line(points, newPoint, direction, number);
        Line copiedLine = originalLine.copy();

        assertEquals(originalLine, copiedLine);
        assertNotSame(originalLine, copiedLine);
    }

    @Test
    public void testEqualsAndHashCode() {
        List<Point> points1 = Arrays.asList(new Point(1, 1), new Point(2, 2), new Point(3, 3));
        Point newPoint1 = new Point(4, 4);
        Direction direction1 = Direction.HORIZONTAL;
        int number1 = 42;

        Line line1 = new Line(points1, newPoint1, direction1, number1);
        Line line2 = new Line(points1, newPoint1, direction1, number1);

        assertEquals(line1, line2);
        assertEquals(line1.hashCode(), line2.hashCode());

        List<Point> points2 = Arrays.asList(new Point(5, 5), new Point(6, 6), new Point(7, 7));
        Point newPoint2 = new Point(8, 8);
        Direction direction2 = Direction.VERTICAL;
        int number2 = 24;

        Line line3 = new Line(points2, newPoint2, direction2, number2);

        assertNotEquals(line1, line3);
        assertNotEquals(line1.hashCode(), line3.hashCode());
    }
}


