package fr.univ_orleans.info.ihm.swing.vue;

import javax.swing.*;
import java.awt.*;

public class QCM extends JPanel implements QCMInterface{

    JButton supp = new JButton("-");

    public QCM(){
        this.add(supp, BorderLayout.BEFORE_LINE_BEGINS);
    }

}
