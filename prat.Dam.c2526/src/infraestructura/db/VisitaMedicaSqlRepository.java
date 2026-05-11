package infraestructura.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domini.IVisitaMedicaRepository;
import domini.VisitaMedica;

public class VisitaMedicaSqlRepository implements AutoCloseable, IVisitaMedicaRepository {

	private final Connection conn;

	public VisitaMedicaSqlRepository() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ignore) {
		}

		String url = "jdbc:mysql://localhost:3306/C2526_M0486_RA2";
		String user = "root";
		String password = "12345aA";
		this.conn = DriverManager.getConnection(url, user, password);
	}

	@Override
	public void createTableIfNotExists() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS VisitaMedica (\n"
				+ "IdVisita INT AUTO_INCREMENT PRIMARY KEY,\n"
				+ "nomPacient VARCHAR(100) NOT NULL,\n"
				+ "nomMetge VARCHAR(100) NOT NULL,\n"
				+ "data DATE NOT NULL,\n"
				+ "diagnostic TEXT\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		try (Statement st = conn.createStatement()) {
			st.execute(sql);
		}
	}

	// CREATE
	@Override
	public int insert(VisitaMedica v) throws SQLException {
		int resultat = 0;
		String sql = "INSERT INTO VisitaMedica (nomPacient, nomMetge, data, diagnostic) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, v.getNomPacient());
			ps.setString(2, v.getNomMetge());
			ps.setDate(3, Date.valueOf(v.getData()));
			if (v.getDiagnostic() != null)
				ps.setString(4, v.getDiagnostic());
			else
				ps.setNull(4, Types.VARCHAR);

			resultat = ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt(1);
					v.setIdVisita(id);
				}
			}
		} catch (Exception e) {
			throw new SQLException("No s'ha pogut obtenir l'ID generat", e);
		}

		return resultat;
	}

	// READ por Id
	@Override
	public VisitaMedica findById(int id) throws SQLException {
		VisitaMedica result = null;

		String sql = "SELECT IdVisita, nomPacient, nomMetge, data, diagnostic FROM VisitaMedica WHERE IdVisita = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = mapRow(rs);
				}
			}
		}

		if (result == null) {
			throw new SQLException("Visita Mèdica no trobada");
		}
		return result;
	}

	// READ todos
	@Override
	public List<VisitaMedica> findAll() throws SQLException {
		String sql = "SELECT IdVisita, nomPacient, nomMetge, data, diagnostic FROM VisitaMedica ORDER BY IdVisita";
		List<VisitaMedica> out = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				out.add(mapRow(rs));
			}
		}
		return out;
	}

	// UPDATE
	@Override
	public int update(VisitaMedica v) throws SQLException {
		int result = 0;
		if (v.getIdVisita() == null || v.getIdVisita() <= 0)
			throw new IllegalArgumentException("Id requerit per a actualitzar");
		String sql = "UPDATE VisitaMedica SET nomPacient=?, nomMetge=?, data=?, diagnostic=? WHERE IdVisita=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, v.getNomPacient());
			ps.setString(2, v.getNomMetge());
			ps.setDate(3, Date.valueOf(v.getData()));
			if (v.getDiagnostic() != null)
				ps.setString(4, v.getDiagnostic());
			else
				ps.setNull(4, Types.VARCHAR);
			ps.setInt(5, v.getIdVisita());

			result = ps.executeUpdate();
			return result;
		}
	}

	// DELETE
	@Override
	public int deleteById(int id) throws SQLException {
		int result = 0;
		String sql = "DELETE FROM VisitaMedica WHERE IdVisita = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			result = ps.executeUpdate();
			return result;
		}
	}

	private VisitaMedica mapRow(ResultSet rs) throws SQLException {
		int id = rs.getInt("IdVisita");
		String nomPacient = rs.getString("nomPacient");
		String nomMetge = rs.getString("nomMetge");
		Date sqlData = rs.getDate("data");
		LocalDate data = sqlData.toLocalDate();
		String diagnostic = rs.getString("diagnostic");

		return new VisitaMedica(id, nomPacient, nomMetge, data, diagnostic);
	}

	@Override
	public void close() throws SQLException {
		if (conn != null && !conn.isClosed())
			conn.close();
	}
}
