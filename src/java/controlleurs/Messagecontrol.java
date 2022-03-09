package controlleurs;

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

public class Messagecontrol extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String id = req.getParameter("id");
        String sommeecolage = req.getParameter("somme");
        String duMoisecolage = req.getParameter("duMois");
        String payeLeecolage = req.getParameter("payeLe");
        
        int ideleve = Integer.parseInt(id);
        float somme = Float.parseFloat(sommeecolage);
        Date duMois = java.sql.Date.valueOf(duMoisecolage);            
        Date payeLe = java.sql.Date.valueOf(payeLeecolage);

        PaimentEcolage paiment = new PaimentEcolage();
        try {
            paiment.createPaiment(ideleve,somme, duMois, payeLe);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(Messagecontrol.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
}
