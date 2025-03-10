package aplicacion_bbdd;

import java.sql.Connection;
import java.util.Scanner;

public class Jugadores {

	public static String[] cogerDatos(Scanner sc) {
		boolean hayDatos = false;
		String[] tokens = null;
		System.out.println("");
		do {
			Jugadores.printColumnas();
			System.out.print("Introduce los datos entre comillas simples(fin para salir): ");
			String input = sc.nextLine();
			if (input.equals("fin"))
				return null;
			tokens = Metodos.tokenize(input);
			
			if (!Jugadores.validarTokens(tokens)) {
				System.out.println("Formato incorrecto");
				continue;
			}
			hayDatos = true;
		} while (!hayDatos);

		return tokens;
	}

	private static void printColumnas() {
		System.out.println("Columns:\n" + "codigo -> int(11) PK\n" + "Nombre -> varchar(30)\n"
				+ "Procedencia -> varchar(20)\n" + "Altura -> varchar(4)\n" + "Peso -> int(11)\n"
				+ "Posicion -> varchar(5)\n" + "Nombre_equipo -> varchar(20)");
	}

	public static String[] cogerCamposClaves() {
		String[] campos = new String[1];
		campos[0] = "codigo";
		return campos;
	}

	public static String[] cogerNombresDeColumnas() {
		String[] c = new String[7];
		c[0] = "codigo";
		c[1] = "Nombre";
		c[2] = "Procedencia";
		c[3] = "Altura";
		c[4] = "Peso";
		c[5] = "Posicion";
		c[6] = "Nombre_equipo";
		return c;
	}

	private static boolean validarTokens(String[] tokens) {
		if (tokens.length != 7)
			return false;
		if (!Metodos.isInt(tokens[0]))
			return false;
		if (tokens[1].length() > 30)
			return false;
		if (tokens[2].length() > 20)
			return false;
		if (tokens[3].length() > 4)
			return false;
		if (!Metodos.isInt(tokens[4]))
			return false;
		if (tokens[5].length() > 5)
			return false;
		if (tokens[6].length() > 20)
			return false;

		return true;
	}

	private static boolean validarCampoClave(String dato) {
		if (dato.length() > 11 || dato.length() == 0)
			return false;

		for (int i = 0; i < dato.length(); i++) {
			char letra = dato.charAt(i);
			if (!Character.isDigit(letra)) {
				return false;
			}
		}
		return true;
	}

	public static void eliminarDatos(Connection connection, Scanner sc) {
		System.out.print(
				"\nElimnar datos de la tabla 'Jugadores'\n" + "Indica el codigo formato 'int(11)': ");
		String clave = sc.nextLine();

		if (validarCampoClave(clave)) {
			String consulta = "delete from jugadores where codigo = " + clave;
			Metodos.ejecutarConsultaDeAccion(connection, consulta);
			System.out.println("Eliminación de datos completada.");
		} else {
			System.out.println("Formato no válido");
		}
	}
}
