package controlPersonne;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import personne.Eleve;

public class UpdateEleve extends HttpServlet {
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
            Logger.getLogger(UpdateEleve .class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
}