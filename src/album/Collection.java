package album;
/**
 *
 */

/**
 * This class defines the array list data structure to hold the album
 * collection and provide the operations to manage the collection
 *
 * @author Jack Dunich
 *
 */
public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection

    //find the album index, or return NOT_FOUND
    private int find(Album album) {
        int i;
        for(i = 0; i < albums.length; i++){
            if(albums.equals(album)){
                    return i;
            }
        }
        return -1;
    }

    //increase the capacity of the array list by 4
    private void grow() {
        Album[] temp = copyOver(albums, 4);
        numAlbums += 4;
        albums = temp;
    }

    //adds album to the array list
    public boolean add(Album album) {
        if (find(album) == -1){return false;}
        Album[] temp = copyOver(albums, 1);
        temp[numAlbums] = album;
        numAlbums++;
        albums = temp;
        return true;
    }

    //removes album and keeps the same order
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

    //set to not available //TODO: isAvailible constructor
    public boolean lendingOut(Album album) {
        int i;
        for (i = 0; i < albums.length; i++){
            if (albums[i] == album && albums[i].getAvailable()){
                return true;
            }
        }
        return false;
    }

    //set to available //TODO: isAvailible getter
    public boolean returnAlbum(Album album) {
        int i;
        for (i = 0; i < albums.length; i++){
            if (albums[i] == album && !albums[i].getAvailable()){
                return true;
            }
        }
        return false;
    }

    //display the list without specifying the order
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

    //Prints by release date
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

    //Prints by genre
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

    //Copies the Album list and extends by n
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

    //Orders the album list by Date//TODO: getter for releaseDate
    public Album[] inOrderDate(Album[] album){
        int n = album.length;
        for (int i = 0; i < n; i++){
            Album key = album[i];
            int j = i - 1;
            Date temp = new Date(key.getReleaseDate());
            while(j >= 0 && temp.compareTo(new Date(album[j].getReleaseDate()))){
                album[j + 1] = album[j];
                j = j-1;
            }
            album[j + 1] =  key;
        }
        return album;
    }

    //Orders the album list by Genre //TODO: getter for genre
    public Album[] inOrderGenre(Album[] album){
        int n = album.length;
        for (int i = 1; i < n; i++){
            Album key = album[i];
            int j = i - 1;
            while(j >= 0 && album[j].genre.ordinal() > key.genre.ordinal()){
                album[j + 1] = album[j];
                j = j-1;
            }
            album[j + 1] =  key;
        }
        return album;
    }

}
