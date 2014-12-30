package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.IReponse;
import fr.univ_orleans.info.ihm.modele.beans.Reponse;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.ReponseEnum;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ReponseBaseDAO extends AbstractDAOObject implements  IReponseDAO{
    private static final Logger LOGGER = Logger.getLogger(ReponseBaseDAO.class.getCanonicalName());
    private static IReponseDAO instance=null;

    private ReponseBaseDAO(){
        super();
    }

    /**
     * Permet d'obtenir l'instance unique de la classe singleton.
     * @return instance unique de la classe singleton.
     */
    public static IReponseDAO getInstance(){
        if(instance == null){
            instance = new ReponseBaseDAO();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReponse creerReponse(int idQuestion, String intituleReponse, boolean correct) {
        IReponse reponse = null;
        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?);",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.ID_QUESTION, ReponseEnum.INTITULE_REPONSE, ReponseEnum.CORRECT_REPONSE);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idQuestion);
            preparedStatement.setString(++numeroParametre, intituleReponse);
            preparedStatement.setBoolean(++numeroParametre, correct);
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idReponse généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance reponse avec les informations à notre disposition.
                reponse = new Reponse(resultSet.getInt(1), intituleReponse, correct);
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return reponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReponse getReponse(int idReponse) {
        IReponse reponse = null;
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.ID_REPONSE);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idReponse);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance reponse avec les informations à notre disposition.
                reponse = new Reponse(resultSet.getInt(ReponseEnum.ID_REPONSE.toString()),
                        resultSet.getString(ReponseEnum.INTITULE_REPONSE.toString()),
                        resultSet.getBoolean(ReponseEnum.CORRECT_REPONSE.toString()));
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return reponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IReponse> getReponseListByIdQuestion(int idQuestion) {
        List<IReponse> reponseList = new ArrayList<>();
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.ID_QUESTION);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idQuestion);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        if(resultSet!=null){
            try {
                //Tant qu'il reste des lignes dans le resultSet
                while(resultSet.next()) {
                    //On créé une instance réponse avec les informations à notre disposition, et on l'ajoute.
                    reponseList.add(new Reponse(resultSet.getInt(ReponseEnum.ID_REPONSE.toString()),
                            resultSet.getString(ReponseEnum.INTITULE_REPONSE.toString()),
                            resultSet.getBoolean(ReponseEnum.CORRECT_REPONSE.toString())));
                }
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return reponseList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReponse majReponse(int idReponse, String intituleReponse, boolean correct) {
        IReponse reponse = null;
        String sqlQuery = String.format("UPDATE %s SET %s=?, %s=? WHERE %s=?;",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.INTITULE_REPONSE, ReponseEnum.CORRECT_REPONSE,
                ReponseEnum.ID_REPONSE);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            int numeroParametre = 1;
            preparedStatement.setString(numeroParametre, intituleReponse);
            preparedStatement.setBoolean(++numeroParametre, correct);
            preparedStatement.setInt(++numeroParametre, idReponse);
            preparedStatement.executeUpdate();
            reponse = new Reponse(idReponse,intituleReponse,correct);
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        this.getBd().closePrepared(preparedStatement);

        return reponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionReponse(int idReponse) {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.ID_REPONSE);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            preparedStatement.setInt(1, idReponse);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        this.getBd().closePrepared(preparedStatement);
    }
}
