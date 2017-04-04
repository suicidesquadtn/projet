package tn.esprit.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Attribution;
import tn.esprit.entities.Badge;
//import tn.esprit.entities.Categ;

@Local
public interface ManageBadgesLocal {
	public void Createbadge(Badge b);
	public void Updatebadge(Badge b);
	public void Deletebadge(Badge b);
	public Badge findById(int badgeId);
	public List<Badge> findByName(String name);
	public List<Badge> getAll();
	void CreateAttribution(Attribution a);
	
}
