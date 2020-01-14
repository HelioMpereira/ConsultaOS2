package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

/**
 * java com Mysql
 * 
 * @author t1064452
 */

public class Conector {
   

    private Connection connection;
    
    
    
    //abre e retorna a conexao com o mysql
    public Connection conect;

    
    public Connection conectar()throws Throwable{
//        String servidor = "localhost";
//        String usuario = "root";
//        String senha = "fl@v1@12";
//        String banco = "subsidio_proativo";

        
        String servidor = "localhost";
        String usuario = "root";
        String senha = "fl@v1@heli0";
        String banco = "controleosgarantia";
        
        String url = "jdbc:mysql://"+servidor+":3306/"+banco;
        conect = DriverManager.getConnection(url,usuario,senha);
        return  conect;
    } 

     public void fechar() throws SQLException
    {
        conect.close();
    }

//    public Connection conectar() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            this.connection = DriverManager.getConnection("jdbc:mysql://10.105.87.250:3306/subsidio_proativo","f3295813","7xJU@UEr");
//            return this.connection;
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }

 
//    public void desconectar(Connection connection) {
//        try {
//            connection.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
}

