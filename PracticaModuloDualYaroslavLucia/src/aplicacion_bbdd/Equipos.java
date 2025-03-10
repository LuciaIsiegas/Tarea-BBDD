package aplicacion_bbdd;

import java.sql.Connection;
import java.util.Scanner;

public class Equipos {

	public static String[] cogerDatos(Scanner sc) {
		boolean hayDatos = false;
		String[] tokens = null;
		System.out.println("");
		do {
			Equipos.printColumnas();
			System.out.print("Introduce los datos entre comillas simples(fin para salir): ");
			String input = sc.nextLine();
			if (input.equals("fin"))
				return null;
			tokens = Metodos.tokenize(input);

			if (!Equipos.validarTokens(tokens)) {
				System.out.println("Formato incorrecto");
				continue;
			}
			hayDatos = true;
		} while (!hayDatos);

		return tokens;
	}

	private static void printColumnas() {
		System.out.println("Columns:\n" + "Nombre -> varchar(20) PK\n" + "Ciudad -> varchar(20)\n"
				+ "Conferencia -> varchar(4)\n" + "Division -> varchar(9)");
	}

	public static String[] cogerCamposClaves() {
		String[] campos = new String[1];
		campos[0] = "Nombre";
		return campos;
	}

	public static String[] cogerNombresDeColumnas() {
		String[] c = new String[4];
		c[0] = "Nombre";
		c[1] = "Ciudad";
		c[2] = "Conferencia";
		c[3] = "Division";
		return c;
	}

	private static boolean validarTokens(String[] tokens) {
		if (tokens.length != 4)
			return false;
		if (tokens[0].length() > 20)
			return false;
		if (tokens[1].length() > 20)
			return false;
		if (tokens[2].length() > 4)
			return false;
		if (tokens[3].length() > 9)
			return false;

		return true;
	}

	private static boolean validarCampoClave(String dato) {
		if (dato.length() > 20 || dato.length() == 0)
			return false;
		return true;
	}

	public static void eliminarDatos(Connection connection, Scanner sc) {
		System.out.print(
				"\nElimnar datos de la tabla 'Equipos'\n" + "Indica el nombre formato 'varchar(20)': ");
		String clave = sc.nextLine();

		if (validarCampoClave(clave)) {
			String consulta = "delete from equipos where nombre like '" + clave + "'";
			Metodos.ejecutarConsultaDeAccion(connection, consulta);
			System.out.println("Eliminación de datos completada.");
		} else {
			System.out.println("Formato no válido");
		}
	}
}
