package fr.univ_orleans.info.ihm.modele.beans;

import java.util.Date;
import java.util.List;

public interface IResultatUtilisateur {
    public int getIdResultatUtilisateur();
    public int getIdUtilisateur();
    public int getIdQCM();
    public int getScore();
    public int getScoreMax();
    public Date getDate();
    public List<IQuestion> getQuestionReponses();
    public IUtilisateur getUtilisateur();
    public IResultatUtilisateur getResultatUtilisateur();
    public void setResultatUtilisateur(IResultatUtilisateur resultatUtilisateur);
    public void setScore(int score);
    public void setScoreMax(int scoreMax);
    public void setUtilisateur(IUtilisateur utilisateur);

    /**
     * Permet d'ajouter une réponse aux résultats.
     * @param question la question avec la liste des réponses de l'utilisateur.
     */
    public void addReponse(IQuestion question);

    //Ensembles de fonctions pour le JSON
    public int getNumeroEtudiant();
    public String getNomUtilisateur();
    public String getPrenomUtilisateur();
}
