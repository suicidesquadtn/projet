package tn.esprit.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.entities.Member;
import tn.esprit.entities.Users;


import tn.esprit.services.gestionUtilisateursLocal;


@ManagedBean(name="MembreBean")
@ViewScoped
public class MembreBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users u = new Users();
	private Member m=new Member();
	private List<Member> ms = new ArrayList<Member>();
	
	  private boolean visible=false;

	@EJB  
	gestionUtilisateursLocal gmlocal;
	


	public void doRegistre(){
		gmlocal.Create(u);
	}

	
	
	public Member getM() {
		return m;
	}
	public void setM(Member m) {
		this.m = m;
	}
	public List<Member> getMs() {
		return ms;
	}
	public void setMs(List<Member> ms) {
		this.ms = ms;
	}
	@PostConstruct
	public void init(){ 
		ms=new ArrayList<Member>();
	List<Users> users=gmlocal.getAll();
	
	if(users!=null){
		for(Users u:users){
			if(u instanceof Member){
			
				ms.add((Member) u);
			
			}
		}
	}
}
	

	
	public String doUpdate(){
		gmlocal.Update(m);
		setVisible(false);
		init();
		return null;
	}
	public String initialiser(){
		m=new Member();
		setVisible(true);
		return null;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Users getU() {
		return u;
	}

	public void setU(Users u) {
		this.u = u;
	}
	

}
