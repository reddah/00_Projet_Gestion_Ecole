package com.intiformation.gestionecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestionecole.entity.Administrateur;
import com.intiformation.gestionecole.tool.JpaUtil;

public class AdministrateurDaoImpl implements IGestionDao<Administrateur> {

	// 1. récup de la l'entityManager à partir de JpaUtil
	private EntityManager entityManager = JpaUtil.getInstance();

	/* ============================================================== */
	/* ======================= Ajouter Administrateur ========================= */
	/* ============================================================== */
	@Override
	public boolean ajouter(Administrateur aAdministrateur) {

		EntityTransaction transaction = null;
		try {
			// 1. récup de ouverture d'une transaction
			transaction = entityManager.getTransaction();
			transaction.begin();

			// 2. ajout de l'administrateur à la bdd via la méthode persist()
			entityManager.persist(aAdministrateur);

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
	}// fin de la méthode ajouterAdministrateur()

	/* ============================================================== */
	/* ===================== Get By IdAdministrateur ====================== */
	/* ============================================================== */
	@Override
	public Administrateur getById(int pIdAdministrateur) {

		Administrateur administrateur = null;

		try {
			// 2. récup de l'administrateur à la bdd via la méthode find()
			administrateur = entityManager.find(Administrateur.class, pIdAdministrateur);

			return administrateur;
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 3. fermeture de l'entityManager
			// entityManager.close();
		} // end finally

		return null;
	}// end getAdministrateurById()

	/* ============================================================== */
	/* ===================== Modifier Administrateur ====================== */
	/* ============================================================== */
	@Override
	public boolean modifier(int pIdAdministrateur, Administrateur pAdministrateur) {
		System.out.println("modifier : 1");
		EntityTransaction transaction = null;
		System.out.println("modifier : 2");
		try {
			System.out.println("modifier try: 1");
			// 1. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			System.out.println("modifier try: 2");
			transaction.begin();
			System.out.println("modifier try: 3");
			// 3. récup de l'administrateur à modifier
			Administrateur administrateurUpdate = getById(pIdAdministrateur);
			System.out.println("modifier try: 4");
			// 4. modif de l'administrateur

			administrateurUpdate.setMotDePasse(pAdministrateur.getMotDePasse());
			administrateurUpdate.setNom(pAdministrateur.getNom());
			administrateurUpdate.setPrenom(pAdministrateur.getPrenom());
			administrateurUpdate.setEmail(pAdministrateur.getEmail());
			System.out.println("modifier try: 5");
			// 5. modif de l'administrateur dans la bdd via la méthode update()
			entityManager.merge(administrateurUpdate);
			System.out.println("modifier try: 6");
			// 6. validation de la transaction avec commit()
			transaction.commit();
			System.out.println("modifier try: 7");
			return true;
		} catch (PersistenceException ex) {
			System.out.println("modifier catch: 1");
			if (transaction != null) {
				System.out.println("modifier catch: 2");
				// 7. annulation de la transaction
				transaction.rollback();
				System.out.println("modifier catch: 3");
				ex.printStackTrace();
			}
			System.out.println("modifier catch: 4");
		} finally {
			// 8. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		System.out.println("modifier après finally");
		return false;
	}// end modifierAdministrateur

	/* ============================================================== */
	/* ==================== Supprimer Administrateur ====================== */
	/* ============================================================== */
	@Override
	public boolean supprimer(int pIdAdministrateur) {

		EntityTransaction transaction = null;

		try {
			// 2. ouverture d'une transaction via l'entityManager
			transaction = entityManager.getTransaction();
			transaction.begin();
			// 3. récup de l'administrateur à modifier
			Administrateur administrateurSupp = getById(pIdAdministrateur);

			// 4. modif de l'administrateur dans la bdd via la méthode remove() de
			// l'entityManager
			entityManager.remove(administrateurSupp);

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
	}// end supprimerAdministrateur

	/* ============================================================== */
	/* ===================== Get all Administrateur ======================= */
	/* ============================================================== */
	@Override
	public List<Administrateur> getAll() {

		List<Administrateur> listeAllAdministrateurs = null;

		try {
			Query getAllAdministrateurQuery = entityManager.createNamedQuery("Administrateur_getAll");

			listeAllAdministrateurs = getAllAdministrateurQuery.getResultList();

			return listeAllAdministrateurs;

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			// 7. fermeture de l'entityManager
			// entityManager.close();
		} // end finally
		return null;

	}// Fin de la méthode getAllAdministrateur
}// Fin de la classe AdministrateurDaoImpl
