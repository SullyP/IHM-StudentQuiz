package fr.univ_orleans.info.ihm.struts.action.def;

import com.opensymphony.xwork2.ActionSupport;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.struts.rmi.ModeleClient;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public abstract class ServiceAndSessionAwareAction extends ActionSupport implements SessionAware {
    private SessionMap<String, Object> session;
    private IModeleService modeleService;

    public ServiceAndSessionAwareAction() {
        this.session = null;
        this.modeleService = ModeleClient.getModeleServiceInstance();
    }

    protected SessionMap<String, Object> getSession() {
        return this.session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = (SessionMap<String, Object>) session;
    }

    protected IModeleService getModeleService() {
        return this.modeleService;
    }
}
