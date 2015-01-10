package fr.univ_orleans.info.ihm.swing.vue;


import javax.swing.*;
import java.awt.*;

public class Question extends JPanel implements QuestionInterface {

    JButton valider = new JButton("Valider");

    public Question() {
        this.add(valider, BorderLayout.SOUTH);
    }


    @Override
    public void affichageReponses(Question question) {
        return;
    }
}
