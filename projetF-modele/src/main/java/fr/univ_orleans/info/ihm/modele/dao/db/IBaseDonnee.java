package fr.univ_orleans.info.ihm.modele.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IBaseDonnee {

	public abstract void open();

	public abstract void close();

	public abstract ResultSet executeRequest(String sql) throws SQLException;

	public abstract ResultSet executeUpdate(String sql) throws SQLException;

	public abstract String getDbPath();

	public abstract void setDbPath(String dbPath);

}