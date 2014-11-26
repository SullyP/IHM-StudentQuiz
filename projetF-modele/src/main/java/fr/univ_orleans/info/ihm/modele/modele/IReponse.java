package fr.univ_orleans.info.ihm.modele.modele;

public interface IReponse {
    public int getIdReponse();
    public String getIntituleReponse();
    public boolean isCorrectReponse();
    public IReponse getReponse();
    public void setReponse(IReponse reponse);
    public void setIntituleReponse(String intituleReponse);
    public void setCorrectReponse(boolean correctReponse);
}
