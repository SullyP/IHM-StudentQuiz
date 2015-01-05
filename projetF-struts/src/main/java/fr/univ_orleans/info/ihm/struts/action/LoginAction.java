package fr.univ_orleans.info.ihm.struts.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.modele.rmi.InitRemoteService;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;

import java.rmi.RemoteException;
import java.util.Map;

public class LoginAction extends ActionSupport implements ApplicationAware {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class.getCanonicalName());
    private IModeleService monService;
    private String userName;
    private String password;

    public LoginAction() {
        super();
        monService = null;
        userName = null;
        password = null;
    }

    @Override
    public String execute() {
        IUtilisateur user = null;

        if (this.userName != null) {
            try {
                user = this.monService.getUtilisateurByIdentifiant(this.userName);
            } catch (RemoteException e) {
                LOGGER.fatal(e);
            }
            if (user == null) {
                addActionError(getText("login.validation.userName.wrong"));
                return Action.INPUT;
            } else if (user.validerMotDePasseUtilisateur(this.password)) {
                ServletActionContext.getRequest().getSession().setAttribute("userName", userName);
                ServletActionContext.getRequest().getSession().setAttribute("userId", user.getIdUtilisateur());
                if (user.isAdmin()) {
                    ServletActionContext.getRequest().getSession().setAttribute("userLevel", "admin");
                    return "isAdmin";
                } else {
                    ServletActionContext.getRequest().getSession().setAttribute("userLevel", "user");
                    return "isUser";
                }
            } else {
                addActionError(getText("login.validation.password.wrong"));
                return Action.INPUT;
            }
        }

        return Action.NONE;
    }

    @Override
    public void validate() {
        if (this.userName != null && this.userName.trim().length() == 0) {
            addActionError(getText("login.validation.userName.null"));
        }
    }

    @Override
    public void setApplication(Map<String, Object> application) {
        this.monService = (IModeleService) application.get("ModeleService");
        if (this.monService == null) {
            this.monService = InitRemoteService.getService();
            application.put("ModeleService", this.monService);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
