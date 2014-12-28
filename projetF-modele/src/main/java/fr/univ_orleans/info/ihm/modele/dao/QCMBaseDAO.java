package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.dao.db.*;
import fr.univ_orleans.info.ihm.modele.modele.IQCM;
import fr.univ_orleans.info.ihm.modele.modele.IQuestion;
import fr.univ_orleans.info.ihm.modele.modele.QCM;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class QCMBaseDAO extends AbstractDAOObject implements IQCMDAO {
    private static final Logger logger = Logger.getLogger(QCMBaseDAO.class.getCanonicalName());
    private static IQCMDAO instance = null;

    private QCMBaseDAO() {
        super();
    }

    /**
     * Permet d'obtenir l'instance unique de la classe singleton.
     *
     * @return instance unique de la classe singleton.
     */
    public static IQCMDAO getInstance() {
        if (instance == null) {
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

        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?);",
                BaseDonneeEnum.QCM,
                QCMEnum.ID_CREATEUR, QCMEnum.NOM_QCM, QCMEnum.DATE_CREATION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idCreateur);
            preparedStatement.setString(++numeroParametre, nomQCM);
            preparedStatement.setDate(++numeroParametre, dateCreation);
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idQCM généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e) {
            logger.warn(e);
        }

        if (resultSet != null) {
            try {
                //Si resultSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance QCM avec les informations à notre disposition.
                qcm = new QCM(resultSet.getInt(1), idCreateur, nomQCM, dateCreation);
                resultSet.close();
            } catch (SQLException e) {
                logger.warn(e);
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

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idQCM);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.warn(e);
        }

        if (resultSet != null) {
            try {
                //Si resultSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance QCM avec les informations à notre disposition.
                qcm = new QCM(resultSet.getInt(QCMEnum.ID_QCM.toString()),
                        resultSet.getInt(QCMEnum.ID_CREATEUR.toString()),
                        resultSet.getString(QCMEnum.NOM_QCM.toString()),
                        resultSet.getDate(QCMEnum.DATE_CREATION.toString()));
                resultSet.close();
            } catch (SQLException e) {
                logger.warn(e);
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

        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.QCM_QUESTION,
                QCMQuestionEnum.ID_QCM);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idQCM);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.warn(e);
        }

        if (resultSet != null) {
            try {
                //Tant qu'il reste des lignes dans le resultSet
                while (resultSet.next()) {
                    //On ajoute l'idQuestion à la liste
                    idQuestionList.add(resultSet.getInt(QCMQuestionEnum.ID_QUESTION.toString()));
                }
                resultSet.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return idQuestionList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion getNextQuestionQCM(int idQCM, int idResultatUtilisateur) {
        IQuestion question = null;

        String sqlQuery = String.format("SELECT %s, COUNT(*) AS NB FROM %s ru JOIN %s res ON ru.%s = res.%s AND res.%s = ? JOIN %s r ON ru.%s = r.%s AND %s NOT IN (SELECT %s  FROM %s ru JOIN %s r ON %s = ? AND ru.%s = r.%s) GROUP BY %s ORDER BY NB LIMIT 1;",
                ReponseEnum.ID_QUESTION,
                BaseDonneeEnum.REPONSE_UTILISATEUR, BaseDonneeEnum.RESULTAT_UTILISATEUR,
                ReponseUtilisateurEnum.ID_RESULTAT_UTILISATEUR, ResultatUtilisateurEnum.ID_RESULTAT_UTILISATEUR, ResultatUtilisateurEnum.ID_QCM,
                BaseDonneeEnum.REPONSE,
                ReponseUtilisateurEnum.ID_REPONSE, ReponseEnum.ID_REPONSE,
                ReponseEnum.ID_QUESTION, ReponseEnum.ID_QUESTION,
                BaseDonneeEnum.REPONSE_UTILISATEUR, BaseDonneeEnum.REPONSE,
                ReponseUtilisateurEnum.ID_RESULTAT_UTILISATEUR, ReponseUtilisateurEnum.ID_REPONSE, ReponseEnum.ID_REPONSE,
                ReponseEnum.ID_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        ResultSet resultSet = null;
        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idQCM);
            preparedStatement.setInt(++numeroParametre, idResultatUtilisateur);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.warn(e);
        }

        if (resultSet != null) {
            try {
                //Si resultSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On obtient les informations de la question
                question = QuestionBaseDAO.getInstance().getQuestionWithReponseList(resultSet.getInt(QuestionEnum.ID_QUESTION.toString()));
                resultSet.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM majNomQCM(int idQCM, String nomQCM) {
        IQCM qcm = null;
        String sqlQuery = String.format("UPDATE %s SET %s=? WHERE %s=?;",
                BaseDonneeEnum.QCM,
                QCMEnum.NOM_QCM, QCMEnum.ID_QCM);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        try {
            int numeroParametre = 1;
            preparedStatement.setString(1, nomQCM);
            preparedStatement.setInt(++numeroParametre, idQCM);
            preparedStatement.executeUpdate();
            qcm = new QCM(idQCM);
            qcm.setNomQCM(nomQCM);
        } catch (SQLException e) {
            logger.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);

        return qcm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ajoutQCMQuestion(int idQCM, int idQuestion) {
        String sqlQuery = String.format("INSERT INTO %s (%s,%s) VALUES (?,?);",
                BaseDonneeEnum.QCM_QUESTION,
                QCMQuestionEnum.ID_QCM, QCMQuestionEnum.ID_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idQCM);
            preparedStatement.setInt(++numeroParametre, idQuestion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQCMQuestion(int idQCM, int idQuestion) {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=? AND %s=?;",
                BaseDonneeEnum.QCM_QUESTION,
                QCMQuestionEnum.ID_QCM, QCMQuestionEnum.ID_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idQCM);
            preparedStatement.setInt(++numeroParametre, idQuestion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQCM(int idQCM) {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.QCM,
                QCMQuestionEnum.ID_QCM);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        try {
            preparedStatement.setInt(1, idQCM);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);
    }
}
