package fr.univ_orleans.info.ihm.swing.controlleur;

import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.Main;
import fr.univ_orleans.info.ihm.swing.vue.Question;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class QCMListener implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(QCMListener.class.getCanonicalName());
    IUtilisateur utilisateur=null;
    IModeleService service;
    IQCM qcm;
    java.util.List<IQCM> list=null;
    QuestionListener questionListener;

    public QCMListener(IModeleService service, IUtilisateur utilisateur){
        this.service=service;
        this.utilisateur=utilisateur;
        list=Main.appli.qcm.list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int indexqcm=Main.appli.qcm.listeQCM.getSelectedIndex();
        if(indexqcm>=0 && indexqcm<list.size()) {
            String nomqcm = String.valueOf(Main.appli.qcm.listeQCM.getSelectedValue());
            qcm = list.get(indexqcm);
            int idqcm;
            if (nomqcm != null && qcm != null) {
                idqcm = qcm.getIdQCM();
                try {
                    if(service.getQCM(idqcm).isOpened()) {
                        Main.appli.qcm.setVisible(false);
                        Main.appli.question = new Question(service, utilisateur, idqcm);
                        Main.appli.add(Main.appli.question, BorderLayout.CENTER);
                        this.questionListener = new QuestionListener(service, utilisateur);
                        Main.appli.question.valider.addActionListener(questionListener);
                    }
                } catch (RemoteException e1) {
                    LOGGER.trace(e1);
                }
            }
        }
    }
}
