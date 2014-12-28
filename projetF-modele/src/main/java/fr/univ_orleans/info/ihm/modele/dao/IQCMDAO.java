package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.modele.beans.IQuestion;

import java.sql.Date;
import java.util.List;

public interface IQCMDAO {
    /**
     * Créer un QCM
     * @param idCreateur id du créateur
     * @param nomQCM nom
     * @param dateCreation date de création
     * @return le QCM
     */
    public IQCM creerQCM(int idCreateur, String nomQCM, Date dateCreation);

    /**
     * Permet d'obtenir un QCM via son id
     * @param idQCM id du QCM
     * @return le QCM
     */
    public IQCM getQCM(int idQCM);

    /**
     * Permet d'obtenir un QCM et toutes les questions du QCM via son id
     * @param idQCM id du QCM
     * @return le QCM
     */
    public IQCM getQCMWithQuestionList(int idQCM);

    /**
     * Permet d'obtenir la liste des idQuestion appartenant au QCM
     * @param idQCM id du QCM
     * @return la liste des idQuestion
     */
    public List<Integer> getListIdQuestionQCM(int idQCM);

    /**
     * Permet d'obtenir la prochaine question du QCM pour une session utilisateur donnée
     * @param idQCM id du QCM
     * @param idResultatUtilisateur id du résultat courant de l'utilisateur
     * @return la prochaine question (avec la liste des réponses)
     */
    public IQuestion getNextQuestionQCM(int idQCM, int idResultatUtilisateur);

    /**
     * Permet de mettre à jour le nom d'un QCM via son id
     * @param idQCM id du QCM
     * @param nomQCM nouveau nom du QCM
     * @return le QCM
     */
    public IQCM majNomQCM(int idQCM, String nomQCM);

    /**
     * Permet d'ajouter une question dans la liste de question du QCM
     * @param idQCM id du QCM
     * @param idQuestion id de la question
     */
    public void ajoutQCMQuestion(int idQCM, int idQuestion);

    /**
     * Supprime une question dans la liste de question du QCM
     * @param idQCM id du QCM
     * @param idQuestion id de la question
     */
    public void suppressionQCMQuestion(int idQCM, int idQuestion);

    /**
     * Supprime un QCM via son id
     * @param idQCM id du QCM
     */
    public void suppressionQCM(int idQCM);
}
