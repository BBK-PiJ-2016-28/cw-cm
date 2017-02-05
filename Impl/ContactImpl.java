package Impl;

import java.io.Serializable;

import Spec.Contact;

public class ContactImpl implements Contact, Serializable {
	private int ID;
	private String name;
	private String notes;
	
	

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNotes(String note) {
		this.notes = notes + " New note: " + note;
		
	}

}
