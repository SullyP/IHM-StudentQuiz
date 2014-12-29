package fr.univ_orleans.info.ihm.struts.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

public class LogoutAction extends ActionSupport {

    @Override
    public String execute() {
        ServletActionContext.getRequest().getSession().invalidate();
        addActionMessage("Vous êtes désormais déconnecté.");
        return Action.SUCCESS;
    }
}
