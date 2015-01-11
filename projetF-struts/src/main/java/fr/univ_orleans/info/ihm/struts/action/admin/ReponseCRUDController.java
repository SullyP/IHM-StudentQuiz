package fr.univ_orleans.info.ihm.struts.action.admin;

import com.opensymphony.xwork2.Action;
import fr.univ_orleans.info.ihm.modele.beans.IReponse;
import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;

import java.io.IOException;
import java.util.List;

public class ReponseCRUDController extends ServiceAndSessionAwareAction {

    private List<IReponse> records;
    private String result;
    private String message;
    private IReponse record;

    private int idQuestion;
    private int idReponse;
    private String intituleReponse;
    private boolean correctReponse;

    public String list() {
        try {
            records = this.getModeleService().getReponseListByIdQuestion(idQuestion);
            result = "OK";
        } catch (Exception e) {
            result = "ERROR";
            message = e.getMessage();
            System.err.println(e.getMessage());
        }
        return Action.SUCCESS;
    }

    public String create() throws IOException {
        try {
            record = this.getModeleService().creerReponse(idQuestion, intituleReponse, correctReponse);
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
            this.getModeleService().majReponse(idReponse, intituleReponse, correctReponse);
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
            this.getModeleService().suppressionReponse(idReponse);
            result = "OK";
        } catch (Exception e) {
            result = "ERROR";
            message = e.getMessage();
            System.err.println(e.getMessage());
        }
        return Action.SUCCESS;
    }

    public List<IReponse> getRecords() {
        return records;
    }

    public void setRecords(List<IReponse> records) {
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

    public IReponse getRecord() {
        return record;
    }

    public void setRecord(IReponse record) {
        this.record = record;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getIntituleReponse() {
        return intituleReponse;
    }

    public void setIntituleReponse(String intituleReponse) {
        this.intituleReponse = intituleReponse;
    }

    public boolean isCorrectReponse() {
        return correctReponse;
    }

    public void setCorrectReponse(boolean correctReponse) {
        this.correctReponse = correctReponse;
    }
}