package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comments
 *
 */
@Entity
public class Comments implements Serializable {

	private int IdComments;
	private int aime;
	private String content;
	private Date DateCreaton;
	private Subject sujet;
	private Users users;
	

	private static final long serialVersionUID = 1L;
	public Comments() {
		super();
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public int getAime() {
		return aime;
	}
	public void setAime(int aime) {
		this.aime = aime;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdComments() {
		return IdComments;
	}
	public void setIdComments(int idComments) {
		IdComments = idComments;
	}
	public Date getDateCreaton() {
		return DateCreaton;
	}
	public void setDateCreaton(Date dateCreaton) {
		DateCreaton = dateCreaton;
	}
	@ManyToOne
	public Subject getSujet() {
		return sujet;
	}
	public void setSujet(Subject sujet) {
		this.sujet = sujet;
	}
	
	

	
	
   
}
