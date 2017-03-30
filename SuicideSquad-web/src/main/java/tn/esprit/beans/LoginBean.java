package tn.esprit.beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
private Boolean isLoggedIn=false;
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
 if(u!=null){
		System.out.println("in verifier true");
	 setIsLoggedIn(true); 
	 return "index"; }
 else
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

 

}
