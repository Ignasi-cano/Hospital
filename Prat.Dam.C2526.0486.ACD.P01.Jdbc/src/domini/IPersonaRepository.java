package domini;

import java.util.List;

public interface IPersonaRepository {

	void createTableIfNotExists() throws Exception;

	// CREATE
	int insert(Persona p) throws Exception;

	// READ por Id
	Persona findById(int id) throws Exception;

	// READ todos
	List<Persona> findAll() throws Exception;

	// UPDATE
	int update(Persona p) throws Exception;

	// DELETE
	int deleteById(int id) throws Exception;

	void close() throws Exception;

}