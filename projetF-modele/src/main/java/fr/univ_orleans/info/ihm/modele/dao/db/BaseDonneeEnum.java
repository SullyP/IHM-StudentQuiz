package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant tous les noms des tables de la base de donnée.
 */
public enum BaseDonneeEnum {
    ENTITE("Entite"),
    UTILISATEUR("Utilisateur"),
    RESULTAT_UTILISATEUR("ResultatUtilisateur"),
    REPONSE_UTILISATEUR("ReponseUtilisateur"),
    REPONSE("Reponse"),
    QUESTION("Question"),
    QCM ("QCM"),
    QCM_QUESTION("QCMQuestion"),
    MEMO_QUESTION("MemoGetNextQuestion");

    private String nomBaseDonnee;
    private static final String NOM_SCHEMA = "IHMProjetFF";
    BaseDonneeEnum(String nomBaseDonnee){
        this.nomBaseDonnee = nomBaseDonnee;
    }

    @Override
    public String toString(){
        return NOM_SCHEMA + "." + this.nomBaseDonnee;
    }
}
