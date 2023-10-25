package dev;

import java.util.HashSet;
import java.util.Set;

/**
 * Cette classe représente un point avec des directions bloquées.
 */
public class BlockedDirectionPoint extends Point {
    private final Set<Direction> lockedDirections;

    public BlockedDirectionPoint(int x, int y) {
        super(x, y);
        lockedDirections = new HashSet<>();
    }

    /**
     * Vérifie si une direction est bloquée.
     *
     * @param direction La direction à vérifier.
     * @return true si la direction est bloquée, sinon false.
     */
    public boolean isLocked(Direction direction) {
        if (lockedDirections == null) {
            return false;
        }
        return lockedDirections.contains(direction);
    }

    /**
     * Bloque une direction.
     *
     * @param direction La direction à bloquer.
     */
    public void lock(Direction direction) {
        if (lockedDirections != null) {
            lockedDirections.add(direction);
        }
    }

    /**
     * Débloque une direction.
     *
     * @param direction La direction à débloquer.
     */
    public void unlock(Direction direction) {
        if (lockedDirections != null) {
            lockedDirections.remove(direction);
        }
    }
}
