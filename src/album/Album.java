package album;

/**
 * This class defines the abstract data type that models an 
 * album in a collection of albums
 * @author Kiana Perst, Jack Dunich
 */
public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class: Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;
    
    /** 
     * Constructor that creates an empty Album object
     */
    public Album() {
        title = null;
        artist = null;
        genre = Genre.Unknown;
        releaseDate = new Date();
        isAvailable = false;
    }
    
    /** 
     * Constructor that creates an Album object
     * @param String album_title, String artist, enum Genre genre, Date releaseDate
     */
    public Album(String title, String artist, Genre genre, Date releaseDate) {
        
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        isAvailable = true;
        
    }
    
    /** 
     * Constructor that creates an Album object with limited parameters
     * @param String album_title, String artist
     */
    public Album(String title, String artist) {
        
        this.title = title;
        this.artist = artist;
        isAvailable = false;
    }
    
    /**
     * Determines if two albums are equal based on the title and artist
     * @param Object which will be cast into an abstract Album object
     * @return true if both the title and artist fields are equal
     */
    @Override
    public boolean equals(Object obj) {
        if ((title.compareTo(((Album)obj).getTitle()) == 0) && (artist.compareTo(((Album)obj).getArtist()) == 0)) {
            return true;
        }
        return false;
    }
    
    /**
     * This method prints out the textual representation of an album in the form:
     * "title::artist::genre::releaseDate::isAvailable"
     * @return String that lists all the fields of the Album object
     */
    @Override
    public String toString(){
        String result;
        String separator = "::";
        String available_str;
        
        if(isAvailable) {
            available_str = "is available";
        }
        else {
            available_str = "is not available";
        }
        
        if (genre == null) {
            result = title + separator + artist;
        }
        else {
            result = title + separator + artist + separator + genre + separator + 
                    releaseDate.dateToString() + separator + available_str;
        }
        return result;
    }
    
    /**
     * Accessor method for title field
     * @return String title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Accessor method for artist field
     * @return String artist
     */
    public String getArtist() {
        return artist;
    }
    
    /**
     * Accessor method for genre field
     * @return Genre enum
     */
    public Genre getGenre() {
        return genre;
    }
    
    /**
     * Accessor method for releaseDate field
     * @return Date releaseDate
     */
    public Date getReleaseDate() {
        return releaseDate;
    }
    
    /**
     * Accessor method for isAvailable field
     * @return boolean isAvailable
     */
    public boolean getAvailable() {
        return isAvailable;
    }
    
    /**
     * Mutator method for isAvailable field
     * @param boolean isAvailable
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
}
