package com.intiformation.gestionecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestionecole.entity.EtudiantCours;
import com.intiformation.gestionecole.tool.JpaUtil;

public class EtudiantCoursDaoImpl implements IGestionDao<EtudiantCours>{

	// 1. r�cup de la l'entityManager � partir de JpaUtil
	private EntityManager entityManager = JpaUtil.getInstance();

	/* ============================================================== */
	/* ======================= Ajouter EtudiantCours ========================= */
	/* ============================================================== */
	@Override
	public boolean ajouter(EtudiantCours aEtudiantCours) {

		EntityTransaction transaction = null;
		try {
			// 1. r�cup de ouverture d'une transaction
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 2. ajout de etudiantCours � la bdd via la m�thode persist()
			entityManager.persist(aEtudiantCours);

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
	}// fin de la m�thode ajouterEtudiantCours()

	/* ============================================================== */
	/* ======================= Get By IdEtudiantCours ======================== */
	/* ============================================================== */
	@Override
	public EtudiantCours getById(int pIdEtudiantCours) {

		EtudiantCours etudiantCours = null;

		try {
			// 1. r�cup de l'enseignant � la bdd via la m�thode find()
			etudiantCours = entityManager.find(EtudiantCours.class, pIdEtudiantCours);
			
			return etudiantCours;

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 3. fermeture de l'entityManager
			// entityManager.close();
		} // end finally

		return null;
	}// end getEtudiantCoursById()

	/* ============================================================== */
	/* ======================= Modifier EtudiantCours ======================== */
	/* ============================================================== */
	@Override
	public boolean modifier(int pIdEtudiantCours, EtudiantCours pEtudiantCours) {

		EntityTransaction transaction = null;

		try {
			// 1. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 3. r�cup de etudiantCours � modifier
			EtudiantCours etudiantCoursUpdate = getById(pIdEtudiantCours);

			// 4. modif de etudiantCours
			etudiantCoursUpdate.setAbsence(pEtudiantCours.isAbsence());
			etudiantCoursUpdate.setMotif(pEtudiantCours.getMotif());
			etudiantCoursUpdate.setCours(pEtudiantCours.getCours());
			etudiantCoursUpdate.setEtudiant(pEtudiantCours.getEtudiant());

			// 5. modif de etudiantCours dans la bdd via la m�thode update()
			entityManager.merge(etudiantCoursUpdate);

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
	}// end modifierEtudiantCours

	/* ============================================================== */
	/* ======================= Supprimer EtudiantCours ======================= */
	/* ============================================================== */
	@Override
	public boolean supprimer(int pIdEtudiantCours) {

		EntityTransaction transaction = null;

		try {
			// 2. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();
			// 3. r�cup de etudiantCours � modifier
			EtudiantCours etudiantCoursSupp = getById(pIdEtudiantCours);

			// 4. modif de etudiantCours dans la bdd via la m�thode remove() de l'entityManager
			entityManager.remove(etudiantCoursSupp);

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
	}// end supprimerEtudiantCours
	
	/* ============================================================== */
	/* ======================== Get all EtudiantCours ======================== */
	/* ============================================================== */
	@Override
	public List<EtudiantCours> getAll() {

		List<EtudiantCours> listeAllEtudiantCours = null;

		try {
			Query getAllEtudiantCoursQuery = entityManager.createNamedQuery("EtudiantCours_getAll");
			
			listeAllEtudiantCours = getAllEtudiantCoursQuery.getResultList();
			
			return listeAllEtudiantCours;

		} catch (PersistenceException ex) {
				ex.printStackTrace();
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return null;

	}// Fin de la m�thode getAllEtudiantCours
	
}// Fin de la classe EtudiantCoursDao
