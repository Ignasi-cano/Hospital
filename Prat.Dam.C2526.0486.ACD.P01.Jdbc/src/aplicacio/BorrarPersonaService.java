package aplicacio;



import domini.IPersonaRepository;

public class BorrarPersonaService {

	public BorrarPersonaService(IPersonaRepository iPersonaRepository) {

		this.iPersonaRepository = iPersonaRepository;
	}

	private IPersonaRepository iPersonaRepository;

	public int borrar(int id) throws Exception {

		return iPersonaRepository.deleteById(id);

	}
}
