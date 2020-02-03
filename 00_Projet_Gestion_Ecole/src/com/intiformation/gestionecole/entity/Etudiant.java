package com.intiformation.gestionecole.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "etudiant")
@Table(name = "etudiants")
@NamedQuery(name = "Etudiant_getAll", query = "SELECT e FROM etudiant e")
public class Etudiant extends Personne implements Serializable {

	/* ____________ Props ____________ */

	private String urlPhotoEtudiant;
	// Les propriétés temporelles de type Date / Calendar
	@Temporal(TemporalType.DATE)
	@Column(name = "date_de_naissance")
	private Date dateNaissance;
	
	
	@OneToMany(mappedBy = "etudiant", targetEntity = EtudiantCours.class, cascade = CascadeType.ALL)
	private List<EtudiantCours> listeEtudiantCours;
	
	
	/* ____________ Ctor ____________ */

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(int identifiant, String motDePasse, String nom, String prenom, String email) {
		super(identifiant, motDePasse, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String motDePasse, String nom, String prenom, String email, String urlPhotoEtudiant,  Adresse adresse) {
		super(motDePasse, nom, prenom, email, adresse);
		this.urlPhotoEtudiant = urlPhotoEtudiant;
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String motDePasse, String nom, String prenom, String email,  String urlPhotoEtudiant, Adresse adresse, Date dateNaissance) {
		super(motDePasse, nom, prenom, email, adresse);
		this.urlPhotoEtudiant = urlPhotoEtudiant;
		this.dateNaissance = dateNaissance;
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String motDePasse, String nom, String prenom, String email, String urlPhotoEtudiant) {
		super(motDePasse, nom, prenom, email);
		this.urlPhotoEtudiant = urlPhotoEtudiant;
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}

	public Etudiant(Date dateNaissance) {
		super();
		this.dateNaissance = dateNaissance;
	}

//	public Etudiant(EtudiantCours etudiantCours) {
//		super();
//		this.etudiantCours = etudiantCours;
//	}

	/* ____________ getters/setters ____________ */

	

	public String getUrlPhotoEtudiant() {
		return urlPhotoEtudiant;
	}

	public void setUrlPhotoEtudiant(String urlPhotoEtudiant) {
		this.urlPhotoEtudiant = urlPhotoEtudiant;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

//	public EtudiantCours getEtudiantCours() {
//		return etudiantCours;
//	}
//
//	public void setEtudiantCours(EtudiantCours etudiantCours) {
//		this.etudiantCours = etudiantCours;
//	}

}// Fin de la classe Etudiant
