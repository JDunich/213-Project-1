package album;

/**
 * This class defines the abstract data type that models an
 * album in a collection of albums
 * @author Jack Dunich
 * @author Kiana Perst
 */
public class Album {
    /**
     * title of album
     */
    private final String title;
    /**
     * artist of album
     */
    private final String artist;
    /**
     * genre of album
     */
    private Genre genre; //enum class: Classical, Country, Jazz, Pop, Unknown
    /**
     * release date of album
     */
    private Date releaseDate;
    /**
     * album availability
     */
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
     * @param title title album
     * @param artist artist album
     * @param genre genre album
     * @param releaseDate release date album
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
     * @param title title album
     * @param artist artist album
     */
    public Album(String title, String artist) {

        this.title = title;
        this.artist = artist;
        isAvailable = false;
    }

    /**
     * Determines if two albums are equal based on the title and artist
     * @param obj which will be cast into an abstract Album object
     * @return true if both the title and artist fields are equal
     */
    @Override
    public boolean equals(Object obj) {
        return (title.compareTo(((Album) obj).getTitle()) == 0) && (artist.compareTo(((Album) obj).getArtist()) == 0);
    }

    /**
     * This method prints out the textual representation of an album in the form:
     * "title::artist::genre::releaseDate::isAvailable"
     * @return String that lists all the fields of the Album object
     */
    @Override
    public String toString() {
        String result;
        String separator = "::";
        String available_str;

        if (isAvailable) {
            available_str = "is available";
        } else {
            available_str = "is not available";
        }

        if (genre == null) {
            result = title + separator + artist;
        } else {
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
     * @param available availability of album
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}
