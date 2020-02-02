package com.intiformation.gestionecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestionecole.entity.Personne;
import com.intiformation.gestionecole.tool.JpaUtil;

public class PersonneDao implements IGestionDao<Personne> {
	// 1. récup de la l'entityManager à partir de JpaUtil
	private EntityManager entityManager = JpaUtil.getInstance();

	/* ============================================================== */
	/* ======================= Ajouter Personne ========================= */
	/* ============================================================== */
	@Override
	public boolean ajouter(Personne aPersonne) {

		EntityTransaction transaction = null;
		try {
			// 1. récup de ouverture d'une transaction
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 2. ajout de l'personne à la bdd via la méthode persist()
			entityManager.persist(aPersonne);

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
	}// fin de la méthode ajouterPersonne()

	/* ============================================================== */
	/* ===================== Get By IdPersonne ====================== */
	/* ============================================================== */
	@Override
	public Personne getById(int pIdPersonne) {

		Personne personne = null;

		try {
			Query getByIdQuery = entityManager.createNamedQuery("Personne_getById");
			getByIdQuery.setParameter("pID", pIdPersonne);

			personne = (Personne) getByIdQuery.getSingleResult();

			return personne;

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 3. fermeture de l'entityManager
			// entityManager.close();
		} // end finally

		return null;
	}// end getPersonneById()

	/* ============================================================== */
	/* ===================== Modifier Personne ====================== */
	/* ============================================================== */
	@Override
	public boolean modifier(int pIdPersonne, Personne pPersonne) {

		EntityTransaction transaction = null;

		try {
			// 1. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 3. récup de l'personne à modifier
			Personne personneUpdate = getById(pIdPersonne);

			// 4. modif de l'personne

			personneUpdate.setIdentifiant(pPersonne.getIdentifiant());
			personneUpdate.setMotDePasse(pPersonne.getMotDePasse());
			personneUpdate.setNom(pPersonne.getNom());
			personneUpdate.setPrenom(pPersonne.getPrenom());
			personneUpdate.setEmail(pPersonne.getEmail());

			// 5. modif de l'personne dans la bdd via la méthode update()
			entityManager.merge(personneUpdate);

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
	}// end modifierPersonne

	/* ============================================================== */
	/* ==================== Supprimer Personne ====================== */
	/* ============================================================== */
	@Override
	public boolean supprimer(int pIdPersonne) {

		EntityTransaction transaction = null;

		try {
			// 2. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();
			// 3. récup de l'personne à modifier
			Personne personneSupp = getById(pIdPersonne);

			// 4. modif de l'personne dans la bdd via la méthode remove() de l'entityManager
			entityManager.remove(personneSupp);

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
	}// end supprimerPersonne

	/* ============================================================== */
	/* ===================== Get all Personne ======================= */
	/* ============================================================== */
	@Override
	public List<Personne> getAll() {

		List<Personne> listeAllPersonnes = null;

		try {
			Query getAllPersonneQuery = entityManager.createNamedQuery("Personne_getAll");

			listeAllPersonnes = getAllPersonneQuery.getResultList();

			return listeAllPersonnes;

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return null;

	}// Fin de la méthode getAllPersonne

}// Fin de la classe PersonneDao
