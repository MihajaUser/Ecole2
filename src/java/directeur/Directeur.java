package directeur;

import java.sql.Date;

public class Directeur {
    int idDirecteur;
    String nom;
    String prenom;
    Date dateNaissance;
    String statut;
    Boolean sexe;
    String adresse;

    public int getIdDirecteur() {
        return idDirecteur;
    }
    public void setIdDirecteur(int idDirecteur) {
        this.idDirecteur = idDirecteur;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Boolean getSexe() {
        return sexe;
    }
    public void setSexe(Boolean sexe) {
        this.sexe = sexe;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
}
