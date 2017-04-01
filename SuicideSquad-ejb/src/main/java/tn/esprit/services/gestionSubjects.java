package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Subject;

/**
 * Session Bean implementation class gestionSubjects
 */
@Stateless
public class gestionSubjects implements gestionSubjectsRemote, gestionSubjectsLocal {
    @PersistenceContext
    EntityManager em;
    /**
     * Default constructor. 
     */
    public gestionSubjects() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public boolean Add(Subject s) {
		try {
			em.persist(s);	
          return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<Subject> findAll() {
		  Query query=em.createQuery("select s from Subject s");
		return query.getResultList();
	}
	@Override
	public List<Subject> findAccepted() {
		  Query query=em.createQuery("select s from Subject s where s.status='1'");
		return query.getResultList();
	}
	@Override
	public boolean Delete(Subject s) {
		try
		{
			em.remove(em.merge(s));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	@Override
	public boolean Update(Subject s) {
		try
		{
			em.merge(s);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	@Override
	public Subject findById(int id) {
		try
		{
			return em.find(Subject.class, id);
		}
		catch(Exception e)
		{}
		return null;
	}
	@Override
	public List<Subject> findByThematic(String Thematic) {
		List<Subject> subjects = (List<Subject>) em
				.createQuery("SELECT s FROM Subject s  WHERE s.thematic like :thematic").setParameter("thematic", Thematic).getResultList();
		return subjects;
	}
	@Override
	public long countSub(){
		long query=(long) em.createQuery("SELECT count(s) FROM Subject s").getSingleResult();
		return query;
	}
}
