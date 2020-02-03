package com.intiformation.gestionecole.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
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


//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "ETUDIANT_ID", // Nom de la FK
//			referencedColumnName = "identifiant" // Nom de la colonne de la classe associée
//	) // gestion de la FK
//	private Etudiant etudiant;
//	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "ADMINISTRATEUR_ID", // Nom de la FK
//			referencedColumnName = "identifiant" // Nom de la colonne de la classe associée
//	) // gestion de la FK
//	private Administrateur administrateur;
//
//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "ENSEIGNANT_ID", // Nom de la FK
//			referencedColumnName = "identifiant" // Nom de la colonne de la classe associée
//	) // gestion de la FK
//	private Enseignant enseignant;
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


	@Override
	public String toString() {
		return "Adresse [idAdresse=" + idAdresse + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville
				+ "]";
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


}// Fin de la classe Adresse
