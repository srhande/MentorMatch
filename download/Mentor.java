/* This file contains the Mentor class and all the methods for the Mentor Class.
 * It is used to hold important code for the a mentor user in
 * Mentor Match. */

import java.io.PrintWriter;
import java.util.Scanner;

/* This class contains all the characteristics and behaviors of a Mentor in Men
 * torMatch. The variables of this class are used to hold all the
 * important information needed to create a Mentor. */
public class Mentor{// extends User{
    private String username, name, college, major, number, email,
            bio;
    private String contact="";

    /* Constructor-called whenever a new mentor signs up. Creates the mentor
     * object. The constructor takes in the mentor's username, name, college, and
     *  major and then sets the Mentor object's variables to those arguments.*/
    public Mentor(String username, String name, String college, String major, String number, String email, String contact, String bio){
        this.username = username;
        this.name = name;
        this.college = college;
        this.major = major;
        this.number = number;
        this.email = email;
        this.contact = contact;
        this.bio = bio;
    }

    /* Set Profile method-called whenever a new mentor signs up in order to create
     * their profile. When this method is called, the user is able to enter their
     * phone number,email, and preferred form of contact. The information is used to
     * create the mentor's profile.
     * Returns nothing and no parameters.*/
    public void setProfile(PrintWriter outputMentor){
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your phone number?");
        this.number = scan.nextLine();
        outputMentor.println(number);
        System.out.println("What is your email?");
        this.email = scan.nextLine();
        outputMentor.println(email);
        //the contact is initialed to be ""
        //so until the user enters a number 1, 2, or 3, the value of contact won't be updated to "text" "email" or "call"
        //and instead will remain as ""
        while(contact.equals(""))
        { /*Executes loop while there is no preferred
contact*/
            System.out.println("What is preferred(Enter 1, 2, or 3):");
            System.out.println("1. text \n2. email \n3. call");
            switch(scan.nextLine()){ //Multiple choice
                case "1": this.contact = "text";
                    break;
                case "2": this.contact = "email";
                    break;
                case "3": this.contact = "call";
                    break;
                default: System.out.println("Please enter valid input");
                    break; /*Prints this when user doesn't enter 1,2,or 3
                        Then it asks the question again. Repeats this until
                        user enters a valid input*/
            }
        }
        outputMentor.println(contact);
        System.out.println ("Please enter a bio:");
        System.out.println("Talk about your hobbies, career plans, etc.");
        this.bio = scan.nextLine();
        outputMentor.println(bio);
        //TODO: Write the prompts to get profile details and store in profileDetails
    }

    //Get all the info

    //This method returns the name of the mentor.
    //@return String username
    public String getUsername(){
        return username;
    }

    //This method returns the name of the mentor.
    //@return String name
    public String getName(){
        return this.name;
    }

    //This method returns the college the mentor is in.
    //@return String college
    public String getCollege(){
        return this.college;
    }

    //This method returns the Mentor's major.
    //@return String major
    public String getMajor(){
        return this.major;
    }

    //This method returns the Mentor's number
    //@return String number
    public String getNumber(){
        return this.number;
    }

    //This method returns the Mentor's email
    //@return String email
    public String getEmail(){
        return this.email;
    }

    //This method returns the Mentor's preferred contact
    //@return String contact
    public String getContact(){
        return this.contact;
    }

    //This method returns the Mentor's bio
    //@return String bio
    public String getBio(){
        return this.bio;
    }

    //This method returns the mentor's contact information, which includes
    //their name, number, email, and preferred form of contact.
    //This is displayed when the user chooses to see the Mentor's contact
    //info
    //@return String contactInfo
    public String getContactInfo(){
        String contactInfo = "\n\t\t***CONTACT INFORMATION***\nName: " + this.name +
                "\nPhonenumber: " + this.number + "\nEmail: " +
                this.email + "\nPreferred form of contact: " + this.contact;
        return contactInfo;
    }

    //This method returns the mentor's profile, which includes their name,
    //college, major, and personal bio.
    //This is displayed as one of the matches when the mentee's matches
    //show up
    //@return String profile
    public String getProfile(){
        String profile = "\n" + "\t\t***PROFILE for " + this.username + "***\n\nName: " + this.name + "\t\tCollege: " + this.college
                + "\t\tMajor: " + this.major + "\nBio: " +this.bio;
        return profile;
    }

    //Returns all of the mentor's information: username, contact info
    //(name, number, email, preferred contact), profile(college, major, bio)
    //@return String
    public String toString(){
        return "Username: " + this.username + "\nContact Info: " + getContact()
                + "\n" + getProfile();

    }
}
