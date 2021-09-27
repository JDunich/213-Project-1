package album;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This is a class handles the I/O for the user
 * @author Jack Dunich, Kiana Perst
 */
public class CollectionManager {
    
    /**
     * Reads the command line and runs line based on isValid()
     */
    public void run(){
        Scanner sc = new Scanner(System.in);
        while(true){
            String s = sc.nextLine();
            StringTokenizer input = new StringTokenizer(s, ",");
            if(!isValid(s)){System.out.println("Invalid command!");}
            String command = input.nextToken();
            if(command == "A" && !getValidDate(input)){System.out.println("Invalid Date!");}
            if(isPrintCommand(command)){task(command, null);}
            else{
                Album album = createAlbum(input);
                task(command, album);
            }
        }
    }
    
    /**
     * Creates an Album object
     * @param input from command line
     * @return new album object
     */
    public Album createAlbum(StringTokenizer input){
        String title = input.nextToken();
        String artist = input.nextToken();
        Genre genre = Genre.valueOf(input.nextToken());
        Date releaseDate = new Date(input.nextToken());
        Album temp = new Album(title, artist, genre, releaseDate);
        return temp;
    }
    
    /**
     * Checks to see if a command is a print command
     * @param String
     * @return true if the command matches a valid print command,
     *          false if it does not
     */
    public boolean isPrintCommand(String command){
        if(command.matches("P|PD|PG")){return true;}
        return false;
    }

    /**
     * Creates a collection array and executes a command
     * @param String command, album
     */
    public void task(String command, Album album){
        Collection arr = new Collection();
        if(command == "A"){
            if(arr.add(album)){System.out.println(album + " >> added.");}
            else{System.out.println(album.toString() + " >> is already in the collection.");}
        }else if(command == "D"){
            if(arr.remove(album)){System.out.println(album + " >> deleted.");}
            else{System.out.println(album.toString() + " >> is already in the collection.");}
        }else if(command == "L") {
            if (arr.lendingOut(album)) {
                System.out.println(album + " >> lending out and set to not available.");
            } else {System.out.println(album.toString() + " >> is not available.");}
        }else if(command == "R"){
            if(arr.returnAlbum(album)){System.out.println(album + " >> returning and set to available.");}
            else{System.out.println(album.toString() + " >> return cannot be completed.");}
        }else if(command == "P"){
            arr.print();
        }else if(command == "PD"){
            arr.printByReleaseDate();
        }else if(command == "PG"){
            arr.printByGenre();
        }else if(command == "Q"){
            System.out.println("Collection Manager terminated.");
            System.exit(0);
        }
    }


    /**
     * Checks if the command is valid
     * @param String input
     * @return true if the command is one of the listed valid commands,
     *          false if it is not
     */
    public boolean isValid(String input){
        StringTokenizer temp = new StringTokenizer(input, ",");
        String ptr = temp.nextToken();
        if(!ptr.matches("A|D|L|R|P|PD|PG|Q")){return false;}
        return true;
    }
    
    /**
     * Checks if the Date is valid by implementing the isValid method
     * @param String input
     * @return true if the date string is a valid date,
     *          false if it is not
     */
    public boolean getValidDate(StringTokenizer input){
        String ptr = null;
            for(int i = 0; i < 5; i++){
                ptr = input.nextToken();
            }
            Date temp = new Date(ptr);
            if(!temp.isValid()){return false;}
       return true;
    }

}
