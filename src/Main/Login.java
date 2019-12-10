package Main;

import utils.Javatosql;

public class Login {

	public Teste t;

	public Login() {
		t = new Teste();
	}

	/**
	 * Verificar se username existe no SQL com a password
	 * 
	 * @param username
	 * @param password
	 * @return valor boleano (valido/invalido)a
	 */
	public String Entrar(int nif) {

		if (nif == 123465789) {
			return "Administrador.jsp";
		} else if (Javatosql.checkUtente(t.getCon(), nif)) {
			return "Utente.jsp";
		} else if (Javatosql.checkMedico(t.getCon(), nif)) {
			return "Medico.jsp";
		} else {
			return null;
		}
	}

}