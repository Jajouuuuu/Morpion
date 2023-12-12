package model;

import java.util.List;

/**
 * L'interface PlayObserver définit les méthodes nécessaires pour observer les changements dans le jeu.
 */
public interface PlayObserver {
	
	/**
     * Méthode appelée pour informer les observateurs des mises à jour dans le jeu.
     *
     * @param grid           La grille mise à jour
     * @param highlightPoints La liste des points à mettre en surbrillance
     * @param highlightLines  La liste des lignes à mettre en surbrillance
     */
    void update(Grid grid, List<Point> highlightPoints, List<Line> highlightLines);
}
