package fr.univ_orleans.info.ihm.struts.action.def;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public abstract class SuccessAction extends ActionSupport {

    @Override
    public String execute() {
        return Action.SUCCESS;
    }
}
