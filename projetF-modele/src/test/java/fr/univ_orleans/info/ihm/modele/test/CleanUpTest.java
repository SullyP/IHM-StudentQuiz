package fr.univ_orleans.info.ihm.modele.test;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class CleanUpTest {

    @Test
    public void cleanMvDB() {
        try {
            File mvDB = new File(AllTests.DB_PATH + ".mv.db");

            mvDB.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cleanTraceDB() {
        try {
            File traceDB = new File(AllTests.DB_PATH + ".trace.db");

            traceDB.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
