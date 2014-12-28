package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.IQuestion;

import java.util.List;

public interface IQuestionDAO {
    /**
     * Créer une nouvelle question
     * @param intitule enonce de la question
     * @param multiple question a multiple réponse
     * @param duree durée de la question en secondes
     * @param pointQuestion nombre de point que rapporte la question
     * @return la nouvelle question
     */
    public IQuestion creerQuestion(String intitule, boolean multiple, int duree, int pointQuestion);

    /**
     * Permet d'obtenir une question via son id
     * @param idQuestion id de la question
     * @return la question
     */
    public IQuestion getQuestion(int idQuestion);

    /**
     * Permet d'obtenir une question via son id
     * @param idQuestion id de la question
     * @return la question avec la liste des réponses
     */
    public IQuestion getQuestionWithReponseList(int idQuestion);

    /**
     * Permet d'obtenir la liste des questions d'un QCM via son id
     * @param idQCM id du QCM
     * @return la liste de questions
     */
    public List<IQuestion> getQuestionListByIdQCM(int idQCM);

    /**
     * Permet de mettre a jour la question
     * @param idQuestion id de la question
     * @param intitule intitutile de la question
     * @param multiple question a multiple réponse
     * @param duree durée de la question en secondes
     * @param pointQuestion nombre de point que rapporte la question
     * @return la question
     */
    public IQuestion majQuestion(int idQuestion, String intitule, boolean multiple, int duree, int pointQuestion);

    /**
     * Permet de supprimer une question via son id
     * @param idQuestion id de la question
     */
    public void suppressionQuestion(int idQuestion);
}
