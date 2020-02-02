package com.intiformation.gestionecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestionecole.entity.Enseignant;
import com.intiformation.gestionecole.tool.JpaUtil;

public class EnseignantDaoImpl implements IGestionDao<Enseignant>{


	// 1. r�cup de la l'entityManager � partir de JpaUtil
		private EntityManager entityManager = JpaUtil.getInstance();

		/* ============================================================== */
		/* ======================= Ajouter Enseignant ========================= */
		/* ============================================================== */
		@Override
		public boolean ajouter(Enseignant aEnseignant) {

			EntityTransaction transaction = null;
			try {
				// 1. r�cup de ouverture d'une transaction
				transaction = entityManager.getTransaction();
				transaction.begin();

				// 2. ajout de l'enseignant � la bdd via la m�thode persist()
				entityManager.persist(aEnseignant);

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
		}// fin de la m�thode ajouterEnseignant()

		/* ============================================================== */
		/* ===================== Get By IdEnseignant ====================== */
		/* ============================================================== */
		@Override
		public Enseignant getById(int pIdEnseignant) {

			Enseignant enseignant = null;
			try {
				// 1. r�cup de l'enseignant � la bdd via la m�thode find()
				enseignant = entityManager.find(Enseignant.class, pIdEnseignant);
				
				return enseignant;

			} catch (PersistenceException ex) {
				ex.printStackTrace();
			} finally {
				// 2. fermeture de l'entityManager
				// entityManager.close();
			} // end finally

			return null;
		}// end getEnseignantById()

		/* ============================================================== */
		/* ===================== Modifier Enseignant ====================== */
		/* ============================================================== */
		@Override
		public boolean modifier(int pIdEnseignant, Enseignant pEnseignant) {
			EntityTransaction transaction = null;
			try {
				// 1. ouverture d'une transaction via l'entityManager
				transaction = entityManager.getTransaction();
				transaction.begin();
				// 3. r�cup de l'enseignant � modifier
				Enseignant enseignantUpdate = getById(pIdEnseignant);
				// 4. modif de l'enseignant

				enseignantUpdate.setMotDePasse(pEnseignant.getMotDePasse());
				enseignantUpdate.setNom(pEnseignant.getNom());
				enseignantUpdate.setPrenom(pEnseignant.getPrenom());
				enseignantUpdate.setEmail(pEnseignant.getEmail());
				// 5. modif de l'enseignant dans la bdd via la m�thode update()
				entityManager.merge(enseignantUpdate);
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
		}// end modifierEnseignant

		/* ============================================================== */
		/* ==================== Supprimer Enseignant ====================== */
		/* ============================================================== */
		@Override
		public boolean supprimer(int pIdEnseignant) {

			EntityTransaction transaction = null;

			try {
				// 2. ouverture d'une transaction via l'entityManager
				transaction = entityManager.getTransaction();
				transaction.begin();
				// 3. r�cup de l'enseignant � modifier
				Enseignant enseignantSupp = getById(pIdEnseignant);

				// 4. modif de l'enseignant dans la bdd via la m�thode remove() de l'entityManager
				entityManager.remove(enseignantSupp);

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
		}// end supprimerEnseignant

		/* ============================================================== */
		/* ===================== Get all Enseignant ======================= */
		/* ============================================================== */
		@Override
		public List<Enseignant> getAll() {

			List<Enseignant> listeAllEnseignants = null;

			try {
				Query getAllEnseignantQuery = entityManager.createNamedQuery("Enseignant_getAll");

				listeAllEnseignants = getAllEnseignantQuery.getResultList();

				return listeAllEnseignants;

			} catch (PersistenceException ex) {
				ex.printStackTrace();
			} finally {
				// 7. fermeture de l'entityManager
				// entityManager.close();
			} // end finally
			return null;

		}// Fin de la m�thode getAllEnseignant
}// Fin de la classe EnseignantDaoImpl
