package tn.esprit.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Comments;
import tn.esprit.entities.Subject;


@Local
public interface GestionCommentsLocal {
	public List<Comments> findAll();
	public List<Comments> findBySubject(Subject s);
	public boolean Add(Comments c);
	public boolean Delete(Comments c);
	public boolean Update(Comments c);
	Comments findById(int id);
	
}
