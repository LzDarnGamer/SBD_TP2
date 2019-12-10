package utente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;

import utils.Javatosql;
import utils.Sqltojava;
import utils.databaseAcess;
import utils.utils;

public class Utente {
	Connection con;
	private final int nif;
	private final int idade;
	private final String sexo;

	public Utente(int nif) {
		this.nif = nif;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinica?"
					+ "useLegacyDatetimeCode=false&serverTimezone=Europe/Lisbon",
					databaseAcess.USER, databaseAcess.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.idade = Javatosql.getAge(con, nif);
		this.sexo = Javatosql.getSex(con, nif);
	}

	public int marcarConsulta(Date data, Time hora, String nome_especialidade, int nif_medico) {
		int id_ManchaHoraria = Javatosql.getIDmancha(con, data, hora, nome_especialidade, nif_medico);
		System.out.println(id_ManchaHoraria);
		if (utils.testEspecialidades(idade, sexo, nome_especialidade)) {
			if (id_ManchaHoraria == -1) {
				System.out.println("Mancha horária inexistente");
				return 0;
			}
				

			if (Javatosql.removeMancha(con, id_ManchaHoraria) == 0) {
				System.out.println("Mancha horária inexistente");
				return 0;
			}
			return Javatosql.insertConsulta(con, data, hora, nome_especialidade, nif, nif_medico);
		}
		System.out.println("Idade/Género não estão de acordo com a consulta");
		return 0;

	}

	public int cancelarConsulta(Date data, Time hora, String nome_especialidade, int nif_medico) {

		int id_Consulta = Javatosql.getIDconsulta(con, data, hora, nome_especialidade, nif_medico);
		if (id_Consulta == -1) {
			return 0;
		}
		Javatosql.insertToMancha(con, data, hora, hora, nome_especialidade, nif_medico);
		return Javatosql.removeConsulta(con, id_Consulta);
	}

	public int remarcarConsulta(Date data, Time hora, Date newData, Time newTime, String nome_especialidade,
			int nif_medico) {
		int id_ManchaHoraria = Javatosql.getIDmancha(con, newData, newTime, nome_especialidade, nif_medico);
		int id_Consulta = Javatosql.getIDconsulta(con, data, hora, nome_especialidade, nif_medico);

		if (id_Consulta == -1) {
			System.out.println("Consulta atual não existe");
			return 0;
		}
		if (utils.testEspecialidades(idade, sexo, nome_especialidade)) {
			if (id_ManchaHoraria == -1) {
				System.out.println("Mancha horária inexistente");
				return 0;

			}
			if (Javatosql.removeMancha(con, id_ManchaHoraria) == 0) {
				System.out.println("Mancha horária inexistente");
				return 0;
			}
			Javatosql.removeConsulta(con, id_Consulta);
			Javatosql.insertConsulta(con, newData, newTime, nome_especialidade, nif, nif_medico);
			return Javatosql.insertRemarcacao(con, newData, nome_especialidade, newTime, utils.add30Min(newTime), nif,
					nif_medico);
		}
		return 0;
	}

	public String[] listarConsultas() {
		return Javatosql.listarConsultas(con, nif);

	}
	
	public Connection getCon() {
		return con;
	}
}
