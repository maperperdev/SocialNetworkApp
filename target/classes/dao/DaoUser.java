package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import daointerface.IDaoUser;
import model.User;

public class DaoUser implements IDaoUser {
	private static final String SQL_INSERT = "insert into USER (`NICK`, `EMAIL`, `PROFILE_PIC`, `DATE_BIRTH`,"
			+ "`DATE_REGISTER`, `PASSWORD`, `BIO`)" + " values (?, ?, ?, ?, ?, ?, ?)";
//	private static final String SQL_UPDATE = "UPDATE USER SET NICK = ?, EMAIL = ?,"
//			+ "DATE_BIRTH = ?, PASSWORD = ?, BIO= ? WHERE NICK = ?";
	private static final String SQL_READ_BY_NICK = "SELECT * FROM USER WHERE NICK = ?";
	private static final String SQL_READ_BY_EMAIL = "SELECT * FROM USER WHERE EMAIL = ?";

	private DataSource dataSource;

	public DaoUser(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean insert(User user) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(SQL_INSERT);
			ps.setString(1, user.getNick());
			ps.setString(2, user.getEmail());
			ps.setString(3, null);
			ps.setDate(4, user.getDateBirth());
			ps.setDate(5, Date.valueOf(LocalDate.now()));
			ps.setString(6, user.getPassword());
			ps.setString(7, user.getBio());

			if (ps.executeUpdate() > 0) {
				if (rs.next()) {
					return true;
				}
			}
		} catch (Exception e) {

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public User getUserByNick(String nickInput) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		User foundUser = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(SQL_READ_BY_NICK);
			ps.setString(1, nickInput);
			rs = ps.executeQuery();
			System.out.println(rs.toString());
			while (rs.next()) {
				String nick = rs.getString("NICK");
				String email = rs.getString("EMAIL");
				Date dateBirth = rs.getDate("DATE_BIRTH");
				Date dateRegister = rs.getDate("DATE_REGISTER");
				String password = rs.getString("PASSWORD");
				String bio = rs.getString("BIO");
				foundUser = new User(nick, email, dateBirth, dateRegister, password,
						bio);
			}
			return foundUser;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User getUserByEmail(String correoElectronicoInput) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		User foundUser = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(SQL_READ_BY_EMAIL);
			ps.setString(1, correoElectronicoInput);
			rs = ps.executeQuery();
			System.out.println(rs.toString());
			while (rs.next()) {
				String nick = rs.getString("NICK");
				String email = rs.getString("EMAIL");
				Date dateBirth = rs.getDate("DATE_BIRTH");
				Date dateRegister = rs.getDate("DATE_REGISTER");
				String password = rs.getString("PASSWORD");
				String bio = rs.getString("BIO");
				foundUser = new User(nick, email, dateBirth, dateRegister, password,
						bio);
			}
			return foundUser;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean update(User usuario) throws Exception {
		PreparedStatement ps = null;
		// ResultSet rs = null;
		Connection con = null;
		boolean result = false;
//		try {
//			con = origenDatos.getConnection();
//			ps = con.prepareStatement(SQL_UPDATE);
//			ps.setString(1, usuario.getNombre());
//			ps.setString(2, usuario.getApellido());
//			ps.setDate(3, Date.valueOf(usuario.getFechaNacimiento()));
//			ps.setString(4, usuario.getCorreoElectronico());
//			ps.setString(5, usuario.getContrasenia());
//			ps.setDate(6, Date.valueOf(usuario.getFechaModificacion()));
//			ps.setInt(7, usuario.getIdUsuario());
//			result = !ps.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return result;
	}

}
