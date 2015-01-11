package fr.univ_orleans.info.ihm.modele.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultatUtilisateur implements IResultatUtilisateur , Serializable {
    private int idResultatUtilisateur;
    private int idUtilisateur;
    private int idQCM;
    private int score;
    private int scoreMax;
    private Date date;
    private List<IQuestion> questionReponses;

    public ResultatUtilisateur(int idResultatUtilisateur){
        this.questionReponses = new ArrayList<>();
        this.idResultatUtilisateur = idResultatUtilisateur;
    }

    public ResultatUtilisateur(int idResultatUtilisateur, int idUtilisateur, int idQCM, Date date){
        this.questionReponses = new ArrayList<>();
        this.idResultatUtilisateur = idResultatUtilisateur;
        this.idUtilisateur = idUtilisateur;
        this.idQCM = idQCM;
        this.date = date;
    }

    @Override
    public int getScoreMax() {
        return scoreMax;
    }

    @Override
    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    @Override
    public int getIdResultatUtilisateur() {
        return this.idResultatUtilisateur;
    }

    @Override
    public int getIdUtilisateur() {
        return this.idUtilisateur;
    }

    @Override
    public int getIdQCM() {
        return this.idQCM;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public List<IQuestion> getQuestionReponses() {
        return this.questionReponses;
    }

    @Override
    public IResultatUtilisateur getResultatUtilisateur() {
        return this;
    }

    @Override
    public void setResultatUtilisateur(IResultatUtilisateur resultatUtilisateur) {
        this.idQCM = resultatUtilisateur.getIdQCM();
        this.idUtilisateur = resultatUtilisateur.getIdUtilisateur();
        this.idResultatUtilisateur = resultatUtilisateur.getIdResultatUtilisateur();
        this.questionReponses = resultatUtilisateur.getQuestionReponses();
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Permet d'ajouter une réponse aux résultats.
     *
     * @param question la question avec la liste des réponses de l'utilisateur.
     */
    @Override
    public void addReponse(IQuestion question) {
        questionReponses.add(question);
    }
}
