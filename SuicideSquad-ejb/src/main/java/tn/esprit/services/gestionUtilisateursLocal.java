package tn.esprit.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Moderator;
import tn.esprit.entities.Section;
import tn.esprit.entities.Users;

@Local
public interface gestionUtilisateursLocal {
	public boolean Create(Users u);
	public boolean Update(Users u);
	public boolean Delete(Users u);
	public Users findById(int id);
	public List<Users> getAll();
	public Users authentificate(String login,String pwd);
	public Section findSectionById(int id);
	public List<Moderator> findModeratorsBySections(Section section);
	public List<Section> getAllSections();

}
