package fr.univ_orleans.info.ihm.swing.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QCM extends JPanel implements QCMInterface{

    JButton commencerQCM = new JButton("Commencer le QCM");

    public QCM(){

        affichageQCM();
        this.add(commencerQCM, BorderLayout.SOUTH);

        ActionListener commencerListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if(QCM en cours){
                    //afficher la premiere question
                }*/

            }
        };
        commencerQCM.addActionListener(commencerListener);

    }

    @Override
    public void affichageQCM() {

    }
}
