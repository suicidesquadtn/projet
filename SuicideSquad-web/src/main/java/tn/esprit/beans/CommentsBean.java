package tn.esprit.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;
import tn.esprit.entities.Comments;
import tn.esprit.entities.Subject;
import tn.esprit.services.GestionCommentsLocal;

@ManagedBean(name ="commentsbean")
@SessionScoped
public class CommentsBean {

	@EJB
	GestionCommentsLocal gcl;
	
	private List<Comments> comments=new ArrayList<Comments>();
	private Comments comment = new Comments();
	private List<Comments> filtredComments;
	private Comments selectedComment;
	private boolean formDisplayed = false;
    @ManagedProperty(value="#{gamesbean}")
	private GamesBean gb;
	
	private Date date;
	
	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public Comments getComment() {
		return comment;
	}

	public void setComment(Comments comment) {
		this.comment = comment;
	}
	

	public List<Comments> getFiltredComments() {
		return filtredComments;
	}

	public void setFiltredComments(List<Comments> filtredComments) {
		this.filtredComments = filtredComments;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}
	
	
public String doSaveOrUpdate(){
		date = new Date();	
		comment.setDateCreaton(date);
		gcl.Update(comment);
		comment=new Comments();
		comments = gcl.findBySubject(gb.getSujet());
		init();
		formDisplayed = false;	
		 return null;
	}
public String doUpdate(Subject s){
	Date d=new Date();
	comment.setDateCreaton(d);
	gcl.Update(comment);
	comments = gcl.findBySubject(gb.getSujet());
	gb.gotoInfopage(s);
	init();
    return "infogame?faces-redirect=true";
}
	public void doNew(){
		comment = new Comments();
		formDisplayed = true;
	}
	
	public void doCancel(){
		
		comment = new Comments();
		comments=gcl.findBySubject(gb.getSujet());
		formDisplayed = false;
		
	}
	
	public String doDelete(Comments comment){
		System.out.println("je suis la");
		System.out.println("je veux supprimer "+comment.getContent());
		gcl.Delete(comment);
		comments = gcl.findBySubject(gb.getSujet());
		init();
		 return null;
	}
	public String GoToUpdatePage(Comments comment){
		this.comment=comment;
		comments = gcl.findBySubject(gb.getSujet());
		init();
		return "editcomment";
		
	}
	
	public void onRowSelect(SelectEvent event){
		formDisplayed = true;
	}
	
	@PostConstruct
	public void init(){
		comment=new Comments();
		System.out.println("je suis la je suis la je suis la ::::"+gb.getSujet().getSubjectId());
		comments = gcl.findBySubject(gb.getSujet());
		comments.forEach(System.out::println);
		
	}

	public Date getDate() {
		return date;
		
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Comments getSelectedComment() {
		return selectedComment;
	}

	public void setSelectedComment(Comments selectedComment) {
		this.selectedComment = selectedComment;
	}

	public GamesBean getGb() {
		return gb;
	}

	public void setGb(GamesBean gb) {
		this.gb = gb;
	}
}
