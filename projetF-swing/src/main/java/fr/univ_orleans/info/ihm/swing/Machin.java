package fr.univ_orleans.info.ihm.swing;

import fr.univ_orleans.info.ihm.modele.MyLogger;
import fr.univ_orleans.info.ihm.modele.service.IFacadeDAO;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;

public class Machin {

    public static final String HOST = "127.0.0.1";
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, 9345);
            IFacadeDAO facadeDAO = (IFacadeDAO) registry.lookup(IFacadeDAO.SERVICE_NAME);
            //facadeDAO.creerEntite("test");
        } catch (Exception e) {
            MyLogger.getLogger().logp(Level.SEVERE, Machin.class.getName(), "main", "ServiceRemote exception", e);
        }
    }
}
