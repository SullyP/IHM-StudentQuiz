package fr.univ_orleans.info.ihm.modele.service;

import fr.univ_orleans.info.ihm.modele.MyLogger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;


public class InitRemoteService implements ServletContextListener {
    private static boolean isRegistered = false;
    private static IFacadeDAO service;
    private static final int PORT_REGISTRY = 9345;

    public InitRemoteService(){

    }

    public static IFacadeDAO getService() {
        return service;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if(!isRegistered){
            try {
                service = new FacadeBaseDAO();
                IFacadeDAO stub = (IFacadeDAO) UnicastRemoteObject.exportObject(service, 0);
                Registry registry = LocateRegistry.createRegistry(PORT_REGISTRY);
                registry.rebind(IFacadeDAO.SERVICE_NAME, stub);
                MyLogger.getLogger().logp(Level.INFO, InitRemoteService.class.getName(), "contextInitialized", "RemoteService Started");
                isRegistered = true;
            } catch (Exception e) {
                MyLogger.getLogger().logp(Level.SEVERE, InitRemoteService.class.getName(), "contextInitialized", "Init Fail", e);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MyLogger.getLogger().logp(Level.INFO, InitRemoteService.class.getName(), "contextDestroyed", "RemoteService Destroyed");
    }
}
