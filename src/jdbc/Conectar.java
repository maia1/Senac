package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class Conectar {
//    private Connection connection = null;
//    private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    private final String dbName = "DB_ESTOQUE";
//    private final String url = "jdbc:sqlserver://localhost:1433;databaseName="+dbName+";";
//    private final String usuario = "sa";
//    private final String senha = "senac";
    
        ///Conectar ao MySql
    
    private Connection connection = null;
    private final String driver = "com.mysql.jdbc.Driver";
    private final String dbName = "db_estoque3";
    private final String url = "jdbc:mysql://127.0.0.1:3306/"+dbName;
    private final String usuario = "root";
    private final String senha = "";
    
    public Connection getConnection(){
        try{
        Class.forName(driver);
        connection = DriverManager.getConnection(url, usuario, senha);
        return connection;
        
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Driver não encontrado!!");
            return null;
        }catch(SQLException s){
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o BD!!");
            return null;
        }
        
    }
    
    public void close(){
        try{
            connection.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro!!");
        }
    }
    
}
