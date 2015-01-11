package fr.univ_orleans.info.ihm.swing;


import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.rmi.RemoteException;

public class JListQCM extends AbstractListModel {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getCanonicalName());

    IModeleService service;

    public JListQCM(IModeleService service){
        this.service=service;
    }

    @Override
    public int getSize() {
        try {
            return service.getListQCMDispo().size();
        } catch (RemoteException e) {
            LOGGER.trace(e);
        }
        return -1;
    }

    @Override
    public Object getElementAt(int index) {
        IQCM qcm = null;
        try {
            qcm = service.getListQCMDispo().get(index);
        } catch (RemoteException e) {
            LOGGER.trace(e);
        }
        return qcm.getNomQCM();
    }
}
