package fr.univ_orleans.info.ihm.swing;

import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.controlleur.Controlleur;
import fr.univ_orleans.info.ihm.swing.vue.Appli;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static final String HOST = "127.0.0.1";
    private static final Logger LOGGER = Logger.getLogger(Main.class.getCanonicalName());

    private Main() {
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                IModeleService modeleService = null;
                Appli appli = null;

                try {
                    Registry registry = LocateRegistry.getRegistry(HOST, 9345);
                    modeleService = (IModeleService) registry.lookup(IModeleService.SERVICE_NAME);
                } catch (Exception e) {
                    LOGGER.warn("Remote service exception", e);
                }
                try {
                    /* Select the Look and Style you love */
//                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel");
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel");
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    LOGGER.warn("Look and Feel exception", e);
                }

                new Controlleur(modeleService,appli);
            }
        });
    }
}
