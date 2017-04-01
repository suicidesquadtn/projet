package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reclamation
 *
 */
@Entity

public class Reclamation implements Serializable {

	   
	
	private int ReclamationId;
	private String Titre;
	private String Contenu;
	private Date DatePublication;
	private String Etat="Non Valide";
	//private List<Users> usersList;
	private Moderator moderator;
	private static final long serialVersionUID = 1L;

	public Reclamation() {
		super();
	}   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)   
	public int getReclamationId() {
		return this.ReclamationId;
	}

	public void setReclamationId(int ReclamationId) {
		this.ReclamationId = ReclamationId;
	}   
	public String getTitre() {
		return this.Titre;
	}

	public void setTitre(String Titre) {
		this.Titre = Titre;
	}   
	public String getContenu() {
		return this.Contenu;
	}

	public void setContenu(String Contenu) {
		this.Contenu = Contenu;
	}   
	public Date getDatePublication() {
		return this.DatePublication;
	}

	public void setDatePublication(Date DatePublication) {
		this.DatePublication = DatePublication;
	}   
	public String getEtat() {
		return this.Etat;
	}

	public void setEtat(String Etat) {
		this.Etat = Etat;
	}
	/*
	@ManyToMany
	public List<Users> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}
   */
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName="UserId",updatable=false,insertable=false)
	public Moderator getModerator() {
		return moderator;
	}
	public void setModerator(Moderator moderator) {
		this.moderator = moderator;
	}
}
