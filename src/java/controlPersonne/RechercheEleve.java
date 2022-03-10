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
import personne.Eleve;

public class RechercheEleve extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String nom = req.getParameter("nom");
        String sexeeleve = req.getParameter("sexe");
        String adresse = req.getParameter("adresse");
        String naissanceeleve = req.getParameter("naissance");
        
        Boolean sexe = Boolean.parseBoolean(sexeeleve);
        Date naissance = java.sql.Date.valueOf(naissanceeleve);

        Eleve eleve = new Eleve();
        try {
            Eleve[] listeleve = eleve.recherche(nom, sexe, adresse, naissance);
            req.setAttribute("list", listeleve);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(RechercheEleve.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
}