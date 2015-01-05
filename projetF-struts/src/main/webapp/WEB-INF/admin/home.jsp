<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page d'administration</title>
</head>
<body>
<div class="container">
    <s:if test="%{listQCM.size==0}">
        <p><s:text name="global.noQCM"/></p>
    </s:if>
    <s:else>
        <s:iterator value="listQCM">
            <s:property value="nomQCM"/> - <s:property value="etatQCM"/><br/>
        </s:iterator>
    </s:else>
</div>
</body>
</html>
