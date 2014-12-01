package fr.univ_orleans.info.ihm.modele.service;

import fr.univ_orleans.info.ihm.modele.modele.IEntite;
import fr.univ_orleans.info.ihm.modele.modele.IUtilisateur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFacadeDAO extends Remote{
    public final String SERVICE_NAME = "QCMService";

    /**
     * Insère une nouvelle entite en BDD
     * @param nom nom de l'entité
     * @return l'entité créé
     */
    public abstract IEntite creerEntite(String nom);

    /**
     * Permet d'obtenir une entité via son id
     * @param idEntite id de l'entité
     * @return l'entité
     */
    public abstract IEntite getEntite(int idEntite);

    /**
     * Permet de mettre à jour le nom d'une entité
     * @param idEntite id de l'entité
     * @param nom nom de l'entité
     * @return l'entité mise à jour
     */
    public abstract IEntite majEntite(int idEntite, String nom);

    /**
     * Permet de supprimer une entité via son id
     * @param idEntite id de l'entité
     */
    public abstract void suppressionEntite(int idEntite);

    public abstract void init() throws RemoteException;

    /**
     * Permet d'insérer un nouvel utilisateur en BDD.
     * @param prenom prenom de l'utilisateur.
     * @param nom nom  de l'utilisateur.
     * @param identifiant identifiant de l'utilisateur.
     * @param motDePasse mot de passe de l'utilisateur.
     * @param numeroEtudiant numero étudiant de l'utilisateur (si étudiant, 0 sinon).
     * @param idEntite id de l'entité de l'utilisateur.
     * @return l'utilisateur créé.
     */
    public abstract IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite);

    /**
     * Permet d'obtenir un utilisateur via son id.
     * @param idUtilisateur id de l'utilisateur.
     * @return l'utilisateur
     */
    public abstract IUtilisateur getUtilisateur(int idUtilisateur);

    /**
     * Permet de mettre à jour le mot de passe de l'utilisateur.
     * @param idUtilisateur id de l'utilisateur.
     * @param motDePasse nouveau mot de passe
     * @return utilisateur
     */
    public abstract IUtilisateur majMotDePasseUtilisateur(int idUtilisateur, String motDePasse);

    /**
     * Permet de mettre à jour l'entité de l'utilisateur
     * @param idUtilisateur id de l'utilisateur.
     * @param idEntite nouvel id de l'entité
     * @return utilisateur
     */
    public abstract IUtilisateur majEntiteUtilisateur(int idUtilisateur, int idEntite);

    /**
     * Permet de supprimer un utilisateur
     * @param idUtilisateur id de l'utilisateur à supprimer.
     */
    public abstract void suppressionUtilisateur(int idUtilisateur);
}
