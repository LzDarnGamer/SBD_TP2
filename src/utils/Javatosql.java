package utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class Javatosql {

	private final String pessoaQuery = "insert into Pessoa (nif, idade, nome, apelido, morada)"
			+ " values (?, ?, ?, ?, ?)";

	private final String medicoQuery = "insert into Medico (nif, vencimento, nome_especialidade)" + "values(?,?,?)";

	private final String utenteQuery = "insert into Utente (nif)" + "values(?)";

	private final String consultaQuery = "insert into Consulta (idConsulta, data, hora)" + "values(?,?,?)";

	private final String especialidadeQuery = "insert into Especialidade (nome)" + "values(?)";

	private final String manchaQuery = "insert into ManchaHoraria (dia, horaInit, horaFim)" + "values(?,?,?)";

	
	public int insertToPessoa(Connection con, int nif, int idade, String nome, String apelido, String morada) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(pessoaQuery);
			preparedStmt.setInt(1, nif);
			preparedStmt.setInt(2, idade);
			preparedStmt.setString(3, nome);
			preparedStmt.setString(4, apelido);
			preparedStmt.setString(5, morada);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}
	public int insertToUtente(Connection con, int nif) throws SQLException {

		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(utenteQuery);
			preparedStmt.setInt(1, nif);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}
	public int insertToMedico(Connection con, int nif, int vencimento, String especialidade) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(medicoQuery);
			preparedStmt.setInt(1, nif);
			preparedStmt.setInt(2, vencimento);
			preparedStmt.setString(3, especialidade);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}
	public int insertToConsulta(Connection con, int idconsulta, Date data, Time hora) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(consultaQuery);
			preparedStmt.setInt(1, idconsulta);
			preparedStmt.setDate(2, data);
			preparedStmt.setTime(3, hora);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}
	public int insertToEspecialidade(Connection con, String nome) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(especialidadeQuery);
			preparedStmt.setString(1, nome);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}
	public int insertToMancha(Connection con, int dia, Time horaInit, Time horaFim) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(manchaQuery);
			preparedStmt.setInt(1, dia);
			preparedStmt.setTime(2, horaInit);
			preparedStmt.setTime(3, horaFim);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);	
		}
		return 0;
	}
	public static void printForeignKeys(Connection connection, String tableName) throws SQLException {

	}
	

}
