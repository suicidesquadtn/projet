//package tn.esprit.entities;
//
//import java.io.Serializable;
//import java.lang.String;
//import java.util.Date;
//
//import javax.persistence.*;
//
///**
// * Entity implementation class for Entity: ClaimComments
// *
// */
//@Entity
//
//public class ClaimComments implements Serializable {
///*
//	private ClaimCommentsPk claimcommentsId;/*feha userid
//	private String content;
//	private Date date;
//	private Users utilisateurclaim;/*User Id
//	private Comments commentclaim;UserId+subjectId
//	private static final long serialVersionUID = 1L;
//
//	public ClaimComments() {
//		super();
//	}   
//	public String getContent() {
//		return this.content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}   
//	public Date getDate() {
//		return this.date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//	@EmbeddedId
//	public ClaimCommentsPk getClaimcommentsId() {
//		return claimcommentsId;
//	}
//	public void setClaimcommentsId(ClaimCommentsPk claimcommentsId) {
//		this.claimcommentsId = claimcommentsId;
//	}
//	@ManyToOne
//	@JoinColumn(name="userId", referencedColumnName="userId",
//	updatable=false, insertable=false)
//	public Users getUtilisateurclaim() {
//		return utilisateurclaim;
//	}
//	public void setUtilisateurclaim(Users utilisateurclaim) {
//		this.utilisateurclaim = utilisateurclaim;
//	}
//	@ManyToOne
//    @JoinColumns({
//        @JoinColumn(name="userId", referencedColumnName="userId",updatable=false, insertable=false),
//        @JoinColumn(name="subjectId", referencedColumnName="subjectId",updatable=false, insertable=false)
//    })
//	public Comments getCommentclaim() {
//		return commentclaim;
//	}
//	public void setCommentclaim(Comments commentclaim) {
//		this.commentclaim = commentclaim;
//	}
//   */
//}
