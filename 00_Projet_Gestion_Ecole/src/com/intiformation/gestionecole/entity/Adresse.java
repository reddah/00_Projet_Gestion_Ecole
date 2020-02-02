package com.intiformation.gestionecole.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "adresse")
@Table(name = "adresses")
@NamedQuery(name = "Adresse_getAll", query = "SELECT adr FROM adresse adr")
public class Adresse implements Serializable {
	/* ____________ Props ____________ */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Adresse")
	private int idAdresse;
	private String rue;
	private int codePostal;
	private String ville;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ETUDIANT_ID", // Nom de la FK
			referencedColumnName = "identifiant" // Nom de la colonne de la classe associée
	) // gestion de la FK
	private Etudiant etudiant;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADMINISTRATEUR_ID", // Nom de la FK
			referencedColumnName = "identifiant" // Nom de la colonne de la classe associée
	) // gestion de la FK
	private Administrateur administrateur;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ENSEIGNANT_ID", // Nom de la FK
			referencedColumnName = "identifiant" // Nom de la colonne de la classe associée
	) // gestion de la FK
	private Enseignant enseignant;
	/* ____________ Ctor ____________ */

	public Adresse() {
		super();
	}

	public Adresse(int idAdresse) {
		super();
		this.idAdresse = idAdresse;
	}

	public Adresse(String rue, int codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Adresse(int idAdresse, String rue, int codePostal, String ville, Administrateur administrateur) {
		super();
		this.idAdresse = idAdresse;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.administrateur = administrateur;
	}

	public Adresse(int idAdresse, String rue, int codePostal, String ville, Enseignant enseignant) {
		super();
		this.idAdresse = idAdresse;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.enseignant = enseignant;
	}

	public Adresse(int idAdresse, String rue, int codePostal, String ville, Etudiant etudiant) {
		super();
		this.idAdresse = idAdresse;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.etudiant = etudiant;
	}
	
	

	public Adresse(Etudiant etudiant, Administrateur administrateur, Enseignant enseignant) {
		super();
		this.etudiant = etudiant;
		this.administrateur = administrateur;
		this.enseignant = enseignant;
	}

	public Adresse(Etudiant etudiant) {
		super();
		this.etudiant = etudiant;
	}
	public Adresse( Enseignant enseignant) {
		super();
		this.enseignant = enseignant;
	}
	public Adresse(Administrateur administrateur) {
		super();
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		return "Adresse [idAdresse=" + idAdresse + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville
				+ ", etudiant=" + etudiant + ", administrateur=" + administrateur + ", enseignant=" + enseignant + "]";
	}
	
	/* ____________ getters/setters ____________ */


	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

}// Fin de la classe Adresse
