package aplicacio;

import domini.IVisitaMedicaRepository;

public class BorrarVisitaService {

	private IVisitaMedicaRepository iVisitaMedicaRepository;

	public BorrarVisitaService(IVisitaMedicaRepository iVisitaMedicaRepository) {
		this.iVisitaMedicaRepository = iVisitaMedicaRepository;
	}

	public int borrar(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("L'Id ha de ser més gran que 0 per esborrar");
		}
		return iVisitaMedicaRepository.deleteById(id);
	}
}
