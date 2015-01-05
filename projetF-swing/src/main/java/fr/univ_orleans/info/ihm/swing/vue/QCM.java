package fr.univ_orleans.info.ihm.swing.vue;

import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;

public class QCM extends JPanel implements QCMInterface{

    JButton commencerQCM = new JButton("Commencer le QCM");
    public IModeleService monService;
    List listeQCM;
    private static final Logger LOGGER = Logger.getLogger(QCM.class.getCanonicalName());
    JPanel panel = new JPanel();

    public QCM(IModeleService service){

        this.monService=service;
        affichageQCM();
        //this.add(commencerQCM, BorderLayout.SOUTH);

        ActionListener commencerListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        for(int i=0;i<listeQCM.size();i++){
            panel.add((Component) listeQCM.get(i));
        }
    }
}
