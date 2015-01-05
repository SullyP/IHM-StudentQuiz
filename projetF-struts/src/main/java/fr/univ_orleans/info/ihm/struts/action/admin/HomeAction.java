package fr.univ_orleans.info.ihm.struts.action.admin;

import com.opensymphony.xwork2.ActionSupport;
import fr.univ_orleans.info.ihm.modele.rmi.IModeleService;
import fr.univ_orleans.info.ihm.modele.rmi.InitRemoteService;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class HomeAction extends ActionSupport  implements ApplicationAware {
    private static final Logger LOGGER = Logger.getLogger(HomeAction.class.getCanonicalName());
    private IModeleService monService;
    private List listQCM;

    public List getListQCM() {
        return listQCM;
    }

    @Override
    public String execute() {
        int idUser = (int) ServletActionContext.getRequest().getSession().getAttribute("userId");
        try {
           this.listQCM = this.monService.getListQCMByIdCreateur(idUser);
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