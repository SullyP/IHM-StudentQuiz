package fr.univ_orleans.info.ihm.swing.vue;


import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;

import javax.swing.*;
import java.awt.*;

public class Appli extends AppliInterface {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    //JLabel statut = new JLabel();
    IUtilisateur utilisateur = null;
    public Login login;
    public Connecte deco;
    public QCM qcm;
    public Question question;

    public Appli(String s, IModeleService service) {
        super(s);
        login = new Login(service,utilisateur);
        deco = null;
        qcm = null;
        question = null;
        this.add(login, BorderLayout.CENTER);
        setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
