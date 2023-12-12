package model;

/**
 * La classe User représente un utilisateur avec un nom d'utilisateur et un mot de passe.
 */
public class User {

    private String username;
    private String password;
    
    /**
     * Constructeur par défaut.
     */
    public User() {}

    /**
     * Constructeur avec paramètres pour initialiser le nom d'utilisateur et le mot de passe.
     *
     * @param username Le nom d'utilisateur.
     * @param password Le mot de passe.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retourne le nom d'utilisateur.
     *
     * @return Le nom d'utilisateur.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retourne le mot de passe.
     *
     * @return Le mot de passe.
     */
    public String getPassword() {
        return password;
    }
}