package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Users
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Users implements Serializable {

	
	private int UserId;
	private String login;
	private String pwd;
	private String image;
	private Date date;
	private String mail;
	private String nom;
	private List<ClaimSubject> claimsubjectsuser;
//	private List <ClaimComments> claimcommentsuser;
	private List<Attribution> attributions;
	private List<Visibility> visibilityusers;
	private List<Subject> subjectscomment;
    private List<Subject> subjects;
    private Users userpm;
    private List<Users> utilisateurspm;
    private List<Comments>comments;
	private static final long serialVersionUID = 1L;

	public Users() {
		super();
	}   
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getUserId() {
		return this.UserId;
	}

	public void setUserId(int UserId) {
		this.UserId = UserId;
	}   
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}   
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}   
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Users(String login, String pwd, String image, Date date, String mail, String nom) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.image = image;
		this.date = date;
		this.mail = mail;
		this.nom = nom;
	}
	@OneToMany(mappedBy="utilisateur",fetch=FetchType.EAGER)
	public List<Subject> getSubjectscomment() {
		return subjectscomment;
	}
	public void setSubjectscomment(List<Subject> subjectscomment) {
		this.subjectscomment = subjectscomment;
	}
	@OneToMany(mappedBy="utilisateursubject",fetch=FetchType.EAGER)
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@OneToMany(mappedBy="userattribution",fetch=FetchType.EAGER)
	public List<Attribution> getAttributions() {
		return attributions;
	}
	public void setAttributions(List<Attribution> attributions) {
		this.attributions = attributions;
	}
	@OneToMany(mappedBy="userclaim",fetch=FetchType.EAGER)
	public List<ClaimSubject> getClaimsubjectsuser() {
		return claimsubjectsuser;
	}
	public void setClaimsubjectsuser(List<ClaimSubject> claimsubjectsuser) {
		this.claimsubjectsuser = claimsubjectsuser;
	}
	@OneToMany(mappedBy="utilisateur",fetch=FetchType.EAGER)
	public List<Visibility> getVisibilityusers() {
		return visibilityusers;
	}
	public void setVisibilityusers(List<Visibility> visibilityusers) {
		this.visibilityusers = visibilityusers;
	}
//	@OneToMany(mappedBy="utilisateurclaim",fetch=FetchType.EAGER)	
//	public List <ClaimComments> getClaimcommentsuser() {
//		return claimcommentsuser;
//	}
//	public void setClaimcommentsuser(List <ClaimComments> claimcommentsuser) {
//		this.claimcommentsuser = claimcommentsuser;
//	}
	@ManyToOne
	@JoinColumn(name="user_fk")
	public Users getUserpm() {
		return userpm;
	}
	public void setUserpm(Users userpm) {
		this.userpm = userpm;
	}
	@OneToMany(mappedBy="userpm",fetch=FetchType.EAGER)
	public List<Users> getUtilisateurspm() {
		return utilisateurspm;
	}
	public void setUtilisateurspm(List<Users> utilisateurspm) {
		this.utilisateurspm = utilisateurspm;
	}
	@OneToMany(mappedBy="users")
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	
	
}
