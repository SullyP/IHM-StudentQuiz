package fr.univ_orleans.info.ihm.modele.test;

import fr.univ_orleans.info.ihm.modele.dao.*;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeH2;
import fr.univ_orleans.info.ihm.modele.beans.IEntite;
import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.modele.beans.IQuestion;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class QCMTest {
    protected IQCMDAO qcmDAO;
    protected IUtilisateur utilisateur;

    @Before
    public void setUp() {
        //Utilisation d'une base de donnée H2 uniquement pour le test
        BaseDonneeH2.getInstance().setDbPath("~/testUnit");
        qcmDAO = QCMBaseDAO.getInstance();
        IEntite entite = EntiteBaseDAO.getInstance().creerEntite("New");
        utilisateur = UtilisateurBaseDAO.getInstance().creerUtilisateur("jean","paul","jp","azerty", 123456, entite.getIdEntite());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreerQCM() throws Exception {
        Date date = new Date(2014,12,23);
        IQCM qcm = qcmDAO.creerQCM(utilisateur.getIdUtilisateur(),"Test de connaissances IHM",date);
        assertNotNull(qcm);
        assertEquals(qcm.getDateCreationQCM(),date);
        assertEquals(qcm.getIdCreateurQCM(),utilisateur.getIdUtilisateur());
        assertEquals(qcm.getNomQCM(),"Test de connaissances IHM");
    }

    @Test
    public void testGetQCMById() throws Exception {
        Date date = new Date(2014,12,23);
        IQCM qcm1 = qcmDAO.creerQCM(utilisateur.getIdUtilisateur(), "Test de connaissances IHM", date);
        IQCM qcm2 = qcmDAO.getQCM(qcm1.getIdQCM());
        assertNotNull(qcm2);
        assertEquals(qcm1.getNomQCM(), qcm2.getNomQCM());
        assertEquals(qcm1.getIdCreateurQCM(), qcm2.getIdCreateurQCM());
        assertEquals(qcm1.getDateCreationQCM(), qcm2.getDateCreationQCM());
        assertEquals(qcm1.getIdQCM(), qcm1.getIdQCM());
    }

    @Test
    public void testGetQCMWithQuestionList() throws Exception {
        Date date = new Date(2014,12,23);
        IQCM qcm1 = qcmDAO.creerQCM(utilisateur.getIdUtilisateur(), "Test de connaissances IHM", date);
        assertNotNull(qcm1);
        IQuestion question1 = QuestionBaseDAO.getInstance().creerQuestion("Bonjour ?", false, 10, 5);
        assertNotNull(question1);
        IQuestion question2 = QuestionBaseDAO.getInstance().creerQuestion("Au Revoir ?", false, 10, 5);
        assertNotNull(question2);

        qcmDAO.ajoutQCMQuestion(qcm1.getIdQCM(), question1.getIdQuestion());
        qcmDAO.ajoutQCMQuestion(qcm1.getIdQCM(), question2.getIdQuestion());
        IQCM qcm2 = qcmDAO.getQCMWithQuestionList(qcm1.getIdQCM());
        assertNotNull(qcm2);
        assertEquals(qcm1.getNomQCM(), qcm2.getNomQCM());
        assertEquals(qcm1.getIdCreateurQCM(), qcm2.getIdCreateurQCM());
        assertEquals(qcm1.getDateCreationQCM(), qcm2.getDateCreationQCM());
        assertEquals(qcm1.getIdQCM(), qcm1.getIdQCM());

        assertNotNull(qcm2.getQuestions());
        assertEquals(qcm2.getQuestions().size(),2);
        assertEquals(qcm2.getQuestions().get(0).getIdQuestion(), question1.getIdQuestion());
        assertEquals(qcm2.getQuestions().get(1).getIdQuestion(), question2.getIdQuestion());
        assertEquals(qcm2.getQuestions().get(0).getDureeQuestion(), question1.getDureeQuestion());
        assertEquals(qcm2.getQuestions().get(1).getDureeQuestion(), question2.getDureeQuestion());
        assertEquals(qcm2.getQuestions().get(0).getIntituleQuestion(), question1.getIntituleQuestion());
        assertEquals(qcm2.getQuestions().get(1).getIntituleQuestion(), question2.getIntituleQuestion());
        assertEquals(qcm2.getQuestions().get(0).getPointQuestion(), question1.getPointQuestion());
        assertEquals(qcm2.getQuestions().get(1).getPointQuestion(), question2.getPointQuestion());
    }

    @Test
    public void testMajNomQCM() throws Exception {
        Date date = new Date(2014,12,23);
        IQCM qcm1 = qcmDAO.creerQCM(utilisateur.getIdUtilisateur(), "Test de connaissances IHM", date);
        IQCM qcm2 = qcmDAO.majNomQCM(qcm1.getIdQCM(),"Quizz IHM");
        assertNotNull(qcm2);
        assertEquals(qcm1.getIdQCM(),qcm2.getIdQCM());
        assertEquals(qcm2.getNomQCM(),"Quizz IHM");
    }

    @Test
    public void testAjoutEtSuppressionQCMQuestion() throws Exception{
        Date date = new Date(2014,12,23);
        IQCM qcm1 = qcmDAO.creerQCM(utilisateur.getIdUtilisateur(), "Test de connaissances IHM", date);
        assertNotNull(qcm1);
        IQuestion question1 = QuestionBaseDAO.getInstance().creerQuestion("Bonjour ?", false, 10, 5);
        assertNotNull(question1);
        IQuestion question2 = QuestionBaseDAO.getInstance().creerQuestion("Au Revoir ?", false, 10, 5);
        assertNotNull(question2);

        qcmDAO.ajoutQCMQuestion(qcm1.getIdQCM(), question1.getIdQuestion());
        qcmDAO.ajoutQCMQuestion(qcm1.getIdQCM(), question2.getIdQuestion());
        List<Integer> listIdQuestion = qcmDAO.getListIdQuestionQCM(qcm1.getIdQCM());
        assertNotNull(listIdQuestion);
        assertEquals(listIdQuestion.size(),2);
        assertEquals((int)listIdQuestion.get(0), question1.getIdQuestion());
        assertEquals((int)listIdQuestion.get(1), question2.getIdQuestion());

        qcmDAO.suppressionQCMQuestion(qcm1.getIdQCM(),question1.getIdQuestion());
        listIdQuestion = qcmDAO.getListIdQuestionQCM(qcm1.getIdQCM());
        assertNotNull(listIdQuestion);
        assertEquals(listIdQuestion.size(),1);
        assertEquals((int)listIdQuestion.get(0), question2.getIdQuestion());
    }

    @Test
    public void testSuppressionQCM() throws Exception {
        Date date = new Date(2014,12,23);
        IQCM qcm1 = qcmDAO.creerQCM(utilisateur.getIdUtilisateur(), "Test de connaissances IHM", date);
        int idQCM = qcm1.getIdQCM();
        qcmDAO.suppressionQCM(idQCM);
        IQCM qcm2 = qcmDAO.getQCM(idQCM);
        assertNull(qcm2);
    }
}