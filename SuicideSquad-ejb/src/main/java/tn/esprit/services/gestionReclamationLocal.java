package tn.esprit.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Reclamation;



@Local
public interface gestionReclamationLocal {
	public Boolean add(Reclamation u);
	public Boolean update(Reclamation u);
	public Reclamation findById(int id);
	public Boolean delete(Reclamation u);
	public List<Reclamation> findAll();
}
