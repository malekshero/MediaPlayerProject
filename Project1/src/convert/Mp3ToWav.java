package convert;

import java.io.File;
import javazoom.jl.converter.Converter;

public class Mp3ToWav {
    
    private String filePath;
    private File selectedFile;
        
    public Object construct( String newName ) {
        return doWork( newName );
    }
    
    public void applyConvert( String newName ) {
        construct( newName );
    }
    
    public Object doWork( String newName ) {
        
        File groupOfFiles = new File(filePath);
        setSelectedFile(groupOfFiles);
        
        try {
            
            System.out.println("Please Wait : " + selectedFile.getName() + " still in converting...");
            
            Converter myConverter = new Converter();
            myConverter.convert(getSelectedFile().getPath(), "C:\\Users\\samra\\Music\\" + newName + ".wav");

        } catch (Exception error) {
            
            System.out.println("Converting Process Fail For : " + selectedFile.getName());
            
        }
        
        return new Object();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }
    
}



