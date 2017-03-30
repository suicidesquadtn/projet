package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Section
 *
 */
@Entity

public class Section implements Serializable {

	
	private int sectionId;
	private String nom;
	private List<Moderator> moderators;
	private List<Subject> subjectssection;
	private Blob image;
	private Date releasedate;
	private static final long serialVersionUID = 1L;

	public Section() {
		super();
	}   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}   
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	@ManyToMany(fetch=FetchType.EAGER)
	public List<Moderator> getModerators() {
		return moderators;
	}
	public void setModerators(List<Moderator> moderators) {
		this.moderators = moderators;
	}
	@OneToMany(mappedBy="section",fetch=FetchType.EAGER)
	public List<Subject> getSubjectssection() {
		return subjectssection;
	}
	public void setSubjectssection(List<Subject> subjectssection) {
		this.subjectssection = subjectssection;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public Date getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}
   
}
