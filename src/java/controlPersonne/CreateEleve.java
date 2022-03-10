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
import personne.Personne;

public class CreateEleve extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String naissanceeleve = req.getParameter("naissance");
        String sexeeleve = req.getParameter("sexe");        
        String adresse = req.getParameter("adresse");
        String creele = req.getParameter("cree_le");
        String idclasseeleve = req.getParameter("idclasse");
        
        Date naissance = Date.valueOf(naissanceeleve);
        Boolean sexe = Boolean.parseBoolean(sexeeleve);
        Date cree = Date.valueOf(creele);
        int idclasse = Integer.parseInt(idclasseeleve);
        
        Personne personne = new Personne();
        Eleve eleve = new Eleve();
   
        try {
            personne.create(nom, prenom, naissance, sexe, adresse);
            personne = personne.getlast();
            int id = personne.getId();
            eleve.create(id, idclasse, cree);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(CreateEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
       /* RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);*/
    }
}