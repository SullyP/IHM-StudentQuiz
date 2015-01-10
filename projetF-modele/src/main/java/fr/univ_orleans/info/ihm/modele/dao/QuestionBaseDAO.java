package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.IQuestion;
import fr.univ_orleans.info.ihm.modele.beans.IReponse;
import fr.univ_orleans.info.ihm.modele.beans.Question;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.QCMQuestionEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.QuestionEnum;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class QuestionBaseDAO extends AbstractDAOObject implements IQuestionDAO {
    private static final Logger LOGGER = Logger.getLogger(QuestionBaseDAO.class.getCanonicalName());
    private static IQuestionDAO instance = null;

    private QuestionBaseDAO(){
        super();
    }

    /**
     * Permet d'obtenir l'instance unique de la classe singleton.
     * @return instance unique de la classe singleton.
     */
    public static IQuestionDAO getInstance(){
        if(instance == null){
            instance = new QuestionBaseDAO();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion creerQuestion(String intitule, boolean multiple, int duree, int pointQuestion) {
        IQuestion question = null;

        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?);",
                BaseDonneeEnum.QUESTION,
                QuestionEnum.INTITULE_QUESTION, QuestionEnum.MULTIPLE_QUESTION, QuestionEnum.DUREE_QUESTION, QuestionEnum.POINT_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        ResultSet resultSet = null;
        try {
            int numeroParametre = 1;
            preparedStatement.setString(numeroParametre, intitule);
            preparedStatement.setBoolean(++numeroParametre, multiple);
            preparedStatement.setInt(++numeroParametre, duree);
            preparedStatement.setInt(++numeroParametre, pointQuestion);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        if(resultSet!=null){
            try {
                resultSet.next();
                question= new Question(resultSet.getInt(1),duree,pointQuestion,multiple,intitule);
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion getQuestion(int idQuestion) {
        IQuestion question = null;

        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.QUESTION,
                QuestionEnum.ID_QUESTION);

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
                resultSet.next();
                question= new Question(resultSet.getInt(QuestionEnum.ID_QUESTION.toString()),
                        resultSet.getInt(QuestionEnum.DUREE_QUESTION.toString()),
                        resultSet.getInt(QuestionEnum.POINT_QUESTION.toString()),
                        resultSet.getBoolean(QuestionEnum.MULTIPLE_QUESTION.toString()),
                        resultSet.getString(QuestionEnum.INTITULE_QUESTION.toString()));
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion getQuestionWithReponseList(int idQuestion) {
        IQuestion question = this.getQuestion(idQuestion);

        if(question != null) {
            List<IReponse> reponseList = ReponseBaseDAO.getInstance().getReponseListByIdQuestion(idQuestion);
            for (IReponse reponse : reponseList) {
                question.addReponse(reponse);
            }
        }
        return question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IQuestion> getQuestionListByIdQCM(int idQCM) {
        List<IQuestion> questionList = new ArrayList<>();

        String sqlQuery = String.format("SELECT q.%s, q.%s, q.%s, q.%s, q.%s FROM %s q JOIN %s qcm ON qcm.%s=? AND q.%s=qcm.%s;",
                QuestionEnum.ID_QUESTION, QuestionEnum.INTITULE_QUESTION, QuestionEnum.MULTIPLE_QUESTION, QuestionEnum.DUREE_QUESTION, QuestionEnum.POINT_QUESTION,
                BaseDonneeEnum.QUESTION, BaseDonneeEnum.QCM_QUESTION,
                QCMQuestionEnum.ID_QCM,
                QuestionEnum.ID_QUESTION, QCMQuestionEnum.ID_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idQCM);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        if(resultSet!=null){
            try {
                while(resultSet.next()) {
                    questionList.add(new Question(resultSet.getInt(QuestionEnum.ID_QUESTION.toString()),
                                resultSet.getInt(QuestionEnum.DUREE_QUESTION.toString()),
                                resultSet.getInt(QuestionEnum.POINT_QUESTION.toString()),
                                resultSet.getBoolean(QuestionEnum.MULTIPLE_QUESTION.toString()),
                            resultSet.getString(QuestionEnum.INTITULE_QUESTION.toString())));
                }
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return questionList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion majQuestion(int idQuestion, String intitule, boolean multiple, int duree, int pointQuestion) {
        IQuestion question = null;

        String sqlQuery = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?;",
                BaseDonneeEnum.QUESTION,
                QuestionEnum.INTITULE_QUESTION, QuestionEnum.MULTIPLE_QUESTION, QuestionEnum.DUREE_QUESTION, QuestionEnum.POINT_QUESTION,
                QuestionEnum.ID_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        try {
            int numeroParametre = 1;
            preparedStatement.setString(numeroParametre,intitule);
            preparedStatement.setBoolean(++numeroParametre, multiple);
            preparedStatement.setInt(++numeroParametre, duree);
            preparedStatement.setInt(++numeroParametre, pointQuestion);
            preparedStatement.setInt(++numeroParametre, idQuestion);
            preparedStatement.executeUpdate();
            question = new Question(idQuestion,duree,pointQuestion,multiple,intitule);
        } catch (SQLException e){
            LOGGER.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);

        return question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQuestion(int idQuestion) {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.QUESTION,
                QuestionEnum.ID_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        try {
            preparedStatement.setInt(1,idQuestion);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            LOGGER.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);
    }
}
