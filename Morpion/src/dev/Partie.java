package dev;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Partie {
    private String nomJoueur;
    private String modeJeu;
    private Date datePartie;

    public Partie(String nomJoueur, String modeJeu, Date datePartie) {
        this.nomJoueur = nomJoueur;
        this.modeJeu = modeJeu;
        this.datePartie = datePartie;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public String getModeJeu() {
        return modeJeu;
    }

    public Date getDatePartie() {
        return datePartie;
    }

    public static Partie enregistrerPartie(String nomJoueur, String modeJeu, List<Partie> parties) {
        Date datePartie = new Date();
        Partie partie = new Partie(nomJoueur, modeJeu, datePartie);
        parties.add(partie);
        return partie;
    }
    
    public static void sauvegarderPartiesEnJSON(String nomFichier, List<Partie> parties) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(parties, new TypeToken<List<Partie>>() {}.getType());
            FileWriter writer = new FileWriter(nomFichier);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Partie> chargerPartiesDepuisJSON(String nomFichier, java.lang.reflect.Type type) {
        try (FileReader reader = new FileReader(nomFichier)) {
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

