package fr.univ_orleans.info.ihm.swing.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel implements LoginInterface{

    JTextField log = new JTextField("");
    JLabel labellog = new JLabel("Identifiant");
    JPasswordField mdp = new JPasswordField();
    JLabel labelmdp = new JLabel("Mot de passe");
    JButton connecter = new JButton("Connecter");
    JPanel panel = new JPanel();

    public Login (){
        panel.add(log);
        panel.add(labellog);
        panel.add(mdp);
        panel.add(labelmdp);
        this.add(panel, BorderLayout.CENTER);
        this.add(connecter, BorderLayout.SOUTH);

        ActionListener connecterListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = log.getText();
                String motdepasse = new String(mdp.getPassword());
                if(login == null && motdepasse == null ){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir les champs Identifiant et Mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                else if(login == null){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir le champs Identifiant.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                else if(motdepasse == null ){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir le champs Mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                else{

                }
            }
        };
        connecter.addActionListener(connecterListener);
    }
}
