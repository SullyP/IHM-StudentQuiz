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
    <script type="text/javascript" src="/struts/js/base/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.min.js?s2j=3.7.1"></script>
    <script type="text/javascript" src="/js/jquery-ui-i18n.min.js?s2j=3.7.1"></script>
    <script type="text/javascript" src="/struts/js/plugins/jquery.subscribe.min.js?s2j=3.7.1"></script>
    <script type="text/javascript" src="/struts/js/struts2/jquery.struts2.min.js?s2j=3.7.1"></script>
    <script type="text/javascript">
        $(function () {
            jQuery.struts2_jquery.version = "3.7.1";
            jQuery.scriptPath = "/struts/";
            jQuery.ajaxSettings.traditional = true;

            jQuery.ajaxSetup({
                cache: false
            });

            jQuery.struts2_jquery.require("js/struts2/jquery.ui.struts2.min.js?s2j=3.7.1");

        });
    </script>
    <link id="jquery_theme_link" rel="stylesheet"
          href="/css/jquery-ui.min.css?s2j=3.7.1" type="text/css"/>
    <link id="jquery_custom_link" rel="stylesheet" href="/css/jquery-ui-1.10.3.custom.css" type="text/css"/>
    <link id="jquery_theme_link" rel="stylesheet" href="/css/jquery-ui-1.10.3.theme.css" type="text/css"/>
    <link id="jquery_ie_link" rel="stylesheet" href="/css/jquery.ui.1.10.3.ie.css" type="text/css"/>
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
