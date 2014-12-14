<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <s:if test="hasActionErrors()">
    <s:actionerror />
  </s:if>
  <s:if test="hasActionMessages()">
    <s:actionmessage />
  </s:if>

  <s:form action="login">
    <s:textfield label="Identifiant" name="identifiantUtilisateur" />
    <s:password label="Mot de passe" name="motDePasseUtilisateur"/>

    <s:submit label="Connection" name="submit" />
  </s:form>
</body>
</html>