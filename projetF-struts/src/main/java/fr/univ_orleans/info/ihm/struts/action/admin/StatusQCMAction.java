package fr.univ_orleans.info.ihm.struts.action.admin;

import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.rmi.RemoteException;

@ParentPackage(value = "admin")
@Namespace(value = "/admin")
public class StatusQCMAction extends ServiceAndSessionAwareAction {
    private static final Logger LOGGER = Logger.getLogger(StatusQCMAction.class.getCanonicalName());
    private int idQCM;

    @Action(value = "statusQCM", results = {
            @Result(type = "tiles", location = "admin/blank.tiles")
    })
    @Override
    public String execute() {
        try {
            IQCM qcm = this.getModeleService().getQCM(this.idQCM);
            if (qcm.isClosed()) {
                qcm.waiting();
            } else if (qcm.isWaiting()) {
                qcm.open();
            } else if (qcm.isOpened()) {
                qcm.close();
            }
        } catch (RemoteException e) {
            LOGGER.fatal(e);
        }
        return SUCCESS;
    }

    public int getIdQCM() {
        return this.idQCM;
    }

    public void setIdQCM(int idQCM) {
        this.idQCM = idQCM;
    }
}
