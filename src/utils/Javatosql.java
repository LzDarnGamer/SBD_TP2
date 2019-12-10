package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Javatosql {

	private static final String pessoaQuery = "insert into Pessoa (nif, idade,sexo, nome, apelido, morada)"
			+ " values (?, ?, ?, ?, ?, ?)";

	private static final String medicoQuery = "insert into Medico (nif, vencimento, nome_especialidade)"
			+ "values(?,?,?)";

	private static final String utenteQuery = "insert into Utente (nif)" + "values(?)";

	private static final String consultaQuery = "insert into Consulta (dataConsulta, hora, "
			+ "nome_especialidade, nif_utente, nif_medico)" + "values(?,?,?,?,?)";

	private static final String especialidadeQuery = "insert into Especialidade (nome)" + "values(?)";

	private static final String manchaQuery = "insert into ManchaHoraria ( "
			+ "dia, horaInicio, horaFim, nome_Especialidade, nif_medico)" + "values(?,?,?,?,?)";

	private static final String faltaQuery = "insert into Falta ( "
			+ "nif_utente, nif_medico, horaInicio, horaFim, dataConsulta, nome_Especialidade)" + "values(?,?,?,?,?,?)";

	private static final String presencaQuery = "insert into Presenca ( "
			+ "nif_utente, nif_medico, horaInicio, horaFim, dataConsulta, nome_Especialidade)" + "values(?,?,?,?,?,?)";

	private static final String remarcacaoQuery = "insert into Remarcacoes( "
			+ "nif_utente, nif_medico, horaInicio, horaFim, dataConsulta, nome_Especialidade)" + "values(?,?,?,?,?,?)";

	private static final String cancelamentoQuery = "insert into Cancelamento()";

	public static int insertToPessoa(Connection con, int nif, int idade, String sexo, String nome, String apelido,
			String morada) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(pessoaQuery);
			preparedStmt.setInt(1, nif);
			preparedStmt.setInt(2, idade);
			preparedStmt.setString(3, sexo);
			preparedStmt.setString(4, nome);
			preparedStmt.setString(5, apelido);
			preparedStmt.setString(6, morada);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int insertToUtente(Connection con, int nif) {

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

	public static int insertToMedico(Connection con, int nif, int vencimento, String especialidade) {
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

	public static int insertToConsulta(Connection con, int idManchaHoraria, Date data, Time hora, String especialidade,
			int nif_utente, int nif_medico) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(consultaQuery);
			preparedStmt.setDate(1, data);
			preparedStmt.setTime(2, hora);
			preparedStmt.setString(3, especialidade);
			preparedStmt.setInt(4, nif_utente);
			preparedStmt.setInt(5, nif_medico);
			preparedStmt.setInt(6, idManchaHoraria);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int insertToEspecialidade(Connection con, String nome) {
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

	public static int insertToMancha(Connection con, Date dia, Time horaInit, Time horaFim, String nomeEspecialidade,
			int nif_medico) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(manchaQuery);
			preparedStmt.setDate(1, dia);
			preparedStmt.setTime(2, horaInit);
			preparedStmt.setTime(3, horaFim);
			preparedStmt.setString(4, nomeEspecialidade);
			preparedStmt.setInt(5, nif_medico);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int insertConsulta(Connection con, Date dia, Time hora, String nome_especialidade, int nif_utente,
			int nif_medico) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(consultaQuery);
			preparedStmt.setDate(1, dia);
			preparedStmt.setTime(2, hora);
			preparedStmt.setString(3, nome_especialidade);
			preparedStmt.setInt(4, nif_utente);
			preparedStmt.setInt(5, nif_medico);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int insertFalta(Connection con, Date dia, Time horaInit, String nome_especialidade, Time horaFim,
			int nif_utente, int nif_medico) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(faltaQuery);
			preparedStmt.setInt(1, nif_utente);
			preparedStmt.setInt(2, nif_medico);
			preparedStmt.setTime(3, horaInit);
			preparedStmt.setTime(4, horaFim);
			preparedStmt.setDate(5, dia);
			preparedStmt.setString(6, nome_especialidade);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int insertPresenca(Connection con, Date dia, Time horaInit, String nome_especialidade, Time horaFim,
			int nif_utente, int nif_medico) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(presencaQuery);
			preparedStmt.setInt(1, nif_utente);
			preparedStmt.setInt(2, nif_medico);
			preparedStmt.setTime(3, horaInit);
			preparedStmt.setTime(4, horaFim);
			preparedStmt.setDate(5, dia);
			preparedStmt.setString(6, nome_especialidade);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int insertRemarcacao(Connection con, Date dia, String nome_especialidade, Time horaInit, Time horaFim,
			int nif_utente, int nif_medico) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(remarcacaoQuery);
			preparedStmt.setInt(1, nif_utente);
			preparedStmt.setInt(2, nif_medico);
			preparedStmt.setTime(3, horaInit);
			preparedStmt.setTime(4, horaFim);
			preparedStmt.setDate(5, dia);
			preparedStmt.setString(6, nome_especialidade);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int insertCancelamento(Connection con) {
		try {
			PreparedStatement st;
			st = con.prepareStatement(cancelamentoQuery);
			return st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int removeEspecialidade(Connection con, String nomeEspecialidade) {
		PreparedStatement st;
		try {
			st = con.prepareStatement("DELETE FROM Especialidade WHERE nome = ?");
			st.setString(1, nomeEspecialidade);
			return st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int removeUtente(Connection con, int nif) {
		PreparedStatement st;
		try {
			st = con.prepareStatement("DELETE FROM Utente WHERE nif = ?");
			st.setInt(1, nif);
			return st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int removePessoa(Connection con, int nif) {
		PreparedStatement st;
		try {
			st = con.prepareStatement("DELETE FROM Pessoa WHERE nif = ?");
			st.setInt(1, nif);
			return st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int removeMedico(Connection con, int nif) {
		PreparedStatement st;
		try {
			st = con.prepareStatement("DELETE FROM Medico WHERE nif = ?");
			st.setInt(1, nif);
			return st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int removeMancha(Connection con, int idManchaHoraria) {
		PreparedStatement st;
		try {
			st = con.prepareStatement("DELETE FROM ManchaHoraria WHERE idManchaHoraria = ?");
			st.setInt(1, idManchaHoraria);
			return st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int removeConsulta(Connection con, int idConsulta) {
		PreparedStatement st;
		try {
			st = con.prepareStatement("DELETE FROM Consulta WHERE idConsulta = ?");
			st.setInt(1, idConsulta);
			return st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;

	}

	public static boolean checkUtente(Connection con, int nif) {

		try {
			String queryCheck = "SELECT * from Utente WHERE nif = ?";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif);
			ResultSet resultSet = ps.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public static boolean checkMedico(Connection con, int nif) {

		try {
			String queryCheck = "SELECT * from Medico WHERE nif = ?";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif);
			ResultSet resultSet = ps.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public static boolean checkPessoa(Connection con, int nif) {

		try {
			String queryCheck = "SELECT * from Pessoa WHERE nif = ?";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif);
			ResultSet resultSet = ps.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public static boolean checkEspecialidade(Connection con, String nome) {

		try {
			String queryCheck = "SELECT * from Especialidade WHERE nome = ?";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setString(1, nome);
			ResultSet resultSet = ps.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public static String getUtente(Connection con, int nif) {
		try {
			String queryCheck = "SELECT Pessoa.nome, Pessoa.apelido, Pessoa.nif from Pessoa WHERE nif = ?";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			return Sqltojava.getList(con, list);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static int getIDconsulta(Connection con, Date data, Time hora, String nome_especialidade, int nif_medico) {
		String queryCheck = "SELECT Consulta.idConsulta FROM Consulta WHERE dataConsulta = ?" + "AND hora = ?"
				+ "AND nome_especialidade = ?" + "AND nif_medico = ?";
		try {
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setDate(1, data);
			ps.setTime(2, hora);
			ps.setString(3, nome_especialidade);
			ps.setInt(4, nif_medico);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int getIDmancha(Connection con, Date data, Time hora, String nome_especialidade, int nif_medico) {

		String queryCheck = "SELECT ManchaHoraria.idManchaHoraria FROM ManchaHoraria WHERE dia = ? "
				+ "AND horaInicio = ?" + "AND nome_Especialidade = ?" + "AND nif_medico = ?";

		try {
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setDate(1, data);
			ps.setTime(2, hora);
			ps.setString(3, nome_especialidade);
			ps.setInt(4, nif_medico);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			return -1;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (NumberFormatException e2) {
			System.out.println("Mancha não Encontrada");
		}
		return -1;
	}

	public static String[] listarConsultas(Connection con, int nif) {
		try {
			String queryCheck = "SELECT Consulta.Data from Consulta WHERE nif_utente = ?";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s.split("\n");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;

	}

	public static String[] listarMedicosbyEsp(Connection con, String nome_especialidade) {
		try {
			String queryCheck = "SELECT Pessoa.nome,Pessoa.apelido,Pessoa.nif,Pessoa.idade "
					+ "FROM ((Medico INNER JOIN Especialidade ON Medico.nome_especialidade = Especialidade.nome)"
					+ "INNER JOIN Pessoa ON Medico.nif = Pessoa.nif)" + "WHERE (Medico.nome_especialidade = ?)";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setString(1, nome_especialidade);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s.split(" ");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String[] listarMedicosbyNif(Connection con, int nif_medico) {
		try {
			String queryCheck = "SELECT Pessoa.nif,Pessoa.nome,Pessoa.apelido,Pessoa.idade "
					+ "FROM ((Medico INNER JOIN Especialidade ON Medico.nome_especialidade = Especialidade.nome)"
					+ "INNER JOIN Pessoa ON Medico.nif = Pessoa.nif)" + "WHERE (Medico.nif_medico = ?)";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif_medico);
			ResultSet rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getInt(1) + "=" + rs.getString(2) + "=" + rs.getString(3) + "=" + rs.getInt(4));
			}
			return list.toArray(new String[list.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String[] listarTabelaStatus(Connection con, String tipo) {
		try {
			String query = "SELECT E.nif_utente, E.horaInicio, E.dataConsulta" + " FROM " + tipo + " E";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			return Sqltojava.getList(con, list).split("\n");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String listarStatusUtente(Connection con, String tipo) {
		try {
			String query = "SELECT " + tipo + ".nif_utente FROM " + tipo;
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			return Sqltojava.getList(con, list);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static Date dateFormater(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, day);

		return new Date(cal.getTimeInMillis());

	}

	public static Time timeFormater(String hora, String minutos) {
		String time = hora + ":" + minutos + ":00";
		SimpleDateFormat s = new SimpleDateFormat("H:mm:ss");
		Long ms = null;
		try {
			ms = s.parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Time(ms);
	}

	public static Time timeFormater(String t) {
		SimpleDateFormat s = new SimpleDateFormat("H:mm:ss");
		Long ms = null;
		try {
			ms = s.parse(t).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Time(ms);
	}

	public static String getSex(Connection con, int nif) {
		String queryCheck = "SELECT Pessoa.sexo FROM Pessoa WHERE nif = ?";
		try {
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getAge(Connection con, int nif) {
		String queryCheck = "SELECT Pessoa.idade FROM Pessoa WHERE nif = ?";
		try {
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static String getEspecialidade(Connection con, int nif) {
		String queryCheck = "SELECT Medico.nome_especialidade FROM Medico WHERE nif = ?";
		try {
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String[] procurarUtentes(Connection con) {
		try {
			String query = "SELECT Pessoa.nif,Pessoa.idade,Pessoa.sexo,Pessoa.nome,Pessoa.apelido,Pessoa.morada"
					+ " from Utente RIGHT JOIN Pessoa ON Utente.nif = Pessoa.nif";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s.split("\n");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String[] listarEspecialidades(Connection con) {
		try {
			String query = "SELECT Especialidade.nome FROM Especialidade";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s.split("\n");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static Integer[] listarMedicos(Connection con) {
		try {
			String query = "SELECT Medico.nif FROM Medico";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<Integer> list = new ArrayList<Integer>();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
			return list.toArray(new Integer[list.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String[] listarDiaHora(Connection con, String especialidade) {
		try {
			String query = "SELECT M.dia, M.horaInicio, M.horaFim, M.nif_medico"
					+ " FROM ManchaHoraria M INNER JOIN Especialidade E on M.nome_Especialidade = E.nome "
					+ "WHERE (E.nome = ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, especialidade);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s.split("\n");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String listarStatusEspecialidade(Connection con, String nome_especialidade, String status) {
		try {
			String query = "SELECT E.nif_utente, E.horaInicio, E.dataConsulta FROM " + status + " E"
					+ " WHERE (E.nome_Especialidade = ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, nome_especialidade);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			return Sqltojava.getList(con, list);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static int listarTempoPerdido(Connection con, int nif_medico, Date init, Date fim) {
		try {
			String query = "SELECT COUNT(*),(SELECT COUNT(*) FROM Falta WHERE (Falta.nif_medico = ?"
					+ " and Falta.dataConsulta between ? and ? ))"
					+ "FROM Cancelamento WHERE (Cancelamento.nif_medico = ?"
					+ " and Cancelamento.dataConsulta between ? and ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, nif_medico);
			ps.setDate(2, init);
			ps.setDate(3, fim);
			ps.setInt(4, nif_medico);
			ps.setDate(5, init);
			ps.setDate(6, fim);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1) + resultSet.getInt(2);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return -1;
	}
//	public static boolean checkConsulta(Connection con, int nif) {
//
//		try {
//			String queryCheck = "SELECT * from Utente WHERE nif = ?";
//			PreparedStatement ps = con.prepareStatement(queryCheck);
//			ps.setInt(1, nif);
//			ResultSet resultSet = ps.executeQuery();
//			return resultSet.next();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//		return false;
//	}
//	

}
