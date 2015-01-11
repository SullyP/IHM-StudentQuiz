<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#navbar-collapse-menu">
                        <span class="sr-only"><s:text name="global.navbar.collapse-menu"/></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<s:url namespace="/" action=""/>"><s:text name="global.appName"/></a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="<s:url namespace="/" action="home"/>"><s:text
                                name="global.home"/></a></li>
                        <li><a href=""><s:text name="global.about"/></a></li>
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle" data-toggle="dropdown"><s:text
                                    name="global.navbar.pages"/> <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <s:if test="%{#session.userLevel == 'admin'}">
                                    <%@ include file="pages/admin.jsp" %>
                                </s:if>
                                <s:elseif test="%{#session.userLevel == 'user'}">
                                    <%@ include file="pages/user.jsp" %>
                                </s:elseif>
                                <s:else>
                                    <%@ include file="pages/visitor.jsp" %>
                                </s:else>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <s:textfield cssClass="form-control" placeholder="%{getText('global.search')}"/>
                        </div>
                        <button type="submit" class="btn btn-default"><s:text name="global.search"/></button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <s:if test="%{#session.userName == null}">
                            <li class="dropdown">
                                <a href="<s:url namespace="/" action="login"/>" class="dropdown-toggle"
                                   data-toggle="dropdown">Connexion
                                    <b class="caret"></b></a>
                                <ul id="navbar-login" class="dropdown-menu">
                                    <li>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <s:form namespace="/" action="login" theme="bootstrap" cssClass="form">
                                                    <fieldset>
                                                        <div class="form-group">
                                                            <s:textfield name="userName" placeholder="%{getText('login.userName')}"/>
                                                        </div>
                                                        <div class="form-group">
                                                            <s:password name="password" placeholder="%{getText('login.password')}"/>
                                                        </div>
                                                        <s:submit cssClass="btn btn-success btn-block"
                                                                  value="%{getText('login')}"/>
                                                    </fieldset>
                                                </s:form>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </s:if>
                        <s:else>
                            <li><a href="<s:url namespace="/" action="logout"/>"><s:text name="logout"/></a></li>
                        </s:else>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
        </div>
    </div>
</nav>
