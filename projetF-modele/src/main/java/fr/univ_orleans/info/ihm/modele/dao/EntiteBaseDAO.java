package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.MyLogger;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.EntiteEnum;
import fr.univ_orleans.info.ihm.modele.modele.Entite;
import fr.univ_orleans.info.ihm.modele.modele.IEntite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class EntiteBaseDAO extends AbstractDAOObject implements IEntiteDAO {
    private static IEntiteDAO instance=null;

    private EntiteBaseDAO(){
        super();
    }

    /**
     * Permet d'obtenir l'instance unique de la classe singleton.
     * @return instance unique de la classe singleton.
     */
    public static IEntiteDAO getInstance(){
        if(instance == null){
            instance = new EntiteBaseDAO();
        }
        return instance;
    }

    /**
     * Insère une nouvelle entite en BDD
     *
     * @param nom nom de l'entité
     * @return l'entité créé
     */
    @Override
    public IEntite creerEntite(String nom) {
        IEntite entite = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("INSERT INTO %s (%s) VALUES (?);",
                BaseDonneeEnum.ENTITE, EntiteEnum.NOM_ENTITE);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setString(1, nom);
            //On éxécute la requête
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idEntite généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, EntiteBaseDAO.class.getName(), "creerEntite", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Entite avec les informations à notre disposition.
                entite = new Entite(resultSet.getInt(1), nom);
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, EntiteBaseDAO.class.getName(), "creerEntite", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return entite;
    }

    /**
     * Permet d'obtenir une entité via son id
     *
     * @param idEntite id de l'entité
     * @return l'entité
     */
    @Override
    public IEntite getEntite(int idEntite) {
        IEntite entite = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?;",
                BaseDonneeEnum.ENTITE, EntiteEnum.ID_ENTITE);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idEntite);
            //On éxécute la requête
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, EntiteBaseDAO.class.getName(), "getEntite", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Entite avec les informations à notre disposition.
                entite = new Entite(resultSet.getInt(EntiteEnum.ID_ENTITE.toString()),
                        resultSet.getString(EntiteEnum.NOM_ENTITE.toString()));
                resultSet.close();
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, EntiteBaseDAO.class.getName(), "getEntite", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return entite;
    }

    /**
     * Permet de mettre à jour le nom d'une entité
     *
     * @param idEntite id de l'entité
     * @param nom      nom de l'entité
     * @return l'entité mise à jour
     */
    @Override
    public IEntite majEntite(int idEntite, String nom) {
        IEntite entite = null;
        //On écrit la requête à éxécuter
        String sqlQuery = String.format("UPDATE %s SET %s=? WHERE %s=?;",
                BaseDonneeEnum.ENTITE, EntiteEnum.NOM_ENTITE, EntiteEnum.ID_ENTITE);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2, idEntite);
            //On éxécute la requête
            preparedStatement.executeUpdate();
            //On créé une instance Entite avec les informations à notre disposition.
            entite = new Entite(idEntite, nom);
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, EntiteBaseDAO.class.getName(), "majEntite", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        this.getBd().closePrepared(preparedStatement);

        return entite;
    }

    /**
     * Permet de supprimer une entité via son id
     *
     * @param idEntite id de l'entité
     */
    @Override
    public void suppressionEntite(int idEntite) {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.ENTITE, EntiteEnum.ID_ENTITE);
        //On ouvre la connection à la bdd et on prépare la requête
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            //On ajoute les valeurs de la requête préparée
            preparedStatement.setInt(1, idEntite);
            //On éxécute la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            //On log l'exception
            MyLogger.getLogger().logp(Level.WARNING, EntiteBaseDAO.class.getName(), "suppressionEntite", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        this.getBd().closePrepared(preparedStatement);
    }
}
