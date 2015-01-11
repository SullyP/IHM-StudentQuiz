package fr.univ_orleans.info.ihm.struts.action.admin;

import fr.univ_orleans.info.ihm.modele.beans.IQCM;
import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

@ParentPackage(value = "admin")
@Namespace(value = "/admin")
public class AddQCMAction extends ServiceAndSessionAwareAction {
    private static final Logger LOGGER = Logger.getLogger(AddQCMAction.class.getCanonicalName());
    private int idQCM;
    private String nomQCM;

    @Action(value = "addQCM", results = {
            @Result(type = "tiles", location = "admin/listQuestionQCM.tiles")
    })
    @Override
    public String execute() {
        //On ajoute le QCM si le nom n'est pas vide
        if(!this.nomQCM.isEmpty()) {
            try {
                int idUtilisateur = (int) this.getSession().get("userId");
                Date date = Calendar.getInstance().getTime();
                IQCM qcm = this.getModeleService().creerQCM(idUtilisateur, this.nomQCM, date);
                this.idQCM = qcm.getIdQCM();
            } catch (RemoteException e) {
                LOGGER.fatal(e);
            }
        }
        return SUCCESS;
    }

    public String getNomQCM() {
        return nomQCM;
    }

    public void setNomQCM(String nomQCM) {
        this.nomQCM = nomQCM;
    }

    public int getIdQCM() {
        return idQCM;
    }
}
