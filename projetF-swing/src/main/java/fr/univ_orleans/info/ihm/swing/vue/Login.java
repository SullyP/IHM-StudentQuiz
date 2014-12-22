package fr.univ_orleans.info.ihm.swing.vue;


import javax.swing.*;
import java.awt.*;

public class Login extends JPanel implements LoginInterface{

    JTextField log = new JTextField("");
    JLabel labellog = new JLabel("Identifiant");
    JTextField mdp = new JTextField("");
    JLabel labelmdp = new JLabel("Mot de passe");
    JButton valider = new JButton("Valider");
    JPanel panel = new JPanel();

    public Login (){
        panel.add(log);
        panel.add(labellog);
        panel.add(mdp);
        panel.add(labelmdp);
        this.add(panel, BorderLayout.CENTER);
        this.add(valider, BorderLayout.SOUTH);
    }
}
