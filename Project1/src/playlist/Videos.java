package playlist;

import java.io.Serializable;
import java.util.ArrayList;


public class Videos implements Serializable {
    
    private ArrayList<Video> videos = new ArrayList<>();
    
    public ArrayList<Video> getVideos() {
        return videos;
    }
    
    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }
    
    public void addVideo( Video video ) {
        this.videos.add( video );
    }
    
    public void addVideo( int x, Video video ) {
        this.videos.add( x, video );
    }
    
    public Video getVideo( int videoNumber ) {
        return videos.get(videoNumber);
    }
    
}
