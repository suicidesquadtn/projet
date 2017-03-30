package tn.esprit.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AttributionPk
 *
 */
@Embeddable

public class AttributionPk implements Serializable {

	private int badgeId;
	private int userId;
	private static final long serialVersionUID = 1L;
    
	public AttributionPk() {
		super();
	}

	public int getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + badgeId;
		result = prime * result + userId;
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
		AttributionPk other = (AttributionPk) obj;
		if (badgeId != other.badgeId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
   
}
