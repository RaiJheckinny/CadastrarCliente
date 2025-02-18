package br.com.raijheckinny.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    private ConnectionFactory(Connection connection){
    }

    public static Connection getConnetion(){
        if(connection == null){
            connection = initConnection();
        }
        return connection;
    }

    private static Connection initConnection() {
        try{
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:15432/Loja","postgres","admin");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

}
