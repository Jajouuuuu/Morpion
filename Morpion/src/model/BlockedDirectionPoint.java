package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Point avec des directions bloquées.
 * Cette classe étend la classe Point et ajoute la fonctionnalité de bloquer des directions.
 */
public class BlockedDirectionPoint extends Point implements Serializable {
	
    private static final long serialVersionUID = 1L;
	private final Set<Direction> lockedDirections;
	
	/**
     * Vérifie si une direction est bloquée.
     *
     * @param direction Direction à vérifier.
     * @return true si la direction est bloquée, sinon false.
     */
    public boolean isLocked(Direction direction) {
        return lockedDirections.contains(direction);
    }

    /**
     * Bloque une direction.
     *
     * @param direction Direction à bloquer.
     */
    public void lock(Direction direction) {
        lockedDirections.add(direction);
    }

    /**
     * Débloque une direction.
     *
     * @param direction Direction à débloquer.
     */
    public void unlock(Direction direction) {
        lockedDirections.remove(direction);
    }

    /**
     * Constructeur de BlockedDirectionPoint.
     *
     * @param x Coordonnée x du point.
     * @param y Coordonnée y du point.
     */
    public BlockedDirectionPoint(int x, int y) {
        super(x, y);
        lockedDirections = new HashSet<>();
    }

    /**
     * Crée une copie du BlockedDirectionPoint.
     *
     * @return Copie du BlockedDirectionPoint.
     */
    public BlockedDirectionPoint copy() {
        BlockedDirectionPoint copy = new BlockedDirectionPoint(x, y);
        copy.lockedDirections.addAll(lockedDirections);
        return copy;
    }
}
