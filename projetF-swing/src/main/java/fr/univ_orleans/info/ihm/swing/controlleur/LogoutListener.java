package fr.univ_orleans.info.ihm.swing.controlleur;


import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.Main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener {

    IUtilisateur utilisateur=null;
    IModeleService service;
    LoginListener loginListener;

    public LogoutListener(IModeleService service, IUtilisateur utilisateur){
        this.utilisateur=utilisateur;
        this.service=service;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.appli.deco.setVisible(false);
        Main.appli.qcm.setVisible(false);
        this.utilisateur=null;
        //Main.appli.login=new Login(service,utilisateur);
        //Main.appli.add(Main.appli.login, BorderLayout.CENTER);
        Main.appli.login.setVisible(true);
        //this.loginListener=new LoginListener(service,utilisateur);
        //Main.appli.login.connecter.addActionListener(loginListener);
    }
}
