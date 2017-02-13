package Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import Impl.ContactImpl;
import Impl.ContactManagerImpl;
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
	
	
	
/**	@Test
	public void testaddFutureMeeting(){
		Calendar date = Calendar.getInstance();
		date.set(2017, 02, 27);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(date.getTime());
		cons.add(mark);
		cm.addFutureMeeting(cons, date); 
		
	} 
	
	@Test
	public void addNewPastMeetingTest(){
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
	} **/
	
	@Test
	public void testGetPastMeeting(){
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
		testGetMeeting(id);
	} 
	
	@Test
	public void testGetFutureMeeting(){
		
	}
	
	//@Test
	public void testGetMeeting(int id){
		System.out.println("------------------------------------------");
		System.out.println("Searching for " + id);
		PastMeeting pastmeeting = new PastMeetingImpl();
		pastmeeting = (PastMeeting) cm.getMeeting(id);
		System.out.println(pastmeeting.getNotes());
		
	}
	
	@Test
	public void testGetFutureMeetingList(){
		
	}
	
	@Test
	public void testGetPastMeetingListFor(){
		
	}
	
/**	@Test
	public void testgetMeetingListOn(){
		Calendar date = Calendar.getInstance();
		date.set(2017, 02, 27);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(date.getTime());
		cons.add(mark); 
		cons.add(jane);
		cons.add(rob);
		cm.addFutureMeeting(cons, date);
		cm.addFutureMeeting(cons, date);
		cm.getMeetingListOn(date);
	} **/
	
	
	@Test
	public void testAddMeetingNotes(){
		
	}
	
	@Test
	public void testAddNewContacts(){
		
	}
	
	@Test
	public void testGetContacts(){
		
	}
	
	@Test
	public void testFlush(){
		
	}

	

}
