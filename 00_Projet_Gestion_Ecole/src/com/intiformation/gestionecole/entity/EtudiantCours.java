package com.intiformation.gestionecole.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "etudiantCours")
@Table(name = "etudiantCours")
@NamedQuery(name = "EtudiantCours_getAll", query = "SELECT etuC FROM etudiantCours etuC")
public class EtudiantCours {
	/* ===================Props ===================== */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_EtudiantCours")
	private int idEtudiantCours;
	private boolean absence;
	private String motif;
	private String cours;
	private String etudiant;

	// Amélioration pour avoir 2 tables
	@OneToMany(mappedBy="etudiantCours",
			   targetEntity=Cours.class, 
			   cascade=CascadeType.ALL)
	private List<Cours> listeCours;

	// Amélioration pour avoir 2 tables
	@OneToMany(mappedBy="etudiantCours",
			   targetEntity=Etudiant.class, 
			   cascade=CascadeType.ALL)
	private List<Etudiant> listeEtudiant;

	/* ===================Ctors===================== */
	public EtudiantCours() {
		super();
	}

	public EtudiantCours(boolean absence, String motif, String cours, String etudiant) {
		super();
		this.absence = absence;
		this.motif = motif;
		this.cours = cours;
		this.etudiant = etudiant;
	}

	public EtudiantCours(int idEtudiantCours, boolean absence, String motif, String cours, String etudiant) {
		super();
		this.idEtudiantCours = idEtudiantCours;
		this.absence = absence;
		this.motif = motif;
		this.cours = cours;
		this.etudiant = etudiant;
	}
	
	

	@Override
	public String toString() {
		return "EtudiantCours [absence=" + absence + ", motif=" + motif + ", cours=" + cours + ", etudiant=" + etudiant
				+ "]";
	}
	/* ==================getters/setters=================== */

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

	public String getCours() {
		return cours;
	}

	public void setCours(String cours) {
		this.cours = cours;
	}

	public String getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(String etudiant) {
		this.etudiant = etudiant;
	}

	public List<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	public List<Etudiant> getListeEtudiant() {
		return listeEtudiant;
	}

	public void setListeEtudiant(List<Etudiant> listeEtudiant) {
		this.listeEtudiant = listeEtudiant;
	}

	
} // fin de la classe EtudiantCours
