package fr.univ_orleans.info.ihm.struts.action.admin;

import com.opensymphony.xwork2.Action;
import fr.univ_orleans.info.ihm.modele.beans.IResultatUtilisateur;
import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;

import java.io.IOException;
import java.util.List;

public class ResultatUtilisateurCRUDController extends ServiceAndSessionAwareAction {

    private List<IResultatUtilisateur> records;
    private String result;
    private String message;

    private int idQCM;
    private int idResultatUtilisateur;

    public String list() {
        try {
            records = this.getModeleService().getListResultatUtilisateurByQCM(idQCM);
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
            this.getModeleService().suppressionResultatutilisateur(idResultatUtilisateur);
            result = "OK";
        } catch (Exception e) {
            result = "ERROR";
            message = e.getMessage();
            System.err.println(e.getMessage());
        }
        return Action.SUCCESS;
    }

    public int getIdResultatUtilisateur() {
        return idResultatUtilisateur;
    }

    public void setIdResultatUtilisateur(int idResultatUtilisateur) {
        this.idResultatUtilisateur = idResultatUtilisateur;
    }

    public List<IResultatUtilisateur> getRecords() {
        return records;
    }

    public void setRecords(List<IResultatUtilisateur> records) {
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

    public int getIdQCM() {
        return idQCM;
    }

    public void setIdQCM(int idQCM) {
        this.idQCM = idQCM;
    }
}
