package personne;

import ecolage.PaimentEcolage;
import fonction.Connexion;
import fonction.Fonction;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Personne {
    int id;
    String nom;
    String prenom;
    Date dateNaissance;
    Boolean sexe;
    String adresse;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public boolean isSexe() {
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
    
    public void create(String nom, String prenom, Date date, boolean sexe, String adresse) throws Exception {
        int insert = 0;
        String sql ;
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = new Connexion().connectpsql();
            
            sql = "insert into personnes (nom,prenom,naissance,sexe,adresse) values ('" + nom + "','" + prenom + "','" + date + "','" + sexe + "','" + adresse + "')";
            stmt = con.createStatement();
            insert = stmt.executeUpdate(sql);
        }
        
        catch(Exception e) {
            throw e;
        }
        
        finally {
            stmt.close();
            con.close();
        }
    }
    
    public Personne[] read() throws Exception {
        Personne[] personne = null;
        Vector<Personne> vec = new Vector();
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "select * from personnes";
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()) {
                this.setNom(res.getString(2));
                this.setPrenom(res.getString(3));
                this.setDateNaissance(res.getDate(4));
                this.setSexe(res.getBoolean(5));
                this.setAdresse(res.getString(6));
                vec.add(this);
            }

            personne = new Personne[vec.size()];
            for(int i=0; i<vec.size(); i++) {
                personne[i] = vec.get(i);
            }
        }
        
        catch(Exception e) {
            throw e;
        }
        
        finally {
            res.close();
            stmt.close();
            con.close();
        }
        
        return personne;
    }
    
    public void update(int id, String nom, String prenom, Date date, Boolean sexe, String adresse) throws Exception {
        int update = 0;
        Connection con = null;
        Statement stmt = null;
        
        try{
            con = new Connexion().connectpsql();
            
            String sql = "update personnes set nom = '" + nom + "' , prenom = '" + prenom + "' , naissance = '" + date + "' , sexe = '" + sexe + "' , adresse = '" + adresse + "' where idpersonne = " + id;
            stmt = con.createStatement();
            update = stmt.executeUpdate(sql);
        }
        
        catch(Exception e) {
            throw e;
        }
        
        finally {
            stmt.close();
            con.close();
        }
    }
    
    public void delete(int id) throws Exception {
        int delete = 0;
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "delete from personnes where idpersonne = " + id;
            stmt = con.createStatement();
            delete = stmt.executeUpdate(sql);
        }
        
        catch(Exception e) {
            throw e;
        }
        
        finally {
            stmt.close();
            con.close();
        }
    }
    
}