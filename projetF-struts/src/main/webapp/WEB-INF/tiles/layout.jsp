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
    <%--<sj:head jqueryui="true"/>--%>
    <script src="${pageContext.request.contextPath}/struts/js/base/jquery-1.11.0.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/struts/js/plugins/jquery.subscribe.min.js?s2j=3.7.1"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/struts/js/struts2/jquery.struts2.min.js?s2j=3.7.1"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.validationEngine.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.validationEngine-fr.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            jQuery.struts2_jquery.version = "3.7.1";
            jQuery.scriptPath = "/struts/";
            jQuery.ajaxSettings.traditional = true;

            jQuery.ajaxSetup({
                cache: false
            });

            jQuery.struts2_jquery.require("${pageContext.request.contextPath}/js/struts2/jquery.ui.struts2.min.js?s2j=3.7.1");

        });
    </script>
    <sb:head includeScripts="true" includeScriptsValidation="true"/>
    <link href="${pageContext.request.contextPath}/css/validationEngine.jquery.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.bootstrap.css?s2j=3.7.1" rel="stylesheet"
          type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.bootstrap.min.js?s2j=3.7.1"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.bootstrap.i18n.js?s2j=3.7.1"
            type="text/javascript"></script>
    <%--<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js?s2j=3.7.1" type="text/javascript"></script>--%>
    <%--<script src="${pageContext.request.contextPath}/js/jquery-ui-i18n.min.js?s2j=3.7.1" type="text/javascript"></script>--%>
    <tiles:importAttribute name="cssList"/>
    <s:iterator value="#attr.cssList" var="cssValue">
        <link href="${pageContext.request.contextPath}<s:url value="%{cssValue}"/>" rel="stylesheet" type="text/css">
    </s:iterator>
    <tiles:importAttribute name="jsList"/>
    <s:iterator value="#attr.jsList" var="jsValue">
        <script src="${pageContext.request.contextPath}<s:url value="%{jsValue}"/>" type="text/javascript"></script>
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
