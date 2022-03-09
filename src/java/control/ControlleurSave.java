package control;

import ecolage.EcolageNiveau;
import ecolage.PaimentEcolage;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import note.Note;
import personne.Eleve;
import personne.Personne;

public class ControlleurSave extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String eleve = req.getParameter("ideleve");
        String matiere = req.getParameter("idmatiere");
        String notepoint = req.getParameter("point");
        String dateexamen = req.getParameter("dateexamen");
        
        int ideleve = Integer.parseInt(eleve);
        int idmatiere = Integer.parseInt(matiere);
        float pointnote = Float.parseFloat(notepoint);
        Date examen = java.sql.Date.valueOf(dateexamen);

        Note note = new Note();
        try {
            note.enregistrement(ideleve, idmatiere, pointnote, examen);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(ControlleurSave.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String ideleve = req.getParameter("id");
                 
        int id = Integer.parseInt(ideleve);

        Note note = new Note();
        try {
            note.validation(id);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(ControlleurSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
}