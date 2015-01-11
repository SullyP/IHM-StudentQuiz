package fr.univ_orleans.info.ihm.struts.action.admin;

import com.opensymphony.xwork2.Action;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;

import java.io.IOException;
import java.util.List;

public class UtilisateurCRUDController extends ServiceAndSessionAwareAction {

    private List<IUtilisateur> records;
    private String result;
    private String message;
    private IUtilisateur record;

    private int idUtilisateur;
    private int numeroEtudiant;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String identifiantUtilisateur;
    private String motDePasseUtilisateur;
    private int idEntiteUtilisateur;

    //Login verif
    private String fieldId;
    private String fieldValue;

    public String loginVerif() {
        try {
            IUtilisateur utilisateur = this.getModeleService().getUtilisateurByIdentifiant(this.fieldValue);
            if (utilisateur == null || utilisateur.getIdUtilisateur() == this.idUtilisateur) {
                result = "OK";
            } else {
                result = "ERROR";
            }
        } catch (Exception e) {
            result = "ERROR";
            message = e.getMessage();
            System.err.println(e.getMessage());
        }
        return Action.SUCCESS;
    }

    public String list() {
        try {
            records = this.getModeleService().getListUtilisateur();
            result = "OK";
        } catch (Exception e) {
            result = "ERROR";
            message = e.getMessage();
            System.err.println(e.getMessage());
        }
        return Action.SUCCESS;
    }

    public String create() throws IOException {
        //Si la checkbox est décochée
        if (idEntiteUtilisateur == 0) {
            idEntiteUtilisateur = 1;
        }
        try {
            record = this.getModeleService().creerUtilisateur(prenomUtilisateur, nomUtilisateur, identifiantUtilisateur, motDePasseUtilisateur, numeroEtudiant, idEntiteUtilisateur);
            record.setMotDePasseUtilisateur("");
            result = "OK";

        } catch (Exception e) {
            result = "ERROR";
            message = e.getMessage();
            System.err.println(e.getMessage());
        }
        return Action.SUCCESS;
    }

    public String update() throws IOException {
        try {
            record = this.getModeleService().majUtilisateur(idUtilisateur, prenomUtilisateur, nomUtilisateur, identifiantUtilisateur, numeroEtudiant, idEntiteUtilisateur);
            if (motDePasseUtilisateur.length() > 0) {
                this.getModeleService().majMotDePasseUtilisateur(idUtilisateur, motDePasseUtilisateur);
            }
            result = "OK";
        } catch (Exception e) {
            result = "ERROR";
            message = e.getMessage();
            System.err.println(e.getMessage());
        }
        return Action.SUCCESS;
    }

    public String delete() throws IOException {
        try {
            this.getModeleService().suppressionUtilisateur(idUtilisateur);
            result = "OK";
        } catch (Exception e) {
            result = "ERROR";
            message = e.getMessage();
            System.err.println(e.getMessage());
        }
        return Action.SUCCESS;
    }

    public List<IUtilisateur> getRecords() {
        return records;
    }

    public void setRecords(List<IUtilisateur> records) {
        this.records = records;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IUtilisateur getRecord() {
        return record;
    }

    public void setRecord(IUtilisateur record) {
        this.record = record;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(int numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getIdentifiantUtilisateur() {
        return identifiantUtilisateur;
    }

    public void setIdentifiantUtilisateur(String identifiantUtilisateur) {
        this.identifiantUtilisateur = identifiantUtilisateur;
    }

    public String getMotDePasseUtilisateur() {
        return motDePasseUtilisateur;
    }

    public void setMotDePasseUtilisateur(String motDePasseUtilisateur) {
        this.motDePasseUtilisateur = motDePasseUtilisateur;
    }

    public int getIdEntiteUtilisateur() {
        return idEntiteUtilisateur;
    }

    public void setIdEntiteUtilisateur(int idEntiteUtilisateur) {
        this.idEntiteUtilisateur = idEntiteUtilisateur;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public boolean getStatus() {
        return "OK".equals(this.result);
    }

    public void setEdit_idUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}