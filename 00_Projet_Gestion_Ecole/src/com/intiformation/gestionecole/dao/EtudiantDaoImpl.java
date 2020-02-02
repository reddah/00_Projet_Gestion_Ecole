package com.intiformation.gestionecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestionecole.entity.Etudiant;
import com.intiformation.gestionecole.tool.JpaUtil;

public class EtudiantDaoImpl implements IGestionDao<Etudiant>{


	// 1. r�cup de la l'entityManager � partir de JpaUtil
		private EntityManager entityManager = JpaUtil.getInstance();

		/* ============================================================== */
		/* ======================= Ajouter Etudiant ========================= */
		/* ============================================================== */
		@Override
		public boolean ajouter(Etudiant aEtudiant) {

			EntityTransaction transaction = null;
			try {
				// 1. r�cup de ouverture d'une transaction
				transaction = entityManager.getTransaction();
				transaction.begin();

				// 2. ajout de l'etudiant � la bdd via la m�thode persist()
				entityManager.persist(aEtudiant);

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
		}// fin de la m�thode ajouterEtudiant()

		/* ============================================================== */
		/* ===================== Get By IdEtudiant ====================== */
		/* ============================================================== */
		@Override
		public Etudiant getById(int pIdEtudiant) {

			Etudiant etudiant = null;
			try {
				// 2. r�cup de l'etudiant � la bdd via la m�thode find()
				etudiant = entityManager.find(Etudiant.class, pIdEtudiant);
				
				return etudiant;

			} catch (PersistenceException ex) {
				ex.printStackTrace();
			} finally {
				// 3. fermeture de l'entityManager
				// entityManager.close();
			} // end finally

			return null;
		}// end getEtudiantById()

		/* ============================================================== */
		/* ===================== Modifier Etudiant ====================== */
		/* ============================================================== */
		@Override
		public boolean modifier(int pIdEtudiant, Etudiant pEtudiant) {
			EntityTransaction transaction = null;
			try {
				// 1. ouverture d'une transaction via l'entityManager
				transaction = entityManager.getTransaction();
				transaction.begin();
				// 3. r�cup de l'etudiant � modifier
				Etudiant etudiantUpdate = getById(pIdEtudiant);
				// 4. modif de l'etudiant

				etudiantUpdate.setMotDePasse(pEtudiant.getMotDePasse());
				etudiantUpdate.setNom(pEtudiant.getNom());
				etudiantUpdate.setPrenom(pEtudiant.getPrenom());
				etudiantUpdate.setEmail(pEtudiant.getEmail());
				// 5. modif de l'etudiant dans la bdd via la m�thode update()
				entityManager.merge(etudiantUpdate);
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
		}// end modifierEtudiant

		/* ============================================================== */
		/* ==================== Supprimer Etudiant ====================== */
		/* ============================================================== */
		@Override
		public boolean supprimer(int pIdEtudiant) {

			EntityTransaction transaction = null;

			try {
				// 2. ouverture d'une transaction via l'entityManager
				transaction = entityManager.getTransaction();
				transaction.begin();
				// 3. r�cup de l'etudiant � modifier
				Etudiant etudiantSupp = getById(pIdEtudiant);

				// 4. modif de l'etudiant dans la bdd via la m�thode remove() de l'entityManager
				entityManager.remove(etudiantSupp);

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
		}// end supprimerEtudiant

		/* ============================================================== */
		/* ===================== Get all Etudiant ======================= */
		/* ============================================================== */
		@Override
		public List<Etudiant> getAll() {

			List<Etudiant> listeAllEtudiants = null;

			try {
				Query getAllEtudiantQuery = entityManager.createNamedQuery("Etudiant_getAll");

				listeAllEtudiants = getAllEtudiantQuery.getResultList();

				return listeAllEtudiants;

			} catch (PersistenceException ex) {
				ex.printStackTrace();
			} finally {
				// 7. fermeture de l'entityManager
				// entityManager.close();
			} // end finally
			return null;

		}// Fin de la m�thode getAllEtudiant
}// Fin de la classe EtudiantDaoImpl
