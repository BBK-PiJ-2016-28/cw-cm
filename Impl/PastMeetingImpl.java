package Impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Spec.Contact;
import Spec.PastMeeting;

public class PastMeetingImpl implements PastMeeting, Serializable {
	private int ID;
	private Calendar date;
	private Set<Contact> contacts = new HashSet();
	private String notes;

	public PastMeetingImpl(){
		
	}
	public PastMeetingImpl(Set<Contact> contacts, int ID, Calendar date, String notes){
		if(contacts.isEmpty()){
			try{
				throw new IllegalArgumentException();
			} catch (IllegalArgumentException emptrySetOfContacts){
				System.out.println("You must provide at least one contact for this meeting");
			}
		} else {
			Iterator<Contact> it = contacts.iterator();
			while(it.hasNext()){
				this.contacts.add(it.next());
			}
			System.out.println("This past meeting contains " + contacts.size() + " contacts");
			this.date = date;
			this.ID = ID;
			this.notes = notes;
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String formatted = format1.format(date.getTime());
			System.out.println("New past meeting added for " + formatted + ". Your ID for this meeting is " + ID);
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
	
	public Contact getContact(Contact contact){
		Contact someone = new ContactImpl();
		for(int x = 0; x < contacts.size(); x++){
			if (someone == contact){
				return someone;
			}
		}
		
		return null;
		
	}

	@Override
	public String getNotes() {
		return this.notes;
	}
	
	public void setNotes(String notes){
		this.notes = this.notes + "\t New notes: " + notes;
	}

}
