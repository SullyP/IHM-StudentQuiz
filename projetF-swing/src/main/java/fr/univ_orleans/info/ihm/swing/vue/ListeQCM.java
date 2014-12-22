package fr.univ_orleans.info.ihm.swing.vue;


import javax.swing.*;
import java.awt.*;

public class ListeQCM extends JPanel implements ListeQCMInterface {

    JButton ajouter = new JButton("Ajouter un QCM");
    JTextField texte = new JTextField("Voici la liste des QCMs disponibles :");

    /*public void paintComponent(Graphics g){
        Font font = new Font("Courier", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString("Voici la liste des QCMs disponibles :", 10, 20);
    }*/

    public ListeQCM(){
        this.add(texte, BorderLayout.NORTH);
        //condition "en tant qu'admin"
        this.add(ajouter, BorderLayout.SOUTH);
    }

}
