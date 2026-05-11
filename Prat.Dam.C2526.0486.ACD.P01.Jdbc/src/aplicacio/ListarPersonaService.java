package aplicacio;

import java.util.List;

import domini.IPersonaRepository;
import domini.Persona;

public class ListarPersonaService {
	public ListarPersonaService(IPersonaRepository iPersonaRepository) {
		this.iPersonaRepository = iPersonaRepository;
	}

	private IPersonaRepository iPersonaRepository = null;

	public List<Persona> getAll() throws Exception {
		return iPersonaRepository.findAll();
	}
}
