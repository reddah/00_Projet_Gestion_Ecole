package com.intiformation.gestionecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestionecole.entity.Aide;
import com.intiformation.gestionecole.tool.JpaUtil;

public class AideDao implements IGestionDao<Aide>{

	// 1. récup de la l'entityManager à partir de JpaUtil
	private EntityManager entityManager = JpaUtil.getInstance();

	/* ============================================================== */
	/* ======================= Ajouter Aide ========================= */
	/* ============================================================== */
	@Override
	public boolean ajouter(Aide aAide) {

		EntityTransaction transaction = null;
		try {
			// 1. récup de ouverture d'une transaction
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 2. ajout de l'aide à la bdd via la méthode persist()
			entityManager.persist(aAide);

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
	}// fin de la méthode ajouterAide()

	/* ============================================================== */
	/* ======================= Get By IdAide ======================== */
	/* ============================================================== */
	@Override
	public Aide getById(int pIdAide) {

		Aide aide = null;

		try {
			// 1. récup de l'enseignant à la bdd via la méthode find()
			aide = entityManager.find(Aide.class, pIdAide);
			
			return aide;

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 3. fermeture de l'entityManager
			// entityManager.close();
		} // end finally

		return null;
	}// end getAideById()

	/* ============================================================== */
	/* ======================= Modifier Aide ======================== */
	/* ============================================================== */
	@Override
	public boolean modifier(int pIdAide, Aide pAide) {

		EntityTransaction transaction = null;

		try {
			// 1. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 3. récup de l'aide à modifier
			Aide aideUpdate = getById(pIdAide);

			// 4. modif de l'aide
			aideUpdate.setPage(pAide.getPage());
			aideUpdate.setContenu(pAide.getContenu());

			// 5. modif de l'aide dans la bdd via la méthode update()
			entityManager.merge(aideUpdate);

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
	}// end modifierAide

	/* ============================================================== */
	/* ======================= Supprimer Aide ======================= */
	/* ============================================================== */
	@Override
	public boolean supprimer(int pIdAide) {

		EntityTransaction transaction = null;

		try {
			// 2. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();
			// 3. récup de l'aide à modifier
			Aide aideSupp = getById(pIdAide);

			// 4. modif de l'aide dans la bdd via la méthode remove() de l'entityManager
			entityManager.remove(aideSupp);

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
	}// end supprimerAide
	
	/* ============================================================== */
	/* ======================== Get all Aide ======================== */
	/* ============================================================== */
	@Override
	public List<Aide> getAll() {

		List<Aide> listeAllAides = null;

		try {
			Query getAllAideQuery = entityManager.createNamedQuery("Aide_getAll");
			
			listeAllAides = getAllAideQuery.getResultList();
			
			return listeAllAides;

		} catch (PersistenceException ex) {
				ex.printStackTrace();
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return null;

	}// Fin de la méthode getAllAide
	
	
	/* ============================================================== */
	/* =================== Get all Page de Aide ================== */
	/* ============================================================== */
	
	public List<String> getAllContenuAide() {
		List<String> listeAllContenuAides = null;
		try {
			Query getAllContenuAideQuery = entityManager.createNamedQuery("Aide_getAllContenu");
			
			listeAllContenuAides = getAllContenuAideQuery.getResultList();
			
			return listeAllContenuAides;

		} catch (PersistenceException ex) {
			System.out.println("Probleme survenu lors de l'utilisation de la méthode getAllContenuAide ");
				ex.printStackTrace();
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return null;


	}// Fin de la méthode getAllContenuAide

	/* ============================================================== */
	/* =================== Get all Page de Aide ================== */
	/* ============================================================== */
	
	public List<String> getAllPageAide() {
		List<String> listeAllPageAides = null;
		try {
			Query getAllPageAideQuery = entityManager.createNamedQuery("Aide_getAllPage");
			
			listeAllPageAides = getAllPageAideQuery.getResultList();
			
			return listeAllPageAides;

		} catch (PersistenceException ex) {
			System.out.println("Probleme survenu lors de l'utilisation de la méthode getAllPageAide ");
				ex.printStackTrace();
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return null;


	}// Fin de la méthode getAllPageAide




}// Fin de la classe AideDao
