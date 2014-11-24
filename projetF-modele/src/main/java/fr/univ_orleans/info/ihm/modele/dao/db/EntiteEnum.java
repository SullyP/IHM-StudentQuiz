package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table Entite.
 */
public enum EntiteEnum {
    idEntite ("idEntite"),
    nomEntite ("nomEntite");

    private String nomChampTableBaseDonnee;
    EntiteEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
