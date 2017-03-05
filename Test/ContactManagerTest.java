package Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import Impl.ContactImpl;
import Impl.ContactManagerImpl;
import Impl.FutureMeetingImpl;
import Impl.MeetingImpl;
import Impl.PastMeetingImpl;
import Spec.Contact;
import Spec.PastMeeting;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class ContactManagerTest {
	public Contact mark = new ContactImpl();
	public Contact jane = new ContactImpl();
	public Contact rob = new ContactImpl();
	public Set<Contact> cons = new HashSet<Contact>();
	public Set<Contact> pastCons = new HashSet<Contact>();
	public ContactManagerImpl cm = new ContactManagerImpl();
	int id;
	public Calendar date = Calendar.getInstance();
	
	
	
	@Test
	public void testaddFutureMeeting(){ //WORKING
		System.out.println("---------------------- ADDING NEW FUTURE MEETING -----------------");
		cons.add(mark);
		cm.addFutureMeeting(cons, this.date); 
		System.out.println("----------------------------------------------------------------");
	} 

	@Test
	public void addNewPastMeetingTest(){ //WORKING
		System.out.println("---------------------- ADDING NEW PAST MEETING -----------------");
		Calendar date = Calendar.getInstance();
		date.set(2016, 02, 27);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(date.getTime());
		pastCons.add(mark); 
		pastCons.add(jane);
		pastCons.add(rob);
		String text = "shiiiiit";
		cm.addNewPastMeeting(pastCons, date, text);
		System.out.println("----------------------------------------------------------------");
	} 
	
	@Test
	public void testGetPastMeeting(){ //WORKING
		pastCons.add(mark);
		pastCons.add(jane);
		Calendar date = Calendar.getInstance();
		date.set(2016, 02, 27);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(date.getTime());
		this.id = cm.addNewPastMeeting(pastCons, date, "past meeting up in the hizzzzzzzieeeee");
		System.out.println(id);
		PastMeeting pastmeeting = new PastMeetingImpl();
		pastmeeting = cm.getPastMeeting(id);
		System.out.println(pastmeeting.getNotes());
	} 
	
	@Test
	public void testGetFutureMeeting(){
		System.out.println("--------------------get future meeting test----------------------");
		System.out.println("Searching for 1106"); //HERE ADD IN THE ID OF ONE OF THE SERIALIZED OBJECTS, 
		cm.getFutureMeeting(1413);
	}
	
	@Test
	public void testGetMeeting(){ //WORKING
		System.out.println("--------------------get past meeting test----------------------");
		System.out.println("Searching for 1106"); //HERE ADD IN THE ID OF ONE OF THE SERIALIZED OBJECTS, 
		PastMeeting pastmeeting = new PastMeetingImpl();
		pastmeeting = (PastMeeting) cm.getMeeting(1235);
		System.out.println(pastmeeting.getNotes());
		System.out.println(pastmeeting.getDate());
		//System.out.println(pastmeeting.getContacts());
	}
	
	@Test
	public void testGetFutureMeetingList(){ //WORKING
		System.out.println("--------------------FUTURE MEETING LIST FOR----------------");
		cm.getFutureMeetingList(mark);
	}
	
	@Test
	public void testGetPastMeetingListFor(){
		
	}
	
	@Test
	public void testgetMeetingListOn(){ //WORKING
		System.out.println("--------------------GET MEETING LIST ON----------------------");
		System.out.println("PRINTING THE DATE " + this.date);
		cm.getMeetingListOn(this.date);
	} 
	
	
	@Test
	public void testAddMeetingNotes(){
		
	}
	
	@Test
	public void testAddNewContacts(){
		
	}
	
	@Test
	public void testGetContacts(){
		System.out.println("------------GETTING CONTACTS------------");
		int[] ids = new int[]{1235, 1488};
		cm.getContacts(ids);
	}
	
	@Test
	public void testFlush(){
		
	}

	

}
