
package tn.esprit.beans;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;

import tn.esprit.entities.Moderator;
import tn.esprit.entities.Section;
import tn.esprit.entities.Users;
import tn.esprit.services.gestionUtilisateursLocal;
 
@ManagedBean(name="formateurBean")
@ViewScoped
public class UsersBean {
  private Moderator Moderator=new Moderator();
  private List<Moderator> Moderators=new ArrayList<Moderator>();
  private List<Section> sections = new ArrayList<Section>();
  private String nbrComments;
  private List<SelectItem> selectItemsForSections;
  private int selectedSectionId = -1;
  private Part file;
  private Blob UploadImage;
  @EJB
  gestionUtilisateursLocal myService;
  
  
  public List<Section> getSections() {
	return sections;
}
public void setSections(List<Section> sections) {
	this.sections = sections;
}
private boolean visible=false;

  public Moderator getModerator() {
		return Moderator;
	}
	public void setModerator(Moderator moderator) {
		Moderator = moderator;
	}
	public List<Moderator> getModerators() {
		return Moderators;
	}
	public void setModerators(List<Moderator> moderators) {
		Moderators = moderators;
	}
	
public String doDelete(Users u){
	myService.Delete(u);
	init();
	 return null;
}

@PostConstruct
	public void init(){ 
	
	List<Section> sections = myService.getAllSections();
	setNbrComments(myService.bestUser().getNom());
	selectItemsForSections = new ArrayList<SelectItem>(sections.size());
	for(Section section:sections){
		selectItemsForSections.add(new SelectItem(section.getSectionId(),section.getNom()));
	}
	Moderators=new ArrayList<Moderator>();
	List<Users> users=myService.getAll();
	if(users!=null){
		for(Users u:users){
			if(u instanceof Moderator){
				Moderators.add((Moderator) u);
			}
		}
	}
}



public String doUpdate() throws IOException, SerialException, SQLException{
	if(file!=null){
	    InputStream is=file.getInputStream();
	    byte[] content = IOUtils.toByteArray(is);
	    Blob blob = new javax.sql.rowset.serial.SerialBlob(content);
	    Moderator.setImageUser(blob);
	    }
		else{
			Moderator.setImageUser(UploadImage);
		}
	
	Date date = new Date();
	Moderator.setDate(date);
	Moderator.setSection(myService.findSectionById(selectedSectionId));
	myService.Update(Moderator);
	setVisible(false);
	init();
	return ("Moderator");
}
public String initialiser(){
	Moderator=new Moderator();
	selectedSectionId = -1;
	setVisible(true);
	return null;
}

public boolean isVisible() {
	return visible;
}

public void setVisible(boolean visible) {
	this.visible = visible;
}




public int getSelectedSectionId() {
	return selectedSectionId;
}
public void setSelectedSectionId(int selectedSectionId) {
	this.selectedSectionId = selectedSectionId;
}
public List<SelectItem> getSelectItemsForSections() {
	return selectItemsForSections;
}
public void setSelectItemsForSections(List<SelectItem> selectItemsForSections) {
	this.selectItemsForSections = selectItemsForSections;
}
public String getNbrComments() {
	return nbrComments;
}
public void setNbrComments(String nbrComments) {
	this.nbrComments = nbrComments;
}
public Part getFile() {
	return file;
}
public void setFile(Part file) {
	this.file = file;
}
public Blob getUploadImage() {
	return UploadImage;
}
public void setUploadImage(Blob uploadImage) {
	UploadImage = uploadImage;
}
public byte[] afficherImage(Blob image) throws SQLException{
   	return image.getBytes(1, (int) image.length());
}


}
