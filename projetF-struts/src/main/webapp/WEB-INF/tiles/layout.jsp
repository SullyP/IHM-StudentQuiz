<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<tiles:insertAttribute name="description" ignore="true"/>"/>
    <meta name="keywords" content="<tiles:insertAttribute name="keywords" ignore="true"/>"/>

    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
    <sj:head jqueryui="true"/>
    <sb:head includeScripts="true" includeScriptsValidation="true"/>
    <tiles:importAttribute name="cssList"/>
    <s:iterator value="#attr.cssList" var="cssValue">
        <link href="${pageContext.request.contextPath}<s:url value="%{cssValue}"/>" rel="stylesheet" type="text/css">
    </s:iterator>
    <tiles:importAttribute name="jsList"/>
    <s:iterator value="#attr.jsList" var="jsValue">
        <script src="${pageContext.request.contextPath}<s:url value="%{jsValue}"/>"></script>
    </s:iterator>
    <s:set var="titleKey">
        <tiles:getAsString name="title" ignore="true"/>
    </s:set>
    <title><s:text name="title.website"/> | <s:text name="%{#titleKey}"/></title>
</head>

<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footer"/>
</body>

</html>
