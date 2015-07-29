package org.betavzw.hfdstk3.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CursusDAO {
	private DataSource ds;
	public CursusDAO(DataSource ds) {
		this.ds = ds;
	}

	
	/**
	 * Voegt een nieuwe cursus toe.
	 * @param code De code van de cursus.
	 * @param naam De naam van de cursus.
	 * @throws SQLIntegrityConstraintViolationException Wanneer de code al bestaat.
	 */
	public void InsertCursus(String code, String naam, int duur) throws SQLIntegrityConstraintViolationException{
		try(Connection conn = ds.getConnection();
		    PreparedStatement ps = conn.prepareStatement("INSERT INTO cursus (code, naam, duur) VALUES (?,?,?)")){
			ps.setString(1, code);
			ps.setString(2, naam);
			ps.setInt(3, duur);
			ps.executeUpdate();
		}catch(SQLIntegrityConstraintViolationException icv){
			throw icv;
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Verwijdert een cursus.
	 * @param code De code van de cursus die verwijderd moet worden.
	 * @throws IllegalArgumentException Wanneer de code niet bestaat.
	 */
	public void DeleteCursus(String code) throws IllegalArgumentException{
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM cursus WHERE code = ?")) {
			ps.setString(1, code);
			int aantal = ps.executeUpdate();
			if (aantal == 0) throw new IllegalArgumentException("code bestaat niet");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Geeft een lijst met alle cursussen terug.
	 * @return Een lijst met cursussen. 
	 */
	public List<Cursus> getCursussen() {
		try(Connection conn = ds.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT code, naam, duur FROM cursus")){
			List<Cursus> cursussen = new ArrayList<>();
			while(rs.next()){
				String code = rs.getString(1);
				String naam = rs.getString(2);
				int duur = rs.getInt(3);
				Cursus c = new Cursus();
				c.setCode(code);
				c.setNaam(naam);
				c.setDuur(duur);
				cursussen.add(c);
			}
			return cursussen;
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
