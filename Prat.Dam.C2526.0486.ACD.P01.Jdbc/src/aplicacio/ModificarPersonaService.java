package aplicacio;

import domini.IPersonaRepository;
import domini.Persona;

public class ModificarPersonaService {
	public ModificarPersonaService(IPersonaRepository iPersonaRepository) {

		this.iPersonaRepository = iPersonaRepository;
	}

	private IPersonaRepository iPersonaRepository;

	public int update(Persona p) throws Exception {

		ValidacionesPersona.validarModificacion(p);
		return iPersonaRepository.update(p);

	}
}
