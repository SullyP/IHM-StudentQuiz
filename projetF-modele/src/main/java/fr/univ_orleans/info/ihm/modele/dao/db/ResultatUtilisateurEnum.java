package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table ResultatUtilisateur.
 */
public enum ResultatUtilisateurEnum {
    ID_RESULTAT_UTILISATEUR("idResultatUtilisateur"),
    ID_UTILISATEUR("idUtilisateur"),
    ID_QCM("idQCM"),
    DATE_RESULTAT_UTILISATEUR("dateResultatUtilisateur"),
    SCORE("score");

    private String nomChampTableBaseDonnee;
    ResultatUtilisateurEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
