package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Comments;
import tn.esprit.entities.Subject;

/**
 * Session Bean implementation class GestionComments
 */
@Stateless
@LocalBean
public class GestionComments implements GestionCommentsRemote, GestionCommentsLocal {

	@PersistenceContext
    EntityManager em;
    public GestionComments() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Comments> findAll() {
		return em.createQuery("select c from Comments c ORDER BY c.aime DESC",Comments.class)
				.getResultList();
		
	}

	@Override
	public boolean Add(Comments c) {
		try {
			em.persist(c);	
          return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Delete(Comments c) {
		try
		{
			em.remove(em.merge(c));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean Update(Comments c) {
		try
		{
			em.merge(c);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public Comments findById(int id) {
		try
		{
			return em.find(Comments.class, id);
		}
		catch(Exception e)
		{}
		return null;
	}

	@Override
	public List<Comments> findBySubject(Subject s) {
		return em.createQuery("select c from Comments c  where c.sujet=:s",Comments.class).setParameter("s", s).getResultList();
	}

}
