package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.modele.IUtilisateur;

public interface IUtilisateurDAO {
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite);
    public IUtilisateur getUtilisateur(int idUtilisateur);
    public IUtilisateur majMotDePasse(int idUtilisateur, String motDePasse);
    public IUtilisateur majEntite(int idUtilisateur, int idEntite);
    public void suppressionUtilisateur(int idUtilisateur);
}
