package com.intiformation.gestionecole.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "enseignant")
@Table(name="enseignants")
@NamedQuery(name="Enseignant_getAll", query="SELECT en FROM enseignant en")
public class Enseignant extends Personne implements Serializable {

	/* ____________ Props ____________ */

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
	


	/* ____________ getters/setters ____________ */

}// Fin de la classe Enseignant
