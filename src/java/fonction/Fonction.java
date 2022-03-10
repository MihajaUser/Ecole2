package fonction;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Fonction {
    public int getcolsize(Connection con,Object obj) throws Exception {
        ResultSet res = null;
        Statement stmt = null;
        String tablename = obj.getClass().getSimpleName();
        stmt = con.createStatement();
        String sql = "select * from " + tablename;
        res = stmt.executeQuery(sql);
        int colsize = res.getMetaData().getColumnCount();
        res.close();
        stmt.close();
        return colsize;
    }
    
    public Method getMethodSet(Field field,Object oject){
        Method method=null;
        Class laClasse=oject.getClass();
        String name=field.getName();
        name=name.replace(name.substring(0,1), name.substring(0,1).toUpperCase());
        while(!laClasse.getName().equals("java.lang.Object")){
            try{
                method=laClasse.getDeclaredMethod("set" + name,field.getType());
                break;
            }
            catch(Exception e){
                laClasse=laClasse.getSuperclass();
            }
        }
        
        return method;
    }
    
    public Field[] getFields(Object o){
        Vector<Field> liste=new Vector();
        Class laClasse=o.getClass();
        Field[] tableau;
        while(!laClasse.getName().equals("java.lang.Object")){
            tableau=laClasse.getDeclaredFields();
            for(int i=0;i<tableau.length;i++)
                liste.add(tableau[i]);
            laClasse=laClasse.getSuperclass();
        }
        tableau =new Field[liste.size()];
        for(int i=0;i<liste.size();i++)
            tableau[i]=(Field)liste.get(i);
        return tableau;
    }
    
    public Object[] select(Object obj, Connection con) throws Exception {
        ResultSet res = null;
        Statement stmt = null;
        Method mat = null;
        Object[] objt = null;
        
        try {
            String tablename = obj.getClass().getSimpleName();
            
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from " + tablename;
            res = stmt.executeQuery(sql);
            
            int size = 0;
            res.last();
            size = res.getRow();
            res.beforeFirst();
            objt = new Object[size];
            
            int colsize = getcolsize(con,obj);
            
            String[] colname = new String[colsize];
            String[] coltype = new String[colsize];
            
            int i;
            int j;
            
            for(i=0; i<colsize; i++) {
                colname[i] = res.getMetaData().getColumnName(i+1);
                coltype[i] = res.getMetaData().getColumnTypeName(i+1);
            }
            
            Field[] fieldname = getFields(obj);
//            Field[] fieldname = obj.getClass().getDeclaredFields();
            
            int h = fieldname.length;
            
            int indice=0;
            while(res.next()) {
                Object temporaire=obj.getClass().newInstance();
                for(j=0; j<colsize; j++) {
                    for(i=0; i<h; i++) {
                        if(coltype[j].equalsIgnoreCase("VARCHAR2") == true && colname[j].equalsIgnoreCase(fieldname[i].getName().toUpperCase()) == true) {
                            String r = res.getString(j+1);
//                            mat = obj.getClass().getDeclaredMethod("set" + fieldname[i].getName(),fieldname[i].getType());
                            mat = getMethodSet(fieldname[i],obj);
                            mat.invoke(temporaire, r);
                        }
                        else if(coltype[j].equalsIgnoreCase("NUMBER") == true && colname[j].equalsIgnoreCase(fieldname[i].getName().toUpperCase()) == true) {
                            int r = res.getInt(j+1);
//                            mat = obj.getClass().getDeclaredMethod("set" + fieldname[i].getName(),fieldname[i].getType());
                            mat = getMethodSet(fieldname[i],obj);
                            mat.invoke(temporaire, r);
                        }
                        else if(coltype[j].equalsIgnoreCase("DATE") == true && colname[j].equalsIgnoreCase(fieldname[i].getName().toUpperCase()) == true) {                                
                            Date r = res.getDate(j+1);
//                            mat = obj.getClass().getDeclaredMethod("set" + fieldname[i].getName(),fieldname[i].getType());
                            mat = getMethodSet(fieldname[i],obj);
                            mat.invoke(temporaire, r);
                        }  
                    }    
                }
                objt[indice] = temporaire;
                indice++;
            }
        }
        
        catch (Exception e) {
            throw e;
        }
        
        finally {
            res.close();
            stmt.close();
            con.close();
        }
        
        return objt;
    }
}
