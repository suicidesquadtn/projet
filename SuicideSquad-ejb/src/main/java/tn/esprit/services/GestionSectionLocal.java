package tn.esprit.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Section;

@Local
public interface GestionSectionLocal {
	public boolean Create(Section sec);
	public boolean Update(Section sec);
	public boolean Delet(Section sec);
	public List<Section> getAll();

}
