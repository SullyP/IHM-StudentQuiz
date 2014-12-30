package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table QCM.
 */
public enum QCMEnum {
    ID_QCM("idQCM"),
    ID_CREATEUR("idCreateur"),
    NOM_QCM("nomQCM"),
    DATE_CREATION("dateCreation"),
    ETAT_QCM("etatQCM");

    private String nomChampTableBaseDonnee;
    QCMEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
