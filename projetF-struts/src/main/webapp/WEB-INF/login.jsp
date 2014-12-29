<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="keywords" content="struts2, twitter, bootstrap, plugin, showcase" />
  <meta name="description" content="Enable Client Side Validation - A Showcase for the Struts2 Bootstrap Plugin" />
  <title>Client Side Validation - Struts2 Bootstrap Plugin Showcase</title>

  <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
  <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

  <sb:head includeScripts="true" includeScriptsValidation="true" />
  <sj:head jqueryui="true" />
</head>

<body>
<div class="navbar navbar-default">

  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target=".navbar-responsive-collapse">
      <span class="icon-bar"></span> <span class="icon-bar"></span> <span
            class="icon-bar"></span>
    </button>
  </div>

  <div class="navbar-collapse collapse navbar-responsive-collapse">
    <form class="navbar-form navbar-right">
      <input type="text" class="form-control" placeholder="Search">
    </form>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="home">Home</a></li>
      <li class="active"><a href="signup-input">Signup</a></li>
      <li><a href="login">Login</a></li>
      <li class="dropdown"><a href="#" class="dropdown-toggle"
                              data-toggle="dropdown">Explore<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="#">Contact us</a></li>
          <li class="divider"></li>
          <li><a href="#">Further Actions</a></li>
        </ul></li>
    </ul>
  </div>
</div>

<div class="container">
  <s:actionerror theme="bootstrap" />
  <s:actionmessage theme="bootstrap" />
  <s:fielderror theme="bootstrap" />

  <s:form action="login" theme="bootstrap" cssClass="form-horizontal" label="A sample horizontal Form">
    <s:textfield label="Identifiant" name="userName" tooltip="Enter your Name here" />
    <s:password label="Mot de passe" name="password" tooltip="Enter your Password here" />

    <s:submit cssClass="btn" />
  </s:form>

  <footer class="footer">
    <p class="pull-right"><a href="#">Back to top</a></p>
    <p>Let's go to <a href="http://google.fr" target="_blank">@Google</a>.</p>
  </footer>
</div> <!-- /container -->
</body>
</html>
