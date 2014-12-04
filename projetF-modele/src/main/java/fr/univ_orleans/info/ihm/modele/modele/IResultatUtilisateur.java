package fr.univ_orleans.info.ihm.modele.modele;

import java.util.Date;
import java.util.List;

public interface IResultatUtilisateur {
    public int getIdResultatUtilisateur();
    public int getIdUtilisateur();
    public int getIdQCM();
    public double getScore();
    public Date getDate();
    public List<IQuestion> getQuestionReponses();
    public IResultatUtilisateur getResultatUtilisateur();
    public void setResultatUtilisateur(IResultatUtilisateur resultatUtilisateur);
    public void setScore(double score);

    /**
     * Permet d'ajouter une réponse aux résultats.
     * @param question la question avec la liste des réponses de l'utilisateur.
     */
    public void addReponse(IQuestion question);
}
