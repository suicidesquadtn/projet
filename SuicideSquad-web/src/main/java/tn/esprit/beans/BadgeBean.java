package tn.esprit.beans;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import tn.esprit.entities.Attribution;
import tn.esprit.entities.AttributionPk;
import tn.esprit.entities.Badge;
//import tn.esprit.entities.Categ;
import tn.esprit.services.ManageBadgesLocal;

@ManagedBean(name="badgeBean")
@ViewScoped
public class BadgeBean {
			
	private StreamedContent image;
	private Part file;
	private String n;
	private String n1;
	private List<Badge> test ;
	private List<Badge> test1 ;
	private Badge badge = new Badge();	
	private List<Badge> badges = new ArrayList<Badge>();
	
	@EJB
	ManageBadgesLocal myService;
	private boolean visible=false;
	
	
	
		
	public String doDelete(Badge badge){
		myService.Deletebadge(badge);
		init();
		 return null;
	}
	public String doUpdate() throws IOException, SerialException, SQLException{
		InputStream is=file.getInputStream();
        byte[] content = IOUtils.toByteArray(is);
        Blob blob = new javax.sql.rowset.serial.SerialBlob(content);
        badge.setImage(blob);
		myService.Updatebadge(badge);
		
		setVisible(false);
		init();
		return null;
	}

	public String doAdd() throws IOException, SerialException, SQLException{
		//badge=new Badge();
		InputStream is=file.getInputStream();
        byte[] content = IOUtils.toByteArray(is);
        Blob blob = new javax.sql.rowset.serial.SerialBlob(content);
        badge.setImage(blob);

		myService.Updatebadge(badge);
		setVisible(false);
		init();
		return null;
	}
	@PostConstruct
	public void init(){
		
		badge=new Badge();
		badges = myService.getAll();
		
	}
	
	public String initialiser(){
		
		setVisible(true);
		return null;
	}
	
	public void doCancel() {
		badge = new Badge();
		setVisible(false);

	}
	public void searchresult(){
		test = myService.findByName(n);
	}
	
	//public void searchforresult(){
	//	test1 = myService.findByCategory(n1);
	//}
	
	public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public byte[] afficherImage(Blob image) throws SQLException{
       	return image.getBytes(1, (int) image.length());
    	
    }
	
	
	public StreamedContent getImage() {
		return image;
	}
	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public Badge getBadge() {
		return badge;
	}
	public void setBadge(Badge badge) {
		this.badge = badge;
	}
	public List<Badge> getBadges() {
		return badges;
	}
	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	  
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	
	
	public List<Badge> getTest() {
		return test;
	}
	
	public void setTest(List<Badge> test) {
		this.test = test;
	}
	
	public String getN() {
		return n;
	}
	
	public void setN(String n) {
		this.n = n;
	}
	/**
	 * @return the n1
	 */
	public String getN1() {
		return n1;
	}
	/**
	 * @param n1 the n1 to set
	 */
	public void setN1(String n1) {
		this.n1 = n1;
	}
	/**
	 * @return the test1
	 */
	public List<Badge> getTest1() {
		return test1;
	}
	/**
	 * @param test1 the test1 to set
	 */
	public void setTest1(List<Badge> test1) {
		this.test1 = test1;
	}



	
	
	
	
}
