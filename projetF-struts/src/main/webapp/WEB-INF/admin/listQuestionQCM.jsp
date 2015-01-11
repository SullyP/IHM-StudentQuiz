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
                listAction: 'json/listActionQuestion?idQCM=<s:property value="idQCM"/>',
                createAction:'json/createActionQuestion?idQCM=<s:property value="idQCM"/>',
                updateAction: 'json/updateActionQuestion',
                deleteAction: 'json/deleteActionQuestion'
            },
            fields: {
                idQuestion: {
                    key: true,
                    create: false,
                    edit: false,
                    list: false
                },
                //Tableau enfant pour les réponses
                Reponses: {
                    title: '',
                    width: '5%',
                    sorting: false,
                    edit: false,
                    create: false,
                    display: function (questionData) {
                        //Image à afficher sur la ligne
                        var $img = $('<img src="/css/images/list_metro.png" title="<s:text name="admin.listQuestionQCM.editReponses"/>" />');
                        //Ouverture du tableau enfant lors du click
                        $img.click(function () {
                            $('#QuestionTable').jtable('openChildTable',
                                    $img.closest('tr'),
                                    {
                                        title: questionData.record.intituleQuestion + ' - <s:text name="admin.listQuestionQCM.Reponses"/> ',
                                        actions: {
                                            listAction: 'json/listActionReponse?idQuestion=' + questionData.record.idQuestion,
                                            deleteAction: 'json/deleteActionReponse',
                                            updateAction: 'json/updateActionReponse',
                                            createAction: 'json/createActionReponse'
                                        },
                                        fields: {
                                            idQuestion: {
                                                type: 'hidden',
                                                defaultValue: questionData.record.idQuestion
                                            },
                                            idReponse: {
                                                key: true,
                                                create: false,
                                                edit: false,
                                                list: false
                                            },
                                            intituleReponse: {
                                                title: '<s:text name="admin.listQuestionQCM.intituleQuestion"/>',
                                                width: '70%',
                                                inputClass: 'validate[required]'
                                            },
                                            correctReponse: {
                                                title: '<s:text name="admin.listQuestionQCM.correctReponse"/>',
                                                width: '30%',
                                                type: 'checkbox',
                                                values: { 'false': '<s:text name="global.No"/>', 'true': '<s:text name="global.Yes"/>' }
                                            }
                                        },
                                        formCreated: function (event, data) {
                                            //Initialisation du validateur
                                            data.form.validationEngine();
                                        },
                                        //Validation du formulaire avant envoie
                                        formSubmitting: function (event, data) {
                                            return data.form.validationEngine('validate');
                                        },
                                        //Libérer le validateur lors de fermeture du formulaire
                                        formClosed: function (event, data) {
                                            data.form.validationEngine('hide');
                                            data.form.validationEngine('detach');
                                        }
                                    }, function (data) { //handler
                                        data.childTable.jtable('load');
                                    });
                        });
                        //Retourne l'image à afficher sur la ligne
                        return $img;
                    }
                },
                intituleQuestion: {
                    title: '<s:text name="admin.listQuestionQCM.intituleQuestion"/>',
                    width: '50%',
                    inputClass: 'validate[required]'
                },
                dureeQuestion: {
                    title: '<s:text name="admin.listQuestionQCM.dureeQuestion"/>',
                    width: '25%',
                    input: function (data) {
                        if (data.record) {
                            return '<input class="myspinner" name="dureeQuestion" value="' + data.record.dureeQuestion + '" />';
                        } else {
                            return '<input class="myspinner" name="dureeQuestion" value="1" '+'/>';
                        }
                    },
                    inputClass: 'validate[required,custom[onlyNumberSp]]'
                },
                pointQuestion: {
                    title: '<s:text name="admin.listQuestionQCM.pointQuestion"/>',
                    width: '10%',
                    input: function (data) {
                        if (data.record) {
                            return '<input class="myspinner" name="pointQuestion" value="' + data.record.pointQuestion + '" />';
                        } else {
                            return '<input class="myspinner" name="pointQuestion" value="1" '+'/>';
                        }
                    },
                    inputClass: 'validate[required,custom[onlyNumberSp]]'
                },
                multipleQuestion: {
                    title: '<s:text name="admin.listQuestionQCM.multipleQuestion"/>',
                    width: '10%',
                    type: 'checkbox',
                    values: { 'false': '<s:text name="global.No"/>', 'true': '<s:text name="global.Yes"/>' }
                }
            },
            formCreated: function (event, data) {
                //Permet de transformer les inputs des popup en spinner
                $(".myspinner").spinner({min: 1}).addClass('validate[required,custom[onlyNumberSp]]');

                //Initialisation du validateur
                data.form.validationEngine('attach', {
                    relative: true,
                    overflownDIV: '#' + data.form.get(0).id,
                    promptPosition:"bottomLeft"
                });
            },
            //Validation du formulaire avant envoie
            formSubmitting: function (event, data) {
                return data.form.validationEngine('validate');
            },
            //Libérer le validateur lors de fermeture du formulaire
            formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            }
        });

        //Chargement des données depuis le serveur
        $('#QuestionTable').jtable('load');
    });
</script>