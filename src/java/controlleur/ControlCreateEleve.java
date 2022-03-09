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

public class ControlCreateEleve extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        /*out.println("hello");
        System.out.println("Miantso servlet");
        
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
        
        Eleve eleve = new Eleve();
        int requete = 10;
        out.println(requete);
        
        try {
            out.println("voici1");
            requete = eleve.create(nom, prenom, naissance, sexe, adresse, cree, idclasse);
                        out.println("voici");
            req.setAttribute("list", requete);
        } 
        
        catch (Exception ex) {
                        out.println("voici2");
            Logger.getLogger(ControlRecherche.class.getName()).log(Level.SEVERE, null, ex);
        }
            out.println("voici3");
        /*RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);*/
    }
}