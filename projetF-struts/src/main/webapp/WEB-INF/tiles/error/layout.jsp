<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
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

    <link href="${pageContext.request.contextPath}/css/error.css" rel="stylesheet" type="text/css"/>
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>

<body>
<tiles:insertAttribute name="body"/>
</body>

</html>
