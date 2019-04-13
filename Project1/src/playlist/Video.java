package playlist;

import java.io.Serializable;

public class Video implements Serializable {
    
    private String path;
    private String title;
    private String description;
    private String type;
    private boolean like;
    private int playListId;
    private int rate;
    private float ResumePoint; 

    public Video() {
        path = "";
        description = "";
        like = false;
        playListId = 0;
        rate = 0;
        ResumePoint = 0;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean getLike() {
        return like;
    }
    
    public void setLike(boolean like) {
        this.like = like;
    }

    public int getPlayListId() {
        return playListId;
    }
    
    public void setPlayListId(int playListId) {
        this.playListId = playListId;
    }
    
    public int getRate() {
        return rate;
    }
    
    public void setRate(int rate) {
        this.rate = rate;
    }
    
    public float getResumePoint() {
        return ResumePoint;
    }
    
    public void setResumePoint(float ResumePoint) {
        this.ResumePoint = ResumePoint;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
