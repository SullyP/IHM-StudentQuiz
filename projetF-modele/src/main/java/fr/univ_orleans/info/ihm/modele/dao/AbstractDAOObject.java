package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeH2;
import fr.univ_orleans.info.ihm.modele.dao.db.IBaseDonnee;

/**
* Classe permettant d'abstraire l'instanciation d'une base de donnée.
* Permet un changement de type de base de donnée rapide (évolution vers Oracle ou autre...).
*/
public class AbstractDAOObject {
	private IBaseDonnee bd;

	public AbstractDAOObject() {
		bd=BaseDonneeH2.getInstance();
	}

	public IBaseDonnee getBd() {
		return bd;
	}
}
