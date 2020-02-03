package com.intiformation.gestionecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestionecole.entity.Cours;
import com.intiformation.gestionecole.tool.JpaUtil;

public class CoursDaoImpl implements IGestionDao<Cours>{

	// 1. récup de la l'entityManager à partir de JpaUtil
	private EntityManager entityManager = JpaUtil.getInstance();

	/* ============================================================== */
	/* ======================= Ajouter Cours ========================= */
	/* ============================================================== */
	@Override
	public boolean ajouter(Cours aCours) {

		EntityTransaction transaction = null;
		try {
			// 1. récup de ouverture d'une transaction
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 2. ajout de du cours à la bdd via la méthode persist()
			entityManager.persist(aCours);

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
	}// fin de la méthode ajouterCours()

	/* ============================================================== */
	/* ======================= Get By IdCours ======================== */
	/* ============================================================== */
	@Override
	public Cours getById(int pIdCours) {

		Cours cours = null;

		try {
			// 1. récup de l'enseignant à la bdd via la méthode find()
			cours = entityManager.find(Cours.class, pIdCours);
			
			return cours;

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 3. fermeture de l'entityManager
			// entityManager.close();
		} // end finally

		return null;
	}// end getCoursById()

	/* ============================================================== */
	/* ======================= Modifier Cours ======================== */
	/* ============================================================== */
	@Override
	public boolean modifier(int pIdCours, Cours pCours) {

		EntityTransaction transaction = null;

		try {
			// 1. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 3. récup de du cours à modifier
			Cours coursUpdate = getById(pIdCours);

			// 4. modif de du cours
			coursUpdate.setLibelle(pCours.getLibelle());
			coursUpdate.setDate(pCours.getDate());
			coursUpdate.setduree(pCours.getduree());
			coursUpdate.setDescription(pCours.getDescription());

			// 5. modif de du cours dans la bdd via la méthode update()
			entityManager.merge(coursUpdate);

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
	}// end modifierCours

	/* ============================================================== */
	/* ======================= Supprimer Cours ======================= */
	/* ============================================================== */
	@Override
	public boolean supprimer(int pIdCours) {

		EntityTransaction transaction = null;

		try {
			// 2. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();
			// 3. récup de du cours à modifier
			Cours coursSupp = getById(pIdCours);

			// 4. modif de du cours dans la bdd via la méthode remove() de l'entityManager
			entityManager.remove(coursSupp);

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
	}// end supprimerCours
	
	/* ============================================================== */
	/* ======================== Get all Cours ======================== */
	/* ============================================================== */
	@Override
	public List<Cours> getAll() {

		List<Cours> listeAllCourss = null;

		try {
			Query getAllCoursQuery = entityManager.createNamedQuery("Cours_getAll");
			
			listeAllCourss = getAllCoursQuery.getResultList();
			
			return listeAllCourss;

		} catch (PersistenceException ex) {
				ex.printStackTrace();
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return null;

	}// Fin de la méthode getAllCours
	
}// Fin de la classe CoursDao
