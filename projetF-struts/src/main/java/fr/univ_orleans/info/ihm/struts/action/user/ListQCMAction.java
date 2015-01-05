package fr.univ_orleans.info.ihm.struts.action.user;

import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.rmi.RemoteException;
import java.util.List;

@ParentPackage(value = "user")
@Namespace(value = "/user")
public class ListQCMAction extends ServiceAndSessionAwareAction {
    private static final Logger LOGGER = Logger.getLogger(ListQCMAction.class.getCanonicalName());
    private List listQCM;

    @Action(value = "listQCM", results = {
            @Result(type = "tiles", location = "user/listQCM.tiles")
    })
    @Override
    public String execute() {
        try {
            this.listQCM = this.getModeleService().getListQCMDispo();
        } catch (RemoteException e) {
            LOGGER.fatal(e);
        }
        return SUCCESS;
    }

    public List getListQCM() {
        return this.listQCM;
    }
}
