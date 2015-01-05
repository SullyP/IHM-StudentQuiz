package fr.univ_orleans.info.ihm.swing.vue;

import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.Main;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class Login extends JPanel implements LoginInterface{

    JTextField log = new JTextField("",15);
    JLabel labellog = new JLabel("Identifiant");
    JPasswordField mdp = new JPasswordField("",15);
    JLabel labelmdp = new JLabel("Mot de passe");
    JButton connecter = new JButton("Connecter");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    IModeleService monService;
    IUtilisateur utilisateur = null;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getCanonicalName());

    public Login (IModeleService service){

        this.monService=service;
        panel1.add(log);
        panel1.add(labellog);
        panel2.add(mdp);
        panel2.add(labelmdp);
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(connecter, BorderLayout.SOUTH);

        ActionListener connecterListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = log.getText();
                String motdepasse = new String(mdp.getPassword());
                if(login.equals("") && motdepasse.equals("") ){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir les champs Identifiant et Mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else if(login.equals("")){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir le champs Identifiant.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else if(motdepasse.equals("")){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir le champs Mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else{
                    try {
                        utilisateur = monService.getUtilisateurByIdentifiant(login);
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
        };
        connecter.addActionListener(connecterListener);
    }
}
