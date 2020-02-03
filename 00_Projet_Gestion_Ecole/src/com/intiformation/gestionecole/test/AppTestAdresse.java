package com.intiformation.gestionecole.test;

import com.intiformation.gestionecole.dao.AdresseDaoImpl;
import com.intiformation.gestionecole.entity.Adresse;

public class AppTestAdresse {

	public static void main(String[] args) {
		
		/* ============================================================== */
		/* =================== Ajouter Adresse =================== */
		/* ============================================================== */
		// adresse à ajouter
		Adresse adresse = new Adresse("120 rue de chez moi", 33000, "Bordeaux");
		
		// DAO Adresse
		AdresseDaoImpl adresseDao = new AdresseDaoImpl();
		// Ajout adresse
		adresseDao.ajouter(adresse);
		// Affichage
		System.out.println("Ajout des adresses : ++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + adresse);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

		System.out.println(" Adresses ajouter : ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + adresse.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

		/* ============================================================== */
		/* ================== Get By IdAdresse =================== */
		/* ============================================================== */

		System.out.println("Get by id ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > Rue : " + adresseDao.getById(1).getRue());
		System.out.println("\t > Code postal : " + adresseDao.getById(1).getCodePostal());
		System.out.println("\t > Ville : " + adresseDao.getById(1).getVille());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

		System.out.println("Get by id toString ++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t > " + adresse.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

		/* ============================================================== */
		/* ================= Modifier Adresse ==================== */
		/* ============================================================== */

		System.out.println("UPDATE +++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		adresse = new Adresse("20 rue de chez moi", 33000, "Bordeaux");
		adresseDao.modifier(1, adresse);
		System.out.println("\t > Aprés update : ");
		Adresse adresse1 = adresseDao.getById(1);
		System.out.println("\t\t > id = : " + adresse1.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

		/* ============================================================== */
		/* ================== Supprimer Adresse ================== */
		/* ============================================================== */

		System.out.println("DELETE ++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		//adresseDao.supprimer(7);

		/* ============================================================== */
		/* ================== Get all Adresse ==================== */
		/* ============================================================== */

		System.out.println("Get All Adresses ===================================");
		for (Adresse adresse2 : adresseDao.getAll()) {
			System.out.println("\t > " + adresse2.toString());
		}

		System.out.println("===================================================================\n");
		
		
		
		/* ============================================================== */
		/* ===================== Attribuer Adresse ====================== */
		/* ============================================================== */
		
//		/* A un Admin */
//		AdministrateurDaoImpl administrateurDaoImpl = new AdministrateurDaoImpl();
//		
//		Administrateur administrateur =  administrateurDaoImpl.getById(1);
//		
//		Adresse attribuerAdresseAdmin = new Adresse(administrateur);
//		
//		adresseDao.attribuerAdresse(adresseDao.getById(1).getIdAdresse(), attribuerAdresseAdmin);
//		
//		System.out.println("Attribuer Adresse ++++++++++++++++++++++++++++++++++++++++++++++++++");
//		System.out.println("\t > Rue : " + adresseDao.getById(1).getRue());
//		System.out.println("\t > Code postal : " + adresseDao.getById(1).getCodePostal());
//		System.out.println("\t > Ville : " + adresseDao.getById(1).getVille());
//		System.out.println("\t > Personne : " + adresseDao.getById(1).getAdministrateur());
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
//
//		
//		/* A un Etudiant */
//		EtudiantDaoImpl etudiantDaoImpl = new EtudiantDaoImpl();
//		
//		Etudiant etudiant =  etudiantDaoImpl.getById(1);
//		
//		Adresse attribuerAdresseEtudiant = new Adresse(etudiant);
//		
//		adresseDao.attribuerAdresse(adresseDao.getById(2).getIdAdresse(), attribuerAdresseEtudiant);
//		
//		System.out.println("Attribuer Adresse ++++++++++++++++++++++++++++++++++++++++++++++++++");
//		System.out.println("\t > Rue : " + adresseDao.getById(2).getRue());
//		System.out.println("\t > Code postal : " + adresseDao.getById(2).getCodePostal());
//		System.out.println("\t > Ville : " + adresseDao.getById(2).getVille());
//		System.out.println("\t > Personne : " + adresseDao.getById(2).getEtudiant());
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
//		
//		
//		/* A un Enseignant */
//		EnseignantDaoImpl enseignantDaoImpl = new EnseignantDaoImpl();
//		
//		Enseignant enseignant =  enseignantDaoImpl.getById(3);
//		
//		Adresse attribuerAdresseEnseignant = new Adresse(enseignant);
//		
//		adresseDao.attribuerAdresse(adresseDao.getById(3).getIdAdresse(), attribuerAdresseEnseignant);
//		
//		System.out.println("Attribuer Adresse ++++++++++++++++++++++++++++++++++++++++++++++++++");
//		System.out.println("\t > Rue : " + adresseDao.getById(3).getRue());
//		System.out.println("\t > Code postal : " + adresseDao.getById(3).getCodePostal());
//		System.out.println("\t > Ville : " + adresseDao.getById(3).getVille());
//		System.out.println("\t > Personne : " + adresseDao.getById(3).getEnseignant());
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
//		
//		/* Générique */
//		
//		Etudiant etudiantG =  null;
//		
//		Administrateur administrateurG =  administrateurDaoImpl.getById(4);
//	
//		Enseignant enseignantG = null;
//		
//		Adresse attribuerAdresse = new Adresse(etudiantG, administrateurG, enseignantG);
//		
//		adresseDao.attribuerAdresse(adresseDao.getById(10).getIdAdresse(), attribuerAdresse);
//		
//		System.out.println("Attribuer Adresse ++++++++++++++++++++++++++++++++++++++++++++++++++");
//		System.out.println("\t > Personne : " + adresseDao.getById(10).toString());
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
	}// Fin du main

}// Fin de la classe
