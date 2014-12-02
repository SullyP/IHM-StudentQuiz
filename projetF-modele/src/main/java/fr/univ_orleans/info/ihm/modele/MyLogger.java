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
    private static final Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
    public static final String MESSAGE_ERREUR_SQL = "Erreur lors de l'exécution d'une requête SQL.";

    private MyLogger() {
        try {
            FileHandler fileHandler = new FileHandler("myLog.log", 10000, 1);
            fileHandler.setFormatter(new XMLFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            LOGGER.throwing(MyLogger.class.getName(), "Constructeur", e);
        }
        //On définit le niveau de criticité maximal pour lequel les logs seront éffectués
        LOGGER.setLevel(Level.ALL);
    }

    /**
     * Permet d'obtenir le logger de la classe.
     * @return logger
     */
    public static Logger getLogger(){
        if(instance == null){
            instance = new MyLogger();
        }
        return LOGGER;
    }
}
