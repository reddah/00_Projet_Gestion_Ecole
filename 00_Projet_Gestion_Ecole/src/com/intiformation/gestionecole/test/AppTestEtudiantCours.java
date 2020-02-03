package com.intiformation.gestionecole.test;

import com.intiformation.gestionecole.dao.EtudiantCoursDaoImpl;
import com.intiformation.gestionecole.entity.EtudiantCours;

public class AppTestEtudiantCours {

	public static void main(String[] args) {
		System.out.println("Main AppTestEtudiantCours : 1");
		/*==============================================================*/
		/*=================== Ajouter EtudiantCours ===================*/
		/*==============================================================*/
		
		// etudiantCours à ajouter
		EtudiantCours etudiantCours = new EtudiantCours(true,"Motif1","cours","etudiant");
		System.out.println("Main AppTestEtudiantCours : 2");

		// DAO
		EtudiantCoursDaoImpl etudiantCoursDao = new EtudiantCoursDaoImpl();
		System.out.println("Main AppTestEtudiantCours : 3");
		
		// Ajout
		etudiantCoursDao.ajouter(etudiantCours);
		System.out.println("Main AppTestEtudiantCours : 4");
		
		// Affichage
		System.out.println("Ajout des etudiantCourss : ++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + etudiantCours);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ================== Get By IdEtudiantCours =================== */
		/* ============================================================== */
		
		System.out.println("Get by id ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > Email : " + etudiantCoursDao.getById(1).isAbsence());
		System.out.println("\t > Mot de passe : " + etudiantCoursDao.getById(1).getMotif());
		System.out.println("\t > Nom : " + etudiantCoursDao.getById(1).getCours());
		System.out.println("\t > Prenom : " + etudiantCoursDao.getById(1).getEtudiant());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		System.out.println("Get by id toString ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + etudiantCours.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ================= Modifier EtudiantCours ==================== */
		/* ============================================================== */
		
		System.out.println("UPDATE +++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		etudiantCours = new EtudiantCours(true,"Motif1","cours","etudiant");
		etudiantCoursDao.modifier(1, etudiantCours);
		System.out.println("\t > Aprés update : ");
		EtudiantCours etudiantCours1 = etudiantCoursDao.getById(1);
		System.out.println("\t\t > id = : " + etudiantCours1.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		/* ============================================================== */
		/* ================== Supprimer EtudiantCours ================== */
		/* ============================================================== */
		
		System.out.println("DELETE ++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		//etudiantCoursDao.supprimer(2);
		
		
		/* ============================================================== */
		/* ================== Get all EtudiantCours ==================== */
		/* ============================================================== */
		
		System.out.println("Get All EtudiantCourss ===================================");	
		for (EtudiantCours etudiantCours2 : etudiantCoursDao.getAll()) {
			System.out.println("\t > " + etudiantCours2.toString());
		}
		
		System.out.println("===================================================================\n");
		

		
	}// Fin du main

}// Fin de la classe 
