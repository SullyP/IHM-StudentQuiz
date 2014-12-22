package fr.univ_orleans.info.ihm.swing.vue;

import javax.swing.*;
import java.awt.*;


public class Reponse extends JPanel implements ReponseInterface {

    private JCheckBox check = new JCheckBox();

    public Reponse() {
        this.add(check, BorderLayout.WEST);
    }


}
