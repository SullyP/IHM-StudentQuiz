package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table Utilisateur.
 */
public enum UtilisateurEnum {
    idUtilisateur ("idUtilisateur"),
    idEntite ("idEntite"),
    nomUtilisateur ("nomUtilisateur"),
    prenomUtilisateur ("prenomUtilisateur"),
    numeroEtudiant ("numeroEtudiant"),
    identifiantUtilisateur ("identifiantUtilisateur"),
    motDePasseUtilisateur ("motDePasseUtilisateur");

    private String nomChampTableBaseDonnee;
    UtilisateurEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
