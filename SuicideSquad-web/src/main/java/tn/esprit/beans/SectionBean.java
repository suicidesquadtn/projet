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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;

import tn.esprit.entities.Section;

import tn.esprit.services.GestionSectionLocal;



@ManagedBean
@SessionScoped
public class SectionBean {
	
private Section section = new Section();
private List<Section> sections = new ArrayList<Section>();
private boolean visible=false;
private Part file;
private Blob UploadImage;

@EJB
GestionSectionLocal myService;

public Section getSection() {
	return section;
}

public void setSection(Section section) {
	this.section = section;
}

public List<Section> getSections() {
	return sections;
}

public void setSections(List<Section> sections) {
	this.sections = sections;
}

public String ajouter()
{
	myService.Create(section);
init();
return null ;
	}

public String modifier() throws IOException, SerialException, SQLException
{ 
	if(file!=null){
    InputStream is=file.getInputStream();
    byte[] content = IOUtils.toByteArray(is);
    Blob blob = new javax.sql.rowset.serial.SerialBlob(content);
    section.setImage(blob);}
	else{
		section.setImage(UploadImage);
	}
	Date date=new Date();
	section.setReleasedate(date);
	myService.Update(section);
    setVisible(false);
	init();
	return "sections";
	}

public String supprimer(Section sec){
	 myService.Delet(sec);
	 init();
	 return null ;
 }

public String update(Section sec){
	section=sec;
      return "updatesection";
 }
@PostConstruct
public void init(){ 
sections=new ArrayList<Section>();
List<Section> sec=myService.getAll();
if(sec!=null){
	for(Section s:sec){
		if(s instanceof Section){
			sections.add((Section) s);
		}
	}
}
}

public boolean isVisible() {
	return visible;
}

public void setVisible(boolean visible) {
	this.visible = visible;
}

public String initialiser(){
	section=new Section();
	setVisible(true);
	return null;
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
public String CreateSection(){
	section=new Section();
	return "updatesection";
}

public Blob getUploadImage() {
	return UploadImage;
}

public void setUploadImage(Blob uploadImage) {
	UploadImage = uploadImage;
}
}
