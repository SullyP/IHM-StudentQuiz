package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.MyLogger;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.QCMEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.QCMQuestionEnum;
import fr.univ_orleans.info.ihm.modele.modele.IQCM;
import fr.univ_orleans.info.ihm.modele.modele.IQuestion;
import fr.univ_orleans.info.ihm.modele.modele.QCM;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class QCMBaseDAO extends AbstractDAOObject implements IQCMDAO {
    private static IQCMDAO instance=null;

    private QCMBaseDAO(){
        super();
    }

    /**
     * Permet d'obtenir l'instance unique de la classe singleton.
     * @return instance unique de la classe singleton.
     */
    public static IQCMDAO getInstance(){
        if(instance == null){
            instance = new QCMBaseDAO();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM creerQCM(int idCreateur, String nomQCM, Date dateCreation) {
        IQCM qcm = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?);",
                BaseDonneeEnum.QCM,
                QCMEnum.ID_CREATEUR, QCMEnum.NOM_QCM, QCMEnum.DATE_CREATION);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idCreateur);
            preparedStatement.setString(++numeroParametre, nomQCM);
            preparedStatement.setDate(++numeroParametre, dateCreation);
            //On éxécute la requête
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idQCM généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QCMBaseDAO.class.getName(), "creerQCM", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance QCM avec les informations à notre disposition.
                qcm = new QCM(resultSet.getInt(1), idCreateur, nomQCM, dateCreation);
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, QCMBaseDAO.class.getName(), "creerQCM", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return qcm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM getQCM(int idQCM) {
        IQCM qcm = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.QCM,
                QCMEnum.ID_QCM);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idQCM);
            //On éxécute la requête
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QCMBaseDAO.class.getName(), "getQCM", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance QCM avec les informations à notre disposition.
                qcm = new QCM(resultSet.getInt(QCMEnum.ID_QCM.toString()),
                        resultSet.getInt(QCMEnum.ID_CREATEUR.toString()),
                        resultSet.getString(QCMEnum.NOM_QCM.toString()),
                        resultSet.getDate(QCMEnum.DATE_CREATION.toString()));
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, QCMBaseDAO.class.getName(), "getQCM", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return qcm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM getQCMWithQuestionList(int idQCM) {
        IQCM qcm = this.getQCM(idQCM);
        List<IQuestion> questionList = QuestionBaseDAO.getInstance().getQuestionListByIdQCM(idQCM);
        for (IQuestion question : questionList) {
            qcm.addQuestion(question);
        }
        return qcm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getListIdQuestionQCM(int idQCM) {
        List<Integer> idQuestionList = new ArrayList<>();
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.QCM_QUESTION,
                QCMQuestionEnum.ID_QCM);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idQCM);
            //On exécute la requête
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QCMBaseDAO.class.getName(), "getListIdQuestionQCM", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        if(resultSet!=null){
            try {
                //Tant qu'il reste des lignes dans le resultSet
                while(resultSet.next()) {
                    //On ajoute l'idQuestion à la liste
                    idQuestionList.add(resultSet.getInt(QCMQuestionEnum.ID_QUESTION.toString()));
                }
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, QCMBaseDAO.class.getName(), "getListIdQuestionQCM", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return idQuestionList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM majNomQCM(int idQCM, String nomQCM) {
        IQCM qcm = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("UPDATE %s SET %s=? WHERE %s=?;",
                BaseDonneeEnum.QCM,
                QCMEnum.NOM_QCM, QCMEnum.ID_QCM);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setString(1, nomQCM);
            preparedStatement.setInt(++numeroParametre, idQCM);
            //On éxécute la requête
            preparedStatement.executeUpdate();
            qcm = new QCM(idQCM);
            qcm.setNomQCM(nomQCM);
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QCMBaseDAO.class.getName(), "majNomQCM", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        this.getBd().closePrepared(preparedStatement);

        return qcm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ajoutQCMQuestion(int idQCM, int idQuestion) {
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("INSERT INTO %s (%s,%s) VALUES (?,?);",
                BaseDonneeEnum.QCM_QUESTION,
                QCMQuestionEnum.ID_QCM, QCMQuestionEnum.ID_QUESTION);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idQCM);
            preparedStatement.setInt(++numeroParametre, idQuestion);
            //On éxécute la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QCMBaseDAO.class.getName(), "ajoutQCMQuestion", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        this.getBd().closePrepared(preparedStatement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQCMQuestion(int idQCM, int idQuestion) {
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=? AND %s=?;",
                BaseDonneeEnum.QCM_QUESTION,
                QCMQuestionEnum.ID_QCM, QCMQuestionEnum.ID_QUESTION);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idQCM);
            preparedStatement.setInt(++numeroParametre, idQuestion);
            //On éxécute la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QCMBaseDAO.class.getName(), "suppressionQCMQuestion", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        this.getBd().closePrepared(preparedStatement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQCM(int idQCM) {
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.QCM,
                QCMQuestionEnum.ID_QCM);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idQCM);
            //On éxécute la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QCMBaseDAO.class.getName(), "suppressionQCM", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        this.getBd().closePrepared(preparedStatement);
    }
}
