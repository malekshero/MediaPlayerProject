package subtitle;

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class parseSubtitle {

    public Logger logger = Logger.getLogger(parseSubtitle.class);;
    private String strFilePath;
    private ArrayList<Subtitle> subtitlesWord = new ArrayList<>();
    
    public void main() {

        try {
                logger.info(SRTUtils.textTimeToMillis("01:00:00,360"));
        } catch (Exception e) {
                logger.error("error parsing time", e);
        }

        //strFilePath = System.getProperty("user.dir") + "\\files\\suben.srt";

        logger.info(strFilePath);

        ArrayList<Subtitle> subtitles = SRTParser
                        .getSubtitlesFromFile(strFilePath, false);

        logger.info(SRTUtils.findSubtitle(subtitles, 000100));
        
        this.setSubtitlesWord(subtitles);
//        for (Subtitle subtitle : subtitles) {
//                System.out.println(subtitle);
//        }
    }
    
    public String getStrFilePath() {
        return strFilePath;
    }
    
    public void setStrFilePath(String strFilePath) {
        this.strFilePath = strFilePath;
    }
    
    public ArrayList<Subtitle> getSubtitlesWord() {
        return subtitlesWord;
    }
    
    public void setSubtitlesWord(ArrayList<Subtitle> subtitlesWord) {
        this.subtitlesWord = subtitlesWord;
    }
}
