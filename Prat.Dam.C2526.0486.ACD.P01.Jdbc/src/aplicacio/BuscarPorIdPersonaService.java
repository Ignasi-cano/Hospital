package aplicacio;

import domini.IPersonaRepository;
import domini.Persona;

public class BuscarPorIdPersonaService {

	public BuscarPorIdPersonaService(IPersonaRepository iPersonaRepository) {
		this.iPersonaRepository = iPersonaRepository;
	}

	private IPersonaRepository iPersonaRepository = null;

	public Persona getPersonaById(int id) throws Exception {
		return iPersonaRepository.findById(id);
	}

}
