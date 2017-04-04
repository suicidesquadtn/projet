package tn.esprit.services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Attribution;
import tn.esprit.entities.Badge;
import tn.esprit.entities.Users;

/**
 * Session Bean implementation class ManageAttribution
 */
@Stateless
@LocalBean
public class ManageAttribution implements ManageAttributionLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
    public ManageAttribution() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void AffectUserBadge(Users u, Badge b) {
		
		String requete = "select a from Attribution a where a.attributionId.userId=:us and a.attributionId.badgeId=:ba" ;
		Attribution a=em.createQuery(requete, Attribution.class).setParameter("us", u.getUserId()).setParameter("ba", b.getBadgeId()).getSingleResult();
		Date date=new Date();
	    a.setDateAttribution(date);
	    em.merge(a);
	}

	@Override
	public void RemoveUserBadge(Users u, Badge b) {
		String requete = "select a from Attribution a where a.attributionId.userId=:us and a.attributionId.badgeId=:ba" ;
		Attribution a=em.createQuery(requete, Attribution.class).setParameter("us", u.getUserId()).setParameter("ba", b.getBadgeId()).getSingleResult();
		em.remove(em.merge(a));
	}

	@Override
	public List<Badge> getAllbadges() {
		try
		{
			Query query = em.createQuery("select b from Badge b");
			return query.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Badge findbadgeById(int badgeId) {
		try
		{
			return em.find(Badge.class, badgeId);
		}
		catch(Exception e)
		{}
		return null;
	}

	@Override
	public List<Attribution> findattributionBybadge(Badge badge) {
		return em.createQuery("select a from Attribution a where a.badge=:x", Attribution.class)
				.setParameter("x", badge)
				.getResultList();
	}

	@Override
	public List<Users> getAllusers() {
		try
		{
			Query query = em.createQuery("select u from Users u");
			return query.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Users finduserById(int UserId) {
		try
		{
			return em.find(Users.class, UserId);
		}
		catch(Exception e)
		{}
		return null;
	}

	@Override
	public List<Attribution> findattributionByuser(Users users) {
		return em.createQuery("select a from Attribution a where a.users=:x", Attribution.class)
				.setParameter("x", users)
				.getResultList();
	}

	@Override
	public List<Attribution> getAllattribution() {
		Query query=em.createQuery("select a from Attribution a");
		return query.getResultList();
	}

	@Override
	public void CreateAttribution(Attribution a) {
		try {
			em.persist(a);
			System.out.println("Badge added");
		} catch (Exception e) {
			System.out.println("Error while adding a badge");
		}
	}

	@Override
	public void DeleteAttribution(Attribution a) {
		em.remove(em.merge(a));
		
	}

}
