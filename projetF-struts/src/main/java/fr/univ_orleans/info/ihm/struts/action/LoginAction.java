package fr.univ_orleans.info.ihm.struts.action;

import com.opensymphony.xwork2.Action;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;

public class LoginAction extends ServiceAndSessionAwareAction {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class.getCanonicalName());
    private String userName;
    private String password;

    public LoginAction() {
        super();
        userName = null;
        password = null;
    }

    @Override
    public String execute() {
        IUtilisateur user = null;

        if (this.userName != null) {
            try {
                user = this.getModeleService().getUtilisateurByIdentifiant(this.userName);
            } catch (RemoteException e) {
                LOGGER.fatal(e);
            }
            if (user == null) {
                addActionError(getText("login.validation.userName.wrong"));
                return Action.INPUT;
            } else if (user.validerMotDePasseUtilisateur(this.password)) {
                this.getSession().put("userId", user.getIdUtilisateur());
                this.getSession().put("userName", userName);
                this.getSession().put("firstName", user.getPrenomUtilisateur());
                this.getSession().put("lastName", user.getNomUtilisateur());
                this.getSession().put("studentID", user.getNumeroEtudiant());
                if (user.isAdmin()) {
                    this.getSession().put("userLevel", "admin");
                    return "isAdmin";
                } else {
                    this.getSession().put("userLevel", "user");
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
