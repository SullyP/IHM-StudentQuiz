package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.MyLogger;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.QuestionEnum;
import fr.univ_orleans.info.ihm.modele.modele.IQuestion;
import fr.univ_orleans.info.ihm.modele.modele.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public final class QuestionBaseDAO extends AbstractDAOObject implements IQuestionDAO {
    private static IQuestionDAO instance=null;

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
     * Créer une nouvelle question
     *
     * @param intitule      enonce de la question
     * @param multiple      question a multiple réponse
     * @param duree         durée de la question en secondes
     * @param pointQuestion nombre de point que rapporte la question
     * @return la nouvelle question
     */
    @Override
    public IQuestion creerQuestion(String intitule, boolean multiple, int duree, int pointQuestion) {
        IQuestion question = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?);",
                BaseDonneeEnum.QUESTION,
                QuestionEnum.INTITULE_QUESTION, QuestionEnum.MULTIPLE_QUESTION, QuestionEnum.DUREE_QUESTION, QuestionEnum.POINT_QUESTION);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setString(numeroParametre, intitule);
            preparedStatement.setBoolean(++numeroParametre, multiple);
            preparedStatement.setInt(++numeroParametre, duree);
            preparedStatement.setInt(++numeroParametre, pointQuestion);
            //On éxécute la requête
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idQuestion généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QuestionBaseDAO.class.getName(), "creerQuestion", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Utilisateur avec les informations à notre disposition.
                question= new Question(resultSet.getInt(1),duree,pointQuestion,multiple,intitule);
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, QuestionBaseDAO.class.getName(), "creerQuestion", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return question;
    }

    /**
     * Permet d'obtenir une question via son id
     *
     * @param idQuestion id de la question
     * @return la question
     */
    @Override
    public IQuestion getQuestion(int idQuestion) {
        IQuestion question = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.QUESTION,
                QuestionEnum.ID_QUESTION);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idQuestion);
            //On exécute la requête
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QuestionBaseDAO.class.getName(), "getQuestion", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Utilisateur avec les informations à notre disposition.
                question= new Question(resultSet.getInt(QuestionEnum.ID_QUESTION.toString()),
                        resultSet.getInt(QuestionEnum.DUREE_QUESTION.toString()),
                        resultSet.getInt(QuestionEnum.POINT_QUESTION.toString()),
                        resultSet.getBoolean(QuestionEnum.MULTIPLE_QUESTION.toString()),
                        resultSet.getString(QuestionEnum.INTITULE_QUESTION.toString()));
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, QuestionBaseDAO.class.getName(), "getQuestion", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return question;
    }

    /**
     * Permet de mettre a jour la question
     *
     * @param idQuestion    id de la question
     * @param intitule      intitutile de la question
     * @param multiple      question a multiple réponse
     * @param duree         durée de la question en secondes
     * @param pointQuestion nombre de point que rapporte la question
     * @return la question
     */
    @Override
    public IQuestion majQuestion(int idQuestion, String intitule, boolean multiple, int duree, int pointQuestion) {
        IQuestion question = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?;",
                BaseDonneeEnum.QUESTION,
                QuestionEnum.INTITULE_QUESTION, QuestionEnum.MULTIPLE_QUESTION, QuestionEnum.DUREE_QUESTION, QuestionEnum.POINT_QUESTION,
                QuestionEnum.ID_QUESTION);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setString(numeroParametre,intitule);
            preparedStatement.setBoolean(++numeroParametre, multiple);
            preparedStatement.setInt(++numeroParametre, duree);
            preparedStatement.setInt(++numeroParametre, pointQuestion);
            //On exécute la requête
            preparedStatement.executeUpdate();
            //On créé une instance de question avec les informations à notre disposition
            question = new Question(idQuestion,duree,pointQuestion,multiple,intitule);
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QuestionBaseDAO.class.getName(), "majQuestion", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        this.getBd().closePrepared(preparedStatement);

        return question;
    }

    /**
     * Permet de supprimer une question via son id
     *
     * @param idQuestion id de la question
     */
    @Override
    public void suppressionQuestion(int idQuestion) {
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.QUESTION,
                QuestionEnum.ID_QUESTION);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre,idQuestion);
            //On exécute la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, QuestionBaseDAO.class.getName(), "suppressionQuestion", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        this.getBd().closePrepared(preparedStatement);
    }
}
