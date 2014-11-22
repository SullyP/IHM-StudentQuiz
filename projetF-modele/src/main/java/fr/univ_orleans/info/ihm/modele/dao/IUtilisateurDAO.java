package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.modele.IDroit;
import fr.univ_orleans.info.ihm.modele.modele.IEntite;
import fr.univ_orleans.info.ihm.modele.modele.IUtilisateur;

import java.sql.SQLException;

public interface IUtilisateurDAO {
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite, int idDroit) throws SQLException;
    public IUtilisateur getUtilisateur(int idUtilisateur);
    public IUtilisateur majMotDePasse(int idUtilisateur, String motDePasse);
    public IUtilisateur majDroit(int idUtilisateur, IDroit droit);
    public IUtilisateur majEntite(int idUtilisateur, IEntite entite);
    public void suppressionUtilisateur(int idUtilisateur);
}
