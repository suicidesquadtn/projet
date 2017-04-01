package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Subject
 *
 */
@Entity

public class Subject implements Serializable {

	
	private int subjectId;
	private int rate;
	private String name;
	private String content;
	private Blob image;
	private String file;
	private String video;
	private String producer;
	private int reviews;
	private int nbrview;
	private Date Date;
	private statusujet status;
	private Date releasedate;
	private String Category;
	private String Thematic;
	private Section section;
	private Users utilisateur;
	private Users utilisateursubject;
	private List<Visibility> visibilitysubject;
	private List<ClaimSubject> claimsubjects;
	private List<Comments> commentaires;
	private static final long serialVersionUID = 1L;

	public Subject() {
		super();
	}   
	   
		@Id    
		@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}   
	@Lob
	@Column(length = 100000)
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
	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public Date getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getThematic() {
		return Thematic;
	}
	public void setThematic(String thematic) {
		Thematic = thematic;
	}
	@ManyToOne
	public Users getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Users utilisateur) {
		this.utilisateur = utilisateur;
	}
	@ManyToOne
	public Users getUtilisateursubject() {
		return utilisateursubject;
	}
	public void setUtilisateursubject(Users utilisateursubject) {
		this.utilisateursubject = utilisateursubject;
	}
	@ManyToOne
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	@OneToMany(mappedBy="subjectclaim",fetch=FetchType.EAGER)
	public List<ClaimSubject> getClaimsubjects() {
		return claimsubjects;
	}
	public void setClaimsubjects(List<ClaimSubject> claimsubjects) {
		this.claimsubjects = claimsubjects;
	}
	@OneToMany(mappedBy="subject",fetch=FetchType.EAGER)
	public List<Visibility> getVisibilitysubject() {
		return visibilitysubject;
	}
	public void setVisibilitysubject(List<Visibility> visibilitysubject) {
		this.visibilitysubject = visibilitysubject;
	}
    @Temporal(TemporalType.DATE) 
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getReviews() {
		return reviews;
	}
	public void setReviews(int reviews) {
		this.reviews = reviews;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getNbrview() {
		return nbrview;
	}

	public void setNbrview(int nbrview) {
		this.nbrview = nbrview;
	}

	public statusujet getStatus() {
		return status;
	}

	public void setStatus(statusujet status) {
		this.status = status;
	}
    @OneToMany(mappedBy="sujet")
	public List<Comments> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Comments> commentaires) {
		this.commentaires = commentaires;
	}

   
}
