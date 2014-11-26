package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.MyLogger;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.EntiteEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.UtilisateurEnum;
import fr.univ_orleans.info.ihm.modele.modele.Entite;
import fr.univ_orleans.info.ihm.modele.modele.IEntite;
import fr.univ_orleans.info.ihm.modele.modele.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.modele.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;


public final class UtilisateurBaseDAO extends AbstractDAOObject implements IUtilisateurDAO {
    private static IUtilisateurDAO instance=null;

    private UtilisateurBaseDAO(){
        super();
    }

    /**
     * Permet d'obtenir l'instance unique de la classe singleton.
     * @return instance unique de la classe singleton.
     */
    public static IUtilisateurDAO getInstance(){
        if(instance == null){
            instance = new UtilisateurBaseDAO();
        }
        return instance;
    }

    @Override
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite){
        IUtilisateur utilisateur = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s) VALUES (?,?,?,?,?,?);",
                BaseDonneeEnum.UTILISATEUR,
                UtilisateurEnum.PRENOM_UTILISATEUR, UtilisateurEnum.NOM_UTILISATEUR, UtilisateurEnum.IDENTIFIANT_UTILISATEUR,
                UtilisateurEnum.MOT_DE_PASSE_UTILISATEUR, UtilisateurEnum.NUMERO_ETUDIANT, UtilisateurEnum.ID_ENTITE);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setString(1, prenom);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, identifiant);
            preparedStatement.setString(4, motDePasse);
            preparedStatement.setInt(5, numeroEtudiant);
            preparedStatement.setInt(6, idEntite);
            //On éxécute la requête
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idUtilisateur généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.INFO, UtilisateurBaseDAO.class.getName(), "creerUtilisateur", "Erreur lors de l'éxécution d'une requête SQL.", e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Utilisateur avec les informations à notre disposition.
                utilisateur = new Utilisateur(resultSet.getInt(1), numeroEtudiant, nom, prenom, identifiant, motDePasse);
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.INFO, UtilisateurBaseDAO.class.getName(), "creerUtilisateur", "Erreur lors de l'éxécution d'une requête SQL.", e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return utilisateur;
    }

    @Override
    public IUtilisateur getUtilisateur(int idUtilisateur) {
        IUtilisateur utilisateur = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("SELECT u.%s, u.%s, u.%s, u.%s, u.%s, u.%s, u.%s, e.%s  FROM %s u JOIN %s e ON u.%s = e.%s AND u.%s=?;",
                UtilisateurEnum.ID_UTILISATEUR, UtilisateurEnum.NUMERO_ETUDIANT, UtilisateurEnum.NOM_UTILISATEUR, UtilisateurEnum.PRENOM_UTILISATEUR,
                UtilisateurEnum.IDENTIFIANT_UTILISATEUR, UtilisateurEnum.MOT_DE_PASSE_UTILISATEUR, UtilisateurEnum.ID_ENTITE, EntiteEnum.NOM_ENTITE,
                BaseDonneeEnum.UTILISATEUR, BaseDonneeEnum.ENTITE,
                UtilisateurEnum.ID_ENTITE, EntiteEnum.ID_ENTITE, UtilisateurEnum.ID_UTILISATEUR);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idUtilisateur);
            //On éxécute la requête
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.INFO, UtilisateurBaseDAO.class.getName(), "getUtilisateur", "Erreur lors de l'éxécution d'une requête SQL.", e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Utilisateur avec les informations à notre disposition.
                utilisateur = new Utilisateur(resultSet.getInt(UtilisateurEnum.ID_UTILISATEUR.toString()),
                        resultSet.getInt(UtilisateurEnum.NUMERO_ETUDIANT.toString()),
                        resultSet.getString(UtilisateurEnum.NOM_UTILISATEUR.toString()),
                        resultSet.getString(UtilisateurEnum.PRENOM_UTILISATEUR.toString()),
                        resultSet.getString(UtilisateurEnum.IDENTIFIANT_UTILISATEUR.toString()),
                        resultSet.getString(UtilisateurEnum.MOT_DE_PASSE_UTILISATEUR.toString()));
                //Ainsi qu'une instance d'ENTITE que l'on ajoutera dans Utilisateur
                IEntite entite = new Entite(resultSet.getInt(UtilisateurEnum.ID_ENTITE.toString()),
                        resultSet.getString(EntiteEnum.NOM_ENTITE.toString()));
                utilisateur.setEntiteUtilisateur(entite);
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.INFO, UtilisateurBaseDAO.class.getName(), "getUtilisateur", "Erreur lors de l'éxécution d'une requête SQL.", e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return utilisateur;
    }

    @Override
    public IUtilisateur majMotDePasse(int idUtilisateur, String motDePasse) {
        return null;
    }

    @Override
    public IUtilisateur majEntite(int idUtilisateur, int idEntite) {
        return null;
    }

    @Override
    public void suppressionUtilisateur(int idUtilisateur) {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.UTILISATEUR, UtilisateurEnum.ID_UTILISATEUR);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idUtilisateur);
            //On éxécute la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.INFO, UtilisateurBaseDAO.class.getName(), "suppressionUtilisateur", "Erreur lors de l'éxécution d'une requête SQL.", e);
        }

        this.getBd().closePrepared(preparedStatement);
    }
}