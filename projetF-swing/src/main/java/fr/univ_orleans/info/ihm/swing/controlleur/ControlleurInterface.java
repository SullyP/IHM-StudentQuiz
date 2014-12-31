package fr.univ_orleans.info.ihm.swing.controlleur;

import fr.univ_orleans.info.ihm.swing.vue.Appli;
import fr.univ_orleans.info.ihm.swing.vue.Question;
import fr.univ_orleans.info.ihm.swing.vue.Resultat;

public interface ControlleurInterface {

    //changer de question pendant un QCM
    public Question questionSuivante (Question question);

    //modification du nombre d'utilisateurs ayant fini
    public Resultat modifUtilisateurFini(Resultat resultat);

    //pour changer de la vue
    public void changerVue(Appli appli);

    //initialise la vue
    public void initVue();

}
