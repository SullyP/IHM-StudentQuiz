package fr.univ_orleans.info.ihm.struts.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends AbstractInterceptor implements StrutsStatics {

    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(HTTP_REQUEST);
        HttpSession session = request.getSession(true);

        //Un utilisateur est-il en session ?
        IUtilisateur utilisateur = (IUtilisateur) session.getAttribute("Utilisateur");
        if (utilisateur != null) {
            //Si un utilisateur est connecté, il peut accéder au site.
            return invocation.invoke();
        } else {
            //Sinon l'utilisateur doit se connecter.
            return "login";
        }
    }
}