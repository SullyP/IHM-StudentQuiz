<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">
    <s:if test="%{listQCM.size == 0}">
        <p><s:text name="admin.listQCM.noQCM"/></p>
    </s:if>
    <s:else>
        <s:iterator value="listQCM">
            <s:property value="nomQCM"/> - <s:property value="etatQCM"/><br/>
        </s:iterator>
    </s:else>
</div>
