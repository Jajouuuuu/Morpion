package dev;

import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {

	/*
	 * Main pour la gestion de la partie coté Paulo
	 * public static void main(String[] args) { MorpionSolitaireModel model = new
	 * MorpionSolitaireModel(); MorpionSolitaireView view = new
	 * MorpionSolitaireView(model); MorpionSolitaireController controller = new
	 * MorpionSolitaireController(model, view); controller.startGame(); }
	 */
	
	public static void main(String[] args) {
	    List<Utilisateur> utilisateurs = Utilisateur.chargerUtilisateursDepuisJSON("utilisateurs.json", new TypeToken<List<Utilisateur>>() {}.getType());
	    List<Score> scores = Score.chargerScoresDepuisJSON("scores.json", new TypeToken<List<Score>>() {}.getType());
	    List<Partie> parties = Partie.chargerPartiesDepuisJSON("parties.json", new TypeToken<List<Partie>>() {}.getType());

	    Scanner scanner = new Scanner(System.in);

	    while (true) {
	        afficherMenu();
	        int choix = scanner.nextInt();

	        switch (choix) {
	            case 1:
	                Utilisateur utilisateur = Utilisateur.creerUtilisateur(scanner, utilisateurs);
	                utilisateurs.add(utilisateur);
	                Gson gson = new GsonBuilder()
	                    .registerTypeAdapter(Utilisateur.class, new UtilisateurAdapter())
	                    .create();
	                Utilisateur.sauvegarderUtilisateursEnJSON(utilisateurs, "utilisateurs.json");
	                break;
	            case 2:
	                System.out.print("Nom d'utilisateur :");
	                String nomUtilisateur = scanner.next();
	                Utilisateur utilisateurTrouve = Utilisateur.trouverUtilisateurParNom(nomUtilisateur, utilisateurs);
	                if (utilisateurTrouve != null) {
	                    System.out.println("Fonctionne ?????");
	                } else {
	                    System.out.println("Utilisateur non trouvé.");
	                }
	                break;
	            case 3:
	                System.out.print("Nom du joueur : ");
	                String nomJoueur = scanner.next();
	                System.out.print("Mode de jeu (5T ou 5D) : ");
	                String modeJeu = scanner.next();
	                Partie partie = Partie.enregistrerPartie(nomJoueur, modeJeu, parties);
	                parties.add(partie);
	                Partie.sauvegarderPartiesEnJSON("parties.json", parties);
	                break;
	            case 4:
	                Score.afficherMeilleursScores(scores, 10);
	                break;
	            case 5:
	                System.out.println("Bye bye les nullos !");
	                scanner.close();
	                System.exit(0);
	                break;
	            default:
	                System.out.println("Choix invalide. Veuillez réessayer.");
	        }
	    }
	}

    public static void afficherMenu() {
        System.out.println("Menu :");
        System.out.println("1. Créer un utilisateur");
        System.out.println("2. Se connecter");
        System.out.println("3. Enregistrer une partie");
        System.out.println("4. Afficher les meilleurs scores");
        System.out.println("5. Quitter");
        System.out.print("Choix : ");
    }
}
