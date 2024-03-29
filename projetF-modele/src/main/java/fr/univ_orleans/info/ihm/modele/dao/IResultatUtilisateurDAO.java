package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.IQuestion;
import fr.univ_orleans.info.ihm.modele.beans.IResultatUtilisateur;

import java.util.Date;
import java.util.List;

public interface IResultatUtilisateurDAO {
    /**
     * Permet de créer une nouvelle entrée pour un resultatUtilisateur.
     * @param idUtilisateur id de l'utilisateur
     * @param idQCM id du QCM
     * @param dateResultat date du résultat
     * @return le resultatUtilisateur
     */
    public IResultatUtilisateur creerResultatUtilisateur(int idUtilisateur, int idQCM, Date dateResultat);

    /**
     * Permet d'obtenir une instance de resultatUtilisateur
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return le resultatUtilisateur (sans la liste de réponses)
     */
    public IResultatUtilisateur getResultatUtilisateur(int idResultatUtilisateur);

    /**
     * Permet d'obtenir les resultatUtilisateur pour un QCM donné
     * @param idQCM id du QCM
     * @return les resultatUtilisateur
     */
    public List<IResultatUtilisateur> getListResultatUtilisateurByQCM(int idQCM);

    /**
     * Permet d'ajouter une réponse à une instance de resultatUtilisateur
     * @param idResultatUtilisateur id du résultat utilisateur
     * @param idReponse id de la réponse
     */
    public void addReponse(int idResultatUtilisateur, int idReponse);

    /**
     * Permet d'obtenir les question/réponses d'un résultat utilisateur donné
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return liste de question/réponses
     */
    public List<IQuestion> getQuestionReponseListResultatUtilisateur(int idResultatUtilisateur);

    /**
     * Permet de calculer le score d'une instance de resultatUtilisateur
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return le resultatUtilisateur (avec la liste de réponses)
     */
    public IResultatUtilisateur calculerScore(int idResultatUtilisateur);

    /**
     * Permet de supprimer une instance de resultatUtilisateur
     * @param idResultatUtilisateur id du résultat utilisateur
     */
    public void suppressionResultatutilisateur(int idResultatUtilisateur);
}
