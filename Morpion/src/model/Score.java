package model;

/**
 * La classe Score représente le score d'un joueur et est comparable avec d'autres scores.
 */
public class Score implements Comparable<Score> {
	
    private String username;
    private int score;

    /**
     * Constructeur pour créer un objet Score avec un nom d'utilisateur et un score donnés.
     *
     * @param username Le nom d'utilisateur associé au score.
     * @param score    Le score à attribuer à l'utilisateur.
     */
    public Score(String user, int score) {
        this.score = score;
        this.username = user;
    }
	
    /**
     * Retourne le nom d'utilisateur associé à ce score.
     *
     * @return Le nom d'utilisateur associé à ce score.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retourne le score.
     *
     * @return Le score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Compare ce score avec un autre score pour déterminer l'ordre de tri.
     *
     * @param other L'autre score à comparer.
     * @return Une valeur négative, nulle ou positive selon que ce score est inférieur, égal ou supérieur à l'autre.
     */
    @Override
    public int compareTo(Score other) {
        return Integer.compare(this.score, other.score);
    }  
}