<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css" />
    <title>Test Page</title>

    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <sj:head jqueryui="true" />
    <sb:head includeScripts="true" includeScriptsValidation="true" />
</head>

<body>
<div class="container">
    <%@ include file="navbar.jsp" %>

    <p>Une page de test</p>

    <%@ include file="footer.jsp" %>
</div><!-- /.container -->

</body>
</html>
