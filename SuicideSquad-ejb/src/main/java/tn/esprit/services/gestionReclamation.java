package tn.esprit.services;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Reclamation;
import tn.esprit.entities.Users;

/**
 * Session Bean implementation class gestionReclamation
 */
@Stateless
public class gestionReclamation implements gestionReclamationLocal {

	 @PersistenceContext
	    EntityManager em;
    /**
     * Default constructor. 
     */
    public gestionReclamation() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Boolean add(Reclamation u) {
		try{
			em.persist(u);
			return true;
		}catch(Exception e){
			
		}
		return false;
	}

	@Override
	public Boolean update(Reclamation u) {
		try{
			em.merge(u);
			return true;
		}catch(Exception e){
			
		}
		return false;
	}

	@Override
	public Reclamation findById(int id) {
		return em.find(Reclamation.class, id);
		}
	@Override
	public Boolean delete(Reclamation u) {
		try{
			em.remove(em.merge(u));
			return true;
		}catch(Exception e){
			
		}
		return false;
	}

	@Override
	public List<Reclamation> findAll() {
		  Query query=em.createQuery("select u from Reclamation u");
			return query.getResultList();
		}

}
