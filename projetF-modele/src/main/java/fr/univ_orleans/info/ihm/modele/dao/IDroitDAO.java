package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.modele.IDroit;

public interface IDroitDAO {
    public IDroit creerDroit(String nom);
    public IDroit getDroit(int idDroit);
    public IDroit majDroit(int idDroit, String nom);
    public void suppressionDroit(IDroit c);
}
