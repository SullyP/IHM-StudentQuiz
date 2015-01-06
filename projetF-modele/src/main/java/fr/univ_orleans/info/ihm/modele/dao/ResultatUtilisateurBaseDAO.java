package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.*;
import fr.univ_orleans.info.ihm.modele.dao.db.*;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ResultatUtilisateurBaseDAO extends AbstractDAOObject implements IResultatUtilisateurDAO {
    private static final Logger LOGGER = Logger.getLogger(ResultatUtilisateurBaseDAO.class.getCanonicalName());
    private static IResultatUtilisateurDAO instance=null;

    private ResultatUtilisateurBaseDAO(){
        super();
    }

    /**
     * Permet d'obtenir l'instance unique de la classe singleton.
     * @return instance unique de la classe singleton.
     */
    public static IResultatUtilisateurDAO getInstance(){
        if(instance == null){
            instance = new ResultatUtilisateurBaseDAO();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResultatUtilisateur creerResultatUtilisateur(int idUtilisateur, int idQCM, Date dateResultat) {
        IResultatUtilisateur resultatUtilisateur = null;
        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?);",
                BaseDonneeEnum.RESULTAT_UTILISATEUR,
                ResultatUtilisateurEnum.ID_UTILISATEUR, ResultatUtilisateurEnum.ID_QCM, ResultatUtilisateurEnum.DATE_RESULTAT_UTILISATEUR);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idUtilisateur);
            preparedStatement.setInt(++numeroParametre, idQCM);
            preparedStatement.setDate(++numeroParametre, new java.sql.Date(dateResultat.getTime()));
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idResultatUtilisateur généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance ResultatUtilisateur avec les informations à notre disposition.
                resultatUtilisateur = new ResultatUtilisateur(resultSet.getInt(1), idUtilisateur, idQCM, dateResultat);
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return resultatUtilisateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResultatUtilisateur getResultatUtilisateur(int idResultatUtilisateur) {
        IResultatUtilisateur resultatUtilisateur = null;

        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.RESULTAT_UTILISATEUR,
                ResultatUtilisateurEnum.ID_RESULTAT_UTILISATEUR);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idResultatUtilisateur);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance ResultatUtilisateur avec les informations à notre disposition.
                resultatUtilisateur = new ResultatUtilisateur(resultSet.getInt(ResultatUtilisateurEnum.ID_RESULTAT_UTILISATEUR.toString()),
                        resultSet.getInt(ResultatUtilisateurEnum.ID_UTILISATEUR.toString()),
                        resultSet.getInt(ResultatUtilisateurEnum.ID_QCM.toString()),
                        resultSet.getDate(ResultatUtilisateurEnum.DATE_RESULTAT_UTILISATEUR.toString()));
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return resultatUtilisateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addReponse(int idResultatUtilisateur, int idReponse) {

        String sqlQuery = String.format("INSERT INTO %s (%s,%s) VALUES (?,?);",
                BaseDonneeEnum.REPONSE_UTILISATEUR,
                ReponseUtilisateurEnum.ID_RESULTAT_UTILISATEUR, ReponseUtilisateurEnum.ID_REPONSE);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idResultatUtilisateur);
            preparedStatement.setInt(++numeroParametre, idReponse);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            LOGGER.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IQuestion> getQuestionReponseListResultatUtilisateur(int idResultatUtilisateur) {
        List<IQuestion> questionList = new ArrayList<>();

        String sqlQuery = String.format("SELECT r.%s, r.%s, r.%s, r.%s, q.%s, q.%s, q.%s, q.%s FROM (%s ru JOIN %s r ON ru.%s=? AND r.%s=ru.%s) JOIN %s q ON r.%s=q.%s ORDER BY r.%s;",
                ReponseEnum.ID_QUESTION, ReponseEnum.ID_REPONSE, ReponseEnum.INTITULE_REPONSE, ReponseEnum.CORRECT_REPONSE, QuestionEnum.INTITULE_QUESTION, QuestionEnum.MULTIPLE_QUESTION, QuestionEnum.DUREE_QUESTION, QuestionEnum.POINT_QUESTION,
                BaseDonneeEnum.REPONSE_UTILISATEUR, BaseDonneeEnum.REPONSE,
                ReponseUtilisateurEnum.ID_RESULTAT_UTILISATEUR,
                ReponseEnum.ID_REPONSE, ReponseUtilisateurEnum.ID_REPONSE,
                BaseDonneeEnum.QUESTION,
                ReponseEnum.ID_QUESTION, QuestionEnum.ID_QUESTION,
                ReponseEnum.ID_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idResultatUtilisateur);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            LOGGER.warn(e);
        }

        if(resultSet!=null){
            try {
                //Tant qu'il reste des lignes dans le resultSet
                IQuestion questionEnCours = null;
                while(resultSet.next()) {
                    //On créé une instance réponse avec les informations à notre disposition, et on l'ajoute.
                    if(questionEnCours == null || questionEnCours.getIdQuestion() != resultSet.getInt(ReponseEnum.ID_QUESTION.toString())){
                        //Si la question n'est pas null, on l'ajoute à la liste
                        if(questionEnCours != null){
                            questionList.add(questionEnCours);
                        }
                        //Si questionEnCours est null ou que l'on a changer de question, on en créé une nouvelle
                        questionEnCours = new Question(resultSet.getInt(ReponseEnum.ID_QUESTION.toString()),
                                resultSet.getInt(QuestionEnum.DUREE_QUESTION.toString()),
                                resultSet.getInt(QuestionEnum.POINT_QUESTION.toString()),
                                resultSet.getBoolean(QuestionEnum.MULTIPLE_QUESTION.toString()),
                                resultSet.getString(QuestionEnum.INTITULE_QUESTION.toString()));
                    }
                    //On ajoute la réponse de la ligne à la question
                    questionEnCours.addReponse(new Reponse(resultSet.getInt(ReponseEnum.ID_REPONSE.toString()),
                            resultSet.getString(ReponseEnum.INTITULE_REPONSE.toString()),
                            resultSet.getBoolean(ReponseEnum.CORRECT_REPONSE.toString())));

                }
                //Si la question n'est pas null, on l'ajoute à la liste
                if(questionEnCours != null){
                    questionList.add(questionEnCours);
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
    public IResultatUtilisateur calculerScore(int idResultatUtilisateur) {
        double score = 0;
        IResultatUtilisateur resultatUtilisateur = new ResultatUtilisateur(idResultatUtilisateur);
        List<IQuestion> questionReponses = this.getQuestionReponseListResultatUtilisateur(idResultatUtilisateur);

        String sqlQuery = String.format("SELECT COUNT(*) FROM %s WHERE %s=?;",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.ID_QUESTION);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        for(IQuestion question:questionReponses){
            //Ajout des réponses dans le résultatUtilisateur
            resultatUtilisateur.addReponse(question);

            //On compte le nombre de réponse correct à la question
            int nbReponseCorrect = 0;
            for(IReponse reponse:question.getReponses()){
                if(reponse.isCorrectReponse()){
                    nbReponseCorrect++;
                }
            }

            if(nbReponseCorrect > 0){
                //Si au moins une réponse est juste
                if(question.isMultipleQuestion()){
                    //Dans le cas d'une question à choix multiple, il faut connaître le nombre total de réponses corrects
                    ResultSet resultSet = null;
                    try {
                        preparedStatement.setInt(1, question.getIdQuestion());
                        resultSet = preparedStatement.executeQuery();
                    } catch (SQLException e){
                        LOGGER.warn(e);
                    }

                    if(resultSet!=null){
                        try {
                            //Si resusltSet n'est pas nul, on accède à la première ligne.
                            resultSet.next();
                            //On accède au nombre total de réponses corrects
                            int nbReponseCorrectTotal = resultSet.getInt(1);
                            if(nbReponseCorrectTotal > 0){
                                //On ajoute les points au score
                                score += question.getPointQuestion() * (nbReponseCorrect/nbReponseCorrectTotal);
                            }

                        } catch (SQLException e) {
                            LOGGER.warn(e);
                        }
                    }
                }else{
                    //La question n'a qu'une seule réponse, on ajoute donc les points de la question au score
                    score += question.getPointQuestion();
                }
            }
        }

        this.getBd().closePrepared(preparedStatement);

        //On enregistre le score avant de retourner le resultatUtilisateur
        resultatUtilisateur.setScore(score);
        return resultatUtilisateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionResultatutilisateur(int idResultatUtilisateur) {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.RESULTAT_UTILISATEUR,
                ResultatUtilisateurEnum.ID_RESULTAT_UTILISATEUR);

        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            preparedStatement.setInt(1, idResultatUtilisateur);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            LOGGER.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);
    }
}
