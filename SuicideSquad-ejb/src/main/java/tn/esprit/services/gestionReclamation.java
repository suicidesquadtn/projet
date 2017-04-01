package tn.esprit.services;


import java.text.Normalizer;
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
	@Override
	public boolean filtercontenu(String chaine) {

		boolean contenuides =false;

		String[] Dictionaire ={"MERDE","NIGGA","NULL","BASTERD","NEGRO"};
		
		String normalized = Normalizer.normalize(chaine, Normalizer.Form.NFD); 
	    String propre = normalized.replaceAll("[\u0300-\u036F]", "");
	    String upercase = propre.toUpperCase();
	    
	    String[] espace = upercase.split("\\s");

	    
	    for(String i : Dictionaire ){
	    	for(String j : espace ){
	    		if(i.equals(j)){
	    			
	    			contenuides = true;
	    			
	    			//System.out.println("yesssssssssssssssssssssssssssssss");
	    			break;
	    		}
	    	}
	    }
	    
	   
		return contenuides;
	}

	@Override
	public void test(Reclamation u) {
		
		if(update(u)==true){
		
			String qr ="SELECT t from Reclamation t ORDER BY reclamationId DESC ";
			Query query = em.createQuery(qr);
			List<Reclamation>  t = (List<Reclamation>) query.getResultList();
			
		    Query gg = em.createNativeQuery("UPDATE reclamation SET userId=1");
		  //  gg.setParameter(1,t.get(0).getReclamationId() );
		   // gg.setParameter(2, iduser);
		    gg.executeUpdate();
		
		}
		else{
			System.out.println("noooooooo");
		}
		
	}
}
