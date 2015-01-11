package fr.univ_orleans.info.ihm.swing.controlleur;


import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.Main;
import fr.univ_orleans.info.ihm.swing.vue.Connecte;
import fr.univ_orleans.info.ihm.swing.vue.QCM;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class LoginListener implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getCanonicalName());
    IUtilisateur utilisateur = null;
    IModeleService service;
    String login;
    String motdepasse;
    JTextField log;
    JPasswordField mdp;
    LogoutListener logoutListener;

    /*public LoginListener(JTextField log, JPasswordField mdp, IModeleService service, IUtilisateur utilisateur) {
        this.utilisateur = utilisateur;
        this.service = service;
        this.log = log;
        this.mdp = mdp;
    }*/

    public LoginListener(IModeleService service, IUtilisateur utilisateur){
        this.service=service;
        this.utilisateur=utilisateur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.log=Main.appli.login.log;
        this.mdp=Main.appli.login.mdp;
        login = this.log.getText();
        motdepasse = new String(this.mdp.getPassword());
        if("".equals(login) && "".equals(motdepasse)){
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs Identifiant et Mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(login)) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champs Identifiant.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(motdepasse)) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champs Mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                utilisateur = service.getUtilisateurByIdentifiant(login);
            } catch (RemoteException e1) {
                LOGGER.fatal(e1);
            }
            if (utilisateur == null) {
                JOptionPane.showMessageDialog(null, "L'identifiant n'est pas valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                if (utilisateur.validerMotDePasseUtilisateur(motdepasse)) {
                    JOptionPane.showMessageDialog(null, "identifiant et mot de passe valides", "Information", JOptionPane.INFORMATION_MESSAGE);
                    changerVueLogin();
                } else {
                    JOptionPane.showMessageDialog(null, "Le mot de passe n'est pas valide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void changerVueLogin() {
        Main.appli.login.setVisible(false);
        //Main.appli.remove(Main.appli.login);
        //if(Main.appli.deco==null && Main.appli.qcm==null) {
            Main.appli.deco = new Connecte(utilisateur, service);
            Main.appli.add(Main.appli.deco, BorderLayout.NORTH);
            Main.appli.qcm = new QCM(service);
            Main.appli.add(Main.appli.qcm, BorderLayout.CENTER);
            this.logoutListener = new LogoutListener(service, utilisateur);
            Main.appli.deco.deconnecter.addActionListener(logoutListener);
        //}
        /*else{

            Main.appli.deco.setVisible(true);
            Main.appli.qcm.setVisible(true);
        }*/
    }
}
