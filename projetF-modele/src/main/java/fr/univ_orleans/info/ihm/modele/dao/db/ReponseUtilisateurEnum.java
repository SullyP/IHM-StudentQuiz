package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table ReponseUtilisateur.
 */
public enum ReponseUtilisateurEnum {
    ID_RESULTAT_UTILISATEUR("idResultatUtilisateur"),
    ID_REPONSE("idReponse");

    private String nomChampTableBaseDonnee;
    ReponseUtilisateurEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
