<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<li><a href="<s:url namespace="%{getText('namespace.admin')}" action="%{getText('action.admin.listQCM')}"/>"><s:text
        name="global.pages.admin.listQCM"/></a></li>
<li><a href="<s:url namespace="%{getText('namespace.admin')}" action="%{getText('action.admin.listUser')}"/>"><s:text
        name="global.pages.admin.listUser"/></a></li>
