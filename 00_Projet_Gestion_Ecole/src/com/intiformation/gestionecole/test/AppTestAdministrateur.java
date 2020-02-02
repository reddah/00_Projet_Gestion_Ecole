package com.intiformation.gestionecole.test;

import com.intiformation.gestionecole.dao.AdministrateurDaoImpl;
import com.intiformation.gestionecole.entity.Administrateur;

public class AppTestAdministrateur {

	public static void main(String[] args) {
		/* ============================================================== */
		/* =================== Ajouter Administrateur =================== */
		/* ============================================================== */

		/* =============== Relation unidirectionnelle =================== */
		/**
		 * 1. Récupérer l'adresse de la personne à l'id = 1
		 *  -> utilisation de l'entité Administrateur
		 */
		// administrateur à ajouter
		Administrateur administrateur = new Administrateur("mdp123", "AdminNom", "AdminPrenom", "admin@admin.com");
//		// adresse à ajouter
//		Adresse adresse = new Adresse("15 rue de chez moi", 33000, "Bordeaux");
//		// 3. Association de l'administrateur à l'adresse
//		administrateur.setAdresse(adresse);
		// DAO Administrateur
		AdministrateurDaoImpl administrateurDao = new AdministrateurDaoImpl();
		// Ajout administrateur
		administrateurDao.ajouter(administrateur);
		// Affichage
		System.out.println("Ajout des administrateurs : ++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + administrateur);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

		System.out.println(" Administrateurs ajouter : ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + administrateur.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

		/* ============================================================== */
		/* ================== Get By IdAdministrateur =================== */
		/* ============================================================== */

		System.out.println("Get by id ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > Email : " + administrateurDao.getById(1).getEmail());
		System.out.println("\t > Mot de passe : " + administrateurDao.getById(1).getMotDePasse());
		System.out.println("\t > Nom : " + administrateurDao.getById(1).getNom());
		System.out.println("\t > Prenom : " + administrateurDao.getById(1).getPrenom());
	//	System.out.println("\t > Adresse : " + administrateurDao.getById(1).getAdresse());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

		System.out.println("Get by id toString ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + administrateur.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

		/* ============================================================== */
		/* ================= Modifier Administrateur ==================== */
		/* ============================================================== */

		System.out.println("UPDATE +++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		administrateur = new Administrateur("mdp000", "AdminNom1", "AdminPrenom1", "admin1@admin.com");
		administrateurDao.modifier(1, administrateur);
		System.out.println("\t > Aprés update : ");
		Administrateur administrateur1 = administrateurDao.getById(1);
		System.out.println("\t\t > id = : " + administrateur1.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

		/* ============================================================== */
		/* ================== Supprimer Administrateur ================== */
		/* ============================================================== */

		System.out.println("DELETE ++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		// administrateurDao.supprimer(1);

		/* ============================================================== */
		/* ================== Get all Administrateur ==================== */
		/* ============================================================== */

		System.out.println("Get All Administrateurs ===================================");
		for (Administrateur administrateur2 : administrateurDao.getAll()) {
			System.out.println("\t > " + administrateur2.toString());
		}

		System.out.println("===================================================================\n");

	}// Fin du main

}// Fin de la classe
