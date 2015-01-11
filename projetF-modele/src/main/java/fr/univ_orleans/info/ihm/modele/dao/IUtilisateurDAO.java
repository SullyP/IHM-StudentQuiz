package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;

import java.util.List;

/**
 * Classe d'abastraction de la table Utilisateur
 */
public interface IUtilisateurDAO {
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
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite);

    /**
     * Permet d'obtenir un utilisateur via son id.
     * @param idUtilisateur id de l'utilisateur.
     * @return l'utilisateur
     */
    public IUtilisateur getUtilisateur(int idUtilisateur);

    /**
     * Permet d'obtenir la liste de tous les utilisateurs.
     * @return liste de tous les utilisateurs.
     */
    public List<IUtilisateur> getListUtilisateur();

    /**
     * Permet d'obtenir un utilisateur via son identifiant (login).
     * @param identifiant identifiant de l'utilisateur (login).
     * @return l'utilisateur
     */
    public IUtilisateur getUtilisateurByIdentifiant(String identifiant);

    /**
     * Permet de savoir si un identifiant est disponible.
     * @param identifiant identifiant (login)
     * @return vrai s'il est disponible, faux sinon.
     */
    public boolean identifiantIsDisponible(String identifiant);

    /**
     * Permet de mettre à jour le mot de passe de l'utilisateur.
     * @param idUtilisateur id de l'utilisateur.
     * @param motDePasse nouveau mot de passe
     * @return utilisateur
     */
    public IUtilisateur majMotDePasse(int idUtilisateur, String motDePasse);

    /**
     * Permet de mettre à jour l'entité de l'utilisateur
     * @param idUtilisateur id de l'utilisateur.
     * @param idEntite nouvel id de l'entité
     * @return utilisateur
     */
    public IUtilisateur majEntite(int idUtilisateur, int idEntite);

    /**
     * Permet de supprimer un utilisateur
     * @param idUtilisateur id de l'utilisateur à supprimer.
     */
    public void suppressionUtilisateur(int idUtilisateur);
}
