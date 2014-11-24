package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table QCMQuestion.
 */
public enum QCMQuestionEnum {
    idQCM ("idQCM"),
    idQuestion ("idQuestion");

    private String nomChampTableBaseDonnee;
    QCMQuestionEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
