package utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Period;
import java.util.Base64;
import java.util.Calendar;

import javax.sql.rowset.serial.SerialBlob;

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
		Time t = Javatosql.timeFormater("0", "30");
		Calendar c = Calendar.getInstance();
		c.setTime(t);
		if (times == 0) {
			return Javatosql.timeFormater("0", "0");
		}
		while (times > 1) {
			c.add(Calendar.MINUTE, 30);
			times -= 1;
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
		} else if (idade > 18 && sexo.equalsIgnoreCase("F")) {
			for (EspecialidadesHomem i : EspecialidadesHomem.values()) {
				if (especialidade.equals(i.toString()))
					return false;
			}
			return true;
		} else {
			return true;
		}
	}

	public static boolean testDataBetween(Date start, Date end, Date d) {
		return d.after(start) && d.before(end);
	}

	public static boolean checkifDayWeek(Date dia) {
		Calendar c = Calendar.getInstance();
		c.setTime(dia);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1 || dayOfWeek == 7)
			return false;
		return true;
	}

	public static int calculateAge(String age) {
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(1960, 10, 1);

		Period p = Period.between(birthday, today);

		return p.getYears();
	}

	public static String Base64Converter(byte[] arr) {
		return Base64.getEncoder().encodeToString(arr);
	}
	
	public static Blob Base64Decoder(String str) {
		try {
			byte[] b = Base64.getDecoder().decode(str.getBytes("UTF-8"));
			return new SerialBlob(b);
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
