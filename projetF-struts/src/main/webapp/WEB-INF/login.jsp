<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<div class="container">
    <s:actionerror theme="bootstrap"/>
    <s:actionmessage theme="bootstrap"/>
    <s:fielderror theme="bootstrap"/>

    <s:form action="login" theme="bootstrap" cssClass="well form-inline">
        <s:textfield placeholder="Identifiant" name="userName"/>
        <s:password placeholder="Mot de passe" name="password"/>

        <s:submit cssClass="btn btn-success" value="Connexion"/>
    </s:form>
</div>
