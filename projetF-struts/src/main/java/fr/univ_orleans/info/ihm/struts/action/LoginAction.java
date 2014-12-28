package fr.univ_orleans.info.ihm.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.univ_orleans.info.ihm.modele.modele.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.rmi.IFacadeDAO;
import fr.univ_orleans.info.ihm.modele.rmi.InitRemoteService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;

import java.util.Map;

public class LoginAction extends ActionSupport implements ApplicationAware{
    private IFacadeDAO monService;
    private String identifiantUtilisateur;
    private String motDePasseUtilisateur;

    public void setIdentifiantUtilisateur(String identifiantUtilisateur) {
        this.identifiantUtilisateur = identifiantUtilisateur;
    }

    public void setMotDePasseUtilisateur(String motDePasseUtilisateur) {
        this.motDePasseUtilisateur = motDePasseUtilisateur;
    }

    @Override
    public String execute() {
        IUtilisateur utilisateur = this.monService.getUtilisateurByIdentifiant(this.identifiantUtilisateur);
        if(utilisateur == null){
            addActionError("Identifiant incorrect.");
            return INPUT;
        }else if(utilisateur.validerMotDePasseUtilisateur(this.motDePasseUtilisateur)){
            ServletActionContext.getRequest().getSession().setAttribute("Utilisateur", utilisateur);
            if(utilisateur.isAdmin()){
                return "admin";
            }else{
                return "nonAdmin";
            }
        }else{
            addActionError("Mot de passe incorrect.");
            return INPUT;
        }
    }

    @Override
    public void validate() {
        if("".equalsIgnoreCase(this.identifiantUtilisateur.trim()) || "".equalsIgnoreCase(this.motDePasseUtilisateur.trim())){
            addActionError("Le nom d'utilisateur et le mot de passe ne peuvent pas être vide.");
        }
    }

    @Override
    public void setApplication(Map<String, Object> stringObjectMap) {
        this.monService =(IFacadeDAO)stringObjectMap.get("monService");

        if (this.monService == null) {
            this.monService = InitRemoteService.getService();
            stringObjectMap.put("monService",this.monService);
        }
    }
}