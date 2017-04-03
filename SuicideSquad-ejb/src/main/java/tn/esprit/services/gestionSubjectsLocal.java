package tn.esprit.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Section;
import tn.esprit.entities.Subject;

@Local
public interface gestionSubjectsLocal {
	public List<Subject> findAll();
	public List<Subject> findAccepted();
	public boolean Add(Subject s);
	public boolean Delete(Subject s);
	public boolean Update(Subject s);
	Subject findById(int id);
	public List<Subject> findByThematic(String Thematic);
	List<Object[]> subjectParMois(Section sec);
}
