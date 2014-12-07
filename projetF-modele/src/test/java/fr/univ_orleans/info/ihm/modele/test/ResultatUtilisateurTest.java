package fr.univ_orleans.info.ihm.modele.test;

import fr.univ_orleans.info.ihm.modele.dao.*;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeH2;
import fr.univ_orleans.info.ihm.modele.modele.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ResultatUtilisateurTest {
    protected IResultatUtilisateurDAO resultatUtilisateurDAO;
    protected IQCM qcm;
    protected IUtilisateur utilisateur;
    protected IQuestion question1, question2;
    protected IReponse reponse1Question1, reponse2Question1, reponse1Question2, reponse2Question2;
    protected Date date;

    @Before
    public void setUp() {
        //Utilisation d'une base de donnée H2 uniquement pour le test
        BaseDonneeH2.getInstance().setDbPath("~/testUnit");
        resultatUtilisateurDAO = ResultatUtilisateurBaseDAO.getInstance();
        question1 = QuestionBaseDAO.getInstance().creerQuestion("Comment vas-tu ?", false, 10, 2);
        question2 = QuestionBaseDAO.getInstance().creerQuestion("Esprit es-tu là ?", true, 10, 2);
        reponse1Question1 = ReponseBaseDAO.getInstance().creerReponse(question1.getIdQuestion(),"Bof non.", false);
        reponse2Question1 = ReponseBaseDAO.getInstance().creerReponse(question1.getIdQuestion(),"Mieux qu'hier, moins bien que demain.", true);
        reponse1Question2 = ReponseBaseDAO.getInstance().creerReponse(question2.getIdQuestion(),"Non je ne suis pas là.", true);
        reponse2Question2 = ReponseBaseDAO.getInstance().creerReponse(question2.getIdQuestion(),"A toi de me le dire.", true);
        IEntite entite = EntiteBaseDAO.getInstance().creerEntite("Nouvelle entité");
        utilisateur = UtilisateurBaseDAO.getInstance().creerUtilisateur("jean", "paul", "jp", "azerty", 123456, entite.getIdEntite());
        date = new Date(2014,11,23);
        qcm = QCMBaseDAO.getInstance().creerQCM(utilisateur.getIdUtilisateur(),"QCMaven",date);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testcreerResultatUtilisateur() throws Exception {
        IResultatUtilisateur resultatUtilisateur = resultatUtilisateurDAO.creerResultatUtilisateur(
                utilisateur.getIdUtilisateur(), qcm.getIdQCM(), date);
        assertNotNull(resultatUtilisateur);
        assertEquals(resultatUtilisateur.getIdQCM(),qcm.getIdQCM());
        assertEquals(resultatUtilisateur.getDate(), date);
        assertEquals(resultatUtilisateur.getIdUtilisateur(), utilisateur.getIdUtilisateur());
    }

    @Test
    public void testGetResultatUtilisateurById() throws Exception {
        IResultatUtilisateur resultatUtilisateur = resultatUtilisateurDAO.creerResultatUtilisateur(
                        utilisateur.getIdUtilisateur(), qcm.getIdQCM(), date);
        IResultatUtilisateur resultatUtilisateur2 = resultatUtilisateurDAO.getResultatUtilisateur(resultatUtilisateur.getIdResultatUtilisateur());
        assertNotNull(resultatUtilisateur);
        assertNotNull(resultatUtilisateur2);
        assertEquals(resultatUtilisateur.getIdResultatUtilisateur(),resultatUtilisateur2.getIdResultatUtilisateur());
        assertEquals(resultatUtilisateur.getIdQCM(),resultatUtilisateur2.getIdQCM());
        assertEquals(resultatUtilisateur.getDate(), resultatUtilisateur2.getDate());
        assertEquals(resultatUtilisateur.getIdUtilisateur(), resultatUtilisateur2.getIdUtilisateur());
    }

    @Test
    public void testAjoutGetQuestionReponsesResultatutilisateur() throws Exception {
        IResultatUtilisateur resultatUtilisateur = resultatUtilisateurDAO.creerResultatUtilisateur(
                utilisateur.getIdUtilisateur(), qcm.getIdQCM(), date);
        resultatUtilisateurDAO.addReponse(resultatUtilisateur.getIdResultatUtilisateur(), reponse1Question1.getIdReponse());
        resultatUtilisateurDAO.addReponse(resultatUtilisateur.getIdResultatUtilisateur(), reponse1Question2.getIdReponse());
        resultatUtilisateurDAO.addReponse(resultatUtilisateur.getIdResultatUtilisateur(), reponse2Question2.getIdReponse());
        List<IQuestion> questionReponses = resultatUtilisateurDAO.getQuestionReponseListResultatUtilisateur(resultatUtilisateur.getIdResultatUtilisateur());
        assertNotNull(resultatUtilisateur);
        assertNotNull(questionReponses);

        assertEquals(questionReponses.size(),2);
        //Les questions sont-elles identiques à celle de départ ?
        assertEquals(questionReponses.get(0).getIdQuestion(),question1.getIdQuestion());
        assertEquals(questionReponses.get(0).getPointQuestion(),question1.getPointQuestion());
        assertEquals(questionReponses.get(0).getDureeQuestion(),question1.getDureeQuestion());
        assertEquals(questionReponses.get(0).getIntituleQuestion(),question1.getIntituleQuestion());
        assertEquals(questionReponses.get(0).isMultipleQuestion(),question1.isMultipleQuestion());
        assertEquals(questionReponses.get(1).getIdQuestion(),question2.getIdQuestion());
        assertEquals(questionReponses.get(1).getPointQuestion(),question2.getPointQuestion());
        assertEquals(questionReponses.get(1).getDureeQuestion(),question2.getDureeQuestion());
        assertEquals(questionReponses.get(1).getIntituleQuestion(),question2.getIntituleQuestion());
        assertEquals(questionReponses.get(1).isMultipleQuestion(),question2.isMultipleQuestion());
        //Les réponses ?
        assertEquals(questionReponses.get(0).getReponses().size(),1);
        assertEquals(questionReponses.get(1).getReponses().size(),2);
        assertEquals(questionReponses.get(0).getReponses().get(0).getIdReponse(),reponse1Question1.getIdReponse());
        assertEquals(questionReponses.get(0).getReponses().get(0).getIntituleReponse(),reponse1Question1.getIntituleReponse());
        assertEquals(questionReponses.get(0).getReponses().get(0).isCorrectReponse(),reponse1Question1.isCorrectReponse());
        assertEquals(questionReponses.get(1).getReponses().get(0).getIdReponse(),reponse1Question2.getIdReponse());
        assertEquals(questionReponses.get(1).getReponses().get(0).getIntituleReponse(),reponse1Question2.getIntituleReponse());
        assertEquals(questionReponses.get(1).getReponses().get(0).isCorrectReponse(),reponse1Question2.isCorrectReponse());
        assertEquals(questionReponses.get(1).getReponses().get(1).getIdReponse(),reponse2Question2.getIdReponse());
        assertEquals(questionReponses.get(1).getReponses().get(1).getIntituleReponse(),reponse2Question2.getIntituleReponse());
        assertEquals(questionReponses.get(1).getReponses().get(1).isCorrectReponse(),reponse2Question2.isCorrectReponse());
    }

    @Test
    public void testCalculScoreResultatutilisateur() throws Exception{
        IResultatUtilisateur resultatUtilisateur = resultatUtilisateurDAO.creerResultatUtilisateur(
                utilisateur.getIdUtilisateur(), qcm.getIdQCM(), date);
        resultatUtilisateurDAO.addReponse(resultatUtilisateur.getIdResultatUtilisateur(), reponse1Question1.getIdReponse());
        resultatUtilisateurDAO.addReponse(resultatUtilisateur.getIdResultatUtilisateur(), reponse1Question2.getIdReponse());
        resultatUtilisateurDAO.addReponse(resultatUtilisateur.getIdResultatUtilisateur(), reponse2Question2.getIdReponse());
        assertNotNull(resultatUtilisateur);

        IResultatUtilisateur resultatUtilisateur2 = resultatUtilisateurDAO.calculerScore(resultatUtilisateur.getIdResultatUtilisateur());
        assertNotNull(resultatUtilisateur2);
        assertEquals(resultatUtilisateur.getIdResultatUtilisateur(),resultatUtilisateur2.getIdResultatUtilisateur());

        double scoreLocal = question2.getPointQuestion(); //Première question fausse, deuxième tout juste
        assertEquals(scoreLocal, resultatUtilisateur2.getScore(),0);
    }

    @Test
    public void testsuppressionResultatutilisateur() throws Exception {
        IResultatUtilisateur resultatUtilisateur = resultatUtilisateurDAO.creerResultatUtilisateur(
                        utilisateur.getIdUtilisateur(), qcm.getIdQCM(), date);
        int idResulatUtilisateur = resultatUtilisateur.getIdResultatUtilisateur();
        resultatUtilisateurDAO.suppressionResultatutilisateur(idResulatUtilisateur);
        IResultatUtilisateur resultatUtilisateur2 = resultatUtilisateurDAO.getResultatUtilisateur(idResulatUtilisateur);
        assertNotNull(resultatUtilisateur);
        assertNull(resultatUtilisateur2);
    }
}