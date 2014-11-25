package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table Utilisateur.
 */
public enum UtilisateurEnum {
    ID_UTILISATEUR("idUtilisateur"),
    ID_ENTITE("idEntite"),
    NOM_UTILISATEUR("nomUtilisateur"),
    PRENOM_UTILISATEUR("prenomUtilisateur"),
    NUMERO_ETUDIANT("numeroEtudiant"),
    IDENTIFIANT_UTILISATEUR("identifiantUtilisateur"),
    MOT_DE_PASSE_UTILISATEUR("motDePasseUtilisateur");

    private String nomChampTableBaseDonnee;
    UtilisateurEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
