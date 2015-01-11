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
                        <h3 class="title"><s:text name="admin.listUser.userManagement"/></h3>
                    </div>
                </div>
                <div class="space"></div>
            </div>
        </div>
        <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12" id="UserTable"></div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //initialisation jTable
        $('#UserTable').jtable({
            title: '<s:text name="admin.listUser.titleUserTable"/>',
            actions: {
                listAction: 'json/listActionUtilisateur',
                createAction: 'json/createActionUtilisateur',
                updateAction: 'json/updateActionUtilisateur',
                deleteAction: 'json/deleteActionUtilisateur'
            },
            fields: {
                idUtilisateur: {
                    key: true,
                    create: false,
                    edit: false,
                    list: false
                },
                nomUtilisateur: {
                    title: '<s:text name="admin.listUser.lastName"/>',
                    width: '20%',
                    inputClass: 'validate[required]'
                },
                prenomUtilisateur: {
                    title: '<s:text name="admin.listUser.firstName"/>',
                    width: '20%',
                    inputClass: 'validate[required]'
                },
                numeroEtudiant: {
                    title: '<s:text name="admin.listUser.studentNumber"/>',
                    width: '20%',
                    display: function (data) {
                        if (data.record.idEntiteUtilisateur == 1) {
                            return data.record.numeroEtudiant;
                        } else {
                            return '';
                        }
                    },
                    input: function (data) {
                        if (data.record) {
                            return '<input class="myspinner" name="numeroEtudiant" value="' + data.record.numeroEtudiant + '" />';
                        } else {
                            return '<input class="myspinner" name="numeroEtudiant" value="1" ' + '/>';
                        }
                    },
                    inputClass: 'validate[required,custom[onlyNumberSp]]'
                },
                identifiantUtilisateur: {
                    title: '<s:text name="admin.listUser.login"/>',
                    width: '20%',
                    inputClass: 'validate[required]'
                },
                motDePasseUtilisateur: {
                    title: '<s:text name="login.password"/>',
                    list: false,
                    type: 'password',
                    inputClass: 'validate[required]'
                },
                idEntiteUtilisateur: {
                    title: '<s:text name="admin.listUser.entity"/>',
                    width: '20%',
                    type: 'checkbox',
                    values: {
                        '1': '<s:text name="admin.listUser.student"/>',
                        '2': '<s:text name="admin.listUser.teacher"/>'
                    }
                }
            },
            formCreated: function (event, data) {
                //Permet de transformer les inputs des popup en spinner
                $(".myspinner").spinner({min: 1}).addClass('validate[required,custom[onlyNumberSp]]');
                //Initialisation du validateur
                data.form.validationEngine('attach', {
                    relative: true,
                    overflownDIV: '#' + data.form.get(0).id,
                    promptPosition: "bottomLeft"
                });
            },
            //Libérer le validateur lors de fermeture du formulaire
            formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            }
        });

        //Chargement des données depuis le serveur
        $('#UserTable').jtable('load');
    });
</script>