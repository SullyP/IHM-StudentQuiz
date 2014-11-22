package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.modele.IQuestion;

public interface IQuestionDAO {
    public IQuestion creerQuestion(String enonce, boolean multiple,int ratioQuestion);
    public IQuestion getQuestion(int idQuestion);
    public IQuestion majQuestion(int idQuestion, String enonce, boolean multiple);
    public IQuestion majQuestion(int idQuestion, String enonce);
    public IQuestion majQuestion(int idQuestion, boolean multiple);
    public void suppressionQuestion(int idQuestion);
}
