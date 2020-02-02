package com.intiformation.gestionecole.tool;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class JpaUtil {
	
	private static EntityManager entityManager;
	private static String pu_name = "00_Projet_Gestion_Ecole";
	/**
	 * récup une entityManager <br/>
	 */
	public static EntityManager getInstance() {
		if (entityManager == null) {
			
			try {
				entityManager = Persistence.createEntityManagerFactory(pu_name).createEntityManager();
				
			} catch (PersistenceException e) {
				System.out.println("La création de l'entityManger a échouée");
				e.printStackTrace();
			}// Fin du catch
			
			
		}// fin du if
		return entityManager;
	}
}// Fin de la classe 