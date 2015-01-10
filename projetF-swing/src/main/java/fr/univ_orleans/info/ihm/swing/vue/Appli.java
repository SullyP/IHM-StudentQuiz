package fr.univ_orleans.info.ihm.swing.vue;


import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Appli extends AppliInterface {

    JLabel statut = new JLabel();

    Login login;
    //QCM qcm;


    public Appli(String s, IModeleService service) {
        super(s);
        login = new Login(service);
        //qcm = new QCM(service);
        this.add(login, BorderLayout.CENTER);
        //this.add(qcm, BorderLayout.SOUTH);
        setSize(600, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
