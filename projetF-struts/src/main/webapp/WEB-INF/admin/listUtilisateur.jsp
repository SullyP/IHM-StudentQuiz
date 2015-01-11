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
                    <div class="info infoJTable">
                        <h3 class="title"><s:text name="admin.listUser.userManagement"/></h3>
                        <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12" id="UserTable" ></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //initialisation jTable
        $('#UserTable').jtable({
            title: '<s:text name="admin.listUser.titleUserTable"/>',
            actions: {
                listAction: 'json/listActionUtilisateur',
                createAction:'json/createActionUtilisateur',
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
                prenomUtilisateur:{
                    title: '<s:text name="admin.listUser.firstName"/>',
                    width: '20%',
                    inputClass: 'validate[required]'
                },
                numeroEtudiant: {
                    title: '<s:text name="admin.listUser.studentNumber"/>',
                    width: '20%',
                    display: function (data) {
                        if(data.record && data.record.idEntiteUtilisateur == 1){
                            return data.record.numeroEtudiant;
                        }else{
                            return '';
                        }
                    },
                    input: function (data) {
                        if (data.record && data.record.idEntiteUtilisateur == 1) {
                            return '<input class="myspinner" name="numeroEtudiant" value="' + data.record.numeroEtudiant + '" />';
                        } else if (data.formType == "create") {
                            return '<input class="myspinner" name="numeroEtudiant" value="1" '+' />';
                        } else {
                            return '<input class="myspinner2" name="numeroEtudiant" value="1" '+' />';
                        }
                    },
                    inputClass: 'validate[required,custom[onlyNumberSp]]'
                },
                identifiantUtilisateur: {
                    title: '<s:text name="admin.listUser.login"/>',
                    width: '20%'
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
                    values: { '1': '<s:text name="admin.listUser.student"/>', '2': '<s:text name="admin.listUser.teacher"/>' }
                }
            },
            formCreated: function (event, data) {
                //Permet de transformer les inputs des popup en spinner
                $(".myspinner2").spinner({min: 1}).addClass('validate[required,custom[onlyNumberSp]]').spinner("disable");;
                $(".myspinner").spinner({min: 1}).addClass('validate[required,custom[onlyNumberSp]]');
                //AJAX login verif
                if(data.formType == "edit"){
                    data.form.find('input[name="identifiantUtilisateur"]').addClass('validate[required,minSize[1],maxSize[50],ajax[ajaxLoginUserEditCall]]');
                }else{
                    data.form.find('input[name="identifiantUtilisateur"]').addClass('validate[required,minSize[1],maxSize[50],ajax[ajaxLoginUserCreateCall]]');
                }
                //Cacher le numéro étudiant si on est Prof
                data.form.find('input[name="idEntiteUtilisateur"]').click(function(){
                    if($(this).get(0).checked){
                        $('#Edit-numeroEtudiant').spinner("disable");
                    }else{
                        $('#Edit-numeroEtudiant').spinner("enable");
                    }
                });
                //Initialisation du validateur
                data.form.validationEngine('attach', {
                    relative: true,
                    overflownDIV: '#' + data.form.get(0).id,
                    promptPosition: "bottomLeft",
                    eventTrigger: 'submit'
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
        $('#UserTable').jtable('load');
    });
</script>