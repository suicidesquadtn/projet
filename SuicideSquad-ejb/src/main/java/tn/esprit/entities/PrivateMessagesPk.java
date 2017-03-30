package tn.esprit.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PrivateMessagesPk
 *
 */
@Embeddable
public class PrivateMessagesPk implements Serializable {

	
	private static final long serialVersionUID = 1L;
    private int userId;
    private int userIdReceive;
	public PrivateMessagesPk() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		result = prime * result + userIdReceive;
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
		PrivateMessagesPk other = (PrivateMessagesPk) obj;
		if (userId != other.userId)
			return false;
		if (userIdReceive != other.userIdReceive)
			return false;
		return true;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserIdReceive() {
		return userIdReceive;
	}
	public void setUserIdReceive(int userIdReceive) {
		this.userIdReceive = userIdReceive;
	}
   
}
