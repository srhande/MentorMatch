/* 
 * This file contains the Mentor class and all the methods for the Mentor Class.
 */
import java.util.ArrayList;
import java.util.Scanner;
/* 
 * This class contains all the characteristics and behaviors of a 
 * Mentor in MentorMatch.
 */
public class Mentee implements User{
	private String username, name, college, major, number, email;
	private String contact="";
	//list of previously encountered Mentor objects
	private ArrayList<Mentor> previouslyEncounteredMentorList = 
			new ArrayList<>(); 

	//Constructor-called whenever a new mentor signs up
	public Mentee(String username, String name, String college, String major){
		this.username = username;
		this.name = name;
		this.college = college;
		this.major = major;
	}

	//Set Profile method-called whenever a new mentor signs up in order to
	//create their profile
	public void setProfile(){
		Scanner scan = new Scanner(System.in);
		System.out.println("What is your phone number?");
		this.number = scan.nextLine();
		System.out.println("What is your email?");
		this.email = scan.nextLine();
		while(contact==""){ //Executes loop while there is no preferred contact
			System.out.println("What is preferred(Enter 1, 2, or 3):");
			System.out.println("1. text \n2. email \n3. call");
			switch(scan.nextLine()){
				case "1": this.contact = "text";
					break;
				case "2": this.contact = "email";
					break;
				case "3": this.contact = "call";
					break;
				default: System.out.println("Please enter valid input");
					break; //Prints this when user doesn't enter 1,2,or 3
				//Then it asks the question again. Repeats this until
				//user enters a valid input
			}
		}
	}

	//return mentee username
	@Override
	public String getUsername()
	{
		return username;
	}

	public ArrayList<Mentor> getPrevMentorsList()
	{
		return previouslyEncounteredMentorList;
	}

	public void addMentorToPrevMentorsList(Mentor prevEncounteredMentor)
	{
		previouslyEncounteredMentorList.add(prevEncounteredMentor);
	}
	//return name of user
	@Override
	public String getName() {
		return name;
	}
	
	//return mentee college
	@Override
	public String getCollege()
	{
		return college;
	}
	
	//Get mentee major
	@Override
	public String getMajor()
	{
		return major;
	}
	
	//toString method - return user information
	public String toString() {
		return "\nCollege: " + this.college + "\nMajor: " + this.major;
	}
 
}
