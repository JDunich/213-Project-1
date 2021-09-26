package album;

/**
 * This class defines the array list data structure to hold the album
 * collection and provide the operations to manage the collection
 *
 * @author Jack Dunich, Kiana Perst
 */
public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection

    /**
     * Find the album index
     * @param album
     * @return int index or -1 if not found
     */
    private int find(Album album) {
        int i;
        for(i = 0; i < albums.length; i++){
            if(albums[i].equals(album)){
                    return i;
            }
        }
        return -1;
    }

    /**
     * Increase the capacity of the array list by 4
     */
    private void grow() {
        Album[] temp = copyOver(albums, 4);
        numAlbums += 4;
        albums = temp;
    }

    /**
     * Adds album to the array list
     * @param album
     * @return true if the album is added, false if the album already exists in the list
     */
    public boolean add(Album album) {
        if (find(album) == -1){
            Album[] temp = copyOver(albums, 1);
            temp[numAlbums] = album;
            numAlbums++;
            albums = temp;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Removes album and keeps the same order
     * @param album
     * @return true if it the album is removed, and false if the album is not in the list
     */
    public boolean remove(Album album) {
        int i = find(album);
        if (i == -1){return false;}
        Album[] temp = new Album[numAlbums-1];
        int j;
        for (j = 0; j < i; j++){
            temp[j] = albums[j];
        }
        for (i = i+1; i < albums.length - 1; i++){
            temp[i-1] = albums[i+1];
        }
        return true;
    }

    /**
     * Set to not available 
     * @param album
     * @return true if the getAvailable field is changed to false,
     *          false if it is not in the list or the field is already false
     */
    public boolean lendingOut(Album album) {
        int i;
        for (i = 0; i < albums.length; i++){
            if ((albums[i].equals(album)) && albums[i].getAvailable()){
                albums[i].setAvailable(false);
                return true;
            }
        }
        return false;
    }

    /**
     * Set to available
     * @param album
     * @return true if the getAvailable field is changed to true,
     *          false if it is not in the list or the field is already true
     */
    public boolean returnAlbum(Album album) {
        int i;
        for (i = 0; i < albums.length; i++){
            if (albums[i] == album && !albums[i].getAvailable()){
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
        if (albums == null){
            System.out.println("The collection is empty!");
        }else {
            System.out.println("*List of albums in the collection.");
            for(int i = 0; i < numAlbums; i++){
                System.out.println(albums[i].toString());
            }
            System.out.println("*End of List");
        }
    }

    /**
     * Prints by release date
     */
    public void printByReleaseDate() {
        if (albums == null){
            System.out.println("The collection is empty!");
        }else {
            Album[] temp = inOrderDate(albums);
            System.out.println("*Album collection by release date.");
            for(int i = 0; i < numAlbums; i++){
                System.out.println(temp[i].toString());
            }
            System.out.println("*End of List");
        }
    }

    /**
     * Prints by genre
     */
    public void printByGenre() {
        if (albums == null){
            System.out.println("The collection is empty!");
        }else {
            Album[] temp = inOrderGenre(albums);
            System.out.println("*Album collection by genre.");
            for(int i = 0; i < numAlbums; i++){
                System.out.println(temp[i].toString());
            }
            System.out.println("*End of List");
        }
    }

    /**
     * Copies the Album list and extends by n, alternative to the grow() method
     * @param album, num to extend the list
     * @return new Album array
     */
    public Album[] copyOver(Album[] album, int num){
        Album[] arr = new Album[album.length];
        int i;
        for (i = 0; i < album.length; i++){
            arr[i] = album[i];
        }
        for (i = album.length; i < (album.length + num); i++){
            arr[i] = null;
        }
        return arr;
    }

    /**
     * Orders the album list by Date //TODO: finish this method
     * @param album
     * @return edited album
     */
    public Album[] inOrderDate(Album[] album){
        int n = album.length;
        for (int i = 0; i < n; i++){
            Album key = album[i];
            int j = i - 1;
            Date temp = key.getReleaseDate();
            while(j >= 0 && temp.compareTo(album[j].getReleaseDate())){
                album[j + 1] = album[j];
                j = j-1;
            }
            album[j + 1] =  key;
        }
        return album;
    }

    /**
     * Orders the album list by Genre
     * @param album
     * @return edited album
     */
    public Album[] inOrderGenre(Album[] album){
        int n = album.length;
        for (int i = 1; i < n; i++){
            Album key = album[i];
            int j = i - 1;
            while(j >= 0 && album[j].getGenre().ordinal() > key.getGenre().ordinal()){
                album[j + 1] = album[j];
                j = j-1;
            }
            album[j + 1] =  key;
        }
        return album;
    }

}
