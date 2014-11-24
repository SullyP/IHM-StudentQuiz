package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table Question.
 */
public enum QuestionEnum {
    idQuestion ("idQuestion"),
    intituleQuestion ("intituleQuestion"),
    multipleQuestion ("multipleQuestion"),
    dureeQuestion ("dureeQuestion"),
    pointQuestion ("pointQuestion");

    private String nomChampTableBaseDonnee;
    QuestionEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
