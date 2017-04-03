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
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((moderators == null) ? 0 : moderators.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((releasedate == null) ? 0 : releasedate.hashCode());
		result = prime * result + sectionId;
		result = prime * result + ((subjectssection == null) ? 0 : subjectssection.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Section other = (Section) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (moderators == null) {
			if (other.moderators != null)
				return false;
		} else if (!moderators.equals(other.moderators))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (releasedate == null) {
			if (other.releasedate != null)
				return false;
		} else if (!releasedate.equals(other.releasedate))
			return false;
		if (sectionId != other.sectionId)
			return false;
		if (subjectssection == null) {
			if (other.subjectssection != null)
				return false;
		} else if (!subjectssection.equals(other.subjectssection))
			return false;
		return true;
	}
   
	
}
