package modelTests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Point;

public class PointTest {

    @Test
    public void testPointInitialization() {
        Point point = new Point(7, 3);

        assertEquals(7, point.x);
        assertEquals(3, point.y);
    }

    @Test
    public void testPointToString() {
        Point point = new Point(7, 3);

        assertEquals("(7,3)", point.toString());
    }

    @Test
    public void testPointEquality() {
        Point point1 = new Point(7, 3);
        Point point2 = new Point(7, 3);
        Point point3 = new Point(4, 5);

        assertEquals(point1, point2);
        assertNotEquals(point1, point3);
    }

    @Test
    public void testPointHashCode() {
        Point point1 = new Point(7, 3);
        Point point2 = new Point(7, 3);

        assertEquals(point1.hashCode(), point2.hashCode());
    }
}
