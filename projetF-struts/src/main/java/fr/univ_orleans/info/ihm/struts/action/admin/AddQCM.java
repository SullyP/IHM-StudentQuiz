package fr.univ_orleans.info.ihm.struts.action.admin;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage(value = "admin")
@Namespace(value = "/admin")
public class AddQCM extends ActionSupport {
    private static final Logger LOGGER = Logger.getLogger(AddQCM.class.getCanonicalName());

    @Action(value = "addQCM", results = {
            @Result(type = "tiles", location = "admin/addQCM.tiles")
    })
    @Override
    public String execute() {

        return SUCCESS;
    }
}
