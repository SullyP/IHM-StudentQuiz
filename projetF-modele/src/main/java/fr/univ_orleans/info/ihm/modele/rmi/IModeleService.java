package fr.univ_orleans.info.ihm.modele.rmi;

import fr.univ_orleans.info.ihm.modele.beans.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IModeleService extends Remote {
    public void init() throws RemoteException;

    /**
     * Insère une nouvelle entite en BDD
     * @param nom nom de l'entité
     * @return l'entité créé
     */
    public IEntite creerEntite(String nom) throws RemoteException;

    /**
     * Permet d'obtenir une entité via son id
     * @param idEntite id de l'entité
     * @return l'entité
     */
    public IEntite getEntite(int idEntite) throws RemoteException;

    /**
     * Permet de mettre à jour le nom d'une entité
     * @param idEntite id de l'entité
     * @param nom nom de l'entité
     * @return l'entité mise à jour
     */
    public IEntite majEntite(int idEntite, String nom) throws RemoteException;

    /**
     * Permet de supprimer une entité via son id
     * @param idEntite id de l'entité
     */
    public void suppressionEntite(int idEntite) throws RemoteException;

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
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite) throws RemoteException;

    /**
     * Permet d'obtenir un utilisateur via son id.
     * @param idUtilisateur id de l'utilisateur.
     * @return l'utilisateur
     */
    public IUtilisateur getUtilisateur(int idUtilisateur) throws RemoteException;

    /**
     * Permet d'obtenir un utilisateur via son identifiant (login).
     * @param identifiant identifiant de l'utilisateur (login).
     * @return l'utilisateur
     */
    public IUtilisateur getUtilisateurByIdentifiant(String identifiant) throws RemoteException;

    /**
     * Permet de mettre à jour le mot de passe de l'utilisateur.
     * @param idUtilisateur id de l'utilisateur.
     * @param motDePasse nouveau mot de passe
     * @return utilisateur
     */
    public IUtilisateur majMotDePasseUtilisateur(int idUtilisateur, String motDePasse) throws RemoteException;

    /**
     * Permet de mettre à jour l'entité de l'utilisateur
     * @param idUtilisateur id de l'utilisateur.
     * @param idEntite nouvel id de l'entité
     * @return utilisateur
     */
    public IUtilisateur majEntiteUtilisateur(int idUtilisateur, int idEntite) throws RemoteException;

    /**
     * Permet de supprimer un utilisateur
     * @param idUtilisateur id de l'utilisateur à supprimer.
     */
    public void suppressionUtilisateur(int idUtilisateur) throws RemoteException;

    /**
     * Créé une réponse
     * @param idQuestion id de la question associé à la réponse
     * @param intituleReponse intitule de la réponse
     * @param correct vrai si la réponse est correct, faux sinon
     * @return la réponse
     */
    public IReponse creerReponse(int idQuestion, String intituleReponse, boolean correct) throws RemoteException;

    /**
     * Permet d'obtenir une réponse via son id
     * @param idReponse id de la réponse
     * @return la réponse
     */
    public IReponse getReponse(int idReponse) throws RemoteException;

    /**
     * Permet d'obtenir la liste des réponses d'une question via son id
     * @param idQuestion id de la question
     * @return liste de réponses
     */
    public List<IReponse> getReponseListByIdQuestion(int idQuestion) throws RemoteException;

    /**
     * Permet de mettre à jour une réponse
     * @param idReponse id de la réponse
     * @param intituleReponse intitule de la réponse
     * @param correct vrai si la réponse est correct, faux sinon
     * @return la réponse
     */
    public IReponse majReponse(int idReponse, String intituleReponse, boolean correct) throws RemoteException;

    /**
     * Permet de supprimer une réponse via son id
     * @param idReponse id de la réponse
     */
    public void suppressionReponse(int idReponse) throws RemoteException;

    /**
     * Créer une nouvelle question
     * @param intitule enonce de la question
     * @param multiple question a multiple réponse
     * @param duree durée de la question en secondes
     * @param pointQuestion nombre de point que rapporte la question
     * @return la nouvelle question
     */
    public IQuestion creerQuestion(String intitule, boolean multiple, int duree, int pointQuestion) throws RemoteException;

    /**
     * Permet d'obtenir une question via son id
     * @param idQuestion id de la question
     * @return la question
     */
    public IQuestion getQuestion(int idQuestion) throws RemoteException;

    /**
     * Permet d'obtenir la liste des questions d'un QCM via son id
     * @param idQCM id du QCM
     * @return la liste de questions
     */
    public List<IQuestion> getQuestionListByIdQCM(int idQCM) throws RemoteException;

    /**
     * Permet de mettre a jour la question
     * @param idQuestion id de la question
     * @param intitule intitutile de la question
     * @param multiple question a multiple réponse
     * @param duree durée de la question en secondes
     * @param pointQuestion nombre de point que rapporte la question
     * @return la question
     */
    public IQuestion majQuestion(int idQuestion, String intitule, boolean multiple, int duree, int pointQuestion) throws RemoteException;

    /**
     * Permet de supprimer une question via son id
     * @param idQuestion id de la question
     */
    public void suppressionQuestion(int idQuestion) throws RemoteException;

    /**
     * Créer un QCM
     * @param idCreateur id du créateur
     * @param nomQCM nom
     * @param dateCreation date de création
     * @return le QCM
     */
    public IQCM creerQCM(int idCreateur, String nomQCM, Date dateCreation) throws RemoteException;

    /**
     * Permet d'obtenir un QCM via son id
     * @param idQCM id du QCM
     * @return le QCM
     */
    public IQCM getQCM(int idQCM) throws RemoteException;

    /**
     * Permet d'obtenir un QCM et toutes les questions du QCM via son id
     * @param idQCM id du QCM
     * @return le QCM
     */
    public IQCM getQCMWithQuestionList(int idQCM) throws RemoteException;

    /**
     * Permet d'obtenir la liste des idQuestion appartenant au QCM
     * @param idQCM id du QCM
     * @return la liste des idQuestion
     */
    public List<Integer> getListIdQuestionQCM(int idQCM) throws RemoteException;

    /**
     * Permet d'obtenir la première question du QCM (choisi aléatoirement) pour une session utilisateur donnée
     * @param idQCM id du QCM
     * @param idResultatUtilisateur id du résultat courant de l'utilisateur
     * @return la première question (avec la liste des réponses)
     */
    public IQuestion getFirstQuestionQCM(int idQCM, int idResultatUtilisateur) throws RemoteException;

    /**
     * Permet d'obtenir la prochaine question du QCM pour une session utilisateur donnée
     * @param idQCM id du QCM
     * @param idResultatUtilisateur id du résultat courant de l'utilisateur
     * @return la prochaine question (avec la liste des réponses)
     */
    public IQuestion getNextQuestionQCM(int idQCM, int idResultatUtilisateur) throws RemoteException;

    /**
     * Permet de calculer le score maximal d'un QCM
     * @param idQCM id du QCM
     * @return score maximal du QCM
     */
    public int calculerScoreMaxQCM(int idQCM) throws RemoteException;

    /**
     * Permet d'obtenir la liste de tous les QCM disponibles (en attente ou ouvert)
     * @return liste de tous les QCM disponibles
     */
    public List<IQCM> getListQCMDispo() throws RemoteException;

    /**
     * Permet d'obtenir la liste de tous les QCM créés par un utilisateur donné
     * @param idCreateur id du createur
     * @return liste de tous les QCM créés par un utilisateur donné
     */
    public List<IQCM> getListQCMByIdCreateur(int idCreateur) throws RemoteException;

    /**
     * Permet de mettre à jour le nom d'un QCM via son id
     * @param idQCM id du QCM
     * @param nomQCM nouveau nom du QCM
     * @return le QCM
     */
    public IQCM majNomQCM(int idQCM, String nomQCM) throws RemoteException;

    /**
     * Permet d'ajouter une question dans la liste de question du QCM
     * @param idQCM id du QCM
     * @param idQuestion id de la question
     */
    public void ajoutQCMQuestion(int idQCM, int idQuestion) throws RemoteException;

    /**
     * Supprime une question dans la liste de question du QCM
     * @param idQCM id du QCM
     * @param idQuestion id de la question
     */
    public void suppressionQCMQuestion(int idQCM, int idQuestion) throws RemoteException;

    /**
     * Supprime un QCM via son id
     * @param idQCM id du QCM
     */
    public void suppressionQCM(int idQCM) throws RemoteException;

    /**
     * Permet de créer une nouvelle entrée pour un resultatUtilisateur.
     * @param idUtilisateur id de l'utilisateur
     * @param idQCM id du QCM
     * @param dateResultat date du résultat
     * @return le resultatUtilisateur
     */
    public IResultatUtilisateur creerResultatUtilisateur(int idUtilisateur, int idQCM, Date dateResultat) throws RemoteException;

    /**
     * Permet d'obtenir une instance de resultatUtilisateur
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return le resultatUtilisateur (sans la liste de réponses)
     */
    public IResultatUtilisateur getResultatUtilisateur(int idResultatUtilisateur) throws RemoteException;

    /**
     * Permet d'ajouter une réponse à une instance de resultatUtilisateur
     * @param idResultatUtilisateur id du résultat utilisateur
     * @param idReponse id de la réponse
     */
    public void ajoutReponseResultatUtilisateur(int idResultatUtilisateur, int idReponse) throws RemoteException;

    /**
     * Permet d'obtenir les question/réponses d'un résultat utilisateur donné
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return liste de question/réponses
     */
    public List<IQuestion> getQuestionReponseListResultatUtilisateur(int idResultatUtilisateur) throws RemoteException;

    /**
     * Permet de calculer le score d'une instance de resultatUtilisateur
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return le resultatUtilisateur (avec la liste de réponses)
     */
    public IResultatUtilisateur calculerScoreResultatUtilisateur(int idResultatUtilisateur) throws RemoteException;

    /**
     * Permet de supprimer une instance de resultatUtilisateur
     * @param idResultatUtilisateur id du résultat utilisateur
     */
    public void suppressionResultatutilisateur(int idResultatUtilisateur) throws RemoteException;
}
