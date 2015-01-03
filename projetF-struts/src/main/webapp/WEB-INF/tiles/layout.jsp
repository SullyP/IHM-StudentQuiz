<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<tiles:insertAttribute name="description" ignore="true" />"/>
    <meta name="keywords" content="<tiles:insertAttribute name="keywords" ignore="true" />"/>

    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <sj:head jqueryui="true"/>
    <sb:head includeScripts="true" includeScriptsValidation="true"/>
    <link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>

<body>
<tiles:insertAttribute name="header"/>
<%--<div class="container">--%>
<tiles:insertAttribute name="body"/>
<%--</div>--%>
<!-- /.container -->
<tiles:insertAttribute name="footer"/>
</body>

</html>
