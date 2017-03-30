package tn.esprit.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ClaimPk
 *
 */
@Embeddable

public class ClaimSubjectPk implements Serializable {

	
	private static final long serialVersionUID = 1L;
    private int subjectId;
    private int userId;
	public ClaimSubjectPk() {
		super();
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
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
		result = prime * result + subjectId;
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
		ClaimSubjectPk other = (ClaimSubjectPk) obj;
		if (subjectId != other.subjectId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
   
}
