package fr.univ_orleans.info.ihm.struts.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminInterceptor extends AbstractInterceptor implements StrutsStatics {

    public String intercept(ActionInvocation actionInvocation) throws InterceptorException {
        HttpServletRequest request = (HttpServletRequest) actionInvocation.getInvocationContext().get(HTTP_REQUEST);
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        String userLevel = (String) session.getAttribute("userLevel");

        if (userName != null) {
            if (userLevel.equals("isAdmin")){
                try {
                    return actionInvocation.invoke();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new InterceptorException();
                }
            } else {
                return "isUser";
            }
        }

        return Action.LOGIN;
    }
}