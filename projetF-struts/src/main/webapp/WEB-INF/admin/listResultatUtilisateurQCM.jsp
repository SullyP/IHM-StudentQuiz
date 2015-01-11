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
            <h3 class="title"><s:property value="nomQCM"/></h3>
          </div>
        </div>
      </div>
    </div>
    <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12" id="Table" ></div>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function () {
    //initialisation jTable
    $('#Table').jtable({
      title: '<s:text name="admin.listResultatUtilisateur.titleTable"/> <s:property value="scoreMax"/> )',
      actions: {
        listAction: 'json/listActionResultatUtilisateur?idQCM=<s:property value="idQCM"/>',
        deleteAction: 'json/deleteActionResultatUtilisateur'
      },
      fields: {
        idResultatUtilisateur: {
          key: true,
          list: false
        },
        nomUtilisateur: {
          title: '<s:text name="admin.listUser.lastName"/>',
          width: '20%'
        },
        prenomUtilisateur: {
          title: '<s:text name="admin.listUser.firstName"/>',
          width: '20%'
        },
        numeroEtudiant: {
          title: '<s:text name="admin.listUser.studentNumber"/>',
          widht: '20%'
        },
        date: {
          title: '<s:text name="admin.listResultatUtilisateur.date"/>',
          width: '20%',
          display: function (data) {
            if (data.record && data.record.date) {
              return data.record.date.toString().split('T')[0];
            } else {
              return '';
            }
          }
        },
        score: {
          title: '<s:text name="admin.listResultatUtilisateur.score"/>',
          width: '20%'
        }
      }
    });

    //Chargement des données depuis le serveur
    $('#Table').jtable('load');
  });
</script>