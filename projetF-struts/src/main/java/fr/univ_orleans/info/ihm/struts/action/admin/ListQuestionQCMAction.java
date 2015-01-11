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
public class ListQuestionQCMAction extends ServiceAndSessionAwareAction {
    private static final Logger LOGGER = Logger.getLogger(ListQuestionQCMAction.class.getCanonicalName());
    private int idQCM;
    private String nomQCM;

    @Action(value = "listQuestionQCM", results = {
            @Result(type = "tiles", location = "admin/listQuestionQCM.tiles")
    })
    @Override
    public String execute() {
        try {
            IQCM qcm = this.getModeleService().getQCM(this.idQCM);
            this.nomQCM = qcm.getNomQCM();
        } catch (RemoteException e) {
            LOGGER.fatal(e);
        }
        return SUCCESS;
    }

    public int getIdQCM() {
        return idQCM;
    }

    public void setIdQCM(int idQCM) {
        this.idQCM = idQCM;
    }

    public String getNomQCM() {
        return nomQCM;
    }
}