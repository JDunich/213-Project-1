package album;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This is a class handles the I/O for the user
 * @author Jack Dunich, Kiana Perst
 */
public class CollectionManager {
    
    Collection arr = new Collection();
    
    /**
     * Reads the command line and runs line based on isValid()
     */
    public void run(){
        System.out.print("Collection Manger starts running.");
        Scanner sc = new Scanner(System.in);
        while(true){
            String s = sc.nextLine();
            StringTokenizer input = new StringTokenizer(s, ",");
            if(!isValid(s)){System.out.println("Invalid command!");}
            else {
                String command = input.nextToken();
                if(command.equals("A") && !getValidDate(s)){System.out.println("Invalid Date!");}
                else {
                    if (isPrintCommand(command)) {
                        task(command, null);
                    } else {
                        Album album = createAlbum(input, command);
                        task(command, album);
                    }
                }
            }
        }
    }
    
    /**
     * Creates an Album object based on given command line input where input contains the Album details and command contains the command
     * @param StringTokenizer input, String Command
     * @return new album object
     */
    private Album createAlbum(StringTokenizer input, String command){
        String title = input.nextToken();
        String artist = input.nextToken();
        Album temp;
        if(command.matches("D|L|R")) {
            temp = new Album(title, artist);
        }
        else {
            String genre_string = (input.nextToken()).toLowerCase();
            genre_string = genre_string.substring(0, 1).toUpperCase() + genre_string.substring(1);
            genre_string = isEnum(genre_string);
            Genre genre = Genre.valueOf(genre_string);
            Date releaseDate = new Date(input.nextToken());
            temp = new Album(title, artist, genre, releaseDate);
        }
        return temp;
    }
    
    /**
     * Checks to see if a command is a print command
     * @param String command
     * @return true if the command matches a valid print command,
     *          false if it does not
     */
    private boolean isPrintCommand(String command){
        if(command.matches("P|PD|PG|Q")){return true;}
        return false;
    }

    /**
     * Creates a collection array and executes a command
     * @param String command, Album album
     */
    private void task(String command, Album album){
        if(command.equals("A")){
            if(arr.add(album)){System.out.println(album + " >> added.");}
            else{System.out.println(album.toString() + " >> is already in the collection.");}
        }else if(command.equals("D")){
            if(arr.remove(album)){System.out.println(album + " >> deleted.");}
            else{System.out.println(album.toString() + " >> is not in the collection.");}
        }else if(command.equals("L")) {
            if (arr.lendingOut(album)) {
                System.out.println(album + " >> lending out and set to not available.");
            } else {System.out.println(album.toString() + " >> is not available.");}
        }else if(command.equals("R")){
            if(arr.returnAlbum(album)){System.out.println(album + " >> returning and set to available.");}
            else{System.out.println(album.toString() + " >> return cannot be completed.");}
        }else if(command.equals("P")){
            arr.print();
        }else if(command.equals("PD")){
            arr.printByReleaseDate();
        }else if(command.equals("PG")){
            arr.printByGenre();
        }else if(command.equals("Q")){
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
    private boolean isValid(String input){
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
    private boolean getValidDate(String s){
        StringTokenizer input = new StringTokenizer(s, ",");
        String ptr = null;
            for(int i = 0; i < 5; i++){
                ptr = input.nextToken();
            }
            Date temp = new Date(ptr);
            if(!temp.isValid()){return false;}
       return true;
    }

    /**
     * Checks if the string is a defined enum
     * @param String String
     * @return string if is enum or Unknown if not defined enum
     */
    private String isEnum(String string){
        if(string.equals("Classical") || string.equals("Country") || string.equals("Jazz") || string.equals("Pop")){
            return string;
        }
        return "Unknown";
    }

}
