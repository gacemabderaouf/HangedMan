package GuiHangAMan;

import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Created by Rifux on 07/05/2017.
 */
public class GoBackButton extends Button{


    public GoBackButton(Scene welcomeScene, WelcomeButtons welcomeButtons) {
        setText("GO BACK");
        setId("goBackToWelcome");
        setOnMouseClicked(e->{
            SoundsPlayer.clickSoundStop();
            if(welcomeButtons.top.getSoundIcon().getId().equals("soundIcon-on")) SoundsPlayer.clickSoundPlay();
            welcomeScene.setRoot(welcomeButtons);
        });
    }
}
