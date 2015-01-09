<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">
    <s:if test="%{listQCM.size == 0}">
        <p><s:text name="user.listQCM.noQCM"/></p>
    </s:if>
    <s:else>
        <div class="row">
            <s:iterator value="listQCM" var="qCM">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="box">
                        <div class="icon">
                            <div class="image">
                                <s:if test="%{qCM.isOpen()}">
                                    <i class="glyphicon glyphicon-eye-open" aria-hidden="true"></i>
                                </s:if>
                                <s:else>
                                    <i class="glyphicon glyphicon-time" aria-hidden="true"></i>
                                </s:else>
                            </div>
                            <div class="info">
                                <h3 class="title"><s:property value="qCM.nomQCM"/></h3>

                                <p>
                                    <s:property value="qCM.etatQCM"/>
                                </p>

                                <div class="more">
                                    <s:if test="%{qCM.isOpen()}">
                                        <s:url namespace="%{getText('namespace.user')}" action="beginQCM" var="urlTag">
                                            <s:param name="idQCM"><s:property value="qCM.idQCM"/></s:param>
                                        </s:url>
                                        <a href="<s:property value="#urlTag" />">
                                            <s:text name="user.listQCM.participate"/> <i
                                                class="glyphicon glyphicon-chevron-right"></i>
                                        </a>
                                    </s:if>
                                    <s:else>
                                        <a class="invalid">
                                            <s:text name="user.listQCM.participate"/> <i
                                                class="glyphicon glyphicon-chevron-right"></i>
                                        </a>
                                    </s:else>
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
