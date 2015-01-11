package fr.univ_orleans.info.ihm.struts.action.admin;

import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage(value = "admin")
@Namespace(value = "/admin")
public class ListUserAction extends ServiceAndSessionAwareAction {

    @Action(value = "listUser", results = {
            @Result(type = "tiles", location = "admin/listUser.tiles")
    })
    @Override
    public String execute() {
        return SUCCESS;
    }
}