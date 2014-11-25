package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant tous les noms des tables de la base de donnée.
 */
public enum BaseDonneeEnum {
    ENTITE("ProjetFIHM.Entite"),
    UTILISATEUR("ProjetFIHM.Utilisateur"),
    RESULTAT_UTILISATEUR("ProjetFIHM.ResultatUtilisateur"),
    REPONSE_UTILISATEUR("ProjetFIHM.ReponseUtilisateur"),
    REPONSE("ProjetFIHM.Reponse"),
    QUESTION("ProjetFIHM.Question"),
    QCM ("ProjetFIHM.QCM"),
    QCM_QUESTION("ProjetFIHM.QCMQuestion");

    private String nomBaseDonnee;
    BaseDonneeEnum(String nomBaseDonnee){
        this.nomBaseDonnee = nomBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomBaseDonnee;
    }
}
