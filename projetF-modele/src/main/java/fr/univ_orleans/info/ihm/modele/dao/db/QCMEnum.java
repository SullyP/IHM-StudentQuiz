package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table QCM.
 */
public enum QCMEnum {
    idQCM ("idQCM"),
    idCreateur ("idCreateur"),
    nomQCM ("nomQCM"),
    dateCreation ("dateCreation");

    private String nomChampTableBaseDonnee;
    QCMEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
