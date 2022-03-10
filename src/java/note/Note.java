package note;

import fonction.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Note {
    int idEleve;
    String eleveNom;
    String eleveClasse;
    int idMatiere;
    String matiereNom;
    int matiereCoefficient;
    float notePoint;
    Date noteDateExamen;

    public int getIdEleve() {
        return idEleve;
    }
    public void setIdEleve(int idEleve) {
        this.idEleve = idEleve;
    }

    public String getEleveNom() {
        return eleveNom;
    }
    public void setEleveNom(String eleveNom) {
        this.eleveNom = eleveNom;
    }

    public String getEleveClasse() {
        return eleveClasse;
    }
    public void setEleveClasse(String eleveClasse) {
        this.eleveClasse = eleveClasse;
    }

    public int getIdMatiere() {
        return idMatiere;
    }
    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getMatiereNom() {
        return matiereNom;
    }
    public void setMatiereNom(String matiereNom) {
        this.matiereNom = matiereNom;
    }

    public int getMatiereCoefficient() {
        return matiereCoefficient;
    }
    public void setMatiereCoefficient(int matiereCoefficient) {
        this.matiereCoefficient = matiereCoefficient;
    }

    public float getNotePoint() {
        return notePoint;
    }
    public void setNotePoint(float notePoint) {
        this.notePoint = notePoint;
    }

    public Date getNoteDateExamen() {
        return noteDateExamen;
    }
    public void setNoteDateExamen(Date noteDateExamen) {
        this.noteDateExamen = noteDateExamen;
    }
    
    public Note[] read() throws Exception {
        Note[] note = null;
        Vector<Note> vec = new Vector();
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "select * from Notes";
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()) {
                Note notes = new Note();
                notes.setIdEleve(res.getInt(1));
                notes.setEleveNom(res.getString(2));
                notes.setEleveClasse(res.getString(3));
                notes.setIdMatiere(res.getInt(4));
                notes.setMatiereNom(res.getString(5));                
                notes.setMatiereCoefficient(res.getInt(6));                
                notes.setNotePoint(res.getFloat(7));                
                notes.setNoteDateExamen(res.getDate(8));
                vec.add(notes);
            }

            note = new Note[vec.size()];
            for(int i=0; i<vec.size(); i++) {
                note[i] = vec.get(i);
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
        
        return note;
    }
    
    public void update(int ideleve, float note, Date dateexamen) throws Exception {
        int update = 0;
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "update note set poimt = " + note + " , dateExamen = '" + dateexamen + "' where idEleve = " + ideleve;
            stmt = con.createStatement();
            update = stmt.executeUpdate(sql);
        }
        
        catch(Exception e) {
            throw e;
        }
        
        finally {
            
        }
    }
    
    public void delete(float note) throws Exception {
        int delete = 0;
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "delete from note where poimt = " + note;
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
    
    public void enregistrement(int ideleve, int idmatiere, float notepoint, Date dateexamen) throws Exception {
        int insert = 0;
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "insert into note (idEleve,idMatiere,poimt,dateExamen,validation) values (" + ideleve + "," + idmatiere + "," + notepoint + ",'" + dateexamen + "','false')";
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
    
    public void validation(int ideleve) throws Exception {
        int valide = 0;
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "update note set validation = true where idEleve = " + ideleve;
            stmt = con.createStatement();
            valide = stmt.executeUpdate(sql);
        }
        
        catch(Exception e) {
            throw e;
        }
        
        finally {
            stmt.close();
            con.close();
        }
    }
    
    public Note[] getBulletin(int ideleve) throws Exception {
        Note[] note = null;
        Vector<Note> vec = new Vector();
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        
        try {
            con = new Connexion().connectpsql();
            
            String sql = "select * from notes where idEleve = " + idEleve;
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()) {
                Note notes = new Note();
                notes.setIdEleve(res.getInt(1));
                notes.setEleveNom(res.getString(2));
                notes.setEleveClasse(res.getString(3));
                notes.setIdMatiere(res.getInt(4));
                notes.setMatiereNom(res.getString(5));                
                notes.setMatiereCoefficient(res.getInt(6));                
                notes.setNotePoint(res.getFloat(7));                
                notes.setNoteDateExamen(res.getDate(8));
                vec.add(notes);
            }
            
            note = new Note[vec.size()];
            for(int i=0; i<vec.size(); i++) {
                note[i] = vec.get(i);
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
        
        return note;
    }
    
}
