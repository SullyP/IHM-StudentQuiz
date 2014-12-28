package fr.univ_orleans.info.ihm.modele.beans;

import java.util.List;

public interface IQuestion {
    public int getIdQuestion();
    public String getIntituleQuestion();
    public boolean isMultipleQuestion();
    public int getDureeQuestion();
    public int getPointQuestion();
    public List<IReponse> getReponses();
    public IQuestion getQuestion();
    public void setQuestion(IQuestion question);
    public void setPointQuestion(int pointQuestion);
    public void setDureeQuestion(int dureeQuestion);
    public void setIntituleQuestion(String intituleQuestion);
    public void setMultipleQuestion(Boolean multipleQuestion);

    /**
     * Permet d'ajouter une réponse à la question.
     * @param reponse nouvelle réponse.
     */
    public void addReponse(IReponse reponse);

    /**
     * Permet de supprimer une réponse à la question.
     * @param idReponse id de la réponse à supprimer.
     */
    public void removeReponse(int idReponse);
}
