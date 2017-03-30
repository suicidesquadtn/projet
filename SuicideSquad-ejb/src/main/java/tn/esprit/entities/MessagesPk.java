package tn.esprit.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MessagesPk
 *
 */
@Embeddable

public class MessagesPk implements Serializable {

	
	private static final long serialVersionUID = 1L;
    private int roomId;
    private int userId;
	public MessagesPk() {
		super();
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
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
		result = prime * result + roomId;
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
		MessagesPk other = (MessagesPk) obj;
		if (roomId != other.roomId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
   
}
