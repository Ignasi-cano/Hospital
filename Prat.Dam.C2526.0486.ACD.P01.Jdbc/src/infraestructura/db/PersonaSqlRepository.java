package infraestructura.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import domini.IPersonaRepository;
import domini.Persona;

public class PersonaSqlRepository implements AutoCloseable, IPersonaRepository {

	private final Connection conn;

	public PersonaSqlRepository() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ignore) {

		}
		
		String url = "jdbc:mysql://localhost:3306/Prat_M0486_P01";
		String user = "root";
		String password = "root";this.conn = DriverManager.getConnection(url, user, password);
	}

	@Override
	public void createTableIfNotExists() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Personas (\n" + "  Id INT UNSIGNED NOT NULL AUTO_INCREMENT,\n"
				+ "  Nombre VARCHAR(50) NOT NULL,\n" + "  Apellido1 VARCHAR(50) NOT NULL,\n"
				+ "  Apellido2 VARCHAR(50) NULL,\n" + "  FechaNacimiento DATE NOT NULL,\n" + "  PRIMARY KEY (Id),\n"
				+ "  INDEX idx_apellidos (Apellido1, Apellido2),\n" + "  INDEX idx_fecha (FechaNacimiento)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
		try (Statement st = conn.createStatement()) {
			st.execute(sql);
		}
	}

	// CREATE
	@Override
	public int insert(Persona p) throws SQLException {

		int resultat = 0;
		String sql = "INSERT INTO Personas (Nombre, Apellido1, Apellido2, FechaNacimiento) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellido1());
			if (p.getApellido2() != null)
				ps.setString(3, p.getApellido2());
			else
				ps.setNull(3, Types.VARCHAR);
			ps.setDate(4, Date.valueOf(p.getFechaNacimiento()));
			resultat = ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt(1);
					p.setId(id);

				}
			}
		} catch (Exception e) {
			throw new SQLException("No se pudo obtener el ID generado");
		}

		return resultat;
	}

	// READ por Id
	@Override
	public Persona findById(int id) throws SQLException {
		Persona result = null;

		String sql = "SELECT Id, Nombre, Apellido1, Apellido2, FechaNacimiento FROM Personas WHERE Id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = mapRow(rs);
				}
			}
		}
		
		if (result == null)
		{
			throw new SQLException("Persona no econtrada");
		}
		return result;
	}

	// READ todos
	@Override
	public List<Persona> findAll() throws SQLException {
		String sql = "SELECT Id, Nombre, Apellido1, Apellido2, FechaNacimiento FROM Personas ORDER BY Id";
		List<Persona> out = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql); 
		ResultSet rs = ps.executeQuery()) {
			while (rs.next())
			{
				out.add(mapRow(rs));
			}
		}
		return out;
	}

	// UPDATE
	@Override
	public int update(Persona p) throws SQLException {
		int result = 0;
		if (p.getId() == null)
			throw new IllegalArgumentException("Id requerido para update");
		String sql = "UPDATE Personas SET Nombre=?, Apellido1=?, Apellido2=?, FechaNacimiento=? WHERE Id=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellido1());
			if (p.getApellido2() != null)
				ps.setString(3, p.getApellido2());
			else
				ps.setNull(3, Types.VARCHAR);
			ps.setDate(4, Date.valueOf(p.getFechaNacimiento()));
			ps.setInt(5, p.getId());
			result  = ps.executeUpdate();
			return result;
		}
	}

	// DELETE
	@Override
	public int deleteById(int id) throws SQLException {
		int result = 0;
		String sql = "DELETE FROM Personas WHERE Id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			result = ps.executeUpdate();
			return result;
		}
	}

	private Persona mapRow(ResultSet rs) throws SQLException {
		int id = rs.getInt("Id");
		String nombre = rs.getString("Nombre");
		String apellido1 = rs.getString("Apellido1");
		String apellido2 = rs.getString("Apellido2");
		Date fn = rs.getDate("FechaNacimiento");
		LocalDate fechaNac = fn.toLocalDate();
		return new Persona(id, nombre, apellido1, apellido2, fechaNac);
	}

	@Override
	public void close() throws SQLException {
		if (conn != null && !conn.isClosed())
			conn.close();
	}

}
