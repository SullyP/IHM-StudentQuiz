package fr.univ_orleans.info.ihm.swing.vue;


import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.modele.beans.IQuestion;
import fr.univ_orleans.info.ihm.modele.beans.IResultatUtilisateur;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.*;

public class Question extends JPanel implements QuestionInterface {

    private static final Logger LOGGER = Logger.getLogger(QCM.class.getCanonicalName());
    public JButton valider = new JButton("Valider");
    public JLabel labelquestion;
    public JList listereponse;
    public JScrollPane sp ;
    JPanel panel = new JPanel();
    IQCM qcm;
    public int idqcm;
    java.util.List<IQuestion> listequestion;
    public IQuestion question=null;
    IModeleService service;
    IUtilisateur utilisateur;
    public IResultatUtilisateur resultatUtilisateur;

    public Question(IModeleService service, IUtilisateur utilisateur, int idqcm) {
        this.service=service;
        this.utilisateur=utilisateur;
        this.idqcm=idqcm;
        Date date = Calendar.getInstance().getTime();
        if(question==null) {
            try {
                resultatUtilisateur = this.service.creerResultatUtilisateur(this.utilisateur.getIdUtilisateur(), this.idqcm, date);
            } catch (RemoteException e) {
                LOGGER.trace(e);
            }

            try {
                this.qcm = this.service.getQCMWithQuestionList(this.idqcm);
            } catch (RemoteException e) {
                LOGGER.trace(e);
            }

            listequestion = qcm.getQuestions();

            try {
                question = this.service.getFirstQuestionQCM(this.idqcm, resultatUtilisateur.getIdResultatUtilisateur());
            } catch (RemoteException e) {
                LOGGER.trace(e);
            }
            labelquestion=new JLabel(question.getIntituleQuestion());
        }
        this.add(labelquestion, BorderLayout.CENTER);
        this.add(valider, BorderLayout.SOUTH);
        this.setVisible(true);
    }


    @Override
    public void affichageReponses(Question question) {
        return;
    }
}
