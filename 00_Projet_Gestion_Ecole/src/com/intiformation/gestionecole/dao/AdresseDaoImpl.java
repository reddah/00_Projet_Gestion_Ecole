package com.intiformation.gestionecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestionecole.entity.Adresse;
import com.intiformation.gestionecole.tool.JpaUtil;

public class AdresseDaoImpl implements IGestionDao<Adresse>{
	// 1. récup de la l'entityManager à partir de JpaUtil
	private EntityManager entityManager = JpaUtil.getInstance();

	/* ============================================================== */
	/* ======================= Ajouter Adresse ========================= */
	/* ============================================================== */
	@Override
	public boolean ajouter(Adresse aAdresse) {

		EntityTransaction transaction = null;
		try {
			// 1. récup de ouverture d'une transaction
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 2. ajout de l'adresse à la bdd via la méthode persist()
			entityManager.persist(aAdresse);

			// 3. validation de la transaction avec commit()
			transaction.commit();
			return true;
		} catch (PersistenceException ex) {

			if (transaction != null) {
				// 4. annulation de la transaction
				transaction.rollback();
				ex.printStackTrace();
			} // fin du if
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();

		} // fin du catch
		return false;
	}// fin de la méthode ajouterAdresse()

	/* ============================================================== */
	/* ===================== Get By IdAdresse ====================== */
	/* ============================================================== */
	@Override
	public Adresse getById(int pIdAdresse) {

		Adresse adresse = null;

		try {
			// 2. récup de l'adresse à la bdd via la méthode find()
			adresse = entityManager.find(Adresse.class, pIdAdresse);

			return adresse;
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 3. fermeture de l'entityManager
			// entityManager.close();
		} // end finally

		return null;
	}// end getAdresseById()

	/* ============================================================== */
	/* ===================== Modifier Adresse ====================== */
	/* ============================================================== */
	@Override
	public boolean modifier(int pIdAdresse, Adresse pAdresse) {
		EntityTransaction transaction = null;
		try {
			// 1. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();
			// 3. récup de l'adresse à modifier
			Adresse adresseUpdate = getById(pIdAdresse);
			// 4. modif de l'adresse

			adresseUpdate.setRue(pAdresse.getRue());
			adresseUpdate.setCodePostal(pAdresse.getCodePostal());
			adresseUpdate.setVille(pAdresse.getVille());
			// 5. modif de l'adresse dans la bdd via la méthode update()
			entityManager.merge(adresseUpdate);
			// 6. validation de la transaction avec commit()
			transaction.commit();
			return true;
		} catch (PersistenceException ex) {
			if (transaction != null) {
				// 7. annulation de la transaction
				transaction.rollback();
				ex.printStackTrace();
			}
		} finally {
			// 8. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return false;
	}// end modifierAdresse

	/* ============================================================== */
	/* ==================== Supprimer Adresse ====================== */
	/* ============================================================== */
	@Override
	public boolean supprimer(int pIdAdresse) {

		EntityTransaction transaction = null;

		try {
			// 2. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();
			// 3. récup de l'adresse à modifier
			Adresse adresseSupp = getById(pIdAdresse);

			// 4. modif de l'adresse dans la bdd via la méthode remove() de
			// l'entityManager
			entityManager.remove(adresseSupp);

			// 5. validation de la transaction avec commit()
			transaction.commit();

			return true;
		} catch (PersistenceException ex) {

			if (transaction != null) {
				// 6. annulation de la transaction
				transaction.rollback();
				ex.printStackTrace();
			}

		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return false;
	}// end supprimerAdresse

	/* ============================================================== */
	/* ===================== Get all Adresse ======================= */
	/* ============================================================== */
	@Override
	public List<Adresse> getAll() {

		List<Adresse> listeAllAdresses = null;

		try {
			Query getAllAdresseQuery = entityManager.createNamedQuery("Adresse_getAll");

			listeAllAdresses = getAllAdresseQuery.getResultList();

			return listeAllAdresses;

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return null;

	}// Fin de la méthode getAllAdresse
	
	/* ============================================================== */
	/* ===================== Attribuer Adresse ====================== */
	/* ============================================================== */

	public boolean attribuerAdresse (int pIdAdresse, Adresse pAdresse) {
		EntityTransaction transaction = null;
		try {
			// 1. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();
			// 3. récup de l'adresse à modifier
			Adresse adresseUpdate = getById(pIdAdresse);
			// 4. modif de l'adresse

			adresseUpdate.setAdministrateur(pAdresse.getAdministrateur());
			adresseUpdate.setEnseignant(pAdresse.getEnseignant());
			adresseUpdate.setEtudiant(pAdresse.getEtudiant());
			// 5. modif de l'adresse dans la bdd via la méthode update()
			entityManager.merge(adresseUpdate);
			// 6. validation de la transaction avec commit()
			transaction.commit();
			return true;
		} catch (PersistenceException ex) {
			if (transaction != null) {
				// 7. annulation de la transaction
				transaction.rollback();
				ex.printStackTrace();
			}
		} finally {
			// 8. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return false;
	}// end modifierAdresse
	
	
}// Fin de la classe AdresseDaoImpl
