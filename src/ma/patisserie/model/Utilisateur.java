package ma.patisserie.model;

import java.util.Objects;

public class Utilisateur {

    private int id;
    private String nom;
    private String login;
    private String motDePasse;
    private String role;      // ex: "VENDEUR", "ADMIN"

    public Utilisateur() { }

    public Utilisateur(int id, String nom, String login, String motDePasse, String role) {
        this.id = id;
        this.nom = nom;
        this.login = login;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Vérifie login + mot de passe
    public boolean authentifier(String login, String motDePasse) {
        return this.login.equals(login) && this.motDePasse.equals(motDePasse);
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getLogin() { return login; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Utilisateur autre = (Utilisateur) obj;
        return id == autre.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // On n'affiche JAMAIS le mot de passe dans toString (sécurité)
    @Override
    public String toString() {
        return "Utilisateur{id=" + id + ", nom='" + nom + "', login='" + login
                + "', role='" + role + "'}";
    }
}
