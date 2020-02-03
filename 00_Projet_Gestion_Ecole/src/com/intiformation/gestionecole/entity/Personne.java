package com.intiformation.gestionecole.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
//@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Personne implements Serializable {
	/* ____________ Props ____________ */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int identifiant;
	protected String motDePasse;
	protected String nom;
	protected String prenom;
	protected String email;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADRESSE_ID", // Nom de la FK
			referencedColumnName = "id_Adresse" // Nom de la colonne de la classe associée
	) // gestion de la FK
	private Adresse adresse;

	/* ____________ Ctor ____________ */

	public Personne() {
		super();
	}

	public Personne(String nom) {
		super();
		this.nom = nom;
	}

	public Personne(String motDePasse, String nom, String prenom, String email) {
		super();
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public Personne(int identifiant, String motDePasse, String nom, String prenom, String email) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	public Personne(String motDePasse, String nom, String prenom, String email, Adresse adresse) {
		super();
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
	}


	@Override
	public String toString() {
		return "Personne [identifiant=" + identifiant + ", motDePasse=" + motDePasse + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", adresse=" + adresse + "]";
	}

	/* ____________ getters/setters ____________ */

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}// Fin de la classe Personne