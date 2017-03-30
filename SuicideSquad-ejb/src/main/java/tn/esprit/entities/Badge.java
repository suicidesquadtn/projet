package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Badge
 *
 */
@Entity

public class Badge implements Serializable {

	
	private int badgeId;
	private String image;
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
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
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
   
}
