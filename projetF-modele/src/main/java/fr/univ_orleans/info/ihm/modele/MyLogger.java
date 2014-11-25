package fr.univ_orleans.info.ihm.modele;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 * Permet de logger les messages de l'application.
 * Classe singleton.
 *
 * Niveaux de criticité :
 * ALL 	Tous les niveaux
 * SEVERE 	Niveau le plus élevé
 * WARNING 	Avertissement
 * INFO 	Information
 * CONFIG 	Configuration
 * FINE 	Niveau faible
 * FINER 	Niveau encore plus faible
 * FINEST 	Niveau le plus faible
 * OFF 	Aucun niveau
 */
public final class MyLogger {
    private static MyLogger instance = null;
    private static Logger logger = Logger.getLogger(MyLogger.class.getName());

    private MyLogger() {
        try {
            FileHandler fileHandler = new FileHandler("myLog.log", 10000, 1);
            fileHandler.setFormatter(new XMLFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.throwing(MyLogger.class.getName(), "Constructeur", e);
        }
        //On définit le niveau de criticité maximal pour lequel les logs seront éffectués
        logger.setLevel(Level.ALL);
    }

    /**
     * Permet d'obtenir le logger de la classe.
     * @return logger
     */
    public static Logger getLogger(){
        if(instance == null){
            instance = new MyLogger();
        }
        return logger;
    }
}
