package tn.esprit.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Comments;


@Local
public interface GestionCommentsLocal {
	public List<Comments> findAll();
	public boolean Add(Comments c);
	public boolean Delete(Comments c);
	public boolean Update(Comments c);
	Comments findById(int id);
	
}
