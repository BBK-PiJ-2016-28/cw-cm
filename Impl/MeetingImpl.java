package Impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import Spec.Contact;
import Spec.Meeting;

public abstract class MeetingImpl implements Meeting, Serializable {
	private int ID;
	private Calendar date;
	private Set<Contact> contacts;

	/**
	 * Below is constructor for meeting
	 **/

	public MeetingImpl(Set<Contact> contacts, int ID, Calendar date){
		if (ID <= 0){
			try{
				
			}catch (IllegalArgumentException e){
				System.out.println("No meeting ID has been provided for meeting date " + date);
			}
		}
			if(contacts.isEmpty()){
				try {
					
				} catch (IllegalArgumentException e){
					System.out.println("There are no contacts for this meeting on " + date);
				}
			}
	}
	
	int getIds() {
		return ID;
	}

    
    public Calendar getDate() {
		return date;
	}

    /**
     * Return the details of people that attended the meeting.
     *
     * The list contains a minimum of one contact (if there were
     * just two people: the user and the contact) and may contain an
     * arbitrary number of them.
     *
     * @return the details of people that attended the meeting.
     */
    public Set<Contact> getContacts() {
		return contacts;
	}
}
