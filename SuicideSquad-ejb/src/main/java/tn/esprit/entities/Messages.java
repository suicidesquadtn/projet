package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Messages
 *
 */
@Entity

public class Messages implements Serializable {

	private MessagesPk messagesId;
	private String content;
	private Date Date;
	private Users usermessages;
	private Roomtchat roommessages;
	private static final long serialVersionUID = 1L;

	public Messages() {
		super();
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	public Date getDate() {
		return this.Date;
	}

	public void setDate(Date Date) {
		this.Date = Date;
	}
	@EmbeddedId
	public MessagesPk getMessagesId() {
		return messagesId;
	}
	public void setMessagesId(MessagesPk messagesId) {
		this.messagesId = messagesId;
	}
	@ManyToOne
	public Users getUsermessages() {
		return usermessages;
	}
	public void setUsermessages(Users usermessages) {
		this.usermessages = usermessages;
	}
	@ManyToOne
	public Roomtchat getRoommessages() {
		return roommessages;
	}
	public void setRoommessages(Roomtchat roommessages) {
		this.roommessages = roommessages;
	}
}
