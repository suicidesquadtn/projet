package tn.esprit.entities;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import tn.esprit.entities.Users;

/**
 * Entity implementation class for Entity: Moderator
 *
 */
@Entity

public class Moderator extends Users implements Serializable {

	private int reward;
	private static final long serialVersionUID = 1L;
	 private Section section;
	 private List<Reclamation> reclamations ;
		
	public Moderator() {
		super();
	}

	
	/*
	@OneToMany(mappedBy="moderateur",fetch=FetchType.EAGER)
	public List<Section> getSectionmodere() {
		return sectionmodere;
	}

	public void setSectionmodere(List<Section> sectionmodere) {
		this.sectionmodere = sectionmodere;
	}*/

	


	public int getReward() {
		return reward;
	}

	


	public Moderator(int userId, String login, String pwd, Blob imageUser, Date date, String mail, String nom) {
		super(userId, login, pwd, imageUser, date, mail, nom);
		// TODO Auto-generated constructor stub
	}


	public void setReward(int reward) {
		this.reward = reward;
	}
	@ManyToOne
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@OneToMany(mappedBy="moderator",fetch=FetchType.EAGER)
	public List<Reclamation> getReclamations() {
		return reclamations;
	}


	public void setReclamations(List<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}




}
