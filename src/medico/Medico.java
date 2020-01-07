package medico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Time;

import utils.Javatosql;
import utils.databaseAcess;

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
	
	public int marcarStatusConsulta(Date dia, Time horaInit, String nome_especialidade, int nif_utente, String status) {
        return Javatosql.insertStatusConsulta(con, dia, horaInit, nome_especialidade, nif_utente, nif,
                status);
    }

    public String[] listarStatusConsulta() {
        return Javatosql.listarStatusConsulta(con, nif);
    }
	
	public String[] listarManchasHorarias () {
		return Javatosql.listarManchasHorarias(this.con, this.nif);
	}
	
	public String getEspecialidade () {
		return Javatosql.getEspecialidade(this.con, this.nif);
	}
	
	public String[] listarDatas() {
		return Javatosql.listarDatasMedico(this.con, this.nif);
	}
	
	public Connection getCon() {
		return this.con;
	}
	
	public String getGender () {
		return Javatosql.getSex(this.con, this.nif);
	}
	
	public String getNome () {
		return Javatosql.nomePessoa(this.con, this.nif);
	}
	
	public String[] listarManchasHorariasMarcadas (Date data) {
		return Javatosql.listarManchasMarcadasHoras(con, data);
	}
	
	public String[] listarPessoasManchas (Date data, Time hI) {
		return Javatosql.listarManchasMarcadasPessoas(con, data, hI);
	}
	
	public int criarMancha(Date dia, Time horaInit) {
		Time horaFim = Javatosql.add30(horaInit);
		return Javatosql.insertToMancha(con, dia, horaInit, horaFim, especialidade, nif);
	}
	
	public int removerMancha(int idManchaHoraria) {
		return Javatosql.removeMancha(con, idManchaHoraria);
	}
	
	public boolean atualizarMancha (Date dia, Time hora) {
		return Javatosql.atualizarMancha(this.con, dia, hora, this.nif);
	}
	
	public boolean atualizarMancha (Time horaInicio, Date data) {
		return Javatosql.atualizarMancha(this.con, horaInicio, data, this.nif);
	}
	
	public int atualizarMancha(Date dia, Time horaInit, Time horaFim, Date newDia, Time newHoraInit, Time newHoraFim,
			String nome_especialidade) {
		int idMancha = Javatosql.getIDmancha(con, dia, horaInit, nome_especialidade, nif);
		int idNewMancha = Javatosql.getIDmancha(con, newDia, newHoraInit, nome_especialidade, nif);
		if(idMancha!=-1 && idNewMancha == -1) {
			removerMancha(idMancha);
			return criarMancha(newDia, newHoraInit);
		}
		return 0;
	}
	
	public String getMedicoImagem() {
		return Javatosql.getUtente(con, nif)[0].split("=")[4];
	}
		
}
