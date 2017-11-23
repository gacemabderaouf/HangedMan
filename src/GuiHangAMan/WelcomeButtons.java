package GuiHangAMan;

import KernelHangAMan.Player;
import KernelHangAMan.Players;
import KernelHangAMan.State;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Rifux on 07/05/2017.
 */
public class WelcomeButtons extends WelcomeRoot {
    private GameSessionPane gameSessionPane;
    private Stage stage;
    private BestScorePane bestScorePane;
    private ModifFormPane modifFormPane;
    private FormPane formPane;

    public WelcomeButtons(Stage stage,Scene welcomeScene, GameSessionPane gameSessionPane,BestScorePane bestScorePane,ModifFormPane modifFormPane,FormPane formPane) {
        super(welcomeScene);
        this.gameSessionPane=gameSessionPane;
        this.stage=stage;
        this.bestScorePane=bestScorePane;
        this.modifFormPane=modifFormPane;
        this.formPane=formPane;

        bestScorePane.addGoBackButton(this);
        modifFormPane.setWelcomeButtons(this);
        modifFormPane.getGridPane().add(new GoBackButton(getWelcomeScene(), this), 1, 5);
        formPane.setWelcomeButtons(this);

        VBox welcomeButtons = new VBox();

        welcomeButtons.setId("welcomeButtons");
        welcomeButtons.setAlignment(Pos.CENTER);
        welcomeButtons.setSpacing(15);

        welcomeButtons.getChildren().addAll(setPlayButton(),setModifyPseudonymeButton(),setChangePlayerButton(),setBestScoresButton(),setQuitButton());
        setCenter(welcomeButtons);

    }

    private Button setPlayButton(){
        Button playButton = new Button("PLAY");
        playButton.getStyleClass().add("welcomeButtons");

        playButton.setOnMouseClicked(e->{
            SoundsPlayer.clickSoundStop();
            if(top.getSoundIcon().getId().equals("soundIcon-on")) SoundsPlayer.clickSoundPlay();
            gameSessionPane = new GameSessionPane(Main.player);
            gameSessionPane.setWelcomeButton(this);
            stage.setWidth(800);
            stage.setHeight(550);
            stage.centerOnScreen();
            getWelcomeScene().setRoot(gameSessionPane);
        });

        return playButton;
    }

    private Button setQuitButton(){
        Button quitButton = new Button("QUIT");
        quitButton.setId("quitButton");

        quitButton.setOnMouseClicked(e->{
            SoundsPlayer.clickSoundStop();
            if(top.getSoundIcon().getId().equals("soundIcon-on")) SoundsPlayer.clickSoundPlay();

            stage.close();
            Players.dbToFile();
        });

        return quitButton;
    }

    private Button setBestScoresButton(){
        Button bestScoresButton = new Button("BEST SCORES");
        bestScoresButton.getStyleClass().add("welcomeButtons");

        bestScoresButton.setOnMouseClicked(e->{
            SoundsPlayer.clickSoundStop();
            if(top.getSoundIcon().getId().equals("soundIcon-on")) SoundsPlayer.clickSoundPlay();
            getWelcomeScene().setRoot(bestScorePane);

            List<Player> l= Players.getBestScores(10);
            String s=""; int i=0;
            for (Player p :l) {
                i++;
                s+=i+" - "+p.toString();
            }
            bestScorePane.getScoresAll().setText(s);

            l= Players.getBestScores(Main.player.getPseudonyme(),10);
            s="";  i=0;
            for (Player p :l) {
                i++;
                s+=i+" - "+p.toString();
            }
            bestScorePane.getCurrentPlayerScores().setText(s);
        });

        return bestScoresButton;
    }

    private Button setChangePlayerButton(){
        Button changePlayerButton=new Button("CHANGE PLAYER");
        changePlayerButton.getStyleClass().add("welcomeButtons");

        changePlayerButton.setOnMouseClicked(e->{
            SoundsPlayer.clickSoundStop();
            if(top.getSoundIcon().getId().equals("soundIcon-on")) SoundsPlayer.clickSoundPlay();
            getWelcomeScene().setRoot(formPane);
        });

        return changePlayerButton;
    }

    private Button setModifyPseudonymeButton(){
        Button modifyPseudonymeButton=new Button("MODIFY PSEUDONYME");
        modifyPseudonymeButton.getStyleClass().add("welcomeButtons");

        modifyPseudonymeButton.setOnMouseClicked(e->{
            SoundsPlayer.clickSoundStop();
            if(top.getSoundIcon().getId().equals("soundIcon-on")) SoundsPlayer.clickSoundPlay();
            getWelcomeScene().setRoot(modifFormPane);
        });
        return modifyPseudonymeButton;
    }

    public GameSessionPane getGameSessionPane() {
        return gameSessionPane;
    }
}
