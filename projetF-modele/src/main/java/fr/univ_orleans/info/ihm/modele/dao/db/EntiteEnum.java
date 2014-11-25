package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table ENTITE.
 */
public enum EntiteEnum {
    ID_ENTITE("idEntite"),
    NOM_ENTITE("nomEntite");

    private String nomChampTableBaseDonnee;
    EntiteEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
