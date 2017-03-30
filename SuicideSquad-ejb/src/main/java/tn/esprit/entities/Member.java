package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;
import tn.esprit.entities.Users;

/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity

public class Member extends Users implements Serializable {

	
	private int Grade;
	private int nbrParticipatin;
	private boolean Subscriber;
	private static final long serialVersionUID = 1L;

	public Member() {
		super();
	}   
	 
	
	public int getGrade() {
		return Grade;
	}


	public void setGrade(int grade) {
		Grade = grade;
	}


	public boolean isSubscriber() {
		return Subscriber;
	}


	public void setSubscriber(boolean subscriber) {
		Subscriber = subscriber;
	}


	public Member(String login, String pwd, String image, Date date, String mail, String nom) {
		super(login, pwd, image, date, mail, nom);
		// TODO Auto-generated constructor stub
	}


	public int getNbrParticipatin() {
		return nbrParticipatin;
	}


	public void setNbrParticipatin(int nbrParticipatin) {
		this.nbrParticipatin = nbrParticipatin;
	}
	
   
}
