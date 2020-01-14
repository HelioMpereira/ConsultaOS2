package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class MySQL {

    private final String host = "172.29.14.105";
    private final String user = "cma";
    private final String password = "cma2015";
    private final String database = "mstNovo";
    
    private final String url = "jdbc:mysql://" + host + "/" + database;
    
    private Connection connection;
    
    public MySQL() {
        connect();
    }
    
    private void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void disconnect() {
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int execQuery(String query)
    {
        int result_exec = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            result_exec = statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return result_exec;
    }
    
    public ResultSet selectQuery(String query)
    {
        ResultSet result_query = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            result_query = statement.executeQuery();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return result_query;
    }
    
}

