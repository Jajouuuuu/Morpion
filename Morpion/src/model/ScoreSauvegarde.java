package model;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * La classe ScoreSauvegarde offre des méthodes pour la sauvegarde et le chargement des scores.
 */
public class ScoreSauvegarde {

    private static final String scorePath = "resultats.csv";

    /**
     * Charge les scores à partir du fichier.
     *
     * @return Une liste d'objets Score représentant les scores chargés.
     * @throws IOException En cas d'erreur lors de la lecture du fichier.
     */
    public static List<Score> loadScores() throws IOException {
        File file = new File(scorePath);
        List<Score> scoresList = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            scoresList = new ArrayList<>();
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                int x = line.indexOf(',');
                String name = line.substring(0, x);
                double score = Double.parseDouble(line.substring(x + 1));
                Score scoreEntry = new Score(name, (int) score);
                scoresList.add(scoreEntry);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return scoresList;
    }

    /**
     * Sauvegarde un nouveau score dans le fichier.
     *
     * @param scoreEntry L'objet Score à sauvegarder.
     * @throws IOException En cas d'erreur lors de l'écriture dans le fichier.
     */
    public static void saveScore(Score scoreEntry) throws IOException {
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(scorePath, true))) {
            out.write(scoreEntry.getUsername() + "," + scoreEntry.getScore() + "\n");
        }
    }

    /**
     * Charge les scores d'un joueur spécifique à partir du fichier.
     *
     * @param playerName Le nom du joueur dont on veut charger les scores.
     * @return Une liste des scores du joueur.
     * @throws IOException En cas d'erreur lors de la lecture du fichier.
     */
    public static List<Double> loadScores(String playerName) throws IOException {
        List<Score> scoreEntries = loadScores();
        List<Double> scores = new LinkedList<>();
        for (Score scoreEntry : scoreEntries) {
            System.out.println(playerName + scoreEntry.getUsername());
            if (scoreEntry.getUsername().equalsIgnoreCase(playerName)) {
                scores.add((double) scoreEntry.getScore());
            }
        }
        return scores;
    }
}