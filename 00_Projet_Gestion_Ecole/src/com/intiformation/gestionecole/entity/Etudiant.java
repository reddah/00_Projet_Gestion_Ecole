package com.intiformation.gestionecole.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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

	/* ------------------------------------------------- */
	/* ----------------- Association ------------------- */
	/* ------------------------------------------------- */
	/**
	 * > type de la relation : One Etudiant To Many EtudiantCours
	 */

	@OneToMany(mappedBy = "etudiant", targetEntity = EtudiantCours.class, cascade = CascadeType.ALL)
	private List<EtudiantCours> listeEtudiantCours;

	/**
	 * > type de relataion : Many Etudiant To Many Promotion
	 */
	@ManyToMany(mappedBy = "listeEtudiant", targetEntity = Promotion.class, cascade = CascadeType.ALL)
	private List<Promotion> listePromotion;

	/* ____________ Ctor ____________ */

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(int identifiant, String motDePasse, String nom, String prenom, String email) {
		super(identifiant, motDePasse, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}
	
	public Etudiant(String motDePasse, String nom, String prenom, String email) {
		super(motDePasse, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String motDePasse, String nom, String prenom, String email, String urlPhotoEtudiant,
			Adresse adresse) {
		super(motDePasse, nom, prenom, email, adresse);
		this.urlPhotoEtudiant = urlPhotoEtudiant;
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String motDePasse, String nom, String prenom, String email, String urlPhotoEtudiant,
			Adresse adresse, Date dateNaissance) {
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


	public List<EtudiantCours> getListeEtudiantCours() {
		return listeEtudiantCours;
	}

	public void setListeEtudiantCours(List<EtudiantCours> listeEtudiantCours) {
		this.listeEtudiantCours = listeEtudiantCours;
	}

	public List<Promotion> getListePromotion() {
		return listePromotion;
	}

	public void setListePromotion(List<Promotion> listePromotion) {
		this.listePromotion = listePromotion;
	}
	
	
	
}// Fin de la classe Etudiant
