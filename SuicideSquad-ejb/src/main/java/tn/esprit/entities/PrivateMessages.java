package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PrivateMessages
 *
 */
@Entity

public class PrivateMessages implements Serializable {

	private PrivateMessagesPk privatemessagesId;
	private String content;
	private String file;
	private Type type;
	private static final long serialVersionUID = 1L;

	public PrivateMessages() {
		super();
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}   

	@EmbeddedId
	public PrivateMessagesPk getPrivatemessagesId() {
		return privatemessagesId;
	}
	public void setPrivatemessagesId(PrivateMessagesPk privatemessagesId) {
		this.privatemessagesId = privatemessagesId;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
   
}
