<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">
    <s:if test="%{listQCM.size == 0}">
        <p><s:text name="admin.listQCM.noQCM"/></p>
    </s:if>
    <s:else>
        <div class="row">
            <!--- Bouton d'ajout d'un QCM -->
            <div class="col-lg-3 col-sm-6 col-xs-12 col-md-4">
                <div class="box">
                    <div class="icon">
                        <div class="image">
                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                        </div>
                        <div class="info">
                            <h3 class="title"></h3>
                            <div class="more">
                                <a type="button" href="#addQCM_modal" data-toggle="modal">
                                    <s:text name="admin.listQCM.addQCM"/> <i class="glyphicon glyphicon-plus"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="space"></div>
                </div>
            </div>
            <!--- Liste des QCM -->
            <s:iterator value="listQCM">
                <div class="col-lg-3 col-sm-6 col-xs-12 col-md-4" id="boxQCM<s:property value="idQCM"/>">
                    <div class="box">
                        <div class="icon">
                            <div class="image">
                                <s:if test="%{etatQCM.toString() == 'OUVERT'}">
                                    <i class="glyphicon glyphicon-eye-open" aria-hidden="true"></i>
                                </s:if>
                                <s:elseif test="%{etatQCM.toString() == 'FERME'}">
                                    <i class="glyphicon glyphicon-eye-close" aria-hidden="true"></i>
                                </s:elseif>
                                <s:else>
                                    <i class="glyphicon glyphicon-time" aria-hidden="true"></i>
                                </s:else>
                            </div>
                            <div class="right-corner">
                                <a type="button" href="#deleteQCM_modal" data-toggle="modal" data-whatever="<s:property value="idQCM"/>">
                                    <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                                </a>
                            </div>
                            <div class="info">
                                <h3 class="title"><s:property value="nomQCM"/></h3>
                                <p>
                                    <s:property value="etatQCM"/> </br>
                                    <s:text name="admin.listQCM.createOn"/> <s:property value="dateCreationQCM"/>
                                </p>
                                <div class="more">
                                    <a href="#">
                                        <s:text name="admin.listQCM.edit"/> <i class="glyphicon glyphicon-pencil"></i>
                                    </a>
                                </div>
                                </br>
                                <div class="more">
                                    <a href="#">
                                        <s:text name="admin.listQCM.more"/> <i class="glyphicon glyphicon-plus"></i>
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
    <!-- Modal Ajout QCM -->
    <div class="modal fade" id="addQCM_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"><s:text name="admin.listQCM.addQCM"/></h4>
                </div>
                <div class="modal-body">
                    <s:form namespace="/admin" action="addQCM" theme="bootstrap" cssClass="form">
                        <fieldset>
                                <div class="form-group">
                                    <s:textfield name="nomQCM" label="%{getText('admin.listQCM.nameQCM')}" maxLength="250"/>
                                </div>
                            <s:submit cssClass="btn btn-success" value="%{getText('global.validate')}"/>
                        </fieldset>
                    </s:form>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal Supprimer QCM -->
    <div class="modal fade" id="deleteQCM_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2"><s:text name="admin.listQCM.deleteQCM"/></h4>
                </div>
                <div class="modal-body">
                    <p>
                        <s:text name="admin.listQCM.deleteQCMMessage"/>
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><s:text name="global.cancel"/></button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="deleteQCMButton"><s:text name="global.validate"/></button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#deleteQCM_modal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button qui a ouvert le modal
        var idQCM = button.data('whatever'); // Récupération de l'idQCM
        // On ajoute une requête AJAX sur le bouton de confirmation de la suppression
        $('#deleteQCMButton').click(function () {
            $.ajax({
                type: "POST",
                <s:url namespace="%{getText('namespace.admin')}" action="deleteQCM" var="urlTag" />
                url: "<s:property value="#urlTag" />",
                data: { idQCM: idQCM}
            })
                    .done(function( msg ) {
                        //Lorsque la requête est terminée, on supprime le QCM de la page
                        $('#boxQCM'+idQCM).remove();
                    });
        })
    })
</script>
