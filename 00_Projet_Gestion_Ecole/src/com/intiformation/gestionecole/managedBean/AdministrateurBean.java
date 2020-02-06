package com.intiformation.gestionecole.managedBean;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import com.intiformation.gestionecole.dao.AdministrateurDaoImpl;
import com.intiformation.gestionecole.entity.Administrateur;

public class AdministrateurBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/* _____________________________ CHAMPS __________________________________ */
	private Collection<Administrateur> administrateurs;
	private Administrateur administrateur;

	// DAO
	AdministrateurDaoImpl administrateurDao;

	/* _____________________________ CTORS __________________________________ */
	/**
	 * Création d'une nouvelle instance de AdministrateurBean
	 */
	public AdministrateurBean() {
		administrateurDao = new AdministrateurDaoImpl();
	}

	/**
	 * Récup de la liste des administrateurs à partir de la BDD
	 * 
	 * @return
	 */
	public Collection<Administrateur> getAdministrateur() {
		administrateurs = administrateurDao.getAll();
		return administrateurs;
	}

	/* _____________________________ METHODES __________________________________ */

	/**
	 * Permet d'initialiser un Administrateur appelée lors de l'ajout de
	 * l'Administrateur
	 * 
	 * @param event
	 */
	public void initAdministrateur(ActionEvent event) {
		setAdministrateur(new Administrateur());
	}// Fin de la méthode initAdministrateur

	/**
	 * 
	 * @param event
	 */
	public void selectAdministrateur(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent().findComponent("editID");
		int id = (int) cp.getValue();

		Administrateur administrateur = administrateurDao.getById(id);

		setAdministrateur(administrateur);
	}// Fin de la méthode selectAdministrateur

	/**
	 * Suppression d'un administrateur
	 * 
	 * @param event
	 */
	public void deleteAdministrateur(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		int id = (int) cp.getValue();

		administrateurDao.supprimer(id);
	}// Fin de la méthode deleteAdministrateur

	public void saveAdministrateur() {
		/* ************************************************************* */
		/* ************** Cas : ajout d'un administrateur ************** */
		/* ************************************************************* */
		if (administrateur.getIdentifiant() == 0) {

			administrateurDao.ajouter(administrateur);

		} // Fin du if ajout Admin

		/* ************************************************************* */
		/* ********** Cas : Modification d'un administrateur *********** */
		/* ************************************************************* */

		if (administrateur.getIdentifiant() != 0) {

			administrateurDao.modifier(administrateur.getIdentifiant(), administrateur);

		} // Fin du if ajout Admin

	}// Fin de la méthode saveAdministrateur

	/* _______________________ GETTERS/SETTERS ________________________________ */

	public Collection<Administrateur> getAdministrateurs() {
		return administrateurs;
	}

	public void setAdministrateurs(Collection<Administrateur> administrateurs) {
		this.administrateurs = administrateurs;
	}

	public AdministrateurDaoImpl getAdministrateurDao() {
		return administrateurDao;
	}

	public void setAdministrateurDao(AdministrateurDaoImpl administrateurDao) {
		this.administrateurDao = administrateurDao;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

}// Fin de la classe AdministrateurBean
