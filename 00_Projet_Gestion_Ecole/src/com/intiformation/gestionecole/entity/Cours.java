package com.intiformation.gestionecole.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "cours")
@Table(name = "cours")
@NamedQuery(name = "Cours_getAll", query = "SELECT c FROM cours c")
public class Cours implements Serializable {

	/* ===================Props ===================== */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Cours")
	private int idCours;
	private String libelle;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String duree;
	private String description;

	@OneToMany(mappedBy = "cours", targetEntity = EtudiantCours.class, cascade = CascadeType.ALL)
	private List<EtudiantCours> listeEtudiantCours;
	
	
	/**
	 * > type de relataion : Many Cours To One Matiere 
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MATIERE_ID", referencedColumnName = "id_Matiere")
	private Matiere matiere;

	
	/**
	 * > type de relataion : Many Cours To One Matiere 
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROMOTION_ID", referencedColumnName = "id_Promotion")
	private Promotion promotion;
	/* ===================Ctors===================== */
	public Cours() {
		super();
	}

	public Cours(String libelle, String duree, String description) {
		super();
		this.libelle = libelle;
		this.duree = duree;
		this.description = description;
	}

	public Cours(int idCours, String libelle, String duree, String description) {
		super();
		this.idCours = idCours;
		this.libelle = libelle;
		this.duree = duree;
		this.description = description;
	}

	public Cours(Date date) {
		super();
		this.date = date;
	}

	@Override
	public String toString() {
		return "Cours [libelle=" + libelle + ", date=" + date + ", duree=" + duree + ", description=" + description
				+ "]";
	}

	/* ==================getters/setters=================== */

	public int getIdCours() {
		return idCours;
	}

	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EtudiantCours> getListeEtudiantCours() {
		return listeEtudiantCours;
	}

	public void setListeEtudiantCours(List<EtudiantCours> listeEtudiantCours) {
		this.listeEtudiantCours = listeEtudiantCours;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}



}// fin de la classe Cours
