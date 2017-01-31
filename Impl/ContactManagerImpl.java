package Impl;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import Spec.Contact;
import Spec.ContactManager;
import Spec.FutureMeeting;
import Spec.Meeting;
import Spec.PastMeeting;

public class ContactManagerImpl implements ContactManager {
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		int ID = (int)(Math.random()*500 + 1000);
		
		return ID;
	}
	
	@Override
	public PastMeeting getPastMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Meeting getMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Meeting> getMeetingListOn(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<PastMeeting> getPastMeetingListFor(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public PastMeeting addMeetingNotes(int id, String text) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int addNewContact(String name, String notes) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Set<Contact> getContacts(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Contact> getContacts(int... ids) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	
	
}
