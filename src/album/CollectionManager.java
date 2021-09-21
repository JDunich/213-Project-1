package album;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This is a class handles the I/O for the user
 * @author Jack Funich
 *
 */
public class CollectionManager {
    //reads the command line and runs line based on isValid() TODO: figure out how to print albums the way it is displayed on test
    public void run(){
        Scanner sc = new Scanner(System.in);
        while(true){
            String s = sc.nextLine();
            StringTokenizer input = new StringTokenizer(s, ",");
            if(!isValid(input)){System.out.println("Invalid command!");}
            String command = input.nextToken();
            if(isPrintCommand(command)){task(command, "");}
            else{
            Album album = createAlbum(input);

            }
        }
    }
    //creates an Album object
    public Album createAlbum(StringTokenizer input){
        Album temp = new Album();
        String title = input.nextToken();
        String artist = input.nextToken();
        String genre = input.nextToken();
        String releaseDate = input.nextToken();
        temp.Album(title, artist, genre, releaseDate);
        return temp;
    }

    public boolean isPrintCommand(String command){
        if(command.matches("P|PD|PG")){return true;}
        return false;
    }

    public void task(String command, String album){
        if(command == "A"){
            if(add(album)){System.out.println(album + " >> added.");}
            else{System.out.println(album + " >> is already in the collection.");}
        }else if(command == "D"){
            if(remove(album)){System.out.println(album + " >> deleted.");}
            else{System.out.println(album + " >> is already in the collection.");}
        }else if(command == "L") {
            if (lendingOut(album)) {
                System.out.println(album + " >> lending out and set to not available.");
            } else {System.out.println(album + " >> is not available.");}
        }else if(command == "R"){
            if(returnAlbum(album)){System.out.println(album + " >> returning and set to available.");}
            else{System.out.println(album + " >> return cannot be completed.");}
        }else if(command == "P"){
            System.out.println("*List of albums in collection.");
            print();
            System.out.println("*End of list.");
        }else if(command == "PD"){
            System.out.println("*Album collection by release date.");
            printByReleaseDate();
            System.out.println("*End of list.");
        }else if(command == "PG"){
            System.out.println("*Album collection by genre.");
            printByGenre();
            System.out.println("*End of list.");
        }else if(command == "Q"){
            System.out.println("Collection Manager terminated.");
            System.exit(0);
        }
    }
    //TODO: Code this
    public boolean isValid(StringTokenizer input){
        //checks if the input is valid
    }


}
