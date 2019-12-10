package Main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Arrays;

import admin.Admin;
import medico.Medico;
import utente.Utente;
import utils.Javatosql;
import utils.databaseAcess;
import utils.utils;

public class Teste {

	private Connection con;

	public Teste() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Clinica?" + "useLegacyDatetimeCode=false&serverTimezone=Europe/Lisbon",
					databaseAcess.USER, databaseAcess.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}

	public static void main(String[] args) throws SQLException {
		Teste t = new Teste();

		Connection con = t.getCon();
		Admin admin = new Admin();

//		Javatosql.insertToEspecialidade(t.getCon(), "Cardiologia");
//		Javatosql.insertToEspecialidade(t.getCon(), "Gastroentrologia");
//		Javatosql.insertToEspecialidade(t.getCon(), "Otorrino");
//		admin.createUtente(234567891, "M", 28, "1", "1", "teste1");
//		admin.createUtente(123123123, "F", 44, "2", "2", "teste2");
//		admin.createUtente(412412124, "M", 29, "3", "3", "teste3");
//		admin.createUtente(474574574, "F", 19, "4", "4", "teste4");
//		admin.createUtente(876845632, "M", 22, "5", "5", "teste5");
//		admin.createUtente(892375928, "F", 43, "Mario", "Ramos", "Rua da Constança n3");
//		
//
//		admin.createMedico(123123123, "M", 22, "1", "1", "teste1", 1800, "Cardiologia");
//		admin.createMedico(123123124, "F", 55, "2", "2", "teste2", 900, "Cardiologia");
//		admin.createMedico(445346463, "F", 38, "3", "3", "teste3", 1200, "Cardiologia");
//		admin.createMedico(123123455, "M", 41, "4", "4", "teste4", 2100, "Gastroentrologia");

//		System.out.println(Arrays.toString(admin.procurarUtente()));
//		int p = Javatosql.insertToPessoa(t.getCon(), 123456789, 49, "M", "Shun", "Wang", "teste");
//		int p1 = Javatosql.insertToPessoa(t.getCon(), 987654321, 49, "F", "123", "321", "578");

//		System.out.println(Arrays.deepToString(admin.listarMedicos("Cardiologia")));
//		int medic = Javatosql.insertToMedico(t.getCon(), 987654321, 1500, "Cardiologia");
		Utente u = new Utente(892375928);
		Medico m = new Medico(123123123);

		Date dia = Javatosql.dateFormater(2019, 12, 12);
		Time horaInit = Javatosql.timeFormater("18", "30");
//		m.criarMancha(dia, horaInit, utils.add30Min((Time)horaInit.clone()));

		Date dia1 = Javatosql.dateFormater(2019, 12, 13);
		Time horaInit1 = Javatosql.timeFormater("12", "30");
//		m.criarMancha(dia1, horaInit1, utils.add30Min((Time) horaInit1.clone()));

//		m.marcarFalta(892375928, horaInit, utils.add30Min((Time) horaInit.clone()), dia);
//		u.marcarConsulta(dia, horaInit, "Cardiologia", 123123123);
//		u.remarcarConsulta(dia, horaInit, dia1, horaInit1, "Cardiologia", 123123123);

//
//		Date dia2 = Javatosql.dateFormater(2019, 12, 14);
//		Time horaInit2 = Javatosql.timeFormater("12", "30");
//		Time horaFim2 = Javatosql.timeFormater("13", "00");
//		int mancha2 = Javatosql.insertToMancha(t.getCon(), dia2, horaInit2, horaFim1, "Gastroentrologia", 123123124);
//		
//		System.out.println(u.marcarConsulta(con, dia2, horaInit2, "Cardiologia", 987654321));
//		u.listarConsultas();
//		System.out.println(u.remarcarConsulta(dia2, horaInit2, dia1, horaInit1, "Cardiologia", 987654321));
//		u.listarConsultas();

//	    Calendar c = Calendar.getInstance();
//	    java.util.Date date = new Date(c.getTimeInMillis());
//	    c.setTime(date);
//	    c.setFirstDayOfWeek(1);
//	    int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
//	    c.add(Calendar.DATE, -i - 14);
//	    java.util.Date start = c.getTime();
//	    c.add(Calendar.DATE, 7);
//	    java.util.Date end =  c.getTime();
//	    System.out.println(start + " - " + end);

//		System.out.println(Arrays.toString(Javatosql.listarDiaHora(con, "Cardiologia")));

//		String[] a = Javatosql.listarDiaHora(con, "Cardiologia")[0].split(" ");
//		String[] b = a[0].split("-");
//		Date d = Javatosql.dateFormater(Integer.parseInt(b[0]), Integer.parseInt(b[1]), Integer.parseInt(b[2]));
//		System.out.println(d);
		Date init = Javatosql.dateFormater(2014, 12, 31);
		Date fim = Javatosql.dateFormater(2015, 02, 01);
//		System.out.println(admin.listarUtentesFaltas(init, fim, 892375928).size());

//		admin.listarEspecialidadeStatus(init, fim, "Cardiologia", "Remarcacoes");
		
		admin.listarMedicoTempoPerdido(init, fim, 987654321);

	}

}
