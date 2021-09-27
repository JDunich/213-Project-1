package album;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This is a class handles the I/O for the user
 *
 * @author Jack Dunich
 * @author Kiana Perst
 */
public class CollectionManager {
    /**
     * new collection object
     */
    Collection arr = new Collection();

    /**
     * Reads the command line and runs line based on isValid()
     */
    public void run() {
        System.out.print("Collection Manger starts running.");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            StringTokenizer input = new StringTokenizer(s, ",");
            if (!isValid(s)) {
                System.out.println("Invalid command!");
            } else {
                String command = input.nextToken();
                if (command.equals("A") && !getValidDate(s)) {
                    System.out.println("Invalid Date!");
                } else {
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
     *
     * @param input user inputted album
     * @param command user inputted command
     * @return new album object
     */
    private Album createAlbum(StringTokenizer input, String command) {
        String title = input.nextToken();
        String artist = input.nextToken();
        Album temp;
        if (command.matches("[DLR]")) {
            temp = new Album(title, artist);
        } else {
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
     *
     * @param command uer inputted command
     * @return true if the command matches a valid print command,
     * false if it does not
     */
    private boolean isPrintCommand(String command) {
        return command.matches("P|PD|PG|Q");
    }

    /**
     * Creates a collection array and executes a command
     *
     * @param command uer inputted command
     * @param album album that was created
     */
    private void task(String command, Album album) {
        switch (command) {
            case "A":
                if (arr.add(album)) {
                    System.out.println(album + " >> added.");
                } else {
                    System.out.println(album.toString() + " >> is already in the collection.");
                }
                break;
            case "D":
                if (arr.remove(album)) {
                    System.out.println(album + " >> deleted.");
                } else {
                    System.out.println(album.toString() + " >> is not in the collection.");
                }
                break;
            case "L":
                if (arr.lendingOut(album)) {
                    System.out.println(album + " >> lending out and set to not available.");
                } else {
                    System.out.println(album.toString() + " >> is not available.");
                }
                break;
            case "R":
                if (arr.returnAlbum(album)) {
                    System.out.println(album + " >> returning and set to available.");
                } else {
                    System.out.println(album.toString() + " >> return cannot be completed.");
                }
                break;
            case "P":
                arr.print();
                break;
            case "PD":
                arr.printByReleaseDate();
                break;
            case "PG":
                arr.printByGenre();
                break;
            case "Q":
                System.out.println("Collection Manager terminated.");
                System.exit(0);
        }
    }


    /**
     * Checks if the command is valid
     *
     * @param input user command line input
     * @return true if the command is one of the listed valid commands,
     * false if it is not
     */
    private boolean isValid(String input) {
        StringTokenizer temp = new StringTokenizer(input, ",");
        String ptr = temp.nextToken();
        return ptr.matches("A|D|L|R|P|PD|PG|Q");
    }

    /**
     * Checks if the Date is valid by implementing the isValid method
     *
     * @param s command line input
     * @return true if the date string is a valid date,
     * false if it is not
     */
    private boolean getValidDate(String s) {
        StringTokenizer input = new StringTokenizer(s, ",");
        String ptr = null;
        for (int i = 0; i < 5; i++) {
            ptr = input.nextToken();
        }
        Date temp = new Date(ptr);
        return temp.isValid();
    }

    /**
     * Checks if the string is a defined enum
     *
     * @param string enum string
     * @return string if is enum or Unknown if not defined enum
     */
    private String isEnum(String string) {
        if (string.equals("Classical") || string.equals("Country") || string.equals("Jazz") || string.equals("Pop")) {
            return string;
        }
        return "Unknown";
    }

}
