package fr.univ_orleans.info.ihm.modele.test;

import fr.univ_orleans.info.ihm.modele.dao.IReponseDAO;
import fr.univ_orleans.info.ihm.modele.dao.QuestionBaseDAO;
import fr.univ_orleans.info.ihm.modele.dao.ReponseBaseDAO;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeH2;
import fr.univ_orleans.info.ihm.modele.modele.IQuestion;
import fr.univ_orleans.info.ihm.modele.modele.IReponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ReponseTest {
    protected IReponseDAO reponseDAO;
    protected IQuestion question;

    @Before
    public void setUp() {
        //Utilisation d'une base de donnée H2 uniquement pour le test
        BaseDonneeH2.getInstance().setDbPath("~/testUnit");
        reponseDAO = ReponseBaseDAO.getInstance();
        question = QuestionBaseDAO.getInstance().creerQuestion("Comment vas-tu ?", false, 10, 2);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreerReponse() throws Exception {
        IReponse reponse = reponseDAO.creerReponse(question.getIdQuestion(), "Mieux qu'hier, moins bien que demain.", true);
        assertEquals(reponse.getIntituleReponse(), "Mieux qu'hier, moins bien que demain.");
        assertEquals(reponse.isCorrectReponse(), true);
    }

    @Test
    public void testGetReponseById() throws Exception {
        IReponse reponse1 = reponseDAO.creerReponse(question.getIdQuestion(), "Mieux qu'hier, moins bien que demain.", true);
        IReponse reponse2 = reponseDAO.getReponse(reponse1.getIdReponse());
        assertEquals(reponse1.getIntituleReponse(), reponse2.getIntituleReponse());
        assertEquals(reponse1.isCorrectReponse(), reponse2.isCorrectReponse());
        assertEquals(reponse1.getIdReponse(), reponse2.getIdReponse());
    }

    @Test
    public void testGetReponsesByIdQuestion() throws Exception {
        IReponse reponse1 = reponseDAO.creerReponse(question.getIdQuestion(), "Mieux qu'hier, moins bien que demain.", true);
        IReponse reponse2 = reponseDAO.creerReponse(question.getIdQuestion(), "A fond la forme", false);
        assertNotNull(reponse1);
        assertNotNull(reponse2);
        List<IReponse> reponseList = reponseDAO.getReponsesByIdQuestion(question.getIdQuestion());
        assertNotNull(reponseList);
        assertEquals(2,reponseList.size());

        assertEquals(reponseList.get(0).getIdReponse(),reponse1.getIdReponse());
        assertEquals(reponseList.get(0).getIntituleReponse(),reponse1.getIntituleReponse());
        assertEquals(reponseList.get(0).isCorrectReponse(),reponse1.isCorrectReponse());

        assertEquals(reponseList.get(1).getIdReponse(),reponse2.getIdReponse());
        assertEquals(reponseList.get(1).getIntituleReponse(),reponse2.getIntituleReponse());
        assertEquals(reponseList.get(1).isCorrectReponse(),reponse2.isCorrectReponse());
    }

    @Test
    public void testMajReponse() throws Exception {
        IReponse reponse1 = reponseDAO.creerReponse(question.getIdQuestion(), "Mieux qu'hier, moins bien que demain.", true);
        IReponse reponse2 = reponseDAO.majReponse(reponse1.getIdReponse(), "A fond la forme", false);
        assertEquals(reponse2.getIntituleReponse(), "A fond la forme");
        assertEquals(reponse1.isCorrectReponse(), !reponse2.isCorrectReponse());
        assertEquals(reponse1.getIdReponse(), reponse2.getIdReponse());
    }

    @Test
    public void testSuppresionReponse() throws Exception {
        IReponse reponse1 = reponseDAO.creerReponse(question.getIdQuestion(), "Mieux qu'hier, moins bien que demain.", true);
        int idReponse = reponse1.getIdReponse();
        reponseDAO.suppressionReponse(idReponse);
        IReponse reponse2 = reponseDAO.getReponse(idReponse);
        assertNull(reponse2);
    }
}