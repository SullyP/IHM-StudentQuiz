<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<footer class="footer">
    <div class="row">
        <div class="col-md-12">
            <div class="container">
                <p class="pull-right"><a class="cd-top" href="#"><s:text name="global.footer.backtotop"/></a></p>
                <s:if test="%{#session.userLevel == 'admin'}">
                    <%--<s:text name="global.footer.connectedas"/>--%><s:text name="global.admin"/> : <s:property value="#session.firstName"/> <s:property value="#session.lastName"/> (<a href="<s:url namespace="/" action="%{getText('action.logout')}"/>"><s:text name="logout"/></a>)
                </s:if>
                <s:elseif test="%{#session.userLevel == 'user'}">
                    <%--<s:text name="global.footer.connectedas"/>--%><s:text name="global.student"/> : <s:property value="#session.firstName"/> <s:property value="#session.lastName"/> (<a href="<s:url namespace="/" action="%{getText('action.logout')}"/>"><s:text name="logout"/></a>)
                </s:elseif>
                <s:else>
                    <s:text name="global.footer.notconnected"/> (<a href="<s:url namespace="/" action="%{getText('action.login')}"/>"><s:text name="login"/></a>)
                </s:else>
            </div>
        </div>
    </div>
</footer>
