package fr.univ_orleans.info.ihm.struts.rmi;

import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.modele.rmi.InitRemoteService;
import org.apache.log4j.Logger;

public class ModeleClient {
    private static final Logger LOGGER = Logger.getLogger(ModeleClient.class.getCanonicalName());
    private static ModeleClient instance = null;
    private IModeleService modeleService = null;

    private ModeleClient() {
        this.modeleService = InitRemoteService.getService();
    }

    public static IModeleService getModeleServiceInstance() {
        if (instance == null) {
            instance = new ModeleClient();
        }

        return instance.getModeleService();
    }

    private IModeleService getModeleService() {
        return this.modeleService;
    }
}
