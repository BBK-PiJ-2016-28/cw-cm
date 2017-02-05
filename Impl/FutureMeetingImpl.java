package Impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import Spec.Contact;
import Spec.FutureMeeting;

public class FutureMeetingImpl implements FutureMeeting, Serializable {
	private int ID;
	private Calendar date;
	private Set<Contact> contacts;

	/**
	 * 
	 */
	public FutureMeetingImpl(Set<Contact> contacts, int ID, Calendar date){
		if(contacts.isEmpty()){
			try{
				throw new IllegalArgumentException();
			} catch (IllegalArgumentException emptrySetOfContacts){
				System.out.println("You must provide at least one contact for this meeting");
			}
		} else {
			this.contacts.addAll(contacts);
			this.date = date;
			this.ID = ID;
			System.out.println("New future meeting added for " + date + ". Your ID for this meeting is " + ID);
		}
		
	}

	@Override
	public int getId() {	
		return ID;
	}

	@Override
	public Calendar getDate() {
		return date;
	}

	@Override
	public Set<Contact> getContacts() {
		return contacts;
	}

	
	
	
}
