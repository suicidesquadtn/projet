package tn.esprit.beans;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.sql.rowset.serial.SerialException;



import tn.esprit.entities.Member;
import tn.esprit.entities.Moderator;

import tn.esprit.entities.Users;


import tn.esprit.services.gestionUtilisateursLocal;

@ManagedBean(name="myLogBean")
@SessionScoped()
public class LoginBean {
private String login;
private String password;
private String message="";
private Users u;
private List<Moderator> Moderators=new ArrayList<Moderator>();
private List<Member> me=new ArrayList<Member>();
private Boolean isLoggedIn=false;
private boolean visible=false;
private long nbreC;
private long nbreallC;
private long nbrSub;
private long nbrLikes;
private long numberUsers;
private Users bestUser;
@EJB
gestionUtilisateursLocal myService;

public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Users getU() {
	return u;
}
public void setU(Users u) {
	this.u = u;
}
public String verifier(){
	System.out.println("in verifier");
	 u=myService.authentificate(login,password);

//	 
//	  if(u!=null){
//	 setIsLoggedIn(true); 
//	 return "index"; }
 
	if(u.isSubscriber()){
		this.setU(u);;
		
		isLoggedIn = true;
		return "index";}
	else{
		isLoggedIn =false;
		u=null;
		FacesMessage msg = new FacesMessage("Your count is not confirmed!");
		FacesContext.getCurrentInstance().addMessage("form_login:form_submit", msg);
	
	}
 
 
FacesMessage msg = new FacesMessage("bad credentials!");
FacesContext.getCurrentInstance().addMessage("form_login:form_submit", msg);
return null;
}


public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Boolean getIsLoggedIn() {
	return isLoggedIn;
}
public void setIsLoggedIn(Boolean isLoggedIn) {
	this.isLoggedIn = isLoggedIn;
}
public String logout(){
	isLoggedIn=false;
	u=null;
	return "Login";	
}

public Boolean ModerateurOrNot(Users u){

	 if (u instanceof Moderator){
	 return true;}
	 else
		return false;
 }	 

public String doUpdate() throws IOException, SerialException, SQLException{
	
	Date date = new Date();
	u.setDate(date);
	myService.Update(u);
	setVisible(false);
	init();
	return null;
}
public boolean isVisible() {
	return visible;
}

public String doDelete(Users u){
	myService.Delete(u);
	init();
	 return null;
}






@PostConstruct
public void init(){ 
setNumberUsers(myService.countUsers());
	setBestUser(myService.bestUser());
	
	Moderators=new ArrayList<Moderator>();
	List<Users> users=myService.getAll();
	if(users!=null){
		for(Users u:users){
			if(u instanceof Moderator){
				Moderators.add((Moderator) u);
			}
		}
	}
}
public List<Moderator> getModerators() {
	return Moderators;
}
public void setModerators(List<Moderator> moderators) {
	Moderators = moderators;
}
public void setVisible(boolean visible) {
	this.visible = visible;
}
public String doUpdate1() throws IOException, SerialException, SQLException{
	this.nbreC=myService.nbrComments(u.getUserId());
	this.nbrSub=myService.nbrSujets(u.getUserId());
	this.nbrLikes=myService.nbrLikes(u.getUserId());
	Date date = new Date();
	u.setDate(date);
	
	myService.Update(u);
	setVisible(false);
	init();
	return null;
}
public void init1(){ 

me=new ArrayList<Member>();
List<Users> users=myService.getAll();
if(users!=null){
	for(Users u:users){
		if(u instanceof Moderator){
			Moderators.add((Moderator) u);
		}
	}
}
}
public List<Member> getMe() {
	return me;
}
public void setMe(List<Member> me) {
	this.me = me;
}
public long getNbreC() {
	return nbreC;
}
public void setNbreC(long nbreC) {
	this.nbreC = nbreC;
}
public long getNbrLikes() {
	return nbrLikes;
}
public void setNbrLikes(long nbrLikes) {
	this.nbrLikes = nbrLikes;
}
public long getNbrSub() {
	return nbrSub;
}
public void setNbrSub(long nbrSub) {
	this.nbrSub = nbrSub;
}
public long getNumberUsers() {
	return numberUsers;
}
public void setNumberUsers(long numberUsers) {
	this.numberUsers = numberUsers;
}
public Users getBestUser() {
	return bestUser;
}
public void setBestUser(Users bestUser) {
	this.bestUser = bestUser;
}
public long getNbreallC() {
	return nbreallC;
}
public void setNbreallC(long nbreallC) {
	this.nbreallC = nbreallC;
}
public String doActiveUser() {
	if(u.isSubscriber()){
	u.setSubscriber(false);}
	else{u.setSubscriber(true);}
	myService.Update(u);
	init();
	
	return "pages/dashboard/li?faces-redirect=true";}

}
