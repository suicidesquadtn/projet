package tn.esprit.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Badge;

@Remote
public interface ManageBadgesRemote {
	public void Createbadge(Badge b);
	public void Updatebadge(Badge b);
	public void Deletebadge(Badge b);
	public Badge findById(int badgeId);
	public List<Badge> findByName(String name);
	
	public List<Badge> getAll();

}
