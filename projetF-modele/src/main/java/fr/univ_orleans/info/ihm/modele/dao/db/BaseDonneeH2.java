package fr.univ_orleans.info.ihm.modele.dao.db;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDonneeH2 implements IBaseDonnee {
	 private JdbcDataSource ds;
	 private Connection conn;
	 private Statement stmt;
	 private String dbPath = "~/test";
	 private static BaseDonneeH2 monInstance;
	 private BaseDonneeH2() {
		 try {                   
			 Class.forName("org.h2.Driver");
			 ds = new JdbcDataSource();
			 ds.setURL("jdbc:h2:"+ dbPath);
			 ds.setUser("sa");
			 ds.setPassword("");
	            
     	} catch (ClassNotFoundException e) {
     		System.err.println("cannot create datasource");
     		e.printStackTrace();
    	}
	 }
	 
	 
	 
	 public static IBaseDonnee getInstance() {
		 if (monInstance == null) {
			 monInstance = new BaseDonneeH2();
			monInstance.open();
			 monInstance.createSchema();
			 monInstance.close();
		 }
		 return monInstance;
	 }
	 
	 	/* (non-Javadoc)
		 * @see dao.db.IBaseDonnee#open()
		 */
	 	public void open(){
	 		 try {
					this.conn = this.ds.getConnection();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				 try {
					this.stmt = this.conn.createStatement();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	 	}
	 
	 
	    /* (non-Javadoc)
		 * @see dao.db.IBaseDonnee#close()
		 */
	    public void close(){
	        try {
	        	if(stmt != null)
	            stmt.close();
	        	if(conn != null)
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	    }
	    	    
	    
	    private void createSchema() {
	    	String query = "CREATE SCHEMA IF NOT EXISTS ProjetIHM;"
							+ "CREATE TABLE IF NOT EXISTS ProjetIHM.Entite (idEntite INT NOT NULL AUTO_INCREMENT, nomEntite VARCHAR2(50), PRIMARY KEY(idEntite));"
							+ "CREATE TABLE IF NOT EXISTS ProjetIHM.Droit (idDroit INT NOT NULL AUTO_INCREMENT,  typeDroit VARCHAR2(50), PRIMARY KEY(idDroit));"
	    					+ "CREATE TABLE IF NOT EXISTS ProjetIHM.Utilisateur (idUtilisateur INT NOT NULL AUTO_INCREMENT, idDroit INT, idEntite INT, numeroEtudiant INT, nomUtilisateur VARCHAR2(50), prenomUtilisateur VARCHAR2(50), identifiantUtilisateur VARCHAR2(50), motDePasseUtilisateur VARCHAR2(50), PRIMARY KEY(idUtilisateur), FOREIGN KEY(idDroit) REFERENCES ProjetIHM.Droit(idDroit), FOREIGN KEY(idEntite) REFERENCES ProjetIHM.Entite(idEntite));"
							+ "CREATE TABLE IF NOT EXISTS ProjetIHM.Question (idQuestion INT NOT NULL AUTO_INCREMENT, enonceQuestion VARCHAR2(250), multipleQuestion BOOLEAN, ratioQuestion INT, PRIMARY KEY(idQuestion));"
							+ "CREATE TABLE IF NOT EXISTS ProjetIHM.Reponse (idReponse INT NOT NULL AUTO_INCREMENT, idQuestion INT NOT NULL, enonceReponse VARCHAR2(250), correctReponse BOOLEAN, PRIMARY KEY(idReponse), FOREIGN KEY(idQuestion) REFERENCES ProjetIHM.Question(idQuestion));"
							+ "CREATE TABLE IF NOT EXISTS ProjetIHM.QCM (idQCM INT NOT NULL AUTO_INCREMENT, idCreateur INT NOT NULL, nomQCM VARCHAR2(250), dateCreation DATE, PRIMARY KEY(idQCM), FOREIGN KEY(idCreateur) REFERENCES ProjetIHM.Utilisateur(idUtilisateur));"
							+ "CREATE TABLE IF NOT EXISTS ProjetIHM.QCMQuestion (idQCM INT NOT NULL, idQuestion INT NOT NULL, PRIMARY KEY(idQCM, idQuestion), FOREIGN KEY(idQCM) REFERENCES ProjetIHM.QCM(idQCM), FOREIGN KEY(idQuestion) REFERENCES ProjetIHM.Question(idQuestion));"
							+ "CREATE TABLE IF NOT EXISTS ProjetIHM.ResultatUtilisateur (idResultatUtilisateur INT NOT NULL AUTO_INCREMENT, idUtilisateur INT NOT NULL, idQCM INT NOT NULL, dateResultatUtilisateur DATE, PRIMARY KEY(idResultatUtilisateur), FOREIGN KEY(idQCM) REFERENCES ProjetIHM.QCM(idQCM), FOREIGN KEY(idUtilisateur) REFERENCES ProjetIHM.Utilisateur(idUtilisateur));"
							+ "CREATE TABLE IF NOT EXISTS ProjetIHM.ReponseUtilisateur (idResultatUtilisateur INT NOT NULL, idQuestion INT NOT NULL, idReponse INT NOT NULL, PRIMARY KEY(idResultatUtilisateur, idQuestion, idReponse), FOREIGN KEY(idResultatUtilisateur) REFERENCES ProjetIHM.ResultatUtilisateur(idResultatUtilisateur), FOREIGN KEY(idQuestion) REFERENCES ProjetIHM.Question(idQuestion), FOREIGN KEY(idReponse) REFERENCES ProjetIHM.Reponse(idReponse));";

	    	try {
	            stmt = conn.createStatement();
	    	
	    	stmt.execute(query);


	    	} catch(SQLException e) {
	            e.printStackTrace();
	        }
		}

	    
		/* (non-Javadoc)
		 * @see dao.db.IBaseDonnee#executeRequest(java.lang.String)
		 */
		public ResultSet executeRequest(String sql) throws SQLException{
	        return stmt.executeQuery(sql);
	        
	        
	    }
	    
	    
	    /* (non-Javadoc)
		 * @see dao.db.IBaseDonnee#executeUpdate(java.lang.String, int)
		 */
	    public ResultSet executeUpdate(String sql) throws SQLException{
	         stmt.executeUpdate(sql);
	         return stmt.getGeneratedKeys();
	        
	    }

		/* (non-Javadoc)
		 * @see dao.db.IBaseDonnee#getDbPath()
		 */
		public String getDbPath() {
			return dbPath;
		}

		/* (non-Javadoc)
		 * @see dao.db.IBaseDonnee#setDbPath(java.lang.String)
		 */
		public void setDbPath(String dbPath) {
			this.dbPath = dbPath;
		}



	    
	 
	 
}
