package tn.esprit.beans;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;

import tn.esprit.entities.Member;
import tn.esprit.entities.Moderator;
import tn.esprit.entities.Subject;
import tn.esprit.entities.Users;
import tn.esprit.entities.statusujet;
import tn.esprit.paginator.RepeatPaginator;
import tn.esprit.services.gestionSubjectsLocal;

@ManagedBean(name ="gamesbean")
@SessionScoped
public class GamesBean {
	@EJB
	private gestionSubjectsLocal gsl;
	private String search;
	private String thematic;
	 private List<Subject> subjects=new ArrayList<Subject>();
     List<Subject> subjsFiltered= new ArrayList<Subject>();
     List<Subject> subjsFiltered2= new ArrayList<Subject>();
    private Date date=null;
	private Subject sujet= new Subject();
	private Part file;
	public String searchcat;
	private Blob updateImage;
    @ManagedProperty(value="#{myLogBean}")
	private LoginBean logbean;
    private RepeatPaginator paginator;
	public Subject getSujet() {
		return sujet;
	}

	public void setSujet(Subject sujet) {
		this.sujet = sujet;
	}
	@PostConstruct
	public void init(){ 
		System.out.println("aaaaaaaaaman ");
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		subjects=new ArrayList<Subject>();
		if(getLogbean().getU() instanceof Moderator){
			System.out.println("Moderateur");
	      subjects=gsl.findAll();
		}
		else {
			System.out.println("Autre"+getLogbean().getLogin());
			subjects=gsl.findAccepted();
		    paginator = new RepeatPaginator(this.subjects);

		}
    paginator = new RepeatPaginator(this.subjects);
    redirectTo();
	}
	public String redirectTo(){
		return "games";
	}
	public String ajoutGames() throws IOException, MessagingException, SerialException, SQLException{
      System.out.println("sasasasas");
		InputStream is=file.getInputStream();
        byte[] content = IOUtils.toByteArray(is);
        Blob blob = new javax.sql.rowset.serial.SerialBlob(content);
       sujet.setImage(blob);
		Date date = new Date();
		sujet.setReleasedate(date);

        System.out.println(sujet.getUtilisateursubject() instanceof Member);
        System.out.println(sujet.getUtilisateursubject() instanceof Moderator);
		if(sujet.getUtilisateursubject() instanceof Member){
    	   System.out.println("Membre");
    	   sujet.setStatus(statusujet.Waiting);
       }
       if(sujet.getUtilisateursubject() instanceof Moderator){
    	   System.out.println("Moderateur");   
    	   sujet.setStatus(statusujet.Accepted);
       }
		if(gsl.Add(sujet)){
			System.out.println("je suis pas un Modérateur,je suis :"+logbean.getLogin());
			init();
			return "/pages/games?faces-redirect=true";
		}
     return "null";
	}
	public String updateGames() throws IOException, SerialException, SQLException{
		if(file!=null){
	        InputStream is=file.getInputStream();
	        byte[] content = IOUtils.toByteArray(is);
	        Blob blob = new javax.sql.rowset.serial.SerialBlob(content);
	       sujet.setImage(blob);
		}
		else
			sujet.setImage(updateImage);

		if(gsl.Update(sujet)){
			return "games";
		}
     return "null";
	}
	public String goToUpdatePage(Subject sujet){
		this.sujet=sujet;
			return "updategame";
	}
	public String gotoInfopage(Subject sujet){
		this.sujet=sujet;
	    return "infogame?faces-redirect=true";
	}
	public String CreateGame(){
		sujet= new Subject();
		return "creategame?faces-redirect=true";
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
    public byte[] afficherImage(Blob image) throws SQLException{
       	return image.getBytes(1, (int) image.length());
    	
    } 
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public String doDelete(Subject s){
		gsl.Delete(s);
		init();
		 return null;
	}
	public String doUpdate(Subject s){
		gsl.Update(s);
		return "subjects";
	}
	public void FindByThemOrConsolOrDate(){
		if((thematic!=null)&&(searchcat==null)){
			System.out.println("searchtheme");
			if(date==null){System.out.println("Date null");
			subjsFiltered=subjects.stream().filter(p -> p.getThematic().toLowerCase().contains(thematic.toLowerCase())).collect(Collectors.toList());
    	    paginator = new RepeatPaginator(subjsFiltered);}
			if(date!=null){
				System.out.println("searchtheme+date");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String format = formatter.format(date);
				System.out.println(format);
				subjsFiltered =getSubjects().stream().filter(p -> p.getDate().toString().compareTo(format)==0 &&p.getThematic().toLowerCase().contains(thematic.toLowerCase())).collect(Collectors.toList());
			    paginator = new RepeatPaginator(subjsFiltered);
			}
		}
		if((searchcat!=null)&&(thematic==null)){
			if(date==null){
			System.out.println("searchcat");
			subjsFiltered =subjects.stream().filter(p -> p.getCategory().toLowerCase().contains(searchcat.toLowerCase())).collect(Collectors.toList());
    	    paginator = new RepeatPaginator(subjsFiltered);
			}
			if(date!=null){
				System.out.println("searchcat+date");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String format = formatter.format(date);
				subjsFiltered =getSubjects().stream().filter(p -> p.getDate().toString().compareTo(format)==0 &&p.getCategory().toLowerCase().contains(searchcat.toLowerCase())).collect(Collectors.toList());
			    paginator = new RepeatPaginator(subjsFiltered);
			}

		}
		if((thematic!=null)&&(searchcat!=null)){
			if(date==null){
			System.out.println("les deux");
			subjsFiltered=subjects.stream().filter(p -> p.getThematic().toLowerCase().contains(thematic.toLowerCase())&& p.getCategory().toLowerCase().contains(searchcat.toLowerCase())).collect(Collectors.toList());
    	    paginator = new RepeatPaginator(subjsFiltered);
			}
			if(date!=null){
				System.out.println("les trois");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String format = formatter.format(date);
				System.out.println(format);
				subjsFiltered =getSubjects().stream().filter(p -> p.getDate().toString().compareTo(format)==0 &&p.getThematic().toLowerCase().contains(thematic.toLowerCase())&& p.getCategory().toLowerCase().contains(searchcat.toLowerCase())).collect(Collectors.toList());
			    paginator = new RepeatPaginator(subjsFiltered);
			}

		}
		if((thematic==null)&&(searchcat==null)){
			System.out.println("j'ai ni thematic ni catégorie");
			if(date==null){
				System.out.println("datenull");
			init();
			}
			if(date!=null){
				System.out.println("Date Seulement");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String format = formatter.format(date);
				System.out.println(format);
				subjsFiltered =getSubjects().stream().filter(p -> p.getDate().toString().compareTo(format)==0).collect(Collectors.toList());
			    paginator = new RepeatPaginator(subjsFiltered);
			}
		}
	}
    public String getPathVideo(){
        String relativeWebPath = "/resources/video/";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return servletContext.getRealPath(relativeWebPath);
    }

	public String getThematic() {
		return thematic;
	}

	public void setThematic(String thematic) {
		this.thematic = thematic;
	}
	  public void attrListener(ActionEvent event){

			thematic = (String)event.getComponent().getAttributes().get("thematic");
		  }

	public RepeatPaginator getPaginator() {
		return paginator;
	}

	public void setPaginator(RepeatPaginator paginator) {
		this.paginator = paginator;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	public void filterList(){
		System.out.println("IN Filter");
		System.out.println(search);
		if(search!=null){
	    if((thematic!=null)||(searchcat!=null)){
	    	subjsFiltered2 =subjsFiltered.stream().filter(p -> p.getName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
		    paginator = new RepeatPaginator(subjsFiltered2);
	    }
	    else{
		subjsFiltered =getSubjects().stream().filter(p -> p.getName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
		    paginator = new RepeatPaginator(subjsFiltered);
	    }
		}
		else {
			init();
		}
	}
//	public void FilterDate(){
//		System.out.println(date);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String format = formatter.format(date);
//		System.out.println(format);
//		subjsFiltered =getSubjects().stream().filter(p -> p.getDate().toString().compareTo(format)==0).collect(Collectors.toList());
//	    paginator = new RepeatPaginator(subjsFiltered);
//	}
//	

	public Blob getUpdateImage() {
		return updateImage;
	}

	public void setUpdateImage(Blob updateImage) {
		this.updateImage = updateImage;
	}

	public LoginBean getLogbean() {
		return logbean;
	}

	public void setLogbean(LoginBean logbean) {
		this.logbean = logbean;
	}
	public void accept(Subject s){
		s.setStatus(statusujet.Accepted);
		gsl.Update(s);
	}
	public void refuser(Subject s){
		s.setStatus(statusujet.Refused);
		gsl.Update(s);
	}
	public boolean statusujet(Users u){
		if(u instanceof Moderator){
			return true;
		}
		return false;
	}


	public String getSearchcat() {
		return searchcat;
	}

	public void setSearchcat(String searchcat) {
		this.searchcat = searchcat;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

