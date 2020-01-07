	package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	
	public static Time timeFormater(String t) {
		SimpleDateFormat s = new SimpleDateFormat("H:mm:ss");
		Long ms = null;
		try {
			ms = s.parse(t).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Time(ms);
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

	public static InputStream Str2InputStream(String str) {
	    return new ByteArrayInputStream(str.getBytes());
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
	
	public static Date dateFormater(String str) {
		String[] div = str.split("-");
		return Javatosql.dateFormater(Integer.parseInt(div[0]), Integer.parseInt(div[1]), Integer.parseInt(div[2]));
	}

	public static String[] importarUtente(String path) {
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Dados_Utente");
			
			List<String> dados = new ArrayList<String>();
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String nif = eElement.getElementsByTagName("nif").item(0).getTextContent();
					String nome = eElement.getElementsByTagName("nome").item(0).getTextContent();
					String idade = eElement.getElementsByTagName("idade").item(0).getTextContent();
					String sexo = eElement.getElementsByTagName("sexo").item(0).getTextContent();
					String morada = eElement.getElementsByTagName("morada").item(0).getTextContent();
					String img = eElement.getElementsByTagName("img").item(0).getTextContent();
					String[] nomes = nome.split(" ");
					dados.add(nif + "=" + idade + "=" + sexo + "=" + nomes[0] + "=" + nomes[1] + "="
							+ morada + "=" + img);
				}
			}
			NodeList conList = doc.getElementsByTagName("Consulta");
			for (int temp = 0; temp < conList.getLength(); temp++) {

				Node nNode = conList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
				
					String Data_da_Consulta = eElement.getElementsByTagName("Data_da_Consulta").item(0).getTextContent();
					String Hora_Inicio = eElement.getElementsByTagName("Hora_Inicio").item(0).getTextContent();
					String Hora_Fim = eElement.getElementsByTagName("Hora_Fim").item(0).getTextContent();
					String Nome_Especialidade = eElement.getElementsByTagName("Nome_Especialidade").item(0).getTextContent();
					String nif_utente = eElement.getElementsByTagName("nif_utente").item(0).getTextContent();
					String nif_medico = eElement.getElementsByTagName("nif_medico").item(0).getTextContent();
					dados.add(Data_da_Consulta + "=" + Hora_Inicio + "=" + Hora_Fim + "=" + Nome_Especialidade + "=" 
					+ nif_utente + "=" + nif_medico);
				}
			}
		return dados.toArray(new String[dados.size()]);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void exportarUtente(int nif, String nome, int idade, String sexo, String morada, String img,
			String[] consultas) {
		String path = System.getProperty("user.home") + "/Desktop";
		File f = new File(path, nif + "_Dados_Consultas.xml");
		if (f.exists()) {
			f.delete();
		}
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			Element rootElement = document.createElement("Utente");
			document.appendChild(rootElement);

			Element dados = document.createElement("Dados_Utente");
			rootElement.appendChild(dados);

			Element nif_ = document.createElement("nif");
			nif_.appendChild(document.createTextNode(String.valueOf(nif)));

			Element nome_ = document.createElement("nome");
			nome_.appendChild(document.createTextNode(nome));

			Element idade_ = document.createElement("idade");
			idade_.appendChild(document.createTextNode(String.valueOf(idade)));

			Element sexo_ = document.createElement("sexo");
			sexo_.appendChild(document.createTextNode(sexo));

			Element morada_ = document.createElement("morada");
			morada_.appendChild(document.createTextNode(morada));

			Element img_ = document.createElement("img");
			img_.appendChild(document.createTextNode(img));

			dados.appendChild(nif_);
			dados.appendChild(nome_);
			dados.appendChild(idade_);
			dados.appendChild(sexo_);
			dados.appendChild(morada_);
			dados.appendChild(img_);

			Element consultas_ = document.createElement("Consultas");
			rootElement.appendChild(consultas_);
			for (String con : consultas) {

				
				Element consulta_ = document.createElement("Consulta");
				String[] s = con.split("=");

				Element dataConsulta_ = document.createElement("Data_da_Consulta");
				dataConsulta_.appendChild(document.createTextNode(s[0]));

				Element horaInicio_ = document.createElement("Hora_Inicio");
				horaInicio_.appendChild(document.createTextNode(s[1]));

				Element horafim_ = document.createElement("Hora_Fim");
				horafim_.appendChild(document.createTextNode(""));

				Element nome_especialidade_ = document.createElement("Nome_Especialidade");
				nome_especialidade_.appendChild(document.createTextNode(s[3]));

				Element nif_utente = document.createElement("nif_utente");
				nif_utente.appendChild(document.createTextNode(String.valueOf(nif)));

				Element nif_medico_ = document.createElement("nif_medico");
				nif_medico_.appendChild(document.createTextNode(s[2]));

				consulta_.appendChild(dataConsulta_);
				consulta_.appendChild(horaInicio_);
				consulta_.appendChild(horafim_);
				consulta_.appendChild(nome_especialidade_);
				consulta_.appendChild(nif_utente);
				consulta_.appendChild(nif_medico_);

				consultas_.appendChild(consulta_);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(path, nif + "_Dados_Consultas.xml"));
			transformer.transform(domSource, streamResult);
			System.out.println("Exported to -> " + path + nif + "_Dados_Consultas.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
