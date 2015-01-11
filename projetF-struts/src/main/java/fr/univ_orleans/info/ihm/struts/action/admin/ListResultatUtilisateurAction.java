package fr.univ_orleans.info.ihm.struts.action.admin;

import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage(value = "admin")
@Namespace(value = "/admin")
public class ListResultatUtilisateurAction extends ServiceAndSessionAwareAction {
    private final static Logger LOGGER = Logger.getLogger(ListResultatUtilisateurAction.class.getCanonicalName());
    private int idQCM;
    private int scoreMax;

    @Action(value = "listResultatUtilisateur", results = {
            @Result(type = "tiles", location = "admin/listResultatUtilisateur.tiles")
    })
    @Override
    public String execute() {
        try{
            scoreMax = this.getModeleService().calculerScoreMaxQCM(idQCM);
        } catch (Exception e) {
            LOGGER.warn(e);
        }
        return SUCCESS;
    }

    public int getIdQCM() {
        return idQCM;
    }

    public void setIdQCM(int idQCM) {
        this.idQCM = idQCM;
    }

    public int getScoreMax() {
        return scoreMax;
    }
}