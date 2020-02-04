package com.intiformation.gestionecole.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "etudiantCours")
@Table(name = "etudiantCours")
@NamedQuery(name = "EtudiantCours_getAll", query = "SELECT etuC FROM etudiantCours etuC")
public class EtudiantCours implements Serializable {
	/* ===================Props ===================== */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_EtudiantCours")
	private int idEtudiantCours;
	private boolean absence;
	private String motif;

	
	/* -------------------------------------------------*/
	/* ----------------- Association -------------------*/
	/* -------------------------------------------------*/
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COURS_ID", referencedColumnName = "id_Cours")
	private Cours cours;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ETUDIANT_ID", referencedColumnName = "identifiant")
	private Etudiant etudiant;

	
	/* ===================Ctors===================== */
	public EtudiantCours() {
		super();
	}

	public EtudiantCours(int idEtudiantCours, boolean absence, String motif, Cours cours, Etudiant etudiant) {
		super();
		this.idEtudiantCours = idEtudiantCours;
		this.absence = absence;
		this.motif = motif;
		this.cours = cours;
		this.etudiant = etudiant;
	}

	public EtudiantCours(boolean absence, String motif) {
		super();
		this.absence = absence;
		this.motif = motif;
	}

	@Override
	public String toString() {
		return "EtudiantCours [idEtudiantCours=" + idEtudiantCours + ", absence=" + absence + ", motif=" + motif
				+ ", cours=" + cours + ", etudiant=" + etudiant + "]";
	}



	/* ===================getters/setter===================== */
	public int getIdEtudiantCours() {
		return idEtudiantCours;
	}

	public void setIdEtudiantCours(int idEtudiantCours) {
		this.idEtudiantCours = idEtudiantCours;
	}

	public boolean isAbsence() {
		return absence;
	}

	public void setAbsence(boolean absence) {
		this.absence = absence;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	
} // fin de la classe EtudiantCours
