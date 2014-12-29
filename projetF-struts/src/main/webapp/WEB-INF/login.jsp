<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="keywords" content="struts2, twitter, bootstrap, plugin, showcase" />
  <meta name="description" content="Enable Client Side Validation - A Showcase for the Struts2 Bootstrap Plugin" />
  <title>Login Page</title>

  <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

  <sj:head jqueryui="true" />
  <sb:head includeScripts="true" includeScriptsValidation="true" />
</head>

<body>
<div class="container">
  <s:actionerror theme="bootstrap" />
  <s:actionmessage theme="bootstrap" />
  <s:fielderror theme="bootstrap" />

  <s:form action="login" theme="bootstrap" cssClass="well form-horizontal">
    <s:textfield placeholder="Identifiant" name="userName" />
    <s:password placeholder="Mot de passe" name="password" />

    <s:submit cssClass="btn btn-primary" />
  </s:form>

  <%@ include file="footer.jsp" %>
</div> <!-- /container -->

</body>
</html>
