package fr.univ_orleans.info.ihm.swing.controlleur;

import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.Main;
import fr.univ_orleans.info.ihm.swing.vue.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class QuestionListener implements ActionListener {

    IUtilisateur utilisateur=null;
    IModeleService service;

    public QuestionListener(IModeleService service,IUtilisateur utilisateur){
        this.service=service;
        this.utilisateur=utilisateur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Main.appli.question.question=service.getNextQuestionQCM(Main.appli.question.idqcm,Main.appli.question.resultatUtilisateur.getIdResultatUtilisateur());
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
        Main.appli.question.setVisible(false);
        Main.appli.question.labelquestion.setText(Main.appli.question.question.getIntituleQuestion());
        Main.appli.question.labelquestion.revalidate();
        Main.appli.question.setVisible(true);
    }
}
