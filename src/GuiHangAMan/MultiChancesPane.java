package GuiHangAMan;

import KernelHangAMan.MultiChance;
import KernelHangAMan.Square;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * Created by Mekhalfa Taki Eddine on 20/04/2017.
 */

public class MultiChancesPane extends SquarePane {

    HBox remainingChances;
    TextField resultField;

    public MultiChancesPane(Square square) {
        super(square);
        setPrefColumnCount(1);
        renderRemainingChances();
        getStyleClass().add("multiChancesNode");

        focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && isEditable()) {
                remainingChances.setVisible(true);
                wordHnadler.updateHeader(remainingChances, this.getLayoutX());
            } else remainingChances.setVisible(false);
        });


    }


    @Override
    public void validate(String answer) {
        setText(answer);
        square.setEffectiveChar(answer);
        switch (square.validate()) {
            case SUCCESS: {
                setStyle("-fx-background-color: #00C851");
                wordHnadler.updateSuccededSquares();
                focusNext();
                setEditable(false);
                setFocusTraversable(false);

            }
            break;
            case FAILURE: {
                setStyle("-fx-background-color: #ff4444");
                wordHnadler.wordDone();
                setEditable(false);
                setFocusTraversable(false);
                remainingChances.setVisible(false);
            }
            break;
            case RETRY: {
                doTheShaking();
                SoundsPlayer.playRetrySound();
                resultField.setText(((MultiChance) square).getRemainingChances() + "");
                setStyle("-fx-background-color: #FF8800");
            }
            break;
        }
    }

    private void renderRemainingChances() {
        resultField = new TextField();
        resultField.getStyleClass().add("remainingChancesPane");
        resultField.setPrefColumnCount(1);
        resultField.setAlignment(Pos.CENTER);
        resultField.setEditable(false);
        resultField.setText(((MultiChance) square).getRemainingChances() + "");
        remainingChances = new HBox(resultField);
        remainingChances.setPrefHeight(40);
        remainingChances.setAlignment(Pos.BOTTOM_CENTER);
    }

    private void doTheShaking() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0),new KeyValue(translateXProperty(),getTranslateX()+4)),
                new KeyFrame(Duration.millis(100),new KeyValue(translateXProperty(),getTranslateX()))
        );
        timeline.play();
    }
}
