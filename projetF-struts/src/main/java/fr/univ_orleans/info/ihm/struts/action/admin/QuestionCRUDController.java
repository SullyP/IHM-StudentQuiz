package fr.univ_orleans.info.ihm.struts.action.admin;

import com.opensymphony.xwork2.Action;
import fr.univ_orleans.info.ihm.modele.beans.IQuestion;
import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;

import java.io.IOException;
import java.util.List;

public class QuestionCRUDController extends ServiceAndSessionAwareAction {

    private List<IQuestion> records;
    private String result;
    private String message;
    private IQuestion record;

    private int idQCM;
    private int idQuestion;
    private int dureeQuestion;
    private int pointQuestion;
    private boolean multipleQuestion;
    private String intituleQuestion;

    public String list() {
        try {
            records = this.getModeleService().getQuestionListByIdQCM(idQCM);
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
            record = this.getModeleService().creerQuestion(intituleQuestion, multipleQuestion, dureeQuestion, pointQuestion);
            this.getModeleService().ajoutQCMQuestion(idQCM, record.getIdQuestion());
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
            this.getModeleService().majQuestion(idQuestion, intituleQuestion, multipleQuestion, dureeQuestion, pointQuestion);
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
            this.getModeleService().suppressionQuestion(idQuestion);
            result = "OK";
        } catch (Exception e) {
            result = "ERROR";
            message = e.getMessage();
            System.err.println(e.getMessage());
        }
        return Action.SUCCESS;
    }

    public String getIntituleQuestion() {
        return intituleQuestion;
    }

    public void setIntituleQuestion(String intituleQuestion) {
        this.intituleQuestion = intituleQuestion;
    }

    public int getPointQuestion() {
        return pointQuestion;
    }

    public void setPointQuestion(int pointQuestion) {
        this.pointQuestion = pointQuestion;
    }

    public boolean isMultipleQuestion() {
        return multipleQuestion;
    }

    public void setMultipleQuestion(boolean multipleQuestion) {
        this.multipleQuestion = multipleQuestion;
    }

    public int getDureeQuestion() {
        return dureeQuestion;
    }

    public void setDureeQuestion(int dureeQuestion) {
        this.dureeQuestion = dureeQuestion;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdQCM() {
        return idQCM;
    }

    public void setIdQCM(int idQCM) {
        this.idQCM = idQCM;
    }

    public IQuestion getRecord() {
        return record;
    }

    public void setRecord(IQuestion record) {
        this.record = record;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<IQuestion> getRecords() {
        return records;
    }

    public void setRecords(List<IQuestion> records) {
        this.records = records;
    }
}
