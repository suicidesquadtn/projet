package tn.esprit.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Moderator;
import tn.esprit.entities.Section;
import tn.esprit.entities.Users;

@Local
public interface gestionModerateurLocal {

	public List<Users> findAll();
	public Boolean update(Users u);
	
	public Boolean delete(Users u);
}
