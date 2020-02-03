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
import javax.persistence.Table;

@Entity(name = "matiere")
@Table(name = "matieres")
@NamedQuery(name = "Matiere_getAll", query = "SELECT mat FROM matiere mat")
public class Matiere implements Serializable  {
	/*===================Props =====================*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Matiere")
	private int idMatiere;
	private String libelle;

	
	/* -------------------------------------------------*/
	/* ----------------- Association -------------------*/
	/* -------------------------------------------------*/
	/**
	 * > type de relataion : Many Matiere To Many Enseignant
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="enseigne", 
			   joinColumns=@JoinColumn(name="MATIERE_ID"),
			   inverseJoinColumns=@JoinColumn(name="ENSEIGNANT_ID"))
	private List<Enseignant> listeEnseignant;
	
	
	/**
	 * > type de relataion : Many Matiere To Many Promotion 
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="enseigne", 
			   joinColumns=@JoinColumn(name="MATIERE_ID"),
			   inverseJoinColumns=@JoinColumn(name="PROMOTION_ID"))
	private List<Promotion> listPromotion;
	
	/*===================Ctors=====================*/
	public Matiere() {
		super();
	}
	
	
	public Matiere(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	public Matiere(int idMatiere, String libelle) {
		super();
		this.idMatiere = idMatiere;
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Matière [libelle=" + libelle + "]";
	}

	/*==================getters/setters===================*/
	
	public String getLibelle() {
		return libelle;
	}

	public int getIdMatiere() {
		return idMatiere;
	}


	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


} // fin de la classe Matiere
