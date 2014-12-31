package fr.univ_orleans.info.ihm.swing.vue;


import javax.swing.*;

public class Appli extends AppliInterface {

    JLabel statut = new JLabel();
    JButton connection = new JButton("Se déconnecter");

    public Appli(String s) {
        super(s);
    }
}
