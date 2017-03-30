package tn.esprit.beans;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
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

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

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
	private Subject sujet= new Subject();
	private Part file;
	private UploadedFile video;
	private Blob updateImage;
    @ManagedProperty(value="#{myLogBean}")
	private LoginBean logbean;
    private RepeatPaginator paginator;
    public static final String BASE_PATH = "file:\\C:\\Users\\Anas\\Desktop\\SuicideSquad\\SuicideSquad\\SuicideSquad-web\\src\\main\\uploadvideo\\";
	public Subject getSujet() {
		return sujet;
	}

	public void setSujet(Subject sujet) {
		this.sujet = sujet;
	}
	@PostConstruct
	public void init(){ 
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		subjects=new ArrayList<Subject>();
		if(getLogbean().getU() instanceof Moderator){
			System.out.println("Moderateur");
	      List<Subject> subjs=gsl.findAll();
	  	if(subjs!=null){
			for(Subject s:subjs){
				subjects.add(s);
			}
		}
		}
		else {
			System.out.println("Autre"+getLogbean().getLogin());
			List<Subject> subjs=gsl.findAccepted();
			if(subjs!=null){
				for(Subject s:subjs){
					subjects.add(s);
				}
			}
		}
	
    paginator = new RepeatPaginator(this.subjects);

	}

	public String ajoutGames() throws IOException, MessagingException, SerialException, SQLException{
        InputStream is=file.getInputStream();
        byte[] content = IOUtils.toByteArray(is);
        Blob blob = new javax.sql.rowset.serial.SerialBlob(content);
       sujet.setImage(blob);
		Date date = new Date();
		
		sujet.setReleasedate(date);
		System.out.println(video.getFileName());

		if(video.getFileName().contains("java.io.BufferedInputStream")==true){
		Path folder=Paths.get("C:/Users/Anas/Desktop/SuicideSquad/SuicideSquad/SuicideSquad-web/src/main/uploadvideo");

		String extension = FilenameUtils.getExtension(video.getFileName());
		String filename = FilenameUtils.getBaseName(video.getFileName()); 
	    InputStream input2 = video.getInputstream();
	    String w=input2.toString();
	    w.replaceAll("-.*?.", "");
	    System.out.println(w);
	    Path newfile = Files.createTempFile(folder, filename + "-", "." + extension);
	    Files.copy(input2, newfile, StandardCopyOption.REPLACE_EXISTING);
        sujet.setVideo(newfile.getFileName().toString());}
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
			init();
			return "games";
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
	    return "infogame";
	}
	public String CreateGame(){
		sujet= new Subject();
		return "creategame";
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
	public void findByThematic(){
		System.out.println("no");
        System.out.println(thematic);

      List<Subject> sujet2=gsl.findByThematic(thematic);
      subjects.clear();
  	if(sujet2!=null){
		for(Subject s:sujet2){
			subjects.add(s);
			}
	    paginator = new RepeatPaginator(this.subjects);

		}	}

	public UploadedFile getVideo() {
		return video;
	}

	public void setVideo(UploadedFile video) {
		this.video = video;
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
		List<Subject> subjs2 =getSubjects().stream().filter(p -> p.getName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
	    paginator = new RepeatPaginator(subjs2);}
		else {
			init();
		}
	}
	public String afficherVideo(String video){
		return BASE_PATH+video;
	}

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
	public boolean statusujet(Users u){
		if(u instanceof Moderator){
			return true;
		}
		return false;
	}
}
