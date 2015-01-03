package fr.univ_orleans.info.ihm.struts.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Permet d'éviter à un utilisateur de se connecter s'il est déjà connecté
 */
public class HomeAction extends ActionSupport {

    @Override
    public String execute() {
        return Action.SUCCESS;
    }

}
