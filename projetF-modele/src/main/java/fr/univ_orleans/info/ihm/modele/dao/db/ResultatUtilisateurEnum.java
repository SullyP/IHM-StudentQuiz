package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table ResultatUtilisateur.
 */
public enum ResultatUtilisateurEnum {
    idResultatUtilisateur ("idResultatUtilisateur"),
    idUtilisateur ("idUtilisateur"),
    idQCM ("idQCM"),
    dateResultatUtilisateur ("dateResultatUtilisateur");

    private String nomChampTableBaseDonnee;
    ResultatUtilisateurEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
