package com.intiformation.gestionecole.managedBean;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import com.intiformation.gestionecole.dao.EnseignantDaoImpl;
import com.intiformation.gestionecole.entity.Enseignant;

public class EnseignantBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/* _____________________________ CHAMPS __________________________________ */
	private Collection<Enseignant> enseignants;
	private Enseignant enseignant;

	// DAO
	EnseignantDaoImpl enseignantDao;

	/* _____________________________ CTORS __________________________________ */
	/**
	 * Création d'une nouvelle instance de EnseignantBean
	 */
	public EnseignantBean() {
		enseignantDao = new EnseignantDaoImpl();
	}

	/**
	 * Récup de la liste des enseignants à partir de la BDD
	 * 
	 * @return
	 */
	public Collection<Enseignant> getEnseignant() {
		enseignants = enseignantDao.getAll();
		return enseignants;
	}

	/* _____________________________ METHODES __________________________________ */

	/**
	 * Permet d'initialiser un Enseignant appelée lors de l'ajout de
	 * l'Enseignant
	 * 
	 * @param event
	 */
	public void initEnseignant(ActionEvent event) {
		setEnseignant(new Enseignant());
	}// Fin de la méthode initEnseignant

	/**
	 * 
	 * @param event
	 */
	public void selectEnseignant(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent().findComponent("editID");
		int id = (int) cp.getValue();

		Enseignant enseignant = enseignantDao.getById(id);

		setEnseignant(enseignant);
	}// Fin de la méthode selectEnseignant

	/**
	 * Suppression d'un enseignant
	 * 
	 * @param event
	 */
	public void deleteEnseignant(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		int id = (int) cp.getValue();

		enseignantDao.supprimer(id);
	}// Fin de la méthode deleteEnseignant

	public void saveEnseignant() {
		/* ************************************************************* */
		/* **************** Cas : ajout d'un enseignant **************** */
		/* ************************************************************* */
		if (enseignant.getIdentifiant() == 0) {

			enseignantDao.ajouter(enseignant);

		} // Fin du if ajout Enseignant

		/* ************************************************************* */
		/* ************ Cas : Modification d'un enseignant ************* */
		/* ************************************************************* */

		if (enseignant.getIdentifiant() != 0) {

			enseignantDao.modifier(enseignant.getIdentifiant(), enseignant);

		} // Fin du if ajout Enseignant

	}// Fin de la méthode saveEnseignant

	/* _______________________ GETTERS/SETTERS ________________________________ */

	public Collection<Enseignant> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(Collection<Enseignant> enseignants) {
		this.enseignants = enseignants;
	}

	public EnseignantDaoImpl getEnseignantDao() {
		return enseignantDao;
	}

	public void setEnseignantDao(EnseignantDaoImpl enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

}// Fin de la classe EnseignantBean
