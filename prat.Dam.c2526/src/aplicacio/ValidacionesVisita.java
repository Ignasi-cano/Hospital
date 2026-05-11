package aplicacio;

import domini.VisitaMedica;

public class ValidacionesVisita {

	public static void validarInsercion(VisitaMedica v) throws Exception {
		if (v.getIdVisita() != null && v.getIdVisita() > 0) {
			throw new Exception("En una inserció, l'Id no pot ser major a 0 (ha de ser null o 0)");
		}
		validacionActualizacion(v);
	}

	public static void validarModificacion(VisitaMedica v) throws Exception {
		if (v.getIdVisita() == null || v.getIdVisita() <= 0) {
			throw new Exception("En una modificació, l'Id ha de ser major a 0");
		}
		validacionActualizacion(v);
	}

	public static void validacionActualizacion(VisitaMedica v) throws Exception {
		if (v.getNomPacient() == null || v.getNomPacient().trim().isEmpty()) {
			throw new Exception("El nom del pacient ha d'estar informat");
		}

		if (v.getNomMetge() == null || v.getNomMetge().trim().isEmpty()) {
			throw new Exception("El nom del metge ha d'estar informat");
		}

		if (v.getData() == null) {
			throw new Exception("La data de la visita ha d'estar informada");
		}
	}
}
