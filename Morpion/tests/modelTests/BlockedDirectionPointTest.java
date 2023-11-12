package modelTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.BlockedDirectionPoint;
import model.Direction;


class BlockedDirectionPointTest {

    @Test
    void testIsLocked() {
        BlockedDirectionPoint point = new BlockedDirectionPoint(1, 1);
        assertFalse(point.isLocked(Direction.UP));
        assertFalse(point.isLocked(Direction.DOWN));
        assertFalse(point.isLocked(Direction.HORIZONTAL));
        assertFalse(point.isLocked(Direction.VERTICAL));
    }

    @Test
    void testLockAndUnlock() {
        BlockedDirectionPoint point = new BlockedDirectionPoint(1, 1);
        assertFalse(point.isLocked(Direction.UP));
        point.lock(Direction.UP);
        assertTrue(point.isLocked(Direction.UP));
        assertFalse(point.isLocked(Direction.HORIZONTAL));
        assertFalse(point.isLocked(Direction.VERTICAL));
        point.unlock(Direction.UP);
        assertFalse(point.isLocked(Direction.UP));
    }

    @Test
    void testCopy() {
        BlockedDirectionPoint original = new BlockedDirectionPoint(2, 2);
        original.lock(Direction.UP);
        original.lock(Direction.HORIZONTAL);
        BlockedDirectionPoint copy = original.copy();
        assertTrue(copy.isLocked(Direction.UP));
        assertTrue(copy.isLocked(Direction.HORIZONTAL));
        assertFalse(copy.isLocked(Direction.DOWN));
        assertFalse(copy.isLocked(Direction.VERTICAL));
    }
}
