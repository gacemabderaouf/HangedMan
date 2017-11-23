package GuiHangAMan;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Created by Rifux on 07/05/2017.
 */

public class WelcomeRoot extends BorderPane {
    WelcomeTop top=new WelcomeTop();
    private Scene welcomeScene;

    public WelcomeRoot(Scene welcomeScene) {
        this.welcomeScene=welcomeScene;
        setId("welcomeRoot");
        setTop(top);
        getStylesheets().addAll("styleSheet.css");
        SoundsPlayer.initClickSound();
    }



    public Scene getWelcomeScene() {
        return welcomeScene;
    }

}
