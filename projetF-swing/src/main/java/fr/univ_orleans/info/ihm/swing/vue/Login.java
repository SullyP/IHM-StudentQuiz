package fr.univ_orleans.info.ihm.swing.vue;

import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.Main;
import fr.univ_orleans.info.ihm.swing.controlleur.LoginListener;
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
    JLabel labeldeco;
    JButton deconnecter = new JButton("Se déconnecter");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    IModeleService monService;
    IUtilisateur utilisateur = null;


    public Login (IModeleService service){

        this.monService=service;
        panel1.add(log);
        panel1.add(labellog);
        panel2.add(mdp);
        panel2.add(labelmdp);
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(connecter, BorderLayout.SOUTH);
        String login=log.getText();
        String motdepasse=new String(mdp.getPassword());
        connecter.addActionListener(new LoginListener(log,mdp,monService,utilisateur));
    }


    @Override
    public void changerVue(IUtilisateur utilisateur) {


    }
}
