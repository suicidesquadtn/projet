package tn.esprit.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Visibility
 *
 */
@Entity

public class Visibility implements Serializable {

	private VisibilityPk visibilityId;
	private Users utilisateur;
	private Subject subject;
	private boolean rights;
	private static final long serialVersionUID = 1L;

	public Visibility() {
		super();
	}   
	public boolean getRights() {
		return this.rights;
	}

	public void setRights(boolean rights) {
		this.rights = rights;
	}
	@EmbeddedId
	public VisibilityPk getVisibilityId() {
		return visibilityId;
	}
	public void setVisibilityId(VisibilityPk visibilityId) {
		this.visibilityId = visibilityId;
	}
	@ManyToOne
	public Users getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Users utilisateur) {
		this.utilisateur = utilisateur;
	}
	@ManyToOne
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
   
}
