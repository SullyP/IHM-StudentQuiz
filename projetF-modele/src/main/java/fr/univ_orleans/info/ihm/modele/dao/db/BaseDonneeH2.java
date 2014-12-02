package fr.univ_orleans.info.ihm.modele.dao.db;

import fr.univ_orleans.info.ihm.modele.MyLogger;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.*;
import java.util.logging.Level;

/**
 * Classe permettant l'abstraction à une base de donnée H2.
 */
public final class BaseDonneeH2 implements IBaseDonnee {
    private JdbcDataSource ds;
    private Connection conn;
    private Statement stmt;
    private static String dbPath = "~/qcm";
    private static BaseDonneeH2 monInstance;

    private BaseDonneeH2() {
        try {
            Class.forName("org.h2.Driver");
            ds = new JdbcDataSource();
            ds.setURL("jdbc:h2:"+ dbPath);
            ds.setUser("sa");
            ds.setPassword("");
        } catch (ClassNotFoundException e) {
            //On log l'exception
            MyLogger.getLogger().logp(Level.SEVERE, BaseDonneeH2.class.getName(), "Constructeur", "Cannot create data source.", e);
        }
    }

    /**
     * BaseDonneeH2 est une classe singleton, cette fonction permet d'obtenir son instance.
     * @return L'unique instance de la classe BaseDonneeH2.
     */
    public static IBaseDonnee getInstance() {
        if (monInstance == null) {
            monInstance = new BaseDonneeH2();
            monInstance.open();
            monInstance.createSchema();
            monInstance.close();
        }
        return monInstance;
    }

    /**
     * Permet d'ouvrir la connection à la base de donnée. A éxécuter avant de lancer executeRequest ou executeUpdate.
     */
    @Override
    public void open(){
        try {
            this.conn = this.ds.getConnection();
            this.stmt = this.conn.createStatement();
        } catch (SQLException e) {
            //On log l'exception
            MyLogger.getLogger().logp(Level.SEVERE, BaseDonneeH2.class.getName(), "open", "Erreur lors de la connection à la base de donnée.", e);
        }
    }

    /**
     * Permet d'ouvrir la connection à la base de donnée, et d'obtenir une requête paramétrée.
     *
     * @param sql Requête sql.
     * @return La requête paramétrée correspondant à la requête sql.
     */
    @Override
    public PreparedStatement openPrepared(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            this.conn = this.ds.getConnection();
            //On indique que dans le cas d'un INSERT on souhaite avoir les id générés.
            preparedStatement = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            //On log l'exception
            MyLogger.getLogger().logp(Level.SEVERE, BaseDonneeH2.class.getName(), "openPrepared" , "Erreur lors de la connection à la base de donnée.", e);
        }
        return preparedStatement;
    }


    /**
     * Permet de fermer la connection à la base de donnée.
     */
    @Override
    public void close(){
        try {
            if(stmt != null) {
                stmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            //On log l'exception
            MyLogger.getLogger().logp(Level.SEVERE, BaseDonneeH2.class.getName(), "close", "Erreur lors de la fermeture de la base de donnée.", e);
        }
    }

    /**
     * Permet de fermer la connection à la base de donnée, et une requête paramétrée
     *
     * @param preparedSql La requête paramétrée correspondant à la requête sql.
     */
    @Override
    public void closePrepared(PreparedStatement preparedSql) {
        try {
            preparedSql.close();
        } catch (SQLException e) {
            //On log l'exception
            MyLogger.getLogger().logp(Level.SEVERE, BaseDonneeH2.class.getName(), "closePrepared", "Erreur lors de la fermeture de la base de donnée.", e);
        }
        this.close();
    }

    /**
     * Permet de créer l'ensemble des tables de la base de données si ce n'est pas déjà fait.
     */
    private void createSchema() {
        String query = new StringBuilder().append("CREATE SCHEMA IF NOT EXISTS ProjetFIHM;")
                //La table ENTITE contient toutes les entités utilisateurs, par exemple: Etudiant, Professeur...
                .append("CREATE TABLE IF NOT EXISTS ProjetFIHM.ENTITE (idEntite INT NOT NULL AUTO_INCREMENT, nomEntite VARCHAR2(50), PRIMARY KEY(idEntite));")
                        //La table Utilisateur contient tout les utilisateurs.
                .append("CREATE TABLE IF NOT EXISTS ProjetFIHM.Utilisateur (idUtilisateur INT NOT NULL AUTO_INCREMENT, idEntite INT NOT NULL, numeroEtudiant INT, nomUtilisateur VARCHAR2(50), prenomUtilisateur VARCHAR2(50), identifiantUtilisateur VARCHAR2(50), motDePasseUtilisateur VARCHAR2(50), PRIMARY KEY(idUtilisateur), FOREIGN KEY(idEntite) REFERENCES ProjetFIHM.ENTITE(idEntite));")
                        //La table Question contient toutes les questions disponibles pour l'ajout dans un QCM.
                .append("CREATE TABLE IF NOT EXISTS ProjetFIHM.Question (idQuestion INT NOT NULL AUTO_INCREMENT, intituleQuestion VARCHAR2(250), multipleQuestion BOOLEAN, dureeQuestion INT NOT NULL, pointQuestion INT NOT NULL, PRIMARY KEY(idQuestion));")
                        //La table Reponse contient toutes les réponses associées à une question donnée.
                .append("CREATE TABLE IF NOT EXISTS ProjetFIHM.Reponse (idReponse INT NOT NULL AUTO_INCREMENT, idQuestion INT NOT NULL, intituleReponse VARCHAR2(250), correctReponse BOOLEAN, PRIMARY KEY(idReponse), FOREIGN KEY(idQuestion) REFERENCES ProjetFIHM.Question(idQuestion) ON DELETE CASCADE);")
                        //La table QCM contient tout les QCM créés dans l'aplication.
                .append("CREATE TABLE IF NOT EXISTS ProjetFIHM.QCM (idQCM INT NOT NULL AUTO_INCREMENT, idCreateur INT NOT NULL, nomQCM VARCHAR2(250), dateCreation DATE, PRIMARY KEY(idQCM), FOREIGN KEY(idCreateur) REFERENCES ProjetFIHM.Utilisateur(idUtilisateur) ON DELETE CASCADE);")
                        //La table QCMQuestion contient toutes les questions associées à un QCM donné.
                .append("CREATE TABLE IF NOT EXISTS ProjetFIHM.QCMQuestion (idQCM INT NOT NULL, idQuestion INT NOT NULL, PRIMARY KEY(idQCM, idQuestion), FOREIGN KEY(idQCM) REFERENCES ProjetFIHM.QCM(idQCM) ON DELETE CASCADE, FOREIGN KEY(idQuestion) REFERENCES ProjetFIHM.Question(idQuestion) ON DELETE CASCADE);")
                        //La table ResultatUtilisateur contient toutes les participations à un QCM pour un utilisateur donné.
                .append("CREATE TABLE IF NOT EXISTS ProjetFIHM.ResultatUtilisateur (idResultatUtilisateur INT NOT NULL AUTO_INCREMENT, idUtilisateur INT NOT NULL, idQCM INT NOT NULL, dateResultatUtilisateur DATE, PRIMARY KEY(idResultatUtilisateur), FOREIGN KEY(idQCM) REFERENCES ProjetFIHM.QCM(idQCM) ON DELETE CASCADE, FOREIGN KEY(idUtilisateur) REFERENCES ProjetFIHM.Utilisateur(idUtilisateur) ON DELETE CASCADE);")
                        //La table ReponseUtilisateur contient toutes les réponses données par un utilisateur pour un ResultatUtilisateur donné (suite à la participation à un QCM).
                .append("CREATE TABLE IF NOT EXISTS ProjetFIHM.ReponseUtilisateur (idResultatUtilisateur INT NOT NULL, idReponse INT NOT NULL, PRIMARY KEY(idResultatUtilisateur, idReponse), FOREIGN KEY(idResultatUtilisateur) REFERENCES ProjetFIHM.ResultatUtilisateur(idResultatUtilisateur) ON DELETE CASCADE, FOREIGN KEY(idReponse) REFERENCES ProjetFIHM.Reponse(idReponse) ON DELETE CASCADE);").toString();

        try {
            stmt.execute(query);
        } catch(SQLException e) {
            //On log l'exception
            MyLogger.getLogger().logp(Level.SEVERE, BaseDonneeH2.class.getName(), "createSchema", MyLogger.MESSAGE_ERREUR_SQL, e);
        }
    }


    /**
     * Permet d'éxécuter une requête SQL (SELECT).
     * @param sql la requête SQL à éxécuter
     * @return les résultats de la requête
     * @throws java.sql.SQLException lève des exceptions si la requête soulève des erreurs à la BDD.
     */
    @Override
    public ResultSet executeRequest(String sql) throws SQLException{
        return stmt.executeQuery(sql);
    }


    /**
     * Permet d'éxécuter une requête SQL (UPDATE, INSERT, DELETE).
     * @param sql la requête SQL à éxécuter
     * @return les résultats de la requête correspondant aux id créé.
     * @throws java.sql.SQLException lève des exceptions si la requête soulève des erreurs à la BDD.
     */
    @Override
    public ResultSet executeUpdate(String sql) throws SQLException{
        stmt.executeUpdate(sql);
        return stmt.getGeneratedKeys();
    }

    /**
     * Permet d'obtenir le chemin d'accès de la BDD.
     * @return chemin d'accès de la BDD.
     */
    @Override
    public String getDbPath() {
        return dbPath;
    }

    /**
     * Permet de définir le chemin d'accès de la BDD.
     * @param path chemin d'accès de la BDD.
     */
    @Override
    public void setDbPath(String path) {
        if(!dbPath.equals(path)) {
            dbPath = path;
            monInstance = null;
        }
    }

}
