package utente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Time;

import utils.Javatosql;
import utils.databaseAcess;

public class Utente {
	Connection con;
	private final int nif;
	
	public Utente(int nif) {
		this.nif = nif;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinica?useTimezone=true&serverTimezone=UTC",
					databaseAcess.USER, databaseAcess.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int marcarConsulta(Date data, Time hora, String especialidade, int nif_medico) {
		return Javatosql.insertToConsulta(con, data, hora, especialidade, nif, nif_medico);
	}
	
	public void cancelarConsulta(int idConsulta, int nif) {
		
	}
	
	public void remarcarConsulta() {
		
	}
	
	public void listarConsultas() {
		
	}

}
