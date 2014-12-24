package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.MyLogger;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.ReponseEnum;
import fr.univ_orleans.info.ihm.modele.modele.IReponse;
import fr.univ_orleans.info.ihm.modele.modele.Reponse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class ReponseBaseDAO extends AbstractDAOObject implements  IReponseDAO{
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
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?);",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.ID_QUESTION, ReponseEnum.INTITULE_REPONSE, ReponseEnum.CORRECT_REPONSE);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idQuestion);
            preparedStatement.setString(++numeroParametre, intituleReponse);
            preparedStatement.setBoolean(++numeroParametre, correct);
            //On éxécute la requête
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idReponse généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, ReponseBaseDAO.class.getName(), "creerReponse", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance reponse avec les informations à notre disposition.
                reponse = new Reponse(resultSet.getInt(1), intituleReponse, correct);
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, ReponseBaseDAO.class.getName(), "creerReponse", MyLogger.MESSAGE_ERREUR_SQL, e);
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
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.ID_REPONSE);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idReponse);
            //On éxécute la requête
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, ReponseBaseDAO.class.getName(), "getReponse", MyLogger.MESSAGE_ERREUR_SQL, e);
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
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, ReponseBaseDAO.class.getName(), "getReponse", MyLogger.MESSAGE_ERREUR_SQL, e);
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
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.ID_QUESTION);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idQuestion);
            //On exécute la requête
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, ReponseBaseDAO.class.getName(), "getReponseListByIdQuestion", MyLogger.MESSAGE_ERREUR_SQL, e);
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
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, ReponseBaseDAO.class.getName(), "getReponseListByIdQuestion", MyLogger.MESSAGE_ERREUR_SQL, e);
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
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("UPDATE %s SET %s=?, %s=? WHERE %s=?;",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.INTITULE_REPONSE, ReponseEnum.CORRECT_REPONSE,
                ReponseEnum.ID_REPONSE);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setString(numeroParametre, intituleReponse);
            preparedStatement.setBoolean(++numeroParametre, correct);
            preparedStatement.setInt(++numeroParametre, idReponse);
            //On éxécute la requête
            preparedStatement.executeUpdate();
            //On créé une instance de Reponse avec les informations à notre disposition
            reponse = new Reponse(idReponse,intituleReponse,correct);
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, ReponseBaseDAO.class.getName(), "majReponse", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        this.getBd().closePrepared(preparedStatement);

        return reponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionReponse(int idReponse) {
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.ID_REPONSE);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idReponse);
            //On éxécute la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, ReponseBaseDAO.class.getName(), "suppressionReponse", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        this.getBd().closePrepared(preparedStatement);
    }
}
