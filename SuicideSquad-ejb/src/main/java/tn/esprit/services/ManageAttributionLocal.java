package tn.esprit.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Attribution;
import tn.esprit.entities.Badge;
import tn.esprit.entities.Users;

@Local
public interface ManageAttributionLocal {
	public void AffectUserBadge(Users u,Badge b);
	public void CreateAttribution(Attribution a);
	public void RemoveUserBadge(Users u,Badge b);
	public void DeleteAttribution(Attribution a);
	public List<Attribution> getAllattribution();
	public List<Badge> getAllbadges();
	public Badge findbadgeById(int badgeId);
	public List<Attribution> findattributionBybadge(Badge badge);
	public List<Users> getAllusers();
	public Users finduserById(int UserId);
	public List<Attribution> findattributionByuser(Users users);
	
}
