import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TestFile {
    //read mentor data and print it
    public static void main(String[] args) throws IOException{
        File fileMentee = new File("resources/mentee"); //stores mentor data

        File fileMentor = new File("resources/mentorData"); //stores mentee data

        Scanner inputMentee = new Scanner(fileMentee);
        Scanner inputMentor = new Scanner(fileMentor);

        while (inputMentee.hasNextLine())
        {
             String info = inputMentee.nextLine(); //read a line from INPUTMENTEE file NOT from input keyboard and store it in info


                System.out.println(info); //print out username


        }

        while (inputMentor.hasNextLine())
        {
            String info = inputMentor.nextLine(); //read a line from INPUTMENTEE file NOT from input keyboard and store it in info
            System.out.println(info); //print out username
        }
    }
}
