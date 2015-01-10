package fr.univ_orleans.info.ihm.swing.controlleur;

import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.swing.vue.Question;
import fr.univ_orleans.info.ihm.swing.vue.Resultat;

public interface IControlleur {

    //changer de question pendant un QCM
    public Question questionSuivante(Question question);

    //modification du nombre d'utilisateurs ayant fini
    public Resultat modifUtilisateurFini(Resultat resultat);

    //pour changer de vue une fois connecté
    public void changerVueLogin();

    //initialise la vue
    public void initVue(IModeleService service);

}
