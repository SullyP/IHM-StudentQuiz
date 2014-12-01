package fr.univ_orleans.info.ihm.modele.service;

import fr.univ_orleans.info.ihm.modele.dao.EntiteBaseDAO;
import fr.univ_orleans.info.ihm.modele.dao.UtilisateurBaseDAO;
import fr.univ_orleans.info.ihm.modele.modele.IEntite;
import fr.univ_orleans.info.ihm.modele.modele.IUtilisateur;

import java.rmi.RemoteException;

public class FacadeBaseDAO implements IFacadeDAO {
    /**
     * Insère une nouvelle entite en BDD
     *
     * @param nom nom de l'entité
     * @return l'entité créé
     */
    @Override
    public IEntite creerEntite(String nom) {
        return EntiteBaseDAO.getInstance().creerEntite(nom);
    }

    /**
     * Permet d'obtenir une entité via son id
     *
     * @param idEntite id de l'entité
     * @return l'entité
     */
    @Override
    public IEntite getEntite(int idEntite) {
        return EntiteBaseDAO.getInstance().getEntite(idEntite);
    }

    /**
     * Permet de mettre à jour le nom d'une entité
     *
     * @param idEntite id de l'entité
     * @param nom      nom de l'entité
     * @return l'entité mise à jour
     */
    @Override
    public IEntite majEntite(int idEntite, String nom) {
        return EntiteBaseDAO.getInstance().majEntite(idEntite, nom);
    }

    /**
     * Permet de supprimer une entité via son id
     *
     * @param idEntite id de l'entité
     */
    @Override
    public void suppressionEntite(int idEntite) {
        EntiteBaseDAO.getInstance().suppressionEntite(idEntite);
    }

    @Override
    public void init() throws RemoteException {

    }

    /**
     * Permet d'insérer un nouvel utilisateur en BDD.
     *
     * @param prenom         prenom de l'utilisateur.
     * @param nom            nom  de l'utilisateur.
     * @param identifiant    identifiant de l'utilisateur.
     * @param motDePasse     mot de passe de l'utilisateur.
     * @param numeroEtudiant numero étudiant de l'utilisateur (si étudiant, 0 sinon).
     * @param idEntite       id de l'entité de l'utilisateur.
     * @return l'utilisateur créé.
     */
    @Override
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite) {
        return UtilisateurBaseDAO.getInstance().creerUtilisateur(prenom, nom, identifiant, motDePasse, numeroEtudiant, idEntite);
    }

    /**
     * Permet d'obtenir un utilisateur via son id.
     *
     * @param idUtilisateur id de l'utilisateur.
     * @return l'utilisateur
     */
    @Override
    public IUtilisateur getUtilisateur(int idUtilisateur) {
        return UtilisateurBaseDAO.getInstance().getUtilisateur(idUtilisateur);
    }

    /**
     * Permet de mettre à jour le mot de passe de l'utilisateur.
     *
     * @param idUtilisateur id de l'utilisateur.
     * @param motDePasse    nouveau mot de passe
     * @return utilisateur
     */
    @Override
    public IUtilisateur majMotDePasseUtilisateur(int idUtilisateur, String motDePasse) {
        return UtilisateurBaseDAO.getInstance().majMotDePasse(idUtilisateur, motDePasse);
    }

    /**
     * Permet de mettre à jour l'entité de l'utilisateur
     *
     * @param idUtilisateur id de l'utilisateur.
     * @param idEntite      nouvel id de l'entité
     * @return utilisateur
     */
    @Override
    public IUtilisateur majEntiteUtilisateur(int idUtilisateur, int idEntite) {
        return UtilisateurBaseDAO.getInstance().majEntite(idUtilisateur, idEntite);
    }

    /**
     * Permet de supprimer un utilisateur
     *
     * @param idUtilisateur id de l'utilisateur à supprimer.
     */
    @Override
    public void suppressionUtilisateur(int idUtilisateur) {
        UtilisateurBaseDAO.getInstance().suppressionUtilisateur(idUtilisateur);
    }
}
