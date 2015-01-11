package fr.univ_orleans.info.ihm.swing.vue;

import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.JListQCM;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class QCM extends JPanel implements QCMInterface {

    private static final Logger LOGGER = Logger.getLogger(QCM.class.getCanonicalName());
    public JButton commencerQCM = new JButton("Commencer le QCM");
    private IModeleService monService;
    public JList listeQCM;
    JPanel panel = new JPanel();
    public JScrollPane sp ;
    public java.util.List<IQCM> list=null;

    public QCM(IModeleService service) {

        this.monService=service;
        listeQCM=new JList(new JListQCM(service));
        sp = new JScrollPane(listeQCM);
        listeQCM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recupListQCM();
        panel.add(sp);
        panel.add(commencerQCM);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);

    }

    public void recupListQCM(){
        try {
            list = monService.getListQCMDispo();
        } catch (RemoteException e) {
            LOGGER.trace(e);
        }
    }

    public IModeleService getMonService() {
        return monService;
    }

    public void setMonService(IModeleService monService) {
        this.monService = monService;
    }
}
