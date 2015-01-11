package fr.univ_orleans.info.ihm.swing.vue;

import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.JListQCM;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;

public class QCM extends JPanel implements QCMInterface {

    JButton commencerQCM = new JButton("Commencer le QCM");
    private IModeleService monService;
    List list;
    JList listeQCM;
    private static final Logger LOGGER = Logger.getLogger(QCM.class.getCanonicalName());
    JPanel panel = new JPanel();
    JScrollPane sp ;

    public QCM(IModeleService service) {

        this.monService=service;
        //affichageQCM();
        listeQCM=new JList(new JListQCM(service));
        sp = new JScrollPane(listeQCM);
        panel.add(commencerQCM);

        //commencerQCM.addActionListener(commencerListener);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);

    }

    @Override
    public void affichageQCM() {

        try {
            this.list = monService.getListQCMDispo();


        } catch (RemoteException e1) {
            LOGGER.fatal(e1);
        }
        /*for (Object aListeQCM : listeQCM) {
            panel.add((Component) aListeQCM);
        }*/
    }

    public IModeleService getMonService() {
        return monService;
    }

    public void setMonService(IModeleService monService) {
        this.monService = monService;
    }
}
