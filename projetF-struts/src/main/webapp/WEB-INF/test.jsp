<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>

    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <sj:head jqueryui="true"/>
    <sb:head includeScripts="true" includeScriptsValidation="true"/>
    <link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <title>Test Page</title>
</head>

<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <p><s:text name="global.appName"/></p>
</div>
<!-- /.container -->

<%@ include file="footer.jsp" %>
</body>
</html>
