package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Moderator;
import tn.esprit.entities.Section;
import tn.esprit.entities.Users;

/**
 * Session Bean implementation class gestionModerateur
 */
@Stateless
public class gestionModerateur implements gestionModerateurLocal {

	@PersistenceContext
    EntityManager em;
	/**
     * Default constructor. 
     */
    public gestionModerateur() {
        // TODO Auto-generated constructor stub
    }

	
	@Override
	public Boolean update(Users u) {
		try{
			em.merge(u);
			return true;
		}catch(Exception e){
			
		}
		return false;
	}

	

	@Override
	public Boolean delete(Users u) {
		try{
			em.remove(em.merge(u));
			return true;
		}catch(Exception e){
			
		}
		return false;
	}
	@Override
	public List<Users> findAll() {
		 Query query=em.createQuery("select u from Users u ORDER BY  u.reward DESC");
			return query.getResultList();
	}

}
