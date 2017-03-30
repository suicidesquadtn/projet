package tn.esprit.beans;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

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
  
  private List<SelectItem> selectItemsForSections;
  private int selectedSectionId = -1;
  
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



public String doUpdate(){
	Date date = new Date();
	Moderator.setDate(date);
	Moderator.setSection(myService.findSectionById(selectedSectionId));
	myService.Update(Moderator);
	setVisible(false);
	init();
	return null;
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


}