package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.modele.IResultatUtilisateur;

import java.util.Date;

public interface IResultatUtilisateurDAO {
    public IResultatUtilisateur creerResultatUtilisateur(int idUtilisateur, int idQCM, Date dateResultat);
    public IResultatUtilisateur getResultatUtilisateur(int idResultatUtilisateur);
    public void suppressionResultatutilisateur(int idResultatUtilisateur);
}
