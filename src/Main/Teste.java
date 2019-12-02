package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Teste {
	
	private static Connection con;
	public static void main(String[] args) {
		System.out.println("asd");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/Clinica?useTimezone=true&serverTimezone=UTC","root", 
							"qazwsx123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		t.getTable("Pessoa");
	}
	
	public void getTable(String name) {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs=stmt.executeQuery("select * from Pessoa"); 
			
			while(rs.next()) 
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Teste() {

	}

}
