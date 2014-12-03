package fr.univ_orleans.info.ihm.modele.test;

import fr.univ_orleans.info.ihm.modele.dao.IQuestionDAO;
import fr.univ_orleans.info.ihm.modele.dao.QuestionBaseDAO;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeH2;
import fr.univ_orleans.info.ihm.modele.modele.IQuestion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class QuestionTest {
    protected IQuestionDAO questionDAO;

    @Before
    public void setUp() {
        //Utilisation d'une base de donnée H2 uniquement pour le test
        BaseDonneeH2.getInstance().setDbPath("~/testUnit");
        questionDAO = QuestionBaseDAO.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreerQuestion() throws Exception {
        IQuestion question = questionDAO.creerQuestion("Comment vas-tu ?", false, 10, 2);
        assertEquals(question.getDureeQuestion(),10);
        assertEquals(question.getIntituleQuestion(), "Comment vas-tu ?");
        assertEquals(question.isMultipleQuestion(), false);
        assertEquals(question.getPointQuestion(), 2);
    }

    @Test
    public void testGetQuestionById() throws Exception {
        IQuestion question1 = questionDAO.creerQuestion("Comment vas-tu ?", false, 10, 2);
        IQuestion question2 = questionDAO.getQuestion(question1.getIdQuestion());
        assertEquals(question1.getPointQuestion(), question2.getPointQuestion());
        assertEquals(question1.getIntituleQuestion(), question2.getIntituleQuestion());
        assertEquals(question1.getIdQuestion(), question2.getIdQuestion());
        assertEquals(question1.getDureeQuestion(), question2.getDureeQuestion());
        assertEquals(question1.isMultipleQuestion(), question2.isMultipleQuestion());
    }

    @Test
    public void testMajQuestion() throws Exception {
        IQuestion question1 = questionDAO.creerQuestion("Comment vas-tu ?", false, 10, 2);
        IQuestion question2 = questionDAO.majQuestion(question1.getIdQuestion(), "Ca va ?", true, 3, 1);
        assertEquals(question1.getIdQuestion(), question2.getIdQuestion());
        assertEquals("Ca va ?", question2.getIntituleQuestion());
        assertEquals(true, question2.isMultipleQuestion());
        assertEquals(3, question2.getDureeQuestion());
        assertEquals(1, question2.getPointQuestion());
    }

    @Test
    public void testSuppresionQuestion() throws Exception {
        IQuestion question1 = questionDAO.creerQuestion("Comment vas-tu ?", false, 10, 2);
        int idQuestion = question1.getIdQuestion();
        questionDAO.suppressionQuestion(idQuestion);
        IQuestion question2 = questionDAO.getQuestion(idQuestion);
        assertNull(question2);
    }
}