package tn.esprit.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Section;

@Remote
public interface GestionSectionRemote {
	public boolean Create(Section sec);
	public boolean Update(Section sec);
	public boolean Delet(Section sec);
	public List<Section> getAll();
}
