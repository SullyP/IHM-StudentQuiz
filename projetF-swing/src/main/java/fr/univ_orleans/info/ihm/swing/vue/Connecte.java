package fr.univ_orleans.info.ihm.swing.vue;


import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;

import javax.swing.*;
import java.awt.*;

public class Connecte extends JPanel {

    JLabel labeldeco;
    JButton deconnecter = new JButton("Se déconnecter");
    JPanel panel3 = new JPanel();
    IUtilisateur utilisateur;
    IModeleService service;

    public Connecte(IUtilisateur utilisateur, IModeleService service){

        this.utilisateur=utilisateur;
        this.service=service;
        labeldeco=new JLabel(utilisateur.getPrenomUtilisateur()+utilisateur.getNomEntiteUtilisateur());
        panel3.add(labeldeco);
        panel3.add(deconnecter);
        this.add(panel3, BorderLayout.PAGE_START);

    }


}
