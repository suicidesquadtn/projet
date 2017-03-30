package tn.esprit.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.entities.Users;
import tn.esprit.services.gestionUtilisateursLocal;

@ManagedBean
@ViewScoped
public class RegisterBean implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Users u=new Users();
@EJB
gestionUtilisateursLocal myservices;

public void doRegistre(){
	myservices.Create(u);
}

public Users getU() {
	return u;
}

public void setU(Users u) {
	this.u = u;
}

}
