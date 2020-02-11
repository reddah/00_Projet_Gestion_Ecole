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

import com.intiformation.gestionecole.dao.AdministrateurDaoImpl;
import com.intiformation.gestionecole.entity.Administrateur;

@ManagedBean
public class AdministrateurBean implements Serializable {

	private static final long serialVersionUID = 1L;


	/* _____________________________ CHAMPS __________________________________ */
	private Collection<Administrateur> administrateurs;
	private Administrateur administrateur = new Administrateur();
	private List<Administrateur> administrateurs2;
	private List<Administrateur> listeAdministrateurs;

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
	public Collection<Administrateur> getAdministrateurs() {
		administrateurs = administrateurDao.getAll();
		return administrateurs;
	}

	/* _____________________________ METHODES __________________________________ */

	/**
	 * Permet d'initialiser un Administrateur appelée lors de l'ajout de l'Administrateur
	 * 
	 * @param event
	 */
	public void initAdministrateur(ActionEvent event) {
		setAdministrateur(new Administrateur());
	}// Fin de la méthode initAdministrateur

	public void ajouterNouveauAdministrateur(ActionEvent event) {
		administrateurDao.ajouter(administrateur);
	}

	public void modifierAdministrateur(ActionEvent event) {
		
		administrateurDao.modifier(administrateur.getIdentifiant(), administrateur);
	}
	/**
	 * 
	 * @param event
	 */
	public void selectAdministrateur(ActionEvent event) {
		// récup du param passé au click du lien modifier
		UIParameter cp = (UIParameter) event.getComponent().findComponent("editAdministrateurID");
		int id = (int) cp.getValue();

		Administrateur administrateur = administrateurDao.getById(id);

		// Affectation de l'administrateur à modifier à la variable administrateur du MB
		setAdministrateur(administrateur);
	}// Fin de la méthode selectAdministrateur

	/**
	 * Suppression d'un administrateur
	 * 
	 * @param event
	 */
	public void deleteAdministrateur(ActionEvent event) {
		// récup du param passé au click du lien supprimer
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteAdministrateurID");

		// récup de la valeur du param (l'id de l'administrateur)
		int administrateurId = (int) cp.getValue();

		// Suppression de l'administrateur dans la bdd via la dao
		// envoi d'un message vers la vue
		// Récup du context

		FacesContext context = null;
		if (administrateurDao.supprimer(administrateurId)) {
			// => supp ok
			// --> envoie d'un message vers la vue avce la classe FacesMessage
			context = FacesContext.getCurrentInstance();
			// Définition du message
			FacesMessage messageDelete = new FacesMessage("Infos :","L'administrateur à été supprimer avec succès");

			// envoie du message vers la vue
			// -> null : message global pour toute la page
			// /ou/
			// id du composant context.addMessage("id du composant", messageDelete);
			context.addMessage(null, messageDelete);
			// ->pour récupérer le message dans la vue => <h:message></h:message>
		} else {
			// => supp not ok
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Suppression",
					"La suppression de l'administrateur à échouée"));
		} // Fin du if else
	}// Fin de la méthode deleteAdministrateur

	public void saveAdministrateur() {
		if (administrateur.getIdentifiant() == 0) {
				/* ************************************************************* */
				/* ***************** Cas : ajout d'un administrateur ***************** */
				/* ************************************************************* */

				// ajout de l'Administrateur dans la bdd

				FacesContext context = null;
				if (administrateurDao.ajouter(administrateur)) {
					// => ajout ok
					// --> envoie d'un message vers la vue avce la classe FacesMessage
					context = FacesContext.getCurrentInstance();
					// Définition du message
					FacesMessage messageAdd = new FacesMessage("Infos :","L'administrateur à été ajouté avec succès");

					// envoie du message vers la vue
					// -> null : message global pour toute la page
					// /ou/
					// id du composant context.addMessage("id du composant", messageAdd);
					context.addMessage(null, messageAdd);
					// ->pour récupérer le message dans la vue => <h:message></h:message>

					
				} else {
					// => ajout not ok
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Ajout",
							"L'ajout de l'administrateur à échouée"));
				} // Fin du if else
		

		} // Fin du if ajout Administrateur

		/* ************************************************************* */
		/* ************* Cas : Modification d'un administrateur ************** */
		/* ************************************************************* */

		if (administrateur.getIdentifiant() != 0) {
			
			FacesContext context = null;
			if (administrateurDao.modifier(administrateur.getIdentifiant(), administrateur)) {
				// => modif  ok
				// --> envoie d'un message vers la vue avec la classe FacesMessage
				context = FacesContext.getCurrentInstance();
				// Définition du message
				FacesMessage messageUpdate = new FacesMessage("Infos :","L'administrateur à été modifier avec succès");

				// envoie du message vers la vue
				// -> null : message global pour toute la page
				// /ou/
				// id du composant context.addMessage("id du composant", messageUpdate);
				context.addMessage(null, messageUpdate);
				// ->pour récupérer le message dans la vue => <h:message></h:message>
			} else {
				// => modif not ok
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec modification",
						"La modification de l'administrateur à échouée"));
			} // Fin du if else
		} // Fin du if ajout Administrateur

	}// Fin de la méthode saveAdministrateur
	
	

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);
 
        Administrateur administrateur = (Administrateur) value;
        return administrateur.getEmail().toLowerCase().contains(filterText)
                || administrateur.getNom().toLowerCase().contains(filterText)
                || administrateur.getPrenom().toLowerCase().contains(filterText)
                || administrateur.getIdentifiant() < filterInt;
    }
    
    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
	/* _______________________ GETTERS/SETTERS ________________________________ */


	public void setAdministrateurs(Collection<Administrateur> administrateurs) {
		this.administrateurs = administrateurs;
	}

	public AdministrateurDaoImpl getAdministrateurDao() {
		return administrateurDao;
	}

	public void setAdministrateurDao(AdministrateurDaoImpl administrateurDao) {
		this.administrateurDao = administrateurDao;
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}


	public List<Administrateur> getListeAdministrateurs() {
		return listeAdministrateurs;
	}

	public void setListeAdministrateurs(List<Administrateur> listeAdministrateurs) {
		this.listeAdministrateurs = listeAdministrateurs;
	}

	public List<Administrateur> getAdministrateurs2() {
		return administrateurs2;
	}

	public void setAdministrateurs2(List<Administrateur> administrateurs2) {
		this.administrateurs2 = administrateurs2;
	}
	
}// Fin de la classe AdministrateurBean
