package com.intiformation.gestionecole.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "promotion")
@Table(name = "promotions")
@NamedQuery(name = "promotion_getAll", query = "SELECT promo FROM promotion promo")
public class Promotion implements Serializable {
	/* ===================Props ===================== */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Promotion")
	private int idPromotion;
	private String libelle;

	
	/* -------------------------------------------------*/
	/* ----------------- Association -------------------*/
	/* -------------------------------------------------*/
	/**
	 * > type de relataion : Many Promotion To Many Matiere
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="enseigne", 
			   joinColumns=@JoinColumn(name="PROMOTION_ID"),
			   inverseJoinColumns=@JoinColumn(name="MATIERE_ID"))
	private List<Matiere> listeMatiere;
	
	/**
	 * > type de relataion : Many Promotion To Many Enseignant
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="enseigne", 
			   joinColumns=@JoinColumn(name="PROMOTION_ID"),
			   inverseJoinColumns=@JoinColumn(name="ENSEIGNANT_ID"))
	private List<Enseignant> listeEnseignant;
	
	/**
	 * > type de relataion : Many Promotion To Many Etudiant
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns=@JoinColumn(name="PROMOTION_ID"), inverseJoinColumns=@JoinColumn(name="ETUDIANT_ID"))
	private List<Etudiant> listeEtudiant;
	
	/**
	 * > type de relataion : One Promotion To Many Cours
	 */
	@OneToMany(mappedBy = "promotion", targetEntity = Cours.class, cascade = CascadeType.ALL)
	private List<Cours> listCours;
	
	/* ===================Ctors===================== */
	public Promotion() {
		super();
	}

	public Promotion(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Promotion(int idPromotion, String libelle) {
		super();
		this.idPromotion = idPromotion;
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Promotion [libelle=" + libelle + "]";
	}
	/* ==================getters/setters=================== */

	public String getLibelle() {
		return libelle;
	}

	public int getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(int idPromotion) {
		this.idPromotion = idPromotion;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Matiere> getListeMatiere() {
		return listeMatiere;
	}

	public void setListeMatiere(List<Matiere> listeMatiere) {
		this.listeMatiere = listeMatiere;
	}

	public List<Enseignant> getListeEnseignant() {
		return listeEnseignant;
	}

	public void setListeEnseignant(List<Enseignant> listeEnseignant) {
		this.listeEnseignant = listeEnseignant;
	}

} // fin de la m�thode Promotion
