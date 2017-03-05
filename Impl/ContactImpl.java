package Impl;

import java.io.Serializable;

import Spec.Contact;

public class ContactImpl implements Contact, Serializable {
	private int ID;
	private String name;
	private String notes;
	

	public void setID(int iD) {
		ID = iD;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public void addNotes(String note) {
		this.notes = notes + " New note: " + note;
		
	}

}
