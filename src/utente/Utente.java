package utente;

import java.sql.Connection;
import java.sql.DriverManager;

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
	
	public void marcarConsulta() {
		
	}
	
	public void cancelarConsulta() {
		
	}
	
	public void remarcarConsulta() {
		
	}
	
	public void listarConsultas() {
		
	}

}
