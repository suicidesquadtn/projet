package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Attribution;
import tn.esprit.entities.Badge;
//import tn.esprit.entities.Categ;

/**
 * Session Bean implementation class ManageBadges
 */
@Stateless
@LocalBean
public class ManageBadges implements ManageBadgesRemote, ManageBadgesLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
    public ManageBadges() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void Createbadge(Badge b) {
		try {
			em.persist(b);
			System.out.println("Badge added");
		} catch (Exception e) {
			System.out.println("Error while adding a badge");
		}
	}

	@Override
	public void Updatebadge(Badge b) {
		try{
			em.merge(b);
			System.out.println("Badge Modified");
		}catch(Exception e){
			System.out.println("Error while modifing a badge");
		}
	}

	@Override
	public void Deletebadge(Badge b) {
		em.remove(em.merge(b));
		
	}

	@Override
	public Badge findById(int badgeId) {
		return em.find(Badge.class, badgeId);
	}

	@Override
	public List<Badge> getAll() {
		  Query query=em.createQuery("select b from Badge b");
			return query.getResultList();
	}

	@Override
	public List<Badge> findByName(String name) {
		Query query=em.createQuery("select b from Badge b where b.name=name");
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
	
}
