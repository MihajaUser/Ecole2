package controlNote;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import note.Note;

public class Enregistrement extends HttpServlet {
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
        Date examen = Date.valueOf(dateexamen);

        Note note = new Note();
        try {
            note.enregistrement(ideleve, idmatiere, pointnote, examen);
            
            Note[] listnote = note.read();
            Vector<Note> vec = new Vector();
            for(int i=0; i<listnote.length; i++) {
                vec.add(listnote[i]);
            }
            
            req.setAttribute("list", vec);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(Enregistrement.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
}