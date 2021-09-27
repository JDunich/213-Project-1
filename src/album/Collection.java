package album;

/**
 * This class defines the array list data structure to hold the album
 * collection and provide the operations to manage the collection
 *
 * @author Jack Dunich
 * @author Kiana Perst
 */
public class Collection {
    /**
     * album list
     */
    private Album[] albums;
    /**
     * number of albums in collection
     */
    private int numAlbums;

    /**
     * Find the album index or -1 if the album is not in album array
     *
     * @param album object
     * @return int index or -1 if not found
     */
    private int find(Album album) {
        int i;
        if (numAlbums == 0) {
            albumStart();
        }
        for (i = 0; i < numAlbums; i++) {
            if (albums[i].equals(album)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Increase the capacity of the array list by 4
     */
    private void grow() {
        Album[] arr = new Album[albums.length + 4];
        int i;
        for (i = 0; i < numAlbums; i++) {
            arr[i] = albums[i];
        }

        albums = arr;
    }

    /**
     * Adds album to the array list if it does not already exist in array list
     * @param album object
     * @return true if the album is added, false if the album already exists in the list
     */
    public boolean add(Album album) {
        if (find(album) == -1) {
            int length = albums.length;
            if (albums[length - 1] == null) {
                grow();
            }
            albums[numAlbums] = album;
            numAlbums++;
            return true;
        }
        return false;
    }

    /**
     * Removes album and keeps the same order if the album exists in array list
     * @param album object
     * @return true if the album is removed, and false if the album is not in the list
     */
    public boolean remove(Album album) {
        int i = find(album);
        if (i == -1) {
            return false;
        }
        Album[] temp = new Album[albums.length];
        int j;
        for (j = 0; j < i; j++) {
            temp[j] = albums[j];
        }
        for (i = i + 1; i < numAlbums; i++) {
            temp[i - 1] = albums[i];
        }
        albums = temp;
        numAlbums--;
        return true;
    }

    /**
     * Set to not available
     * @param album object
     * @return true if the getAvailable field is changed to false,
     * false if it is not in the list or the field is already false
     */
    public boolean lendingOut(Album album) {
        int i;
        for (i = 0; i < numAlbums; i++) {
            if ((albums[i].equals(album)) && albums[i].getAvailable()) {
                albums[i].setAvailable(false);
                return true;
            }
        }
        return false;
    }

    /**
     * Set to available
     * @param album object
     * @return true if the getAvailable field is changed to true,
     * false if it is not in the list or the field is already true
     */
    public boolean returnAlbum(Album album) {
        int i;
        for (i = 0; i < numAlbums; i++) {
            if (albums[i].equals(album) && !albums[i].getAvailable()) {
                albums[i].setAvailable(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Display the list without specifying the order
     */
    public void print() {
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
        } else {
            System.out.println("*List of albums in the collection.");
            for (int i = 0; i < numAlbums; i++) {
                System.out.println(albums[i].toString());
            }
            System.out.println("*End of List");
        }
    }

    /**
     * Prints by release date
     */
    public void printByReleaseDate() {
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
        } else {
            inOrderDate();
            System.out.println("*Album collection by release date.");
            for (int i = 0; i < numAlbums; i++) {
                System.out.println(albums[i].toString());
            }
            System.out.println("*End of List");
        }
    }

    /**
     * Prints by genre
     */
    public void printByGenre() {
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
        } else {
            inOrderGenre();
            System.out.println("*Album collection by genre.");
            for (int i = 0; i < numAlbums; i++) {
                System.out.println(albums[i].toString());
            }
            System.out.println("*End of List");
        }
    }


    /**
     * Orders the album list by Date
     */
    private void inOrderDate() {
        int n = numAlbums;
        for (int i = 1; i < n; i++) {
            Album key = albums[i];
            int j = i - 1;
            Date temp = key.getReleaseDate();
            while (j >= 0 && temp.compareTo(albums[j].getReleaseDate()) > 0) {
                albums[j + 1] = albums[j];
                j = j - 1;
            }
            albums[j + 1] = key;
        }
    }

    /**
     * Orders the album list by Genre
     */
    private void inOrderGenre() {
        int n = numAlbums;
        for (int i = 1; i < n; i++) {
            Album key = albums[i];
            int j = i - 1;
            while (j >= 0 && albums[j].getGenre().ordinal() > key.getGenre().ordinal()) {
                albums[j + 1] = albums[j];
                j = j - 1;
            }
            albums[j + 1] = key;
        }
    }

    /**
     * Initializes album
     */
    private void albumStart() {
        albums = new Album[1];
    }

}
