package dev;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;
import com.google.gson.Gson;

public class Utilisateur {
    private String nomUtilisateur;
    private String motDePasse;
    
    public Utilisateur() {
        // No-args constructor
    }

    public Utilisateur(String nomUtilisateur, String motDePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public static Utilisateur creerUtilisateur(Scanner scanner, List<Utilisateur> utilisateurs) {
        System.out.print("Nom d'utilisateur : \n");
        String nomUtilisateur = scanner.next();
        scanner.nextLine();
        System.out.print("Mot de passe : \n");
        String motDePasse = scanner.nextLine();
        String motDePasseHache = hacherMotDePasse(motDePasse);
        Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasseHache);
        utilisateurs.add(utilisateur);
        System.out.println("Utilisateur créé normalement checker le JSON !");
        return utilisateur;
    }

    private static String hacherMotDePasse(String motDePasse) {
        String motDePasseHache = BCrypt.hashpw(motDePasse, BCrypt.gensalt(12));
        if (motDePasseHache != null) {
            return motDePasseHache;
        } else {
            throw new RuntimeException("Erreur de hachage du mot de passe.");
        }
    }

    public static Utilisateur trouverUtilisateurParNom(String nomUtilisateur, List<Utilisateur> utilisateurs) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getNomUtilisateur().equals(nomUtilisateur)) {
                return utilisateur;
            }
        }
        return null;
    }
    
    public static void sauvegarderUtilisateursEnJSON(List<Utilisateur> utilisateurs, String nomFichier) {
        try (FileWriter writer = new FileWriter(nomFichier)) {
            new Gson().toJson(utilisateurs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Utilisateur> chargerUtilisateursDepuisJSON(String nomFichier, Type type) {
        try (FileReader reader = new FileReader(nomFichier)) {
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
