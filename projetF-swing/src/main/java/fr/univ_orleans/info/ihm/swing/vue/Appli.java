package fr.univ_orleans.info.ihm.swing.vue;


import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;

import javax.swing.*;
import java.awt.*;

public class Appli extends AppliInterface {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    JLabel statut = new JLabel();

    Login login;
    //QCM qcm;

    public Appli(String s, IModeleService service) {
        super(s);
        login = new Login(service);
        //qcm = new QCM(service);
        this.add(login, BorderLayout.CENTER);
        //this.add(qcm, BorderLayout.SOUTH);
        setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
