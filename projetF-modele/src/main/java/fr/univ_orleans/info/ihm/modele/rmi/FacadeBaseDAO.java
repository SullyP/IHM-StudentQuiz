package fr.univ_orleans.info.ihm.modele.rmi;

import fr.univ_orleans.info.ihm.modele.dao.*;
import fr.univ_orleans.info.ihm.modele.modele.*;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

public class FacadeBaseDAO implements IFacadeDAO {
    private static final Logger logger = Logger.getLogger(FacadeBaseDAO.class.getCanonicalName());

    @Override
    public void init() throws RemoteException {
        logger.info("Initialisation du service");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEntite creerEntite(String nom) {
        return EntiteBaseDAO.getInstance().creerEntite(nom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEntite getEntite(int idEntite) {
        return EntiteBaseDAO.getInstance().getEntite(idEntite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEntite majEntite(int idEntite, String nom) {
        return EntiteBaseDAO.getInstance().majEntite(idEntite, nom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionEntite(int idEntite) {
        EntiteBaseDAO.getInstance().suppressionEntite(idEntite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite) {
        return UtilisateurBaseDAO.getInstance().creerUtilisateur(prenom, nom, identifiant, motDePasse, numeroEtudiant, idEntite);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur getUtilisateur(int idUtilisateur) {
        return UtilisateurBaseDAO.getInstance().getUtilisateur(idUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur getUtilisateurByIdentifiant(String identifiant) {
        return UtilisateurBaseDAO.getInstance().getUtilisateurByIdentifiant(identifiant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur majMotDePasseUtilisateur(int idUtilisateur, String motDePasse) {
        return UtilisateurBaseDAO.getInstance().majMotDePasse(idUtilisateur, motDePasse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur majEntiteUtilisateur(int idUtilisateur, int idEntite) {
        return UtilisateurBaseDAO.getInstance().majEntite(idUtilisateur, idEntite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionUtilisateur(int idUtilisateur) {
        UtilisateurBaseDAO.getInstance().suppressionUtilisateur(idUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReponse creerReponse(int idQuestion, String intituleReponse, boolean correct) {
        return ReponseBaseDAO.getInstance().creerReponse(idQuestion, intituleReponse, correct);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReponse getReponse(int idReponse) {
        return ReponseBaseDAO.getInstance().getReponse(idReponse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IReponse> getReponseListByIdQuestion(int idQuestion) {
        return ReponseBaseDAO.getInstance().getReponseListByIdQuestion(idQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReponse majReponse(int idReponse, String intituleReponse, boolean correct) {
        return ReponseBaseDAO.getInstance().majReponse(idReponse, intituleReponse, correct);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionReponse(int idReponse) {
        ReponseBaseDAO.getInstance().suppressionReponse(idReponse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion creerQuestion(String intitule, boolean multiple, int duree, int pointQuestion) {
        return QuestionBaseDAO.getInstance().creerQuestion(intitule, multiple, duree, pointQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion getQuestion(int idQuestion) {
        return QuestionBaseDAO.getInstance().getQuestion(idQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IQuestion> getQuestionListByIdQCM(int idQCM) {
        return QuestionBaseDAO.getInstance().getQuestionListByIdQCM(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuestion majQuestion(int idQuestion, String intitule, boolean multiple, int duree, int pointQuestion) {
        return QuestionBaseDAO.getInstance().majQuestion(idQuestion, intitule, multiple, duree, pointQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQuestion(int idQuestion) {
        QuestionBaseDAO.getInstance().suppressionQuestion(idQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM creerQCM(int idCreateur, String nomQCM, Date dateCreation) {
        return QCMBaseDAO.getInstance().creerQCM(idCreateur, nomQCM, dateCreation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM getQCM(int idQCM) {
        return QCMBaseDAO.getInstance().getQCM(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM getQCMWithQuestionList(int idQCM) {
        return QCMBaseDAO.getInstance().getQCMWithQuestionList(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getListIdQuestionQCM(int idQCM) {
        return QCMBaseDAO.getInstance().getListIdQuestionQCM(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQCM majNomQCM(int idQCM, String nomQCM) {
        return QCMBaseDAO.getInstance().majNomQCM(idQCM, nomQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ajoutQCMQuestion(int idQCM, int idQuestion) {
        QCMBaseDAO.getInstance().ajoutQCMQuestion(idQCM, idQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQCMQuestion(int idQCM, int idQuestion) {
        QCMBaseDAO.getInstance().suppressionQCMQuestion(idQCM, idQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionQCM(int idQCM) {
        QCMBaseDAO.getInstance().suppressionQCM(idQCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResultatUtilisateur creerResultatUtilisateur(int idUtilisateur, int idQCM, Date dateResultat) {
        return ResultatUtilisateurBaseDAO.getInstance().creerResultatUtilisateur(idUtilisateur, idQCM, dateResultat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResultatUtilisateur getResultatUtilisateur(int idResultatUtilisateur) {
        return ResultatUtilisateurBaseDAO.getInstance().getResultatUtilisateur(idResultatUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ajoutReponseResultatUtilisateur(int idResultatUtilisateur, int idReponse) {
        ResultatUtilisateurBaseDAO.getInstance().addReponse(idResultatUtilisateur, idReponse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IQuestion> getQuestionReponseListResultatUtilisateur(int idResultatUtilisateur) {
        return ResultatUtilisateurBaseDAO.getInstance().getQuestionReponseListResultatUtilisateur(idResultatUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResultatUtilisateur calculerScoreResultatUtilisateur(int idResultatUtilisateur) {
        return ResultatUtilisateurBaseDAO.getInstance().calculerScore(idResultatUtilisateur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionResultatutilisateur(int idResultatUtilisateur) {
        ResultatUtilisateurBaseDAO.getInstance().suppressionResultatutilisateur(idResultatUtilisateur);
    }
}
