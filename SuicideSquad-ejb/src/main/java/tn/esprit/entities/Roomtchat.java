package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: RoomTchat
 *
 */
@Entity

public class Roomtchat implements Serializable {

	
	private int roomId;
	private String type;
	private String password;
	private int limit;
	private Users utilisateur;
	private List<Messages> messagesroom;
	private static final long serialVersionUID = 1L;

	public Roomtchat() {
		super();
	}   
	@Id    
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getRoomId() {
		return this.roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public int getLimit() {
		return this.limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	@ManyToOne
	public Users getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Users utilisateur) {
		this.utilisateur = utilisateur;
	}
	@OneToMany(mappedBy="roommessages",fetch=FetchType.EAGER)
	public List<Messages> getMessagesroom() {
		return messagesroom;
	}
	public void setMessagesroom(List<Messages> messagesroom) {
		this.messagesroom = messagesroom;
	}
   
}
