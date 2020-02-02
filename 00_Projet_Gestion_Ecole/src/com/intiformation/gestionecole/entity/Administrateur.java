package com.intiformation.gestionecole.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "administrateur")
@Table(name = "administrateurs")
@NamedQuery(name = "Administrateur_getAll", query = "SELECT ad FROM administrateur ad")
public class Administrateur extends Personne implements Serializable {

	/* ____________ Props ____________ */

	/* ____________ Ctor ____________ */

	public Administrateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrateur(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}

	public Administrateur(int identifiant, String motDePasse, String nom, String prenom, String email) {
		super(identifiant, motDePasse, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}

	public Administrateur(String motDePasse, String nom, String prenom, String email) {
		super(motDePasse, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}


	/* ____________ getters/setters ____________ */

}// Fin de la classe Administrateur
