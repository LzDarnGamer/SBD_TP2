package medico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Time;

import utils.Javatosql;
import utils.databaseAcess;
import utils.utils;

public class Medico {
	private Connection con;
	private final String especialidade;
	private final int nif;
	
	public Medico(int nif) {
		this.nif = nif;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinica?"
					+ "useLegacyDatetimeCode=false&serverTimezone=Europe/Lisbon",
					databaseAcess.USER, databaseAcess.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		especialidade = Javatosql.getEspecialidade(con, nif);
	}
	
	public int criarMancha(Date dia, Time horaInit, Time horaFim) {
		if(utils.checkifDayWeek(dia))
			return Javatosql.insertToMancha(con, dia, horaInit, horaFim, especialidade, nif);
		return -1;
	}
		
	public int removerMancha(int idManchaHoraria) {
		return Javatosql.removeMancha(con, idManchaHoraria);
	}
	public int atualizarMancha(Date dia, Time horaInit, Time horaFim, Date newDia, Time newHoraInit, Time newHoraFim,
			String nome_especialidade) {
		int idMancha = Javatosql.getIDmancha(con, dia, horaInit, nome_especialidade, nif);
		int idNewMancha = Javatosql.getIDmancha(con, newDia, newHoraInit, nome_especialidade, nif);
		if(idMancha!=-1 && idNewMancha == -1) {
			removerMancha(idMancha);
			return criarMancha(newDia, newHoraInit, newHoraFim);
		}
		return 0;
	}
	
	public String[] listarConsultas(Date data) {
		
		return null;
	}
	
	public String[] listarConsultas(Time horaInit, Time horaFim) {
		
		return null;
	}
	
	
	public int marcarFalta(int nif_utente, Time horaInit, Time horaFim, Date dia) {
		return Javatosql.insertFalta(con, dia, horaInit, especialidade, horaFim, nif_utente, nif);
	}
	
	public int marcarPresenca(int nif_utente, Time horaInit, Time horaFim, Date dia) {
		return Javatosql.insertPresenca(con, dia, horaInit, especialidade, horaFim, nif_utente, nif);
	}
	
}
