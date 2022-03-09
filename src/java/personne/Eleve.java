package personne;

import fonction.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Eleve {
    int idEleve;
    String nom;
    String classeNom;
    Date dateNaissance;
    Boolean sexe;
    String adresse;
    String statut;
    Date cree_le;
    

    public int getIdEleve() {
        return idEleve;
    }
    public void setIdEleve(int idEleve) {
        this.idEleve = idEleve;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getClasseNom() {
        return classeNom;
    }
    public void setClasseNom(String classeNom) {
        this.classeNom = classeNom;
    }
    
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
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

    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getCree_le() {
        return cree_le;
    }
    public void setCree_le(Date cree_le) {
        this.cree_le = cree_le;
    }
    
    public int create(int idpersonne,Date cree_le, int idclasse) throws Exception {
        int insert = 0;
        String sql ;
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        System.out.println("");
        try {
            con = new Connexion().connectpsql();
            
            sql = "insert into eleves (IdPersonne,IdClasses,statut,cree_le) values (" + idpersonne + "," + idclasse + ",'Eleve','" + cree_le + "')";
            System.out.println(sql);
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
        
        return idpersonne;
    }
    
    public Eleve[] read() throws Exception {
        Eleve[] eleve = null;
        Vector<Eleve> vec = new Vector();
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "select * from eleves";
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()) {
                Eleve eleves = new Eleve();
                eleves.setNom(res.getString(2));
                eleves.setDateNaissance(res.getDate(3));
                eleves.setSexe(res.getBoolean(4));
                eleves.setAdresse(res.getString(5));                
                eleves.setStatut(res.getString(6));                
                eleves.setCree_le(res.getDate(7));
                vec.add(eleves);
            }

            eleve = new Eleve[vec.size()];
            for(int i=0; i<vec.size(); i++) {
                eleve[i] = vec.get(i);
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
        
        return eleve;
    }
    
    public void update(int ideleve, int idclasse) throws Exception {
        int update = 0;
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "update eleves set idClasses = " + idclasse + " where idEleve = " + ideleve;
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
            
            String sql = "delete from eleves where id=" + id;
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
    
    public Eleve[] recherche(String classeNom, Boolean sexe, String adresse, Date naissance) throws Exception {
        Eleve[] eleve = null;
        Vector<Eleve> vec = new Vector();
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "select * from Eleve where classeNom = '" + classeNom + "' , sexe = '" + sexe + "' , adresse = '" + adresse + "' , naissance = '" + naissance + "'";
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()) {
                this.setNom(res.getString(2));
                this.setDateNaissance(res.getDate(3));
                this.setSexe(res.getBoolean(4));
                this.setAdresse(res.getString(5));                
                this.setStatut(res.getString(6));                
                this.setCree_le(res.getDate(7));
                vec.add(this);
            }
            
            eleve = new Eleve[vec.size()];
            for(int i=0; i<vec.size(); i++) {
                eleve[i] = vec.get(i);
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
        
        return eleve;
    }
    
}
