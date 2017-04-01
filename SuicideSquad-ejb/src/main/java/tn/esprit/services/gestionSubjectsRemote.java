package tn.esprit.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Subject;

@Remote
public interface gestionSubjectsRemote {
	public List<Subject> findAll();
	public boolean Add(Subject s);
	public boolean Delete(Subject s);
	public boolean Update(Subject s);
	Subject findById(int id);
	public List<Subject> findByThematic(String Thematic);
	public long countSub();

}
