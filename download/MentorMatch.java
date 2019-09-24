import com.sun.xml.internal.bind.v2.TODO;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * note: we need files to remember the data that was stored previously. 
 * otherwise every time we run,
 * we could lose the previous data
 */
public class MentorMatch
{
  private static ArrayList<Mentor> mentorList;
  private static ArrayList<Mentee> menteeList;
  private static int scanCount = 0; //counter for which Scanner object to use

  public static void main(String[] args) throws IOException
  {
    MentorMatch mentorMatch = new MentorMatch();
    mentorList = new ArrayList<>(); //list of Mentor objects
    menteeList = new ArrayList<>(); //list of Mentee objects

    File fileMentor = new File("mentorData"); //stores mentor data
    FileWriter file1 = new FileWriter(fileMentor, true); 
  //stores mentor data

    File fileMentee = new File("mentee"); //stores mentor data
    FileWriter file2 = new FileWriter(fileMentee, true); 
  //stores mentee data

    //if i write to the same file, will it automatically append???

    PrintWriter outputMentor= new PrintWriter(file1); 
    //note that when using PrintWriter, file automatically appends
    PrintWriter outputMentee = new PrintWriter(file2);

    Scanner inputMentor = new Scanner(fileMentor);
    Scanner inputMentee = new Scanner(fileMentee);

    //since there is no reset option to go back to beginning of file once 
    //end of file has been reached
    //we must create a new Scanner object for each time we need to reset!
    Scanner inputMentor1 = new Scanner(fileMentor);
    Scanner inputMentor2 = new Scanner(fileMentor);
    Scanner inputMentor3 = new Scanner(fileMentor);
    Scanner inputMentor4 = new Scanner(fileMentor);
    Scanner inputMentor5 = new Scanner(fileMentor);
    Scanner inputMentor6 = new Scanner(fileMentor);
    Scanner inputMentor7 = new Scanner(fileMentor);
    Scanner inputMentor8 = new Scanner(fileMentor);
    Scanner inputMentor9 = new Scanner(fileMentor);
    Scanner inputMentor10 = new Scanner(fileMentor);

    Scanner inputMentee1 = new Scanner(fileMentee);
    Scanner inputMentee2 = new Scanner(fileMentee);
    Scanner inputMentee3 = new Scanner(fileMentee);
    Scanner inputMentee4 = new Scanner(fileMentee);
    Scanner inputMentee5 = new Scanner(fileMentee);
    Scanner inputMentee6 = new Scanner(fileMentee);
    Scanner inputMentee7 = new Scanner(fileMentee);
    Scanner inputMentee8 = new Scanner(fileMentee);
    Scanner inputMentee9 = new Scanner(fileMentee);
    Scanner inputMentee10 = new Scanner(fileMentee);

    Scanner[] scanMentorArray = {inputMentor1, inputMentor2, inputMentor3, 
    inputMentor4, inputMentor5, inputMentor6, inputMentor7, 
    inputMentor8,inputMentor9, inputMentor10};
    Scanner[] scanMenteeArray = {inputMentee, inputMentee1, inputMentee2, 
    inputMentee3, inputMentee4, inputMentee5,inputMentee6, 
    inputMentee7, inputMentee8,inputMentee9, inputMentee10};

    Scanner input = new Scanner(System.in); //input from console

    String x;
    mentorList = mentorMatch.populateMentorList(inputMentor);

    //keep prompting user to type N or R indicating they are a new user
    //keep looping as long as q is not entered
    do
    {
      System.out.println("-------------------------Welcome to MentorMatch"
        + "------------------------"); //Display welcome message
    //Prompt user to enter N or R for new or returning user
      System.out.println("Are you a new or returning user? (Type 'N' for "
        + "new or 'R' for returning)");
      x = input.nextLine();

      if(x.equals("N")) //List of prompts for NEW user to enter their info
      {
        int type = mentorMatch.getType();
        //if user is a new MENTOR
        if (type == 1)
        {
          //getInfoMentor is gonna return
          //a new Mentor object that has the username, name, college,
      //and major
          Mentor mentor = (mentorMatch.getInfoMentor(input, outputMentor));

          //in the Mentor class, this method asks user to create 
      //profile
          //mentor.setProfile();

          //add the user to the mentorlist
          mentorList.add(mentor);
        }
        //if user is a new MENTEE
        if (type == 2)
        {
          //getInfoMentor is gonna return
          //a new Mentee object that has the username, name, college,
      //and major
          Mentee mentee = mentorMatch.getInfoMentee(input, 
            outputMentee);
          mentee.setProfile(); //in the Mentee class, this method 
             //asks user to create profile
          menteeList.add(mentee); //add the new Mentee to menteeList
        }
      }
      //going to find user BASED ON USERNAME
      else if(x.equals("R")) //Login and access file if returning user
      {
        System.out.println("Welcome back"); //LOGIN AND ACCESS FILE
        System.out.print("Enter your username (Spelling counts): ");
        String username = input.nextLine(); 
    /*
     * NOTICE it is input NOT inputMentee (input reads from keyboard 
     * while inputMentee reads from mentee file)
     * mentee is either a returning verified user or null 
     * (the username was invalid)
     */

        Mentee mentee = mentorMatch.findMenteeUser(username, 
        scanMenteeArray[scanCount]);
        //mentor is either a returning verified user or null 
    //(the username was invalid)
        Mentor mentor = mentorMatch.findMentorUser(username, 
        scanMentorArray[scanCount]);

        scanCount++;
        //mentee logging in
        if (mentee == null && mentor == null)
        {
          //we are gonna have to search through both the mentor and 
      //mentee list to find a possible matching username
          //if no user name is found in either list 
      //(both lists return null), then tell user the 
      //username could not be found
          System.out.println("USERNAME COULD NOT BE FOUND");
        }
        //logging in as MENTEE
        else if (mentee != null && mentor == null)
        {
          System.out.println("\nUSERNAME FOUND! Logging in as a" +
          " MENTEE. \n");
          System.out.println(mentee.getUsername());
          //start searching for mentors  3/8/18 thurs
          //for loop to cycle through mentors in mentor array list
          //display each mentor's profile only if that mentor has not 
          //been seen by this mentee before
          //if user likes a mentor, then they will match and the 
          //mentor's contact info will show up
          System.out.println("If you like a mentor and want to" + 
          " contact  him, type 'y'. If not, type 'n': ");
          //loop through the list of ALL mentors
          for (int i = 0; i < mentorList.size(); i++)
          {
            boolean hasEncountered = false; 

            //loop through the previously encountered mentor
            //list for THIS mentee
            //note that I added getPrevMentorsList() and 
            //addMentorToPrevMentorsList() to Mentee class
            //because EACH Mentee has its own list of previously 
            //encountered mentors
            for (int j = 0; j < mentee.getPrevMentorsList().size(); j++)
            {
              //first focus on a specific Mentor at index i
              //then compare that Mentor to the logging in Mentee's list of previously encountered mentors
              //if the particular Mentor at index i IS a Mentor that has already been encountered
              //set hasEncountered to be true
              if (mentorList.get(i).equals(mentee.getPrevMentorsList().get(j)))
              {
                hasEncountered = true;
              }
            } //by the end of this inner loop, we will be able to for sure tell whether this mentor has been encountered
            //but we have to make sure we loop through all the mentors in the previously encountered mentor list
            //and compare it to the mentor at position i in the total mentorList

            //if hasEncountered is false, that means that specific Mentee has not yet encountered this Mentor at index i
            //so we should display this Mentor's profile since it is a new mentor
            if (hasEncountered == false)
            {
              System.out.println("\n" + mentorList.get(i).getProfile()); //display mentor's profile info
              System.out.print("Do you want to match? [y] or [n]: ");
              String matchOrNah = input.nextLine();
              //if the mentee likes the mentor and types y, then display mentor's contact info
              //if the user doesn't type 'y' then don't display the contact info
              if (matchOrNah.equalsIgnoreCase("y"))
              {
                //indicate a match with a HEART<3
                System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
                System.out.println("♡♡♡♡♡♡♡MATCH♡♡♡♡♡YAY♡♡♡♡♡♡♡♡♡♡");
                System.out.println(mentorList.get(i).getContactInfo());
              }
              else
              {
                //indicators to separate a new profile
                System.out.println("******************************************" 
        + "**************");
                System.out.println("********************(>'-')> NEXT!*********"
        + "**************");
              }
              //IMPORTANT: since this mentor has not been encountered by this mentee,
              // we add this mentor to THIS Mentee's list of previously encountered mentors
              //that way next time we check to see if we have encountered a mentor, this mentor
              //will be on the list of previously encountered mentors and thus we will skip over it!!
              mentee.addMentorToPrevMentorsList(mentorList.get(i));
            }
            //note that if hasEncountered is true, that means that specific Mentee HAS encountered
            //this Mentor and so we should skip over this Mentor (not display profile)
          }
        }
        //mentor logging in
        else if (mentee == null && mentor != null)
        {
          System.out.println("USERNAME FOUND! You are a MENTOR. Here is your profile and contact info\n");
          //display mentor profile just so the mentor can see what information they originally put
          //if we had time, we were going to let mentor edit profile and contact info.. but too late for that
          System.out.println(mentor.getProfile()); //display mentor's profile info
          System.out.println("\n" + mentor.getContactInfo());
        }
        //if mentee and mentor are both null, then that means they have the same username which shouldn't be possible
        else if (mentee != null && mentor != null)
        {
          System.out.println("ERROR! Your username matches another!!!");
        }
      }
      else //Prints error message if user put invalid input
      {
        System.out.println("Invalid input. Next time, please enter either" + 
      " N or R. or 'Q' to quit\n");
      }
    }
    while (!x.equalsIgnoreCase("q")); // !x.equals("R") || (!x.equals("N")));
  }

  //if try catch doesnt work add a loop that makes sure user types in either 1 or 2
  public int getType()
  {
    Scanner input = new Scanner(System.in);
    int year = 0, type = 0;
    try{
      System.out.println("What year are you?" + 
          " (Enter 1 for first year and so forth)");
      year = input.nextInt(); //MULTIPLE CHOICE
    }catch(NumberFormatException ex){
      System.out.println(year + "is not an integer, please enter an integer");
    }
    try{
      System.out.println("Are you a mentor or mentee? Enter 1 for mentor or 2" +
          " for mentee. (Note: You must be at least a 3rd year to be a mentor)");
      type = input.nextInt();
    }catch(NumberFormatException ex){
      System.out.println(type + "is not an integer," + 
          " please enter either 1 or 2");
    }

    if(type==1 && year>2) //If MENTOR and if they are at least a 3rd year, then call mentor constructor
    {
      return 1; //mentor
      //System.out.println("Call mentor constructor"); //CALL MENTOR CONSTRUCTOR
    }
    else if(type==2) //If mentee, then call the mentee constructor
    {
      return 2;
      //System.out.println("Call mentee constructor"); //CALL MENTEE CONSTRUCTOR
    }
    else//If user enters mentor but is not at least a 3rd year, print error message
    {
      System.out.println("You must be at least a 3rd year to be a mentor.");
      return 0;
    }
  }


  //WRITES the mentor's info (PID, name, college, major) to a file
  //then RETURNS a Mentor object encapsulated
  //https://beginnersbook.com/2014/01/how-to-append-to-a-file-in-java/
  //couldn't we just use arraylist of Mentors and not files??
  public Mentor getInfoMentor(Scanner input, PrintWriter outputMentor)
  {
    System.out.println("Please enter your PID: ");
    String username = input.nextLine();
    outputMentor.println(username);

    System.out.println("Please enter your first and last name: ");
    String name = input.nextLine();
    outputMentor.println(name);

    System.out.println("Please enter your college: ");
    //String college = input.nextLine(); //MULTIPLE CHOICE
    String college = getCollege(input); //calls getCollege() DOWN BELOW!!!!!!!!!!
    outputMentor.println(college);

    System.out.println("Please enter your major: ");
    String major = getMajor(input); //MULTIPLE CHOICE calls getMAJOR() DOWNBELOW!!!!!
    outputMentor.println(major);


    Mentor m = new Mentor(username, name, college, major, "", "","","");
    m.setProfile(outputMentor);

    //the . is the distinguishing factor that signals the end of a particular mentor
    outputMentor.println("."); //new line is IMPORTANT for determining the end of one user's info
    outputMentor.println();

    outputMentor.close();
    return m;
  }

  //WRITES the mentee's info (PID, name, college, major) to mentee file
  //then RETURNS a Mentee object encapsulated
  public Mentee getInfoMentee(Scanner input, PrintWriter outputMentee)
  {
    System.out.println("Please enter your PID: ");
    String username = input.nextLine();
    outputMentee.println(username);

    System.out.println("Please enter your first and last name: ");
    String name = input.nextLine();
    outputMentee.println(name);

    System.out.println("Please enter your college: ");
    String college = getCollege(input);
    outputMentee.println(college);

    System.out.println("Please enter your major: ");
    String major = getMajor(input); //MULTIPLE CHOICE
    outputMentee.println(major);

    outputMentee.println("."); //new line is IMPORTANT for determining the end of one user's info

    outputMentee.close();
    return new Mentee(username, name, college, major);
  }

  //TODO: make return type Mentee so that we are returning the proper user that is logging in and we don't need mentee list

  //break out of while loop
  //http://www.dreamincode.net/forums/topic/39690-reading-from-specific-line-in-text-file/
  //if username matches, then retrieve your info
  //cycle through mentee list if you find a username from a list that matches
  //the username the user types in,
  // PRINT OUT Mentee's info and RETURN MENTEE object of the matching user
  //returns NULL if no matching Mentee username was found
  public Mentee findMenteeUser(String username, Scanner inputMentee)
  {
    String info = "";

    /*cycle through every single line in file (reading file)
    //until the username is found and then since username is the first thing
    //asked, and the rest of the mentee's info is represented by everything
    //up until a new line is found, read that data and capture it into a Mentee object
    */
    //read the file until end of file has been reached

    while (inputMentee.hasNextLine())
    {
      info = inputMentee.nextLine(); //read a line from INPUTMENTEE file NOT from input keyboard and store it in info

      if (info.equals(username)) //if the line read from inputMentee file = the username user types in to keyboard
      {
        System.out.println(info); //print out username
        String name = inputMentee.nextLine(); //next line should be name
        String college = inputMentee.nextLine();
        String major = inputMentee.nextLine();
        inputMentee.nextLine(); //read the '.'
        //while we have not reached the period (delimiter for Mentee)
        //keep printing out that user's info
//            while (inputMentee.hasNextLine() && !info.equals("."))
//            {
//              System.out.println(info);
//              info = inputMentee.nextLine();
//            }
        return new Mentee(username, name, college, major);
      }
    }
    return null;
  }

  // PRINT OUT Mentor's info and RETURN MENTOR object of the matching user
  //returns NULL if no matching Mentor username was found
  public Mentor findMentorUser(String username, Scanner inputMentor)
  {
    String info = "";

    /*cycle through every single line in file (reading file)
    //until the username is found and then since username is the first thing
    //asked, and the rest of the mentee's info is represented by everything
    //up until a new line is found, read that data and capture it into a Mentee object
    */
    //read the file until end of file has been reached

    while (inputMentor.hasNextLine())
    {
      info = inputMentor.nextLine(); //read a line from INPUTMENTOR file NOT from input keyboard and store it in info

      if (info.equals(username)) //if the line read from inputMentor file = the username user types in to keyboard
      {
        System.out.println(info); //print out username
        String name = inputMentor.nextLine(); //next line should be name
        String college = inputMentor.nextLine();  //next line read from inputMentor file should be college
        String major = inputMentor.nextLine();
        String number = inputMentor.nextLine();
        String email = inputMentor.nextLine();
        String contact = inputMentor.nextLine();
        String bio = inputMentor.nextLine();
        inputMentor.nextLine(); //read the '.'

        return new Mentor(username, name, college, major, number, email, contact, bio);
      }
    }
    return null;
  }

  //USED FOR READING DUMMY DATA
  //read the data from the mentor file and populate the mentor array list with the mentor data
  public ArrayList<Mentor> populateMentorList(Scanner inputMentor)
  {
    String info = "";
    String[] s = new String[8]; //array of strings which holds Mentor data
    while (inputMentor.hasNextLine())
    {
      for (int x = 0; x < 8; x++) {
        s[x] = inputMentor.nextLine();
      }
      //new Mentor will be populated with PID, name, college, major since that is the order
      //the data will be read from the file
      Mentor m = new Mentor(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
      mentorList.add(m);

      //reads the next line (which should be a line with ".")
      //when the while loop condition is eval again, if there is no next line that means end of file has been reached
      inputMentor.nextLine();
    }
    //comment out the close() because we need to be able to access the mentor file again later in the run
    //inputMentor.close(); //THIS FIXED THE ERROR: "Exception in thread "main" java.lang.IllegalStateException: Scanner closed"
    return mentorList;
  }

  public String getCollege(Scanner input)
  {
    System.out.println("What college are you in? (Enter 1, 2, 3, 4, 5, or 6)");
    System.out.println("1. Revelle \n2. Muir \n3. Marshall \n4. Warren \n5. Roosevelt \n6. Sixth");
    String college = "";
    switch(input.nextLine()) //MULTIPLE CHOICE
    {
      case "1": college = "Revelle";
        break;
      case "2": college = "Muir";
        break;
      case "3": college = "Marshall";
        break;
      case "4": college = "Warren";
        break;
      case "5": college = "Roosevelt";
        break;
      case "6": college = "Sixth";
        break;
      default: System.out.println("Please enter a valid number from 1-6");
        break;
    }
    return college;
  }

  /**
   * this method is called by getMentor() and getMentee() and returns the major the user enters
   * as a string. note that not all majors are listed.. mostly just general ones
   * @param input
   * @return
   */
  public String getMajor(Scanner input)
  {
    System.out.println("What's your major? (Enter a number from 1-8)");
    System.out.println("1. STEM \n2. Liberal Arts \n3. Social Sciences \n" + 
        "4. Humanities \n5. Enterprising (business) \n" + 
        "6. Social (helpers) \n7. Other \n8. Undeclared");
    String major = "";
    switch(input.nextLine()) //MULTIPLE CHOICE
    {
      case "1": System.out.println("STEM Major: Enter a number from 1-6" + 
          " to specify");
        System.out.println("1. Math \n2. Engineering \n3. Biology \n" + 
          "4. Chemistry \n5. Computer Science \n6. Other");
        switch(input.nextLine())
        {
          case "1": major = "STEM Math";
            break;
          case "2": major = "STEM Engineering";
            break;
          case "3": major = "STEM Biology";
            break;
          case "4": major = "STEM Chemistry";
            break;
          case "5": major = "STEM Computer Science";
            break;
          case "6": major = "STEM Other";
            break;
          default: System.out.println("Please enter a valid number from 1-6");
            break;
        }
        break;
      case "2": System.out.println("Liberal Arts Major: Enter a number from "+
            "1-5 to specify");
        System.out.println("1. Music \n2. Visual Arts \n3. Theatre \n" + 
            "4. Film/Photography \n5. Other");
        switch(input.nextLine())
        {
          case "1": major = "LBAR Music";
            break;
          case "2": major = "LBAR Visual Arts";
            break;
          case "3": major = "LBAR Theatre";
            break;
          case "4": major = "LBAR Film/Photography";
            break;
          case "6": major = "LBAR Other";
            break;
          default: System.out.println("Please enter a valid number from 1-5");
            break;
        }
        break;
      case "3": System.out.println("Social Sciences Major: Enter a number" +
              " from 1-9");
        System.out.println("1. Critical Gender Studies \n2. Economics \n" + 
              "3. Anthropology \n4. Psychology \n5. Sociology \n6. Philosophy " 
        + "\n7. History \n8. Cognitive Science \n9. Other");
        switch(input.nextLine())
        {
          case "1": major = "SOSC Critical Gender Studies";
            break;
          case "2": major = "SOSC Economics";
            break;
          case "3": major = "SOSC Anthropology";
            break;
          case "4": major = "SOSC Psychology";
            break;
          case "5": major = "SOSC Sociology";
            break;
          case "6": major = "SOSC Philosophy";
            break;
          case "7": major = "SOSC History";
            break;
          case "8": major = "SOSC Cognitive Science";
            break;
          case "9": major = "SOSC Other";
            break;
          default: System.out.println("Please enter a valid number from 1-9");
            break;
        }
        break;
      case "4": System.out.println("Humanities Major: Enter a number from 1-3" +
          " to specify");
        System.out.println("1. Communications \n2. Literature \n3. Other");
        switch(input.nextLine())
        {
          case "1": major = "HUMA Communications";
            break;
          case "2": major = "HUMA Literature";
            break;
          case "3": major = "HUMA Other";
            break;
          default: System.out.println("Please enter a valid number from 1-3");
            break;
        }
        break;
      case "5": System.out.println("Enterprising Major: Enter a number from 1-5"
            +" to specify");
        System.out.println("1. Financing \n2. Government and Public" +
            " Administration \n3. Marketing/Promotion/Advertising \n"+
	    "4. General Business \n5. Other");
        switch(input.nextLine())
        {
          case "1": major = "ENTE Financing";
            break;
          case "2": major = "ENTE Government and Public Administration";
            break;
          case "3": major = "ENTE Marketing/Promotion/Advertising";
            break;
          case "4": major = "ENTE General Business";
            break;
          case "6": major = "ENTE Other";
            break;
          default: System.out.println("Please enter a valid number from 1-5");
            break;
        }
        break;
      case "6": System.out.println("Social Major: Enter a number from 1-6 to " +
      	     "specify");
        System.out.println("1. Therapy \n2. Applied Physiology and Sports" +
	     " Management \n3. Health \n4. Teaching \n " + 
	     "5. Markets and Culture \n6. Other");
        switch(input.nextLine())
        {
          case "1": major = "SOCI Therapy";
            break;
          case "2": major = "SOCI Applied Physiology and Sports Management";
            break;
          case "3": major = "SOCI Health";
            break;
          case "4": major = "SOCI Teaching";
            break;
          case "5": major = "SOCI Markets and Culture";
            break;
          case "6": major = "SOCI Other";
            break;
          default: System.out.println("Please enter a valid number from 1-6");
            break;
        }
        break;
      case "7": major = "Other";
        break;
      case "8": major = "Undeclared";
        break;
      default: System.out.println("Please enter a valid number from 1-8");
        break;
    }
    return major;
  }

}
