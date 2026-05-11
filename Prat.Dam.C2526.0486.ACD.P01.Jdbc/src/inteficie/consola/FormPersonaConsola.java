package inteficie.consola;

import java.time.LocalDate;
import java.util.List;

import aplicacio.BorrarPersonaService;
import aplicacio.BuscarPorIdPersonaService;
import aplicacio.CrearPersonaService;
import aplicacio.ListarPersonaService;
import aplicacio.ModificarPersonaService;
import domini.IPersonaRepository;
import domini.Persona;

public class FormPersonaConsola {

	public FormPersonaConsola(IPersonaRepository iPersonaRepository) {
		borrarPersonaService = new BorrarPersonaService(iPersonaRepository);
		buscarPorIdPersonaService = new BuscarPorIdPersonaService(iPersonaRepository);
		crearPersonaService = new CrearPersonaService(iPersonaRepository);
		listarPersonaService = new ListarPersonaService(iPersonaRepository);
		modificarPersonaService = new ModificarPersonaService(iPersonaRepository);

	}

	private BorrarPersonaService borrarPersonaService = null;
	private BuscarPorIdPersonaService buscarPorIdPersonaService = null;
	private CrearPersonaService crearPersonaService = null;
	private ListarPersonaService listarPersonaService = null;
	private ModificarPersonaService modificarPersonaService = null;

	private StringBuilder sbMenu = null;

	public void menu() {
		int opcion = -1;
		while (opcion != 0) {
			opcion = FormUtilsConsola.getEntero(getMenu());
			switch (opcion) {
			case 0:
				break;
			case 1:
				crearPersona();
				break;
			case 2:
				borrarPersona();
				break;
			case 3:
				modificarPersona();
				break;
			case 4:
				buscarPorId();
				break;
			case 5:
				listarPersonas();
				break;
			}
		}
	}

	private String getMenu() {
		if (sbMenu == null) {
			sbMenu = new StringBuilder();
			sbMenu.append("\n");
			sbMenu.append("===========================");
			sbMenu.append("\n");
			sbMenu.append("MENU Gestión Personas");
			sbMenu.append("\n");
			sbMenu.append("===========================");
			sbMenu.append("\n");
			sbMenu.append("0. Salir");
			sbMenu.append("\n");
			sbMenu.append("1. Alta");
			sbMenu.append("\n");
			sbMenu.append("2. Borrar");
			sbMenu.append("\n");
			sbMenu.append("3. Modificar");
			sbMenu.append("\n");
			sbMenu.append("4. Buscar por Id");
			sbMenu.append("\n");
			sbMenu.append("5. Listar");
			sbMenu.append("\n");
			sbMenu.append("----------");
			sbMenu.append("\n");
			sbMenu.append("Opción:");
			sbMenu.append("\n");
			sbMenu.append("----------");
			sbMenu.append("\n");

		}

		return sbMenu.toString();
	}

	private Persona getPersona(boolean pedirId) {
		int id = 0;
		if (pedirId) {
			// id = FormUtilsConsola.getEntero("Id:");
			id = this.pedirId("Id:");
		}
		String nombre = FormUtilsConsola.getCadena("Nombre:");
		String apellido1 = FormUtilsConsola.getCadena("Apellido1:");
		String apellido2 = FormUtilsConsola.getCadena("Apellido2:");

		int dia = FormUtilsConsola.getEntero("Día nacimiento:");
		int mes = FormUtilsConsola.getEntero("Mes nacimiento:");
		int any = FormUtilsConsola.getEntero("Año nacimiento:");

		LocalDate fechaNacimiento = LocalDate.of(any, mes, dia);

		return new Persona(id, nombre, apellido1, apellido2, fechaNacimiento);
	}

	private int pedirId(String mensaje) {
		return FormUtilsConsola.getEntero(mensaje);
	}

	private void crearPersona() {
		FormUtilsConsola.mostrarMensaje("---- Alta ");
		Persona p = getPersona(false);
		try {
			int id = crearPersonaService.insert(p);
			FormUtilsConsola.mostrarMensaje("Persona agregada con id: " + id);
		} catch (Exception e) {
			FormUtilsConsola.mostrarError(e.getMessage());
		}
	}

	private void borrarPersona() {
		FormUtilsConsola.mostrarMensaje("---- Borrar ");
		int id = pedirId("Id Persona:");
		try {
			int rowsAffected = borrarPersonaService.borrar(id);
			FormUtilsConsola.mostrarMensaje("Personas eliminadas: " + rowsAffected);
		} catch (Exception e) {
			FormUtilsConsola.mostrarError(e.getMessage());
		}
	}

	private void modificarPersona() {
		FormUtilsConsola.mostrarMensaje("---- Modificar ");
		Persona p = getPersona(true);
		try {
			int rowsAffected = modificarPersonaService.update(p);
			FormUtilsConsola.mostrarMensaje("Personas modificadas: " + rowsAffected);
		} catch (Exception e) {
			FormUtilsConsola.mostrarError(e.getMessage());
		}
	}

	private void buscarPorId() {
		FormUtilsConsola.mostrarMensaje("---- Buscar por Id ");
		int id = pedirId("Id Persona:");
		try {
			Persona p = buscarPorIdPersonaService.getPersonaById(id);
			mostrarPersona(p);
		} catch (Exception e) {
			FormUtilsConsola.mostrarError(e.getMessage());
		}
	}

	private void listarPersonas() {
		FormUtilsConsola.mostrarMensaje("---- Listado ");

		try {
			List<Persona> lista = listarPersonaService.getAll();
			if (lista != null) {
				for (Persona p : lista) {
					mostrarPersona(p);
				}
			}
		} catch (Exception e) {
			FormUtilsConsola.mostrarError(e.getMessage());
		}
	}

	private void mostrarPersona(Persona p) {
		FormUtilsConsola.mostrarMensaje(p.toString());
	}

}
