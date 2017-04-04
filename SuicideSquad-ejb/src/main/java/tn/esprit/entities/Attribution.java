package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Attribution
 *
 */
@Entity

public class Attribution implements Serializable {

	private AttributionPk attributionId;
	private Date DateAttribution;
	private Badge badgeattribution;
	private Users userattribution;
	private static final long serialVersionUID = 1L;

	public Attribution() {
		super();
	}  
	@Temporal(TemporalType.DATE)
	public Date getDateAttribution() {
		return this.DateAttribution;
	}

	public void setDateAttribution(Date DateAttribution) {
		this.DateAttribution = DateAttribution;
	}
	@EmbeddedId
	public AttributionPk getAttributionId() {
		return attributionId;
	}
	public void setAttributionId(AttributionPk attributionId) {
		this.attributionId = attributionId;
	}
	@ManyToOne
	@JoinColumn(name="badgeId", referencedColumnName="badgeId",updatable=false, insertable=false)
	public Badge getBadgeattribution() {
		return badgeattribution;
	}
	public void setBadgeattribution(Badge badgeattribution) {
		this.badgeattribution = badgeattribution;
	}
	@ManyToOne
	@JoinColumn(name="userId", referencedColumnName="userId",
	updatable=false, insertable=false)
	public Users getUserattribution() {
		return userattribution;
	}
	public void setUserattribution(Users userattribution) {
		this.userattribution = userattribution;
	}
	public Attribution(Date dateAttribution, Badge badgeattribution, Users userattribution) {
		super();
		DateAttribution = dateAttribution;
		this.badgeattribution = badgeattribution;
		this.userattribution = userattribution;
		this.attributionId = new AttributionPk(badgeattribution.getBadgeId(), userattribution.getUserId());
	}
   
}
