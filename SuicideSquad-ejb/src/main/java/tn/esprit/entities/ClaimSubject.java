package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Claim
 *
 */
@Entity

public class ClaimSubject implements Serializable {

	private ClaimSubjectPk claimSubjectId;
	private String content;
	private Date date;
	private Users userclaim;
	private Subject subjectclaim;
	private static final long serialVersionUID = 1L;

	public ClaimSubject() {
		super();
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	@EmbeddedId
	public ClaimSubjectPk getClaimId() {
		return claimSubjectId;
	}
	public void setClaimId(ClaimSubjectPk claimId) {
		this.claimSubjectId = claimId;
	}
	@ManyToOne
	@JoinColumn(name="userId", referencedColumnName="userId",
	updatable=false, insertable=false)
	public Users getUserclaim() {
		return userclaim;
	}
	public void setUserclaim(Users userclaim) {
		this.userclaim = userclaim;
	}
	@ManyToOne
	@JoinColumn(name="subjectId", referencedColumnName="subjectId",
	updatable=false, insertable=false)
	public Subject getSubjectclaim() {
		return subjectclaim;
	}
	public void setSubjectclaim(Subject subjectclaim) {
		this.subjectclaim = subjectclaim;
	}
   
}
