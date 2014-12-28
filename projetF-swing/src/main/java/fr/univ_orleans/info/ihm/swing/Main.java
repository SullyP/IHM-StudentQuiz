package fr.univ_orleans.info.ihm.swing;

import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import org.apache.log4j.Logger;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getCanonicalName());
    public static final String HOST = "127.0.0.1";
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, 9345);
            IModeleService service = (IModeleService) registry.lookup(IModeleService.SERVICE_NAME);
        } catch (Exception e) {
            logger.warn("Remote service exception", e);
        }
    }
}
