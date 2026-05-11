package inicio;

import domini.IVisitaMedicaRepository;
import infraestructura.db.VisitaMedicaSqlRepository;
import inteficie.consola.FormVisitaConsola;

public class TestMain {

	public static void main(String[] args) {
		try {
			IVisitaMedicaRepository iVisitaMedicaRepository = new VisitaMedicaSqlRepository();
			
			// Ens assegurem que la taula i la BD existeixen abans d'iniciar el menú
			iVisitaMedicaRepository.createTableIfNotExists();
			
			FormVisitaConsola fvc = new FormVisitaConsola(iVisitaMedicaRepository);
			fvc.menu();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
