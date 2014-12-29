package fr.univ_orleans.info.ihm.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.modele.rmi.InitRemoteService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;

import java.rmi.RemoteException;
import java.util.Map;

public class LoginAction extends ActionSupport implements ApplicationAware {
    private IModeleService monService;
    private String userName;
    private String password;

    public LoginAction() {
        super();
        monService = null;
        userName = "";
        password = "";
    }

    @Override
    public String execute() {
        IUtilisateur user = null;
        try {
            user = this.monService.getUtilisateurByIdentifiant(this.userName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (user == null) {
            addActionError("Identifiant incorrect.");
            return INPUT;
        } else if (user.validerMotDePasseUtilisateur(this.password)) {
            ServletActionContext.getRequest().getSession().setAttribute("userName", user);
            if (user.isAdmin()) {
                return "isAdmin";
            } else {
                return "isUser";
            }
        } else {
            addActionError("Mot de passe incorrect.");
            return INPUT;
        }
    }

    @Override
    public void validate() {
        if (this.userName.trim().length() == 0) {
            addActionError("L'identifiant ne peut pas être vide.");
        }
    }

    @Override
    public void setApplication(Map<String, Object> stringObjectMap) {
        this.monService = (IModeleService) stringObjectMap.get("ModeleService");
        if (this.monService == null) {
            this.monService = InitRemoteService.getService();
            stringObjectMap.put("ModeleService", this.monService);
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
