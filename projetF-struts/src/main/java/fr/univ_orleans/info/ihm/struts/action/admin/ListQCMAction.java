package fr.univ_orleans.info.ihm.struts.action.admin;

import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.rmi.RemoteException;
import java.util.List;

@ParentPackage(value = "admin")
@Namespace(value = "/admin")
public class ListQCMAction extends ServiceAndSessionAwareAction {
    private static final Logger LOGGER = Logger.getLogger(ListQCMAction.class.getCanonicalName());
    private List listQCM;

    @Action(value = "listQCM", results = {
            @Result(type = "tiles", location = "admin/listQCM.tiles")
    })
    @Override
    public String execute() {
        int idUser = (int) this.getSession().get("userId");
        try {
            this.listQCM = this.getModeleService().getListQCMByIdCreateur(idUser);
        } catch (RemoteException e) {
            LOGGER.fatal(e);
        }
        return SUCCESS;
    }

    public List getListQCM() {
        return this.listQCM;
    }
}