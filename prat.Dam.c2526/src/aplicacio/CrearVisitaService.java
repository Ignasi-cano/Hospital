package aplicacio;

import domini.IVisitaMedicaRepository;
import domini.VisitaMedica;

public class CrearVisitaService {

	private IVisitaMedicaRepository iVisitaMedicaRepository;

	public CrearVisitaService(IVisitaMedicaRepository iVisitaMedicaRepository) {
		this.iVisitaMedicaRepository = iVisitaMedicaRepository;
	}

	public int insert(VisitaMedica v) throws Exception {
		ValidacionesVisita.validarInsercion(v);
		return iVisitaMedicaRepository.insert(v);
	}
}
