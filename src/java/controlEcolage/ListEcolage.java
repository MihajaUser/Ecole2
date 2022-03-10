package controlEcolage;

import ecolage.EcolageNiveau;
import ecolage.PaimentEcolage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListEcolage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        EcolageNiveau ecolage = new EcolageNiveau();
        PaimentEcolage paiment = new PaimentEcolage();
        try {
            EcolageNiveau[] niveau = ecolage.getList();
            PaimentEcolage[] pai = paiment.getPaimentEcolage();
            req.setAttribute("list", niveau);
            req.setAttribute("list", pai);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(ListEcolage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
        RequestDispatcher dispatpai = req.getRequestDispatcher("/resultatpai.jsp");
        dispat.forward(req,res);
    }
}