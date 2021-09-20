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

    @Override//find the album index, or return NOT_FOUND
    private int find(Album album) {
        int i;
        for(i = 0; i < albums.length; i++){
            if(albums.equals(album)){
                    return i;
            }
        }
        return -1;
    }

    @Override//increase the capacity of the array list by 4
    private void grow() {
        Album[] temp = copyOver(albums, 4);
    }

    @Override
    public boolean add(Album album) {
        Album[] temp = copyOver(albums, 1);
        temp[numAlbums] = album;
        albums = temp;
        return true;
    }

    @Override
    public boolean remove(Album album) {}

    @Override//set to not available
    public boolean lendingOut(Album album) {
        album.isAvailable = false;
    }

    @Override//set to available
    public boolean returnAlbum(Album album) {
        album.isAvailable = true;
    }

    @Override//display the list without specifying the order
    public void print() {
        System.out.println(albums);
    }

    @Override
    public void printByReleaseDate() {
        Album[] temp = inOrderDate(albums);
        System.out.println(temp);
    }

    @Override
    public void printByGenre() {
        Album[] temp = inOrderGenre(albums);
        System.out.println(temp);
    }

    @Override
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

    @Override
    public Album[] inOrderDate(Album[] album){

    }

}
