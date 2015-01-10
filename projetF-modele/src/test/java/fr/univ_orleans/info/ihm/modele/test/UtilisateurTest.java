package fr.univ_orleans.info.ihm.modele.test;

import fr.univ_orleans.info.ihm.modele.dao.EntiteBaseDAO;
import fr.univ_orleans.info.ihm.modele.dao.IEntiteDAO;
import fr.univ_orleans.info.ihm.modele.dao.IUtilisateurDAO;
import fr.univ_orleans.info.ihm.modele.dao.UtilisateurBaseDAO;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeH2;
import fr.univ_orleans.info.ihm.modele.beans.IEntite;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UtilisateurTest {
    protected IEntiteDAO entiteDAO;
    protected IUtilisateurDAO utilisateurDAO;

    @Before
    public void setUp() {
        //Utilisation d'une base de donnée H2 uniquement pour le test
        BaseDonneeH2.getInstance().setDbPath(AllTests.DB_PATH);
        entiteDAO = EntiteBaseDAO.getInstance();
        utilisateurDAO = UtilisateurBaseDAO.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreerUtilisateur() throws Exception {
        IEntite entite = entiteDAO.creerEntite("Nouvelle entité");
        IUtilisateur utilisateur = utilisateurDAO.creerUtilisateur("jean","paul","jp", "azerty", 123456, entite.getIdEntite());
        assertEquals(utilisateur.getPrenomUtilisateur(),"jean");
        assertEquals(utilisateur.getNomUtilisateur(),"paul");
        assertEquals(utilisateur.validerMotDePasseUtilisateur("azerty"), true);
        assertEquals(utilisateur.getIdentifiantUtilisateur(),"jp");
        assertEquals(utilisateur.getNumeroEtudiant(),123456);
    }

    @Test
    public void testGetUtilisateurById() throws Exception {
        IEntite entite = entiteDAO.creerEntite("Nouvelle entité");
        IUtilisateur utilisateur1 = utilisateurDAO.creerUtilisateur("jean", "paul", "jp", "azerty", 123456, entite.getIdEntite());
        IUtilisateur utilisateur2 = utilisateurDAO.getUtilisateur(utilisateur1.getIdUtilisateur());
        assertEquals(utilisateur1.getIdUtilisateur(), utilisateur2.getIdUtilisateur());
        assertEquals(utilisateur2.getIdEntiteUtilisateur(), entite.getIdEntite());
        assertEquals(utilisateur1.getPrenomUtilisateur(),utilisateur2.getPrenomUtilisateur());
        assertEquals(utilisateur1.getNomUtilisateur(),utilisateur2.getNomUtilisateur());
        assertEquals(utilisateur1.validerMotDePasseUtilisateur("azerty"), utilisateur2.validerMotDePasseUtilisateur("azerty"));
        assertEquals(utilisateur1.getIdentifiantUtilisateur(),utilisateur2.getIdentifiantUtilisateur());
        assertEquals(utilisateur1.getNumeroEtudiant(),utilisateur2.getNumeroEtudiant());
    }

    @Test
    public void testGetUtilisateurByIdentifiant() throws Exception {
        IUtilisateur utilisateur1 = utilisateurDAO.getUtilisateur(1);
        IUtilisateur utilisateur2 = utilisateurDAO.getUtilisateurByIdentifiant(utilisateur1.getIdentifiantUtilisateur());
        assertEquals(utilisateur1.getIdUtilisateur(), utilisateur2.getIdUtilisateur());
        assertEquals(utilisateur1.getIdEntiteUtilisateur(), utilisateur2.getIdEntiteUtilisateur());
        assertEquals(utilisateur1.getPrenomUtilisateur(),utilisateur2.getPrenomUtilisateur());
        assertEquals(utilisateur1.getNomUtilisateur(),utilisateur2.getNomUtilisateur());
        assertEquals(utilisateur1.getIdentifiantUtilisateur(),utilisateur2.getIdentifiantUtilisateur());
        assertEquals(utilisateur1.getNumeroEtudiant(),utilisateur2.getNumeroEtudiant());
    }

    @Test
    public void testMajEntiteutilisateur() throws Exception {
        IEntite entite1 = entiteDAO.creerEntite("Nouvelle");
        IEntite entite2 = entiteDAO.creerEntite("Old-School");
        IUtilisateur utilisateur1 = utilisateurDAO.creerUtilisateur("jean", "paul", "jp", "azerty", 123456, entite1.getIdEntite());
        IUtilisateur utilisateur2 = utilisateurDAO.majEntite(utilisateur1.getIdUtilisateur(), entite2.getIdEntite());
        assertEquals(utilisateur1.getIdUtilisateur(), utilisateur2.getIdUtilisateur());
        assertEquals(utilisateur2.getIdEntiteUtilisateur(), entite2.getIdEntite());
    }

    @Test
    public void testMajMotDePasseUtilisateur() throws Exception {
        IEntite entite1 = entiteDAO.creerEntite("Nouvelle");
        IUtilisateur utilisateur1 = utilisateurDAO.creerUtilisateur("jean", "paul", "jp", "azerty", 123456, entite1.getIdEntite());
        IUtilisateur utilisateur2 = utilisateurDAO.majMotDePasse(utilisateur1.getIdUtilisateur(), "azerty2");
        assertEquals(utilisateur1.getIdUtilisateur(), utilisateur2.getIdUtilisateur());
        assertEquals(utilisateur1.validerMotDePasseUtilisateur("azerty"), utilisateur2.validerMotDePasseUtilisateur("azerty2"));
    }

    @Test
    public void testSuppresionUtilisateur() throws Exception {
        IEntite entite = entiteDAO.creerEntite("Nouvelle entité");
        IUtilisateur utilisateur1 = utilisateurDAO.creerUtilisateur("jean", "paul", "jp", "azerty", 123456, entite.getIdEntite());
        int idUtilisateur = utilisateur1.getIdUtilisateur();
        utilisateurDAO.suppressionUtilisateur(idUtilisateur);
        IUtilisateur utilisateur2 = utilisateurDAO.getUtilisateur(idUtilisateur);
        assertNull(utilisateur2);
    }
}
