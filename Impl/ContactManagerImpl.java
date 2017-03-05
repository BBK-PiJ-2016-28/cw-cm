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
import java.util.Iterator;
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
	
public void printallcontacts(){
	readSerializedData();
	for (int x = 0; x < allContacts.size(); x++){
		System.out.println("size of contacts list = " + allContacts.size());
		System.out.println(allContacts.get(x).getName());
	}
		
}
	
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
	      contactAdder(contacts, ID);
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
		readSerializedData();
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
			Meeting futureMeeting_BY_ID = futureMeetingArray.get(x);
			if(futureMeeting_BY_ID.getId() == id){
				return futureMeeting_BY_ID;
			}
		}
		if(pastMeetingArray.size() > 0){
				for(int y = 0; y < pastMeetingArray.size(); y++){
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
		readSerializedData();
		List<Meeting> futureMeetingList_FOR_CONTACT = new ArrayList<Meeting>();
		Set<Contact> contacts = new HashSet<Contact>();
		List<Contact> cn = new ArrayList<Contact>();
		Contact currentContact = new ContactImpl();
		for(int x = 0; x < futureMeetingArray.size(); x++){
			Meeting futMeeting = futureMeetingArray.get(x);
			if(futMeeting.getContacts().iterator().next().getName().equals(contact.getName())){
				futureMeetingList_FOR_CONTACT.add(futMeeting);
			}
		}
		System.out.println("list has " + futureMeetingList_FOR_CONTACT.size() + " contacts");
		return futureMeetingList_FOR_CONTACT;
	}
	
	@Override
	public List<Meeting> getMeetingListOn(Calendar date) {
		readSerializedData();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(date.getTime());
		List<Meeting> meetings = new ArrayList<Meeting>();
		Meeting currentMeeting = futureMeetingArray.get(0);
		if(futureMeetingArray.size() > 0){
			for(int x = 0; x < futureMeetingArray.size(); x++){
				currentMeeting = futureMeetingArray.get(x);
				Calendar currentMeetingsDate = currentMeeting.getDate();
				if(currentMeetingsDate.equals(date)){
					System.out.println("Adding future meeting");
					meetings.add(currentMeeting);
				}
			}
		}
		if(pastMeetingArray.size() > 0){
			for (int p = 0; p < pastMeetingArray.size(); p++){
				Meeting currentMeetingPast = new PastMeetingImpl();
				if(currentMeetingPast.equals(date)){
					System.out.println("Adding past meeting");
					meetings.add(currentMeetingPast);
				}
			}
		}
		return meetings;
	}
	
	@Override
	public List<PastMeeting> getPastMeetingListFor(Contact contact) {
		readSerializedData();
		List<PastMeeting> pastMeetingList_FOR_CONTACT = new ArrayList<PastMeeting>();
		Set<Contact> contacts = new HashSet<Contact>();
		List<Contact> cn = new ArrayList<Contact>();
		Contact currentContact = new ContactImpl();
		for(int x = 0; x < pastMeetingArray.size(); x++){
			PastMeeting pasMeeting = pastMeetingArray.get(x);
			if(pasMeeting.getContacts().iterator().next().getName().equals(contact.getName())){
				pastMeetingList_FOR_CONTACT.add(pasMeeting);
			}
		}
		System.out.println("list has " + pastMeetingList_FOR_CONTACT.size() + " contacts");
		return pastMeetingList_FOR_CONTACT;
	}
	
	@Override
	public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		readSerializedData();
		int ID = (int)(Math.random()*500 + 1000); //Creates a random, positive integer for the ID
		PastMeetingImpl pm = new PastMeetingImpl(contacts, ID, date, text);
		pastMeetingArray.add(pm);
		contactAdder(contacts, ID);
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
		readSerializedData();
		PastMeetingImpl currentPastMeeting = new PastMeetingImpl();
		for(int x = 0; x < pastMeetingArray.size(); x++){
			currentPastMeeting = pastMeetingArray.get(x);
			if(currentPastMeeting.getId() == id){
				currentPastMeeting.setNotes(text);
				System.out.println(currentPastMeeting.getNotes());
			}
		}
		return currentPastMeeting;
	}
	
	@Override
	public int addNewContact(String name, String notes) {
		int ID = (int)(Math.random()*500 + 1000);
		ContactImpl newContact = new ContactImpl(ID,name, notes);	
		allContacts.add(newContact);
		return ID;
	}
	
	@Override
	public Set<Contact> getContacts(String name) { 
		readSerializedData();
		Contact newContact = new ContactImpl();
		Set<Contact> listOfContacts = new HashSet();
		System.out.println("getting contacts now");
		for(int x = 0; x < allContacts.size(); x++){
			newContact = allContacts.get(x);
				if(newContact.getName().equals(name)){
					listOfContacts.add(newContact);
				}
		}
		System.out.println(listOfContacts.size());
		return listOfContacts;
	}
	
	@Override
	public Set<Contact> getContacts(int... ids){
		readSerializedData();
		Contact newContact = new ContactImpl();
		Set<Contact> listOfContacts = new HashSet<Contact>();
		for(int idsiterator = 0; idsiterator < ids.length; idsiterator++){
		for (int x = 0; x < futureMeetingArray.size(); x++){
			Meeting fm = futureMeetingArray.get(x);
			if(fm.getId() == ids[idsiterator]){
				listOfContacts.add(fm.getContacts().iterator().next());
				System.out.println("FUTURE list for IDs has " + listOfContacts.size() + " contacts");
			}
			for (int y = 0; y < pastMeetingArray.size(); y++){
				Meeting pm = pastMeetingArray.get(y);
				if(pm.getId() == ids[idsiterator]){
					listOfContacts.add(pm.getContacts().iterator().next());
					System.out.println(" PAST list for IDs has " + listOfContacts.size() + " contacts");
				}
			}
			}	
		}
		return listOfContacts;
	}
	
	@Override
	public void flush() {
		//The below method is what I designed prior to this - see that method
		readSerializedData();
		
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
			  InputStream contactsFile = new FileInputStream("./serialized/contacts.out");
			  InputStream buffer = new BufferedInputStream(futureFile);
			  InputStream pastBuffer = new BufferedInputStream(pastFile);
			  InputStream contactsBuffer = new BufferedInputStream(contactsFile);
			  ObjectInput input = new ObjectInputStream (buffer);
			  ObjectInput pastInput = new ObjectInputStream (pastBuffer);
			  ObjectInput contactsInput = new ObjectInputStream(contactsBuffer);
			  //deserialize the List
			  this.futureMeetingArray = (List<Meeting>)input.readObject();
			  this.pastMeetingArray = (List<PastMeetingImpl>)pastInput.readObject();
			  this.allContacts = (List<Contact>)contactsInput.readObject();
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
	public void contactAdder(Set<Contact> contacts, int ID){
		Iterator<Contact> iterator = contacts.iterator();
		while(iterator.hasNext()){
			Contact c = iterator.next();
			allContacts.add(c);
			//allContacts.add(iterator.next());
		}
			//allContacts.addAll(contacts);
			try {
		         FileOutputStream fileOut = new FileOutputStream("./serialized/contacts.out");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(allContacts); //This is so I can use later methods down where you need to get specific meetings in list form
		         out.close();
		         fileOut.close();
		      }catch(IOException i) {
		         i.printStackTrace();
		      }
		}
	}
	
	

