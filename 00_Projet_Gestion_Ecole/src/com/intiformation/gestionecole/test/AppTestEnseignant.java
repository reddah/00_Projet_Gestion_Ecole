package com.intiformation.gestionecole.test;

import com.intiformation.gestionecole.dao.EnseignantDaoImpl;
import com.intiformation.gestionecole.entity.Enseignant;

public class AppTestEnseignant {

	public static void main(String[] args) {

		/*==============================================================*/
		/*=================== Ajouter Enseignant ===================*/
		/*==============================================================*/
		
		// enseignant à ajouter
		Enseignant enseignant = new Enseignant("mdp123","EnseignantNom","EnseignantPrenom","enseignant@enseignant.com");

		// DAO
		EnseignantDaoImpl enseignantDao = new EnseignantDaoImpl();
		
		// Ajout
		enseignantDao.ajouter(enseignant);
		
		// Affichage
		System.out.println("Ajout des enseignants : ++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + enseignant);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ================== Get By IdEnseignant =================== */
		/* ============================================================== */
		
		System.out.println("Get by id ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > Email : " + enseignantDao.getById(1).getEmail());
		System.out.println("\t > Mot de passe : " + enseignantDao.getById(1).getMotDePasse());
		System.out.println("\t > Nom : " + enseignantDao.getById(1).getNom());
		System.out.println("\t > Prenom : " + enseignantDao.getById(1).getPrenom());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		System.out.println("Get by id toString ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + enseignant.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ================= Modifier Enseignant ==================== */
		/* ============================================================== */
		
		System.out.println("UPDATE +++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		enseignant = new Enseignant("mdp000","AdminNom1","AdminPrenom1","admin1@admin.com");
		enseignantDao.modifier(1, enseignant);
		System.out.println("\t > Aprés update : ");
		Enseignant enseignant1 = enseignantDao.getById(1);
		System.out.println("\t\t > id = : " + enseignant1.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		/* ============================================================== */
		/* ================== Supprimer Enseignant ================== */
		/* ============================================================== */
		
		System.out.println("DELETE ++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		//enseignantDao.supprimer(2);
		
		
		/* ============================================================== */
		/* ================== Get all Enseignant ==================== */
		/* ============================================================== */
		
		System.out.println("Get All Enseignants ===================================");	
		for (Enseignant enseignant2 : enseignantDao.getAll()) {
			System.out.println("\t > " + enseignant2.toString());
		}
		
		System.out.println("===================================================================\n");
		

		
	}// Fin du main

}// Fin de la classe 
