package controlleur;

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
import personne.Eleve;
import personne.Personne;

public class Controlpersonne extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String idclasse = req.getParameter("idclasse");
        String ideleve = req.getParameter("id");
        
        int idclass = Integer.parseInt(idclasse);
        int id = Integer.parseInt(ideleve);

        Eleve eleve = new Eleve();
        try {
            eleve.update(id,idclass);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(Controlpersonne .class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String ideleve = req.getParameter("id");
                 
        int id = Integer.parseInt(ideleve);

        Eleve eleve = new Eleve();
        try {
            eleve.delete(id);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(Controlpersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
}