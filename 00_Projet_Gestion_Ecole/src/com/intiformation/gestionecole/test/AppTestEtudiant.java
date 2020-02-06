package com.intiformation.gestionecole.test;

import com.intiformation.gestionecole.dao.EtudiantDaoImpl;
import com.intiformation.gestionecole.entity.Etudiant;

public class AppTestEtudiant {

	public static void main(String[] args) {

		/*==============================================================*/
		/*=================== Ajouter Etudiant ===================*/
		/*==============================================================*/
		
		// etudiant � ajouter
		Etudiant etudiant = new Etudiant("mdp123","Etudiant4Nom","Etudian4Prenom","etudiant4@etudiant.com","urlPhoto1");

		// DAO
		EtudiantDaoImpl etudiantDao = new EtudiantDaoImpl();
		
		// Ajout
		etudiantDao.ajouter(etudiant);
		
		// Affichage
		System.out.println("Ajout des etudiants : ++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + etudiant);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ================== Get By IdEtudiant =================== */
		/* ============================================================== */
		
		System.out.println("Get by id ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > Email : " + etudiantDao.getById(1).getEmail());
		System.out.println("\t > Mot de passe : " + etudiantDao.getById(1).getMotDePasse());
		System.out.println("\t > Nom : " + etudiantDao.getById(1).getNom());
		System.out.println("\t > Prenom : " + etudiantDao.getById(1).getPrenom());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		System.out.println("Get by id toString ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + etudiant.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ================= Modifier Etudiant ==================== */
		/* ============================================================== */
		
		System.out.println("UPDATE +++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		etudiant = new Etudiant("mdp123","Etudiant250Nom","Etudian250Prenom","etudiant1@etudiant.com","urlPhoto250");
		etudiantDao.modifier(3, etudiant);
		System.out.println("\t > Apr�s update : ");
		Etudiant etudiant1 = etudiantDao.getById(3);
		System.out.println("\t\t > id = : " + etudiant1.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		/* ============================================================== */
		/* ================== Supprimer Etudiant ================== */
		/* ============================================================== */
		
		System.out.println("DELETE ++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		//etudiantDao.supprimer(2);
		
		
		/* ============================================================== */
		/* ================== Get all Etudiant ==================== */
		/* ============================================================== */
		
		System.out.println("Get All Etudiants ===================================");	
		for (Etudiant etudiant2 : etudiantDao.getAll()) {
			System.out.println("\t > " + etudiant2.toString());
		}
		
		System.out.println("===================================================================\n");
		

		
	}// Fin du main

}// Fin de la classe 
