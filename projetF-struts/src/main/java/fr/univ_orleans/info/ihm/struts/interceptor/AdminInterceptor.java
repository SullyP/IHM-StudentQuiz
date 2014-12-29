package fr.univ_orleans.info.ihm.struts.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminInterceptor extends AbstractInterceptor implements StrutsStatics {

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest request = (HttpServletRequest) actionInvocation.getInvocationContext().get(HTTP_REQUEST);
        HttpSession session = request.getSession(true);
        IUtilisateur user = (IUtilisateur) session.getAttribute("userName");

        if (user != null) {
            if (user.isAdmin()){
                return actionInvocation.invoke();
            } else {
                return "isUser";
            }
        } else {
            //Sinon l'utilisateur doit se connecter.
            return Action.LOGIN;
        }
    }
}