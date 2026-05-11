package infraestructura.db;

import java.util.List;

import domini.IPersonaRepository;
import domini.Persona;

public class PersonaMongoDbRepository implements IPersonaRepository {

	@Override
	public void createTableIfNotExists() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insert(Persona p) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Persona findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Persona p) throws Exception {
		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public int deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
