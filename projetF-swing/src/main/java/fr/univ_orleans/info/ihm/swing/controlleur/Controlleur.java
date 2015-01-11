package fr.univ_orleans.info.ihm.swing.controlleur;

import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.Main;
import fr.univ_orleans.info.ihm.swing.vue.*;


public class Controlleur implements IControlleur {

    IModeleService service;
    IUtilisateur utilisateur;
    LoginListener loginListener;

    public Controlleur(IModeleService service) {
        this.service = service;
        initVue(service);
        this.loginListener=new LoginListener(service,utilisateur);
        Main.appli.login.connecter.addActionListener(loginListener);

    }

    @Override
    public Question questionSuivante(Question question) {
        return null;
    }

    @Override
    public Resultat modifUtilisateurFini(Resultat resultat) {
        return null;
    }

    @Override
    public void initVue(IModeleService service) {
        Main.appli = new Appli("QCM",service);
    }

}
