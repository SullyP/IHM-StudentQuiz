<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">
  <div class="row">
    <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
      <div class="box">
        <div class="icon">
          <div class="image">
            <i class="glyphicon glyphicon-ok" aria-hidden="true"></i>
          </div>
          <div class="info">
            <h3 class="title"><s:text name="user.endQCM.finish"/></h3>
            <p>
              <s:text name="user.endQCM.score"/> <s:property value="score"/> / <s:property value="scoreMax"/>
            </p>
            <div class="more">
              <s:url namespace="%{getText('namespace.user')}" action="%{getText('action.user.listQCM')}" var="urlTag" >
              </s:url>
              <a href="<s:property value="#urlTag" />">
                <s:text name="global.home"/> <i class="glyphicon glyphicon-chevron-right" aria-hidden="true"></i>
              </a>
            </div>
          </div>
        </div>
        <div class="space"></div>
      </div>
    </div>
  </div>
</div>