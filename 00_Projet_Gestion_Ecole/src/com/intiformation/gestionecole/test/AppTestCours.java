package com.intiformation.gestionecole.test;

import com.intiformation.gestionecole.dao.CoursDaoImpl;
import com.intiformation.gestionecole.entity.Cours;

public class AppTestCours {

	public static void main(String[] args) {

		/*==============================================================*/
		/*=================== Ajouter Cours ===================*/
		/*==============================================================*/
		
		// cours à ajouter
		Cours cours = new Cours("Matiere 1", "2h", "1Test" );
		
		// DAO
		CoursDaoImpl coursDao = new CoursDaoImpl();
		
		// Ajout
		coursDao.ajouter(cours);
		
		// Affichage
		System.out.println("Ajout des courss : ++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + cours);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ================== Get By IdCours =================== */
		/* ============================================================== */
		
		System.out.println("Get by id ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > Email : " + coursDao.getById(1).getLibelle());
		System.out.println("\t > Mot de passe : " + coursDao.getById(1).getduree());
		System.out.println("\t > Nom : " + coursDao.getById(1).getDescription());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		System.out.println("Get by id toString ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + cours.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ================= Modifier Cours ==================== */
		/* ============================================================== */
		
		System.out.println("UPDATE +++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		cours = new Cours("Matiere 1", "2h", "1Test" );
		coursDao.modifier(3, cours);
		System.out.println("\t > Aprés update : ");
		Cours cours1 = coursDao.getById(3);
		System.out.println("\t\t > id = : " + cours1.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		/* ============================================================== */
		/* ================== Supprimer Cours ================== */
		/* ============================================================== */
		
		System.out.println("DELETE ++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		//coursDao.supprimer(2);
		
		
		/* ============================================================== */
		/* ================== Get all Cours ==================== */
		/* ============================================================== */
		
		System.out.println("Get All Courss ===================================");	
		for (Cours cours2 : coursDao.getAll()) {
			System.out.println("\t > " + cours2.toString());
		}
		
		System.out.println("===================================================================\n");
		

		
	}// Fin du main

}// Fin de la classe 
