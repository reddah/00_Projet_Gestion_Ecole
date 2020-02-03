package com.intiformation.gestionecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestionecole.entity.Promotion;
import com.intiformation.gestionecole.tool.JpaUtil;

public class PromotionDaoImpl implements IGestionDao<Promotion> {

	// 1. r�cup de la l'entityManager � partir de JpaUtil
	private EntityManager entityManager = JpaUtil.getInstance();

	/* ============================================================== */
	/* ======================= Ajouter Promotion ========================= */
	/* ============================================================== */
	@Override
	public boolean ajouter(Promotion aPromotion) {

		EntityTransaction transaction = null;
		try {
			// 1. r�cup de ouverture d'une transaction
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 2. ajout de la promotion � la bdd via la m�thode persist()
			entityManager.persist(aPromotion);

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
	}// fin de la m�thode ajouterPromotion()

	/* ============================================================== */
	/* ======================= Get By IdPromotion ======================== */
	/* ============================================================== */
	@Override
	public Promotion getById(int pIdPromotion) {

		Promotion promotion = null;

		try {
			// 1. r�cup de l'enseignant � la bdd via la m�thode find()
			promotion = entityManager.find(Promotion.class, pIdPromotion);

			return promotion;

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 3. fermeture de l'entityManager
			// entityManager.close();
		} // end finally

		return null;
	}// end getPromotionById()

	/* ============================================================== */
	/* ======================= Modifier Promotion ======================== */
	/* ============================================================== */
	@Override
	public boolean modifier(int pIdPromotion, Promotion pPromotion) {

		EntityTransaction transaction = null;

		try {
			// 1. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 3. r�cup de la promotion � modifier
			Promotion promotionUpdate = getById(pIdPromotion);

			// 4. modif de la promotion
			promotionUpdate.setLibelle(pPromotion.getLibelle());

			// 5. modif de la promotion dans la bdd via la m�thode update()
			entityManager.merge(promotionUpdate);

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
	}// end modifierPromotion

	/* ============================================================== */
	/* ======================= Supprimer Promotion ======================= */
	/* ============================================================== */
	@Override
	public boolean supprimer(int pIdPromotion) {

		EntityTransaction transaction = null;

		try {
			// 2. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();
			// 3. r�cup de la promotion � modifier
			Promotion promotionSupp = getById(pIdPromotion);

			// 4. modif de la promotion dans la bdd via la m�thode remove() de
			// l'entityManager
			entityManager.remove(promotionSupp);

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
	}// end supprimerPromotion

	/* ============================================================== */
	/* ======================== Get all Promotion ======================== */
	/* ============================================================== */
	@Override
	public List<Promotion> getAll() {

		List<Promotion> listeAllPromotions = null;

		try {
			Query getAllPromotionQuery = entityManager.createNamedQuery("Promotion_getAll");

			listeAllPromotions = getAllPromotionQuery.getResultList();

			return listeAllPromotions;

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return null;

	}// Fin de la m�thode getAllPromotion

}// Fin de la classe PromotionDao
