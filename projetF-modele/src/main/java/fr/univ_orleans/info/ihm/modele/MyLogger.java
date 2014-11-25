package fr.univ_orleans.info.ihm.modele;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 * Permet de logger les messages de l'application.
 * Classe singleton.
 */
public class MyLogger {
    private static MyLogger instance = new MyLogger();
    private Logger logger;

    private MyLogger() {
        logger = Logger.getLogger("ProjetFIHM");
        try {
            FileHandler fileHandler = new FileHandler("myLog.log", 10000, 1);
            fileHandler.setFormatter(new XMLFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.severe("Le FileHandler du logger n'a pas pu être créé. Exception: " + e.toString());
        }
        //On définit le niveau de criticité maximal pour lequel les logs seront éffectués
        logger.setLevel(Level.ALL);
    }

    /**
     * Permet d'obtenir l'instance de la classe singleton.
     * @return Instance de la classe singleton.
     */
    public static MyLogger getInstance() {
        return instance;
    }

    /**
     * Permet d'obtenir le logger de la classe.
     * @return logger
     */
    public Logger getLogger(){
        return logger;
    }
}
