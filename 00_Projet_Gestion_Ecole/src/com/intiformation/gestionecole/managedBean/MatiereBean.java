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
	 * Cr�ation d'une nouvelle instance de MatiereBean
	 */
	public MatiereBean() {
		matiereDao = new MatiereDaoImpl();
	}

	/**
	 * R�cup de la liste des matieres � partir de la BDD
	 * 
	 * @return
	 */
	public Collection<Matiere> getMatieres() {
		matieres = matiereDao.getAll();
		return matieres;
	}

	/* _____________________________ METHODES __________________________________ */

	/**
	 * Permet d'initialiser un Matiere appel�e lors de l'ajout de la Matiere
	 * 
	 * @param event
	 */
	public void initMatiere(ActionEvent event) {
		setMatiere(new Matiere());
	}// Fin de la m�thode initMatiere

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
		// r�cup du param pass� au click du lien modifier
		UIParameter cp = (UIParameter) event.getComponent().findComponent("editMatiereID");
		int id = (int) cp.getValue();

		Matiere matiere = matiereDao.getById(id);

		// Affectation de la matiere � modifier � la variable matiere du MB
		setMatiere(matiere);
	}// Fin de la m�thode selectMatiere

	/**
	 * Suppression d'un matiere
	 * 
	 * @param event
	 */
	public void deleteMatiere(ActionEvent event) {
		// r�cup du param pass� au click du lien supprimer
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteMatiereID");

		// r�cup de la valeur du param (l'id de la matiere)
		int matiereId = (int) cp.getValue();

		// Suppression de la matiere dans la bdd via la dao
		// envoi d'un message vers la vue
		// R�cup du context

		FacesContext context = null;
		if (matiereDao.supprimer(matiereId)) {
			// => supp ok
			// --> envoie d'un message vers la vue avce la classe FacesMessage
			context = FacesContext.getCurrentInstance();
			// D�finition du message
			FacesMessage messageDelete = new FacesMessage("Infos :","La matiere � �t� supprimer avec succ�s");

			// envoie du message vers la vue
			// -> null : message global pour toute la page
			// /ou/
			// id du composant context.addMessage("id du composant", messageDelete);
			context.addMessage(null, messageDelete);
			// ->pour r�cup�rer le message dans la vue => <h:message></h:message>
		} else {
			// => supp not ok
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Suppression",
					"La suppression de la matiere � �chou�e"));
		} // Fin du if else
	}// Fin de la m�thode deleteMatiere

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
					// D�finition du message
					FacesMessage messageAdd = new FacesMessage("Infos :","La matiere � �t� ajout� avec succ�s");

					// envoie du message vers la vue
					// -> null : message global pour toute la page
					// /ou/
					// id du composant context.addMessage("id du composant", messageAdd);
					context.addMessage(null, messageAdd);
					// ->pour r�cup�rer le message dans la vue => <h:message></h:message>

					
				} else {
					// => ajout not ok
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Ajout",
							"L'ajout de la matiere � �chou�e"));
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
				// D�finition du message
				FacesMessage messageUpdate = new FacesMessage("Infos :","La matiere � �t� modifier avec succ�s");

				// envoie du message vers la vue
				// -> null : message global pour toute la page
				// /ou/
				// id du composant context.addMessage("id du composant", messageUpdate);
				context.addMessage(null, messageUpdate);
				// ->pour r�cup�rer le message dans la vue => <h:message></h:message>
			} else {
				// => modif not ok
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec modification",
						"La modification de la matiere � �chou�e"));
			} // Fin du if else
		} // Fin du if ajout Matiere

	}// Fin de la m�thode saveMatiere

	
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
