package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comments
 *
 */
@Entity

public class Comments implements Serializable {

	private CommentsPk commentsId;
	private String content;
	private List <ClaimComments> commentsclaim;
	private static final long serialVersionUID = 1L;
 
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
	@OneToMany(mappedBy="commentclaim",fetch=FetchType.EAGER)
	public List <ClaimComments> getCommentsclaim() {
		return commentsclaim;
	}
	public void setCommentsclaim(List <ClaimComments> commentsclaim) {
		this.commentsclaim = commentsclaim;
	}
   
}
