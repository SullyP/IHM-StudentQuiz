package fr.univ_orleans.info.ihm.swing.vue;

import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class QCM extends JPanel implements QCMInterface {
    private static final Logger LOGGER = Logger.getLogger(QCM.class.getCanonicalName());
    JButton commencerQCM = new JButton("Commencer le QCM");
    List listeQCM;
    JPanel panel = new JPanel();
    private IModeleService monService;

    public QCM(IModeleService service) {

        this.monService = service;
        affichageQCM();
/*
        this.add(commencerQCM, BorderLayout.SOUTH);
*/

        ActionListener commencerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        };
        commencerQCM.addActionListener(commencerListener);
        this.add(panel, BorderLayout.CENTER);

    }

    @Override
    public void affichageQCM() {
        try {
            listeQCM = monService.getListQCMDispo();
        } catch (RemoteException e1) {
            LOGGER.fatal(e1);
        }
        for (Object aListeQCM : listeQCM) {
            panel.add((Component) aListeQCM);
        }
    }

    public IModeleService getMonService() {
        return monService;
    }

    public void setMonService(IModeleService monService) {
        this.monService = monService;
    }
}
