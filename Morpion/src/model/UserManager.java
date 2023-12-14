package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * La classe UserManager gère la liste des utilisateurs, y compris l'authentification et l'ajout d'utilisateurs.
 */
public class UserManager {

    private List<User> users;
    private final String filePath = "users.json";

    /**
     * Constructeur de la classe UserManager.
     * Charge les utilisateurs depuis le fichier s'ils existent, sinon initialise une liste vide.
     */
    public UserManager() {
        loadUsers();
    }

    /**
     * Charge les utilisateurs depuis le fichier JSON.
     */
    private void loadUsers() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                users = objectMapper.readValue(file, new TypeReference<List<User>>() {});
            } else {
                users = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sauvegarde la liste des utilisateurs dans le fichier JSON.
     */
    private void saveUsers() {
        try {
            File file = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(file, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Authentifie un utilisateur en vérifiant le nom d'utilisateur et le mot de passe.
     *
     * @param username Le nom d'utilisateur.
     * @param password Le mot de passe.
     * @return True si l'authentification réussit, sinon false.
     */
    public boolean authenticate(String username, String password) {
    	if (this.users == null) {
            this.users = new ArrayList<>();
        }
        return this.users.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    /**
     * Ajoute un nouvel utilisateur avec le nom d'utilisateur et le mot de passe spécifiés.
     *
     * @param username Le nom d'utilisateur du nouvel utilisateur.
     * @param password Le mot de passe du nouvel utilisateur.
     */
    public void addUser(String username, String password) {
        users.add(new User(username, password));
        saveUsers();
    }
}