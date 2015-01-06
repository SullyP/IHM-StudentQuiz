<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">
  <div class="row">
      <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
        <div class="box">
          <div class="icon">
            <div class="image">
              <div id="timer"></div>
            </div>
            <div class="left-corner">
              <s:property value="dureeQuestion"/> s
            </div>
            <div class="right-corner">
              <s:property value="pointQuestion"/> points
            </div>
            <div class="info">
              <h3 class="title"><s:property value="intituleQuestion"/></h3>
              <p>
              <s:form id="myform" namespace="/user" action="nextQuestion" theme="bootstrap" cssClass="form">
                <fieldset>
                  <s:iterator value="reponses">
                    <div class="form-group">
                      <s:checkbox name="%{getText('idReponse')}" label="%{getText('intituleReponse')}"/> //comment récupérer les property ???
                    </div>
                  </s:iterator>
                  <s:submit cssClass="btn btn-success btn-block" value="%{getText('global.validate')}"/>
                </fieldset>
              </s:form>
              </p>
            </div>
          </div>
          <div class="space"></div>
        </div>
      </div>
  </div>
</div>
<script type="text/javascript">
  $(function() {
    $('#timer').pietimer({
      timerSeconds: <s:property value="dureeQuestion"/>,
      color: '#FFF',
      fill: false,
      showPercentage: true,
      callback: function() {
        document.myform.submit(); //A la fin du timer, le formulaire est envoyé
      }
    });
  });
</script>