package tn.esprit.services;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Member;
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
	
	@Override
	public Users bestUser() {
		List<Users> users = em.createQuery("SELECT u FROM Users u ", Users.class)
		.getResultList();
		long max=0;
		Users bestUser = new Users();
		for (Users user : users) {
			long count =0;
// 		long discution = (long) em.createQuery("SELECT count(d.user.id) FROM User u Join u.discutions d where u.id=?1 " ).setParameter(1,user.getId())
// 				.getSingleResult();
		long comment = (long) em.createQuery("SELECT count(i.users.userId) FROM Users u Join u.comments  i where u.userId=?1 " ).setParameter(1,user.getUserId())
				.getSingleResult();
// 		long rating = (long) em.createQuery("SELECT count(i.id) FROM User u Join u.rating r JOIN r.ratingId i where u.id=?1 " ).setParameter(1,user.getId())
// 			.getSingleResult();
//		count = discution+comment+carpool ;
		count = comment;
		if (count>max){
			max=count;
			bestUser=user;
		}
		}
		return bestUser;
	}

	
	@Override
	//@Schedule(hour="*",minute="*",second="*/20")
	public void grade() {
		List<Users> users = em.createQuery("SELECT u FROM Users u ", Users.class)
		.getResultList();

		for (Users user : users) {
		
		int id=user.getUserId();
		
			long count =0;
		long comment = (long) em.createQuery("SELECT count(i.users.userId) FROM Users u Join u.comments  i where u.userId=?1 " ).setParameter(1,id)
				.getSingleResult();
	
		long aimes = (long) em.createQuery("SELECT sum(c.users.aime) FROM Users u Join u.comments  i where u.userId=?1 " ).setParameter(1,id)
				.getSingleResult(); 
		
		
		long subject = (long) em.createQuery("SELECT count(s.utilisateur) FROM Subject s  where s.utilisateur.userId=?1 " ).setParameter(1,id)
				.getSingleResult();
		
		
		Member m = em.createQuery("SELECT u FROM Member u where u.userId=?1 " ,Member.class).setParameter(1,id)
				.getSingleResult();
count= aimes+subject+comment-m.getNbrParticipatin();
if((count>1)&&(m.getGrade()<5)){
	m.setGrade(m.getGrade()+1);
	m.setNbrParticipatin((int)count+m.getNbrParticipatin());
	em.merge(m);
	Member m2 = em.createQuery("SELECT u FROM Member u where u.userId=?1 " ,Member.class).setParameter(1,id)
			.getSingleResult();
	System.out.println(m2.getNom());
	System.out.println("---------------------------"+aimes+"---------------------");
	
}}
		System.out.println("**************************************************************");
	}
	@Override
public long nbrComments(int id){
	 long comment = (long) em.createQuery("SELECT count(i.users.userId) FROM Users u Join u.comments  i where u.userId=?1 " ).setParameter(1,id)
			.getSingleResult();
	 return comment;
}
	@Override
public long nbrSujets(int id){
		long subject = (long) em.createQuery("SELECT count(s.utilisateur) FROM Subject s  where s.utilisateur.userId=?1 " ).setParameter(1,id)
				.getSingleResult();
	 return subject;
}
	
	
	@Override
public long nbrLikes(int id){
		long aimes = (long) em.createQuery("SELECT sum(i.aime) FROM Users u Join u.comments  i where u.userId=?1 " ).setParameter(1,id)
				.getSingleResult(); 
		System.out.println("test ************" + aimes +"**************");
	 return aimes;
}
	
// les commentaires de tous les users
	@Override
	public long countUsers() {
		long query = (long) em.createQuery("SELECT count(u) FROM Users u")
				.getSingleResult();
				return query;
	}	
	@Override
	public long countComments() {
		long query = (long) em.createQuery("SELECT count(c) FROM comments c")
				.getSingleResult();
				return query;
	}	
	
	@Override
	public Moderator bestModerator() {
		List<Moderator> Moderators = em.createQuery("SELECT u FROM Moderator u ", Moderator.class)
				.getResultList();
				long max=0;
				Moderator bestModerat = new Moderator();
				for (Moderator moderator : Moderators) {
					long count =0;
				long nbrRec = (long) em.createQuery("SELECT count(i.moderator.userId) FROM Moderator u JOIN u.reclamations i where u.userId=?1").setParameter(1,moderator.getUserId())
						.getSingleResult();
				System.out.println("iciiiiiiiiiiiiiiiiiii"+ nbrRec);
				
				count = nbrRec;
				if (count>max){
					max=count;
					bestModerat=moderator;
				}
				}
				return bestModerat;
			}
}
