package Impl;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import Spec.Contact;
import Spec.ContactManager;
import Spec.FutureMeeting;
import Spec.Meeting;
import Spec.PastMeeting;

public class ContactManagerImpl implements ContactManager {
	Calendar date;
	List<Meeting> futureMeetingArray = new ArrayList<Meeting>(); 
	List<PastMeetingImpl> pastMeetingArray = new ArrayList<PastMeetingImpl>();
	List<Contact> allContacts = new ArrayList<Contact>();
	

	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		readSerializedData();
		int ID = (int)(Math.random()*500 + 1000); //Creates a random, positive integer for the ID
		FutureMeetingImpl fm = new FutureMeetingImpl(contacts, ID, date);
		futureMeetingArray.add(fm);
		makeNewFolder();
	      try {
	         FileOutputStream fileOut = new FileOutputStream("./serialized/newfuturemeeting1.out");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(futureMeetingArray); //This is so I can use later methods down where you need to get specific meetings in list form
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	      contactAdder(contacts);
		return  ID;
	}
	
	@Override
	public PastMeeting getPastMeeting(int id) {
		readSerializedData();
		PastMeetingImpl pastMeetingReturn = new PastMeetingImpl();
		if(pastMeetingArray.size() == 0){
			System.out.println("No past meetings");
		} else{
		for(int x = 0; x < pastMeetingArray.size(); x++){
			
			pastMeetingReturn = pastMeetingArray.get(x);
			if(pastMeetingReturn.getId() == id){
				return pastMeetingReturn;
			}
		}
		}
		return pastMeetingReturn;
	}
	
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		for(int x = 0; x < futureMeetingArray.size(); x++){
			Meeting currentMeeting = futureMeetingArray.get(x);
			if(currentMeeting.getId() == id){
				return (FutureMeeting) currentMeeting;
			}
		}
		return null;
	}
	
	@Override
	public Meeting getMeeting(int id) {
		readSerializedData();
		if(futureMeetingArray.size() > 0){
		for(int x = 0; x < futureMeetingArray.size(); x++){
			System.out.println("Searching future meetings");
			Meeting futureMeeting_BY_ID = futureMeetingArray.get(x);
			if(futureMeeting_BY_ID.getId() == id){
				return futureMeeting_BY_ID;
			}
		}
		if(pastMeetingArray.size() > 0){
				for(int y = 0; y < pastMeetingArray.size(); y++){
					System.out.println("Searching past meetings");
					Meeting pastMeeting_BY_ID = pastMeetingArray.get(y);
					if(pastMeeting_BY_ID.getId() == id){
						return pastMeeting_BY_ID;
					}
				}
			  }
			}
		
		System.out.println("Unfortunately I could not find the provided ID. Please try again using a valid ID");
		return null;
	}
	
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		List<Meeting> futureMeetingList_FOR_CONTACT = new ArrayList<Meeting>();
		for(int x = 0; x < futureMeetingArray.size(); x++){
			Meeting currentMeeting = futureMeetingArray.get(x);
			if(currentMeeting.getContacts() == contact){
				futureMeetingList_FOR_CONTACT.add(currentMeeting);
			}
		}
		return futureMeetingList_FOR_CONTACT;
	}
	
	@Override
	public List<Meeting> getMeetingListOn(Calendar date) {
		List<Meeting> futureDatesMeeting = new ArrayList<Meeting>();
		for(int x = 0; x < futureMeetingArray.size(); x++){
			Meeting currentMeeting = futureMeetingArray.get(x);
			if(currentMeeting.getDate() == date){
				futureDatesMeeting.add(currentMeeting);
			}
		}
		for(int y = 0; y < futureDatesMeeting.size(); y++){
			Meeting meetingPrint = futureDatesMeeting.get(y);
			System.out.println("Your list of meetings include meeting IDs: " + meetingPrint.getId());
		}
		return futureDatesMeeting;
	}
	
	@Override
	public List<PastMeeting> getPastMeetingListFor(Contact contact) {
		List<PastMeeting> pastMeetingArray_BY_CONTACT = new ArrayList<PastMeeting>();
		for(int x = 0; x < pastMeetingArray.size(); x++){
			PastMeetingImpl pastMeeting_BY_CONTACT = pastMeetingArray.get(x);
			if(pastMeeting_BY_CONTACT.getContact(contact) == contact){
				pastMeetingArray.add(pastMeeting_BY_CONTACT);
			}
		}
		return pastMeetingArray_BY_CONTACT;
	}
	
	@Override
	public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		readSerializedData();
		int ID = (int)(Math.random()*500 + 1000); //Creates a random, positive integer for the ID
		PastMeetingImpl pm = new PastMeetingImpl(contacts, ID, date, text);
		pastMeetingArray.add(pm);
		makeNewFolder();
	      try {
	         FileOutputStream fileOut = new FileOutputStream("./serialized/newPastmeeting.out");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(pastMeetingArray); //This is so I can use later methods down where you need to get specific meetings in list form
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
		return  ID;
	}
	
	@Override
	public PastMeeting addMeetingNotes(int id, String text) {
		PastMeetingImpl currentPastMeeting = new PastMeetingImpl();
		for(int x = 0; x < pastMeetingArray.size(); x++){
			currentPastMeeting = pastMeetingArray.get(x);
			if(currentPastMeeting.getId() == id){
				currentPastMeeting.setNotes(text);
			}
		}
		return currentPastMeeting;
	}
	
	@Override
	public int addNewContact(String name, String notes) {
		ContactImpl newContact = new ContactImpl();	
		newContact.setName(name);
		newContact.setNotes(notes);
		allContacts.add(newContact);
					
	
		return 0;
	}
	
	@Override
	public Set<Contact> getContacts(String name) { 
		Contact newContact = new ContactImpl();
		Set<Contact> listOfContacts = new HashSet();
		for(int x = 0; x < allContacts.size(); x++){
			newContact = allContacts.get(x);
				if(newContact.getName() == name){
					listOfContacts.add(newContact);
				}
		}
		return listOfContacts;
	}
	
	@Override
	public Set<Contact> getContacts(int... ids){
		Contact newContact = new ContactImpl();
		Set<Contact> listOfContacts = new HashSet<Contact>();
		for(int id : ids){
			for(int x = 0; x < allContacts.size(); x++){
				newContact = allContacts.get(x);
					if(newContact.getId() == id){
						listOfContacts.add(newContact);
					}
				}
		}
		return listOfContacts;
	}
	
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	
	public Calendar currentDate(Calendar date){
		this.date = Calendar.getInstance();
		this.date = date;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(date.getTime());
		 return this.date; 
	}
	
	/**
	 * Exists to make a new folder to store the serialized arrays
	 */
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
	private void readSerializedData(){
		 try{
			  InputStream futureFile = new FileInputStream("./serialized/newfuturemeeting1.out");
			  InputStream pastFile = new FileInputStream("./serialized/newPastmeeting.out");
			  InputStream buffer = new BufferedInputStream(futureFile);
			  InputStream pastBuffer = new BufferedInputStream(pastFile);
			  ObjectInput input = new ObjectInputStream (buffer);
			  ObjectInput pastInput = new ObjectInputStream (pastBuffer);
			  //deserialize the List
			  this.futureMeetingArray = (List<Meeting>)input.readObject();
			  this.pastMeetingArray = (List<PastMeetingImpl>)pastInput.readObject();
			  System.out.println("PAST ARRAY = " + pastMeetingArray.size());
			  System.out.println("DATA LOADED");
			 } catch(ClassNotFoundException ex){
			     ex.printStackTrace();
			     System.out.println("No data");
			   } catch(IOException ex){
			     ex.printStackTrace();
			     System.out.println("No data");
			   }
			 }
			  
	
	
	/**
	 * Exists to add contacts to the global contact array [above] 
	 */
	public void contactAdder(Set<Contact> contacts){
			allContacts.addAll(contacts);
		}
	}
	
	

