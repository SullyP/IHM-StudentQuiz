package fr.univ_orleans.info.ihm.modele.rmi;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class InitRemoteService implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(InitRemoteService.class.getCanonicalName());
    private static final int PORT_REGISTRY = 1099;
    private static boolean isRegistered = false;
    private static IFacadeDAO service;

    public InitRemoteService(){

    }

    public static IFacadeDAO getService() {
        return service;
    }

    @Override
    public void contextInitialized(ServletContextEvent sCE) {
        if(!isRegistered){
            try {
                service = new FacadeBaseDAO();
                IFacadeDAO stub = (IFacadeDAO) UnicastRemoteObject.exportObject(service, 0);
                Registry registry = LocateRegistry.createRegistry(PORT_REGISTRY);
                registry.rebind(IFacadeDAO.SERVICE_NAME, stub);
                logger.info("Remote service bound");
                isRegistered = true;
            } catch (Exception e) {
                logger.error("Remote service exception:", e);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sCE) {
        logger.info("Remote service destroyed");
    }
}
