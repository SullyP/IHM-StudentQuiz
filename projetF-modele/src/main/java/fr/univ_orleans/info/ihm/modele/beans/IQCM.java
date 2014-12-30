package fr.univ_orleans.info.ihm.modele.beans;

import java.util.Date;
import java.util.List;

public interface IQCM {
    public int getIdQCM();
    public int getIdCreateurQCM();
    public String getNomQCM();
    public Date getDateCreationQCM();
    public List<IQuestion> getQuestions();
    public EtatQCMEnum getEtatQCM();
    public IQCM getQCM();
    public void setQCM(IQCM qcm);
    public void setIdCreateurQCM(int idCreateurQCM);
    public void setNomQCM(String nomQCM);
    public void setEtatQCM(EtatQCMEnum etatQCM);

    /**
     * Permet d'ajouter une question au QCM.
     * @param question nouvelle question.
     */
    public void addQuestion(IQuestion question);

    /**
     * Permet de supprimer une question du QCM.
     * @param idQuestion id de la question à supprimer.
     */
    public void removeQuestion(int idQuestion);
}
