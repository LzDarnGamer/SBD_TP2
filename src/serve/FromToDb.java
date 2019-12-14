package serve;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FromToDb {
	
	
	public static Connection getConn() {
		final String host = "localhost";
		final String database = "flags";
		final String url = "jdbc:mysql://"+host+"/"+database+"?useLegacyDatetimeCode=false&serverTimezone=Europe/Lisbon";
		final String user = "root";
		final String pass = "root";
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL8

			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection successful..");

		} catch (ClassNotFoundException e) {
			System.err.println("Class not found!");
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.err.println("Connection failed!");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static void writeBlob(String country, String column, InputStream input) {
		/* column  = {mini,normal,big,ultra9} */
		Connection conn = getConn();
		String SQL = "UPDATE FLAG SET "+column+" = ? "
				+ " WHERE `ISO3166-1A2C` = "
				+ "(SELECT `ISO3166-1A2C` FROM COUNTRY WHERE SHORTNAME = ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SQL);
			// set parameters
			pstmt.setBinaryStream(1, input);
			pstmt.setString(2, country);
			
			// store  in database
			pstmt.executeUpdate();
			System.out.println("File uploaded and saved into database!");
			pstmt.close();
		} 
		catch (SQLException e) {
			System.err.println("SQL Exception!");
			System.out.println(e.getMessage());
		}
		finally{
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.err.println("Connot close!");
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public static void readBlob(String country, String column, OutputStream output) {
		if(country==null || country=="")
			country="Portugal";
		/* column  = {mini,normal,big,ultra} */
		String selectSQL = "SELECT "+ column + 
		" FROM flag f, country c where f.`ISO3166-1A2C` = c.`ISO3166-1A2C` and shortname like '"+country+"%';";
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectSQL);

			while (rs.next()) {
				InputStream input = rs.getBinaryStream(column);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}
			}
			output.close();
		} 
		catch (SQLException e) {
			System.err.println("SQL Exception!");
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.err.println("Connot close!");
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static String readList(String country) {
		Connection conn = null;
		Statement stmt = null;
		String buffer = "<datalist>";
		try {
			conn = getConn();
			stmt = conn.createStatement();
			System.out.println("Procura like: " + country);
			ResultSet rs = stmt
					.executeQuery("Select ShortName from country where ShortName LIKE '"+ country + "%' order by ShortName");
			while (rs.next()) {
				buffer = buffer + "<option value='" + rs.getString("ShortName") + "'>";
				// System.out.println(rs.getString("ShortName"));
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception!");
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.err.println("Connot close!");
				System.out.println(e.getMessage());
			}
		}
		
		/* <datalist id="browsers">
		  <option value="Internet Explorer">
		  <option value="Firefox">
		  <option value="Chrome">
		  <option value="Opera">
		  <option value="Safari">
		</datalist> */

		return buffer + "</datalist>";
	}

	public static void main(String[] args) {
		
	}

}
