package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table Question.
 */
public enum QuestionEnum {
    ID_QUESTION("idQuestion"),
    INTITULE_QUESTION("intituleQuestion"),
    MULTIPLE_QUESTION("multipleQuestion"),
    DUREE_QUESTION("dureeQuestion"),
    POINT_QUESTION("pointQuestion");

    private String nomChampTableBaseDonnee;
    QuestionEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
