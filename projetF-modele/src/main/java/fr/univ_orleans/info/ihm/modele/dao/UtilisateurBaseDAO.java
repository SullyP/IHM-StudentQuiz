package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.dao.db.IBaseDonnee;
import fr.univ_orleans.info.ihm.modele.modele.*;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UtilisateurBaseDAO extends AbstractDAOObject implements IUtilisateurDAO {
    private static IUtilisateurDAO instance=null;
    private IBaseDonnee bd;

    private UtilisateurBaseDAO(){
        super();
        this.bd = getBd();
    }

    public static IUtilisateurDAO getInstance(){
        if(instance == null){
            instance = new UtilisateurBaseDAO();
        }
        return instance;
    }

    @Override
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite, int idDroit){
        IUtilisateur utilisateur = null;
        bd.open();
        String sqlQuery = String.format("INSERT INTO ProjetIHM.Utilisateur (prenomUtilisateur, nomUtilisateur, identifiantUtilisateur, motDePasseUtilisateur, numeroEtudiant, idEntite, idDroit) VALUES ('%s', '%s', '%s', '%s', %d, %d, %d);",
                prenom, nom, identifiant, motDePasse, numeroEtudiant, idEntite, idDroit);
        ResultSet r = null;
        try {
            r = bd.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(r!=null){
            try {
                r.next();
                utilisateur = new Utilisateur(r.getInt(1), numeroEtudiant, nom, prenom, identifiant, motDePasse);
                r.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        bd.close();

        return utilisateur;
    }

    @Override
    public IUtilisateur getUtilisateur(int idUtilisateur) {
        IUtilisateur utilisateur = null;
        bd.open();
        String sqlQuery = String.format("SELECT u.IDUTILISATEUR, u.NUMEROETUDIANT, u.NOMUTILISATEUR, u.PRENOMUTILISATEUR, u.IDENTIFIANTUTILISATEUR, u.MOTDEPASSEUTILISATEUR, u.IDDROIT, d.TYPEDROIT, u.IDENTITE, e.NOMENTITE  FROM ((PROJETIHM.UTILISATEUR u  JOIN PROJETIHM.DROIT d ON u.IDDROIT = d.IDDROIT) JOIN PROJETIHM.ENTITE e ON u.IDENTITE = e.IDENTITE) WHERE u.IDUTILISATEUR=%d;",
                idUtilisateur);
        ResultSet r = null;
        try {
            r = bd.executeRequest(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(r!=null){
            try {
                r.next();
                utilisateur = new Utilisateur(r.getInt("idUtilisateur"), r.getInt("numeroEtudiant"), r.getString("nomUtilisateur"), r.getString("prenomUtilisateur"), r.getString("identifiantUtilisateur"), r.getString("motDePasseUtilisateur"));
                IDroit droit = new Droit(r.getInt("idDroit"), r.getString("typeDroit"));
                utilisateur.setDroitUtilisateur(droit);
                IEntite entite = new Entite(r.getInt("idEntite"), r.getString("nomEntite"));
                utilisateur.setEntiteUtilisateur(entite);
                r.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        bd.close();

        return utilisateur;
    }

    @Override
    public IUtilisateur majMotDePasse(int idUtilisateur, String motDePasse) {
        return null;
    }

    @Override
    public IUtilisateur majDroit(int idUtilisateur, IDroit droit) {
        return null;
    }

    @Override
    public IUtilisateur majEntite(int idUtilisateur, IEntite entite) {
        return null;
    }

    @Override
    public void suppressionUtilisateur(int idUtilisateur) {
        bd.open();
        String sqlQuery = String.format("DELETE FROM PROJETIHM.UTILISATEUR WHERE IDUTILISATEUR=%d;",
                idUtilisateur);
        try {
            bd.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        bd.close();
    }
}