<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">
    <div class="row">
        <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
            <div class="box">
                <div class="icon">
                    <div class="image">
                        <i class="glyphicon glyphicon-list" aria-hidden="true"></i>
                    </div>
                    <div class="info">
                        <h3 class="title"><s:property value="nomQCM"/></h3>
                        <s:hidden id="HiddenIdQCM" name="idQCM" value="%{idQCM}"/>
                        <div class="more">
                            <a href="#addQuestion_modal" data-toggle="modal" type="button">
                                <i class="glyphicon glyphicon-plus"></i> <s:text name="admin.listQuestionQCM.addQuestion"/>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="space"></div>
            </div>
        </div>
        <s:iterator value="listQuestionQCM">
            <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12" id="questionBox<s:property value="idQuestion"/>">
                <div class="box">
                    <div class="icon">
                        <div class="info">
                            <h3 class="title"><s:property value="intituleQuestion"/></h3>
                            <p>
                                <i class="glyphicon glyphicon-time" aria-hidden="true"></i> <s:property value="dureeQuestion"/> s
                                <i class="glyphicon glyphicon-certificate" aria-hidden="true"></i> <s:property value="pointQuestion"/> points
                                <s:if test="%{multipleQuestion}">
                                    <br>
                                    <s:text name="admin.listQuestionQCM.multipleQuestion"/>
                                </s:if>
                                <br>
                                <s:iterator value="reponses">
                                    <br>
                                    <span id="reponse<s:property value="idReponse"/>">
                                        <s:if test="%{correctReponse}">
                                            <i id="correct<s:property value="idReponse"/>" class="glyphicon glyphicon-check" aria-hidden="true"></i>
                                        </s:if>
                                        <s:else>
                                            <i id="correct<s:property value="idReponse"/>" class="glyphicon glyphicon-unchecked" aria-hidden="true"></i>
                                        </s:else>
                                        <s:property value="intituleReponse"/>
                                    </span>
                                </s:iterator>
                            </p>
                            <div class="more">
                                <a href="#editQuestion_modal" data-toggle="modal" type="button" data-whatever="<s:property value="idQuestion"/>">
                                    <i class="glyphicon glyphicon-pencil"></i> <s:text name="admin.listQuestionQCM.editQuestion"/>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="space"></div>
                </div>
            </div>
        </s:iterator>
    </div>
</div>
