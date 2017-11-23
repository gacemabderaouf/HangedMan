package GuiHangAMan;

import KernelHangAMan.Player;
import KernelHangAMan.Players;
import KernelHangAMan.Word;
import KernelHangAMan.Words;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class GameSessionPane extends BorderPane {

    private static final int NUMBER_OF_WORDS = 10;
    private static final int MAX_BAD_WORDS = 6;

    private HBox topHeader;
    private Label topScore;
    private Label currentScoreLabel;
    private Label currentWordLabel;
    private Label pseudonyme;
    private HBox howManyGoodOrBad;
    private HBox hangedManPane;
    private Player player;
    private List<Word> words;
    private List<WordPane> wordPanes;
    private HBox volumeController;
    private ImageView goBack;
    private ImageView tutorial;
    private WelcomeButtons welcomeButtons;
    private Stage tutorialStage;
    private int currentScore;
    private int currentWord;
    private int goodWords;
    private int badWords;
    private boolean volumeOn;

    public GameSessionPane(Player player) {
        this.player = player;
        getStyleClass().add("gameSession");
        getStylesheets().add("HangAManStyleSheet.css");
        initGame();
    }


    private void renderTopHeader() {
        topHeader = new HBox();
        pseudonyme = new Label();
        pseudonyme.setText(player.getPseudonyme());
        topScore = new Label();
        topScore.setText("Best Score: " + player.getHighestScore());
        currentScoreLabel = new Label("Current Score: 0");
        currentWordLabel = new Label("Current Word: 1/" + NUMBER_OF_WORDS);
        topHeader.getChildren().addAll(pseudonyme, topScore, currentScoreLabel, currentWordLabel);
        topHeader.setSpacing(20);
        topHeader.setPadding(new Insets(10, 5, 10, 5));
        topHeader.getStyleClass().add("topHeaderGameSession");
        topHeader.setAlignment(Pos.CENTER);
        topHeader.setStyle("-fx-font-weight: bold");
        setTop(topHeader);

    }


    public void nextWord() {

        currentWord++;
        setCenter(wordPanes.get(currentWord - 1));
        updateCurrentWord();
    }

    private void updateCurrentWord() {
        currentWordLabel.setText("Current Word: " + currentWord + "/" + NUMBER_OF_WORDS);
    }

    public void updateGoodOrBad(boolean good) {
        ImageView sadFace = new ImageView(new Image("./images/sadface.png"));
        ImageView happyFace = new ImageView(new Image("./images/smile.png"));
        howManyGoodOrBad.getChildren().clear();
        if (good)
            howManyGoodOrBad.getChildren().addAll(
                    happyFace, new Label(++goodWords + ""), sadFace, new Label(badWords + "")
            );
        else {
            howManyGoodOrBad.getChildren().addAll(
                    happyFace, new Label(goodWords + ""), sadFace, new Label(++badWords + "")
            );
            SoundsPlayer.playFailureSound();
            updateDrawing();
        }

        if (badWords == MAX_BAD_WORDS) gameOver();
        else if (currentWord == NUMBER_OF_WORDS) gameEnd();
    }

    private void renderGoodOrBad() {
        howManyGoodOrBad = new HBox();
        ImageView sadFace = new ImageView(new Image("./images/sadface.png"));
        ImageView happyFace = new ImageView(new Image("./images/smile.png"));
        howManyGoodOrBad.getChildren().addAll(
                happyFace, new Label("0"), sadFace, new Label("0")
        );
        howManyGoodOrBad.setSpacing(10);
        howManyGoodOrBad.setAlignment(Pos.CENTER);
        howManyGoodOrBad.setStyle("-fx-font-size: 15px;");
        topHeader.getChildren().add(howManyGoodOrBad);
    }

    public void updateScore(int score) {
        currentScore += score;
        currentScoreLabel.setText("Current Score: " + currentScore);
    }

    public void initGame() {
        currentWord = 0;
        goodWords = 0;
        badWords = 0;
        currentScore = 0;
        words = Words.generateRandom(NUMBER_OF_WORDS);
        wordPanes = new ArrayList<>(words.size());
        for (Word word : words
                ) {
            word.generateSquares();
            WordPane wordPane = new WordPane(word);
            wordPane.setGameSessionPane(this);
            wordPanes.add(wordPane);
        }
        renderTopHeader();
        nextWord();
        renderGoodOrBad();
        renderHangedManPane();
        renderGoBack();
        renderVolumeController();
        renderTutorial();
        renderTutorialStage();
        SoundsPlayer.initBackGroundMusic();
        SoundsPlayer.playBackGroundMusic();

    }


    private void renderVolumeController() {
        volumeOn = true;
        ImageView imageViewTempo = new ImageView(new Image("./images/volumeOn.png"));
        imageViewTempo.setFitWidth(35);
        imageViewTempo.setFitHeight(35);
        volumeController = new HBox(imageViewTempo);
        volumeController.setAlignment(Pos.CENTER);
        volumeController.getStyleClass().add("volumeController");
        volumeController.setOnMouseClicked(event -> {

            ImageView imageView = new ImageView();
            imageView.setFitHeight(35);
            imageView.setFitWidth(35);
            if (volumeOn) {
                imageView.setImage(new Image("/images/volumeOff.png"));
                SoundsPlayer.stopBackGroundMusic();
            } else {
                imageView.setImage(new Image("./images/volumeOn.png"));
                SoundsPlayer.playBackGroundMusic();
            }

            volumeOn = !volumeOn;
            volumeController.getChildren().clear();
            volumeController.getChildren().add(imageView);
        });


        topHeader.getChildren().add(volumeController);
    }

    private void renderHangedManPane() {

        hangedManPane = new HBox(new ImageView());
        hangedManPane.setAlignment(Pos.CENTER);
        setBottom(hangedManPane);
    }

    private void updateDrawing() {
        switch (badWords) {
            case 1: {
                /*CubicCurve cubicCurve = new CubicCurve(340, 292, 349, 270, 94, 269, 116, 298);
                cubicCurve.setFill(Color.TRANSPARENT);
                cubicCurve.setStroke(Color.BLACK);
                cubicCurve.setStrokeWidth(4);
                cubicCurve.setStrokeLineCap(StrokeLineCap.ROUND);
                hangedManPane.getChildren().add(cubicCurve);*/
                ((ImageView) (hangedManPane.getChildren().get(0))).setImage(new Image("./images/hangedman0.png"));
            }
            break;
            case 2: {
/*
                CubicCurve cubicCurve = new CubicCurve(215, 280, 222, 109, 176, 4, 385, 34);
                cubicCurve.setFill(Color.TRANSPARENT);
                cubicCurve.setStroke(Color.BLACK);
                cubicCurve.setStrokeWidth(4);
                cubicCurve.setStrokeLineCap(StrokeLineCap.ROUND);
                hangedManPane.getChildren().add(cubicCurve);
*/
                ((ImageView) (hangedManPane.getChildren().get(0))).setImage(new Image("./images/hangedman1.png"));

            }
            break;
            case 3: {
                /*CubicCurve cubicCurve = new CubicCurve(217, 123, 216, 125, 344, 24, 345, 26);
                cubicCurve.setFill(Color.TRANSPARENT);
                cubicCurve.setStroke(Color.BLACK);
                cubicCurve.setStrokeLineCap(StrokeLineCap.ROUND);
                cubicCurve.setStrokeWidth(4);

                CubicCurve cubicCurve1 = new CubicCurve(384, 34, 474, 46, 463, 134, 403, 123);
                cubicCurve1.setFill(Color.TRANSPARENT);
                cubicCurve1.setStroke(Color.BLACK);
                cubicCurve1.setStrokeLineCap(StrokeLineCap.ROUND);
                cubicCurve1.setStrokeWidth(4);

                hangedManPane.getChildren().addAll(cubicCurve, cubicCurve1);*/
                ((ImageView) (hangedManPane.getChildren().get(0))).setImage(new Image("./images/hangedman2.png"));
            }
            break;
            case 4: {
               /* CubicCurve cubicCurve = new CubicCurve(405, 70, 299, 151, 482, 133, 407, 70);
                cubicCurve.setFill(Color.TRANSPARENT);
                cubicCurve.setStroke(Color.BLACK);
                cubicCurve.setStrokeLineCap(StrokeLineCap.ROUND);
                cubicCurve.setStrokeWidth(4);

                hangedManPane.getChildren().add(cubicCurve);*/
                ((ImageView) (hangedManPane.getChildren().get(0))).setImage(new Image("./images/hangedman3.png"));
            }
            break;
            case 5: {
              /*  CubicCurve cubicCurve = new CubicCurve(346, 187, 401, 120, 396, 125, 448, 198);
                cubicCurve.setFill(Color.TRANSPARENT);
                cubicCurve.setStroke(Color.BLACK);
                cubicCurve.setStrokeLineCap(StrokeLineCap.ROUND);
                cubicCurve.setStrokeWidth(4);

                CubicCurve cubicCurve1 = new CubicCurve(393, 207, 392, 206, 402, 123, 403, 121);
                cubicCurve1.setFill(Color.TRANSPARENT);
                cubicCurve1.setStroke(Color.BLACK);
                cubicCurve1.setStrokeLineCap(StrokeLineCap.ROUND);
                cubicCurve1.setStrokeWidth(4);

//                hangedManPane.getChildren().addAll(cubicCurve, cubicCurve1);*/
                ((ImageView) (hangedManPane.getChildren().get(0))).setImage(new Image("./images/hangedman4.png"));

            }
            break;
            case 6: {
                /*CubicCurve cubicCurve = new CubicCurve(331, 263, 430, 211, 354, 175, 447, 260);
                cubicCurve.setFill(Color.TRANSPARENT);
                cubicCurve.setStroke(Color.BLACK);
                cubicCurve.setStrokeWidth(4);
                cubicCurve.setStrokeLineCap(StrokeLineCap.ROUND);
//                hangedManPane.getChildren().add(cubicCurve);
                gameOver();*/
                ((ImageView) (hangedManPane.getChildren().get(0))).setImage(new Image("./images/hangedman5.png"));
            }
        }
    }

    private void gameOver() {
        renderFinalPane("./images/gameOverLogo.png");
        SoundsPlayer.playGameOverMusic();
    }

    public void gameEnd() {
        renderFinalPane("./images/theEnd.png");
        SoundsPlayer.playGameEndMusic();

    }

    private void renderFinalPane(String imageUrl) {
        ImageView gameOver = new ImageView(imageUrl);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.millis(2000),
                        new KeyValue(gameOver.rotateProperty(), 720)
                )
        );


        Button replayButton = new Button("Restart", new ImageView(new Image("./images/replay.png")));
        replayButton.getStyleClass().add("replayButton");
        replayButton.setOnAction(event -> {
            if (SaveRequest.saveScoreRequest()) Players.addScore(Main.player.getPseudonyme(), currentScore);
            initGame();
        });
        replayButton.setVisible(false);


        Button closeButton = new Button("Close", new ImageView(new Image("./images/close.png")));
        closeButton.getStyleClass().add("closeButton");
        closeButton.setVisible(false);

        closeButton.setOnAction(event -> {

            if (SaveRequest.saveScoreRequest()) Players.addScore(Main.player.getPseudonyme(), currentScore);

            getScene().getWindow().setWidth(550);
            getScene().getWindow().setHeight(600);
            getScene().getWindow().centerOnScreen();
            getScene().setRoot(welcomeButtons);

        });

        VBox vBox = new VBox(gameOver, replayButton, closeButton);

        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        setCenter(vBox);

        timeline.play();
        timeline.setOnFinished(event -> {
            replayButton.setVisible(true);
            closeButton.setVisible(true);
        });

        SoundsPlayer.stopBackGroundMusic();
        volumeController.setDisable(true);
    }

    public void renderGoBack() {
        goBack = new ImageView(new Image("./images/backArrow.png"));
        goBack.setOnMouseClicked(event -> {
            getScene().getWindow().setWidth(550);
            getScene().getWindow().setHeight(600);
            getScene().getWindow().centerOnScreen();
            getScene().setRoot(welcomeButtons);
            SoundsPlayer.stopBackGroundMusic();
        });

        goBack.setOnMouseEntered(event ->
                goBack.setImage(new Image("./images/backArrowHovered.png")));

        goBack.setOnMouseExited(event ->
                goBack.setImage(new Image("./images/backArrow.png")));

        topHeader.getChildren().add(goBack);
    }

    private void renderTutorial() {
        tutorial = new ImageView(new Image("./images/closedBook.png"));
        tutorial.setOnMouseEntered(event -> tutorial.setImage(new Image("./images/openBook.png")));
        tutorial.setOnMouseExited(event -> tutorial.setImage(new Image("./images/closedBook.png")));
        tutorial.setOnMouseClicked(event -> {
            tutorialStage.showAndWait();
        });
        topHeader.getChildren().add(tutorial);
    }

    private void renderTutorialStage() {
        Label label = new Label("Tutorial:");
        Label label1 = new Label("In this game, you have to find a french word!, To help you we have three types of hints");
        HBox hBox = new HBox(new Label("The hint is after this icon"), new ImageView(new Image("./images/sign-question-icon.png")), new Label(", hints are: A DEFINITION (x3), A SYNONYM (x2), An ANTONYM (x1)"));

        Label label2 = new Label("You have also three types a squares:");
        HBox hBox1 = new HBox(new ImageView(new Image("./images/zero.jpg")), new Label("A zero Chance square, you should give the correct answer in the first try, it costs +3pts"));

        HBox hBox2 = new HBox(new ImageView(new Image("./images/propo.jpg")), new Label("A propositions square, you will have 4 propositions, and you should guess the correct in the first time, it costs +2pts"));
        HBox hBox3 = new HBox(new ImageView(new Image("./images/multiChance.jpg")), new Label("A multi Chance square, you have 3 chances to get it correct, if you fail 3 times you will loose the word, it costs +1pts"));

        Label label3 = new Label("The score of the word is the sum of the correct squares times the hint type points");

        Label label4 = new Label("In the case where you have more than 5 propositions and multichance squares, you will have a penalty");
        Label label5 = new Label("A failed proposition square costs -1pts && each failed attempt in multiChance costs -2pts ");

        hBox1.setAlignment(Pos.CENTER_LEFT);
        hBox1.setSpacing(10);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.setSpacing(10);
        hBox3.setAlignment(Pos.CENTER_LEFT);
        hBox3.setSpacing(10);

        VBox vBox = new VBox(
            hBox1,hBox2,hBox3
        );
        vBox.setSpacing(10);

        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(5);
        VBox vBox1 = new VBox(label, label1, hBox, label2, vBox,label3,label4,label5);
        vBox1.setPadding(new Insets(5,5,5,10));
        vBox1.setSpacing(25);
        Scene scene = new Scene(vBox1);
        tutorialStage = new Stage();
        tutorialStage.setScene(scene);

    }

    public void setWelcomeButton(WelcomeButtons welcomeButtons) {
        this.welcomeButtons = welcomeButtons;
    }

    public void setPlayer(Player player) {

        this.player = player;
    }
}


