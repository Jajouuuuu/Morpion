package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BlockedDirectionPoint extends Point implements Serializable {
	
    private static final long serialVersionUID = 1L;
	private final Set<Direction> lockedDirections;
	
    public boolean isLocked(Direction direction) {
        return lockedDirections.contains(direction);
    }

    public void lock(Direction direction) {
        lockedDirections.add(direction);
    }

    public void unlock(Direction direction) {
        lockedDirections.remove(direction);
    }

    public BlockedDirectionPoint(int x, int y) {
        super(x, y);
        lockedDirections = new HashSet<>();
    }

    public BlockedDirectionPoint copy() {
        BlockedDirectionPoint copy = new BlockedDirectionPoint(x, y);
        copy.lockedDirections.addAll(lockedDirections);
        return copy;
    }
}
