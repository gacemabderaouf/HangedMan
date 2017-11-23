package GuiHangAMan;

import KernelHangAMan.Player;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameSession extends Stage {

    Player player;

    public GameSession(Player player) {
        super();
        this.player = player;
        GameSessionPane gameSessionPane = new GameSessionPane(player);
        Scene scene = new Scene(gameSessionPane, 600, 600);
        scene.getStylesheets().add("HangAManStyleSheet.css");
        setScene(scene);
        setOnCloseRequest(event -> SoundsPlayer.stopBackGroundMusic());
        show();
    }


}
