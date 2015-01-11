package fr.univ_orleans.info.ihm.modele.beans;

import java.io.Serializable;

public class Reponse implements IReponse , Serializable {
    private int idReponse;
    private String intituleReponse;
    private boolean correctReponse;

    public Reponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public Reponse(int idReponse, String intituleReponse, boolean correctReponse) {
        this.idReponse = idReponse;
        this.intituleReponse = intituleReponse;
        this.correctReponse = correctReponse;
    }

    @Override
    public int getIdReponse() {
        return idReponse;
    }

    @Override
    public String getIntituleReponse() {
        return intituleReponse;
    }

    @Override
    public void setIntituleReponse(String intituleReponse) {
        this.intituleReponse = intituleReponse;
    }

    @Override
    public boolean isCorrectReponse() {
        return correctReponse;
    }

    @Override
    public IReponse getReponse() {
        return this;
    }

    @Override
    public void setReponse(IReponse reponse) {
        this.correctReponse = reponse.isCorrectReponse();
        this.intituleReponse = reponse.getIntituleReponse();
        this.idReponse = reponse.getIdReponse();
    }

    @Override
    public void setCorrectReponse(boolean correctReponse) {
        this.correctReponse = correctReponse;
    }
}
