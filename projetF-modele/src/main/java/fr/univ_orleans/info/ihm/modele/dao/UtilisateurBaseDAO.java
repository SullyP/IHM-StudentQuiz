package fr.univ_orleans.info.ihm.modele.dao;

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


public class UtilisateurBaseDAO extends AbstractDAOObject implements IUtilisateurDAO {
    private static IUtilisateurDAO instance=null;

    private UtilisateurBaseDAO(){
        super();
    }

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
                BaseDonneeEnum.Utilisateur,
                UtilisateurEnum.prenomUtilisateur, UtilisateurEnum.nomUtilisateur, UtilisateurEnum.identifiantUtilisateur,
                UtilisateurEnum.motDePasseUtilisateur, UtilisateurEnum.numeroEtudiant, UtilisateurEnum.idEntite);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        //On ajoute les valeurs de la requête préparée
        try {
            preparedStatement.setString(1, prenom);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, identifiant);
            preparedStatement.setString(4, motDePasse);
            preparedStatement.setInt(5, numeroEtudiant);
            preparedStatement.setInt(6, idEntite);
        } catch (SQLException e){
            //TODO Système de log
            e.printStackTrace();
        }

        ResultSet resultSet = null;
        try {
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idUtilisateur généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e) {
            //TODO Système de log
            e.printStackTrace();
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Utilisateur avec les informations à notre disposition.
                utilisateur = new Utilisateur(resultSet.getInt(1), numeroEtudiant, nom, prenom, identifiant, motDePasse);
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
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
                UtilisateurEnum.idUtilisateur, UtilisateurEnum.numeroEtudiant, UtilisateurEnum.nomUtilisateur, UtilisateurEnum.prenomUtilisateur,
                UtilisateurEnum.identifiantUtilisateur, UtilisateurEnum.motDePasseUtilisateur, UtilisateurEnum.idEntite, EntiteEnum.nomEntite,
                BaseDonneeEnum.Utilisateur, BaseDonneeEnum.Entite,
                UtilisateurEnum.idEntite, EntiteEnum.idEntite, UtilisateurEnum.idUtilisateur);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);
        //On ajoute les valeurs de la requête préparée
        try {
            preparedStatement.setInt(1, idUtilisateur);
        } catch (SQLException e){
            //TODO Système de log
            e.printStackTrace();
        }

        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            //TODO Système de log
            e.printStackTrace();
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Utilisateur avec les informations à notre disposition.
                utilisateur = new Utilisateur(resultSet.getInt(UtilisateurEnum.idUtilisateur.toString()),
                        resultSet.getInt(UtilisateurEnum.numeroEtudiant.toString()),
                        resultSet.getString(UtilisateurEnum.nomUtilisateur.toString()),
                        resultSet.getString(UtilisateurEnum.prenomUtilisateur.toString()),
                        resultSet.getString(UtilisateurEnum.identifiantUtilisateur.toString()),
                        resultSet.getString(UtilisateurEnum.motDePasseUtilisateur.toString()));
                //Ainsi qu'une instance d'Entite que l'on ajoutera dans Utilisateur
                IEntite entite = new Entite(resultSet.getInt(UtilisateurEnum.idEntite.toString()),
                        resultSet.getString(EntiteEnum.nomEntite.toString()));
                utilisateur.setEntiteUtilisateur(entite);
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
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
                BaseDonneeEnum.Utilisateur, UtilisateurEnum.idUtilisateur);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idUtilisateur);
            //On éxécute la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            //TODO Système de log
            e.printStackTrace();
        }

        this.getBd().closePrepared(preparedStatement);
    }
}