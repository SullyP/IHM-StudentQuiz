package fr.univ_orleans.info.ihm.modele.beans;

public enum EtatQCMEnum {
    FERME("FERME"),
    EN_ATTENTE("EN_ATTENTE"),
    OUVERT("OUVERT");

    private String nomBaseDonnee;
    EtatQCMEnum(String nomBaseDonnee){
        this.nomBaseDonnee = nomBaseDonnee;
    }

    @Override
    public String toString(){
        return this.nomBaseDonnee;
    }
}
