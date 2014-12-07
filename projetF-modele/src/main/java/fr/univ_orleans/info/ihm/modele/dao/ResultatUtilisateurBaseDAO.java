package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.MyLogger;
import fr.univ_orleans.info.ihm.modele.dao.db.*;
import fr.univ_orleans.info.ihm.modele.modele.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class ResultatUtilisateurBaseDAO extends AbstractDAOObject implements IResultatUtilisateurDAO {
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
     * Permet de créer une nouvelle entrée pour un resultatUtilisateur.
     *
     * @param idUtilisateur id de l'utilisateur
     * @param idQCM         id du QCM
     * @param dateResultat  date du résultat
     * @return le resultatUtilisateur
     */
    @Override
    public IResultatUtilisateur creerResultatUtilisateur(int idUtilisateur, int idQCM, Date dateResultat) {
        IResultatUtilisateur resultatUtilisateur = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?);",
                BaseDonneeEnum.RESULTAT_UTILISATEUR,
                ResultatUtilisateurEnum.ID_UTILISATEUR, ResultatUtilisateurEnum.ID_QCM, ResultatUtilisateurEnum.DATE_RESULTAT_UTILISATEUR);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idUtilisateur);
            preparedStatement.setInt(++numeroParametre, idQCM);
            preparedStatement.setDate(++numeroParametre, dateResultat);
            //On éxécute la requête
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idResultatUtilisateur généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, ResultatUtilisateurBaseDAO.class.getName(), "creerResultatUtilisateur", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance ResultatUtilisateur avec les informations à notre disposition.
                resultatUtilisateur = new ResultatUtilisateur(resultSet.getInt(1), idUtilisateur, idQCM, dateResultat);
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, ResultatUtilisateurBaseDAO.class.getName(), "creerResultatUtilisateur", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return resultatUtilisateur;
    }

    /**
     * Permet d'obtenir une instance de resultatUtilisateur
     *
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return le resultatUtilisateur (sans la liste de réponses)
     */
    @Override
    public IResultatUtilisateur getResultatUtilisateur(int idResultatUtilisateur) {
        IResultatUtilisateur resultatUtilisateur = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.RESULTAT_UTILISATEUR,
                ResultatUtilisateurEnum.ID_RESULTAT_UTILISATEUR);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idResultatUtilisateur);
            //On éxécute la requête
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, ResultatUtilisateurBaseDAO.class.getName(), "getResultatUtilisateur", MyLogger.MESSAGE_ERREUR_SQL, e);
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
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, ResultatUtilisateurBaseDAO.class.getName(), "getResultatUtilisateur", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return resultatUtilisateur;
    }

    /**
     * Permet d'ajouter une réponse à une instance de resultatUtilisateur
     *
     * @param idResultatUtilisateur id du résultat utilisateur
     * @param idReponse             id de la réponse
     */
    @Override
    public void addReponse(int idResultatUtilisateur, int idReponse) {
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("INSERT INTO %s (%s,%s) VALUES (?,?);",
                BaseDonneeEnum.REPONSE_UTILISATEUR,
                ReponseUtilisateurEnum.ID_RESULTAT_UTILISATEUR, ReponseUtilisateurEnum.ID_REPONSE);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idResultatUtilisateur);
            preparedStatement.setInt(++numeroParametre, idReponse);
            //On éxécute la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, ResultatUtilisateurBaseDAO.class.getName(), "addReponse", MyLogger.MESSAGE_ERREUR_SQL, e);
        }
        this.getBd().closePrepared(preparedStatement);
    }

    /**
     * Permet d'obtenir les question/réponses d'un résultat utilisateur donné
     *
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return liste de question/réponses
     */
    @Override
    public List<IQuestion> getQuestionReponseListResultatUtilisateur(int idResultatUtilisateur) {
        List<IQuestion> questionList = new ArrayList<>();
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("SELECT r.%s, r.%s, r.%s, r.%s, q.%s, q.%s, q.%s, q.%s FROM (%s ru JOIN %s r ON ru.%s=? AND r.%s=ru.%s) JOIN %s q ON r.%s=q.%s ORDER BY r.%s;",
                ReponseEnum.ID_QUESTION, ReponseEnum.ID_REPONSE, ReponseEnum.INTITULE_REPONSE, ReponseEnum.CORRECT_REPONSE, QuestionEnum.INTITULE_QUESTION, QuestionEnum.MULTIPLE_QUESTION, QuestionEnum.DUREE_QUESTION, QuestionEnum.POINT_QUESTION,
                BaseDonneeEnum.REPONSE_UTILISATEUR, BaseDonneeEnum.REPONSE,
                ReponseUtilisateurEnum.ID_RESULTAT_UTILISATEUR,
                ReponseEnum.ID_REPONSE, ReponseUtilisateurEnum.ID_REPONSE,
                BaseDonneeEnum.QUESTION,
                ReponseEnum.ID_QUESTION, QuestionEnum.ID_QUESTION,
                ReponseEnum.ID_QUESTION);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idResultatUtilisateur);
            //On exécute la requête
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, ResultatUtilisateurBaseDAO.class.getName(), "getQuestionReponseListResultatUtilisateur", MyLogger.MESSAGE_ERREUR_SQL, e);
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
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, ResultatUtilisateurBaseDAO.class.getName(), "getQuestionReponseListResultatUtilisateur", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return questionList;
    }

    /**
     * Permet de calculer le score d'une instance de resultatUtilisateur
     *
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return le resultatUtilisateur (avec la liste de réponses)
     */
    @Override
    public IResultatUtilisateur calculerScore(int idResultatUtilisateur) {
        double score = 0;
        IResultatUtilisateur resultatUtilisateur = new ResultatUtilisateur(idResultatUtilisateur);
        List<IQuestion> questionReponses = this.getQuestionReponseListResultatUtilisateur(idResultatUtilisateur);

        //On écrit la requête à éxécuter pour récupérer le nombre total de réponses corrects d'une question
        String sqlQuery = String.format("SELECT COUNT(*) FROM %s WHERE %s=?;",
                BaseDonneeEnum.REPONSE,
                ReponseEnum.ID_QUESTION);
        //On ouvre la connection à la bdd et on prépare la requête
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
                        //On ajoute les valeurs de la requête préparée
                        preparedStatement.setInt(1, question.getIdQuestion());
                        //On éxécute la requête
                        resultSet = preparedStatement.executeQuery();
                    } catch (SQLException e){
                        //On log l'exception
                        MyLogger.getLogger().logp(Level.WARNING, ResultatUtilisateurBaseDAO.class.getName(), "calculerScore", MyLogger.MESSAGE_ERREUR_SQL, e);
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
                            //On log l'exception
                            MyLogger.getLogger().logp(Level.WARNING, ResultatUtilisateurBaseDAO.class.getName(), "calculerScore", MyLogger.MESSAGE_ERREUR_SQL, e);
                        }
                    }
                }else{
                    //La question n'a qu'une seule réponse, on ajoute donc les points de la question au score
                    score += question.getPointQuestion();
                }
            }
        }

        //On ferme la connection à la BDD et on libère la requête paramétrée
        this.getBd().closePrepared(preparedStatement);

        //On enregistre le score avant de retourner le resultatUtilisateur
        resultatUtilisateur.setScore(score);
        return resultatUtilisateur;
    }

    /**
     * Permet de supprimer une instance de resultatUtilisateur
     *
     * @param idResultatUtilisateur id du résultat utilisateur
     */
    @Override
    public void suppressionResultatutilisateur(int idResultatUtilisateur) {
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.RESULTAT_UTILISATEUR,
                ResultatUtilisateurEnum.ID_RESULTAT_UTILISATEUR);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idResultatUtilisateur);
            //On éxécute la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, ResultatUtilisateurBaseDAO.class.getName(), "suppressionResultatutilisateur", MyLogger.MESSAGE_ERREUR_SQL, e);
        }
        this.getBd().closePrepared(preparedStatement);
    }
}
