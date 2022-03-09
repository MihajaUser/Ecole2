package ecolage;

import fonction.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

public class EcolageNiveau {
    int idClasse;
    String classeNom;
    float ecolage;
    Date anneDedut;
    Date anneFin;

    public int getIdClasse() {
        return idClasse;
    }
    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public String getClasseNom() {
        return classeNom;
    }
    public void setClasseNom(String classeNom) {
        this.classeNom = classeNom;
    }

    public float getEcolage() {
        return ecolage;
    }
    public void setEcolage(float ecolage) {
        this.ecolage = ecolage;
    }

    public Date getAnneDedut() {
        return anneDedut;
    }
    public void setAnneDedut(Date anneDedut) {
        this.anneDedut = anneDedut;
    }

    public Date getAnneFin() {
        return anneFin;
    }
    public void setAnneFin(Date anneFin) {
        this.anneFin = anneFin;
    }
    
    public EcolageNiveau[] getList() throws Exception {
        EcolageNiveau[] ecolage = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        Vector<EcolageNiveau> vec = new Vector();
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "select * from EcolageNiveau";
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()) {
                EcolageNiveau niveau = new EcolageNiveau();
                niveau.setIdClasse(res.getInt(1));
                niveau.setClasseNom(res.getString(2));
                niveau.setEcolage(res.getFloat(3));
                niveau.setAnneDedut(res.getDate(4));                
                niveau.setAnneFin(res.getDate(5));
                vec.add(niveau);
            }

            ecolage = new EcolageNiveau[vec.size()];
            for(int i=0; i<vec.size(); i++) {
                ecolage[i] = vec.get(i);
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
        
        return ecolage;
    } 
}