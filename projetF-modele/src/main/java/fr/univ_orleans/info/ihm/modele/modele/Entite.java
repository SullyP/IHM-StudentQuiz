package fr.univ_orleans.info.ihm.modele.modele;

public class Entite implements IEntite{
    private int idEntite;
    private String nomEntite;

    public Entite(int idEntite, String nomEntite){
        this.idEntite = idEntite;
        this.nomEntite = nomEntite;
    }

    @Override
    public int getIdEntite() {
        return this.idEntite;
    }

    @Override
    public String getNomEntite() {
        return this.nomEntite;
    }

    @Override
    public IEntite getEntite() {
        return this;
    }

    @Override
    public void setEntite(IEntite e) {
        this.idEntite = e.getIdEntite();
        this.nomEntite = e.getNomEntite();
    }

    @Override
    public void setNomEntite(String nomEntite) {
        this.nomEntite = nomEntite;
    }
}
