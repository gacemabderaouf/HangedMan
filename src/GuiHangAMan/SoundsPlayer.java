package GuiHangAMan;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class SoundsPlayer {

    private static final String GOOD_SQUARE_SOUND = "./music/goodSquare.mp3";
    private static final String RETRY_SQUARE = "./music/retrySquare.mp3";
    private static final String FAILURE_SQUARE = "./music/failureSquare.wav";
    private static final String BACK_GROUND_MUSIC = "./music/backGroundMusic.mp3";
    private static final String GAME_OVER_MUSIC = "./music/gameover.wav";
    private static final String GAME_END_MUSIC = "./music/gameEnd.mp3";
    private static final String CLICK_SOUND = "music/click.mp3";

    private static MediaPlayer backGroundMusicPlayer;
    private static MediaPlayer clickSound;



    public static void playSuccesSound() {
        Media sound = new Media(new File(GOOD_SQUARE_SOUND).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void playRetrySound() {
        Media sound = new Media(new File(RETRY_SQUARE).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void playFailureSound() {
        Media sound = new Media(new File(FAILURE_SQUARE).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }

    public static void playGameOverMusic() {
        Media sound = new Media(new File(GAME_OVER_MUSIC).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void playGameEndMusic() {
        Media sound = new Media(new File(GAME_END_MUSIC).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void initBackGroundMusic() {
        backGroundMusicPlayer = new MediaPlayer(
                new Media(
                        new File(BACK_GROUND_MUSIC).toURI().toString()
                )
        );

        backGroundMusicPlayer.setOnEndOfMedia(() -> backGroundMusicPlayer.seek(Duration.ZERO));
    }

    public static void playBackGroundMusic() {
        backGroundMusicPlayer.play();
    }

    public static void stopBackGroundMusic() {
        backGroundMusicPlayer.stop();
    }

    public static void initClickSound(){
        clickSound = new MediaPlayer(
                new Media(new File(CLICK_SOUND).toURI().toString())
        );
    }

    public static void clickSoundPlay(){clickSound.play();}
    public static void clickSoundStop(){clickSound.stop();}
}
