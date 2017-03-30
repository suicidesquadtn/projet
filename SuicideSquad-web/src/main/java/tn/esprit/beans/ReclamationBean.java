
package tn.esprit.beans;


import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;


import javax.faces.bean.ViewScoped;


import tn.esprit.entities.Reclamation;

import tn.esprit.services.gestionReclamationLocal;


@ManagedBean(name="ReclamationBean")
@ViewScoped
public class ReclamationBean {
	@EJB  
	gestionReclamationLocal local;
	private List<Reclamation> Reclamations = new ArrayList<Reclamation>();
	private Reclamation u = new Reclamation(); 
	 private boolean visible=false;
	public Reclamation getU() {
		return u;
	}
	public void setU(Reclamation u) {
		this.u = u;
	}
	public String Doadd()
	{System.out.println("in!!!");
		local.add(u);
		System.out.println("succus...!!");
		return null;
	}
	public String doDelete(Reclamation u){
		local.delete(u);
		init();
		 return null;
	}
	public String doUpdate(){
		local.update(u);
		setVisible(false);
		init();
		return null;
	}
	public String doFindAllUser()
	{
		local.findAll();
		return null;
	}
	public List<Reclamation> getReclamations() {
		return Reclamations;
	}
	public void setReclamations(List<Reclamation> reclamations) {
		Reclamations = reclamations;
	}
	
	@PostConstruct
	public void init(){ 
		//Reclamations=new ArrayList<Reclamation>();
	 Reclamations= local.findAll();
	/*if(Reclamations!=null){
		for(Reclamation u:Reclamations){
			
			Reclamations.add((Reclamation) u);
			
		}
	}*/
}
	public String initialiser(){
		u=new Reclamation();
		setVisible(true);
		return null;
	}
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	 
	     
	   
}