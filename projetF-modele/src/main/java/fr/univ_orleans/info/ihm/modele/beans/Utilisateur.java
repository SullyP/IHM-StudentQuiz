package fr.univ_orleans.info.ihm.modele.beans;

import java.io.Serializable;

public class Utilisateur implements IUtilisateur, Serializable{
    private final String NOM_ENTITE_ADMIN = "Professeur";

    private int idUtilisateur;
    private int numeroEtudiant;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String identifiantUtilisateur;
    private String motDePasseUtilisateur;
    private IEntite entiteUtilisateur;

    public Utilisateur(int idUtilisateur, int numeroEtudiant, String nomUtilisateur, String prenomUtilisateur, String identifiantUtilisateur, String motDePasseUtilisateur){
        this.idUtilisateur = idUtilisateur;
        this.numeroEtudiant = numeroEtudiant;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.identifiantUtilisateur = identifiantUtilisateur;
        this.motDePasseUtilisateur = motDePasseUtilisateur;
    }

    public Utilisateur(int idUtilisateur){
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int getIdUtilisateur() {
        return this.idUtilisateur;
    }

    @Override
    public int getIdEntiteUtilisateur() {
        return this.entiteUtilisateur.getIdEntite();
    }

    @Override
    public int getNumeroEtudiant() {
        return this.numeroEtudiant;
    }

    @Override
    public String getNomUtilisateur() {
        return this.nomUtilisateur;
    }

    @Override
    public String getPrenomUtilisateur() {
        return this.prenomUtilisateur;
    }

    @Override
    public String getNomEntiteUtilisateur() {
        return this.entiteUtilisateur.getNomEntite();
    }

    @Override
    public String getIdentifiantUtilisateur() {
        return this.identifiantUtilisateur;
    }

    @Override
    public IUtilisateur getUtilisateur() {
        return this;
    }

    @Override
    public IEntite getEntiteUtilisateur() {
        return this.entiteUtilisateur;
    }

    @Override
    public void setUtilisateur(IUtilisateur utilisateur) {
        this.nomUtilisateur = utilisateur.getNomUtilisateur();
        this.prenomUtilisateur = utilisateur.getPrenomUtilisateur();
        this.idUtilisateur = utilisateur.getIdUtilisateur();
        this.identifiantUtilisateur = utilisateur.getIdentifiantUtilisateur();
        this.numeroEtudiant = utilisateur.getNumeroEtudiant();
        this.entiteUtilisateur = new Entite(utilisateur.getIdEntiteUtilisateur(), utilisateur.getNomEntiteUtilisateur());
        this.motDePasseUtilisateur = ((Utilisateur)utilisateur).motDePasseUtilisateur;
    }

    @Override
    public void setEntiteUtilisateur(IEntite e) {
        this.entiteUtilisateur = new Entite(e.getIdEntite(), e.getNomEntite());
    }

    @Override
    public void setNumeroEtudiant(int numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    @Override
    public void setIdentifiantUtilisateur(String login) {
        this.identifiantUtilisateur = login;
    }

    @Override
    public void setMotDePasseUtilisateur(String motDePasse) {
        this.motDePasseUtilisateur = motDePasse;
    }

    @Override
    public boolean validerMotDePasseUtilisateur(String motDePasse) {
        return motDePasse.equals(this.motDePasseUtilisateur);
    }

    /**
     * Permet de savoir si un utilisateur est Admin
     *
     * @return vrai s'il est admin, faux sinon
     */
    @Override
    public boolean isAdmin() {
        return this.entiteUtilisateur != null && this.entiteUtilisateur.getNomEntite().equals(NOM_ENTITE_ADMIN);
    }
}
