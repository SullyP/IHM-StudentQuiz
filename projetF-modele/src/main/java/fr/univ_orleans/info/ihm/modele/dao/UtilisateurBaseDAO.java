package fr.univ_orleans.info.ihm.modele.dao;

import fr.univ_orleans.info.ihm.modele.beans.Entite;
import fr.univ_orleans.info.ihm.modele.beans.IEntite;
import fr.univ_orleans.info.ihm.modele.beans.IUtilisateur;
import fr.univ_orleans.info.ihm.modele.beans.Utilisateur;
import fr.univ_orleans.info.ihm.modele.dao.db.BaseDonneeEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.EntiteEnum;
import fr.univ_orleans.info.ihm.modele.dao.db.UtilisateurEnum;
import org.apache.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class UtilisateurBaseDAO extends AbstractDAOObject implements IUtilisateurDAO {
    private static final Logger LOGGER = Logger.getLogger(UtilisateurBaseDAO.class.getCanonicalName());
    private static IUtilisateurDAO instance = null;

    private UtilisateurBaseDAO() {
        super();
    }

    /**
     * Permet d'obtenir l'instance unique de la classe singleton.
     *
     * @return instance unique de la classe singleton.
     */
    public static IUtilisateurDAO getInstance() {
        if (instance == null) {
            instance = new UtilisateurBaseDAO();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur creerUtilisateur(String prenom, String nom, String identifiant, String motDePasse, int numeroEtudiant, int idEntite) {
        IUtilisateur utilisateur = null;
        String sqlQuery = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s) VALUES (?,?,?,?,?,?);",
                BaseDonneeEnum.UTILISATEUR,
                UtilisateurEnum.PRENOM_UTILISATEUR, UtilisateurEnum.NOM_UTILISATEUR, UtilisateurEnum.IDENTIFIANT_UTILISATEUR,
                UtilisateurEnum.MOT_DE_PASSE_UTILISATEUR, UtilisateurEnum.NUMERO_ETUDIANT, UtilisateurEnum.ID_ENTITE);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        //Cryptage du mot de passe
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String motDePasseCrypt = passwordEncryptor.encryptPassword(motDePasse);

        ResultSet resultSet = null;
        try {
            int numeroParametre = 1;
            preparedStatement.setString(numeroParametre, prenom);
            preparedStatement.setString(++numeroParametre, nom);
            preparedStatement.setString(++numeroParametre, identifiant);
            preparedStatement.setString(++numeroParametre, motDePasseCrypt);
            preparedStatement.setInt(++numeroParametre, numeroEtudiant);
            preparedStatement.setInt(++numeroParametre, idEntite);
            preparedStatement.executeUpdate();
            //On cherche à obtenir l'idUtilisateur généré.
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        if (resultSet != null) {
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //On créé une instance Utilisateur avec les informations à notre disposition.
                utilisateur = new Utilisateur(resultSet.getInt(1), numeroEtudiant, nom, prenom, identifiant, motDePasseCrypt);
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return utilisateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur getUtilisateur(int idUtilisateur) {
        IUtilisateur utilisateur = null;
        String sqlQuery = String.format("SELECT u.%s, u.%s, u.%s, u.%s, u.%s, u.%s, u.%s, e.%s  FROM %s u JOIN %s e ON u.%s = e.%s AND u.%s=?;",
                UtilisateurEnum.ID_UTILISATEUR, UtilisateurEnum.NUMERO_ETUDIANT, UtilisateurEnum.NOM_UTILISATEUR, UtilisateurEnum.PRENOM_UTILISATEUR,
                UtilisateurEnum.IDENTIFIANT_UTILISATEUR, UtilisateurEnum.MOT_DE_PASSE_UTILISATEUR, UtilisateurEnum.ID_ENTITE, EntiteEnum.NOM_ENTITE,
                BaseDonneeEnum.UTILISATEUR, BaseDonneeEnum.ENTITE,
                UtilisateurEnum.ID_ENTITE, EntiteEnum.ID_ENTITE, UtilisateurEnum.ID_UTILISATEUR);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, idUtilisateur);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        if (resultSet != null) {
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
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return utilisateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur getUtilisateurByIdentifiant(String identifiant) {
        IUtilisateur utilisateur = null;
        String sqlQuery = String.format("SELECT u.%s, u.%s, u.%s, u.%s, u.%s, u.%s, u.%s, e.%s  FROM %s u JOIN %s e ON u.%s = e.%s AND u.%s=?;",
                UtilisateurEnum.ID_UTILISATEUR, UtilisateurEnum.NUMERO_ETUDIANT, UtilisateurEnum.NOM_UTILISATEUR, UtilisateurEnum.PRENOM_UTILISATEUR,
                UtilisateurEnum.IDENTIFIANT_UTILISATEUR, UtilisateurEnum.MOT_DE_PASSE_UTILISATEUR, UtilisateurEnum.ID_ENTITE, EntiteEnum.NOM_ENTITE,
                BaseDonneeEnum.UTILISATEUR, BaseDonneeEnum.ENTITE,
                UtilisateurEnum.ID_ENTITE, EntiteEnum.ID_ENTITE, UtilisateurEnum.IDENTIFIANT_UTILISATEUR);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        ResultSet resultSet = null;
        try {
            preparedStatement.setString(1, identifiant);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        if (resultSet != null) {
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
                LOGGER.warn(e);
            }
        }
        this.getBd().closePrepared(preparedStatement);

        return utilisateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur majMotDePasse(int idUtilisateur, String motDePasse) {
        IUtilisateur utilisateur = null;
        String sqlQuery = String.format("UPDATE %s SET %s=? WHERE %s=?;",
                BaseDonneeEnum.UTILISATEUR, UtilisateurEnum.MOT_DE_PASSE_UTILISATEUR, UtilisateurEnum.ID_UTILISATEUR);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        //Cryptage du mot de passe
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String motDePasseCrypt = passwordEncryptor.encryptPassword(motDePasse);

        try {
            int numeroParametre = 1;
            preparedStatement.setString(numeroParametre, motDePasseCrypt);
            preparedStatement.setInt(++numeroParametre, idUtilisateur);
            //On créé une instance Utilisateur avec les informations à notre disposition.
            utilisateur = new Utilisateur(idUtilisateur);
            utilisateur.setMotDePasseUtilisateur(motDePasseCrypt);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);

        return utilisateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUtilisateur majEntite(int idUtilisateur, int idEntite) {
        IUtilisateur utilisateur = null;
        String sqlQuery = String.format("UPDATE %s SET %s=? WHERE %s=?;",
                BaseDonneeEnum.UTILISATEUR, UtilisateurEnum.ID_ENTITE, UtilisateurEnum.ID_UTILISATEUR);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            int numeroParametre = 1;
            preparedStatement.setInt(numeroParametre, idEntite);
            preparedStatement.setInt(++numeroParametre, idUtilisateur);
            //On créé une instance Utilisateur avec les informations à notre disposition.
            utilisateur = new Utilisateur(idUtilisateur);
            utilisateur.setEntiteUtilisateur(new Entite(idEntite));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }
        this.getBd().closePrepared(preparedStatement);

        return utilisateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suppressionUtilisateur(int idUtilisateur) {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s=?;",
                BaseDonneeEnum.UTILISATEUR, UtilisateurEnum.ID_UTILISATEUR);
        PreparedStatement preparedStatement = this.getBd().openPrepared(sqlQuery);

        try {
            preparedStatement.setInt(1, idUtilisateur);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        this.getBd().closePrepared(preparedStatement);
    }
}
