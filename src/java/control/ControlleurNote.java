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

public class ControlleurNote extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String ideleve = req.getParameter("ideleve");
        String noteeleve = req.getParameter("note");
        String datenoteexamen = req.getParameter("examen");
        
        int id = Integer.parseInt(ideleve);
        float pointnote = Float.parseFloat(noteeleve);
        Date examen = java.sql.Date.valueOf(datenoteexamen);

        Note note = new Note();
        try {
            note.update(id, pointnote, examen);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(ControlleurNote.class.getName()).log(Level.SEVERE, null, ex);
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
            note.delete(id);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(ControlleurNote.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
}