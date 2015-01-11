package fr.univ_orleans.info.ihm.swing.vue;


import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;

import javax.swing.*;
import java.awt.*;

public class Connecte extends JPanel {

    JLabel labeldeco;
    public JButton deconnecter = new JButton("Se déconnecter");
    JPanel panel3 = new JPanel();
    IUtilisateur utilisateur;
    IModeleService service;

    public Connecte(IUtilisateur utilisateur, IModeleService service){

        this.utilisateur=utilisateur;
        this.service=service;
        String prenom=this.utilisateur.getPrenomUtilisateur();
        String nom=this.utilisateur.getNomEntiteUtilisateur();
        labeldeco=new JLabel(prenom+" "+nom);
        panel3.add(labeldeco);
        panel3.add(deconnecter);
        this.add(panel3, BorderLayout.CENTER);
        this.setVisible(true);


    }


}
