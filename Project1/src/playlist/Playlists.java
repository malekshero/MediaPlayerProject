package playlist;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlists implements Serializable {

    private ArrayList<Playlist> playlists = new ArrayList<>();
    
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    
    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
    
    public void addPlaylist( Playlist playlist ) {
        this.playlists.add( playlist );
    }
    
    public Playlist getPlaylist( int playlistNumber ) {
        return playlists.get(playlistNumber);
    }  
}
