package domini;

import java.util.List;

public interface IVisitaMedicaRepository {

	void createTableIfNotExists() throws Exception;

	// CREATE
	int insert(VisitaMedica v) throws Exception;

	// READ por Id
	VisitaMedica findById(int id) throws Exception;

	// READ todos
	List<VisitaMedica> findAll() throws Exception;

	// UPDATE
	int update(VisitaMedica v) throws Exception;

	// DELETE
	int deleteById(int id) throws Exception;

	void close() throws Exception;

}
