package album;
import java.util.Scanner; //TODO: REMOVE THIS IT IS NOT ALLOWED IN FINAL CODE

/**
 * This class defines the abstract data type that models an album in a collection of albums
 * @author Kiana Perst
 */
public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class: Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;
    
    public Album() {
        title = null;
        artist = null;
        genre = Genre.Unknown;
        releaseDate = new Date();
        isAvailable = false;
    }
    
    public Album(String title, String artist, Genre genre, Date releaseDate) {
        
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        isAvailable = true;
        
    }
    
    //Determines if two albums are equal based on the title and artist
    @Override
    public boolean equals(Object obj) {
        if () {
            
        }
        return false;
    }
    
    //TODO: Finish this method after you do Date class
    @Override
    public String toString(){
        String result;
        String separator = "::";
        result = title + separator + artist + separator + genre + separator ;
        return result;
    }
    
    public Genre getGenre() {
        return genre;
    }
    
    public Date getReleaseDate() {
        return releaseDate;
    }
    
    public boolean getAvailable() {
        return isAvailable;
    }
    
    //TODO: remove this temporary testbed
    public static void main(String[] args) {
        Album ABC = new Album();
        Date ok = new Date();
        Album DEF = new Album("d", "e", Genre.Unknown, ok);
        
        System.out.println(ABC.isAvailable);
        System.out.println(DEF.isAvailable);
        System.out.println(ABC.isAvailable);
        System.out.println(DEF.toString());
    }
}
