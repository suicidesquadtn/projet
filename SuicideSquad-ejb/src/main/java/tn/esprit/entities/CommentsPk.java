package tn.esprit.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
 * Entity implementation class for Entity: CommentsPk
 *
 */
@Embeddable

public class CommentsPk implements Serializable {

    private int userId;
    private int subjectId;
    private Date dateCreation;
	private static final long serialVersionUID = 1L;

	public CommentsPk() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
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
		CommentsPk other = (CommentsPk) obj;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (subjectId != other.subjectId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
   
}
