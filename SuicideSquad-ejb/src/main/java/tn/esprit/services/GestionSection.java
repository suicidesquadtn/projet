package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Section;

/**
 * Session Bean implementation class GestionSection
 */
@Stateless
@LocalBean
public class GestionSection implements GestionSectionRemote, GestionSectionLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
    public GestionSection() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean Create(Section sec) {
		try
		{
			em.persist(sec);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean Update(Section sec) {
		try
		{
			em.merge(sec);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean Delet(Section sec) {
		try
		{
			em.remove(em.merge(sec));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<Section> getAll() {
		try
		{
			Query query = em.createQuery("select u from Section u");
			return query.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
