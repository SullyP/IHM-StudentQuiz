package fr.univ_orleans.info.ihm.modele.service;

import fr.univ_orleans.info.ihm.modele.MyLogger;
import fr.univ_orleans.info.ihm.modele.dao.*;
import fr.univ_orleans.info.ihm.modele.modele.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;

public class FacadeBaseDAO implements IFacadeDAO {
    /**
     * Insère une nouvelle entite en BDD
     *
     * @param nom nom de l'entité
     * @return l'entité créé
     */
    @Override
    public IEntite creerEntite(String nom) {
        return EntiteBaseDAO.getInstance().creerEntite(nom);
    }

    /**
     * Permet d'obtenir une entité via son id
     *
     * @param idEntite id de l'entité
     * @return l'entité
     */
    @Override
    public IEntite getEntite(int idEntite) {
        return EntiteBaseDAO.getInstance().getEntite(idEntite);
    }

    /**
     * Permet de mettre à jour le nom d'une entité
     *
     * @param idEntite id de l'entité
     * @param nom      nom de l'entité
     * @return l'entité mise à jour
     */
    @Override
    public IEntite majEntite(int idEntite, String nom) {
        return EntiteBaseDAO.getInstance().majEntite(idEntite, nom);
    }

    /**
     * Permet de supprimer une entité via son id
     *
     * @param idEntite id de l'entité
     */
    @Override
    public void suppressionEntite(int idEntite) {
        EntiteBaseDAO.getInstance().suppressionEntite(idEntite);
    }

    @Override
    public void init() throws RemoteException {
        MyLogger.getLogger().logp(Level.INFO, FacadeBaseDAO.class.getName(), "init", "Initialisation du service");
    }

    /**
     * Permet d'insérer un nouvel utilisateur en BDD.
     *
     * @param prenom         prenom de l'utilisateur.
     * @param nom            nom  de l'utilisateur.
     * @param identifiant    identifiant de l'utilisateur.
     * @param motDePasse     mot de passe de l'utilisateur.
     * @param numeroEtudiant numero étudiant de l'utilisateur (si étudiant, 0 sinon).
     * @param idEntite       id de l'entité de l'utilisateur.
     * @return l'utilisateur créé.
     */
    @Override
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite) {
        return UtilisateurBaseDAO.getInstance().creerUtilisateur(prenom, nom, identifiant, motDePasse, numeroEtudiant, idEntite);
    }

    /**
     * Permet d'obtenir un utilisateur via son id.
     *
     * @param idUtilisateur id de l'utilisateur.
     * @return l'utilisateur
     */
    @Override
    public IUtilisateur getUtilisateur(int idUtilisateur) {
        return UtilisateurBaseDAO.getInstance().getUtilisateur(idUtilisateur);
    }

    /**
     * Permet d'obtenir un utilisateur via son identifiant (login).
     *
     * @param identifiant identifiant de l'utilisateur (login).
     * @return l'utilisateur
     */
    @Override
    public IUtilisateur getUtilisateurByIdentifiant(String identifiant) {
        return UtilisateurBaseDAO.getInstance().getUtilisateurByIdentifiant(identifiant);
    }

    /**
     * Permet de mettre à jour le mot de passe de l'utilisateur.
     *
     * @param idUtilisateur id de l'utilisateur.
     * @param motDePasse    nouveau mot de passe
     * @return utilisateur
     */
    @Override
    public IUtilisateur majMotDePasseUtilisateur(int idUtilisateur, String motDePasse) {
        return UtilisateurBaseDAO.getInstance().majMotDePasse(idUtilisateur, motDePasse);
    }

    /**
     * Permet de mettre à jour l'entité de l'utilisateur
     *
     * @param idUtilisateur id de l'utilisateur.
     * @param idEntite      nouvel id de l'entité
     * @return utilisateur
     */
    @Override
    public IUtilisateur majEntiteUtilisateur(int idUtilisateur, int idEntite) {
        return UtilisateurBaseDAO.getInstance().majEntite(idUtilisateur, idEntite);
    }

    /**
     * Permet de supprimer un utilisateur
     *
     * @param idUtilisateur id de l'utilisateur à supprimer.
     */
    @Override
    public void suppressionUtilisateur(int idUtilisateur) {
        UtilisateurBaseDAO.getInstance().suppressionUtilisateur(idUtilisateur);
    }

    /**
     * Créé une réponse
     *
     * @param idQuestion      id de la question associé à la réponse
     * @param intituleReponse intitule de la réponse
     * @param correct         vrai si la réponse est correct, faux sinon
     * @return la réponse
     */
    @Override
    public IReponse creerReponse(int idQuestion, String intituleReponse, boolean correct) {
        return ReponseBaseDAO.getInstance().creerReponse(idQuestion, intituleReponse, correct);
    }

    /**
     * Permet d'obtenir une réponse via son id
     *
     * @param idReponse id de la réponse
     * @return la réponse
     */
    @Override
    public IReponse getReponse(int idReponse) {
        return ReponseBaseDAO.getInstance().getReponse(idReponse);
    }

    /**
     * Permet d'obtenir la liste des réponses d'une question via son id
     *
     * @param idQuestion id de la question
     * @return liste de réponses
     */
    @Override
    public List<IReponse> getReponseListByIdQuestion(int idQuestion) {
        return ReponseBaseDAO.getInstance().getReponseListByIdQuestion(idQuestion);
    }

    /**
     * Permet de mettre à jour une réponse
     *
     * @param idReponse       id de la réponse
     * @param intituleReponse intitule de la réponse
     * @param correct         vrai si la réponse est correct, faux sinon
     * @return la réponse
     */
    @Override
    public IReponse majReponse(int idReponse, String intituleReponse, boolean correct) {
        return ReponseBaseDAO.getInstance().majReponse(idReponse, intituleReponse, correct);
    }

    /**
     * Permet de supprimer une réponse via son id
     *
     * @param idReponse id de la réponse
     */
    @Override
    public void suppressionReponse(int idReponse) {
        ReponseBaseDAO.getInstance().suppressionReponse(idReponse);
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
        return QuestionBaseDAO.getInstance().creerQuestion(intitule, multiple, duree, pointQuestion);
    }

    /**
     * Permet d'obtenir une question via son id
     *
     * @param idQuestion id de la question
     * @return la question
     */
    @Override
    public IQuestion getQuestion(int idQuestion) {
        return QuestionBaseDAO.getInstance().getQuestion(idQuestion);
    }

    /**
     * Permet d'obtenir la liste des questions d'un QCM via son id
     *
     * @param idQCM id du QCM
     * @return la liste de questions
     */
    @Override
    public List<IQuestion> getQuestionListByIdQCM(int idQCM) {
        return QuestionBaseDAO.getInstance().getQuestionListByIdQCM(idQCM);
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
        return QuestionBaseDAO.getInstance().majQuestion(idQuestion, intitule, multiple, duree, pointQuestion);
    }

    /**
     * Permet de supprimer une question via son id
     *
     * @param idQuestion id de la question
     */
    @Override
    public void suppressionQuestion(int idQuestion) {
        QuestionBaseDAO.getInstance().suppressionQuestion(idQuestion);
    }

    /**
     * Créer un QCM
     *
     * @param idCreateur   id du créateur
     * @param nomQCM       nom
     * @param dateCreation date de création
     * @return le QCM
     */
    @Override
    public IQCM creerQCM(int idCreateur, String nomQCM, Date dateCreation) {
        return QCMBaseDAO.getInstance().creerQCM(idCreateur, nomQCM, dateCreation);
    }

    /**
     * Permet d'obtenir un QCM via son id
     *
     * @param idQCM id du QCM
     * @return le QCM
     */
    @Override
    public IQCM getQCM(int idQCM) {
        return QCMBaseDAO.getInstance().getQCM(idQCM);
    }

    /**
     * Permet d'obtenir un QCM et toutes les questions du QCM via son id
     *
     * @param idQCM id du QCM
     * @return le QCM
     */
    @Override
    public IQCM getQCMWithQuestionList(int idQCM) {
        return QCMBaseDAO.getInstance().getQCMWithQuestionList(idQCM);
    }

    /**
     * Permet d'obtenir la liste des idQuestion appartenant au QCM
     *
     * @param idQCM id du QCM
     * @return la liste des idQuestion
     */
    @Override
    public List<Integer> getListIdQuestionQCM(int idQCM) {
        return QCMBaseDAO.getInstance().getListIdQuestionQCM(idQCM);
    }

    /**
     * Permet de mettre à jour le nom d'un QCM via son id
     *
     * @param idQCM  id du QCM
     * @param nomQCM nouveau nom du QCM
     * @return le QCM
     */
    @Override
    public IQCM majNomQCM(int idQCM, String nomQCM) {
        return QCMBaseDAO.getInstance().majNomQCM(idQCM, nomQCM);
    }

    /**
     * Permet d'ajouter une question dans la liste de question du QCM
     *
     * @param idQCM      id du QCM
     * @param idQuestion id de la question
     */
    @Override
    public void ajoutQCMQuestion(int idQCM, int idQuestion) {
        QCMBaseDAO.getInstance().ajoutQCMQuestion(idQCM, idQuestion);
    }

    /**
     * Supprime une question dans la liste de question du QCM
     *
     * @param idQCM      id du QCM
     * @param idQuestion id de la question
     */
    @Override
    public void suppressionQCMQuestion(int idQCM, int idQuestion) {
        QCMBaseDAO.getInstance().suppressionQCMQuestion(idQCM, idQuestion);
    }

    /**
     * Supprime un QCM via son id
     *
     * @param idQCM id du QCM
     */
    @Override
    public void suppressionQCM(int idQCM) {
        QCMBaseDAO.getInstance().suppressionQCM(idQCM);
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
        return ResultatUtilisateurBaseDAO.getInstance().creerResultatUtilisateur(idUtilisateur, idQCM, dateResultat);
    }

    /**
     * Permet d'obtenir une instance de resultatUtilisateur
     *
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return le resultatUtilisateur (sans la liste de réponses)
     */
    @Override
    public IResultatUtilisateur getResultatUtilisateur(int idResultatUtilisateur) {
        return ResultatUtilisateurBaseDAO.getInstance().getResultatUtilisateur(idResultatUtilisateur);
    }

    /**
     * Permet d'ajouter une réponse à une instance de resultatUtilisateur
     *
     * @param idResultatUtilisateur id du résultat utilisateur
     * @param idReponse             id de la réponse
     */
    @Override
    public void ajoutReponseResultatUtilisateur(int idResultatUtilisateur, int idReponse) {
        ResultatUtilisateurBaseDAO.getInstance().addReponse(idResultatUtilisateur, idReponse);
    }

    /**
     * Permet d'obtenir les question/réponses d'un résultat utilisateur donné
     *
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return liste de question/réponses
     */
    @Override
    public List<IQuestion> getQuestionReponseListResultatUtilisateur(int idResultatUtilisateur) {
        return ResultatUtilisateurBaseDAO.getInstance().getQuestionReponseListResultatUtilisateur(idResultatUtilisateur);
    }

    /**
     * Permet de calculer le score d'une instance de resultatUtilisateur
     *
     * @param idResultatUtilisateur id du résultat utilisateur
     * @return le resultatUtilisateur (avec la liste de réponses)
     */
    @Override
    public IResultatUtilisateur calculerScoreResultatUtilisateur(int idResultatUtilisateur) {
        return ResultatUtilisateurBaseDAO.getInstance().calculerScore(idResultatUtilisateur);
    }

    /**
     * Permet de supprimer une instance de resultatUtilisateur
     *
     * @param idResultatUtilisateur id du résultat utilisateur
     */
    @Override
    public void suppressionResultatutilisateur(int idResultatUtilisateur) {
        ResultatUtilisateurBaseDAO.getInstance().suppressionResultatutilisateur(idResultatUtilisateur);
    }
}
