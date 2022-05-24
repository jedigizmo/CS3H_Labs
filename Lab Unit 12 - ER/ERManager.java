 /**
 * ERManager.java
 * ERManager class holds the constructor and various helper methods to run the hospital
 *
 * @author Gikonyo Njendu
 * Collaborators: Takuto Ban
 * Teacher Name: Ms.Bailey
 * Period: 6
 * Due Date: 03/31/2022
 */
 
import java.io.*;
import java.util.*;
 
public class ERManager
{
    private PriorityQueue<Patient> room;
    
   	/** Constructor class for the ERManager class
	 */
    public ERManager(){
        room = new PriorityQueue<>();
    }
   	
   	/** Checks if the queue has patients
	 *  @returns true if there are patients, false if empty
	 */
    public boolean hasPatients(){
        return !room.isEmpty();
    }
   /** Adds patient to queue, keeping min at top
	 *  @param the patient to be added to queue
	 */
    public void addPatient(Patient P){
        room.offer(P);
    }
   /** Treats the next patient in the queue and returns the treated patient
	 *  @return the patient that was treated
	 */
    public Patient treatNextPatient(){
        return room.poll();
    }
   /** Treats all the patinet in the queue, and returns an array of treated patients
	 *  @returns list of all treated patients
	 */
    public List<Patient> treatAllPatients(){
        List<Patient> treated = new ArrayList<>();
        while(!room.isEmpty())
            treated.add(room.poll());
        return treated;
    }
   /** Grabs the list of waiting patients and return them as an array
	 *  @return array of patients waiting in the waiting room
	 */
    public Patient[] getWaitingPatients(){
        return room.toArray(new Patient[room.size()]);
    }
   
}
