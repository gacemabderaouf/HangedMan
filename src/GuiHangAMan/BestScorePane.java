package GuiHangAMan;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by Rifux on 07/05/2017.
 */
public class BestScorePane extends WelcomeRoot {
    private Text scoresAll;
    private Text currentPlayerScores;
    private VBox bestScoresPane;

    public BestScorePane(Scene welcomeScene) {
        super(welcomeScene);

        bestScoresPane = new VBox();
        bestScoresPane.setId("bestScoresPane");
        bestScoresPane.setAlignment(Pos.CENTER);
        bestScoresPane.setSpacing(15);

        scoresAll = new Text();
        scoresAll.getStyleClass().add("scores");

        currentPlayerScores=new Text();
        currentPlayerScores.getStyleClass().add("scores");

        HBox hBox =new HBox(170);
        hBox.getChildren().addAll(currentPlayerScores,scoresAll);
        hBox.setAlignment(Pos.TOP_CENTER);

        bestScoresPane.getChildren().addAll(setScoresHeadList(),hBox);
        setCenter(bestScoresPane);
    }


    private VBox setScoresHeadList(){
        Text scoresHeadList = new Text();
        scoresHeadList.getStyleClass().add("scoresHeadList");
        scoresHeadList.setText("HIGHEST SCORES ARE :\n");

        Text personal= new Text();
        personal.getStyleClass().add("scoresHeadList");
        personal.setText("PERSONAL SCORES");

        Text players= new Text();
        players.getStyleClass().add("scoresHeadList");
        players.setText("ALL PLAYERS SCORES");

        HBox hBox = new HBox(50);
        hBox.getChildren().addAll(personal,players);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(scoresHeadList,hBox);

        return vBox;
    }

    public Text getScoresAll() {
        return scoresAll;
    }

    public VBox getBestScoresPane() {
        return bestScoresPane;
    }

    public Text getCurrentPlayerScores() {
        return currentPlayerScores;
    }

    public void addGoBackButton(WelcomeButtons welcomeButtons){
        getBestScoresPane().getChildren().add(new GoBackButton(getWelcomeScene(),welcomeButtons));
    }
}
