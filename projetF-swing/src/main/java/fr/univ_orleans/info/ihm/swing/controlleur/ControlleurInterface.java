package fr.univ_orleans.info.ihm.swing.controlleur;

import fr.univ_orleans.info.ihm.swing.vue.QCM;
import fr.univ_orleans.info.ihm.swing.vue.Question;

/**
 * Created by Eléonore on 22/12/2014.
 */
public interface ControlleurInterface {

    //ajouter un QCM
    public void ajouterQCM ();

    //supprimer un QCM
    public void supprimerQCM (QCM qcm);

    //ajouter une question
    public void ajouterQuestion ();

    //supprimer une question
    public void supprimerQuestion (Question question);

}
