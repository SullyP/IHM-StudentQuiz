package fr.univ_orleans.info.ihm.modele.rmi;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class InitRemoteService implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(InitRemoteService.class.getCanonicalName());
    private static final int PORT_REGISTRY = 9345;
    private static boolean isRegistered = false;
    private static IModeleService service;

    public InitRemoteService(){

    }

    public static IModeleService getService() {
        return service;
    }

    @Override
    public void contextInitialized(ServletContextEvent sCE) {
        if(!isRegistered){
            try {
                service = new ModeleService();
                IModeleService stub = (IModeleService) UnicastRemoteObject.exportObject(service, 0);
                Registry registry = LocateRegistry.createRegistry(PORT_REGISTRY);
                registry.rebind(ModeleService.SERVICE_NAME, stub);
                LOGGER.info("Remote service bound");
                isRegistered = true;
            } catch (Exception e) {
                LOGGER.error("Remote service exception:", e);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sCE) {
        LOGGER.info("Remote service destroyed");
    }
}
