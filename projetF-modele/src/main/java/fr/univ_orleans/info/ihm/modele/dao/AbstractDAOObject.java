package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeH2;
import fr.univ_orleans.info.ihm.modele.dao.db.IBaseDonnee;

public class AbstractDAOObject {

	private IBaseDonnee bd;
	
	
	public AbstractDAOObject() {
		bd=BaseDonneeH2.getInstance();
	}


	public IBaseDonnee getBd() {
		return bd;
	}


	public void setBd(IBaseDonnee bd) {
		this.bd = bd;
	}
	
	
	
	
	
}
