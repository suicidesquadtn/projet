package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Moderator;
import tn.esprit.entities.Section;
import tn.esprit.entities.Users;


/**
 * Session Bean implementation class GestionUtilisateurs
 */
@Stateless
public class GestionUtilisateurs implements GestionUtilisateursRemote,gestionUtilisateursLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
	
    public GestionUtilisateurs() {
    }

	@Override
	public boolean Create(Users u) {
		try
		{
			em.persist(u);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}

	@Override
	public boolean Update(Users u) {
		try
		{
			em.merge(u);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}

	@Override
	public boolean Delete(Users u) {
		try
		{
			em.remove(em.merge(u));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public Users findById(int id) {
		try
		{
			return em.find(Users.class, id);
		}
		catch(Exception e)
		{}
		return null;
	}


	@Override
	public List<Users> getAll() {
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
	public Users authentificate(String login, String pwd) {
		try {
			Query query=em.createQuery("select u from Users u where u.login=:login and u.pwd=:pwd");
		    query.setParameter("login", login);
		    query.setParameter("pwd", pwd);
		    return (Users) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Section findSectionById(int id) {
		try
		{
			return em.find(Section.class, id);
		}
		catch(Exception e)
		{}
		return null;
	}

	@Override
	public List<Moderator> findModeratorsBySections(Section section) {
		return em
				.createQuery("select p from Moderator p where p.section=:x", Moderator.class)
				.setParameter("x", section)
				.getResultList();
		
	}

	@Override
	public List<Section> getAllSections() {
		try {
			Query query = em.createQuery("select p from Section p");
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
