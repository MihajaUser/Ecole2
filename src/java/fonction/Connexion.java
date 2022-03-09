package fonction;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    public Connection connectpsql() throws Exception {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecole","postgres","1234");
            System.out.println("ok");
        }
        catch(Exception e){ 
            System.out.println(e);
            throw e;
        }
        return con;
    }
}
