package com.intiformation.gestionecole.test;

import com.intiformation.gestionecole.dao.MatiereDaoImpl;
import com.intiformation.gestionecole.entity.Matiere;

public class AppTestMatiere {

	public static void main(String[] args) {
		System.out.println("Main AppTestMatiere : 1");

		/*==============================================================*/
		/*=================== Ajouter Matiere ===================*/
		/*==============================================================*/
		
		// matiere à ajouter
		Matiere matiere = new Matiere("matiere 1");

		System.out.println("Main AppTestMatiere : 2");
		// DAO
		MatiereDaoImpl matiereDao = new MatiereDaoImpl();
		System.out.println("Main AppTestMatiere : 3");
		
		// Ajout
		matiereDao.ajouter(matiere);
		System.out.println("Main AppTestMatiere : 4");
		
		// Affichage
		System.out.println("Ajout des matieres : ++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + matiere);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ================== Get By IdMatiere =================== */
		/* ============================================================== */
		
		System.out.println("Get by id ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > id : " + matiereDao.getById(1).getIdMatiere());
		System.out.println("\t > libelle : " + matiereDao.getById(1).getLibelle());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		System.out.println("Get by id toString ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + matiere.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ================= Modifier Matiere ==================== */
		/* ============================================================== */
		
		System.out.println("UPDATE +++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		matiere = new Matiere("matiere 2");
		matiereDao.modifier(1, matiere);
		System.out.println("\t > Aprés update : ");
		Matiere matiere1 = matiereDao.getById(1);
		System.out.println("\t\t > id = : " + matiere1.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		/* ============================================================== */
		/* ================== Supprimer Matiere ================== */
		/* ============================================================== */
		
		System.out.println("DELETE ++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		//matiereDao.supprimer(2);
		
		
		/* ============================================================== */
		/* ================== Get all Matiere ==================== */
		/* ============================================================== */
		
		System.out.println("Get All Matieres ===================================");	
		for (Matiere matiere2 : matiereDao.getAll()) {
			System.out.println("\t > " + matiere2.toString());
		}
		
		System.out.println("===================================================================\n");
		

		
	}// Fin du main

}// Fin de la classe 
