package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.IEntite;

/**
 * Classe d'abastraction de la table Entite
 */
public interface IEntiteDAO {
    /**
     * Insère une nouvelle entite en BDD
     * @param nom nom de l'entité
     * @return l'entité créé
     */
    public IEntite creerEntite(String nom);

    /**
     * Permet d'obtenir une entité via son id
     * @param idEntite id de l'entité
     * @return l'entité
     */
    public IEntite getEntite(int idEntite);

    /**
     * Permet de mettre à jour le nom d'une entité
     * @param idEntite id de l'entité
     * @param nom nom de l'entité
     * @return l'entité mise à jour
     */
    public IEntite majEntite(int idEntite, String nom);

    /**
     * Permet de supprimer une entité via son id
     * @param idEntite id de l'entité
     */
    public void suppressionEntite(int idEntite);
}
