package project_1;

import com.jfoenix.controls.JFXSlider;
import convert.Mp3ToWav;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javax.swing.JFileChooser;
import mp3Reader.Mp3Player;
import playlist.Playlist;
import playlist.Playlists;
import playlist.Video;
import playlist.Videos;
import record.SoundRecorder;
import subtitle.parseSubtitle;
import subtitle.SRTParser;
import subtitle.SRTUtils;
import subtitle.Subtitle;
import wavReader.Reader;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane VIDEO_PLAYER_CONTAINER, DASHBOARD_CONTAINER;

    //  ***       ***  ***  *******    *******    ******
    //   ***     ***   ***  ***   **   ***       ***  ***
    //    ***   ***    ***  ***   ***  *******  ***    ***
    //     *** ***     ***  ***   **   ***       ***  ***
    //      *****      ***  *******    *******    ******
    @FXML
    private AnchorPane MENU_PLAY_PAUSE, MENU_SEEK_RIGHT, MENU_SEEK_LEFT, // Anchorpanes in the bottom main menu
            MENU_SOUND, MENU_REPLAY, MENU_SHUFFLE, // Anchorpanes in the right main menu
            MENU_MAIN_MENU, MENU_LIKE;                                   // Anchorpanes in the left main menu

    @FXML
    private AnchorPane TOP_EXIT, TOP_MINIMIZE, TOP_FULLSCREEN;                      // Anchorpanes in the top nav

    @FXML
    private ImageView TOP_EXIT_IMAGE, TOP_MINIMIZE_IMAGE, TOP_FULLSCREEN_IMAGE;     // Images in the top nav

    @FXML
    private ImageView MENU_PLAY_IMAGE, MENU_SEEK_RIGHT_IMAGE, MENU_SEEK_LEFT_IMAGE, // Images in the bottom main menu
            MENU_SOUND_IMAGE, MENU_REPLAY_IMAGE, MENU_SHUFFLE_IMAGE, // Images in the right main menu
            MENU_MAIN_MENU_IMAGE, MENU_PAUSE_IMAGE, MENU_UNLIKE_IMAGE, // Images in the left main menu
            MENU_LIKE_IMAGE;                                                        // Images in the left main menu

    @FXML
    private AnchorPane MENU, SUB_MENU, SUB_MENU_SUBTITLE, SOUND_VALUME, BOTTOM_SECTION_PANE,
            TOP_SECTION_PANE, VIDEO_CONTAINER;
    
    @FXML
    private AnchorPane OPTION_MEDIA, OPTION_VIDEO, OPTION_SUBTITLE, // Anchorpanes in the left menu 
            OPTION_VIEW, OPTION_HELP;                                // Anchorpanes in the left menu 

    @FXML
    private ImageView OPTION_ARRAW_MEDIA, OPTION_ARRAW_VIDEO, // Images in the left menu
            OPTION_ARRAW_SUBTITLE, OPTION_ARRAW_VIEW;                 // Images in the left menu

    @FXML
    private Text OPTION_TEXT_MEDIA, OPTION_TEXT_VIDEO, OPTION_TEXT_SUBTITLE, // text in the left menu
            OPTION_TEXT_VIEW, OPTION_TEXT_HELP;                              // text in the left menu

    @FXML
    private AnchorPane MEDIA_OPEN_FILE, MEDIA_OPEN_FOLDER, // option for "media" section in the left menu
            MEDIA_OPEN_MULTIPLE_FILE, MEDIA_CONVERT, MEDIA_EXIT;     // option for "media" section in the left menu

    @FXML
    private Text MEDIA_TEXT_OPEN_FILE, MEDIA_TEXT_OPEN_FOLDER, // text in the left menu
            MEDIA_TEXT_OPEN_MULTIPLE_FILE, MEDIA_TEXT_CONVERT, MEDIA_TEXT_EXIT;      // text in the left menu

    @FXML
    private AnchorPane SUBTITLE_ADD_FILE, SUBTITLE_DISABLE; // option for "subtitle" section in the left menu

    @FXML
    private Text SUBTITLE_TEXT_ADD_FILE, SUBTITLE_TEXT_DISABLE; // text in the left menu

    @FXML
    private Text SEEK_VALUE_TEXT, MEDIA_TITLE,
            SUBTITLE_CONTENT;
    
    @FXML
    private BorderPane borderpane;

    //  *******        ******       ******   ***  ***
    //  ***   **      ***  ***      ***      ***  ***
    //  ***   ***    ***    ***     ******   ********
    //  ***   **    *** **** ***       ***   ***  ***
    //  *******    ***        ***   ******   ***  ***
    @FXML
    private AnchorPane ELEMENT_CONTENT;
    
    @FXML
    private Button DASHBOARD_BUTTON_HOME, DASHBOARD_BUTTON_VIDEOS, DASHBOARD_BUTTON_MUSIC;
    
    @FXML
    private Text DASHBOARD_LABEL_HOME, DASHBOARD_LABEL_VIDEOS, DASHBOARD_LABEL_MUSIC;
    
    @FXML
    private AnchorPane DASHBOARD_PANE_LIKED_TRUCK, DASHBOARD_PANE_PLAYLISTS,
            DASHBOARD_PANE_RECENTLY_PLAYED, DASHBOARD_PANE_FOLDERS,
            DASHBOARD_PANE_EXIT;
    
    @FXML
    private Text DASHBOARD_LABEL_LIKED_TRUCK, DASHBOARD_LABEL_PLAYLISTS,
            DASHBOARD_LABEL_RECENTLY_PLAYED, DASHBOARD_LABEL_FOLDERS,
            DASHBOARD_LABEL_EXIT;
    
    @FXML
    private AnchorPane DASHBOARD_CONTENT_CONTAINER;
    
    @FXML
    private AnchorPane DASHBOARD_PANE_SETTINGS, DASHBOARD_PANE_SEARCH;
    
    @FXML
    private ImageView DASHBOARD_SETTINGS_IMAGE, DASHBOARD_SEARCH_IMAGE,
            DASHBOARD_EXIT_IMAGE;
    
    @FXML
    private TextField DASHBOARD_SEARCH_FIELD;
    
    @FXML
    private Pane DASHBOARD_PANE_HOME, DASHBOARD_PANE_VIDEOS, DASHBOARD_PANE_MUSIC;
    
    @FXML
    private AnchorPane DASHBOARD_ANNCHORPANE_1, DASHBOARD_ANNCHORPANE_2,
            DASHBOARD_ANNCHORPANE_3, DASHBOARD_ANNCHORPANE_4,
            DASHBOARD_ANNCHORPANE_5, DASHBOARD_ANNCHORPANE_6,
            DASHBOARD_ANNCHORPANE_7, DASHBOARD_ANNCHORPANE_8,
            DASHBOARD_ANNCHORPANE_9, DASHBOARD_ANNCHORPANE_10;
    
    @FXML
    private AnchorPane DASHBOARD_ANNCHORPANE_LISTENER_1, DASHBOARD_ANNCHORPANE_LISTENER_2,
            DASHBOARD_ANNCHORPANE_LISTENER_3, DASHBOARD_ANNCHORPANE_LISTENER_4,
            DASHBOARD_ANNCHORPANE_LISTENER_5, DASHBOARD_ANNCHORPANE_LISTENER_6,
            DASHBOARD_ANNCHORPANE_LISTENER_7, DASHBOARD_ANNCHORPANE_LISTENER_8,
            DASHBOARD_ANNCHORPANE_LISTENER_9, DASHBOARD_ANNCHORPANE_LISTENER_10;
    
    @FXML
    private AnchorPane ADD_FILE_1, ADD_FILE_2, ADD_FILE_3, ADD_FILE_4, ADD_FILE_5,
            ADD_FILE_6, ADD_FILE_7, ADD_FILE_8, ADD_FILE_9, ADD_FILE_10;
    
    @FXML
    private AnchorPane IMAGE_CONTAINER_1, IMAGE_CONTAINER_2, IMAGE_CONTAINER_3,
            IMAGE_CONTAINER_4, IMAGE_CONTAINER_5, IMAGE_CONTAINER_6,
            IMAGE_CONTAINER_7, IMAGE_CONTAINER_8, IMAGE_CONTAINER_9,
            IMAGE_CONTAINER_10;
    
    @FXML
    private AnchorPane PAGENATION_LEFT, PAGENATION_RIGHT, PAGENATION_CENTER;
    
    @FXML
    private ImageView PAGENATION_LEFT_IMAGE, PAGENATION_RIGHT_IMAGE,
            PAGENATION_LEFT_DARK_IMAGE, PAGENATION_RIGHT_DARK_IMAGE;
    
    @FXML
    private Text PAGENITION_TEXT;
    
    @FXML
    private Text TITLE_1, TITLE_2, TITLE_3, TITLE_4, TITLE_5,
            TITLE_6, TITLE_7, TITLE_8, TITLE_9, TITLE_10;
    
    @FXML
    private Text DESCRIPTION_1, DESCRIPTION_2, DESCRIPTION_3, DESCRIPTION_4, DESCRIPTION_5,
            DESCRIPTION_6, DESCRIPTION_7, DESCRIPTION_8, DESCRIPTION_9, DESCRIPTION_10;
    
    @FXML
    private MediaPlayer mediaplayer;
    
    @FXML
    private MediaView mediaView;
    
    private String filepath;
    
    @FXML
    private JFXSlider slider;
    
    @FXML
    private JFXSlider seekslider;
    
    @FXML
    private AnchorPane ADD_PLAYLIST, CONVERT_SONG, DASHBOARD, RECORD;
    
    @FXML
    private Button CREATE_PLAYLIST, CANCEL_PLAYLIST;
    @FXML
    private Button SAVE_NEW_NAME, CANCEL_NEW_NAME;
    
    @FXML
    private TextField TITLE_PLAYLIST, DESCRIPTION_PLAYLIST;
    @FXML
    private TextField SONG_NEW_NAME;

    @FXML
    private AnchorPane NOTIFICATION_LOADING, NOTIFICATION_MESSAGE;
    
    @FXML
    private ImageView GEAR;
    
    //
    //
    //
    //
    //
    
    @FXML
    private AnchorPane AUDIO_PLAYER_CONTAINER, IMAGE_CONTAINER,
            TITLE_CONTAINER;
    
    @FXML
    private ImageView AUDIO_PLAY_IMAGE, AUDIO_SEEK_RIGHT_IMAGE, AUDIO_SEEK_LEFT_IMAGE,
            AUDIO_CONVERT_IMAGE, AUDIO_REPLAY_IMAGE, AUDIO_PAUSE_IMAGE,
            AUDIO_UNLIKE_IMAGE, AUDIO_LIKE_IMAGE, AUDIO_OPEN_FILE_IMAGE;
    
    @FXML
    private Text AUDIO_SEEK_VALUE_TEXT, AUDIO_COMPLETE_TIME_TEXT, AUDIO_SONG_TITLE;
    
    @FXML
    private JFXSlider audioSeekSlider, counterSlider;
    
    
    
    @FXML
    private AnchorPane FIRST_THEME, SECOND_THEME, THIRD_THEME;
    @FXML
    private ImageView FIRST_THEME_IMAGE, SECOND_THEME_IMAGE, THIRD_THEME_IMAGE;
    @FXML
    private RadioButton FIRST_RADIOBUTTON, SECOND_RADIOBUTTON;
    
    @FXML
    public AnchorPane COLOR_1, COLOR_2, COLOR_3, COLOR_4,
            COLOR_5, COLOR_6, COLOR_7, COLOR_8,
            COLOR_9, COLOR_10, COLOR_11, COLOR_12,
            COLOR_13, COLOR_14, COLOR_15, COLOR_16;
    
    @FXML
    private Text THEME_TITLE, COLOR_TITLE, SEARCH_TITLE, CONVERT_TITLE, RECORD_TITLE, SUBTITLE_TITLE;
    
    @FXML
    private Button CHOOSE_FILE_BTN, CONVERT_BTN, RECORD_BTN;
    @FXML
    private Text FILE_PATH;
    
    @FXML
    private ImageView RECORD_IMG, STOP_IMG, PAUSE_IMG;
    
    @FXML
    private Text counter;
    
    private ArrayList<AnchorPane> containers = new ArrayList<>();
    private ArrayList<AnchorPane> containersListener = new ArrayList<>();
    private ArrayList<AnchorPane> addfile = new ArrayList<>();
    private ArrayList<AnchorPane> imageContainers = new ArrayList<>();
    private ArrayList<Text> titles = new ArrayList<>();
    private ArrayList<Text> descriptions = new ArrayList<>();
    
    private playlist.Playlists playlists = new Playlists();
    private playlist.Videos likedMedia = new Videos();
    private playlist.Videos recentlyPlayed = new Videos();
    
    private playlist.Playlists musicPlaylists = new Playlists();
    private playlist.Videos musicLikedMedia = new Videos();
    private playlist.Videos musicRecentlyPlayed = new Videos();
    
    Videos searchVideo = new Videos();

    // Transition Variables
    @FXML
    private static final FadeTransition FADEIN = new FadeTransition();      // Fade In Effect Transition
    private static final FadeTransition FADEOUT = new FadeTransition();      // Fade Out Effect Transition
    private static final ScaleTransition SCALE = new ScaleTransition();     // Scale Effect Transition
    private static final ScaleTransition SCALEMINIMIZE = new ScaleTransition();     // Scale Effect Transition
    private static final RotateTransition FADETRANSITION = new RotateTransition();

    private boolean menuStatus = false;                 // this var response of show and hide the bottom menu   
    private boolean subMenuStatus = false;              // this var response of show and hide the bottom sub menu
    private boolean subMenuSubtitleStatus = false;      // this var response of show and hide the bottom sub menu
    private boolean subtitleFound = false;              // this var response of show and hide the bottom sub menu
    private boolean bottomNavStatus = false;            // this var response of show and hide the bottom nav
    private boolean topNavStatus = false;               // this var response of show and hide the top nav
    private boolean soundValumeStatus = false;          // this var response of show and hide the sound box
    private boolean replayStatus = false;               // this var response of show and hide the replay box
    private boolean shuffleStatus = false;              // this var response of show and hide the shuffle box
    private boolean PauseStatus = false;                // this var response of show and hide the play and pause box
    private boolean likeStatus = false;                 // this var response of show and hide like box
    private static boolean hideTopNav = true;           // this var check from state of top nav
    private static boolean hideBottomNav = true;        // this var check from state of bottom nav
    private static boolean openFileState = false;       // this var check from state open file issue
    private boolean subtitleStatus = false;             // this var check from status of subtitle
    boolean isVideo = true;
    boolean isDashboardOpened = false;
    boolean recordState = false;
    boolean recordStopState = false;
    
    private static String State = "LIKEDTRACK";
    private static String menuState = "VIDEO";
    private static String mediaType;
    private static String filePathConvert;
    private static String mainColor = "#3498db";
    
    private static int NUMBER_OF_INDEX = 0;
    private static int CURRENT_PLAYLIST = 0;
    
    GaussianBlur blur = new GaussianBlur();
    GaussianBlur dashboardBlur = new GaussianBlur();
    
    int pageNumberPlaylist = 1;                       // this var related on the number pf pagges in playlist
    int pageNumberLikedMedia = 1;                     // this var related on the number pf pagges in liked media
    int rapitatipn = 1;
    
    Reader reader;
    Mp3Player mp3player = new Mp3Player();
    SoundRecorder recorder = new SoundRecorder();
    private parseSubtitle subtitle;
    
    //                                        //
    //  START TRANSITION AND EFFECT SECTION   //
    //                                        //
    
    // Start Rotate Animation Function
    private void rotateTransition(ImageView imageview, int millis) {
        FADETRANSITION.setNode(imageview);                              // The node that we want to rotate it
        FADETRANSITION.setByAngle(720);                                 // Number of rounds which the node rotate
        FADETRANSITION.setDuration(Duration.millis(millis));            // The period of movment
        FADETRANSITION.setRate(1);                                      // Speed of Node while it rotate
        FADETRANSITION.setCycleCount(1);                                // Number of repetition of the movment
        FADETRANSITION.play();                                          // Finish and play the function
    }
    // End Rotate Animation Function
    
    // Start Fade In Animation Function
    private void showTransition(AnchorPane anchorPane, int millis) {
        FADEIN.setNode(anchorPane);                                    // The node that we want to show it
        FADEIN.setDuration(Duration.millis(millis));                   // Show the node in a half minute
        FADEIN.setFromValue(0.0);                                      // Start Showing from 0 opacity
        FADEIN.setToValue(1.0);                                        // End Showing in 1 opacity
        anchorPane.setVisible(true);                                   // Make the node visible after transition
        FADEIN.play();                                                 // Finish and play the function
    }
    // End Fade In Animation Function

    // Start fade out animation function
    private void hideTransition(AnchorPane anchorPane, int millis) {
        FADEOUT.setNode(anchorPane);                        // The node that we want to show it
        FADEOUT.setDuration(Duration.millis(millis));          // Show the node in a half minute
        FADEOUT.setFromValue(1.0);                          // Start Showing from 0 opacity
        FADEOUT.setToValue(0.0);                            // End Showing in 1 opacity
        anchorPane.setVisible(false);                       // Make the node visible after transition
        FADEOUT.play();                                     // Finish and play the function
    }
    // End Fade out Animation Function

    // Start Fade In Animation Function
    private void showTransition(ImageView imageview, int millis) {
        FADEIN.setNode(imageview);                                    // The node that we want to show it
        FADEIN.setDuration(Duration.millis(millis));                   // Show the node in a half minute
        FADEIN.setFromValue(0.0);                                      // Start Showing from 0 opacity
        FADEIN.setToValue(1.0);                                        // End Showing in 1 opacity
        imageview.setVisible(true);                                   // Make the node visible after transition
        FADEIN.play();                                                 // Finish and play the function
    }
    // End Fade In Animation Function

    // Start fade out animation function
    private void hideTransition(ImageView imageview, int millis) {
        FADEOUT.setNode(imageview);                        // The node that we want to show it
        FADEOUT.setDuration(Duration.millis(millis));          // Show the node in a half minute
        FADEOUT.setFromValue(1.0);                          // Start Showing from 0 opacity
        FADEOUT.setToValue(0.0);                            // End Showing in 1 opacity
        imageview.setVisible(false);                       // Make the node visible after transition
        FADEOUT.play();                                     // Finish and play the function
    }
    // End Fade out Animation Function

    // Start Fade In Animation Function
    private void showTransition(AnchorPane anchorPane, int millis, FadeTransition FADEINEFFECT) {
        
        FADEINEFFECT.setNode(anchorPane);                                    // The node that we want to show it
        FADEINEFFECT.setDuration(Duration.millis(millis));                   // Show the node in a half minute
        FADEINEFFECT.setFromValue(0.0);                                      // Start Showing from 0 opacity
        FADEINEFFECT.setToValue(1.0);                                        // End Showing in 1 opacity
        anchorPane.setVisible(true);                                                    // Make the node visible after transition
        FADEINEFFECT.play();                                                 // Finish and play the function
    }
    // End Fade In Animation Function

    // Start Scale Animation Function
    private void scaleTransition(ImageView imageview, int millis, float scale) {
        SCALE.setNode(imageview);                                    // The node that we want to show it
        SCALE.setDuration(Duration.millis(millis));                  // Show the node in a half minute
        SCALE.setToX(scale);                                         // Scale the node in X
        SCALE.setToY(scale);                                         // Scale the node in Y
        SCALE.setCycleCount(1);                                      // Set The Time Of Repeating
        SCALE.play();                                                // Play The Animation
    }
    // End Scale Animation Function

    // Start Scale Animation Function
    private void scaleTransition(AnchorPane anchorPane, int millis, float scale) {
        SCALE.setNode(anchorPane);                                    // The node that we want to show it
        SCALE.setDuration(Duration.millis(millis));                  // Show the node in a half minute
        SCALE.setToX(scale);                                         // Scale the node in X
        SCALE.setToY(scale);                                         // Scale the node in Y
        SCALE.setCycleCount(1);                                      // Set The Time Of Repeating
        SCALE.play();                                                // Play The Animation
    }
    // End Scale Animation Function

    // Start Scale Animation Function
    private void scaleTransitionMinimize(ImageView imageview, int millis, float scale) {
        SCALEMINIMIZE.setNode(imageview);                            // The node that we want to show it
        SCALEMINIMIZE.setDelay(Duration.millis(millis));             // Show the node after spicifec time
        SCALEMINIMIZE.setDuration(Duration.millis(millis));          // Show the node in a half minute
        SCALEMINIMIZE.setToX(scale);                                 // Scale the node in X
        SCALEMINIMIZE.setToY(scale);                                 // Scale the node in Y
        SCALEMINIMIZE.setCycleCount(1);                              // Set The Time Of Repeating
        SCALEMINIMIZE.play();                                        // Play The Animation
    }
    // End Scale Animation Function

    // Start Scale Animation Function
    private void scaleTransitionMinimize(AnchorPane anchorPane, int millis, float scale) {
        SCALEMINIMIZE.setNode(anchorPane);                            // The node that we want to show it
        SCALEMINIMIZE.setDuration(Duration.millis(millis));          // Show the node in a half minute
        SCALEMINIMIZE.setToX(scale);                                 // Scale the node in X
        SCALEMINIMIZE.setToY(scale);                                 // Scale the node in Y
        SCALEMINIMIZE.setCycleCount(1);                              // Set The Time Of Repeating
        SCALEMINIMIZE.play();                                        // Play The Animation
    }
    // End Scale Animation Function

    // Start Remove Active Section Button Effect
    private void noneActiveSection(Pane pane) {
        pane.setVisible(false);                            // Set Underline Unvisible
    }
    // End Remove Active Section Button Effect

    // Start Add Active Section Button Effect
    private void activeSection(Pane pane) {
        pane.setVisible(true);                      // Set Underline Visible
    }
    // End Add Active Section Button Effect

    // Start Hide Show Effect
    private void setActiveSctionButton(Pane pane) {
        noneActiveSection(DASHBOARD_PANE_HOME);                     // Hide Home Active Effect
        noneActiveSection(DASHBOARD_PANE_VIDEOS);                   // Hide Video Active Effect
        noneActiveSection(DASHBOARD_PANE_MUSIC);                    // Hide Music Active Effect
        activeSection(pane);                                        // Show The Active Effect For Active Section
    }
    // End Hide Show Effect

    // Start Remove Active Section Button Effect
    private void noneActiveSection(Text text) {
        text.setStyle("-fx-opacity: .4");                           // Set Underline Unvisible
    }
    // End Remove Active Section Button Effect

    // Start Add Active Section Button Effect
    private void activeSection(Text text) {
        text.setStyle("-fx-opacity: 1");                      // Set Underline Visible
    }
    // End Add Active Section Button Effect

    // Start Hide Show Effect
    private void setActiveSctionButton(Text text) {
        noneActiveSection(DASHBOARD_LABEL_LIKED_TRUCK);
        noneActiveSection(DASHBOARD_LABEL_PLAYLISTS);
        noneActiveSection(DASHBOARD_LABEL_FOLDERS);
        noneActiveSection(DASHBOARD_LABEL_RECENTLY_PLAYED);
        activeSection(text);
    }
    // End Hide Show Effect

    // Start Hide Show Effect
    private void deleteEffect() {
        
        DASHBOARD_LABEL_LIKED_TRUCK.setEffect(null);
        DASHBOARD_LABEL_RECENTLY_PLAYED.setEffect(null);
        DASHBOARD_LABEL_FOLDERS.setEffect(null);
        DASHBOARD_LABEL_PLAYLISTS.setEffect(null);
        
    }
    // End Hide Show Effect

    //                                        //
    //  END TRANSITION AND EFFECT SECTION     //
    //                                        //
    
    //                       //
    //  START HOVER SECTION  //
    //                       //
    
    // Start hover function - this func to add hover effect for bottom menu element 
    @FXML
    private void MouseHoverBottomMenu(MouseEvent event) {
        if (event.getTarget() == MENU_SEEK_LEFT || event.getTarget() == MENU_SEEK_LEFT_IMAGE) {
            MENU_SEEK_LEFT.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MENU_SEEK_RIGHT || event.getTarget() == MENU_SEEK_RIGHT_IMAGE) {
            MENU_SEEK_RIGHT.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MENU_PLAY_PAUSE || event.getTarget() == MENU_PLAY_IMAGE || event.getTarget() == MENU_PAUSE_IMAGE) {
            MENU_PLAY_PAUSE.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MENU_SOUND || event.getTarget() == MENU_SOUND_IMAGE) {
            MENU_SOUND.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MENU_REPLAY || event.getTarget() == MENU_REPLAY_IMAGE) {
            MENU_REPLAY.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MENU_SHUFFLE || event.getTarget() == MENU_SHUFFLE_IMAGE) {
            MENU_SHUFFLE.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MENU_MAIN_MENU || event.getTarget() == MENU_MAIN_MENU_IMAGE) {
            MENU_MAIN_MENU.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MENU_LIKE || event.getTarget() == MENU_LIKE_IMAGE || event.getTarget() == MENU_UNLIKE_IMAGE) {
            MENU_LIKE.setStyle(" -fx-background-color: " + mainColor + ";");
        }
        
        if (event.getTarget() == OPTION_MEDIA || event.getTarget() == OPTION_ARRAW_MEDIA || event.getTarget() == OPTION_TEXT_MEDIA) {
            OPTION_MEDIA.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == OPTION_VIDEO || event.getTarget() == OPTION_ARRAW_VIDEO || event.getTarget() == OPTION_TEXT_VIDEO) {
            OPTION_VIDEO.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == OPTION_SUBTITLE || event.getTarget() == OPTION_ARRAW_SUBTITLE || event.getTarget() == OPTION_TEXT_SUBTITLE) {
            OPTION_SUBTITLE.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == OPTION_VIEW || event.getTarget() == OPTION_ARRAW_VIEW || event.getTarget() == OPTION_TEXT_VIEW) {
            OPTION_VIEW.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == OPTION_HELP || event.getTarget() == OPTION_TEXT_HELP) {
            OPTION_HELP.setStyle(" -fx-background-color: " + mainColor + ";");
        }
        
        if (event.getTarget() == MEDIA_OPEN_FILE || event.getTarget() == MEDIA_TEXT_OPEN_FILE) {
            MEDIA_OPEN_FILE.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MEDIA_OPEN_FOLDER || event.getTarget() == MEDIA_TEXT_OPEN_FOLDER) {
            MEDIA_OPEN_FOLDER.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MEDIA_OPEN_MULTIPLE_FILE || event.getTarget() == MEDIA_TEXT_OPEN_MULTIPLE_FILE) {
            MEDIA_OPEN_MULTIPLE_FILE.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MEDIA_CONVERT || event.getTarget() == MEDIA_TEXT_CONVERT) {
            MEDIA_CONVERT.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == MEDIA_EXIT || event.getTarget() == MEDIA_TEXT_EXIT) {
            MEDIA_EXIT.setStyle(" -fx-background-color: " + mainColor + ";");
        }
        
        if (event.getTarget() == SUBTITLE_ADD_FILE || event.getTarget() == SUBTITLE_TEXT_ADD_FILE) {
            SUBTITLE_ADD_FILE.setStyle(" -fx-background-color: " + mainColor + ";");
        } else if (event.getTarget() == SUBTITLE_DISABLE || event.getTarget() == SUBTITLE_TEXT_DISABLE) {
            SUBTITLE_DISABLE.setStyle(" -fx-background-color: " + mainColor + ";");
        }
        
    }
    // End hover function  

    // Start exit function - this func to hide the hover effect for bottom menu
    @FXML
    private void MouseExitbottomMenu(MouseEvent event) {
        if (event.getTarget() == MENU_SEEK_LEFT || event.getTarget() == MENU_SEEK_LEFT_IMAGE) {
            MENU_SEEK_LEFT.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == MENU_SEEK_RIGHT || event.getTarget() == MENU_SEEK_RIGHT_IMAGE) {
            MENU_SEEK_RIGHT.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == MENU_PLAY_PAUSE || event.getTarget() == MENU_PLAY_IMAGE || event.getTarget() == MENU_PAUSE_IMAGE) {
            MENU_PLAY_PAUSE.setStyle(" -fx-background-color: transparent; ");
        } else if ((event.getTarget() == MENU_SOUND || event.getTarget() == MENU_SOUND_IMAGE) && soundValumeStatus == false) {
            MENU_SOUND.setStyle(" -fx-background-color: transparent; ");
        } else if ((event.getTarget() == MENU_REPLAY || event.getTarget() == MENU_REPLAY_IMAGE) && replayStatus == false) {
            MENU_REPLAY.setStyle(" -fx-background-color: transparent; ");
        } else if ((event.getTarget() == MENU_SHUFFLE || event.getTarget() == MENU_SHUFFLE_IMAGE) && shuffleStatus == false) {
            MENU_SHUFFLE.setStyle(" -fx-background-color: transparent; ");
        } else if ((event.getTarget() == MENU_MAIN_MENU || event.getTarget() == MENU_MAIN_MENU_IMAGE) && menuStatus == false) {
            MENU_MAIN_MENU.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == MENU_LIKE || event.getTarget() == MENU_LIKE_IMAGE || event.getTarget() == MENU_UNLIKE_IMAGE) {
            MENU_LIKE.setStyle(" -fx-background-color: transparent; ");
        }
        
        if ((event.getTarget() == OPTION_MEDIA || event.getTarget() == OPTION_ARRAW_MEDIA || event.getTarget() == OPTION_TEXT_MEDIA) && subMenuStatus == false) {
            OPTION_MEDIA.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == OPTION_VIDEO || event.getTarget() == OPTION_ARRAW_VIDEO || event.getTarget() == OPTION_TEXT_VIDEO) {
            OPTION_VIDEO.setStyle(" -fx-background-color: transparent; ");
        } else if ((event.getTarget() == OPTION_SUBTITLE || event.getTarget() == OPTION_ARRAW_SUBTITLE || event.getTarget() == OPTION_TEXT_SUBTITLE) && subMenuSubtitleStatus == false ) {
            OPTION_SUBTITLE.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == OPTION_VIEW || event.getTarget() == OPTION_ARRAW_VIEW || event.getTarget() == OPTION_TEXT_VIEW) {
            OPTION_VIEW.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == OPTION_HELP || event.getTarget() == OPTION_TEXT_HELP) {
            OPTION_HELP.setStyle(" -fx-background-color: transparent; ");
        }
        
        if (event.getTarget() == MEDIA_OPEN_FILE || event.getTarget() == MEDIA_TEXT_OPEN_FILE) {
            MEDIA_OPEN_FILE.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == MEDIA_OPEN_FOLDER || event.getTarget() == MEDIA_TEXT_OPEN_FOLDER) {
            MEDIA_OPEN_FOLDER.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == MEDIA_OPEN_MULTIPLE_FILE || event.getTarget() == MEDIA_TEXT_OPEN_MULTIPLE_FILE) {
            MEDIA_OPEN_MULTIPLE_FILE.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == MEDIA_CONVERT || event.getTarget() == MEDIA_TEXT_CONVERT) {
            MEDIA_CONVERT.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == MEDIA_EXIT || event.getTarget() == MEDIA_TEXT_EXIT) {
            MEDIA_EXIT.setStyle(" -fx-background-color: transparent; ");
        }
        
        if (event.getTarget() == SUBTITLE_ADD_FILE || event.getTarget() == SUBTITLE_TEXT_ADD_FILE) {
            SUBTITLE_ADD_FILE.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == SUBTITLE_DISABLE || event.getTarget() == SUBTITLE_TEXT_DISABLE) {
            SUBTITLE_DISABLE.setStyle(" -fx-background-color: transparent; ");
        }
        
    }
    // End exit function

    // Start hover function - this func to add hover effect for top menu element 
    @FXML
    private void MouseHoverTopNav(MouseEvent event) {
        if (event.getTarget() == TOP_EXIT || event.getTarget() == TOP_EXIT_IMAGE) {
            TOP_EXIT.setStyle(" -fx-background-color: #C10C0C; ");
        } else if (event.getTarget() == TOP_MINIMIZE || event.getTarget() == TOP_MINIMIZE_IMAGE) {
            TOP_MINIMIZE.setStyle(" -fx-background-color: #848383; ");
        } else if (event.getTarget() == TOP_FULLSCREEN || event.getTarget() == TOP_FULLSCREEN_IMAGE) {
            TOP_FULLSCREEN.setStyle(" -fx-background-color: #848383; ");
        }
    }
    // End hover function  

    // Start exit function - this func to hide the hover effect for top menu
    @FXML
    private void MouseExitTopNav(MouseEvent event) {
        if (event.getTarget() == TOP_EXIT || event.getTarget() == TOP_EXIT_IMAGE) {
            TOP_EXIT.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == TOP_MINIMIZE || event.getTarget() == TOP_MINIMIZE_IMAGE) {
            TOP_MINIMIZE.setStyle(" -fx-background-color: transparent; ");
        } else if (event.getTarget() == TOP_FULLSCREEN || event.getTarget() == TOP_FULLSCREEN_IMAGE) {
            TOP_FULLSCREEN.setStyle(" -fx-background-color: transparent; ");
        }
    }
    // End exit function

    // Start Mouse Hover Element function - this func to add hover effect for top menu element 
    @FXML
    private void MouseHoverElement(MouseEvent event) {
        
        if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_1) {
            scaleTransition(DASHBOARD_ANNCHORPANE_1, 300, (float) 1.03);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_2) {
            scaleTransition(DASHBOARD_ANNCHORPANE_2, 300, (float) 1.03);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_3) {
            scaleTransition(DASHBOARD_ANNCHORPANE_3, 300, (float) 1.03);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_4) {
            scaleTransition(DASHBOARD_ANNCHORPANE_4, 300, (float) 1.03);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_5) {
            scaleTransition(DASHBOARD_ANNCHORPANE_5, 300, (float) 1.03);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_6) {
            scaleTransition(DASHBOARD_ANNCHORPANE_6, 300, (float) 1.03);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_7) {
            scaleTransition(DASHBOARD_ANNCHORPANE_7, 300, (float) 1.03);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_8) {
            scaleTransition(DASHBOARD_ANNCHORPANE_8, 300, (float) 1.03);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_9) {
            scaleTransition(DASHBOARD_ANNCHORPANE_9, 300, (float) 1.03);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_10) {
            scaleTransition(DASHBOARD_ANNCHORPANE_10, 300, (float) 1.03);
        }
        
        if (event.getTarget() == PAGENATION_LEFT) {
            PAGENATION_LEFT.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0; -fx-border-color: transparent");
            PAGENATION_LEFT_IMAGE.setVisible(false);
            PAGENATION_LEFT_DARK_IMAGE.setVisible(true);
        } else if (event.getTarget() == PAGENATION_RIGHT) {
            PAGENATION_RIGHT_IMAGE.setVisible(false);
            PAGENATION_RIGHT_DARK_IMAGE.setVisible(true);
            PAGENATION_RIGHT.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0; -fx-border-color: transparent");
        }
        
    }
    // End  Mouse Hover Element function

    // Start Mouse Exit Element function - this func to hide the hover effect for top menu
    @FXML
    private void MouseExitElement(MouseEvent event) {
        
        if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_1) {
            scaleTransitionMinimize(DASHBOARD_ANNCHORPANE_1, 300, (float) 1);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_2) {
            scaleTransitionMinimize(DASHBOARD_ANNCHORPANE_2, 300, (float) 1);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_3) {
            scaleTransitionMinimize(DASHBOARD_ANNCHORPANE_3, 300, (float) 1);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_4) {
            scaleTransitionMinimize(DASHBOARD_ANNCHORPANE_4, 300, (float) 1);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_5) {
            scaleTransitionMinimize(DASHBOARD_ANNCHORPANE_5, 300, (float) 1);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_6) {
            scaleTransitionMinimize(DASHBOARD_ANNCHORPANE_6, 300, (float) 1);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_7) {
            scaleTransitionMinimize(DASHBOARD_ANNCHORPANE_7, 300, (float) 1);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_8) {
            scaleTransitionMinimize(DASHBOARD_ANNCHORPANE_8, 300, (float) 1);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_9) {
            scaleTransitionMinimize(DASHBOARD_ANNCHORPANE_9, 300, (float) 1);
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_10) {
            scaleTransitionMinimize(DASHBOARD_ANNCHORPANE_10, 300, (float) 1);
        }
        
        if (event.getTarget() == PAGENATION_LEFT) {
            PAGENATION_LEFT_DARK_IMAGE.setVisible(false);
            PAGENATION_LEFT_IMAGE.setVisible(true);
            PAGENATION_LEFT.setStyle("-fx-background-color: transparent; -fx-border-width: .8px; -fx-border-color: #EEEEEE;");
        } else if (event.getTarget() == PAGENATION_RIGHT) {
            PAGENATION_RIGHT_DARK_IMAGE.setVisible(false);
            PAGENATION_RIGHT_IMAGE.setVisible(true);
            PAGENATION_RIGHT.setStyle("-fx-background-color: transparent; -fx-border-width: .8px; -fx-border-color: #EEEEEE;");
        }
        
    }
    // End Mouse Exit Element function   

    @FXML
    private void MouseClickTopNav(MouseEvent event) {
        if (event.getTarget() == TOP_EXIT || event.getTarget() == TOP_EXIT_IMAGE) {
            saveData();
            System.exit(0);
        } else if (event.getTarget() == TOP_MINIMIZE || event.getTarget() == TOP_MINIMIZE_IMAGE) {
            
        } else if (event.getTarget() == TOP_FULLSCREEN || event.getTarget() == TOP_FULLSCREEN_IMAGE) {
            
        }
    }

    // Start click function - video container - this function to hile all showed element   
    @FXML
    private void handleMouseClick(MouseEvent event) {
        hideAllElement();
    }
    // End click function

    //                     //
    //  END HOVER SECTION  //
    //                     //
    
    @FXML
    private void exit(MouseEvent event){
        isDashboardOpened = false;
        hideTransition(DASHBOARD, 400);
        dashboardBlur.setRadius(0);
        DASHBOARD_CONTENT_CONTAINER.setEffect(dashboardBlur);
    }
    
    private void showSpicifecColor( AnchorPane anchorpane ) {  
        COLOR_1.setOpacity(0);
        COLOR_2.setOpacity(0);
        COLOR_3.setOpacity(0);
        COLOR_4.setOpacity(0);
        COLOR_5.setOpacity(0);
        COLOR_6.setOpacity(0);
        COLOR_7.setOpacity(0);
        COLOR_8.setOpacity(0);
        COLOR_9.setOpacity(0);
        COLOR_10.setOpacity(0);
        COLOR_11.setOpacity(0);
        COLOR_12.setOpacity(0);
        COLOR_13.setOpacity(0);
        COLOR_14.setOpacity(0);
        COLOR_15.setOpacity(0);
        COLOR_16.setOpacity(0);
        
        anchorpane.setOpacity(1);
    }
    public void colorTitle( String maniColor ) {
        THEME_TITLE.setStyle("-fx-fill: " + maniColor);
        COLOR_TITLE.setStyle("-fx-fill: " + maniColor);
        SEARCH_TITLE.setStyle("-fx-fill: " + maniColor);
        CONVERT_TITLE.setStyle("-fx-fill: " + maniColor);
        RECORD_TITLE.setStyle("-fx-fill: " + maniColor);
        SUBTITLE_TITLE.setStyle("-fx-fill: " + maniColor);
    }
    public void colorButton( String maniColor ) {
        CHOOSE_FILE_BTN.setStyle("-fx-text-fill: " + maniColor + "; -fx-border-color: " + maniColor + ";");
        CONVERT_BTN.setStyle("-fx-text-fill: " + maniColor + "; -fx-border-color: " + maniColor + ";");
        RECORD_BTN.setStyle("-fx-text-fill: " + maniColor + "; -fx-border-color: " + maniColor + ";");
    }
    public void setMainColor( String maniColor ) {
        
        mainColor = maniColor;
        
        colorTitle(maniColor);
        colorButton(maniColor);
        
    }
    
    @FXML
    public void applyColor() {
        
        COLOR_1.setOnMousePressed(event -> { showSpicifecColor( COLOR_1 ); setMainColor( "#FFFFFF" ); });
        COLOR_2.setOnMousePressed(event -> { showSpicifecColor( COLOR_2 ); setMainColor( "#AAAAAA" ); });
        COLOR_3.setOnMousePressed(event -> { showSpicifecColor( COLOR_3 ); setMainColor( "#6D6D6D" ); });
        COLOR_4.setOnMousePressed(event -> { showSpicifecColor( COLOR_4 ); setMainColor( "#333333" ); });
        COLOR_5.setOnMousePressed(event -> { showSpicifecColor( COLOR_5 ); setMainColor( "#5DC3B0" ); });
        COLOR_6.setOnMousePressed(event -> { showSpicifecColor( COLOR_6 ); setMainColor( "#3498db" ); });
        COLOR_7.setOnMousePressed(event -> { showSpicifecColor( COLOR_7 ); setMainColor( "#336E7B" ); });
        COLOR_8.setOnMousePressed(event -> { showSpicifecColor( COLOR_8 ); setMainColor( "#013243" ); });
        COLOR_9.setOnMousePressed(event -> { showSpicifecColor( COLOR_9 ); setMainColor( "#87D37C" ); });
        COLOR_10.setOnMousePressed(event -> { showSpicifecColor( COLOR_10 ); setMainColor( "#1E824C" ); });
        COLOR_11.setOnMousePressed(event -> { showSpicifecColor( COLOR_11 ); setMainColor( "#F03434" ); });
        COLOR_12.setOnMousePressed(event -> { showSpicifecColor( COLOR_12 ); setMainColor( "#CF000F" ); });
        COLOR_13.setOnMousePressed(event -> { showSpicifecColor( COLOR_13 ); setMainColor( "#E9D460" ); });
        COLOR_14.setOnMousePressed(event -> { showSpicifecColor( COLOR_14 ); setMainColor( "#F7CA18" ); });
        COLOR_15.setOnMousePressed(event -> { showSpicifecColor( COLOR_15 ); setMainColor( "#F89406" ); });
        COLOR_16.setOnMousePressed(event -> { showSpicifecColor( COLOR_16 ); setMainColor( "#D35400" ); });
    }
    
    @FXML
    public void applyTheme( MouseEvent event ) {
    
        if (event.getTarget() == FIRST_THEME || event.getTarget() == FIRST_THEME_IMAGE) {
            
            DASHBOARD_CONTAINER.setStyle( "-fx-background-image: url( 'project_1/images/BACKGROUND_1.png' )" );
            
            FIRST_THEME.setStyle(" -fx-opacity: 1; ");
            SECOND_THEME.setStyle(" -fx-opacity: .0001; ");
            THIRD_THEME.setStyle(" -fx-opacity: .0001; ");
            
        } else if (event.getTarget() == SECOND_THEME || event.getTarget() == SECOND_THEME_IMAGE) {
            
            DASHBOARD_CONTAINER.setStyle( "-fx-background-image: url( 'project_1/images/BACKGROUND_2.png' )" );
            
            FIRST_THEME.setStyle(" -fx-opacity: .0001; ");
            SECOND_THEME.setStyle(" -fx-opacity: 1; ");
            THIRD_THEME.setStyle(" -fx-opacity: .0001; ");
            
        } else if (event.getTarget() == THIRD_THEME || event.getTarget() == THIRD_THEME_IMAGE) {
            
            DASHBOARD_CONTAINER.setStyle( "-fx-background-image: url( 'project_1/images/BACKGROUND_3.png' )" );
            
            FIRST_THEME.setStyle(" -fx-opacity: .0001; ");
            SECOND_THEME.setStyle(" -fx-opacity: .0001; ");
            THIRD_THEME.setStyle(" -fx-opacity: 1; ");
            
        }
    }
    
    @FXML
    public void applyConvert( ActionEvent event ) {
        
        if( event.getTarget() == CHOOSE_FILE_BTN ) {   
            filePathConvert = chooseFileExtension("mp3", "");
            if ( !filePathConvert.equals("") ) {
                filePathConvert = filePathConvert.replace("%20", " ");
                filePathConvert = filePathConvert.replace("%5", " ");
                FILE_PATH.setText(filePathConvert);
            }
        } else if( event.getTarget() == CONVERT_BTN && !filePathConvert.equals("") ) {
            showTransition(CONVERT_SONG, 500);
        }
        
    }
    
    @FXML
    public void applyRecord() {
        
        showTransition(RECORD, 500);
        
        RECORD_IMG.setOnMousePressed( event -> {
            
            if( recordState == false ) {
                
                recordStopState = false;
                recordState = true;
                Timer timer = new Timer();
                
                TimerTask task = new TimerTask(){
                    int i = 0;
                    public void run(){
                        if (i <= 1800 && recordStopState == false) {
                            counter.setText(audioSliderValueText(i++));
                            counterSlider.setValue( i );
                        } else {
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec

                // creates a new thread that waits for a specified
                // of time before stopping
                new Thread(){

                    @Override
                    public void run() {
                        recorder.start();
                    }
                }.start();
                
            }
        });
        
        STOP_IMG.setOnMousePressed( event -> {
            
            recordStopState = true;
            recordState = false; 
            recorder.finish();
            
            Service service = new Notifications(800);

            if (!service.isRunning()) {
                service.start();
            }

            service.setOnSucceeded(e -> {
                counter.setText("00 : 00");
                hideTransition(RECORD, 200);
                service.reset();

            });
            
        });
        
        PAUSE_IMG.setOnMousePressed( event -> { });
    }
    
    // start init elemnet holder - this func to make dashboard section ready to use
    private void initElementHolder() {
        
        containers.add(DASHBOARD_ANNCHORPANE_1);
        containers.add(DASHBOARD_ANNCHORPANE_2);
        containers.add(DASHBOARD_ANNCHORPANE_3);
        containers.add(DASHBOARD_ANNCHORPANE_4);
        containers.add(DASHBOARD_ANNCHORPANE_5);
        containers.add(DASHBOARD_ANNCHORPANE_6);
        containers.add(DASHBOARD_ANNCHORPANE_7);
        containers.add(DASHBOARD_ANNCHORPANE_8);
        containers.add(DASHBOARD_ANNCHORPANE_9);
        containers.add(DASHBOARD_ANNCHORPANE_10);
        
        addfile.add(ADD_FILE_1);
        addfile.add(ADD_FILE_2);
        addfile.add(ADD_FILE_3);
        addfile.add(ADD_FILE_4);
        addfile.add(ADD_FILE_5);
        addfile.add(ADD_FILE_6);
        addfile.add(ADD_FILE_7);
        addfile.add(ADD_FILE_8);
        addfile.add(ADD_FILE_9);
        addfile.add(ADD_FILE_10);
        
        containersListener.add(DASHBOARD_ANNCHORPANE_LISTENER_1);
        containersListener.add(DASHBOARD_ANNCHORPANE_LISTENER_2);
        containersListener.add(DASHBOARD_ANNCHORPANE_LISTENER_3);
        containersListener.add(DASHBOARD_ANNCHORPANE_LISTENER_4);
        containersListener.add(DASHBOARD_ANNCHORPANE_LISTENER_5);
        containersListener.add(DASHBOARD_ANNCHORPANE_LISTENER_6);
        containersListener.add(DASHBOARD_ANNCHORPANE_LISTENER_7);
        containersListener.add(DASHBOARD_ANNCHORPANE_LISTENER_8);
        containersListener.add(DASHBOARD_ANNCHORPANE_LISTENER_9);
        containersListener.add(DASHBOARD_ANNCHORPANE_LISTENER_10);
        
        imageContainers.add(IMAGE_CONTAINER_1);
        imageContainers.add(IMAGE_CONTAINER_2);
        imageContainers.add(IMAGE_CONTAINER_3);
        imageContainers.add(IMAGE_CONTAINER_4);
        imageContainers.add(IMAGE_CONTAINER_5);
        imageContainers.add(IMAGE_CONTAINER_6);
        imageContainers.add(IMAGE_CONTAINER_7);
        imageContainers.add(IMAGE_CONTAINER_8);
        imageContainers.add(IMAGE_CONTAINER_9);
        imageContainers.add(IMAGE_CONTAINER_10);
        
        titles.add(TITLE_1);
        titles.add(TITLE_2);
        titles.add(TITLE_3);
        titles.add(TITLE_4);
        titles.add(TITLE_5);
        titles.add(TITLE_6);
        titles.add(TITLE_7);
        titles.add(TITLE_8);
        titles.add(TITLE_9);
        titles.add(TITLE_10);
        
        descriptions.add(DESCRIPTION_1);
        descriptions.add(DESCRIPTION_2);
        descriptions.add(DESCRIPTION_3);
        descriptions.add(DESCRIPTION_4);
        descriptions.add(DESCRIPTION_5);
        descriptions.add(DESCRIPTION_6);
        descriptions.add(DESCRIPTION_7);
        descriptions.add(DESCRIPTION_8);
        descriptions.add(DESCRIPTION_9);
        descriptions.add(DESCRIPTION_10);
        
    }
    // end init elemnet holder

    // Start mouse click function - this func to show and hide bottom nav and top nav
    @FXML
    private void mousePosition(MouseEvent event) {
        
        if (event.getY() >= 560 && bottomNavStatus == false) {
            
            showTransition(BOTTOM_SECTION_PANE, 500);
            bottomNavStatus = true;
            hideBottomNav = false;
            
        } else if (event.getY() < 560 && !menuStatus && !subMenuStatus && !hideBottomNav && !subMenuSubtitleStatus) {
            
            Service service = new Notifications();
            if (!service.isRunning()) {
                service.start();
            }
            
            service.setOnSucceeded(e -> {
                hideTransition(BOTTOM_SECTION_PANE, 1000);
                bottomNavStatus = false;
                hideBottomNav = true;
                service.reset();
            });
            
        }
        
        if (event.getY() <= 30 && topNavStatus == false) {
            
            showTransition(TOP_SECTION_PANE, 500);
            topNavStatus = true;
            hideTopNav = false;
            
        } else if (event.getY() > 30 && !hideTopNav) {
            
            Service TNservice = new Notifications();
            if (!TNservice.isRunning()) {
                TNservice.start();
            }
            
            TNservice.setOnSucceeded(e -> {
                hideTransition(TOP_SECTION_PANE, 1000);
                topNavStatus = false;
                hideTopNav = true;
                TNservice.reset();
            });
        }
    }
    // End mouse click function

    // Start service function - this func to show after specific time
    private void service(AnchorPane anchorpane, int duration) {
        
        FadeTransition FADEINEFFECT = new FadeTransition();
        
        Service service = new Notifications(duration);
        if (!service.isRunning()) {
            service.start();
        }
        
        service.setOnSucceeded(e -> {
            showTransition(anchorpane, 400, FADEINEFFECT);
            service.reset();
        });
    }
    // End service function

    // Start parse Title function - this func return the title of video
    private String parseTitle(String filePath) {
        
        int firstIndex = filePath.lastIndexOf("/");
        int lastIndex = filePath.lastIndexOf(".");
        
        filePath = filePath.substring(firstIndex + 1, lastIndex);
        filePath = filePath.replace("%20", " ");
        filePath = filePath.replace("%5", " ");
        
        return filePath;
    }
    // End parse Title function

    // Start parse Title function - this func return the title of video
    private String typeOfMedia(String filePath) {
        
        int firstIndex = filePath.indexOf(".");
        
        filePath = filePath.substring(firstIndex + 1);
        
        return filePath;
    }
    // End parse Title function

    // Start set Max Char Of File Title function - this func set specific number of char
    private String setMaxCharOfFileTitle(String fileTitle) {
        
        if (fileTitle.length() > 23) {
            
            fileTitle = fileTitle.substring(0, 20);
            fileTitle += "...";
            
        }
        
        return fileTitle;
        
    }
    // End set Max Char Of File Title function

    // Start dashboard Menu Delete Effect Mouse Event function - this func to hide all effect
    @FXML
    private void dashboardMenuDeleteEffectMouseEvent(MouseEvent event) {
        deleteEffect();
    }
    // End dashboard Menu Delete Effect Mouse Event function

    // Start click function for bottom menu - this function'll be complated soon
    @FXML
    private void MouseClickbottomMenu(MouseEvent event) throws InterruptedException, IOException {
        
        hideSubMenus();
        
        if (event.getTarget() == MENU_MAIN_MENU || event.getTarget() == MENU_MAIN_MENU_IMAGE) {
            showTransition(MENU, 400);
            menuStatus = true;
        } else if (event.getTarget() == OPTION_MEDIA || event.getTarget() == OPTION_ARRAW_MEDIA || event.getTarget() == OPTION_TEXT_MEDIA) {
            showTransition(SUB_MENU, 400);
            subMenuStatus = true;
        } else if (event.getTarget() == MENU_SOUND || event.getTarget() == MENU_SOUND_IMAGE) {
            showTransition(SOUND_VALUME, 400);
            soundValumeStatus = true;
        } else if (event.getTarget() == MENU_REPLAY || event.getTarget() == MENU_REPLAY_IMAGE) {
            replayStatus = replayStatus == true ? false : true;
        } else if (event.getTarget() == MENU_SHUFFLE || event.getTarget() == MENU_SHUFFLE_IMAGE) {
            shuffleStatus = shuffleStatus == true ? false : true;
        } else if (event.getTarget() == MENU_LIKE || event.getTarget() == MENU_UNLIKE_IMAGE || event.getTarget() == MENU_LIKE_IMAGE) {
            
            if (likeStatus == false) {
                
                hideTransition(MENU_UNLIKE_IMAGE, 400);
                showTransition(MENU_LIKE_IMAGE, 800);
                scaleTransition(MENU_LIKE_IMAGE, 1000, (float) 1.8);
                scaleTransitionMinimize(MENU_LIKE_IMAGE, 1000, 1);
                
                likeStatus = true;
                
                if (State == "PLAYLIST") {
                    
                    playlists.getPlaylists().get(CURRENT_PLAYLIST).getVideo(NUMBER_OF_INDEX).setLike(true);
                    Video video = playlists.getPlaylists().get(CURRENT_PLAYLIST).getVideo(NUMBER_OF_INDEX);
                    likedMedia.addVideo(video);
                } else if (State == "LIKEDTRACK") {
                    likedMedia.getVideo(NUMBER_OF_INDEX).setLike(true);
                } else if (State == "RECENTLYPLAYED") {
                    recentlyPlayed.getVideo(NUMBER_OF_INDEX).setLike(true);
                } else if ( State == "SEARCH" ) {
                    
                }
                
            } else {
                
                hideTransition(MENU_LIKE_IMAGE, 400);
                showTransition(MENU_UNLIKE_IMAGE, 400);
                likeStatus = false;
                
                if (State == "PLAYLIST") {
                    
                    playlists.getPlaylists().get(CURRENT_PLAYLIST).getVideo(NUMBER_OF_INDEX).setLike(false);
                    Video video = playlists.getPlaylists().get(CURRENT_PLAYLIST).getVideo(NUMBER_OF_INDEX);
                    
                    deleteCorrectVideo(video, likedMedia);
                    deleteCorrectVideo(video, recentlyPlayed);
                    
                } else if (State == "LIKEDTRACK") {
                    likedMedia.getVideos().remove(NUMBER_OF_INDEX);
                } else if (State == "RECENTLYPLAYED") {
                    recentlyPlayed.getVideo(NUMBER_OF_INDEX).setLike(false);
                } else if ( State == "SEARCH" ) {
                    
                }
                
            }
        } else if (event.getTarget() == OPTION_VIEW) {
            
            hideAllElement();
            
            hideTransition(BOTTOM_SECTION_PANE, 1000);
            bottomNavStatus = false;
            hideBottomNav = true;
            
            MENU_PAUSE_IMAGE.setVisible(false);
            MENU_PLAY_IMAGE.setVisible(true);
            PauseStatus = false;
            
            subtitleStatus = false;
            subtitleFound = false;
            SUBTITLE_CONTENT.setText("");
            
            mediaplayer.stop();
            
            VIDEO_PLAYER_CONTAINER.setVisible(false);
            DASHBOARD_CONTAINER.setVisible(true);
            
        } else if (event.getTarget() == OPTION_SUBTITLE) {
            showTransition(SUB_MENU_SUBTITLE, 400);
            subMenuSubtitleStatus = true;
        } else {
            hideAllElement();
        }
    }
    // End click function for bottom    

    // Start click function for bottom menu - this function'll be complated soon
    @FXML
    private void subtitleOperation(MouseEvent event) throws InterruptedException, IOException {
        
        if (event.getTarget() == SUBTITLE_ADD_FILE || event.getTarget() == SUBTITLE_TEXT_ADD_FILE) {
            
            subtitleStatus = true;
            getsubtitleFile();
            
        } else if ((event.getTarget() == SUBTITLE_DISABLE || event.getTarget() == SUBTITLE_TEXT_DISABLE) && subtitleFound == true) {
            
            subtitleStatus = subtitleStatus == true ? false : true;
            
            if (subtitleStatus == true) {
                SUBTITLE_TEXT_DISABLE.setText("Disable");
            } else {
                SUBTITLE_TEXT_DISABLE.setText("Enable");
                SUBTITLE_CONTENT.setText("");
            }
            
        }        
        
    }

    // Start play function - this func to play the media
    private void play() {
        mediaplayer.play();
        mediaplayer.setRate(1);
    }
    // End play function function

    // Start puase function - this func to pause the media
    private void pause() {
        mediaplayer.pause();
    }
    // End puase function function

    // Start stop function - this func to stop the media
    private void stop() {
        mediaplayer.stop();
    }
    // End puase function function

    // start hide all element function - this func for hide all option and menu 
    private void hideAllElement() {
        
        menuStatus = false;
        subMenuStatus = false;
        soundValumeStatus = false;
        subMenuSubtitleStatus = false;
        
        hideTransition(MENU, 400);
        hideTransition(SUB_MENU, 400);
        hideTransition(SOUND_VALUME, 400);
        hideTransition(SUB_MENU_SUBTITLE, 400);
        
        MENU_MAIN_MENU.setStyle(" -fx-background-color: transparent; ");
        OPTION_MEDIA.setStyle(" -fx-background-color: transparent; ");
        OPTION_SUBTITLE.setStyle(" -fx-background-color: transparent; ");
        MENU_SOUND.setStyle(" -fx-background-color: transparent; ");
    }
    // end hide all element function

    // start hide all element function - this func for hide all option and menu 
    private void hideSubMenus() {
        
        subMenuStatus = false;
        subMenuSubtitleStatus = false;
        
        hideTransition(SUB_MENU, 400);
        hideTransition(SUB_MENU_SUBTITLE, 400);
        
        OPTION_MEDIA.setStyle(" -fx-background-color: transparent; ");
        OPTION_SUBTITLE.setStyle(" -fx-background-color: transparent; ");
        
    }
    // end hide all element function
    
    // Start add Element To List function - this func to add element in specicfic index in list
    private void addElementToList(Videos videos, Video video, int index) {
        
        videos.addVideo(index, video);
        
    }
    // End add Element To List function

    // start get file index function - this func to return file depend of NUMBER_OF_INDEX number
    private String getFileInfo(int NUMBER_OF_INDEX) {
        
        String file = "";
        
        if (State == "LIKEDTRACK") {
            
            if (menuState.equals("VIDEO")) {
                
                file = likedMedia.getVideos().get(NUMBER_OF_INDEX).getPath();
                addElementToList(recentlyPlayed, likedMedia.getVideos().get(NUMBER_OF_INDEX), 0);
                MENU_UNLIKE_IMAGE.setVisible(false);
                MENU_LIKE_IMAGE.setVisible(true);
                
            } else if (menuState.equals("MUSIC")) {
                
                file = musicLikedMedia.getVideos().get(NUMBER_OF_INDEX).getPath();
                addElementToList(musicRecentlyPlayed, musicLikedMedia.getVideos().get(NUMBER_OF_INDEX), 0);
                MENU_UNLIKE_IMAGE.setVisible(false);
                MENU_LIKE_IMAGE.setVisible(true);
                
            }
            likeStatus = true;
            
        } else if (State == "PLAYLIST") {
            
            if (menuState.equals("VIDEO")) {
                
                file = playlists.getPlaylists().get(CURRENT_PLAYLIST).getVideos().get(((pageNumberPlaylist - 1) * 10) + NUMBER_OF_INDEX).getPath();
                addElementToList(recentlyPlayed, playlists.getPlaylists().get(CURRENT_PLAYLIST).getVideos().get(((pageNumberPlaylist - 1) * 10) + NUMBER_OF_INDEX), 0);
                
                if (playlists.getPlaylists().get(CURRENT_PLAYLIST).getVideos().get(((pageNumberPlaylist - 1) * 10) + NUMBER_OF_INDEX).getLike() == true) {
                    
                    MENU_UNLIKE_IMAGE.setVisible(false);
                    MENU_LIKE_IMAGE.setVisible(true);
                    likeStatus = true;
                    
                } else {
                    
                    MENU_LIKE_IMAGE.setVisible(false);
                    MENU_UNLIKE_IMAGE.setVisible(true);
                    likeStatus = false;
                    
                }
                
            } else if (menuState.equals("MUSIC")) {
                
                file = musicPlaylists.getPlaylists().get(CURRENT_PLAYLIST).getVideos().get(((pageNumberPlaylist - 1) * 10) + NUMBER_OF_INDEX).getPath();
                addElementToList(musicRecentlyPlayed, musicPlaylists.getPlaylists().get(CURRENT_PLAYLIST).getVideos().get(((pageNumberPlaylist - 1) * 10) + NUMBER_OF_INDEX), 0);
                
                if (musicPlaylists.getPlaylists().get(CURRENT_PLAYLIST).getVideos().get(((pageNumberPlaylist - 1) * 10) + NUMBER_OF_INDEX).getLike() == true) {
                    
                    AUDIO_UNLIKE_IMAGE.setVisible(false);
                    AUDIO_LIKE_IMAGE.setVisible(true);
                    likeStatus = true;
                    
                } else {
                    
                    AUDIO_LIKE_IMAGE.setVisible(false);
                    AUDIO_UNLIKE_IMAGE.setVisible(true);
                    likeStatus = true;
                    
                }
                
            }
            
        } else if (State == "RECENTLYPLAYED") {
            
            if (menuState.equals("VIDEO")) {
                
                file = recentlyPlayed.getVideos().get(NUMBER_OF_INDEX).getPath();
                
                if (recentlyPlayed.getVideos().get(NUMBER_OF_INDEX).getLike() == true) {
                    
                    MENU_UNLIKE_IMAGE.setVisible(false);
                    MENU_LIKE_IMAGE.setVisible(true);
                    likeStatus = true;
                    
                } else {
                    
                    MENU_LIKE_IMAGE.setVisible(false);
                    MENU_UNLIKE_IMAGE.setVisible(true);
                    likeStatus = false;
                    
                }
                
            } else if (menuState.equals("MUSIC")) {
                
                file = musicRecentlyPlayed.getVideos().get(NUMBER_OF_INDEX).getPath();
                
                if (musicRecentlyPlayed.getVideos().get(NUMBER_OF_INDEX).getLike() == true) {
                    
                    MENU_UNLIKE_IMAGE.setVisible(false);
                    MENU_LIKE_IMAGE.setVisible(true);
                    likeStatus = true;
                    
                } else {
                    
                    MENU_LIKE_IMAGE.setVisible(false);
                    MENU_UNLIKE_IMAGE.setVisible(true);
                    likeStatus = false;
                    
                }
                
            }
            
        } else if (State == "SEARCH") {
            
            if (menuState.equals("VIDEO")) {
                
                file = searchVideo.getVideos().get(NUMBER_OF_INDEX).getPath();
                addElementToList(recentlyPlayed, searchVideo.getVideos().get(NUMBER_OF_INDEX), 0);
                MENU_UNLIKE_IMAGE.setVisible(true);
                MENU_LIKE_IMAGE.setVisible(false);
                
            } else if (menuState.equals("MUSIC")) {
                
                file = searchVideo.getVideos().get(NUMBER_OF_INDEX).getPath();
                addElementToList(musicRecentlyPlayed, searchVideo.getVideos().get(NUMBER_OF_INDEX), 0);
                MENU_UNLIKE_IMAGE.setVisible(true);
                MENU_LIKE_IMAGE.setVisible(false);
                
            }
            
        }
        
        return file;
    }
    // end get file index function

    // start get file index function - this func to return file depend of NUMBER_OF_INDEX number
    private String getMediaType(int NUMBER_OF_INDEX) {
        
        String type = "";
        
        if (State == "LIKEDTRACK") {
            
            if (menuState.equals("VIDEO")) {
                
                type = likedMedia.getVideos().get(NUMBER_OF_INDEX).getType();
                
            } else if (menuState.equals("MUSIC")) {
                
                type = musicLikedMedia.getVideos().get(NUMBER_OF_INDEX).getType();
                
            }
            likeStatus = true;
            
        } else if (State == "PLAYLIST") {
            
            if (menuState.equals("VIDEO")) {
                
                type = playlists.getPlaylists().get(CURRENT_PLAYLIST).getVideos().get(((pageNumberPlaylist - 1) * 10) + NUMBER_OF_INDEX).getType();
                
            } else if (menuState.equals("MUSIC")) {
                
                type = musicPlaylists.getPlaylists().get(CURRENT_PLAYLIST).getVideos().get(((pageNumberPlaylist - 1) * 10) + NUMBER_OF_INDEX).getType();
                
            }
            
        } else if (State == "RECENTLYPLAYED") {
            
            if (menuState.equals("VIDEO")) {
                
                type = recentlyPlayed.getVideos().get(NUMBER_OF_INDEX).getType();
                
            } else if (menuState.equals("MUSIC")) {
                
                type = musicRecentlyPlayed.getVideos().get(NUMBER_OF_INDEX).getType();
                
            }
            
        } else if (State == "SEARCH") {
                
            type = searchVideo.getVideos().get(NUMBER_OF_INDEX).getType();
        }
        
        return type;
    }
    // end get file index function

    // Start delete Correct Video function - this func to delete a specific video in the liked media list
    private void deleteCorrectVideo(Video video, Videos Videos) {
        
        for (int i = 0; i < Videos.getVideos().size(); i++) {
            
            if (Videos.getVideos().get(i).getPath() == video.getPath()) {
                Videos.getVideos().remove(i);
                break;
            }
            
        }
    }
    // End delete Correct Video function

    // Start Mouse Click PlayList Method function - this func to handle all method belong to playlists
    @FXML
    private void MouseClickPlayListMethod(ActionEvent event) throws InterruptedException {
        
        if (event.getTarget() == CREATE_PLAYLIST) {
            
            Playlist playlist;
            String Title = TITLE_PLAYLIST.getText();
            String Description = DESCRIPTION_PLAYLIST.getText();
            
            if (Description.equals("")) {
                Description = "Amazing PlayList";
            }
            if (!Title.equals("")) {
                
                playlist = new Playlist(Title, Description, 0);
                
                if (menuState.equals("VIDEO")) {
                    playlists.addPlaylist(playlist);
                    printAllPlaylistsInContent(playlists);
                } else if (menuState.equals("MUSIC")) {
                    musicPlaylists.addPlaylist(playlist);
                    printAllPlaylistsInContent(musicPlaylists);
                } else if (menuState.equals("HOME")) {
                    
                }
                
            }
            
            hideTransition(ADD_PLAYLIST, 400);
            
            TITLE_PLAYLIST.setText("");
            DESCRIPTION_PLAYLIST.setText("");
            
            dashboardBlur.setRadius(0);
            DASHBOARD_CONTENT_CONTAINER.setEffect(dashboardBlur);
            
        } else if (event.getTarget() == CANCEL_PLAYLIST) {
            
            hideTransition(ADD_PLAYLIST, 400);
            
            dashboardBlur.setRadius(0);
            DASHBOARD_CONTENT_CONTAINER.setEffect(dashboardBlur);
            
        }
        
    }
    // End Mouse Click PlayList Method function
    
    // Start Mouse Click PlayList Method function - this func to handle all method belong to playlists
    @FXML
    private void MouseClickConvertMethod(ActionEvent event) throws InterruptedException {
        
        if (event.getTarget() == SAVE_NEW_NAME) {
            
            String Title = SONG_NEW_NAME.getText();
            
            if (!Title.equals("")) {
                
                NOTIFICATION_LOADING.setVisible(true);        // this structure respose of the movment 
                rotateTransition(GEAR, 3300);                 // of the gear in the notification section

                Service service = new Notifications(3500);

                if (!service.isRunning()) {
                    service.start();
                }

                service.setOnSucceeded(e -> {
                    
                    showTransition(NOTIFICATION_MESSAGE, 200);
                    
                    SONG_NEW_NAME.setText("");
                    service.reset();
                    
                });
                
                Service service_Message= new Notifications(4500);

                if (!service_Message.isRunning()) {
                    service_Message.start();
                }

                service_Message.setOnSucceeded(e -> {
                    
                    hideTransition(CONVERT_SONG, 400);
                    NOTIFICATION_MESSAGE.setVisible(false);
                    NOTIFICATION_LOADING.setVisible(false);
                    
                    if( isDashboardOpened == false ) {
                        dashboardBlur.setRadius(0);
                        DASHBOARD_CONTENT_CONTAINER.setEffect(dashboardBlur);
                    }
                    
                    filePathConvert = "";
                    FILE_PATH.setText("");
                    
                    service.reset();
                    
                });
                
                new Thread(){

                    @Override
                    public void run() {

                        Mp3ToWav mp3ToWav = new Mp3ToWav();
                        filePathConvert = filePathConvert.replace("file:/", "");
                        
                        if( filePathConvert.toLowerCase().endsWith("mp3") ) {

                            mp3ToWav.setFilePath(filePathConvert);
                            mp3ToWav.applyConvert(Title);

                        }

                    }
                }.start();
                
            }
            
        } else if (event.getTarget() == CANCEL_NEW_NAME) {
            
            hideTransition(CONVERT_SONG, 400);
            
            if( isDashboardOpened == false ) {
                dashboardBlur.setRadius(0);
                DASHBOARD_CONTENT_CONTAINER.setEffect(dashboardBlur);
            }
            
        }
        
    }
    // End Mouse Click PlayList Method function
    
    // Start dash board Menu Mouse Event function - this func to handle button in the dashboard section
    @FXML
    public void dashboardMenuMouseEvent(MouseEvent event) throws InterruptedException {
        
        for (int i = 0; i < 10; i++) {
            setDefaultValue(containers.get(i), addfile.get(i), titles.get(i), descriptions.get(i));
        }
        
        if (mediaplayer != null && isVideo == false) {
            
            if (mediaType.equals("wav") ) {
                reader.stop();
            } else if (mediaType.equals("mp3") ) {
                mp3player.Stop();
            }
            mediaplayer.stop();
            
            hideTransition(AUDIO_PLAYER_CONTAINER, 500);
            showTransition(ELEMENT_CONTENT, 500);
        }
        
        PAGENATION_RIGHT.setVisible(false);
        PAGENATION_LEFT.setVisible(false);
        PAGENATION_CENTER.setVisible(false);
        
        blur.setRadius(2.5);
        
        if (event.getTarget() == DASHBOARD_BUTTON_HOME || event.getTarget() == DASHBOARD_LABEL_HOME) {
            
            setActiveSctionButton(DASHBOARD_PANE_HOME);
            menuState = "HOME";
            State = "LIKEDTRACK";
            isVideo = false;
            
        } else if (event.getTarget() == DASHBOARD_BUTTON_VIDEOS || event.getTarget() == DASHBOARD_LABEL_VIDEOS) {
            
            setActiveSctionButton(DASHBOARD_PANE_VIDEOS);
            
            menuState = "VIDEO";
            State = "LIKEDTRACK";
            
            setActiveSctionButton(DASHBOARD_LABEL_LIKED_TRUCK);
            
            printAllLikedElementInContent(likedMedia);
            
        } else if (event.getTarget() == DASHBOARD_BUTTON_MUSIC || event.getTarget() == DASHBOARD_LABEL_MUSIC) {
            
            setActiveSctionButton(DASHBOARD_PANE_MUSIC);
            
            menuState = "MUSIC";
            State = "LIKEDTRACK";
            
            setActiveSctionButton(DASHBOARD_LABEL_LIKED_TRUCK);
            
            printAllLikedElementInContent(musicLikedMedia);
            
        }
        
        if (event.getTarget() == DASHBOARD_PANE_LIKED_TRUCK || event.getTarget() == DASHBOARD_LABEL_LIKED_TRUCK) {
            
            pageNumberLikedMedia = 1;
            pageNumberPlaylist = 1;
            
            State = "LIKEDTRACK";
            
            DASHBOARD_LABEL_LIKED_TRUCK.setEffect(blur);
            setActiveSctionButton(DASHBOARD_LABEL_LIKED_TRUCK);
            
            if (menuState.equals("VIDEO")) {
                printAllLikedElementInContent(likedMedia);
            } else if (menuState.equals("MUSIC")) {
                printAllLikedElementInContent(musicLikedMedia);
            } else if (menuState.equals("HOME")) {
                
            }
            
        } else if (event.getTarget() == DASHBOARD_PANE_RECENTLY_PLAYED || event.getTarget() == DASHBOARD_LABEL_RECENTLY_PLAYED) {
            
            pageNumberLikedMedia = 1;
            pageNumberPlaylist = 1;
            
            State = "RECENTLYPLAYED";
            
            DASHBOARD_LABEL_RECENTLY_PLAYED.setEffect(blur);
            setActiveSctionButton(DASHBOARD_LABEL_RECENTLY_PLAYED);
            
            if (menuState.equals("VIDEO")) {
                printRecentlyPlayedElementInContent(likedMedia);
            } else if (menuState.equals("MUSIC")) {
                printRecentlyPlayedElementInContent(musicLikedMedia);
            } else if (menuState.equals("HOME")) {
                
            }
            
        } else if (event.getTarget() == DASHBOARD_PANE_FOLDERS || event.getTarget() == DASHBOARD_LABEL_FOLDERS) {
            
            pageNumberLikedMedia = 1;
            pageNumberPlaylist = 1;
            
            State = "FOLDER";
            
            DASHBOARD_LABEL_FOLDERS.setEffect(blur);
            setActiveSctionButton(DASHBOARD_LABEL_FOLDERS);
            
        } else if (event.getTarget() == DASHBOARD_PANE_PLAYLISTS || event.getTarget() == DASHBOARD_LABEL_PLAYLISTS) {
            
            pageNumberLikedMedia = 1;
            pageNumberPlaylist = 1;
            
            State = "PLAYLIST";
            
            DASHBOARD_LABEL_PLAYLISTS.setEffect(blur);
            setActiveSctionButton(DASHBOARD_LABEL_PLAYLISTS);
            
            try {
                if (menuState.equals("VIDEO")) {
                    printAllPlaylistsInContent(playlists);
                } else if (menuState.equals("MUSIC")) {
                    printAllPlaylistsInContent(musicPlaylists);
                } else if (menuState.equals("HOME")) {
                    
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (event.getTarget() == DASHBOARD_PANE_EXIT || event.getTarget() == DASHBOARD_LABEL_EXIT || event.getTarget() == DASHBOARD_EXIT_IMAGE) {
            
            saveData();
            System.exit(0);
            
        }
        
        if (event.getTarget() == PAGENATION_RIGHT || event.getTarget() == PAGENATION_RIGHT_IMAGE || event.getTarget() == PAGENATION_RIGHT_DARK_IMAGE) {
            
            
            
            if (State == "PLAYLIST") {
                
                pageNumberPlaylist++;
                if (menuState.equals("VIDEO")) {
                    printAllElementInContent(playlists.getPlaylists().get(CURRENT_PLAYLIST));
                } else if (menuState.equals("MUSIC")) {
                    printAllElementInContent(musicPlaylists.getPlaylists().get(CURRENT_PLAYLIST));
                } else if (menuState.equals("HOME")) {
                    
                }
                
            } else if (State == "LIKEDTRACK") {
                
                pageNumberLikedMedia++;
                if (menuState.equals("VIDEO")) {
                    printAllLikedElementInContent(likedMedia);
                } else if (menuState.equals("MUSIC")) {
                    printAllLikedElementInContent(musicLikedMedia);
                } else if (menuState.equals("HOME")) {
                    
                }   
                
            } else if (State == "SEARCH") {
                
                pageNumberLikedMedia++;
                printAllLikedElementInContent( searchVideo );
            
            }
            
        } else if (event.getTarget() == PAGENATION_LEFT || event.getTarget() == PAGENATION_LEFT_IMAGE || event.getTarget() == PAGENATION_LEFT_DARK_IMAGE) {
            
            if (State == "PLAYLIST") {
                
                pageNumberPlaylist--;
                if (menuState.equals("VIDEO")) {
                    printAllElementInContent(playlists.getPlaylists().get(CURRENT_PLAYLIST));
                } else if (menuState.equals("MUSIC")) {
                    printAllElementInContent(musicPlaylists.getPlaylists().get(CURRENT_PLAYLIST));
                } else if (menuState.equals("HOME")) {
                    
                }
                
            } else if (State == "LIKEDTRACK") {
                
                pageNumberLikedMedia--;
                if (menuState.equals("VIDEO")) {
                    printAllLikedElementInContent(likedMedia);
                } else if (menuState.equals("MUSIC")) {
                    printAllLikedElementInContent(musicLikedMedia);
                } else if (menuState.equals("HOME")) {
                    
                }   
                
            } else if (State == "SEARCH") {
                
                pageNumberLikedMedia--;
                printAllLikedElementInContent( searchVideo );
            
            }
            
            
        }
        
    }
    // End dash board Menu Mouse Event function

    @FXML
    private void settings(MouseEvent event) throws Exception, Exception {
        isDashboardOpened = true;
        dashboardBlur.setRadius(10);
        DASHBOARD_CONTENT_CONTAINER.setEffect(dashboardBlur);
        showTransition(DASHBOARD, 500);
    }
    
    @FXML
    private void search(MouseEvent event) throws Exception, Exception {
        
        if( !DASHBOARD_SEARCH_FIELD.equals("") ) {
            
            
            for (int i = 0; i < 10; i++) {
            setDefaultValue(containers.get(i), addfile.get(i), titles.get(i), descriptions.get(i));
            }
            if (mediaplayer != null && isVideo == false) {

                if (mediaType.equals("wav") ) {
                    reader.stop();
                } else if (mediaType.equals("mp3") ) {
                    mp3player.Stop();
                }
                mediaplayer.stop();

                hideTransition(AUDIO_PLAYER_CONTAINER, 500);
                showTransition(ELEMENT_CONTENT, 500);
            }
            PAGENATION_RIGHT.setVisible(false);
            PAGENATION_LEFT.setVisible(false);
            PAGENATION_CENTER.setVisible(false);
            blur.setRadius(2.5);
            
            String keyWords[] = DASHBOARD_SEARCH_FIELD.getText().toLowerCase().split(" ");
            String keyWord = DASHBOARD_SEARCH_FIELD.getText().toLowerCase();
            
            DASHBOARD_SEARCH_FIELD.setText("");
            
            Playlists searchTempPlayList = new Playlists();
            searchVideo = new Videos();
            boolean check = false;
            
            if (menuState.equals("VIDEO")) {
                searchTempPlayList = playlists;
            } else if (menuState.equals("MUSIC")) {
                searchTempPlayList = musicPlaylists;
            }
            
            for (int i = 0; i < searchTempPlayList.getPlaylists().size(); i++) {
                
                for (int j = 0; j < searchTempPlayList.getPlaylist(i).getVideos().size(); j++) {
                    
                    if( FIRST_RADIOBUTTON.isSelected() ) {
                    
                        String title[] = searchTempPlayList.getPlaylist(i).getVideos().get(j).getTitle().toLowerCase().split(" ");
                        check = false;

                        for (int k = 0; k < title.length; k++) {

                            for (int l = 0; l < keyWords.length; l++) { 

                                if( title[k].equals(keyWords[l]) ){

                                    searchVideo.addVideo( searchTempPlayList.getPlaylist(i).getVideos().get(j) );
                                    check = true;
                                    break;

                                }

                            }

                            if( check == true )
                                break;

                        }
                    } else {
                        if( (searchTempPlayList.getPlaylist(i).getVideos().get(j).getTitle().toLowerCase()).equals(keyWord) ){

                            searchVideo.addVideo( searchTempPlayList.getPlaylist(i).getVideos().get(j) );

                        }
                    }
                    
                }
                
            }
            
            State = "SEARCH";
            printAllLikedElementInContent( searchVideo );
            
        }
        
    }
    
    // Start video Operation function - this func to handle button which belong to video player like ( playm pause )
    @FXML
    private void AudioOperation(MouseEvent event) throws Exception, Exception {
        
        audioSeekSlider.setMax(mediaplayer.getStopTime().toSeconds());
        AUDIO_COMPLETE_TIME_TEXT.setText(audioSliderValueText((int) audioSeekSlider.getMax()));
        
        if (event.getTarget() == AUDIO_PLAY_IMAGE) {
            
            AUDIO_PLAY_IMAGE.setVisible(false);
            
            showTransition(AUDIO_PAUSE_IMAGE, 400);
            
            try {
                
                play();
                
                if (mediaType.equals("wav")) {
                    if (reader.isPlayState() == true) {
                        reader.play();
                    } else if (reader.isPlayState() == false) {
                        reader.resume();
                    }
                } else if (mediaType.equals("mp3")) {
                    if (mp3player.getPauseLocation() <= 0) {
                        mp3player.Play();
                    } else {
                        mp3player.Resume();
                    }
                    
                }
                
            } catch (Exception ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (event.getTarget() == AUDIO_PAUSE_IMAGE) {
            
            AUDIO_PAUSE_IMAGE.setVisible(false);
            
            showTransition(AUDIO_PLAY_IMAGE, 400);
            
            pause();
            
            if (mediaType.equals("wav")) {
                reader.pause();
            } else if (mediaType.equals("mp3")) {
                mp3player.Pause();
            }
            
        } else if (event.getTarget() == AUDIO_SEEK_RIGHT_IMAGE) {
            
            if (mediaplayer != null && isVideo == false) {
            
                if (mediaType.equals("wav") ) {
                    reader.stop();
                } else if (mediaType.equals("mp3") ) {
                    mp3player.Stop();
                }
                mediaplayer.stop();
            }
            
            filepath = getFileInfo(NUMBER_OF_INDEX+1);
            if( !filepath.equals("") ){
                NUMBER_OF_INDEX++;
                mediaType = getMediaType(NUMBER_OF_INDEX);
                selectVideo();
            } else {
                filepath = getFileInfo(NUMBER_OF_INDEX);
                mediaType = getMediaType(NUMBER_OF_INDEX);
            }
            
        } else if (event.getTarget() == AUDIO_SEEK_LEFT_IMAGE) {
            
            if (mediaplayer != null && isVideo == false) {
            
                if (mediaType.equals("wav") ) {
                    reader.stop();
                } else if (mediaType.equals("mp3") ) {
                    mp3player.Stop();
                }
                mediaplayer.stop();
            }
            
            filepath = getFileInfo(NUMBER_OF_INDEX-1);
            if( !filepath.equals("") ){
                NUMBER_OF_INDEX--;
                mediaType = getMediaType(NUMBER_OF_INDEX);
                selectVideo();
            } else {
                filepath = getFileInfo(NUMBER_OF_INDEX);
                mediaType = getMediaType(NUMBER_OF_INDEX);
            }
            
        } else if (event.getTarget() == AUDIO_CONVERT_IMAGE) {
            
            filePathConvert = filepath;
            
            dashboardBlur.setRadius(4);
            DASHBOARD_CONTENT_CONTAINER.setEffect(dashboardBlur);
                
            showTransition(CONVERT_SONG, 500);
            
        } else if (event.getTarget() == AUDIO_REPLAY_IMAGE) {
            replayStatus = replayStatus == true ? false : true;
        } else if (event.getTarget() == AUDIO_UNLIKE_IMAGE || event.getTarget() == AUDIO_LIKE_IMAGE) {
            
            if (likeStatus == false) {
                
                hideTransition(AUDIO_UNLIKE_IMAGE, 400);
                showTransition(AUDIO_LIKE_IMAGE, 400);
                
                likeStatus = true;
                
                if (State == "PLAYLIST") {
                    
                    musicPlaylists.getPlaylists().get(CURRENT_PLAYLIST).getVideo(NUMBER_OF_INDEX).setLike(true);
                    Video video = musicPlaylists.getPlaylists().get(CURRENT_PLAYLIST).getVideo(NUMBER_OF_INDEX);
                    musicLikedMedia.addVideo(video);
                } else if (State == "LIKEDTRACK") {
                    musicLikedMedia.getVideo(NUMBER_OF_INDEX).setLike(true);
                } else if (State == "RECENTLYPLAYED") {
                    musicRecentlyPlayed.getVideo(NUMBER_OF_INDEX).setLike(true);
                } else if ( State == "SEARCH" ) {
                    
                }
                
            } else {
                
                hideTransition(AUDIO_LIKE_IMAGE, 400);
                showTransition(AUDIO_UNLIKE_IMAGE, 400);
                likeStatus = false;
                
                if (State == "PLAYLIST") {
                    
                    musicPlaylists.getPlaylists().get(CURRENT_PLAYLIST).getVideo(NUMBER_OF_INDEX).setLike(false);
                    Video video = musicPlaylists.getPlaylists().get(CURRENT_PLAYLIST).getVideo(NUMBER_OF_INDEX);
                    
                    deleteCorrectVideo(video, musicLikedMedia);
                    deleteCorrectVideo(video, musicRecentlyPlayed);
                    
                } else if (State == "LIKEDTRACK") {
                    musicLikedMedia.getVideos().remove(NUMBER_OF_INDEX);
                } else if (State == "RECENTLYPLAYED") {
                    musicRecentlyPlayed.getVideo(NUMBER_OF_INDEX).setLike(false);
                } else if ( State == "SEARCH" ) {
                    
                }
                
            }
        }
        
    }

    // Start video Operation function - this func to handle button which belong to video player like ( playm pause )
    @FXML
    private void videoOperation(MouseEvent event) {
        
        seekslider.setMax(mediaplayer.getStopTime().toSeconds()); // here we get the stop time of the video
        // and set it for the slider value                                                         
        if ((event.getTarget() == MENU_PLAY_PAUSE && PauseStatus == false) || event.getTarget() == MENU_PLAY_IMAGE) {
            
            MENU_PLAY_IMAGE.setVisible(false);
            
            showTransition(MENU_PAUSE_IMAGE, 400);
            
            PauseStatus = true;
            
            play();
            
        } else if ((event.getTarget() == MENU_PLAY_PAUSE && PauseStatus == true) || event.getTarget() == MENU_PAUSE_IMAGE) {
            
            MENU_PAUSE_IMAGE.setVisible(false);
            
            showTransition(MENU_PLAY_IMAGE, 400);
            
            PauseStatus = false;
            
            pause();
            
        } else if (event.getTarget() == MENU_SEEK_RIGHT || event.getTarget() == MENU_SEEK_RIGHT_IMAGE) {
            
            mediaplayer.seek(Duration.seconds(seekslider.getValue() + 10));
            
        } else if (event.getTarget() == MENU_SEEK_LEFT || event.getTarget() == MENU_SEEK_LEFT_IMAGE) {
            
            mediaplayer.seek(Duration.seconds(seekslider.getValue() - 5));
            
        }
    }
    // End video Operation function

    // Start open file function - this func to choose file and set it on video container
    @FXML
    private void openFile_handleMouseEvent(MouseEvent event) throws Exception {
        
        if (openFileState == false) {
            
            filepath = chooseFileExtension("mp4", "avi");
            
        } else {
            filepath = getFileInfo(NUMBER_OF_INDEX);
            openFileState = false;
        }
        
        if (filepath != null) {
            
            isVideo = true;
            
            MEDIA_TITLE.setText(parseTitle(filepath));
            
            Media media = new Media(filepath);
            mediaplayer = new MediaPlayer(media);
            
            mediaView.setMediaPlayer(mediaplayer);
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            
            slider.setValue(mediaplayer.getVolume() * 100);
            
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaplayer.setVolume(slider.getValue() / 100);
                }
            });
            
            mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    seekslider.setValue(newValue.toSeconds());
                    
                    SEEK_VALUE_TEXT.setText(videoSliderValueText((int) seekslider.getValue()));
                    
                    if (subtitleStatus == true) {
                        setSubtitle(newValue);
                    }

                    // this section to check from the end of video 
                    // and replay it if I click replay button
                    int stopTime = (int) mediaplayer.getStopTime().toSeconds();
                    if ((stopTime == (int) seekslider.getValue()) && replayStatus == true) {
                        mediaplayer.seek(Duration.seconds(0));
                        replayStatus = false;
                    }
                    
                }
            });
            
            seekslider.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    mediaplayer.seek(Duration.seconds(seekslider.getValue()));
                }
                
            });
        }
    }
    // End open file function

    // Start open file function - this func to choose file and set it on video container
    @FXML
    private void openMusicFile_handleMouseEvent(MouseEvent event) throws Exception {
        
        PAGENATION_RIGHT.setVisible(false);
        PAGENATION_LEFT.setVisible(false);
        PAGENATION_CENTER.setVisible(false);
        
        AUDIO_UNLIKE_IMAGE.setVisible(true);
        AUDIO_LIKE_IMAGE.setVisible(false);
        
        AUDIO_PAUSE_IMAGE.setVisible(false);
        showTransition(AUDIO_PLAY_IMAGE, 400);
        
        isVideo = false;
        
        if (openFileState == false) {
            
            if (mediaType.equals("wav")) {
                reader.stop();
            } else if (mediaType.equals("mp3")) {
                mp3player.Stop();
            }
            mediaplayer.stop();
            
            filepath = chooseFileExtension("mp3", "wav");
            mediaType = typeOfMedia(filepath);
            
        } else {
            
            filepath = getFileInfo(NUMBER_OF_INDEX);
            mediaType = getMediaType(NUMBER_OF_INDEX);
            openFileState = false;
            
        }
        
        if (filepath != null) {
            
            selectVideo();
            
        }
    }
    // End open file function

    public void selectVideo() {
    
        AUDIO_SONG_TITLE.setTextAlignment(TextAlignment.CENTER);
        AUDIO_SONG_TITLE.setText(parseTitle(filepath));

        Media media = new Media(filepath);
        mediaplayer = new MediaPlayer(media);
        mediaplayer.setVolume(0);
        mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

                audioSeekSlider.setValue(newValue.toSeconds());
                AUDIO_SEEK_VALUE_TEXT.setText(audioSliderValueText((int) audioSeekSlider.getValue()));

//                  this section to check from the end of video 
//                  and replay it if I click replay button
                int stopTime = (int) mediaplayer.getStopTime().toSeconds();
                if ((stopTime == (int) audioSeekSlider.getValue())) {

                    AUDIO_PAUSE_IMAGE.setVisible(false);
                    showTransition(AUDIO_PLAY_IMAGE, 400);

                    if (replayStatus == true) {

                        AUDIO_PLAY_IMAGE.setVisible(false);
                        showTransition(AUDIO_PAUSE_IMAGE, 400);

                        mediaplayer.seek(Duration.seconds(0));
                        replayStatus = false;

                        if (mediaType.equals("wav")) {
                            try {
                                filepath = filepath.replace("file:/", "");
                                reader = new Reader(filepath);
                                reader.play();
                            } catch (Exception ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (mediaType.equals("mp3")) {
                            filepath = filepath.replace("%20", " ");
                            filepath = filepath.replace("file:/", "");
                            mp3player.setFilePath(filepath);
                            mp3player.Play();
                        }
                    }
                }

            }
        });

        if (mediaType.equals("wav")) {
            try {
                filepath = filepath.replace("file:/", "");
                reader = new Reader(filepath);
            } catch (Exception ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (mediaType.equals("mp3")) {

            filepath = filepath.replace("%20", " ");
            filepath = filepath.replace("file:/", "");
            mp3player.setFilePath(filepath);

        }
        
    }
    
    // Start choose file extension function - this func to open file with specific extension
    @FXML
    private String chooseFileExtension(String extension1, String extension2) {
        
        String filePath = "";
        
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter filter;
        
        if (extension2.equals("")) {
            filter = new FileChooser.ExtensionFilter("select a file (*." + extension1 + ")", "*." + extension1);
        } else {
            filter = new FileChooser.ExtensionFilter("select a file (*." + extension1 + "," + extension2 + ")", "*." + extension1, "*." + extension2);
        }
        
        filechooser.getExtensionFilters().add(filter);
        File file = filechooser.showOpenDialog(null);
        filePath = file.toURI().toString();
        
        return filePath;
    }
    // End choose file extension function

    // Start video Slider Value Text function - this func return the value of slider
    private String videoSliderValueText(int seekSliderValue) {
        
        int hour = 0, minute = 0, second = 0;

        // this varialble to calculate second
        second = seekSliderValue % 60;

        // this varialble to calculate hours
        hour = seekSliderValue / 3600;

        // this varialble to calculate minutes
        minute = (seekSliderValue < 3600 ? (seekSliderValue / 60) : ((seekSliderValue - (hour * 3600)) / 60));
        
        String hourText, minuteText, secondText;
        
        secondText = second < 10 ? ("0" + second) : ("" + second);
        minuteText = minute < 10 ? ("0" + minute) : ("" + minute);
        hourText = hour < 10 ? ("0" + hour) : ("" + hour);
        
        return ("" + hourText + " : " + minuteText + " : " + secondText);
    }
    // End video Slider Value Text function

    // Start video Slider Value Text function - this func return the value of slider
    private String audioSliderValueText(int seekSliderValue) {
        
        int minute = 0, second = 0;

        // this varialble to calculate second
        second = seekSliderValue % 60;

        // this varialble to calculate minutes
        minute = seekSliderValue / 60;
        
        String minuteText, secondText;
        
        secondText = second < 10 ? ("0" + second) : ("" + second);
        minuteText = minute < 10 ? ("0" + minute) : ("" + minute);
        
        return ("" + minuteText + " : " + secondText);
    }
    // End video Slider Value Text function

    // Start set The Number Of AnchorPane function - this func to select anchorpane which clicked
    @FXML
    private void setTheNumberOfAnchorPane(MouseEvent event) {
        
        if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_1) {
            NUMBER_OF_INDEX = 0;
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_2) {
            NUMBER_OF_INDEX = 1;
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_3) {
            NUMBER_OF_INDEX = 2;
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_4) {
            NUMBER_OF_INDEX = 3;
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_5) {
            NUMBER_OF_INDEX = 4;
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_6) {
            NUMBER_OF_INDEX = 5;
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_7) {
            NUMBER_OF_INDEX = 6;
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_8) {
            NUMBER_OF_INDEX = 7;
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_9) {
            NUMBER_OF_INDEX = 8;
        } else if (event.getTarget() == DASHBOARD_ANNCHORPANE_LISTENER_10) {
            NUMBER_OF_INDEX = 9;
        }
        
    }
    // End set The Number Of AnchorPane function

    @FXML
    private void getsubtitleFile() {
        
        subtitle = new parseSubtitle();
        String strFilePath = chooseFileExtension("srt", "");
        
        strFilePath = strFilePath.replace("file:/", "");
        strFilePath = strFilePath.replace("%20", " ");
        strFilePath = strFilePath.replace("%5", " ");
        subtitle.setStrFilePath(strFilePath);
        
        subtitleFound = true;
        subtitle.main();
        
    }
    
    private void setSubtitle(Duration newValue) {
        
        long value_second = (long) newValue.toSeconds();
        long value_millis = (long) newValue.toMillis();
        
        for (int i = 0; i < subtitle.getSubtitlesWord().size(); i++) {
            
            long time_in_second = (int) (subtitle.getSubtitlesWord().get(i).timeIn / 1000);
            long time_in_millis = (int) ((subtitle.getSubtitlesWord().get(i).timeIn) - (1000 * time_in_second));
            
            long time_out_second = (int) (subtitle.getSubtitlesWord().get(i).timeOut / 1000);
            long time_out_millis = (int) ((subtitle.getSubtitlesWord().get(i).timeOut) - (1000 * time_out_second));
            
            if (value_second >= time_in_second) {
                if (value_millis >= time_in_millis) {
                    if (subtitle.getSubtitlesWord().get(i).status == false) {
                        subtitle.getSubtitlesWord().get(i).status = true;
                        
                        SUBTITLE_CONTENT.setTextAlignment(TextAlignment.CENTER);
                        SUBTITLE_CONTENT.setText(subtitle.getSubtitlesWord().get(i).text);
                        
                        continue;
                    }
                }
            } else {
                subtitle.getSubtitlesWord().get(i).status = false;
                
            }
            
        }
        
    }

    // Start print function - this func to print all element in the content
    private void printAllPlaylistsInContent(Playlists playlistsType) throws InterruptedException {
        
        int sizeOfList = playlistsType.getPlaylists().size();
        
        if (playlistsType.getPlaylists().size() != 0) {
            
            for (int i = 0; i < sizeOfList; i++) {
                
                setDefaultValue(containers.get(i), addfile.get(i), titles.get(i), descriptions.get(i));

//                containers.get(i).setStyle(" -fx-background-color: transparent ");
//                DropShadow shadow = new DropShadow();
//                
//                shadow.setColor(Color.GRAY);
//                shadow.setHeight(15);
//                shadow.setWidth(15);
//                
//                imageContainers.get(i).setEffect(shadow);
//                
//                imageContainers.get(i).setPrefWidth(200);
//                imageContainers.get(i).setPrefHeight(238);
                imageContainers.get(i).setStyle("-fx-background-image: url( 'project_1/images/Tumbnail-images/" + (i + 1) + ".jpg' ); -fx-background-size: 180px;");
                imageContainers.get(i).setVisible(true);
                
                String fileTitle = setMaxCharOfFileTitle(playlistsType.getPlaylists().get(i).getTitle());
                titles.get(i).setText(fileTitle);
                
                String fileDescription = setMaxCharOfFileTitle(playlistsType.getPlaylists().get(i).getDescription());
                descriptions.get(i).setVisible(true);
                descriptions.get(i).setText(fileDescription);
                
                printElementInContent(containers.get(i), 100 + (i * 130));
                
                containersListener.get(i).setOnMouseReleased(event -> {
                    try {
                        
                        showPlaylist(playlistsType);
                        
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                if (sizeOfList != 10) {
                    
                    imageContainers.get(sizeOfList).setVisible(false);
                    
                    addPlayListMouseClick(containers.get(sizeOfList), containersListener.get(sizeOfList), addfile.get(sizeOfList), titles.get(sizeOfList), descriptions.get(sizeOfList), 100 + (i * 130));
                    
                }
                
            }
            
        }
        
        if (playlistsType.getPlaylists().size() == 0) {
            
            imageContainers.get(sizeOfList).setVisible(false);
            addPlayListMouseClick(containers.get(0), containersListener.get(0), addfile.get(0), titles.get(0), descriptions.get(0), 100);
        }
        
    }
    // End print function

    // Start show Playlist function - this func to show specicfic playlist
    private void showPlaylist(Playlists playlistsType) throws InterruptedException {
        printAllElementInContent(playlistsType.getPlaylists().get(NUMBER_OF_INDEX));
        CURRENT_PLAYLIST = NUMBER_OF_INDEX;
    }
    // End show Playlist function

    // Start print function - this func to print all element in the content
    private void printAllElementInContent(Playlist specificPlayList) throws InterruptedException {
        
        for (int i = 0; i < 10; i++) {
            setDefaultValue(containers.get(i), addfile.get(i), titles.get(i), descriptions.get(i));
        }
        
        int sizeOfList = specificPlayList.getVideos().size();
        
        PAGENITION_TEXT.setText("" + pageNumberPlaylist);
        
        PAGENATION_CENTER.setVisible(true);
        
        if (sizeOfList >= ((pageNumberPlaylist * 10))) {
            
            service(PAGENATION_RIGHT, 1400);
            
        }
        
        sizeOfList = (sizeOfList <= ((pageNumberPlaylist * 10))) ? (sizeOfList - ((pageNumberPlaylist - 1) * 10)) : 10;
        
        if (pageNumberPlaylist > 1) {
            
            int time = sizeOfList == 10 ? 10 : ((sizeOfList % 10) + 1);
            int duration = (100 + (time * 130));
            
            service(PAGENATION_LEFT, duration);
            
        }
        
        if (specificPlayList.getVideos().size() != 0) {
            
            for (int i = 0; i < sizeOfList; i++) {
                
                setDefaultValue(containers.get(i), addfile.get(i), titles.get(i), descriptions.get(i));
                
                imageContainers.get(i).setStyle("-fx-background-image: url( 'project_1/images/Tumbnail-images/" + (i + 1) + ".jpg' ) ; ");
                imageContainers.get(i).setVisible(true);
                
                String fileTitle = setMaxCharOfFileTitle(specificPlayList.getVideos().get(((pageNumberPlaylist - 1) * 10) + i).getTitle());
                String fileDesc = specificPlayList.getVideos().get(((pageNumberPlaylist - 1) * 10) + i).getDescription();
                titles.get(i).setText(fileTitle);
                
                descriptions.get(i).setVisible(true);
                descriptions.get(i).setText("Nice Truck");
                
                printElementInContent(containers.get(i), 100 + (i * 130));
                
                containersListener.get(i).setOnMouseReleased(event -> {
                    try {
                        
                        openFileState = true;
                        if (menuState.equals("VIDEO")) {
                            
                            VIDEO_PLAYER_CONTAINER.setVisible(true);
                            openFile_handleMouseEvent(event);
                            
                        } else if (menuState.equals("MUSIC")) {
                            
                            hideTransition(ELEMENT_CONTENT, 500);
                            showTransition(AUDIO_PLAYER_CONTAINER, 500);
                            openMusicFile_handleMouseEvent(event);
                        }
                        
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                if (((i + 1) + ((pageNumberPlaylist - 1) * 10) == specificPlayList.getVideos().size()) && ((sizeOfList - 10) != 0)) {
                    
                    imageContainers.get(sizeOfList).setVisible(false);
                    
                    addFileMouseClick(containers.get(sizeOfList), containersListener.get(sizeOfList), addfile.get(sizeOfList), titles.get(sizeOfList), descriptions.get(sizeOfList), 100 + (i * 130), specificPlayList);
                    
                }
                
            }
            
        }
        
        if ((pageNumberPlaylist - 1) * 10 == specificPlayList.getVideos().size()) {
            
            imageContainers.get(sizeOfList).setVisible(false);
            addFileMouseClick(containers.get(0), containersListener.get(0), addfile.get(0), titles.get(0), descriptions.get(0), 100, specificPlayList);
        }
        
    }
    // End print function

    // Start print function - this func to print single element in the content
    private void printElementInContent(AnchorPane anchorpane, int duration) throws InterruptedException {
        
        FadeTransition FADEINEFFECT = new FadeTransition();
        
        Service service = new Notifications(duration);
        if (!service.isRunning()) {
            service.start();
        }
        
        service.setOnSucceeded(e -> {
            showTransition(anchorpane, 500, FADEINEFFECT);
            service.reset();
        });
        
    }
    // End print function

    // Start print function - this func to print all liked element in the content
    private void printAllLikedElementInContent(Videos mediaType) throws InterruptedException {
        
        for (int i = 0; i < 10; i++) {
            setDefaultValue(containers.get(i), addfile.get(i), titles.get(i), descriptions.get(i));
        }
        
        int sizeOfList = mediaType.getVideos().size();
        
        PAGENITION_TEXT.setText("" + pageNumberLikedMedia);
        
        PAGENATION_CENTER.setVisible(true);
        
        if (sizeOfList > ((pageNumberLikedMedia * 10))) {
            
            service(PAGENATION_RIGHT, 1400);
            
        }
        
        sizeOfList = (sizeOfList <= ((pageNumberLikedMedia * 10))) ? (sizeOfList - ((pageNumberLikedMedia - 1) * 10)) : 10;
        
        if (pageNumberLikedMedia > 1) {
            
            int time = sizeOfList == 10 ? 10 : ((sizeOfList % 10) + 1);
            int duration = (100 + (time * 130));
            
            service(PAGENATION_LEFT, duration);
            
        }
        
        for (int i = 0; i < sizeOfList; i++) {
            
            setDefaultValue(containers.get(i), addfile.get(i), titles.get(i), descriptions.get(i));
            
            imageContainers.get(i).setStyle("-fx-background-image: url( 'project_1/images/Tumbnail-images/" + (i + 1) + ".jpg' ) ; ");
            imageContainers.get(i).setVisible(true);
            
            String fileTitle = setMaxCharOfFileTitle(mediaType.getVideos().get(((pageNumberLikedMedia - 1) * 10) + i).getTitle());
            String fileDesc = mediaType.getVideos().get(((pageNumberPlaylist - 1) * 10) + i).getDescription();
            titles.get(i).setText(fileTitle);
            
            descriptions.get(i).setVisible(true);
            descriptions.get(i).setText("Nice Truck");
            
            printElementInContent(containers.get(i), 100 + (i * 130));
            
            containersListener.get(i).setOnMouseReleased(event -> {
                try {
                    
                    openFileState = true;
                    if (menuState.equals("VIDEO")) {
                        
                        VIDEO_PLAYER_CONTAINER.setVisible(true);
                        openFile_handleMouseEvent(event);
                        
                    } else if (menuState.equals("MUSIC")) {
                        
                        hideTransition(ELEMENT_CONTENT, 500);
                        showTransition(AUDIO_PLAYER_CONTAINER, 500);
                        openMusicFile_handleMouseEvent(event);
                        
                    }
                    
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        }
        
    }
    // End print function

    // Start print function - this func to print all liked element in the content
    private void printRecentlyPlayedElementInContent(Videos specificVideos) throws InterruptedException {
        
        int sizeOfList = specificVideos.getVideos().size();
        
        if (sizeOfList > 10) {
            
            for (int i = 10; i < sizeOfList; i++) {
                specificVideos.getVideos().remove(10);
            }
            
        }
        
        sizeOfList = specificVideos.getVideos().size();
        
        for (int i = 0; i < sizeOfList; i++) {
            
            setDefaultValue(containers.get(i), addfile.get(i), titles.get(i), descriptions.get(i));
            
            imageContainers.get(i).setStyle("-fx-background-image: url( 'project_1/images/Tumbnail-images/" + (i + 1) + ".jpg' ) ; ");
            imageContainers.get(i).setVisible(true);
                
            String fileTitle = setMaxCharOfFileTitle(specificVideos.getVideos().get(i).getTitle());
            String fileDesc = specificVideos.getVideos().get(((pageNumberPlaylist - 1) * 10) + i).getDescription();
            
            titles.get(i).setText(fileTitle);
            
            descriptions.get(i).setVisible(true);
            descriptions.get(i).setText("Nice Truck");
            
            printElementInContent(containers.get(i), 100 + (i * 130));
            
            containersListener.get(i).setOnMouseReleased(event -> {
                try {
                    
                    openFileState = true;
                    if (menuState.equals("VIDEO")) {
                        
                        VIDEO_PLAYER_CONTAINER.setVisible(true);
                        openFile_handleMouseEvent(event);
                        
                    } else if (menuState.equals("MUSIC")) {
                        
                        hideTransition(ELEMENT_CONTENT, 500);
                        showTransition(AUDIO_PLAYER_CONTAINER, 500);
                        openMusicFile_handleMouseEvent(event);
                    }
                    
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        }
        
    }
    // End print function

    // Start add File Mouse Click function - this func to add file in specific playlist
    private void addFileMouseClick(AnchorPane container, AnchorPane listenerCon, AnchorPane addFilePane, Text title, Text Description, int duration, Playlist specificPlayList) {
        
        FadeTransition FADEINEFFECT = new FadeTransition();
        
        container.setStyle("-fx-background-color: transparent; -fx-border-width: .8px; -fx-border-color: #EEEEEE");
        
        Service service = new Notifications(duration);
        if (!service.isRunning()) {
            service.start();
        }
        
        service.setOnSucceeded(e -> {
            showTransition(container, 700, FADEINEFFECT);
            service.reset();
        });
        
        addFilePane.setVisible(true);
        title.setVisible(false);
        Description.setVisible(false);
        
        listenerCon.setOnMouseReleased(event -> {
            try {
                
                String filePath = "", fileTitle = "";
                
                if (menuState.equals("VIDEO")) {
                    filePath = chooseFileExtension("mp4", "avi");
                } else if (menuState.equals("MUSIC")) {
                    filePath = chooseFileExtension("mp3", "wav");
                }
                
                fileTitle = parseTitle(filePath);
                
                Video video = new Video();
                video.setPath(filePath);
                video.setTitle(fileTitle);
                video.setDescription("Nice Truck");
                video.setType(typeOfMedia(filePath));
                specificPlayList.addVideo(video);
                
                System.out.println("DONE ADD FILE");
                
                printAllElementInContent(specificPlayList);
                
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    // End add File Mouse Click function

    // Start add PlayList Mouse Click function - this func to add playlist 
    private void addPlayListMouseClick(AnchorPane container, AnchorPane listenerCon, AnchorPane addFilePane, Text title, Text Description, int duration) {
        
        FadeTransition FADEINEFFECT = new FadeTransition();
        
        container.setStyle("-fx-background-color: transparent; -fx-border-width: .8px; -fx-border-color: #EEEEEE");
        
        Service service = new Notifications(duration);
        if (!service.isRunning()) {
            service.start();
        }
        
        service.setOnSucceeded(e -> {
            showTransition(container, 700, FADEINEFFECT);
            service.reset();
        });
        
        addFilePane.setVisible(true);
        title.setVisible(false);
        Description.setVisible(false);
        
        listenerCon.setOnMouseReleased(event -> {
            try {
                
                dashboardBlur.setRadius(4);
                DASHBOARD_CONTENT_CONTAINER.setEffect(dashboardBlur);
                
                showTransition(ADD_PLAYLIST, 500);
                
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    // End add PlayList Mouse Click function

    // Start set Default Value function - this func reset the default value of each element in the dashboard section
    private void setDefaultValue(AnchorPane anchorpane, AnchorPane addFilePane, Text title, Text Description) {
        anchorpane.setStyle(" -fx-background-color: rgba( 255, 255, 255, .3 ); -fx-border-width: 0; -fx-border-color: transparent");
        anchorpane.setVisible(false);
        addFilePane.setVisible(false);
        title.setVisible(true);
        title.setText("");
        Description.setText("");
    }
    // End set Default Value function

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        applyColor();
        
        initElementHolder();
        
        TOP_SECTION_PANE.setVisible(true);
        topNavStatus = true;
        hideTopNav = false;
        
        loadData();
        
        try {
            printAllLikedElementInContent(likedMedia);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void saveDataPlaylist() {

        // Get the playlist object from the current App
        Playlists tempPlaylist = playlists;
        Playlists tempMusicPlaylist = musicPlaylists;

        // Define that the things we want to store is an "object"
        ObjectOutputStream out1;
        ObjectOutputStream out2;
        
        try {
            // Get the file that we want to save data on it
            // file_page is variable connected to the interface start with 1
            // and will be increase every time we hit the next file button in the interface
            File file1 = new File("save/playlists.ser");
            File file2 = new File("save/musicPlaylists.ser");

            // Define the output path
            out1 = new ObjectOutputStream(new FileOutputStream(file1));
            out2 = new ObjectOutputStream(new FileOutputStream(file2));

            // Write Data
            out1.writeObject(tempPlaylist);
            out2.writeObject(tempMusicPlaylist);

            // Close the file
            out1.flush();
            out1.close();
            
            out2.flush();
            out2.close();
            
            System.out.println("Object written to file");
        } // Handling file errors (Predefined errors in Java)
        catch (FileNotFoundException ex) {
            System.out.println("Error with specified file");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error with I/O processes");
            ex.printStackTrace();
        }
        
    }
    
    public void saveDataRecentlyPlayed() {

        // Get the recentlyPlayed object from the current game
        Videos tempRecentlyPlayed = recentlyPlayed;
        Videos tempMusicRecentlyPlayed = musicRecentlyPlayed;

        // Define that the things we want to store is an "object"
        ObjectOutputStream out1;
        ObjectOutputStream out2;
        
        try {
            // Get the file that we want to save data on it
            // file_page is variable connected to the interface start with 1
            // and will be increase every time we hit the next file button in the interface
            File file1 = new File("save/recentlyplayed.ser");
            File file2 = new File("save/musicRecentlyplayed.ser");

            // Define the output path
            out1 = new ObjectOutputStream(new FileOutputStream(file1));
            out2 = new ObjectOutputStream(new FileOutputStream(file2));

            // Write Data
            out1.writeObject(tempRecentlyPlayed);
            out2.writeObject(tempMusicRecentlyPlayed);

            // Close the file
            out1.flush();
            out1.close();
            
            out2.flush();
            out2.close();
            
            System.out.println("Object written to file");
        } // Handling file errors (Predefined errors in Java)
        catch (FileNotFoundException ex) {
            System.out.println("Error with specified file");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error with I/O processes");
            ex.printStackTrace();
        }
        
    }
    
    public void saveDataLikedMedia() {

        // Get the liked media object from the current game
        Videos tempLikedMedia = likedMedia;
        Videos musicTempLikedMedia = musicLikedMedia;

        // Define that the things we want to store is an "object"
        ObjectOutputStream out1;
        ObjectOutputStream out2;
        
        try {
            // Get the file that we want to save data on it
            // file_page is variable connected to the interface start with 1
            // and will be increase every time we hit the next file button in the interface
            File file1 = new File("save/likedmedia.ser");
            File file2 = new File("save/musicLikedmedia.ser");

            // Define the output path
            out1 = new ObjectOutputStream(new FileOutputStream(file1));
            out2 = new ObjectOutputStream(new FileOutputStream(file2));

            // Write Data
            out1.writeObject(tempLikedMedia);
            out2.writeObject(musicTempLikedMedia);

            // Close the file
            out1.flush();
            out1.close();
            
            out2.flush();
            out2.close();
            
            System.out.println("Object written to file");
        } // Handling file errors (Predefined errors in Java)
        catch (FileNotFoundException ex) {
            System.out.println("Error with specified file");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error with I/O processes");
            ex.printStackTrace();
        }
        
    }
    
    public void saveData() {
        
        saveDataPlaylist();
        saveDataRecentlyPlayed();
        saveDataLikedMedia();
        
    }
    
    public void loadDataPlaylist() {

        // We'll load an object of type playlist
        Playlists load_1 = null;
        Playlists load_2 = null;

        /* Read from file */
        try {
            // Reading the file
            FileInputStream fileIn_1 = new FileInputStream("save/playlists.ser");
            FileInputStream fileIn_2 = new FileInputStream("save/musicPlaylists.ser");

            //Define the input object path
            ObjectInputStream in_1 = new ObjectInputStream(fileIn_1);
            ObjectInputStream in_2 = new ObjectInputStream(fileIn_2);

            // Load an steps object
            load_1 = (Playlists) in_1.readObject();
            load_2 = (Playlists) in_2.readObject();

            // Close the file
            in_1.close();
            fileIn_1.close();
            
            in_2.close();
            fileIn_2.close();
        } // Handling Errors
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error with I/O processes");
            e.printStackTrace();
        }
        
        if (load_1 != null) {
            
            playlists = load_1;
            
        }
        if (load_2 != null) {
            
            musicPlaylists = load_2;
            
        }
        
    }
    
    public void loadDataRecentlyPlayed() {

        // We'll load an object of type playlist
        Videos load_1 = null;
        Videos load_2 = null;

        /* Read from file */
        try {
            // Reading the file
            FileInputStream fileIn_1 = new FileInputStream("save/recentlyplayed.ser");
            FileInputStream fileIn_2 = new FileInputStream("save/musicRecentlyplayed.ser");

            //Define the input object path
            ObjectInputStream in_1 = new ObjectInputStream(fileIn_1);
            ObjectInputStream in_2 = new ObjectInputStream(fileIn_2);

            // Load an steps object
            load_1 = (Videos) in_1.readObject();
            load_2 = (Videos) in_2.readObject();

            // Close the file
            in_1.close();
            fileIn_1.close();
            
            in_2.close();
            fileIn_2.close();
        } // Handling Errors
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error with I/O processes");
            e.printStackTrace();
        }
        
        if (load_1 != null) {
            
            recentlyPlayed = load_1;
            
        }
        if (load_2 != null) {
            
            musicRecentlyPlayed = load_2;
            
        }
        
    }
    
    public void loadDataLikedMedia() {

        // We'll load an object of type playlist
        Videos load_1 = null;
        Videos load_2 = null;

        /* Read from file */
        try {
            // Reading the file
            FileInputStream fileIn_1 = new FileInputStream("save/likedmedia.ser");
            FileInputStream fileIn_2 = new FileInputStream("save/musicLikedmedia.ser");

            //Define the input object path
            ObjectInputStream in_1 = new ObjectInputStream(fileIn_1);
            ObjectInputStream in_2 = new ObjectInputStream(fileIn_2);

            // Load an steps object
            load_1 = (Videos) in_1.readObject();
            load_2 = (Videos) in_2.readObject();

            // Close the file
            in_1.close();
            fileIn_1.close();
            
            in_2.close();
            fileIn_2.close();
        } // Handling Errors
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error with I/O processes");
            e.printStackTrace();
        }
        
        if (load_1 != null) {
            
            likedMedia = load_1;
            
        }
        if (load_2 != null) {
            
            musicLikedMedia = load_2;
            
        }
        
    }
    
    public void loadData() {
        
        loadDataPlaylist();
        loadDataRecentlyPlayed();
        loadDataLikedMedia();
        
    }
    
}
