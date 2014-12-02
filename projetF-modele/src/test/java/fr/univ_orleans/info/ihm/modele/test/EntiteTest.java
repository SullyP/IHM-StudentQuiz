package fr.univ_orleans.info.ihm.modele.test;

import fr.univ_orleans.info.ihm.modele.dao.EntiteBaseDAO;
import fr.univ_orleans.info.ihm.modele.dao.IEntiteDAO;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeH2;
import fr.univ_orleans.info.ihm.modele.modele.IEntite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EntiteTest {
    protected IEntiteDAO entiteDAO;

    @Before
    public void setUp() {
        //Utilisation d'une base de donnée H2 uniquement pour le test
        BaseDonneeH2.getInstance().setDbPath("~/testUnit");
        entiteDAO = EntiteBaseDAO.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreerEntite() throws Exception {
        IEntite entite = entiteDAO.creerEntite("Nouvelle entité");
        assertEquals("Nouvelle entité", entite.getNomEntite());
    }

    @Test
    public void testGetEntiteById() throws Exception {
        IEntite entite1 = entiteDAO.creerEntite("Nouvelle entité");
        IEntite entite2 = entiteDAO.getEntite(entite1.getIdEntite());
        assertEquals(entite1.getIdEntite(), entite2.getIdEntite());
        assertEquals(entite1.getNomEntite(), entite2.getNomEntite());
    }

    @Test
    public void testMajEntite() throws Exception {
        IEntite entite1 = entiteDAO.creerEntite("Nouvelle");
        IEntite entite2 = entiteDAO.majEntite(entite1.getIdEntite(), "Changement");
        assertEquals(entite1.getIdEntite(), entite2.getIdEntite());
        assertEquals("Changement", entite2.getNomEntite());
    }

    @Test
    public void testSuppresionEntite() throws Exception {
        IEntite entite1 = entiteDAO.creerEntite("Nouvelle");
        int idEntite = entite1.getIdEntite();
        entiteDAO.suppressionEntite(idEntite);
        IEntite entite2 = entiteDAO.getEntite(idEntite);
        assertNull(entite2);
    }
}