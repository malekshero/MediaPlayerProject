package project_1;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.log4j.Logger;
import subtitle.SRTParser;
import subtitle.SRTUtils;
import subtitle.Subtitle;
import subtitle.parseSubtitle;

public class PROJECT_1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);            // Create New Scene
        stage.initStyle(StageStyle.TRANSPARENT);  // Hide Header Bar
        stage.setScene(scene);                    // Set The Main Stage Scene
        stage.setMaximized(true);                 // Make The Application Full Screen
        stage.show();                             // Show Main Stage
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
