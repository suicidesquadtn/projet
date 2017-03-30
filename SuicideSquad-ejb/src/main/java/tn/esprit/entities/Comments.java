package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comments
 *
 */
@Entity
public class Comments implements Serializable {

	private CommentsPk commentsId;
	private int aime;
	private String content;

	private static final long serialVersionUID = 1L;
    Users users;
	public Comments() {
		super();
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@EmbeddedId
	public CommentsPk getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(CommentsPk commentsId) {
		this.commentsId = commentsId;
	}

	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName="UserId",updatable=false,insertable=false)
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public int getAime() {
		return aime;
	}
	public void setAime(int aime) {
		this.aime = aime;
	}
	
	

	
	
   
}
