package aplicacio;

import java.util.List;

import domini.IVisitaMedicaRepository;
import domini.VisitaMedica;

public class ListarVisitaService {

	private IVisitaMedicaRepository iVisitaMedicaRepository;

	public ListarVisitaService(IVisitaMedicaRepository iVisitaMedicaRepository) {
		this.iVisitaMedicaRepository = iVisitaMedicaRepository;
	}

	public List<VisitaMedica> getAll() throws Exception {
		return iVisitaMedicaRepository.findAll();
	}
}
