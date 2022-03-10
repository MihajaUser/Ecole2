package controlNote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import note.Note;

public class GetBulletin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String ideleves = req.getParameter("ideleve");
        int id = Integer.parseInt(ideleves);
        
        Note note = new Note();
        try {
            Note[] notes = note.getBulletin(id);
            Vector<Note> vec = new Vector();
            for(int i=0; i<vec.size(); i++){
                vec.add(notes[i]);
            }
            req.setAttribute("list", vec);
        } 
        
        catch (Exception ex) {
            Logger.getLogger(GetBulletin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispat = req.getRequestDispatcher("/resultat.jsp");
        dispat.forward(req,res);
    }
}