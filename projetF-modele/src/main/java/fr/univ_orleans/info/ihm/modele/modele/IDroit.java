package fr.univ_orleans.info.ihm.modele.modele;

public interface IDroit {
    public int getIdDroit();
    public String getTypeDroit();
    public IDroit getDroit();
    public void setDroit(IDroit d);
    public void setTypeDroit(String typeDroit);
}
