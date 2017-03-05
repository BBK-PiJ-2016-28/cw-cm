package Impl;

import java.io.Serializable;

import Spec.Contact;

public class ContactImpl implements Contact, Serializable {
	private int ID;
	private String name;
	private String notes;
	
	public ContactImpl(){
		
	}
	
	public ContactImpl(String name){
		setName(name);
	}
	
	public ContactImpl(int ID, String name, String notes){
		try {
			setID(ID);
		} catch (IllegalArgumentException e) {
			System.out.println("You must provide an ID for" + name);
		}
		
		setName(name);
		setNotes(notes);
	}
	
	public ContactImpl(int ID, String name){
			try {
				setID(ID);
			} catch (IllegalArgumentException e) {
				System.out.println("You must provide an ID for" + name);
			}
		setName(name);
	}
	

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
