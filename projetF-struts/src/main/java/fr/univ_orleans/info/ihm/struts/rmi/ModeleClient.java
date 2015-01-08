package fr.univ_orleans.info.ihm.struts.rmi;

import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.modele.rmi.InitRemoteService;

public final class ModeleClient {
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
