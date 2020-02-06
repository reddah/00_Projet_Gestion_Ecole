package com.intiformation.gestionecole.managedBean;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import com.intiformation.gestionecole.dao.PersonneDaoImpl;
import com.intiformation.gestionecole.entity.Administrateur;
import com.intiformation.gestionecole.entity.Enseignant;
import com.intiformation.gestionecole.entity.Etudiant;
import com.intiformation.gestionecole.entity.Personne;

public class PersonneBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/* _____________________________ CHAMPS __________________________________ */
	private Collection<Personne> personnes;
	private Personne personne;

	// DAO
	PersonneDaoImpl personneDao;

	/* _____________________________ CTORS __________________________________ */
	/**
	 * Création d'une nouvelle instance de PersonneBean
	 */
	public PersonneBean() {
		personneDao = new PersonneDaoImpl();
	}

	/**
	 * Récup de la liste des personnes à partir de la BDD
	 * 
	 * @return
	 */
	public Collection<Personne> getPersonne() {
		personnes = personneDao.getAll();
		return personnes;
	}

	/* _____________________________ METHODES __________________________________ */

	/**
	 * Permet d'initialiser un Administrateur appelée lors de l'ajout de
	 * l'Administrateur
	 * @param event
	 */
	public void initAdmin(ActionEvent event) {
		setPersonne(new Administrateur());
	}// Fin de la méthode initAdmin

	/**
	/**
	 * Permet d'initialiser un Etudiant appelée lors de l'ajout de
	 * l'Etudiant
	 * @param event
	 */
	public void initEtudiant(ActionEvent event) {
		setPersonne(new Etudiant());
	}// Fin de la méthode initEtudiant
	
	/**
	 * Permet d'initialiser un Enseignant appelée lors de l'ajout de
	 * l'Enseignant
	 * @param event
	 */
	public void initEnseignant(ActionEvent event) {
		setPersonne(new Enseignant());
	}// Fin de la méthode initEnseignant
	
	
	/**
	 * 
	 * @param event
	 */
	public void selectPersonne(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent().findComponent("editID");
		int id = (int) cp.getValue();

		Personne personne = personneDao.getById(id);

		setPersonne(personne);
	}// Fin de la méthode selectPersonne

	/**
	 * Suppression d'un personne
	 * 
	 * @param event
	 */
	public void deletePersonne(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		int id = (int) cp.getValue();

		personneDao.supprimer(id);
	}// Fin de la méthode deletePersonne

	public void savePersonne() {
		/* ************************************************************* */
		/* ************** Cas : ajout d'un personne ************** */
		/* ************************************************************* */
		if (personne.getIdentifiant() == 0) {

			personneDao.ajouter(personne);

		} // Fin du if ajout Admin

		/* ************************************************************* */
		/* ********** Cas : Modification d'un personne *********** */
		/* ************************************************************* */

		if (personne.getIdentifiant() != 0) {

			personneDao.modifier(personne.getIdentifiant(), personne);

		} // Fin du if ajout Admin

	}// Fin de la méthode savePersonne

	/* _______________________ GETTERS/SETTERS ________________________________ */

	public Collection<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(Collection<Personne> personnes) {
		this.personnes = personnes;
	}

	public PersonneDaoImpl getPersonneDao() {
		return personneDao;
	}

	public void setPersonneDao(PersonneDaoImpl personneDao) {
		this.personneDao = personneDao;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

}// Fin de la classe PersonneBean
