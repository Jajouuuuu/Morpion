package dev;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Score {
    private String nomJoueur;
    private int score;

    public Score(String nomJoueur, int score) {
        this.nomJoueur = nomJoueur;
        this.score = score;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public int getScore() {
        return score;
    }

    public static List<Score> trierScores(List<Score> scores) {
        scores.sort((score1, score2) -> Integer.compare(score2.getScore(), score1.getScore()));
        return scores;
    }

    public static void afficherMeilleursScores(List<Score> scores, int topN) {
        scores = trierScores(scores);
        System.out.println("Meilleurs scores :");
        int limit = Math.min(topN, scores.size());
        for (int i = 0; i < limit; i++) {
            Score score = scores.get(i);
            System.out.println(score.getNomJoueur() + " - Score : " + score.getScore());
        }
    }
    
    public static void sauvegarderScoresEnJSON(String nomFichier, List<Score> scores) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(scores, new TypeToken<List<Score>>() {}.getType());
            FileWriter writer = new FileWriter(nomFichier);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Score> chargerScoresDepuisJSON(String nomFichier, java.lang.reflect.Type type) {
        try (FileReader reader = new FileReader(nomFichier)) {
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

