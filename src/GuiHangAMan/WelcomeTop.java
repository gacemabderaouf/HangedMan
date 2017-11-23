package GuiHangAMan;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by Rifux on 07/05/2017.
 */
public class WelcomeTop extends VBox{
    private Text currentPlayerPseudonyme;
    private Text currentPlayerScore;
    private ImageView soundIcon;

    public WelcomeTop() {
        currentPlayerPseudonyme = new Text();
        currentPlayerPseudonyme.setId("currentPlayerPseudonyme");
        currentPlayerScore = new Text();
        currentPlayerScore.setId("currentPlayerScore");
        soundIcon=setSoundIcon();

        HBox settings = new HBox();
        settings.setAlignment(Pos.CENTER_RIGHT);
        settings.setId("settings");
        settings.setSpacing(20);
        settings.getChildren().addAll(currentPlayerPseudonyme,currentPlayerScore,soundIcon);

        Text welcomeText = new Text();
        welcomeText.setId("welcomeText");
        welcomeText.setText("Hanged Man Game");

        HBox mainText = new HBox();
        mainText.setAlignment(Pos.CENTER);
        mainText.setId("mainText");
        mainText.getChildren().add(welcomeText);

        getChildren().addAll(settings,mainText);
    }

    private ImageView setSoundIcon(){
        ImageView soundIcon = new ImageView();
        soundIcon.setId("soundIcon-on");
        soundIcon.setFitHeight(30);
        soundIcon.setFitWidth(20);

        soundIcon.setOnMouseEntered(e->{
            soundIcon.setFitHeight(35);
            soundIcon.setFitWidth(25);
        });

        soundIcon.setOnMouseExited(e->{
            soundIcon.setFitHeight(30);
            soundIcon.setFitWidth(20);
        });

        soundIcon.setOnMouseClicked(e->{
            SoundsPlayer.clickSoundStop();
            if(soundIcon.getId().equals("soundIcon-on")) {
                SoundsPlayer.clickSoundPlay();
                soundIcon.setId("soundIcon-off");
            }
            else soundIcon.setId("soundIcon-on");
        });

        return soundIcon;
    }


    public Text getCurrentPlayerPseudonyme() {
        return currentPlayerPseudonyme;
    }

    public Text getCurrentPlayerScore() {
        return currentPlayerScore;
    }

    public ImageView getSoundIcon() {
        return soundIcon;
    }
}
