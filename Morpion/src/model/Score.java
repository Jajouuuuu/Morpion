package model;

public class Score {
	
    private String user;
    private double score;
    
    public Score(String user, double score) {
        this.score = score;
        this.user = user;
    }
	
    public String getUsername() {
        return user;
    }

    public double getScore() {
        return score;
    }
}
