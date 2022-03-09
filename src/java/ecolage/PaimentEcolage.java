package ecolage;

import fonction.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

public class PaimentEcolage {
    String nom;
    Date dateNaissance;
    Boolean sexe;
    String adresse;
    String classeNom;
    float classeEcolage;
    float sommePaye;
    Date duMois;
    Date payeLe;

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
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

    public String getClasseNom() {
        return classeNom;
    }
    public void setClasseNom(String classeNom) {
        this.classeNom = classeNom;
    }

    public float getClasseEcolage() {
        return classeEcolage;
    }
    public void setClasseEcolage(float classeEcolage) {
        this.classeEcolage = classeEcolage;
    }

    public float getSommePaye() {
        return sommePaye;
    }
    public void setSommePaye(float sommePaye) {
        this.sommePaye = sommePaye;
    }

    public Date getDuMois() {
        return duMois;
    }
    public void setDuMois(Date duMois) {
        this.duMois = duMois;
    }

    public Date getPayeLe() {
        return payeLe;
    }
    public void setPayeLe(Date payeLe) {
        this.payeLe = payeLe;
    }

    public void createPaiment(int ideleve, float somme, Date dumois, Date payele) throws SQLException, Exception {
        int insert = 0;
        String sql ;
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = new Connexion().connectpsql();
            
            sql = "insert into paimentEcolage (IdEleve,somme,duMois,paye_le) values (" + ideleve + "," + somme + ",'" + dumois + "','" + payele + "')";
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
    
    public PaimentEcolage[] getPaimentEcolage() throws Exception {
        PaimentEcolage[] paiment = null;
        Vector<PaimentEcolage> vec = new Vector();
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "select * from PaimentEcolages";
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()) {
                PaimentEcolage ecolage = new PaimentEcolage();
                ecolage.setNom(res.getString(1));
                ecolage.setDateNaissance(res.getDate(2));
                ecolage.setSexe(res.getBoolean(3));
                ecolage.setAdresse(res.getString(4));
                ecolage.setClasseNom(res.getString(5));
                ecolage.setClasseEcolage(res.getFloat(6));
                ecolage.setSommePaye(res.getFloat(7));
                ecolage.setDuMois(res.getDate(8));
                ecolage.setPayeLe(res.getDate(9));
                vec.add(ecolage);
            }

            paiment = new PaimentEcolage[vec.size()];
            for(int i=0; i<vec.size(); i++) {
                paiment[i] = vec.get(i);
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
        
        return paiment;
    }
    
}