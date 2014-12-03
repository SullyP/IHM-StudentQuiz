package fr.univ_orleans.info.ihm.modele.modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QCM implements IQCM{
    private int idQCM;
    private int idCreateurQCM;
    private String nomQCM;
    private Date dateCreationQCM;
    private List<IQuestion> questions;

    public QCM(int idQCM){
        this.idQCM = idQCM;
        this.questions = new ArrayList<>();
    }

    public QCM(int idQCM, int idCreateurQCM, String nomQCM, Date dateCreationQCM){
        this.idQCM = idQCM;
        this.nomQCM = nomQCM;
        this.idCreateurQCM = idCreateurQCM;
        this.dateCreationQCM = dateCreationQCM;
        this.questions = new ArrayList<>();
    }

    @Override
    public int getIdQCM() {
        return this.idQCM;
    }

    @Override
    public int getIdCreateurQCM() {
        return this.idCreateurQCM;
    }

    @Override
    public String getNomQCM() {
        return this.nomQCM;
    }

    @Override
    public Date getDateCreationQCM() {
        return this.dateCreationQCM;
    }

    @Override
    public List<IQuestion> getQuestions() {
        return this.questions;
    }

    @Override
    public IQCM getQCM() {
        return this;
    }

    @Override
    public void setQCM(IQCM qcm) {
        this.idQCM = qcm.getIdQCM();
        this.dateCreationQCM = qcm.getDateCreationQCM();
        this.idCreateurQCM = qcm.getIdCreateurQCM();
        this.nomQCM = qcm.getNomQCM();
        this.questions = qcm.getQuestions();
    }

    @Override
    public void setIdCreateurQCM(int idCreateurQCM) {
        this.idCreateurQCM = idCreateurQCM;
    }

    @Override
    public void setNomQCM(String nomQCM) {
        this.nomQCM = nomQCM;
    }

    /**
     * Permet d'ajouter une question au QCM.
     *
     * @param question nouvelle question.
     */
    @Override
    public void addQuestion(IQuestion question) {
        this.questions.add(question);
    }

    /**
     * Permet de supprimer une question du QCM.
     *
     * @param idQuestion id de la question à supprimer.
     */
    @Override
    public void removeQuestion(int idQuestion) {
        for (int i = 0; i < this.questions.size(); i++) {
            if(this.questions.get(i).getIdQuestion() == idQuestion){
                this.questions.remove(i);
            }
        }
    }
}
