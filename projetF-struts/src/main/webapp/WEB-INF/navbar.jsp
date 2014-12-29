<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<div class="row">
    <div class="col-md-12">
        <nav class="navbar navbar-default" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="">projetF-struts</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<s:url action="home" />">Accueil</a></li>
                    <li><a href="">À propos</a></li>
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown">Pages <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="">Action</a></li>
                            <li><a href="">Another action</a></li>
                            <li><a href="">Something else here</a></li>
                            <li class="divider"></li>
                            <li><a href="">Separated link</a></li>
                            <li class="divider"></li>
                            <li><a href="">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Chercher">
                    </div>
                    <button type="submit" class="btn btn-default">Chercher</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="http://google.fr">I love Google</a></li>
                    <li class="dropdown">
                        <a href="<s:url action="login" />" class="dropdown-toggle" data-toggle="dropdown">Connexion <b class="caret"></b></a>
                        <ul id="login-nav" class="dropdown-menu">
                            <li>
                                <div class="row">
                                    <div class="col-md-12">
                                        <s:form action="login" theme="bootstrap" cssClass="form">
                                            <div class="form-group">
                                                <s:textfield name="userName" placeholder="Identifiant" required="true" />
                                            </div>
                                            <div class="form-group">
                                                <s:password name="password" placeholder="Mot de passe" required="true" />
                                            </div>
                                            <s:submit cssClass="btn btn-success btn-block" value="Connexion" />
                                        </s:form>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </div>
</div>
