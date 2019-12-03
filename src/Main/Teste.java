package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map.Entry;

import utils.Javatosql;
import utils.Sqltojava;

public class Teste {

	private static Connection con;
	private final String user = "root";
	private final String pass = "qazwsx123";

	public static void main(String[] args) throws SQLException {
		Javatosql j2s = new Javatosql();
		Teste t = new Teste();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinica?useTimezone=true&serverTimezone=UTC",
					"root", "qazwsx123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Javatosql.printForeignKeys(con, "Utente");
		int v = j2s.insertToEspecialidade(con, "Cardiologista");
		int value = j2s.insertToMedico(con, 123456789, 1500, "Cardiologista");
		
		System.out.println(v);
		System.out.println(value);
		ResultSet r = t.getTable("Medico");
		ResultSetMetaData rsmd = r.getMetaData();

		
		
		List<Sqltojava> list = Sqltojava.formTable(r);
		for (Sqltojava i : list) {
			for (Entry<Object, Class> col : i.row) {
				System.out.println(" > " + ((col.getValue()).cast(col.getKey())));
			}
			System.out.println();
		}

	}

	public int insertPessoa(int nif, int idade, String nome, String apelido, String morada) {
		try {
			String query = " insert into Pessoa (nif, idade, nome, apelido, morada)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, nif);
			preparedStmt.setInt(2, idade);
			preparedStmt.setString(3, nome);
			preparedStmt.setString(4, apelido);
			preparedStmt.setString(5, morada);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
		}
		return 0;
	}

	public void CreateTable(String newTableName) {

	}

	public ResultSet getTable(String name) {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from " + name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}
