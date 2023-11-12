package model;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ScoreSaveLoad {
	
    private static final String scorePath = "resultats.csv";

    public static List<Score> loadScores() throws IOException {
        File file = new File(scorePath);
        List<Score> scoresList;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            scoresList = new ArrayList<>();
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                int x = line.indexOf(',');
                String name = line.substring(0, x);
                double score = Double.parseDouble(line.substring(x + 1));
                Score scoreEntry = new Score(name, score);
                scoresList.add(scoreEntry);
            }
        }
        return scoresList;
    }

    public static void saveScore(Score scoreEntry) throws IOException {
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(scorePath, true))) {
            out.write(scoreEntry.getUsername() + "," + scoreEntry.getScore() + "\n");
        }
    }

    public static List<Double> loadScores(String playerName) throws IOException {
        List<Score> scoreEntries = loadScores();
        List<Double> scores = new LinkedList<>();
        for(Score scoreEntry : scoreEntries) {
            System.out.println(playerName + scoreEntry.getUsername());
            if(scoreEntry.getUsername().equalsIgnoreCase(playerName)) {
                scores.add(scoreEntry.getScore());
            }
        }
        return scores;
    }
}
