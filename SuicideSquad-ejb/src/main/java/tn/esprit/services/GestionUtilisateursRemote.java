package tn.esprit.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Users;



@Remote
public interface GestionUtilisateursRemote {
	public boolean Create(Users u);
	public boolean Update(Users u);
	public boolean Delete(Users u);
	
	public Users findById(int id);
	public List<Users> getAll();
	
}
