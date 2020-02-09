package com.intiformation.gestionecole.managedBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.gestionecole.dao.EnseignantDaoImpl;
import com.intiformation.gestionecole.entity.Enseignant;

@ManagedBean
public class EnseignantBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/* _____________________________ CHAMPS __________________________________ */
	private Collection<Enseignant> enseignants;
	private Enseignant enseignant = new Enseignant();
	private List<Enseignant> enseignants2;
	private List<Enseignant> listeEnseignants;

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
	public Collection<Enseignant> getEnseignants() {
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

	public void ajouterNouveauEnseignant(ActionEvent event) {
		enseignantDao.ajouter(enseignant);
	}

	public void modifierEnseignant(ActionEvent event) {

		enseignantDao.modifier(enseignant.getIdentifiant(), enseignant);
	}

	/**
	 * 
	 * @param event
	 */
	public void selectEnseignant(ActionEvent event) {
		// récup du param passé au click du lien modifier
		UIParameter cp = (UIParameter) event.getComponent().findComponent("editEnseignantID");
		int id = (int) cp.getValue();

		Enseignant enseignant = enseignantDao.getById(id);

		// Affectation de l'enseignant à modifier à la variable enseignant du MB
		setEnseignant(enseignant);
	}// Fin de la méthode selectEnseignant

	/**
	 * Suppression d'un enseignant
	 * 
	 * @param event
	 */
	public void deleteEnseignant(ActionEvent event) {
		// récup du param passé au click du lien supprimer
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteEnseignantID");

		// récup de la valeur du param (l'id de l'enseignant)
		int enseignantId = (int) cp.getValue();

		// Suppression de l'enseignant dans la bdd via la dao
		// envoi d'un message vers la vue
		// Récup du context

		FacesContext context = null;
		if (enseignantDao.supprimer(enseignantId)) {
			// => supp ok
			// --> envoie d'un message vers la vue avce la classe FacesMessage
			context = FacesContext.getCurrentInstance();
			// Définition du message
			FacesMessage messageDelete = new FacesMessage("Infos :", "L'enseignant à été supprimer avec succès");

			// envoie du message vers la vue
			// -> null : message global pour toute la page
			// /ou/
			// id du composant context.addMessage("id du composant", messageDelete);
			context.addMessage(null, messageDelete);
			// ->pour récupérer le message dans la vue => <h:message></h:message>
		} else {
			// => supp not ok
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Suppression",
					"La suppression de l'enseignant à échouée"));
		} // Fin du if else
	}// Fin de la méthode deleteEnseignant

	public void saveEnseignant() {
		if (enseignant.getIdentifiant() == 0) {
			/* ************************************************************* */
			/* ***************** Cas : ajout d'un enseignant ***************** */
			/* ************************************************************* */

			// ajout de l'Enseignant dans la bdd

			FacesContext context = null;
			if (enseignantDao.ajouter(enseignant)) {
				// => ajout ok
				// --> envoie d'un message vers la vue avce la classe FacesMessage
				context = FacesContext.getCurrentInstance();
				// Définition du message
				FacesMessage messageAdd = new FacesMessage("Infos :", "L'enseignant à été ajouté avec succès");

				// envoie du message vers la vue
				// -> null : message global pour toute la page
				// /ou/
				// id du composant context.addMessage("id du composant", messageAdd);
				context.addMessage(null, messageAdd);
				// ->pour récupérer le message dans la vue => <h:message></h:message>

			} else {
				// => ajout not ok
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Ajout",
						"L'ajout de l'enseignant à échouée"));
			} // Fin du if else

		} // Fin du if ajout Enseignant

		/* ************************************************************* */
		/* ************* Cas : Modification d'un enseignant ************** */
		/* ************************************************************* */

		if (enseignant.getIdentifiant() != 0) {

			FacesContext context = null;
			if (enseignantDao.modifier(enseignant.getIdentifiant(), enseignant)) {
				// => modif ok
				// --> envoie d'un message vers la vue avec la classe FacesMessage
				context = FacesContext.getCurrentInstance();
				// Définition du message
				FacesMessage messageUpdate = new FacesMessage("Infos :", "L'enseignant à été modifier avec succès");

				// envoie du message vers la vue
				// -> null : message global pour toute la page
				// /ou/
				// id du composant context.addMessage("id du composant", messageUpdate);
				context.addMessage(null, messageUpdate);
				// ->pour récupérer le message dans la vue => <h:message></h:message>
			} else {
				// => modif not ok
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec modification",
						"La modification de l'enseignant à échouée"));
			} // Fin du if else
		} // Fin du if ajout Enseignant

	}// Fin de la méthode saveEnseignant

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		int filterInt = getInteger(filterText);

		Enseignant enseignant = (Enseignant) value;
		return enseignant.getEmail().toLowerCase().contains(filterText)
				|| enseignant.getNom().toLowerCase().contains(filterText)
				|| enseignant.getPrenom().toLowerCase().contains(filterText)
				|| enseignant.getIdentifiant() < filterInt;
	}

	private int getInteger(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	/* _______________________ GETTERS/SETTERS ________________________________ */

	public void setEnseignants(Collection<Enseignant> enseignants) {
		this.enseignants = enseignants;
	}

	public EnseignantDaoImpl getEnseignantDao() {
		return enseignantDao;
	}

	public void setEnseignantDao(EnseignantDaoImpl enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public List<Enseignant> getListeEnseignants() {
		return listeEnseignants;
	}

	public void setListeEnseignants(List<Enseignant> listeEnseignants) {
		this.listeEnseignants = listeEnseignants;
	}

	public List<Enseignant> getEnseignants2() {
		return enseignants2;
	}

	public void setEnseignants2(List<Enseignant> enseignants2) {
		this.enseignants2 = enseignants2;
	}

}// Fin de la classe EnseignantBean
