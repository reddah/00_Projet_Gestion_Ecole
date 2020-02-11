package com.intiformation.gestionecole.managedBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.gestionecole.dao.MatiereDaoImpl;
import com.intiformation.gestionecole.entity.Matiere;

@ManagedBean
public class MatiereBean implements Serializable {

	private static final long serialVersionUID = 1L;


	/* _____________________________ CHAMPS __________________________________ */
	private Collection<Matiere> matieres;
	private Matiere matiere = new Matiere();

	// DAO
	MatiereDaoImpl matiereDao;

	/* _____________________________ CTORS __________________________________ */
	/**
	 * Création d'une nouvelle instance de MatiereBean
	 */
	public MatiereBean() {
		matiereDao = new MatiereDaoImpl();
	}

	/**
	 * Récup de la liste des matieres à partir de la BDD
	 * 
	 * @return
	 */
	public Collection<Matiere> getMatieres() {
		matieres = matiereDao.getAll();
		return matieres;
	}

	/* _____________________________ METHODES __________________________________ */

	/**
	 * Permet d'initialiser un Matiere appelée lors de l'ajout de la Matiere
	 * 
	 * @param event
	 */
	public void initMatiere(ActionEvent event) {
		setMatiere(new Matiere());
	}// Fin de la méthode initMatiere

	public void ajouterNouveauMatiere(ActionEvent event) {
		matiereDao.ajouter(matiere);
	}

	public void modifierMatiere(ActionEvent event) {
		
		matiereDao.modifier(matiere.getIdMatiere(), matiere);
	}
	/**
	 * 
	 * @param event
	 */
	public void selectMatiere(ActionEvent event) {
		// récup du param passé au click du lien modifier
		UIParameter cp = (UIParameter) event.getComponent().findComponent("editMatiereID");
		int id = (int) cp.getValue();

		Matiere matiere = matiereDao.getById(id);

		// Affectation de la matiere à modifier à la variable matiere du MB
		setMatiere(matiere);
	}// Fin de la méthode selectMatiere

	/**
	 * Suppression d'un matiere
	 * 
	 * @param event
	 */
	public void deleteMatiere(ActionEvent event) {
		// récup du param passé au click du lien supprimer
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteMatiereID");

		// récup de la valeur du param (l'id de la matiere)
		int matiereId = (int) cp.getValue();

		// Suppression de la matiere dans la bdd via la dao
		// envoi d'un message vers la vue
		// Récup du context

		FacesContext context = null;
		if (matiereDao.supprimer(matiereId)) {
			// => supp ok
			// --> envoie d'un message vers la vue avce la classe FacesMessage
			context = FacesContext.getCurrentInstance();
			// Définition du message
			FacesMessage messageDelete = new FacesMessage("Infos :","La matiere à été supprimer avec succès");

			// envoie du message vers la vue
			// -> null : message global pour toute la page
			// /ou/
			// id du composant context.addMessage("id du composant", messageDelete);
			context.addMessage(null, messageDelete);
			// ->pour récupérer le message dans la vue => <h:message></h:message>
		} else {
			// => supp not ok
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Suppression",
					"La suppression de la matiere à échouée"));
		} // Fin du if else
	}// Fin de la méthode deleteMatiere

	public void saveMatiere() {
		if (matiere.getIdMatiere() == 0) {
				/* ************************************************************* */
				/* ***************** Cas : ajout d'un matiere ***************** */
				/* ************************************************************* */

				// ajout de la Matiere dans la bdd

				FacesContext context = null;
				if (matiereDao.ajouter(matiere)) {
					// => ajout ok
					// --> envoie d'un message vers la vue avce la classe FacesMessage
					context = FacesContext.getCurrentInstance();
					// Définition du message
					FacesMessage messageAdd = new FacesMessage("Infos :","La matiere à été ajouté avec succès");

					// envoie du message vers la vue
					// -> null : message global pour toute la page
					// /ou/
					// id du composant context.addMessage("id du composant", messageAdd);
					context.addMessage(null, messageAdd);
					// ->pour récupérer le message dans la vue => <h:message></h:message>

					
				} else {
					// => ajout not ok
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Ajout",
							"L'ajout de la matiere à échouée"));
				} // Fin du if else
		

		} // Fin du if ajout Matiere

		/* ************************************************************* */
		/* ************* Cas : Modification d'un matiere ************** */
		/* ************************************************************* */

		if (matiere.getIdMatiere() != 0) {
			
			FacesContext context = null;
			if (matiereDao.modifier(matiere.getIdMatiere(), matiere)) {
				// => modif  ok
				// --> envoie d'un message vers la vue avec la classe FacesMessage
				context = FacesContext.getCurrentInstance();
				// Définition du message
				FacesMessage messageUpdate = new FacesMessage("Infos :","La matiere à été modifier avec succès");

				// envoie du message vers la vue
				// -> null : message global pour toute la page
				// /ou/
				// id du composant context.addMessage("id du composant", messageUpdate);
				context.addMessage(null, messageUpdate);
				// ->pour récupérer le message dans la vue => <h:message></h:message>
			} else {
				// => modif not ok
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec modification",
						"La modification de la matiere à échouée"));
			} // Fin du if else
		} // Fin du if ajout Matiere

	}// Fin de la méthode saveMatiere

	
	/* _______________________ GETTERS/SETTERS ________________________________ */


	public void setMatieres(Collection<Matiere> matieres) {
		this.matieres = matieres;
	}

	public MatiereDaoImpl getMatiereDao() {
		return matiereDao;
	}

	public void setMatiereDao(MatiereDaoImpl matiereDao) {
		this.matiereDao = matiereDao;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}


}// Fin de la classe MatiereBean
