package fr.univ_orleans.info.ihm.modele.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CleanUpTest.class,
        EntiteTest.class,
        QCMTest.class,
        QuestionTest.class,
        ReponseTest.class,
        ResultatUtilisateurTest.class,
        UtilisateurTest.class
})
public class AllTests {
        public static final String DB_PATH = System.getProperty("user.home") + "/testUnit";
}
