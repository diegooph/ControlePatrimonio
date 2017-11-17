package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static Connection con;
    
    public static Connection getConnection(){
        try { 
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/controlepatrimonio";
            con = DriverManager.getConnection(url,"root","");
            con.setAutoCommit(true);
            return con;
        } catch ( ClassNotFoundException e){
            e.printStackTrace();
        } catch ( SQLException se){
            se.printStackTrace();
        }
        return null;
    }
    
    public static void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
		System.out.println(getConnection());
	}
}