<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="vertical-center">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="error-template">
                    <h1>Oops!</h1>

                    <h2>500 Internal Server Error</h2>

                    <div class="error-details">
                        <p>Sorry, there was a problem serving the requested page.</p>
                    </div>
                    <div class="error-actions">
                        <a href="<s:url namespace="/" action="home"/>" class="btn btn-primary btn-lg"><span
                                class="glyphicon glyphicon-home"></span> Take Me Home</a>
                        <%--<a href="<s:url namespace="/" action="home"/>" class="btn btn-default btn-lg"><span--%>
                        <%--class="glyphicon glyphicon-envelope"></span>    Contact Support</a>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>