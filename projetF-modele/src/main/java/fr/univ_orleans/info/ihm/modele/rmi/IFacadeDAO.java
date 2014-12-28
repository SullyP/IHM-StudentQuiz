package fr.univ_orleans.info.ihm.modele.rmi;

import fr.univ_orleans.info.ihm.modele.modele.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

public interface IFacadeDAO extends Remote {
    public final String SERVICE_NAME = "QCMService";

    public abstract void init() throws RemoteException;

    /**
     * Insère une nouvelle entite en BDD
     * @param nom nom de l'entité
     * @return l'entité créé
     */
    public abstract IEntite creerEntite(String nom);

    /**
     * Permet d'obtenir une entité via son id
     * @param idEntite id de l'entité
     * @return l'entité
     */
    public abstract IEntite getEntite(int idEntite);

    /**
     * Permet de mettre à jour le nom d'une entité
     * @param idEntite id de l'entité
     * @param nom nom de l'entité
     * @return l'entité mise à jour
     */
    public abstract IEntite majEntite(int idEntite, String nom);

    /**
     * Permet de supprimer une entité via son id
     * @param idEntite id de l'entité
     */
    public abstract void suppressionEntite(int idEntite);

    /**
     * Permet d'insérer un nouvel utilisateur en BDD.
     * @param prenom prenom de l'utilisateur.
     * @param nom nom  de l'utilisateur.
     * @param identifiant identifiant de l'utilisateur.
     * @param motDePasse mot de passe de l'utilisateur.
     * @param numeroEtudiant numero étudiant de l'utilisateur (si étudiant, 0 sinon).
     * @param idEntite id de l'entité de l'utilisateur.
     * @return l'utilisateur créé.
     */
    public abstract IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite);

    /**
     * Permet d'obtenir un utilisateur via son id.
     * @param idUtilisateur id de l'utilisateur.
     * @return l'utilisateur
     */
    public abstract IUtilisateur getUtilisateur(int idUtilisateur);

    /**
     * Permet d'obtenir un utilisateur via son identifiant (login).
     * @param identifiant identifiant de l'utilisateur (login).
     * @return l'utilisateur
     */
    public IUtilisateur getUtilisateurByIdentifiant(String identifiant);

    /**
     * Permet de mettre à jour le mot de passe de l'utilisateur.
     * @param idUtilisateur id de l'utilisateur.
     * @param motDePasse nouveau mot de passe
     * @return utilisateur
     */
    public abstract IUtilisateur majMotDePasseUtilisateur(int idUtilisateur, String motDePasse);

    /**
     * Permet de mettre à jour l'entité de l'utilisateur
     * @param idUtilisateur id de l'utilisateur.
     * @param idEntite nouvel id de l'entité
     * @return utilisateur
     */
    public abstract IUtilisateur majEntiteUtilisateur(int idUtilisateur, int idEntite);

    /**
     * Permet de supprimer un utilisateur
     * @param idUtilisateur id de l'utilisateur à supprimer.
     */
    public abstract void suppressionUtilisateur(int idUtilisateur);

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
     * Permet d'obtenir la liste des réponses d'une question via son id
     * @param idQuestion id de la question
     * @return liste de réponses
     */
    public List<IReponse> getReponseListByIdQuestion(int idQuestion);

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
     * Permet d'ajouter une réponse à une instance de resultatUtilisateur
     * @param idResultatUtilisateur id du résultat utilisateur
     * @param idReponse id de la réponse
     */
    public void ajoutReponseResultatUtilisateur(int idResultatUtilisateur, int idReponse);

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
    public IResultatUtilisateur calculerScoreResultatUtilisateur(int idResultatUtilisateur);

    /**
     * Permet de supprimer une instance de resultatUtilisateur
     * @param idResultatUtilisateur id du résultat utilisateur
     */
    public void suppressionResultatutilisateur(int idResultatUtilisateur);
}
