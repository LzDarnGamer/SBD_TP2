package utils;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Iterator;

public class utils {

	private enum EspecialidadesCriancas {
		E1("Psiquiatria da Infancia e da Adolescencia"), E2("Pediatria"),;

		private final String text;

		EspecialidadesCriancas(final String text) {
			this.text = text;
		}

		public String toString() {
			return text;
		}

	}

	private enum EspecialidadesHomem {
		E3("Urologia");

		private final String text;

		EspecialidadesHomem(final String text) {
			this.text = text;
		}

		public String toString() {
			return text;
		}

	}

	private enum EspecialidadesMulher {
		E1("Genecologia"), E2("Obstetricia");

		private final String text;
		
		EspecialidadesMulher(final String text) {
			this.text = text;
		}

		public String toString() {
			return text;
		}

	}
	public static Time multiply30by(int times) {
		Time t = Javatosql.timeFormater("0","30");
		Calendar c = Calendar.getInstance();
		c.setTime(t);
		if(times==0) {
			return Javatosql.timeFormater("0","0");
		}
		while(times>1) {
			c.add(Calendar.MINUTE, 30);
			times-=1;
		}
		t.setTime(c.getTimeInMillis());
		return t;
	}
	public static Time add30Min(Time time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.MINUTE, 30);
		time.setTime(c.getTimeInMillis());
		return time;
	}

	public static boolean testEspecialidades(int idade, String sexo, String especialidade) {
		if (idade > 18) {
			for (EspecialidadesCriancas i : EspecialidadesCriancas.values()) {
				if (especialidade.equals(i.toString()))
					return false;
			}
			return true;
		} else if (idade > 18 && sexo.equalsIgnoreCase("M")) {
			for (EspecialidadesMulher i : EspecialidadesMulher.values()) {
				if (especialidade.equals(i.toString()))
					return false;
			}
			return true;
		}else if(idade > 18 && sexo.equalsIgnoreCase("F")) {
			for (EspecialidadesHomem i : EspecialidadesHomem.values()) {
				if (especialidade.equals(i.toString()))
					return false;
			}
			return true;
		}
		else {
			return true;
		}
	}
	
	public static boolean testDataBetween(Date start, Date end, Date d) {
	    return d.after(start) && d.before(end);
	}
}
