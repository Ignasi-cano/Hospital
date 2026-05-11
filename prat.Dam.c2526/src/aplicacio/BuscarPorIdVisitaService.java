package aplicacio;

import domini.IVisitaMedicaRepository;
import domini.VisitaMedica;

public class BuscarPorIdVisitaService {

	private IVisitaMedicaRepository iVisitaMedicaRepository;

	public BuscarPorIdVisitaService(IVisitaMedicaRepository iVisitaMedicaRepository) {
		this.iVisitaMedicaRepository = iVisitaMedicaRepository;
	}

	public VisitaMedica getVisitaById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("L'Id ha de ser més gran que 0 per buscar");
		}
		return iVisitaMedicaRepository.findById(id);
	}
}
