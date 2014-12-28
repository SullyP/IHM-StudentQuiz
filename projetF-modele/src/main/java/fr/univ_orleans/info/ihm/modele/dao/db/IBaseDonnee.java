package fr.univ_orleans.info.ihm.modele.dao.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface d'abstraction d'une base de donnée.
 */
public interface IBaseDonnee {

    /**
     * Permet d'ouvrir la connection à la base de donnée. A éxécuter avant de lancer executeRequest ou executeUpdate.
     */
    public abstract void open();

    /**
     * Permet d'ouvrir la connection à la base de donnée, et d'obtenir une requête paramétrée.
     *
     * @param sql Requête sql.
     * @return La requête paramétrée correspondant à la requête sql.
     */
    public abstract PreparedStatement openPrepared(String sql);

    /**
     * Permet de fermer la connection à la base de donnée.
     */
    public abstract void close();

    /**
     * Permet de fermer la connection à la base de donnée, et une requête paramétrée
     *
     * @param preparedSql La requête paramétrée correspondant à la requête sql.
     */
    public abstract void closePrepared(PreparedStatement preparedSql);

    /**
     * Permet d'éxécuter une requête SQL (SELECT).
     *
     * @param sql la requête SQL à éxécuter
     * @return les résultats de la requête
     * @throws SQLException lève des exceptions si la requête soulève des erreurs à la BDD.
     */
    public abstract ResultSet executeRequest(String sql) throws SQLException;

    /**
     * Permet d'éxécuter une requête SQL (UPDATE, INSERT, DELETE).
     *
     * @param sql la requête SQL à éxécuter
     * @return les résultats de la requête correspondant aux id créé.
     * @throws SQLException lève des exceptions si la requête soulève des erreurs à la BDD.
     */
    public abstract ResultSet executeUpdate(String sql) throws SQLException;

    /**
     * Permet d'obtenir le chemin d'accès de la BDD.
     *
     * @return chemin d'accès de la BDD.
     */
    public abstract String getDbPath();

    /**
     * Permet de définir le chemin d'accès de la BDD.
     *
     * @param dbPath chemin d'accès de la BDD.
     */
    public abstract void setDbPath(String dbPath);

}