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
            monInstance.insertInitValue();
            monInstance.close();
        }
        return monInstance;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    private void insertInitValue() {
        String query = String.format("SELECT COUNT(*) FROM %s", BaseDonneeEnum.ENTITE);

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
        } catch(SQLException e) {
            //On log l'exception
            MyLogger.getLogger().logp(Level.SEVERE, BaseDonneeH2.class.getName(), "insertInitValue", MyLogger.MESSAGE_ERREUR_SQL, e);
        }

        boolean insert = false;
        if(resultSet!=null){
            try {
                //Si resusltSet n'est pas nul, on accède à la première ligne.
                resultSet.next();
                //Si le nombre d'entité en BDD est de 0, alors on doit insérer des données par défaut (pas d'entité, donc pas d'utilisateur...etc).
                insert = resultSet.getInt(1) == 0;
            } catch (SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.WARNING, BaseDonneeH2.class.getName(), "insertInitValue", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }

        //Si l'on doit insérer des données
        if(insert){
            query = new StringBuilder(String.format("INSERT INTO %s (%s) VALUES ('Etudiant'),('Professeur');",
                            BaseDonneeEnum.ENTITE, EntiteEnum.NOM_ENTITE))
                    .append(String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s) VALUES (2,'Admin','Admin','admin','admin',0),(1,'Etudiant','Etudiant','etudiant','etudiant',123);",
                            BaseDonneeEnum.UTILISATEUR, UtilisateurEnum.ID_ENTITE, UtilisateurEnum.NOM_UTILISATEUR, UtilisateurEnum.PRENOM_UTILISATEUR, UtilisateurEnum.IDENTIFIANT_UTILISATEUR, UtilisateurEnum.MOT_DE_PASSE_UTILISATEUR, UtilisateurEnum.NUMERO_ETUDIANT))
                    .append(String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES ('Quel est la réponse de la vie ?',false,5,1),('Quel note devez vous mettre à notre projet ?',false,5,3),('Qui sont les membres du groupe F ?',true,8,3);",
                            BaseDonneeEnum.QUESTION, QuestionEnum.INTITULE_QUESTION, QuestionEnum.MULTIPLE_QUESTION, QuestionEnum.DUREE_QUESTION, QuestionEnum.POINT_QUESTION))
                    .append(String.format("INSERT INTO %s (%s,%s,%s) VALUES (1,'42',true),(1,'Alexis Lavie',false),(2,'0',false),(2,'20',true),(3,'Jean Bon',false),(3,'Sullivan Perrin',true),(3,'Eléonore Gédéon',true),(3,'Alexis Lavie',true);",
                            BaseDonneeEnum.REPONSE,ReponseEnum.ID_QUESTION, ReponseEnum.INTITULE_REPONSE, ReponseEnum.CORRECT_REPONSE))
                    .append(String.format("INSERT INTO %s (%s,%s,%s) VALUES ('QCM IHM','2014-01-01',2);",
                            BaseDonneeEnum.QCM, QCMEnum.NOM_QCM, QCMEnum.DATE_CREATION, QCMEnum.ID_CREATEUR))
                    .append(String.format("INSERT INTO %s (%s,%s) VALUES (1,1),(2,1),(3,1);",
                            BaseDonneeEnum.QCM_QUESTION, QCMQuestionEnum.ID_QUESTION, QCMQuestionEnum.ID_QCM))
                    .append(String.format("INSERT INTO %s (%s,%s,%s) VALUES (2,1,'2014-01-11');",
                            BaseDonneeEnum.RESULTAT_UTILISATEUR, ResultatUtilisateurEnum.ID_UTILISATEUR, ResultatUtilisateurEnum.ID_QCM, ResultatUtilisateurEnum.DATE_RESULTAT_UTILISATEUR))
                    .append(String.format("INSERT INTO %s (%s,%s) VALUES (1,1),(1,4),(1,5),(1,6);",
                            BaseDonneeEnum.REPONSE_UTILISATEUR, ReponseUtilisateurEnum.ID_RESULTAT_UTILISATEUR, ReponseUtilisateurEnum.ID_REPONSE)).toString();

            try {
                stmt.execute(query);
            } catch(SQLException e) {
                //On log l'exception
                MyLogger.getLogger().logp(Level.SEVERE, BaseDonneeH2.class.getName(), "insertInitValue", MyLogger.MESSAGE_ERREUR_SQL, e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet executeRequest(String sql) throws SQLException{
        return stmt.executeQuery(sql);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet executeUpdate(String sql) throws SQLException{
        stmt.executeUpdate(sql);
        return stmt.getGeneratedKeys();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDbPath() {
        return dbPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDbPath(String path) {
        if(!dbPath.equals(path)) {
            dbPath = path;
            monInstance = null;
        }
    }

}
