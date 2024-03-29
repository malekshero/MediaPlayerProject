package record;

import javax.sound.sampled.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import project_1.FXMLDocumentController;

public class SoundRecorder {
    
    public File wavFile = new File("C:\\Users\\samra\\Music\\Records\\RecordAudio.wav"); 
    // format of audio file  //wav sure//
    public AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE; 
    // the line from which audio data is captured
    public TargetDataLine line; 
    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
        return format;
    }
    /**
     * Captures the sound and record into a WAV file
     */
    public void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); 
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing 
            System.out.println("Start capturing..."); 
            AudioInputStream ais = new AudioInputStream(line); 
            System.out.println("Start recording..."); 
            // start recording
            AudioSystem.write(ais, fileType, wavFile); 
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    } 
    /**
     * Closes the target data line to finish capturing and recording
     */
    public void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }
}
