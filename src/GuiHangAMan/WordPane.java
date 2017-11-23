package GuiHangAMan;

import KernelHangAMan.Square;
import KernelHangAMan.Word;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class WordPane extends VBox {
    private Word word;
    private int succededSquares;
    private int finalScore;
    private FlowPane hintPane;
    private GridPane squaresPane;
    private FlowPane answerPane;
    private Button nextWordButton;
    private HBox header;
    private GameSessionPane gameSessionPane;

    public WordPane(Word word) {
        this.word = word;
        succededSquares = 0;
        finalScore = 0;
        renderHint();
        renderSquaresPane();
        renderHeader();
    }

    public void updateSuccededSquares() {
        SoundsPlayer.playSuccesSound();
        if (++succededSquares == word.getSquares().size()) wordDone();
    }

    public void wordDone() {
        finalScore = word.getScore();
        gameSessionPane.updateScore(finalScore);
        gameSessionPane.updateGoodOrBad(succededSquares == word.getSquares().size());
        renderAnswerPane();
        renderNextWordButton();
        squaresPane.setDisable(true);
    }

    public void updateHeader(Node node, double layoutX) {
        header.getChildren().clear();
        header.getChildren().add(node);
        squaresPane.getChildren().remove(header);
        squaresPane.add(header, 0, 0);
        header.setTranslateX(layoutX);
    }

    private void renderHint() {
        hintPane = new FlowPane();
        hintPane.setPadding(new Insets(10, 0, 0, 10));
        hintPane.getStyleClass().add("hintPane");
        ImageView imageView = new ImageView(new Image("./images/sign-question-icon.png"));
        Label hint = new Label(word.getHintType() + ": " + word.getHint());
        hintPane.getChildren().addAll(imageView, hint);
        hintPane.setHgap(3);
        getChildren().add(hintPane);
    }

    private void renderSquaresPane() {
        squaresPane = new GridPane();
        squaresPane.setAlignment(Pos.CENTER);
        squaresPane.setVgap(4);
        squaresPane.setPadding(new Insets(0, 0, 10, 0));
        HBox hBox = new HBox();
        hBox.setSpacing(13);

        for (Square square : word.getSquares()) {
            SquarePane squarePane = SquareHandlersFactory.getSquareHandeler(square);
            squarePane.setWordHandler(this);
            hBox.getChildren().add(squarePane);
        }

        squaresPane.add(hBox, 0, 1);
        squaresPane.getStyleClass().add("squaresPane");
        getChildren().add(squaresPane);

    }

    private void renderAnswerPane() {
        answerPane = new FlowPane();
        answerPane.setAlignment(Pos.CENTER);
        answerPane.getStyleClass().add("answerPane");
        answerPane.setPadding(new Insets(10, 0, 0, 0));
        Label answer = new Label("La reponse: " + word.getExpectedWord());
        answerPane.getChildren().add(answer);
        getChildren().add(answerPane);
    }

    private void renderNextWordButton() {
        nextWordButton = new Button("  Next Word", new ImageView(new Image("./images/rightArrow.png")));
        nextWordButton.setContentDisplay(ContentDisplay.RIGHT);
        nextWordButton.setGraphicTextGap(0);
        nextWordButton.setPadding(new Insets(-0.2, 0, -0.2, 0));
        nextWordButton.getStyleClass().add("nextWordPane");
        nextWordButton.setOnAction(event -> {
            gameSessionPane.nextWord();
        });
        nextWordButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) gameSessionPane.nextWord();
        });

        HBox hBox = new HBox(nextWordButton);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        nextWordButton.setTranslateX(-20);
        getChildren().add(hBox);
    }

    private void renderHeader() {
        header = new HBox();
    }

    public void setGameSessionPane(GameSessionPane gameSessionPane) {
        this.gameSessionPane = gameSessionPane;
    }

}
