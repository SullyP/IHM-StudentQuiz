package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant tous les noms des tables de la base de donnée.
 */
public enum BaseDonneeEnum {
    ENTITE("IHMProjetF.Entite"),
    UTILISATEUR("IHMProjetF.Utilisateur"),
    RESULTAT_UTILISATEUR("IHMProjetF.ResultatUtilisateur"),
    REPONSE_UTILISATEUR("IHMProjetF.ReponseUtilisateur"),
    REPONSE("IHMProjetF.Reponse"),
    QUESTION("IHMProjetF.Question"),
    QCM ("IHMProjetF.QCM"),
    QCM_QUESTION("IHMProjetF.QCMQuestion");

    private String nomBaseDonnee;
    BaseDonneeEnum(String nomBaseDonnee){
        this.nomBaseDonnee = nomBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomBaseDonnee;
    }
}
