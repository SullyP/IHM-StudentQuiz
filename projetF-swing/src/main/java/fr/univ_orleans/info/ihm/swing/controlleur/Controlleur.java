package fr.univ_orleans.info.ihm.swing.controlleur;

import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.vue.Appli;
import fr.univ_orleans.info.ihm.swing.vue.Question;
import fr.univ_orleans.info.ihm.swing.vue.Resultat;

public class Controlleur implements ControlleurInterface{

    IModeleService service;
    Appli appli;

    public Controlleur(IModeleService service,Appli appli){
        this.service=service;
        this.appli=appli;
        initVue(service);
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
    public void changerVueLogin() {

    }

    @Override
    public void initVue(IModeleService service) {
        appli= new Appli("QCM",service);
    }

}
