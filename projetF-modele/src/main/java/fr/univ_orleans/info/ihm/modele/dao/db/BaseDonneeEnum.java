package fr.univ_orleans.info.ihm.modele.dao.db;

/**
 * Enumération contenant tous les noms des tables de la base de donnée.
 */
public enum BaseDonneeEnum {
    Entite ("ProjetFIHM.Entite"),
    Utilisateur ("ProjetFIHM.Utilisateur"),
    ResultatUtilisateur ("ProjetFIHM.ResultatUtilisateur"),
    ReponseUtilisateur ("ProjetFIHM.ReponseUtilisateur"),
    Reponse ("ProjetFIHM.Reponse"),
    Question ("ProjetFIHM.Question"),
    QCM ("ProjetFIHM.QCM"),
    QCMQuestion ("ProjetFIHM.QCMQuestion");

    private String nomBaseDonnee;
    BaseDonneeEnum(String nomBaseDonnee){
        this.nomBaseDonnee = nomBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomBaseDonnee;
    }
}
