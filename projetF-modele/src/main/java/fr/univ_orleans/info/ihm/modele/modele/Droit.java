package fr.univ_orleans.info.ihm.modele.modele;

public class Droit implements IDroit {
    private int idDroit;
    private String typeDroit;

    public Droit(int idDroit, String typeDroit){
        this.idDroit = idDroit;
        this.typeDroit = typeDroit;
    }

    @Override
    public int getIdDroit() {
        return this.idDroit;
    }

    @Override
    public String getTypeDroit() {
        return this.typeDroit;
    }

    @Override
    public IDroit getDroit() {
        return this;
    }

    @Override
    public void setDroit(IDroit d) {
        this.typeDroit = d.getTypeDroit();
        this.idDroit = d.getIdDroit();
    }

    @Override
    public void setTypeDroit(String typeDroit) {
        this.typeDroit = typeDroit;
    }
}
