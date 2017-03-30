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

	
	private String Grade;
	private boolean Subscriber;
	private static final long serialVersionUID = 1L;

	public Member() {
		super();
	}   
	public String getGrade() {
		return this.Grade;
	}

	public void setGrade(String Grade) {
		this.Grade = Grade;
	}   
	public boolean getSubscriber() {
		return this.Subscriber;
	}

	public void setSubscriber(boolean Subscriber) {
		this.Subscriber = Subscriber;
	}
	public Member(String login, String pwd, String image, Date date, String mail, String nom) {
		super(login, pwd, image, date, mail, nom);
		// TODO Auto-generated constructor stub
	}
	
   
}
