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
        if(numAlbums == 0) {
            albumStart();
        }
        for(i = 0; i < numAlbums; i++){
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
        Album[] arr = new Album[albums.length + 4];
        int i;
        for (i = 0; i < numAlbums; i++){
            arr[i] = albums[i];
        }

        albums =  arr;
    }

    /**
     * Adds album to the array list
     * @param album
     * @return true if the album is added, false if the album already exists in the list
     */
    public boolean add(Album album) {
        if (find(album) == -1) {
            int length = albums.length;
            if(albums[length-1] == null){grow();}
            albums[numAlbums] = album;
            numAlbums++;
            return true;
        }
        return false;
    }

    /**
     * Removes album and keeps the same order
     * @param album
     * @return true if it the album is removed, and false if the album is not in the list
     */
    public boolean remove(Album album) {
        int i = find(album);
        if (i == -1){return false;}
        Album[] temp = new Album[albums.length];
        int j;
        for (j = 0; j < i; j++){
            temp[j] = albums[j];
        }
        for (i = i+1; i < numAlbums; i++){
            temp[i-1] = albums[i];
        }
        albums = temp;
        numAlbums--;
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
        for (i = 0; i < numAlbums; i++){
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
        for (i = 0; i < numAlbums; i++){
            if (albums[i].equals(album) && !albums[i].getAvailable()){
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
        if (numAlbums == 0){
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
        if (numAlbums == 0){
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
        if (numAlbums == 0){
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
     * Orders the album list by Date //TODO: finish this method
     * @param album
     * @return edited album
     */
    public Album[] inOrderDate(Album[] album){
        int n = numAlbums;
        for (int i = 1; i < n; i++){
            Album key = album[i];
            int j = i - 1;
            Date temp = key.getReleaseDate();
            int dateCompare = temp.compareTo(album[j].getReleaseDate());
            while(j >= 0 && dateCompare > 0){
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
        int n = numAlbums;
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
    
    /**
     * Initializes album
     */
    public void albumStart(){
        Album[] temp = new Album[1];
        albums = temp;
    }

}
