package tn.esprit.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.spec.MGF1ParameterSpec;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;

import tn.esprit.entities.Member;
import tn.esprit.entities.Users;
import tn.esprit.services.gestionSubjectsLocal;
import tn.esprit.services.gestionUtilisateursLocal;


@ManagedBean(name="MembreBean")
@SessionScoped
public class MembreBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users u = new Users();
	private Member m;
	private List<Users>users;
	private List<Member> ms = new ArrayList<Member>();
	private long nbreC;
	private long nbrSub;
	private long nbrallSub;
	private long nbrusers;
	private long nbrallCom;
	private long nbrLikes;
	private Part file;
	  private Blob UploadImage;
	  private boolean visible=false;

	@EJB  
	gestionUtilisateursLocal gmlocal;
	@EJB
	gestionSubjectsLocal gmlo;
	
    
	


	public void doRegistre(){
		gmlocal.Create(u);
	}
	public String doDelete(Users u){
		gmlocal.Delete(u);
		init();
		 return null;
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
		setUsers(gmlocal.getAll());
		//setNbrallSub(gmlo.countSub());
		
		
		ms=new ArrayList<Member>();
	List<Users> users=gmlocal.getAll();
	m=new Member();

	setVisible(false);
	if(users!=null){
		for(Users u:users){
			if(u instanceof Member){
			
				ms.add((Member) u);
			
			}
		}
	}
}
	

	
	public void doUpdate() throws IOException, SerialException, SQLException{
		
		this.nbreC=gmlocal.nbrComments(m.getUserId());
		this.nbrSub=gmlocal.nbrSujets(m.getUserId());
		this.nbrLikes=gmlocal.nbrLikes(m.getUserId());
//		gmlocal.Update(m);
	setVisible(true);
		

	}
	 
	public String initialiser(){
		

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
	public long getNbreC() {
		return nbreC;
	}
	public void setNbreC(long nbreC) {
		this.nbreC = nbreC;
	}
	public long getNbrSub() {
		return nbrSub;
	}
	public void setNbrSub(long nbrSub) {
		this.nbrSub = nbrSub;
	}
	public long getNbrLikes() {
		return nbrLikes;
	}
	public void setNbrLikes(long nbrLikes) {
		this.nbrLikes = nbrLikes;
	}
	public String doUpdate1() throws IOException, SerialException, SQLException{
		if(file!=null){
		    InputStream is=file.getInputStream();
		    byte[] content = IOUtils.toByteArray(is);
		    Blob blob = new javax.sql.rowset.serial.SerialBlob(content);
		    m.setImageUser(blob);
		    }
			else{
				m.setImageUser(UploadImage);
			}
		Date date = new Date();
		m.setDate(date);
		
		gmlocal.Update(m);
		setVisible(false);
		init();
		return null;
	}
	public String doActiveUser() {
		if(m.isSubscriber()){
		m.setSubscriber(false);}
		else{m.setSubscriber(true);}
		gmlocal.Update(m);
		init();
		
		return "pages/dashboard/li?faces-redirect=true";}
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	public Blob getUploadImage() {
		return UploadImage;
	}
	public void setUploadImage(Blob uploadImage) {
		UploadImage = uploadImage;
	}
	public byte[] afficherImage(Blob image) throws SQLException{
	   	return image.getBytes(1, (int) image.length());
	}
	public long getNbrallSub() {
		return nbrallSub;
	}
	public void setNbrallSub(long nbrallSub) {
		this.nbrallSub = nbrallSub;
	}
	public long getNbrallCom() {
		return nbrallCom;
	}
	public void setNbrallCom(long nbrallCom) {
		this.nbrallCom = nbrallCom;
	}
	public long getNbrusers() {
		return nbrusers;
	}
	public void setNbrusers(long nbrusers) {
		this.nbrusers = nbrusers;
	}
	

}
