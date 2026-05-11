package aplicacio;

import domini.IVisitaMedicaRepository;
import domini.VisitaMedica;

public class ModificarVisitaService {

	private IVisitaMedicaRepository iVisitaMedicaRepository;

	public ModificarVisitaService(IVisitaMedicaRepository iVisitaMedicaRepository) {
		this.iVisitaMedicaRepository = iVisitaMedicaRepository;
	}

	public int update(VisitaMedica v) throws Exception {
		ValidacionesVisita.validarModificacion(v);
		return iVisitaMedicaRepository.update(v);
	}
}
