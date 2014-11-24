package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.modele.IEntite;

public interface IEntiteDAO {
    public IEntite creerEntite(String nom);
    public IEntite getEntite(int idEntite);
    public IEntite majEntite(int idEntite, String nom);
    public void suppressionEntite(int idEntite);
}
