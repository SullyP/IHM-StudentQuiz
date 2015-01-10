package fr.univ_orleans.info.ihm.swing.controlleur;


import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.Main;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class LoginListener implements ActionListener {

    IUtilisateur utilisateur=null;
    IModeleService service;
    String login;
    String motdepasse;
    JTextField log;
    JPasswordField mdp;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getCanonicalName());

    public LoginListener(JTextField log, JPasswordField mdp, IModeleService service, IUtilisateur utilisateur){
        this.utilisateur=utilisateur;
        this.service=service;
        this.log=log;
        this.mdp=mdp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        login=log.getText();
        motdepasse=new String(mdp.getPassword());
        if(login.equals("") && motdepasse.equals("") ){
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs Identifiant et Mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if(login.equals("")){
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champs Identifiant.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if(motdepasse.equals("")){
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champs Mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else{
            try {
                utilisateur = service.getUtilisateurByIdentifiant(login);
            } catch (RemoteException e1) {
                LOGGER.fatal(e1);
            }
            if(utilisateur == null){
                JOptionPane.showMessageDialog(null, "L'identifiant n'est pas valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }else {
                if (utilisateur.validerMotDePasseUtilisateur(motdepasse)) {
                    JOptionPane.showMessageDialog(null, "identifiant et mot de passe valides", "Information", JOptionPane.INFORMATION_MESSAGE);


                } else {
                    JOptionPane.showMessageDialog(null, "Le mot de passe n'est pas valide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
