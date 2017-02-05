package Impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import Spec.Contact;
import Spec.ContactManager;
import Spec.FutureMeeting;
import Spec.Meeting;
import Spec.PastMeeting;

public class ContactManagerImpl implements ContactManager {
	Calendar calobj;
	

	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		int ID = (int)(Math.random()*500 + 1000); //Creates a random, positive integer for the ID
		FutureMeetingImpl fm = new FutureMeetingImpl(contacts, ID, date);
		makeNewFolder();
		 
	      try {
	         FileOutputStream fileOut = new FileOutputStream("/Users/simonaugustus/Documents/workspace/PiJ - cw3/./serialized/newfuturemeeting.out");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(fm);
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
		return  ID;
	}
	
	@Override
	public PastMeeting getPastMeeting(int id) {
		
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
	
	public Calendar currentDate(){
		 return calobj = Calendar.getInstance();
	}
	
	public void makeNewFolder(){
		File currentFolder = new File(".");
        File workingFolder = new File(currentFolder, "serialized");
        if (!workingFolder.exists()) {
            workingFolder.mkdir();
            System.out.println("Made the folder");
        } else{
        	System.out.println("Folder already exists. Saving to... ");
        }
        System.out.println(workingFolder.getAbsolutePath());
    }
}
	
	

