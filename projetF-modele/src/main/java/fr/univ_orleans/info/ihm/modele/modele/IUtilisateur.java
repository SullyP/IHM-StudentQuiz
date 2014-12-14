package fr.univ_orleans.info.ihm.modele.modele;

public interface IUtilisateur {
    public int getIdUtilisateur();
    public int getIdEntiteUtilisateur();
    public int getNumeroEtudiant();
    public String getNomUtilisateur();
    public String getPrenomUtilisateur();
    public String getNomEntiteUtilisateur();
    public String getIdentifiantUtilisateur();
    public IUtilisateur getUtilisateur();
    public IEntite getEntiteUtilisateur();
    public void setUtilisateur(IUtilisateur u);
    public void setEntiteUtilisateur(IEntite e);
    public void setNumeroEtudiant(int numeroEtudiant);
    public void setIdentifiantUtilisateur(String login);
    public void setMotDePasseUtilisateur(String motDePasse);

    /**
     * Permet de vérifier si le mot de passe est correct.
     * @param motDePasse mot de passe de l'utilisateur.
     * @return vrai si le mot de passe est correct, faux sinon.
     */
    public boolean validerMotDePasseUtilisateur(String motDePasse);

    /**
     * Permet de savoir si un utilisateur est Admin
     * @return vrai s'il est admin, faux sinon
     */
    public boolean isAdmin();
}
