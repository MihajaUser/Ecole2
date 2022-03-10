package controlPersonne;

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
import personne.Personne;

public class ListPersonne extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String datepersonne = req.getParameter("date");
        String sexepersonne = req.getParameter("sexe");
        String adresse = req.getParameter("adresse");
                 
        Date date = Date.valueOf(datepersonne);
        Boolean sexe = Boolean.valueOf(sexepersonne);

        Personne personne = new Personne();
        Personne[] listpersonne = null;
        try {
            listpersonne = personne.read();
            req.setAttribute("list", listpersonne);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(ListPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
}