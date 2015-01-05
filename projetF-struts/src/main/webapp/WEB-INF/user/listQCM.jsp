<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">
    <s:if test="%{listQCM.size == 0}">
        <p><s:text name="user.listQCM.noQCM"/></p>
    </s:if>
    <s:else>
        <div class="row">
            <s:iterator value="listQCM">
                <div class="col-lg-2 col-sm-6 col-xs-12 col-md-4">
                    <div class="box">
                        <div class="icon">
                            <div class="image">
                                <s:if test="%{etatQCM.toString() == 'OUVERT'}">
                                    <i class="glyphicon glyphicon-eye-open" aria-hidden="true"></i>
                                </s:if>
                                <s:else>
                                    <i class="glyphicon glyphicon-time" aria-hidden="true"></i>
                                </s:else>
                            </div>
                            <div class="info">
                                <h3 class="title"><s:property value="nomQCM"/></h3>
                                <p>
                                    <s:property value="etatQCM"/>
                                </p>
                                <div class="more">
                                    <a href="#">
                                        <s:text name="user.listQCM.participate"/> <i class="glyphicon glyphicon-chevron-right"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="space"></div>
                    </div>
                </div>
            </s:iterator>
        </div>
    </s:else>
</div>
