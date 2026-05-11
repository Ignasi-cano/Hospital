package inicio;


import infraestructura.db.PersonaSqlRepository;
import inteficie.consola.FormPersonaConsola;


import domini.IPersonaRepository;

public class TestMain {

	public static void main(String[] args) {
		try {
			IPersonaRepository iPersonaRepository = new PersonaSqlRepository();
			FormPersonaConsola fpc = new FormPersonaConsola(iPersonaRepository);
			fpc.menu();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
