package ma.patisserie.model;
import java.util.Objects;

public class Client {

    // ----- Attributs (privés = encapsulation) -----
    private int id;
    private String nom;
    private String telephone;
    private String email;

    // ----- Constructeurs -----
    public Client() { }   // constructeur vide

    public Client(int id, String nom, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
    }

    // ----- Getters / Setters -----
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }

    public void setNom(String nom) { this.nom = nom; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setEmail(String email) { this.email = email; }

    // ----- equals / hashCode : 2 clients = même id -----
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                          // même référence
        if (obj == null || getClass() != obj.getClass()) return false;
        Client autre = (Client) obj;
        return id == autre.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);   // TOUJOURS cohérent avec equals
    }

    @Override
    public String toString() {
        return "Client{id=" + id + ", nom='" + nom + "', telephone='" + telephone
                + "', email='" + email + "'}";
    }
}
