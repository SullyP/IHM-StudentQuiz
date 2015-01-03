<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="vertical-center">
    <div class="container">
        <div class="row">
            <div class="col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4">
                <div id="form-login" class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Identification</h3>
                    </div>
                    <div class="panel-body">
                        <s:actionmessage theme="bootstrap"/>
                        <s:actionerror theme="bootstrap"/>
                        <s:fielderror theme="bootstrap"/>

                        <s:form action="login" theme="bootstrap" cssClass="form">
                            <fieldset>
                                <div class="form-group">
                                    <s:textfield name="userName" placeholder="Identifiant"/>
                                </div>
                                <div class="form-group">
                                    <s:password name="password" placeholder="Mot de passe"/>
                                </div>
                                <s:submit cssClass="btn btn-success btn-block" value="Connexion"/>
                            </fieldset>
                        </s:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
