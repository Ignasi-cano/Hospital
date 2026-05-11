package aplicacio;

import java.time.LocalDate;

import domini.Persona;

public class ValidacionesPersona {
	public static void validarInsercion(Persona p) throws Exception {

		if (p.getId() > 0) {
			throw new Exception("En una inserción, el Id no puede ser mayor a 0");
		}
		validacionActualizacion(p);
	}

	public static void validarModificacion(Persona p) throws Exception {

		if (p.getId() <= 0) {
			throw new Exception("En una modificación, el Id debe ser mayor a 0");
		}

		validacionActualizacion(p);
	}

	public static void validacionActualizacion(Persona p) throws Exception {
		if (p.getNombre() == null || p.getNombre().isEmpty()) {
			throw new Exception("El nombre debe estar informado");
		}

		if (p.getApellido1() == null || p.getApellido1().isEmpty()) {
			throw new Exception("El apellido 1 debe estar informado");
		}

		if (p.getFechaNacimiento().isAfter(LocalDate.now())) {
			throw new Exception("La data de neixement no pot ser posterior a avui");
		}

	}

}
