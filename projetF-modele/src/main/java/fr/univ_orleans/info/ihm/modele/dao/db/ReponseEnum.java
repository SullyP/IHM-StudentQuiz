package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant l'ensemble des champs contenu dans la table Reponse.
 */
public enum ReponseEnum {
    idReponse ("idReponse"),
    idQuestion ("idQuestion"),
    initituleReponse ("initituleReponse"),
    correctReponse ("correctReponse");

    private String nomChampTableBaseDonnee;
    ReponseEnum(String nomChampTableBaseDonnee){
        this.nomChampTableBaseDonnee = nomChampTableBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomChampTableBaseDonnee;
    }
}
