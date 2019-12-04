package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map.Entry;

import utils.Javatosql;
import utils.Sqltojava;
import utils.databaseAcess;

public class Teste {

	private static Connection con;
	public Teste() {
		Teste t = new Teste();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinica?useTimezone=true&serverTimezone=UTC",
					databaseAcess.USER, databaseAcess.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {

		Javatosql.insertToEspecialidade(con, "Clinica Geral");
		Javatosql.insertToEspecialidade(con, "Gastroentrologia");
		Javatosql.insertToEspecialidade(con, "Otorrino");
		
//		int is = Javatosql.removeEspecialidade(con, "Cardiologia");
//		int p = Javatosql.insertToPessoa(con, 123456789, 49, "Shun", "Wang", "teste");
//		int p1 = Javatosql.insertToPessoa(con, 987654321, 49, "Arman", "Freitas", "teste");
//		
//		int user = Javatosql.insertToUtente(con, 123456789);
//		int medic = Javatosql.insertToMedico(con, 987654321, 1500, "Cardiologia");
		
		

		
		List<Sqltojava> list = Sqltojava.getTable(con, "Especialidade");
		StringBuilder s = new StringBuilder();
		for (Sqltojava i : list) {
			for (Entry<Object, Class> col : i.row) {
				s.append(String.valueOf((col.getValue()).cast(col.getKey())) + "\n");
			}
		}
		System.out.println(s.toString());

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



}
