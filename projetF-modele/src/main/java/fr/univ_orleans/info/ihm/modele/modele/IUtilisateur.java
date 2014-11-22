package fr.univ_orleans.info.ihm.modele.modele;

public interface IUtilisateur {
    public int getIdUtilisateur();
    public int getIdEntiteUtilisateur();
    public int getIdDroitUtilisateur();
    public int getNumeroEtudiant();
    public String getNomUtilisateur();
    public String getPrenomUtilisateur();
    public String getNomEntiteUtilisateur();
    public String getTypeDroitUtilisateur();
    public String getIdentifiantUtilisateur();
    public IUtilisateur getUtilisateur();
    public IDroit getDroitUtilisateur();
    public IEntite getEntiteUtilisateur();
    public void setUtilisateur(IUtilisateur u);
    public void setDroitUtilisateur(IDroit d);
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
}
