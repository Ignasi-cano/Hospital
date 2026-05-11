package inteficie.consola;

import java.util.Scanner;

public class FormUtilsConsola {

	private static Scanner scanner = new Scanner(System.in);

	public static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	public static void mostrarError(String mensaje) {
		System.err.println(mensaje);
	}
	
	public static int getEntero(String mensaje) {
		boolean ok = false;
		int result = 0;
		while (!ok) {
			try {
				System.out.println(mensaje);
				result = scanner.nextInt();
				scanner.nextLine();//Consumim el \n
				ok = true;
			} catch (Exception ex) {
				ok = false;
				System.err.println("Dato no válido");
				scanner.nextLine(); // limpiar entrada inválida
			}

		}
		return result;
	}
	
	public static String getCadena(String mensaje) {
		boolean ok = false;
		String result = null;
		while (!ok) {
			try {
				System.out.println(mensaje);
				result = scanner.nextLine();
				ok = true;
			} catch (Exception ex) {
				ok = false;
				System.err.println("Dato no válido");
				scanner.nextLine(); // limpiar entrada inválida
			}

		}
		return result;
	}
	
}
