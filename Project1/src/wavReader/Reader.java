package wavReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioFormat.Encoding;

/**
 * Class that allows directly passing PCM float mono data to the sound card for
 * playback. The sampling rate of the PCM data must be 44100Hz.
 *
 * @author mzechner
 *
 */
public class Reader {

    /**
     * the buffer size in samples *
     */
    private static int BUFFER_SIZE = 1024;

    /**
     * the java sound line we write our samples to *
     */
    private SourceDataLine out;

    /**
     * buffer for BUFFER_SIZE 16-bit samples *
     */
    private byte[] buffer;
    private String filePath;

    private static boolean playState;
    private static boolean stopState;
    
    private float[] samples;
    private WavDecoder reader;

    /**
     * Constructor, initializes the audio system for 44100Hz 16-bit signed mono
     * output.
     *
     * @throws Exception in case the audio system could not be initialized
     */
    public Reader( String Path ) throws Exception {
        filePath = Path;
        buffer = new byte[BUFFER_SIZE * 2];
        samples = new float[1024];
        reader = new WavDecoder(new FileInputStream(filePath));
        AudioFormat format = new AudioFormat(Encoding.PCM_SIGNED, 44100, 16, 1, 2, 44100, false);
        out = AudioSystem.getSourceDataLine(format);
        out.open(format);
        out.start();
        playState = true;
        stopState = false;
        
    }

    /**
     * Writes the given samples to the audio device. The samples have to be
     * sampled at 44100Hz, mono and have to be in the range [-1,1].
     *
     * @param samples The samples.
     */
    public void writeSamples(float[] samples) {
        fillBuffer(samples);
        getOut().write(getBuffer(), 0, getBuffer().length);
    }

    private void fillBuffer(float[] samples) {
        for (int i = 0, j = 0; i < samples.length; i++, j += 2) {
            short value = (short) (samples[i] * Short.MAX_VALUE);
            getBuffer()[j] = (byte) (value | 0xff);
            getBuffer()[j + 1] = (byte) (value >> 8);
        }
    }

    public void play() throws Exception {
        
        new Thread(){
            
            @Override
            public void run() {
                if (playState = true) {
                    while (getReader().readSamples(getSamples()) > 0 && isStopState() == false ) {
                        
                        writeSamples(getSamples());
                        while( isPlayState() == false && isStopState() == false ) {
                            try {
                                sleep(1);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                    
                }
            }
            
        }.start();

    }
    
    public void resume() {
        setPlayState(true);
    }
    
    public void pause() {
        setPlayState(false);
    }
    
    public void stop() {
        setStopState(true);
        out.stop();
    }

    public static int getBUFFER_SIZE() {
        return BUFFER_SIZE;
    }

    public static void setBUFFER_SIZE(int aBUFFER_SIZE) {
        BUFFER_SIZE = aBUFFER_SIZE;
    }

    public SourceDataLine getOut() {
        return out;
    }

    public void setOut(SourceDataLine out) {
        this.out = out;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public float[] getSamples() {
        return samples;
    }

    public void setSamples(float[] samples) {
        this.samples = samples;
    }

    public WavDecoder getReader() {
        return reader;
    }

    public void setReader(WavDecoder reader) {
        this.reader = reader;
    }
    
    public static void setPlayState(boolean aPlayState) {
        playState = aPlayState;
    }
    
    public static boolean isPlayState() {
        return playState;
    }

    public static boolean isStopState() {
        return stopState;
    }

    public static void setStopState(boolean aStopState) {
        stopState = aStopState;
    }

}
