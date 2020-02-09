package com.intiformation.gestionecole.managedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import com.intiformation.gestionecole.dao.EtudiantDaoImpl;
import com.intiformation.gestionecole.entity.Etudiant;

@ManagedBean
public class EtudiantBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/* _____________________________ CHAMPS __________________________________ */
	private Collection<Etudiant> etudiants;
	private Etudiant etudiant = new Etudiant();
	private List<Etudiant> etudiants2;
	private List<Etudiant> listeEtudiants;



	// Chargement du fichier de l'API servlete
	private Part uploadedFile;

	// DAO
	EtudiantDaoImpl etudiantDao;

	/* _____________________________ CTORS __________________________________ */
	/**
	 * Création d'une nouvelle instance de EtudiantBean
	 */
	public EtudiantBean() {
		etudiantDao = new EtudiantDaoImpl();
	}

	/**
	 * Récup de la liste des etudiants à partir de la BDD
	 * 
	 * @return
	 */
	public Collection<Etudiant> getEtudiants() {
		etudiants = etudiantDao.getAll();
		return etudiants;
	}

	/* _____________________________ METHODES __________________________________ */

	/**
	 * Permet d'initialiser un Etudiant appelée lors de l'ajout de l'Etudiant
	 * 
	 * @param event
	 */
	public void initEtudiant(ActionEvent event) {
		setEtudiant(new Etudiant());
	}// Fin de la méthode initEtudiant

	public void ajouterNouveauEtudiant(ActionEvent event) {
		etudiantDao.ajouter(etudiant);
	}

	public void modifierEtudiant(ActionEvent event) {
		
		etudiantDao.modifier(etudiant.getIdentifiant(), etudiant);
	}
	/**
	 * 
	 * @param event
	 */
	public void selectEtudiant(ActionEvent event) {
		// récup du param passé au click du lien modifier
		UIParameter cp = (UIParameter) event.getComponent().findComponent("editEtudiantID");
		int id = (int) cp.getValue();

		Etudiant etudiant = etudiantDao.getById(id);

		// Affectation de l'etudiant à modifier à la variable etudiant du MB
		setEtudiant(etudiant);
	}// Fin de la méthode selectEtudiant

	/**
	 * Suppression d'un etudiant
	 * 
	 * @param event
	 */
	public void deleteEtudiant(ActionEvent event) {
		// récup du param passé au click du lien supprimer
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteEtudiantID");

		// récup de la valeur du param (l'id de l'etudiant)
		int etudiantId = (int) cp.getValue();

		// Suppression de l'etudiant dans la bdd via la dao
		// envoi d'un message vers la vue
		// Récup du context

		FacesContext context = null;
		if (etudiantDao.supprimer(etudiantId)) {
			// => supp ok
			// --> envoie d'un message vers la vue avce la classe FacesMessage
			context = FacesContext.getCurrentInstance();
			// Définition du message
			FacesMessage messageDelete = new FacesMessage("Infos :","L'etudiant à été supprimer avec succès");

			// envoie du message vers la vue
			// -> null : message global pour toute la page
			// /ou/
			// id du composant context.addMessage("id du composant", messageDelete);
			context.addMessage(null, messageDelete);
			// ->pour récupérer le message dans la vue => <h:message></h:message>
		} else {
			// => supp not ok
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Suppression",
					"La suppression de l'etudiant à échouée"));
		} // Fin du if else
	}// Fin de la méthode deleteEtudiant

	public void saveEtudiant() {
		// traitement du fileUpload : recup du nom de l'image
		String fileName = uploadedFile.getSubmittedFileName();
		// affectation du nom à la prop urlPhotoEtudiant de l'Etudiant
		etudiant.setUrlPhotoEtudiant(fileName);
		// ajout de l'Etudiant dans la bdd
		if (etudiant.getIdentifiant() == 0) {
			try {
				/* ************************************************************* */
				/* ***************** Cas : ajout d'un etudiant ***************** */
				/* ************************************************************* */


				FacesContext context = null;
				if (etudiantDao.ajouter(etudiant)) {
					// => ajout ok
					// --> envoie d'un message vers la vue avce la classe FacesMessage
					context = FacesContext.getCurrentInstance();
					// Définition du message
					FacesMessage messageAdd = new FacesMessage("Infos :","L'etudiant à été ajouté avec succès");

					// envoie du message vers la vue
					// -> null : message global pour toute la page
					// /ou/
					// id du composant context.addMessage("id du composant", messageAdd);
					context.addMessage(null, messageAdd);
					// ->pour récupérer le message dans la vue => <h:message></h:message>

					/* --------ajout de la photo dans le dossier images -------- */
					// recup du contenu de l'image
					InputStream imageContent = uploadedFile.getInputStream();
					// recup de la valeur du param d'initialisation context-param de web.xml
					FacesContext fContext = FacesContext.getCurrentInstance();
					String pathTmp = fContext.getExternalContext().getInitParameter("file-upload");

					String filePath = fContext.getExternalContext().getRealPath(pathTmp);

					// création du fichier image (conteneur de l'image)
					File targetFile = new File(filePath, fileName);

					// instanciation du flux de sortie vers le fichier image
					OutputStream outStream = new FileOutputStream(targetFile);
					byte[] buf = new byte[1024];
					int len;

					while ((len = imageContent.read(buf)) > 0) {
						outStream.write(buf, 0, len);
					}

					outStream.close();
				} else {
					// => ajout not ok
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Suppression",
							"L'ajout de l'etudiant à échouée"));
				} // Fin du if else
				
			} catch (IOException ex) {
				Logger.getLogger(EtudiantBean.class.getName()).log(Level.SEVERE, null, ex);
			} // Fin du try catch

		} // Fin du if ajout Etudiant

		/* ************************************************************* */
		/* ************* Cas : Modification d'un etudiant ************** */
		/* ************************************************************* */

		if (etudiant.getIdentifiant() != 0) {
			if (uploadedFile != null) {

				String fileNameToUpdate = uploadedFile.getSubmittedFileName();

				if (!"".equals(fileNameToUpdate) && fileNameToUpdate != null) {

					// affectation du nouveau nom la prop urlPhotoEtudiant de l'etudiant
					etudiant.setUrlPhotoEtudiant(fileNameToUpdate);
				}
			}

			
			FacesContext context = null;
			if (etudiantDao.modifier(etudiant.getIdentifiant(), etudiant)) {
				// => modif  ok
				// --> envoie d'un message vers la vue avec la classe FacesMessage
				context = FacesContext.getCurrentInstance();
				// Définition du message
				FacesMessage messageUpdate = new FacesMessage("L'etudiant à été modifier avec succès");

				// envoie du message vers la vue
				// -> null : message global pour toute la page
				// /ou/
				// id du composant context.addMessage("id du composant", messageUpdate);
				context.addMessage(null, messageUpdate);
				// ->pour récupérer le message dans la vue => <h:message></h:message>
			} else {
				// => modif not ok
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec Suppression",
						"La modification de l'etudiant à échouée"));
			} // Fin du if else
		} // Fin du if ajout Etudiant

	}// Fin de la méthode saveEtudiant
	
	

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);
 
        Etudiant etudiant = (Etudiant) value;
        return etudiant.getEmail().toLowerCase().contains(filterText)
                || etudiant.getNom().toLowerCase().contains(filterText)
                || etudiant.getPrenom().toLowerCase().contains(filterText)
                || etudiant.getIdentifiant() < filterInt;
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


	public void setEtudiants(Collection<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public EtudiantDaoImpl getEtudiantDao() {
		return etudiantDao;
	}

	public void setEtudiantDao(EtudiantDaoImpl etudiantDao) {
		this.etudiantDao = etudiantDao;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<Etudiant> getListeEtudiants() {
		return listeEtudiants;
	}

	public void setListeEtudiants(List<Etudiant> listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}

	public List<Etudiant> getEtudiants2() {
		return etudiants2;
	}

	public void setEtudiants2(List<Etudiant> etudiants2) {
		this.etudiants2 = etudiants2;
	}
	
}// Fin de la classe EtudiantBean
