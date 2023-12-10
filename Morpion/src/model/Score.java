package model;

public class Score implements Comparable<Score> {
    private String username;
    private int score;

    public Score(String user, int score) {
        this.score = score;
        this.username = user;
    }
	
    public String getUsername() {
        return username;
    }

    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(Score other) {
        return Integer.compare(this.score, other.score);
    }
    
}
