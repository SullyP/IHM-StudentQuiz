<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="vertical-center">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4">
                <div id="form-login" class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <s:if test="%{#session.userName == null}">
                                <s:text name="login.identification"/>
                            </s:if>
                            <s:else>
                                <s:text name="login.already-signed-in"/>
                            </s:else>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <s:actionmessage theme="bootstrap"/>
                        <s:actionerror theme="bootstrap"/>
                        <s:fielderror theme="bootstrap"/>

                        <s:if test="%{#session.userName == null && #session.userLevel == null}">
                            <s:form namespace="/" action="login" theme="bootstrap" cssClass="form">
                                <div class="form-group">
                                    <s:textfield name="userName" placeholder="%{getText('login.userName')}"/>
                                </div>
                                <div class="form-group">
                                    <s:password name="password" placeholder="%{getText('login.password')}"/>
                                </div>
                                <s:submit cssClass="btn btn-success btn-block" value="%{getText('login')}"/>
                            </s:form>
                        </s:if>
                        <s:else>
                            <s:form namespace="/" action="logout" theme="bootstrap" cssClass="form">
                                <s:submit cssClass="btn btn-danger btn-block" value="%{getText('logout')}"/>
                            </s:form>
                        </s:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
