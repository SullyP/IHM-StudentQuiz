package fr.univ_orleans.info.ihm.modele.modele;

import java.util.ArrayList;
import java.util.List;

public class Question implements IQuestion {
    private int idQuestion;
    private int dureeQuestion;
    private int pointQuestion;
    private boolean multipleQuestion;
    private String intituleQuestion;
    private List<IReponse> reponses;

    public Question(int idQuestion){
        this.idQuestion = idQuestion;
        this.reponses = new ArrayList<>();
    }

    public Question(int idQuestion, int dureeQuestion, int pointQuestion, boolean multipleQuestion, String intituleQuestion){
        this.idQuestion = idQuestion;
        this.dureeQuestion = dureeQuestion;
        this.pointQuestion = pointQuestion;
        this.multipleQuestion = multipleQuestion;
        this.intituleQuestion = intituleQuestion;
        this.reponses = new ArrayList<>();
    }

    @Override
    public int getIdQuestion() {
        return this.idQuestion;
    }

    @Override
    public String getIntituleQuestion() {
        return this.intituleQuestion;
    }

    @Override
    public boolean isMultipleQuestion() {
        return this.multipleQuestion;
    }

    @Override
    public int getDureeQuestion() {
        return this.dureeQuestion;
    }

    @Override
    public int getPointQuestion() {
        return this.pointQuestion;
    }

    @Override
    public IQuestion getQuestion() {
        return this;
    }

    @Override
    public void setQuestion(IQuestion question) {
        this.idQuestion = question.getIdQuestion();
        this.multipleQuestion = question.isMultipleQuestion();
        this.intituleQuestion = question.getIntituleQuestion();
        this.dureeQuestion = question.getDureeQuestion();
        this.pointQuestion = question.getPointQuestion();
    }

    @Override
    public void setPointQuestion(int pointQuestion) {
        this.pointQuestion = pointQuestion;
    }

    @Override
    public void setDureeQuestion(int dureeQuestion) {
        this.dureeQuestion = dureeQuestion;
    }

    @Override
    public void setIntituleQuestion(String intituleQuestion) {
        this.intituleQuestion = intituleQuestion;
    }

    @Override
    public void setMultipleQuestion(Boolean multipleQuestion) {
        this.multipleQuestion = multipleQuestion;
    }

    @Override
    public List<IReponse> getReponses() {
        return reponses;
    }

    @Override
    public void addReponse(IReponse reponse) {
        this.reponses.add(reponse);
    }

    @Override
    public void removeReponse(int idReponse){
        for (int i = 0; i < this.reponses.size(); i++) {
            if(this.reponses.get(i).getIdReponse() == idReponse){
                this.reponses.remove(i);
            }
        }
    }
}
