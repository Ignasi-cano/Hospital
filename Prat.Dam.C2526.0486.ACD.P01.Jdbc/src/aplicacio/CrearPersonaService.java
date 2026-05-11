package aplicacio;



import domini.IPersonaRepository;
import domini.Persona;

public class CrearPersonaService {

	public CrearPersonaService(IPersonaRepository iPersonaRepository) {

		this.iPersonaRepository = iPersonaRepository;
	}

	private IPersonaRepository iPersonaRepository;

	public int insert(Persona p) throws Exception {

		ValidacionesPersona.validarInsercion(p);
		// new PersonaSqlRepository().insert(p);
		return iPersonaRepository.insert(p);

	}
}
