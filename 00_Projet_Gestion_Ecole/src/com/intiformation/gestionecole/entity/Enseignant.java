package com.intiformation.gestionecole.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "enseignant")
@Table(name="enseignants")
@NamedQuery(name="Enseignant_getAll", query="SELECT en FROM enseignant en")
@Inheritance(strategy=InheritanceType.JOINED)
public class Enseignant extends Personne implements Serializable {

	/* ____________ Props ____________ */

	
	/* -------------------------------------------------*/
	/* ----------------- Association -------------------*/
	/* -------------------------------------------------*/
	/**
	 * > type de relataion : Many Enseignant To Many Matiere
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="enseigne", 
			   joinColumns=@JoinColumn(name="ENSEIGNANT_ID"),
			   inverseJoinColumns=@JoinColumn(name="MATIERE_ID"))
	private List<Matiere> listeMatiere;
	
	/**
	 * > type de relataion : Many Enseignant To Many Promotion 
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="enseigne", 
			   joinColumns=@JoinColumn(name="ENSEIGNANT_ID"),
			   inverseJoinColumns=@JoinColumn(name="PROMOTION_ID"))
	private List<Promotion> listPromotion;
	/* ____________ Ctor ____________ */

	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enseignant(int identifiant, String motDePasse, String nom, String prenom, String email) {
		super(identifiant, motDePasse, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}

	public Enseignant(String motDePasse, String nom, String prenom, String email) {
		super(motDePasse, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}

	public Enseignant(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}

	public Enseignant(String motDePasse, String nom, String prenom, String email, Adresse adresse) {
		super(motDePasse, nom, prenom, email, adresse);
		// TODO Auto-generated constructor stub
	}


	
	

	/* ____________ getters/setters ____________ */

}// Fin de la classe Enseignant
