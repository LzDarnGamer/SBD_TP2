package utils;

import java.io.InputStream;
import java.sql.Blob;
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

	private static final String pessoaQuery = "insert into Pessoa (nif, idade,sexo, nome, apelido, morada, image)"
			+ " values (?, ?, ?, ?, ?, ?, ?)";

	private static final String medicoQuery = "insert into Medico (nif, vencimento, nome_especialidade)"
			+ "values(?,?,?)";

	private static final String utenteQuery = "insert into Utente (nif)" + "values(?)";

	private static final String consultaQuery = "insert into Consulta (dataConsulta, horaInicio, "
			+ "nome_especialidade, nif_utente, nif_medico)" + "values(?,?,?,?,?)";

	private static final String especialidadeQuery = "insert into Especialidade (nome)" + "values(?)";

	private static final String manchaQuery = "insert into ManchaHoraria ( "
			+ "dia, horaInicio, horaFim, nome_Especialidade, nif_medico)" + "values(?,?,?,?,?)";

	private static final String remarcacaoQuery = "insert into Remarcacoes( "
			+ "nif_utente, nif_medico, horaInicio, horaFim, dataConsulta, nome_Especialidade)" + "values(?,?,?,?,?,?)";

	private static final String cancelamentoQuery = "insert into Cancelamento()";

	private static final String novaManchaQuery = "insert into ManchaHorariaMarcada "
			+ "(dia, horaInicio, horaFim, pessoas, nif_medico)" + "values(?,?,?,?,?)";

	public static int insertToPessoa(Connection con, int nif, int idade, String sexo, String nome, String apelido,
			String morada, InputStream image) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(pessoaQuery);
			preparedStmt.setInt(1, nif);
			preparedStmt.setInt(2, idade);
			preparedStmt.setString(3, sexo);
			preparedStmt.setString(4, nome);
			preparedStmt.setString(5, apelido);
			preparedStmt.setString(6, morada);
			preparedStmt.setBlob(7, image);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int updateUtente(Connection con, int nif, String sexo, int idade, String nome, String apelido,
			String morada, InputStream file) {
		PreparedStatement preparedStmt;
		String query = "UPDATE Pessoa "
				+ "SET nif = ? , idade = ?, nome = ?, apelido = ?, sexo = ?, morada = ?, image = ? " + "WHERE nif = ?";
		try {
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, nif);
			preparedStmt.setInt(2, idade);
			preparedStmt.setString(3, nome);
			preparedStmt.setString(4, apelido);
			preparedStmt.setString(5, sexo);
			preparedStmt.setString(6, morada);
			preparedStmt.setBlob(7, file);
			preparedStmt.setInt(8, nif);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int updateUtente(Connection con, int nif, String sexo, int idade, String nome, String apelido,
			String morada, Blob file) {
		PreparedStatement preparedStmt;
		String query = "UPDATE Pessoa "
				+ "SET nif = ? , idade = ?, nome = ?, apelido = ?, sexo = ?, morada = ?, image = ? " + "WHERE nif = ?";
		try {
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, nif);
			preparedStmt.setInt(2, idade);
			preparedStmt.setString(3, nome);
			preparedStmt.setString(4, apelido);
			preparedStmt.setString(5, sexo);
			preparedStmt.setString(6, morada);
			preparedStmt.setBlob(7, file);
			preparedStmt.setInt(8, nif);
			System.out.println(preparedStmt.executeUpdate());
			return 0;
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

	public static int updateMedico(Connection con, int nif, String sexo, int idade, String nome, String apelido,
			String morada, Blob file, int vencimento) {
		PreparedStatement preparedStmt;
		String query = "UPDATE Pessoa p, Medico m"
				+ "SET p.nif = ? , p.idade = ?, p.nome = ?, p.apelido = ?, p.sexo = ?, p.morada = ?, p.image = ? "
				+ "m.vencimento = ? "
				+ "WHERE p.nif = ? and m.nif = ?";
		try {
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, nif);
			preparedStmt.setInt(2, idade);
			preparedStmt.setString(3, nome);
			preparedStmt.setString(4, apelido);
			preparedStmt.setString(5, sexo);
			preparedStmt.setString(6, morada);
			preparedStmt.setBlob(7, file);
			preparedStmt.setInt(8, vencimento);
			preparedStmt.setInt(9, nif);
			preparedStmt.setInt(10, nif);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int updateMedico(Connection con, int nif, String sexo, int idade, String nome, String apelido,
			String morada, InputStream file, int vencimento) {
		PreparedStatement preparedStmt;
		String query = "UPDATE Pessoa p, Medico m"
				+ "SET p.nif = ? , p.idade = ?, p.nome = ?, p.apelido = ?, p.sexo = ?, p.morada = ?, p.image = ? "
				+ "m.vencimento = ? "
				+ "WHERE p.nif = ? and m.nif = ?";
		try {
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, nif);
			preparedStmt.setInt(2, idade);
			preparedStmt.setString(3, nome);
			preparedStmt.setString(4, apelido);
			preparedStmt.setString(5, sexo);
			preparedStmt.setString(6, morada);
			preparedStmt.setBlob(7, file);
			preparedStmt.setInt(8, vencimento);
			preparedStmt.setInt(9, nif);
			preparedStmt.setInt(10, nif);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int updateEspecialidade(Connection con, int nif, int vencimento, String especialidade) {
		PreparedStatement preparedStmt;
		String query = "UPDATE Especialidade "
				+ "SET nif = ? , idade = ?, nome = ?, apelido = ?, sexo = ?, morada = ?, image = ? " + "WHERE nif = ?";
		try {
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, nif);
			preparedStmt.setInt(2, vencimento);
			preparedStmt.setString(3, especialidade);
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

	public static int insertToConsulta(Connection con, Date data, Time hora, String especialidade, int nif_utente,
			int nif_medico) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(consultaQuery);
			preparedStmt.setDate(1, data);
			preparedStmt.setTime(2, hora);
			preparedStmt.setString(3, especialidade);
			preparedStmt.setInt(4, nif_utente);
			preparedStmt.setInt(5, nif_medico);
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

	public static int insertConsulta(Connection con, Date dataConsulta, Time horaInicio, String nome_especialidade,
			int nif_utente, int nif_medico) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(consultaQuery);
			preparedStmt.setDate(1, dataConsulta);
			preparedStmt.setTime(2, horaInicio);
			preparedStmt.setString(3, nome_especialidade);
			preparedStmt.setInt(4, nif_utente);
			preparedStmt.setInt(5, nif_medico);
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}

		Time horaFim = add30(horaInicio);

		String pessoas = String.valueOf(nif_utente) + "-";

		if (listarManchasMarcadasFim(con, dataConsulta, horaInicio)) {
			String ab = listarManchasMarcadasPessoaFim(con, dataConsulta, horaInicio);

			if (!pessoas.replace("-", "").equals(ab.replace("-", ""))) {
				pessoas += ab;
			}

			// Trocar a hora de fim para a minha hora de fim
			System.out.println("HORA INICIO IGUAL A HORA FIM");
			String query = "update ManchaHorariaMarcada set horaFim = ?, pessoas = ? where dia = ? and horaFim = ?";
			PreparedStatement preparedStmt2;
			try {
				preparedStmt2 = con.prepareStatement(query);
				preparedStmt2.setTime(1, horaFim);
				preparedStmt2.setString(2, pessoas);
				preparedStmt2.setDate(3, dataConsulta);
				preparedStmt2.setTime(4, horaInicio);
				preparedStmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		} else if (listarManchasMarcadasInicio(con, dataConsulta, horaFim)) {
			// Trocar a hora de inicio para a minha hora de inicio
			String query = "update ManchaHorariaMarcada set horaInicio = ? where dia = ? and horaInicio = ?";
			PreparedStatement preparedStmt2;
			try {
				preparedStmt2 = con.prepareStatement(query);
				preparedStmt2.setTime(1, horaInicio);
				preparedStmt2.setString(2, pessoas);
				preparedStmt2.setDate(3, dataConsulta);
				preparedStmt2.setTime(4, horaFim);
				preparedStmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;

		} else {
			PreparedStatement preparedStmt1;
			try {
				preparedStmt1 = con.prepareStatement(novaManchaQuery);
				preparedStmt1.setDate(1, dataConsulta);
				preparedStmt1.setTime(2, horaInicio);
				preparedStmt1.setTime(3, horaFim);
				preparedStmt1.setString(4, pessoas);
				preparedStmt1.setInt(5, nif_medico);
				preparedStmt1.executeUpdate();
				return 1;
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

		return 0;
	}

	public static int insertStatusConsulta(Connection con, Date dia, Time horaInit, String nome_especialidade,
			int nif_utente, int nif_medico, String status) {
		PreparedStatement preparedStmt;
		String query = "UPDATE Consulta SET statuss = ?"
				+ " WHERE nif_utente = ? and nif_medico = ? and horaInicio = ? and dataConsulta = ? and nome_especialidade = ?";
		try {
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, status);
			preparedStmt.setInt(2, nif_utente);
			preparedStmt.setInt(3, nif_medico);
			preparedStmt.setTime(4, horaInit);
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

	public static String nomePessoa(Connection con, int nif) {
		try {
			String queryCheck = "SELECT Pessoa.nome FROM Pessoa WHERE Pessoa.nif = ?";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setString(1, String.valueOf(nif));
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s.split(" ")[0];
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String[] getUtente(Connection con, int nif) {
		try {
			String queryCheck = "SELECT p.nome, p.apelido, p.idade, p.sexo, p.morada, p.image from Pessoa p WHERE nif = ?";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif);
			ResultSet rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				Blob b = rs.getBlob(6);
				if (b != null) {
					String blob = utils.Base64Converter(b.getBytes(1, (int) b.length()));
					list.add(rs.getString(1) + " " + rs.getString(2) + "=" + rs.getInt(3) + "=" + rs.getString(4) + "="
							+ rs.getString(5) + "=" + blob);
				}
			}
			return list.toArray(new String[list.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static int getIDconsulta(Connection con, Date data, Time hora, String nome_especialidade, int nif_medico) {
		String queryCheck = "SELECT Consulta.idConsulta FROM Consulta WHERE dataConsulta = ?" + "AND "
				+ "horaInicio = ?" + "AND nome_especialidade = ?" + "AND nif_medico = ?";
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
			String queryCheck = "SELECT Consulta.dataConsulta, Consulta.horaInicio, Consulta.nif_medico, "
					+ "Consulta.nome_especialidade from Consulta WHERE nif_utente = ?";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setInt(1, nif);
			ResultSet rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getDate(1) + "=" + rs.getTime(2) + "=" + rs.getInt(3) + "=" + rs.getString(4));
			}
			return list.toArray(new String[list.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String[] procurarMedicos(Connection con) {
		try {
			String query = "SELECT m.nif,m.nome,m.apelido,m.idade,m.sexo,m.morada"
					+ ",u.vencimento,u.nome_especialidade,m.image"
					+ " from Pessoa m CROSS JOIN Medico u ON m.nif = u.nif";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				Blob b = rs.getBlob(9);
				if (b != null) {
					String blob = utils.Base64Converter(b.getBytes(1, (int) b.length()));
					list.add(rs.getInt(1) + "=" + rs.getString(2) + " " + rs.getString(3) + "=" + rs.getInt(4) + "="
							+ rs.getString(5) + "=" + rs.getString(6) + "=" + rs.getInt(7) + "=" + rs.getString(8) + "="
							+ blob);
				}
			}
			return list.toArray(new String[list.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String[] listarMedicosbyEsp(Connection con, String nome_especialidade) {
		try {
			List<String> list = new ArrayList<String>();
			String queryCheck = "SELECT Pessoa.nome,Pessoa.apelido,Pessoa.nif,Pessoa.idade "
					+ "FROM ((Medico INNER JOIN Especialidade ON Medico.nome_especialidade = Especialidade.nome)"
					+ "INNER JOIN Pessoa ON Medico.nif = Pessoa.nif)" + "WHERE (Medico.nome_especialidade = ?)";
			PreparedStatement ps = con.prepareStatement(queryCheck);
			ps.setString(1, nome_especialidade);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString(1) + " " + resultSet.getString(2) + "=" + resultSet.getInt(3) + "="
						+ resultSet.getInt(4));
			}
			return list.toArray(new String[list.size()]);
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

	public static String[] listarUtenteNome(Connection con) {
		try {
			String query = "SELECT Pessoa.nome,Pessoa.apelido"
					+ " from Pessoa RIGHT JOIN Utente ON Pessoa.nif = Utente.nif";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString(1) + " " + rs.getString(2));
			}
			return list.toArray(new String[list.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String[] procurarUtentes(Connection con) {
		try {
			String query = "SELECT Pessoa.nif,Pessoa.nome,Pessoa.apelido,Pessoa.idade,Pessoa.sexo,Pessoa.morada"
					+ ",Pessoa.image" + " from Pessoa RIGHT JOIN Utente ON Pessoa.nif = Utente.nif";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				Blob b = rs.getBlob(7);
				if (b != null) {
					String blob = utils.Base64Converter(b.getBytes(1, (int) b.length()));
					list.add(rs.getInt(1) + "=" + rs.getString(2) + " " + rs.getString(3) + "=" + rs.getInt(4) + "="
							+ rs.getString(5) + "=" + rs.getString(6) + "=" + blob);
				}
			}
			return list.toArray(new String[list.size()]);
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

	public static boolean listarManchasMarcadasFim(Connection con, Date data, Time fim) {
		try {
			String query = "SELECT ManchaHorariaMarcada.horaFim FROM ManchaHorariaMarcada WHERE horaFim = ? AND dia = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setTime(1, fim);
			ps.setDate(2, data);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			boolean aav = !s.trim().equals("");
			return aav;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public static String[] listarManchasMarcadasPessoas(Connection con, Date data, Time horaI) {
		try {
			String query = "SELECT ManchaHorariaMarcada.pessoas FROM ManchaHorariaMarcada WHERE dia = ? AND horaInicio = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDate(1, data);
			ps.setTime(2, horaI);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s.trim().split("-");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String[] listarManchasMarcadasHoras(Connection con, Date data) {
		try {
			String query = "SELECT ManchaHorariaMarcada.horaInicio FROM ManchaHorariaMarcada WHERE dia = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDate(1, data);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s.trim().split(" ");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String listarManchasMarcadasPessoaFim(Connection con, Date data, Time fim) {
		try {
			String query = "SELECT ManchaHorariaMarcada.pessoas FROM ManchaHorariaMarcada WHERE horaFim = ? AND dia = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setTime(1, fim);
			ps.setDate(2, data);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String listarManchasMarcadasPessoaInicio(Connection con, Date data, Time fim) {
		try {
			String query = "SELECT ManchaHorariaMarcada.pessoas FROM ManchaHorariaMarcada WHERE horaInicio = ? AND dia = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setTime(1, fim);
			ps.setDate(2, data);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static boolean listarManchasMarcadasInicio(Connection con, Date data, Time fim) {
		try {
			String query = "SELECT ManchaHorariaMarcada.horaInicio FROM ManchaHorariaMarcada WHERE horaInicio = ? AND dia = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setTime(1, fim);
			ps.setDate(2, data);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			boolean aav = !s.trim().equals("");
			return aav;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public static String[] listarDatasMedico(Connection con, int nif) {
		try {
			String queryCheck = "SELECT Consulta.dataConsulta from Consulta WHERE nif_medico = ?";
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

	public static Time add30(Time h1) {

		String[] hora = h1.toString().split(":");

		if (hora[1].equals("30")) {
			int h = Integer.parseInt(hora[0]) + 1;
			hora[0] = String.valueOf(h);
			hora[1] = "00";
		} else if (hora[1].equals("00")) {
			hora[1] = "30";
		}

		return timeFormater(hora[0], hora[1]);
	}

	public static Time remove30(Time h1) {

		String[] hora = h1.toString().split(":");

		if (hora[1].equals("30")) {
			hora[1] = "00";
		} else if (hora[1].equals("00")) {
			int h = Integer.parseInt(hora[0]) - 1;
			hora[0] = String.valueOf(h);
			hora[1] = "30";
		}

		return timeFormater(hora[0], hora[1]);
	}
	
	public static String[] listarManchasHorarias (Connection con, int nif_medico) {
		try {
			String query = "SELECT Manchahoraria.idManchaHoraria, ManchaHoraria.dia, ManchaHoraria.horaInicio, ManchaHoraria.horaFim"
					+ " FROM ManchaHoraria WHERE nif_medico = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, nif_medico);
			ResultSet resultSet = ps.executeQuery();
			List<Sqltojava> list = Sqltojava.formTable(resultSet);
			String s = Sqltojava.getList(con, list);
			return s.trim().split(" ");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static boolean atualizarMancha (Connection con, Date dia, Time horaInicio, int nif_medico) {
		try {
			String query = "UPDATE Manchahoraria  SET dia = ?"
					+ " WHERE nif_medico = ? AND horaInicio = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDate(1, dia);
			ps.setInt(2, nif_medico);
			ps.setTime(3, horaInicio);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public static boolean atualizarMancha (Connection con, Time horaInicioNew, Date data, int nif_medico) {
		try {
			String query = "UPDATE Manchahoraria  SET horaInicio = ?, horaFim = ?"
					+ " WHERE nif_medico = ? AND dia = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setTime(1, horaInicioNew);
			ps.setTime(2, Javatosql.add30(horaInicioNew));
			ps.setInt(3, nif_medico);
			ps.setDate(4, data);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public static String[] listarStatusConsulta(Connection con, int nif_medico) {
		try {
			String query = "SELECT p.nif, p.nome,p.apelido,s.dataConsulta,s.horaInicio,s.nome_especialidade,s.statuss FROM "
					+ "Consulta s INNER JOIN Pessoa p ON s.nif_utente = p.nif " + "WHERE s.nif_medico = ?"
					+ " ORDER BY s.dataConsulta DESC";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, nif_medico);
			ResultSet rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getInt(1) + "=" + rs.getString(2) + " " + rs.getString(3) + "=" + rs.getDate(4) + "="
						+ rs.getTime(5) + "=" + rs.getString(6) + "=" + rs.getString(7));
			}
			return list.toArray(new String[list.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
}
