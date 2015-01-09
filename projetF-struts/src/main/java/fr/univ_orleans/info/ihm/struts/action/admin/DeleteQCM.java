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
public class DeleteQCM extends ServiceAndSessionAwareAction {
    private static final Logger LOGGER = Logger.getLogger(AddQCM.class.getCanonicalName());
    private int idQCM;

    @Action(value = "deleteQCM", results = {
            @Result(type = "tiles", location = "admin/blank.tiles")
    })
    @Override
    public String execute() {
        try {
            IQCM qcm = this.getModeleService().getQCM(this.idQCM);
            int idUtilisateur = (int) this.getSession().get("userId");
            //On supprime le QCM uniquement si l'utilisateur connecté est le créateur du QCM
            if(idUtilisateur == qcm.getIdCreateurQCM()){
                this.getModeleService().suppressionQCM(this.idQCM);
            }
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
}