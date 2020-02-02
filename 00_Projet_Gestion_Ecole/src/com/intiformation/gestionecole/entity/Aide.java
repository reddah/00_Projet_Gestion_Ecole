package com.intiformation.gestionecole.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author IN-BR-020
 *
 */

@Entity(name = "aide")
@Table(name = "aides")
@NamedQueries({ @NamedQuery(name = "Aide_getAll", query = "SELECT a FROM aide a"),
				@NamedQuery(name = "Aide_getAllPage", query = "SELECT a.page FROM aide a"),
				@NamedQuery(name = "Aide_getAllContenu", query = "SELECT a.contenu FROM aide a")})

public class Aide implements Serializable {
	/* ____________ Props ____________ */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Page")
	private int idPage;
	@Column(name = "Page")
	private String page;
	@Column(name = "Contenu")
	private String contenu;

	/* ____________ Ctor ____________ */

	public Aide() {
		super();
	}

	public Aide(String page, String contenu) {
		super();
		this.page = page;
		this.contenu = contenu;
	}
	
	public Aide(int idPage, String page, String contenu) {
		super();
		this.idPage = idPage;
		this.page = page;
		this.contenu = contenu;
	}

	@Override
	public String toString() {
		return "Aide [idPage=" + idPage + ", page=" + page + ", contenu=" + contenu + "]";
	}

	/* ____________ getters/setters ____________ */
	public int getIdPage() {
		return idPage;
	}

	public void setIdPage(int idPage) {
		this.idPage = idPage;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
}// Fin de la classe Aide
