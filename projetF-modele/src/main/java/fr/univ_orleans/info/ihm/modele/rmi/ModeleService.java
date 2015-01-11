package fr.univ_orleans.info.ihm.modele.rmi;

import fr.univ_orleans.info.ihm.modele.beans.*;
import fr.univ_orleans.info.ihm.modele.dao.*;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class ModeleService implements IModeleService {
    private static final Logger LOGGER = Logger.getLogger(ModeleService.class.getCanonicalName());
    public static final String SERVICE_NAME = "ModeleService";

    @Override
    public void init() throws RemoteException {
        LOGGER.info("Initialisation du service");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEntite creerEntite(String nom) throws RemoteException {
        return EntiteBaseDAO.getInstance().creerEntite(nom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEntite getEntite(int idEntite) throws RemoteException {
        return EntiteBaseDAO.getInstance().getEntite(idEntite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEntite majEntite(int idEntite, String nom) throws RemoteException {
        return EntiteBaseDAO.getInstance().majEntite(idEntite, nom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionEntite(int idEntite) throws RemoteException {
        EntiteBaseDAO.getInstance().suppressionEntite(idEntite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite) throws RemoteException {
        return UtilisateurBaseDAO.getInstance().creerUtilisateur(prenom, nom, identifiant, motDePasse, numeroEtudiant, idEntite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur getUtilisateur(int idUtilisateur) throws RemoteException {
        return UtilisateurBaseDAO.getInstance().getUtilisateur(idUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur getUtilisateurByIdentifiant(String identifiant) throws RemoteException {
        return UtilisateurBaseDAO.getInstance().getUtilisateurByIdentifiant(identifiant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IResultatUtilisateur> getListResultatUtilisateurByQCM(int idQCM) throws RemoteException {
        return ResultatUtilisateurBaseDAO.getInstance().getListResultatUtilisateurByQCM(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IUtilisateur> getListUtilisateur() throws RemoteException {
        return UtilisateurBaseDAO.getInstance().getListUtilisateur();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur majMotDePasseUtilisateur(int idUtilisateur, String motDePasse) throws RemoteException {
        return UtilisateurBaseDAO.getInstance().majMotDePasse(idUtilisateur, motDePasse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur majUtilisateur(int idUtilisateur, String prenom, String nom, String identifiant, int numeroEtudiant, int idEntite) throws RemoteException {
        return UtilisateurBaseDAO.getInstance().majUtilisateur(idUtilisateur, prenom, nom, identifiant, numeroEtudiant, idEntite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionUtilisateur(int idUtilisateur) throws RemoteException {
        UtilisateurBaseDAO.getInstance().suppressionUtilisateur(idUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReponse creerReponse(int idQuestion, String intituleReponse, boolean correct) throws RemoteException {
        return ReponseBaseDAO.getInstance().creerReponse(idQuestion, intituleReponse, correct);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReponse getReponse(int idReponse) throws RemoteException {
        return ReponseBaseDAO.getInstance().getReponse(idReponse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IReponse> getReponseListByIdQuestion(int idQuestion) throws RemoteException {
        return ReponseBaseDAO.getInstance().getReponseListByIdQuestion(idQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReponse majReponse(int idReponse, String intituleReponse, boolean correct) throws RemoteException {
        return ReponseBaseDAO.getInstance().majReponse(idReponse, intituleReponse, correct);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionReponse(int idReponse) throws RemoteException {
        ReponseBaseDAO.getInstance().suppressionReponse(idReponse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion creerQuestion(String intitule, boolean multiple, int duree, int pointQuestion) throws RemoteException {
        return QuestionBaseDAO.getInstance().creerQuestion(intitule, multiple, duree, pointQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion getQuestion(int idQuestion) throws RemoteException {
        return QuestionBaseDAO.getInstance().getQuestion(idQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IQuestion> getQuestionListByIdQCM(int idQCM) throws RemoteException {
        return QuestionBaseDAO.getInstance().getQuestionListByIdQCM(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion majQuestion(int idQuestion, String intitule, boolean multiple, int duree, int pointQuestion) throws RemoteException {
        return QuestionBaseDAO.getInstance().majQuestion(idQuestion, intitule, multiple, duree, pointQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQuestion(int idQuestion) throws RemoteException {
        QuestionBaseDAO.getInstance().suppressionQuestion(idQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM creerQCM(int idCreateur, String nomQCM, Date dateCreation) throws RemoteException {
        return QCMBaseDAO.getInstance().creerQCM(idCreateur, nomQCM, dateCreation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM getQCM(int idQCM) throws RemoteException {
        return QCMBaseDAO.getInstance().getQCM(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM getQCMWithQuestionList(int idQCM) throws RemoteException {
        return QCMBaseDAO.getInstance().getQCMWithQuestionList(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getListIdQuestionQCM(int idQCM) throws RemoteException {
        return QCMBaseDAO.getInstance().getListIdQuestionQCM(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion getFirstQuestionQCM(int idQCM, int idResultatUtilisateur) throws RemoteException {
        return QCMBaseDAO.getInstance().getFirstQuestionQCM(idQCM, idResultatUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion getNextQuestionQCM(int idQCM, int idResultatUtilisateur) throws RemoteException {
        return QCMBaseDAO.getInstance().getNextQuestionQCM(idQCM, idResultatUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculerScoreMaxQCM(int idQCM) throws RemoteException {
        return QCMBaseDAO.getInstance().calculerScoreMaxQCM(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IQCM> getListQCMDispo() throws RemoteException {
        return QCMBaseDAO.getInstance().getListQCMDispo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IQCM> getListQCMByIdCreateur(int idCreateur) throws RemoteException {
        return QCMBaseDAO.getInstance().getListQCMByIdCreateur(idCreateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM majNomQCM(int idQCM, String nomQCM) throws RemoteException {
        return QCMBaseDAO.getInstance().majNomQCM(idQCM, nomQCM);
    }

    @Override
    public IQCM majEtatQCM(int idQCM, EtatQCMEnum etatQCM) throws RemoteException {
        return QCMBaseDAO.getInstance().majEtatQCM(idQCM, etatQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ajoutQCMQuestion(int idQCM, int idQuestion) throws RemoteException {
        QCMBaseDAO.getInstance().ajoutQCMQuestion(idQCM, idQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQCMQuestion(int idQCM, int idQuestion) throws RemoteException {
        QCMBaseDAO.getInstance().suppressionQCMQuestion(idQCM, idQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQCM(int idQCM) throws RemoteException {
        QCMBaseDAO.getInstance().suppressionQCM(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResultatUtilisateur creerResultatUtilisateur(int idUtilisateur, int idQCM, Date dateResultat) throws RemoteException {
        return ResultatUtilisateurBaseDAO.getInstance().creerResultatUtilisateur(idUtilisateur, idQCM, dateResultat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResultatUtilisateur getResultatUtilisateur(int idResultatUtilisateur) throws RemoteException {
        return ResultatUtilisateurBaseDAO.getInstance().getResultatUtilisateur(idResultatUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ajoutReponseResultatUtilisateur(int idResultatUtilisateur, int idReponse) throws RemoteException {
        ResultatUtilisateurBaseDAO.getInstance().addReponse(idResultatUtilisateur, idReponse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IQuestion> getQuestionReponseListResultatUtilisateur(int idResultatUtilisateur) throws RemoteException {
        return ResultatUtilisateurBaseDAO.getInstance().getQuestionReponseListResultatUtilisateur(idResultatUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResultatUtilisateur calculerScoreResultatUtilisateur(int idResultatUtilisateur) throws RemoteException {
        return ResultatUtilisateurBaseDAO.getInstance().calculerScore(idResultatUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionResultatutilisateur(int idResultatUtilisateur) throws RemoteException {
        ResultatUtilisateurBaseDAO.getInstance().suppressionResultatutilisateur(idResultatUtilisateur);
    }
}
