package fr.univ_orleans.info.ihm.modele.modele;

public interface IEntite {
    public int getIdEntite();
    public String getNomEntite();
    public IEntite getEntite();
    public void setEntite(IEntite e);
    public void setNomEntite(String nomEntite);
}
