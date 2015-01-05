package fr.univ_orleans.info.ihm.struts.action;

import com.opensymphony.xwork2.Action;
import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;

public class LogoutAction extends ServiceAndSessionAwareAction {

    @Override
    public String execute() {
        this.getSession().invalidate();
        addActionMessage("Vous êtes désormais déconnecté.");

        return Action.SUCCESS;
    }
}
