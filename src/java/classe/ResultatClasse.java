package classe;

import fonction.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class ResultatClasse {
    String classeNom;
    float moyenne;
    Date anneDedut;
    Date anneFin;

    public String getClasseNom() {
        return classeNom;
    }
    public void setClasseNom(String classeNom) {
        this.classeNom = classeNom;
    }

    public float getMoyenne() {
        return moyenne;
    }
    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
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
    
    public ResultatClasse[] getResultatClasse(int idClasse,Date anneeDebut ,Date anneFin) throws Exception{
        ResultatClasse[] resultat = null;
        Vector vec = new Vector();
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "select classes.nom,historiqueClasses.debut,historiqueClasses.fin from historiqueClasses join classes where historiqueClasses.idClasse = classes.id";
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()) {
                this.setClasseNom(res.getString(1));
                this.setAnneDedut(res.getDate(2));
                this.setAnneFin(res.getDate(3));
                vec.add(this);
            }
            
            resultat = new ResultatClasse[vec.size()];
            for(int i=0; i<vec.size(); i++) {
                resultat[i] = (ResultatClasse) vec.get(i);
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
        
        return resultat;
    }
    
}
