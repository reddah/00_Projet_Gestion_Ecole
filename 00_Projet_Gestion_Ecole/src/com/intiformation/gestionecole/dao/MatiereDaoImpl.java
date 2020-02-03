package com.intiformation.gestionecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestionecole.entity.Matiere;
import com.intiformation.gestionecole.tool.JpaUtil;

public class MatiereDaoImpl implements IGestionDao<Matiere>{

	// 1. récup de la l'entityManager à partir de JpaUtil
	private EntityManager entityManager = JpaUtil.getInstance();

	/* ============================================================== */
	/* ======================= Ajouter Matiere ========================= */
	/* ============================================================== */
	@Override
	public boolean ajouter(Matiere aMatiere) {

		EntityTransaction transaction = null;
		try {
			// 1. récup de ouverture d'une transaction
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 2. ajout de la matiere à la bdd via la méthode persist()
			entityManager.persist(aMatiere);

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
	}// fin de la méthode ajouterMatiere()

	/* ============================================================== */
	/* ======================= Get By IdMatiere ======================== */
	/* ============================================================== */
	@Override
	public Matiere getById(int pIdMatiere) {

		Matiere matiere = null;

		try {
			// 1. récup de l'enseignant à la bdd via la méthode find()
			matiere = entityManager.find(Matiere.class, pIdMatiere);
			
			return matiere;

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 3. fermeture de l'entityManager
			// entityManager.close();
		} // end finally

		return null;
	}// end getMatiereById()

	/* ============================================================== */
	/* ======================= Modifier Matiere ======================== */
	/* ============================================================== */
	@Override
	public boolean modifier(int pIdMatiere, Matiere pMatiere) {

		EntityTransaction transaction = null;

		try {
			// 1. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 3. récup de la matiere à modifier
			Matiere matiereUpdate = getById(pIdMatiere);

			// 4. modif de la matiere
			matiereUpdate.setLibelle(pMatiere.getLibelle());

			// 5. modif de la matiere dans la bdd via la méthode update()
			entityManager.merge(matiereUpdate);

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
	}// end modifierMatiere

	/* ============================================================== */
	/* ======================= Supprimer Matiere ======================= */
	/* ============================================================== */
	@Override
	public boolean supprimer(int pIdMatiere) {

		EntityTransaction transaction = null;

		try {
			// 2. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();
			// 3. récup de la matiere à modifier
			Matiere matiereSupp = getById(pIdMatiere);

			// 4. modif de la matiere dans la bdd via la méthode remove() de l'entityManager
			entityManager.remove(matiereSupp);

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
	}// end supprimerMatiere
	
	/* ============================================================== */
	/* ======================== Get all Matiere ======================== */
	/* ============================================================== */
	@Override
	public List<Matiere> getAll() {

		List<Matiere> listeAllMatieres = null;

		try {
			Query getAllMatiereQuery = entityManager.createNamedQuery("Matiere_getAll");
			
			listeAllMatieres = getAllMatiereQuery.getResultList();
			
			return listeAllMatieres;

		} catch (PersistenceException ex) {
				ex.printStackTrace();
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return null;

	}// Fin de la méthode getAllMatiere
	
}// Fin de la classe MatiereDao
