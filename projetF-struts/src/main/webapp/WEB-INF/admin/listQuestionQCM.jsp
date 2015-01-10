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
            <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12" id="QuestionTable" ></div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //initialisation jTable
        $('#QuestionTable').jtable({
            title: '<s:text name="admin.listQuestionQCM.titleQuestionTable"/>',
            actions: {
                listAction: 'CRUDController?action=list',
                createAction:'CRUDController?action=create',
                updateAction: 'CRUDController?action=update',
                deleteAction: 'CRUDController?action=delete'
            },
            fields: {
                customerid: {
                    key: true,
                    list: false
                },
                customername: {
                    title: 'Customer Name',
                    width: '30%'
                },
                email: {
                    title: 'Email',
                    width: '30%'
                },
                phone: {
                    title: 'Phone',
                    width: '20%',
                    create: false,
                    edit: false
                },
                city: {
                    title: 'City',
                    width: '20%'

                }

            }
        });
    });
</script>