package Test;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import Impl.ContactImpl;
import Impl.ContactManagerImpl;


public class ContactManagerTest {
	

	public static void main(String[] args) {
		int year = 2017;
		int month = 03;
		int day = 27;
		Calendar date = Calendar.getInstance();
		date.set(year, month, day);
		ContactImpl mark = new ContactImpl();
		Set cons = new HashSet<ContactImpl>();
		cons.add(mark);
		ContactManagerImpl cm = new ContactManagerImpl();
		System.out.println(cm.addFutureMeeting(cons, date));

	}

}
