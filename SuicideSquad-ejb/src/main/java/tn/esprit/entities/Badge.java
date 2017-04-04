package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Blob;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Badge
 *
 */
@Entity

public class Badge implements Serializable {

	
	private int badgeId;
	private String name;
	private Blob image;
	private String description;
	
	private List<Attribution> attributions;

	private static final long serialVersionUID = 1L;

	public Badge() {
		super();
		}   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getBadgeId() {
		return this.badgeId;
	}

	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}   
   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@OneToMany(mappedBy="badgeattribution",fetch=FetchType.EAGER)
	public List<Attribution> getAttributions() {
		return attributions;
	}
	public void setAttributions(List<Attribution> attributions) {
		this.attributions = attributions;
	}

	public Blob getImage() {
		return image;
	}
	
	public void setImage(Blob image) {
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Badge(String name, Blob image, String description) {
		super();
		this.name = name;
		this.image = image;
		this.description = description;
	}
	
	@PreRemove
	public void preRemove() {
		for (Attribution a : attributions)
			a.setBadgeattribution(null);
	}
   
}
