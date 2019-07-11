package Datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static Conexion instance = null;
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=PELUQUERIA";
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String user = "sa";
    private static final String pass = "sql";
    private static Connection cn = null;
    
    private Conexion() throws Exception, Exception {
        try{
            Class.forName(driver).newInstance();
            cn = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception ex){
            throw ex;
        }
    }
    
    public synchronized static Conexion instanciar() throws Exception{
        if(instance == null){
            instance = new Conexion();
        }
        return instance;
    }
    
    public Connection conectar(){
        return cn;
    }
    
    public void desconectar(){
        instance = null;
    }
}
