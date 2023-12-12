package model;

/**
 * L'interface ScoreObserver définit une méthode pour mettre à jour le score.
 */
public interface ScoreObserver {
	
	/**
     * Met à jour le score.
     *
     * @param score Le nouveau score à observer.
     */
    void updateScore(int score);

}
