package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.modele.IQCM;

import java.util.Date;

public interface IQCMDAO {
    public IQCM creerQCM(int idCreateur, String nomQCM, Date dateCreation);
    public IQCM getQCM(int idQCM);
    public IQCM ajoutQuestion(int idQCM, int idQuestion);
    public IQCM suppressionQuestion(int idQCM, int idQuestion);
    public void suppressionQCM(int idQCM);
}
