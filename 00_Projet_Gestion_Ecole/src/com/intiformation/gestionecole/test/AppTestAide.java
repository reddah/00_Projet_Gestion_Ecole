package com.intiformation.gestionecole.test;

import com.intiformation.gestionecole.dao.AideDao;
import com.intiformation.gestionecole.entity.Aide;

public class AppTestAide {

	public static void main(String[] args) {

		/*==============================================================*/
		/*======================= Ajouter Aide =========================*/
		/*==============================================================*/
		
		// aide à ajouter
		Aide aide = new Aide();
		Aide aide1 = new Aide("Aide 1","C'est pour toi");
		Aide aide2 = new Aide("Aide 2","C'est pour moi");
		Aide aide3 = new Aide("Aide 3","C'est pour nous");
		Aide aide4 = new Aide("Aide 3","C'est pour nous");
		
		// DAO
		AideDao aideDao = new AideDao();
		
		// Ajout
		aideDao.ajouter(aide1);
		aideDao.ajouter(aide2);
		aideDao.ajouter(aide3);
		aideDao.ajouter(aide4);
		
		// Affichage
		System.out.println("Ajout des aides : ++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + aide1);
		System.out.println("\t > " + aide2);
		System.out.println("\t > " + aide3);
		System.out.println("\t > " + aide4);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ======================= Get By IdAide ======================== */
		/* ============================================================== */
		
		System.out.println("Get by id ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > Page : " + aideDao.getById(1).getPage());
		System.out.println("\t > Contenu : " + aideDao.getById(1).getContenu());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		
		/* ============================================================== */
		/* ======================= Modifier Aide ======================== */
		/* ============================================================== */
		
		System.out.println("UPDATE +++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		aide = new Aide("Aide 9","C'est pour vous");
		aideDao.modifier(3, aide);
		System.out.println("\t > Aprés update : ");
		Aide a = aideDao.getById(3);
		System.out.println("\t\t > id = : " + a.getPage() + ", nom : " + a.getContenu() );
		System.out.println("\t\t > " + a.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		/* ============================================================== */
		/* ======================= Supprimer Aide ======================= */
		/* ============================================================== */
		
		System.out.println("DELETE ++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		//aideDao.supprimerAide(5);
		
		
		/* ============================================================== */
		/* ======================== Get all Aide ======================== */
		/* ============================================================== */
		
		System.out.println("Get All Aides ===================================");	
		for (Aide a1 : aideDao.getAll()) {
			System.out.println("\t > " + a1.getPage() + "  " + a1.getContenu());
		}
		
		System.out.println("===================================================================\n");
		
		
		/* ============================================================== */
		/* =================== Get all Contenu de Aide ================== */
		/* ============================================================== */
		
		System.out.println("Get ALL Contenu Aide ===================================");	
		for (String contenu : aideDao.getAllContenuAide()) {
			System.out.println("\t > " + contenu);
		}
		
		System.out.println("===================================================================\n");
		
		
		/* ============================================================== */
		/* =================== Get all Contenu de Aide ================== */
		/* ============================================================== */
		
		System.out.println("Get ALL Page Aide ===================================");	
		for (String page : aideDao.getAllPageAide()) {
			System.out.println("\t > " + page);
		}
		
		System.out.println("===================================================================\n");
	}// Fin du main

}// Fin de la classe 
