package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.Entite;
import fr.univ_orleans.info.ihm.modele.beans.IEntite;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.EntiteEnum;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class EntiteBaseDAO extends AbstractDAOObject implements IEntiteDAO {
    private static final Logger LOGGER = Logger.getLogger(EntiteBaseDAO.class.getCanonicalName());
    private static IEntiteDAO instance=null;

    private EntiteBaseDAO(){
        super();
    }

    /**
     * Permet d'obtenir l'instance unique de la classe singleton.
     * @return instance unique de la classe singleton.
     */
    public static IEntiteDAO getInstance(){
        if(instance == null){
            instance = new EntiteBaseDAO();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEntite creerEntite(String nom) {
        IEntite entite = null;
        String sqlQuery = String.format("INSERT INTO %s (%s) VALUES (?);",
                BaseDonneeEnum.ENTITE, EntiteEnum.NOM_ENTITE);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            preparedStatement.setString(1, nom);
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idEntite généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Entite avec les informations à notre disposition.
                entite = new Entite(resultSet.getInt(1), nom);
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return entite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEntite getEntite(int idEntite) {
        IEntite entite = null;
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.ENTITE, EntiteEnum.ID_ENTITE);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idEntite);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Entite avec les informations à notre disposition.
                entite = new Entite(resultSet.getInt(EntiteEnum.ID_ENTITE.toString()),
                        resultSet.getString(EntiteEnum.NOM_ENTITE.toString()));
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return entite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEntite majEntite(int idEntite, String nom) {
        IEntite entite = null;
        String sqlQuery = String.format("UPDATE %s SET %s=? WHERE %s=?;",
                BaseDonneeEnum.ENTITE, EntiteEnum.NOM_ENTITE, EntiteEnum.ID_ENTITE);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            int numeroParametre = 1;
            preparedStatement.setString(numeroParametre, nom);
            preparedStatement.setInt(++numeroParametre, idEntite);
            preparedStatement.executeUpdate();
            //On créé une instance Entite avec les informations à notre disposition.
            entite = new Entite(idEntite, nom);
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        this.getBd().closePrepared(preparedStatement);

        return entite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionEntite(int idEntite) {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.ENTITE, EntiteEnum.ID_ENTITE);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            preparedStatement.setInt(1, idEntite);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        this.getBd().closePrepared(preparedStatement);
    }
}
