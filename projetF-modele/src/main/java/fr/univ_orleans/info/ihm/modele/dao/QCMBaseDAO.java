package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.EtatQCMEnum;
import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.modele.beans.IQuestion;
import fr.univ_orleans.info.ihm.modele.beans.QCM;
import fr.univ_orleans.info.ihm.modele.dao.db.*;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public final class QCMBaseDAO extends AbstractDAOObject implements IQCMDAO {
    private static final Logger LOGGER = Logger.getLogger(QCMBaseDAO.class.getCanonicalName());
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

        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?);",
                BaseDonneeEnum.QCM,
                QCMEnum.ID_CREATEUR, QCMEnum.NOM_QCM, QCMEnum.DATE_CREATION, QCMEnum.ETAT_QCM);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idCreateur);
            preparedStatement.setString(++numeroParametre, nomQCM);
            preparedStatement.setDate(++numeroParametre, new java.sql.Date(dateCreation.getTime()));
            preparedStatement.setString(++numeroParametre,EtatQCMEnum.FERME.toString());
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idQCM généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        if (resultSet != null) {
            try {
                //Si resultSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance QCM avec les informations à notre disposition.
                qcm = new QCM(resultSet.getInt(1), idCreateur, nomQCM, dateCreation, EtatQCMEnum.FERME);
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
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
            LOGGER.warn(e);
        }

        if (resultSet != null) {
            try {
                //Si resultSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance QCM avec les informations à notre disposition.
                qcm = new QCM(resultSet.getInt(QCMEnum.ID_QCM.toString()),
                        resultSet.getInt(QCMEnum.ID_CREATEUR.toString()),
                        resultSet.getString(QCMEnum.NOM_QCM.toString()),
                        resultSet.getDate(QCMEnum.DATE_CREATION.toString()),
                        EtatQCMEnum.valueOf(resultSet.getString(QCMEnum.ETAT_QCM.toString())));
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
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
            LOGGER.warn(e);
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
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return idQuestionList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IQCM> getListQCMDispo() {
        List<IQCM> listQCMDispo = new ArrayList<>();

        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=? OR %s=?;",
                BaseDonneeEnum.QCM,
                QCMEnum.ETAT_QCM, QCMEnum.ETAT_QCM);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        ResultSet resultSet = null;
        try {
            int numeroParametre = 1;
            preparedStatement.setString(numeroParametre, EtatQCMEnum.EN_ATTENTE.toString());
            preparedStatement.setString(++numeroParametre, EtatQCMEnum.OUVERT.toString());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        if (resultSet != null) {
            try {
                //Tant qu'il reste des lignes dans le resultSet
                while (resultSet.next()) {
                    //On ajoute le QCM à la liste
                    listQCMDispo.add(new QCM(resultSet.getInt(QCMEnum.ID_QCM.toString()),
                            resultSet.getInt(QCMEnum.ID_CREATEUR.toString()),
                            resultSet.getString(QCMEnum.NOM_QCM.toString()),
                            resultSet.getDate(QCMEnum.DATE_CREATION.toString()),
                            EtatQCMEnum.valueOf(resultSet.getString(QCMEnum.ETAT_QCM.toString()))));
                }
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return listQCMDispo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IQCM> getListQCMByIdCreateur(int idCreateur) {
        List<IQCM> listQCMByIdCreateur = new ArrayList<>();

        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.QCM,
                QCMEnum.ID_CREATEUR);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idCreateur);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        if (resultSet != null) {
            try {
                //Tant qu'il reste des lignes dans le resultSet
                while (resultSet.next()) {
                    //On ajoute le QCM à la liste
                    listQCMByIdCreateur.add(new QCM(resultSet.getInt(QCMEnum.ID_QCM.toString()),
                            resultSet.getInt(QCMEnum.ID_CREATEUR.toString()),
                            resultSet.getString(QCMEnum.NOM_QCM.toString()),
                            resultSet.getDate(QCMEnum.DATE_CREATION.toString()),
                            EtatQCMEnum.valueOf(resultSet.getString(QCMEnum.ETAT_QCM.toString()))));
                }
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return listQCMByIdCreateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion getFirstQuestionQCM(int idQCM, int idResultatUtilisateur) {
        IQuestion question = null;

        List<Integer> list = this.getListIdQuestionQCM(idQCM);
        //On choisit aléatoirement l'indice de l'idQuestion présent dans la liste
        Random rand = new Random();
        int max = list.size()-1;
        int min = 0;
        int randomNum = rand.nextInt((max - min) + 1) + min;

        //Obtention de la question
        question = QuestionBaseDAO.getInstance().getQuestionWithReponseList(list.get(randomNum));

        //On enregistre l'idQuestion afin de ne pas le reproposé à l'utilisateur
        if(question != null) {
            this.enregisterQuestionChoisie(idResultatUtilisateur, question.getIdQuestion());
        }

        return question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion getNextQuestionQCM(int idQCM, int idResultatUtilisateur) {
        IQuestion question = null;

        String sqlQuery = String.format("SELECT %s, COUNT(*) NB FROM (SELECT * FROM %s WHERE %s != ? UNION (SELECT * FROM %s WHERE %s=?)) WHERE %s NOT IN (SELECT %s  FROM %s WHERE %s = ?) GROUP BY %s ORDER BY NB LIMIT 1;",
                ReponseEnum.ID_QUESTION,
                BaseDonneeEnum.MEMO_QUESTION, ReponseUtilisateurEnum.ID_RESULTAT_UTILISATEUR,
                BaseDonneeEnum.QCM_QUESTION, QCMQuestionEnum.ID_QCM,
                QCMQuestionEnum.ID_QUESTION, QCMQuestionEnum.ID_QUESTION, BaseDonneeEnum.MEMO_QUESTION, ReponseUtilisateurEnum.ID_RESULTAT_UTILISATEUR,
                ReponseEnum.ID_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        ResultSet resultSet = null;
        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idResultatUtilisateur);
            preparedStatement.setInt(++numeroParametre, idQCM);
            preparedStatement.setInt(++numeroParametre, idResultatUtilisateur);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        if (resultSet != null) {
            try {
                //Si resultSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On obtient les informations de la question
                question = QuestionBaseDAO.getInstance().getQuestionWithReponseList(resultSet.getInt(QuestionEnum.ID_QUESTION.toString()));
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        //On enregistre l'idQuestion afin de ne pas le reproposé à l'utilisateur
        if(question != null) {
            this.enregisterQuestionChoisie(idResultatUtilisateur, question.getIdQuestion());
        }

        return question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculerScoreMaxQCM(int idQCM) {
        int scoreMax = -1;

        String sqlQuery = String.format("SELECT SUM(%s) FROM %s qq JOIN %s q ON qq.%s=q.%s AND qq.%s=?",
                QuestionEnum.POINT_QUESTION,
                BaseDonneeEnum.QCM_QUESTION, BaseDonneeEnum.QUESTION,
                QCMQuestionEnum.ID_QUESTION, QuestionEnum.ID_QUESTION,
                QCMQuestionEnum.ID_QCM);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idQCM);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        if (resultSet != null) {
            try {
                //Si resultSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                scoreMax = resultSet.getInt(1);
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return scoreMax;
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
            LOGGER.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);

        return qcm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM majEtatQCM(int idQCM, EtatQCMEnum etatQCM) {
        IQCM qcm = null;
        String sqlQuery = String.format("UPDATE %s SET %s=? WHERE %s=?;",
                BaseDonneeEnum.QCM,
                QCMEnum.ETAT_QCM, QCMEnum.ID_QCM);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        try {
            int numeroParametre = 1;
            preparedStatement.setString(1, etatQCM.toString());
            preparedStatement.setInt(++numeroParametre, idQCM);
            preparedStatement.executeUpdate();
            qcm = new QCM(idQCM);
            qcm.setEtatQCM(etatQCM);
        } catch (SQLException e) {
            LOGGER.warn(e);
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
            LOGGER.warn(e);
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
            LOGGER.warn(e);
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
            LOGGER.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);
    }

    /**
     * Permet d'enregistrer la question chosie lors des fonctions getNextQuestion et getFirstQuestion, afin de ne pas reproposer la même question plus tard
     * @param idResultatUtilisateur id du résultat utilisateur
     * @param idQuestion id de la question
     */
    private void enregisterQuestionChoisie(int idResultatUtilisateur, int idQuestion){
        String sqlQuery = String.format("INSERT INTO %s (%s,%s) VALUES (?,?);",
                BaseDonneeEnum.MEMO_QUESTION, ResultatUtilisateurEnum.ID_RESULTAT_UTILISATEUR, QCMQuestionEnum.ID_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idResultatUtilisateur);
            preparedStatement.setInt(++numeroParametre, idQuestion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);
    }
}
