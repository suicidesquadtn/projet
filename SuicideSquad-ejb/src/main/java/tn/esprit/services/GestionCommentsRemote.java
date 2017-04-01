package tn.esprit.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Comments;

@Remote
public interface GestionCommentsRemote {
	public List<Comments> findAll();
	public boolean Add(Comments c);
	public boolean Delete(Comments c);
	public boolean Update(Comments c);
	Comments findById(int id);
}
