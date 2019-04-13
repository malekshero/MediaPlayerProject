package mp3Reader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Mp3Player {
    
    private FileInputStream FIS;
    private BufferedInputStream BIS;
    
    private Player player;
    private long pauseLocation;
    private long songTotalLength;
    private String filePath;
    private boolean playState;
    private long currentTime;
    
    public Mp3Player(){
        
        pauseLocation = 0;
        songTotalLength = 0;
        filePath = "";
        playState = false;
        currentTime = 0;
    
    }
    
    public String printTime() throws IOException{
        
        setCurrentTime(getFIS().available());
        
        return "" + getCurrentTime();
    }
    
    public void Stop(){
        if(getPlayer() != null){
            getPlayer().close();
            
            setPauseLocation(0);
            setSongTotalLength(0);
            
        }
    }
    
    public void Pause() throws IOException{
        if(getPlayer() != null){
            try{
                setPauseLocation(getFIS().available());
                getPlayer().close();
              
            }
            catch(IOException ex){
                
            }
           
        }
    }
    
    public void Play(){
        
        try {
            setFIS(new FileInputStream(getFilePath()));
            setBIS(new BufferedInputStream(getFIS()));
            
            setPlayer(new Player(getBIS()));
            setSongTotalLength(getFIS().available());
            setFilePath(getFilePath() + "");
            
        } catch (FileNotFoundException  | JavaLayerException ex ) {
            
        } catch (IOException ex) {
            Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread(){
            @Override
            public void run(){
                try {
                    getPlayer().play();
                    
                    if(getPlayer().isComplete() && isPlayState() == true ){
                        Play();
                    }
                    
                } catch (JavaLayerException ex) {
                    
                }
            }
        }.start();
        
    }
    
    public void Resume(){
        
        try {
            setFIS(new FileInputStream(getFilePath()));
            setBIS(new BufferedInputStream(getFIS()));
            
            setPlayer(new Player(getBIS()));
            getFIS().skip(getSongTotalLength() - getPauseLocation());
    
        } catch (FileNotFoundException  | JavaLayerException ex ) {
            
        } catch (IOException ex) {
            Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        new Thread(){
            @Override
            public void run(){
                try {
                    getPlayer().play();
                } catch (JavaLayerException ex) {
                    
                }
            }
        }.start();
        
    }

    /**
     * @return the FIS
     */
    public FileInputStream getFIS() {
        return FIS;
    }

    /**
     * @param FIS the FIS to set
     */
    public void setFIS(FileInputStream FIS) {
        this.FIS = FIS;
    }

    /**
     * @return the BIS
     */
    public BufferedInputStream getBIS() {
        return BIS;
    }

    /**
     * @param BIS the BIS to set
     */
    public void setBIS(BufferedInputStream BIS) {
        this.BIS = BIS;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the pauseLocation
     */
    public long getPauseLocation() {
        return pauseLocation;
    }

    /**
     * @param pauseLocation the pauseLocation to set
     */
    public void setPauseLocation(long pauseLocation) {
        this.pauseLocation = pauseLocation;
    }

    /**
     * @return the songTotalLength
     */
    public long getSongTotalLength() {
        return songTotalLength;
    }

    /**
     * @param songTotalLength the songTotalLength to set
     */
    public void setSongTotalLength(long songTotalLength) {
        this.songTotalLength = songTotalLength;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the playState
     */
    public boolean isPlayState() {
        return playState;
    }

    /**
     * @param playState the playState to set
     */
    public void setPlayState(boolean playState) {
        this.playState = playState;
    }

    /**
     * @return the currentTime
     */
    public long getCurrentTime() {
        return currentTime;
    }

    /**
     * @param currentTime the currentTime to set
     */
    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
    
}

