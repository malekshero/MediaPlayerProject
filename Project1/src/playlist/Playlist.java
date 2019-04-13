package playlist;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements Serializable {
    
    private String title;
    private String description;
    private int rating;
    private ArrayList<Video> videos = new ArrayList<>();
    
    public Playlist() {
        title ="";
        description = "";
        rating = 0;
    }
    
    public Playlist( String title, String description, int rating ) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rating;
    }

    public void setRate(int rate) {
        this.rating = rate;
    }
    
    public ArrayList<Video> getVideos() {
        return videos;
    }
    
    public void setVideos(ArrayList<Video> Videos) {
        this.videos = Videos;
    }
    
    public void addVideo( Video video ) {
        this.videos.add( video );
    }
    
    public Video getVideo( int videoNumber ) {
        return videos.get(videoNumber);
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}

