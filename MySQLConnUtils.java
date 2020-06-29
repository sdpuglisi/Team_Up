/**
 * 
 */
package com.teamup.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author s.inno, a.musciacchio, s.puglisi, m.valente
 * This class has been implemented with a singleton pattern and it creates a connection to the SQL database
 * @return a connection object to the database 
 *
 */
public class MySQLConnUtils {
	
    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        
        String hostName = "127.0.0.1";
        String dbName = "team_up";
        String userName = "root";
        String password = "salvodario91";
        
        return getMySQLConnection(hostName, dbName, userName, password);
    }
     
    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {
       
        Class.forName("com.mysql.cj.jdbc.Driver");
     
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=UTC";
     
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
