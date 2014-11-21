package main;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBAccess {
	public Connection connect(String user, String pass){
		Connection c=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Acme-Bay");
			System.out.println("Exito");
		}catch(Exception e){
			e.printStackTrace();
		}
		return c;
	}
}
