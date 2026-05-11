package inteficie.consola;

import java.time.LocalDate;
import java.util.List;

import aplicacio.BorrarVisitaService;
import aplicacio.BuscarPorIdVisitaService;
import aplicacio.CrearVisitaService;
import aplicacio.ListarVisitaService;
import aplicacio.ModificarVisitaService;
import domini.IVisitaMedicaRepository;
import domini.VisitaMedica;

public class FormVisitaConsola {

	public FormVisitaConsola(IVisitaMedicaRepository iVisitaMedicaRepository) {
		borrarVisitaService = new BorrarVisitaService(iVisitaMedicaRepository);
		buscarPorIdVisitaService = new BuscarPorIdVisitaService(iVisitaMedicaRepository);
		crearVisitaService = new CrearVisitaService(iVisitaMedicaRepository);
		listarVisitaService = new ListarVisitaService(iVisitaMedicaRepository);
		modificarVisitaService = new ModificarVisitaService(iVisitaMedicaRepository);
	}

	private BorrarVisitaService borrarVisitaService = null;
	private BuscarPorIdVisitaService buscarPorIdVisitaService = null;
	private CrearVisitaService crearVisitaService = null;
	private ListarVisitaService listarVisitaService = null;
	private ModificarVisitaService modificarVisitaService = null;

	private StringBuilder sbMenu = null;

	public void menu() {
		int opcion = -1;
		while (opcion != 0) {
			opcion = FormUtilsConsola.getEntero(getMenu());
			switch (opcion) {
			case 0:
				break;
			case 1:
				crearVisita();
				break;
			case 2:
				borrarVisita();
				break;
			case 3:
				modificarVisita();
				break;
			case 4:
				buscarPorId();
				break;
			case 5:
				listarVisitas();
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
			sbMenu.append("MENÚ Gestió Visites Mèdiques");
			sbMenu.append("\n");
			sbMenu.append("===========================");
			sbMenu.append("\n");
			sbMenu.append("0. Sortir");
			sbMenu.append("\n");
			sbMenu.append("1. Alta");
			sbMenu.append("\n");
			sbMenu.append("2. Esborrar");
			sbMenu.append("\n");
			sbMenu.append("3. Modificar");
			sbMenu.append("\n");
			sbMenu.append("4. Buscar per Id");
			sbMenu.append("\n");
			sbMenu.append("5. Llistar");
			sbMenu.append("\n");
			sbMenu.append("----------");
			sbMenu.append("\n");
			sbMenu.append("Opció:");
			sbMenu.append("\n");
			sbMenu.append("----------");
			sbMenu.append("\n");
		}
		return sbMenu.toString();
	}

	private VisitaMedica getVisita(boolean pedirId) {
		Integer id = null;
		if (pedirId) {
			id = pedirId("Id Visita:");
		}
		String nomPacient = FormUtilsConsola.getCadena("Nom del Pacient:");
		String nomMetge = FormUtilsConsola.getCadena("Nom del Metge:");
		
		int dia = FormUtilsConsola.getEntero("Dia de la visita:");
		int mes = FormUtilsConsola.getEntero("Mes de la visita:");
		int any = FormUtilsConsola.getEntero("Any de la visita:");
		LocalDate data = LocalDate.of(any, mes, dia);
		
		String diagnostic = FormUtilsConsola.getCadena("Diagnòstic:");

		return new VisitaMedica(id, nomPacient, nomMetge, data, diagnostic);
	}

	private int pedirId(String mensaje) {
		return FormUtilsConsola.getEntero(mensaje);
	}

	private void crearVisita() {
		FormUtilsConsola.mostrarMensaje("---- Alta ");
		VisitaMedica v = getVisita(false);
		try {
			int id = crearVisitaService.insert(v);
			FormUtilsConsola.mostrarMensaje("Visita afegida amb id: " + v.getIdVisita());
		} catch (Exception e) {
			FormUtilsConsola.mostrarError(e.getMessage());
		}
	}

	private void borrarVisita() {
		FormUtilsConsola.mostrarMensaje("---- Esborrar ");
		int id = pedirId("Id Visita:");
		try {
			int rowsAffected = borrarVisitaService.borrar(id);
			FormUtilsConsola.mostrarMensaje("Visites eliminades: " + rowsAffected);
		} catch (Exception e) {
			FormUtilsConsola.mostrarError(e.getMessage());
		}
	}

	private void modificarVisita() {
		FormUtilsConsola.mostrarMensaje("---- Modificar ");
		VisitaMedica v = getVisita(true);
		try {
			int rowsAffected = modificarVisitaService.update(v);
			FormUtilsConsola.mostrarMensaje("Visites modificades: " + rowsAffected);
		} catch (Exception e) {
			FormUtilsConsola.mostrarError(e.getMessage());
		}
	}

	private void buscarPorId() {
		FormUtilsConsola.mostrarMensaje("---- Buscar per Id ");
		int id = pedirId("Id Visita:");
		try {
			VisitaMedica v = buscarPorIdVisitaService.getVisitaById(id);
			mostrarVisita(v);
		} catch (Exception e) {
			FormUtilsConsola.mostrarError(e.getMessage());
		}
	}

	private void listarVisitas() {
		FormUtilsConsola.mostrarMensaje("---- Llistat ");
		try {
			List<VisitaMedica> lista = listarVisitaService.getAll();
			if (lista != null) {
				for (VisitaMedica v : lista) {
					mostrarVisita(v);
				}
			}
		} catch (Exception e) {
			FormUtilsConsola.mostrarError(e.getMessage());
		}
	}

	private void mostrarVisita(VisitaMedica v) {
		FormUtilsConsola.mostrarMensaje(v.toString());
	}
}
