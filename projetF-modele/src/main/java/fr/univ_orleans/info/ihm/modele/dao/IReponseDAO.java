package fr.univ_orleans.info.ihm.modele.dao;


import fr.univ_orleans.info.ihm.modele.modele.IReponse;

public interface IReponseDAO {
    /**
     * Créé une réponse
     * @param idQuestion id de la question associé à la réponse
     * @param intituleReponse intitule de la réponse
     * @param correct vrai si la réponse est correct, faux sinon
     * @return la réponse
     */
    public IReponse creerReponse(int idQuestion, String intituleReponse, boolean correct);

    /**
     * Permet d'obtenir une réponse via son id
     * @param idReponse id de la réponse
     * @return la réponse
     */
    public IReponse getReponse(int idReponse);

    /**
     * Permet de mettre à jour une réponse
     * @param idReponse id de la réponse
     * @param intituleReponse intitule de la réponse
     * @param correct vrai si la réponse est correct, faux sinon
     * @return la réponse
     */
    public IReponse majReponse(int idReponse, String intituleReponse, boolean correct);

    /**
     * Permet de supprimer une réponse via son id
     * @param idReponse id de la réponse
     */
    public void suppressionReponse(int idReponse);

}
