package fr.univ_orleans.info.ihm.struts.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends AbstractInterceptor implements StrutsStatics {
    private static final Logger LOGGER = Logger.getLogger(LoginInterceptor.class.getCanonicalName());

    public String intercept(ActionInvocation actionInvocation) throws InterceptorException {
        HttpServletRequest request = (HttpServletRequest) actionInvocation.getInvocationContext().get(HTTP_REQUEST);
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");

        if (userName != null) {
            try {
                return actionInvocation.invoke();
            } catch (Exception e) {
                LOGGER.warn(e);
                throw new InterceptorException();
            }
        }

        return Action.LOGIN;
    }

}