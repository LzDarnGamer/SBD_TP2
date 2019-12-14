package admin;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.Javatosql;
import utils.databaseAcess;
import utils.utils;

public class Admin {
	Connection con;

	public Admin() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Clinica?" + "useLegacyDatetimeCode=false&serverTimezone=Europe/Lisbon",
					databaseAcess.USER, databaseAcess.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createMedico(int nif, String sexo, int idade, String nome, String apelido, String morada,
			int vencimento, String especialidade, InputStream image) {
		if (Javatosql.insertToPessoa(con, nif, idade, sexo, nome, apelido, morada, image) == 1) {
			Javatosql.insertToMedico(con, nif, vencimento, especialidade);
		}

	}
	
	public void createUtente(int nif, String sexo, int idade, String nome, String apelido, String morada, 
			InputStream image) {
		if (Javatosql.insertToPessoa(con, nif, idade, sexo, nome, apelido, morada, image) == 1) {
			Javatosql.insertToUtente(con, nif);
		}
	}
	
	public void updateUtente(int nif, String sexo, int idade, String nome, String apelido, String morada, 
			String file) {
		Javatosql.updateUtente(con, nif, sexo, idade, nome, apelido, morada, utils.Base64Decoder(file));
	}
	public void updateUtente(int nif, String sexo, int idade, String nome, String apelido, String morada, 
			InputStream file) {
		Javatosql.updateUtente(con, nif, sexo, idade, nome, apelido, morada, file);
	}

	public String[] listarUtenteNome() {

		return Javatosql.listarUtenteNome(con);
	}
	public String[] listarUtente() {
		return Javatosql.procurarUtentes(con);
	}
	
	

	public String[] listarMedicos(String nome_especialidade) {
		return Javatosql.listarMedicosbyEsp(con, nome_especialidade);
	}

	public List<String> listarEspecialidadeStatus(Date init, Date fim, String nome_especialidade, String status) {
		String result = Javatosql.listarStatusEspecialidade(con, nome_especialidade, status);
		if(result.isEmpty()){
			return null;
		}

		String[] resultado = result.split("\n");
		List<String> lista = new ArrayList<String>();
		for (int i = 0 ; i < resultado.length; i++) {
			String[] split = resultado[i].split(" ")[2].trim().split("-");
			Date date = Javatosql.dateFormater(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
					Integer.parseInt(split[2]));
			if (utils.testDataBetween(init, fim, date))
				lista.add(resultado[i]);
		}

		return lista;
	}

	public List<String> listarUtentesStatus(Date init, Date fim, int numFaltas, String status) {
		List<String> stats = new ArrayList<String>(Arrays.asList(Javatosql.listarTabelaStatus(con, status)));
		List<String> found = new ArrayList<String>();

		List<String> output = new ArrayList<String>();
		StringBuilder s = new StringBuilder();
		for (String string : stats) {
			String[] help = string.split(" ");
			String[] split = help[2].split("-");
			Date date = Javatosql.dateFormater(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
					Integer.parseInt(split[2]));
			if (!utils.testDataBetween(init, fim, date)) {
				found.add(string);
			}
		}
		stats.removeAll(found);

		for (int i = 0; i < stats.size(); i++) {
			s.append(stats.get(i) + "\n");
		}

		String[] uni = new String[stats.size()];
		for (int i = 0; i < stats.size(); i++) {
			uni[i] = stats.get(i).split(" ")[0];
		}

		String[] unique = Arrays.stream(uni).distinct().toArray(String[]::new);

		for (int i = 0; i < unique.length; i++) {
			String[] help = s.toString().split("\n");
			int occurs = s.toString().split(unique[i]).length - 1;
			if (occurs >= numFaltas) {
				for (String string : help) {
					if (string.contains(unique[i])) {
						output.add(string);
					}
				}
			}
		}

		return output;
	}
	
	public String[] getListaEspecialidades() {
		return Javatosql.listarEspecialidades(con);
	}
	
	public Integer[] getListaMedicos() {
		return Javatosql.listarMedicos(con);
	}
	
	public String listarMedicoTempoPerdido(Date init, Date fim, int nif_medico) {
		int value = Javatosql.listarTempoPerdido(con, nif_medico, init, fim);
		return utils.multiply30by(value).toString();
	}
	
	
	public Connection getCon() {
		return con;
	}
}
