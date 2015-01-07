<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="html" uri="/struts-tags" %>

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
              <s:form id="myform" namespace="/user" action="nextQuestion" theme="bootstrap" cssClass="form">
                <fieldset>
                      <s:if test="%{multipleQuestion}">
                        <s:iterator value="reponses">
                          <div class="form-group">
                            <s:checkbox name="%{idReponse}" label="%{intituleReponse}" value="false"/>
                          </div>
                        </s:iterator>
                      </s:if>
                      <s:else>
                        <div class="form-group">
                          <s:radio name="reponse" list="reponses" listKey="idReponse" listValue="intituleReponse"/>
                        </div>
                      </s:else>
                  <s:submit cssClass="btn btn-success" value="%{getText('global.validate')}"/>
                </fieldset>
              </s:form>
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
       // document.myform.submit(); //A la fin du timer, le formulaire est envoyé
      }
    });
  });
</script>