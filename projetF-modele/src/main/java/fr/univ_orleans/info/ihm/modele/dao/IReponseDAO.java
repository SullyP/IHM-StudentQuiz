package fr.univ_orleans.info.ihm.modele.dao;


import fr.univ_orleans.info.ihm.modele.modele.IQuestion;

public interface IReponseDAO {
    public IQuestion creerReponse(int idQuestion, String enonceReponse, boolean correct);
    public IQuestion getReponse(int idReponse);
    public IQuestion majReponse(int idReponse, String enonceReponse, boolean correct);
    public IQuestion majReponse(int idReponse, String enonceReponse);
    public IQuestion majReponse(int idReponse, boolean correct);
    public void suppressionReponse(int idReponse);

}
