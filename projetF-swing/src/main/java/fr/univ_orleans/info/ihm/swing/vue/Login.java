package fr.univ_orleans.info.ihm.swing.vue;

import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel implements LoginInterface {
    private static final int FIELD_COLUMN = 15;

    public JTextField log = new JTextField("", FIELD_COLUMN);
    JLabel labellog = new JLabel("Identifiant");
    public JPasswordField mdp = new JPasswordField("", FIELD_COLUMN);
    JLabel labelmdp = new JLabel("Mot de passe");
    public JButton connecter = new JButton("Connecter");

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    IModeleService monService;
    public IUtilisateur utilisateur = null;


    public Login (IModeleService service, IUtilisateur utilisateur){
        this.utilisateur=utilisateur;
        this.monService=service;
        panel1.add(labellog);
        panel1.add(log);
        panel2.add(labelmdp);
        panel2.add(mdp);
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(connecter, BorderLayout.SOUTH);
        this.setVisible(true);
    }

}
