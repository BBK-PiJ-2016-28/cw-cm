package Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import Impl.ContactImpl;
import Impl.ContactManagerImpl;
import Impl.MeetingImpl;
import Spec.Contact;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class ContactManagerTest {
	
	@Test
	public void testaddFutureMeeting(){
		Calendar date = Calendar.getInstance();
		date.set(2017, 02, 27);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(date.getTime());
		System.out.println(formatted);

		Contact mark = new ContactImpl();
		Set<Contact> cons = new HashSet<Contact>();
		cons.add(mark);
		ContactManagerImpl cm = new ContactManagerImpl();
		cm.addFutureMeeting(cons, date);
	}

	

}
