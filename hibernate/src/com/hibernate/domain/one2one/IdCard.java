package com.hibernate.domain.one2one;

import java.util.Date;

public class IdCard {
	private int id;
	private Person person;
	private Date lifetime;
	
	public Date getLifetime() {
		return lifetime;
	}
	public void setLifetime(Date lifetime) {
		this.lifetime = lifetime;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	
}
