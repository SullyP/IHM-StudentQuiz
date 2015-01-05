package fr.univ_orleans.info.ihm.struts.action.user;

import com.opensymphony.xwork2.ActionSupport;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.modele.rmi.InitRemoteService;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class ListQCMAction extends ActionSupport implements ApplicationAware {
    private static final Logger LOGGER = Logger.getLogger(ListQCMAction.class.getCanonicalName());
    private IModeleService monService;
    private List listQCM;

    public List getListQCM() {
        return listQCM;
    }

    @Override
    public String execute() {
        try {
            this.listQCM = this.monService.getListQCMDispo();
        } catch (RemoteException e) {
            LOGGER.fatal(e);
        }
        return SUCCESS;
    }

    @Override
    public void setApplication(Map<String, Object> application) {
        this.monService = (IModeleService) application.get("ModeleService");
        if (this.monService == null) {
            this.monService = InitRemoteService.getService();
            application.put("ModeleService", this.monService);
        }
    }
}
