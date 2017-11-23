package GuiHangAMan;

import KernelHangAMan.Square;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Created by Mekhalfa Taki Eddine on 20/04/2017.
 */

public class ZeroSquarePane extends SquarePane {


    public ZeroSquarePane(Square square) {
        super(square);
        setPrefColumnCount(1);
        getStyleClass().add("zeroSquareNode");

        focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue && isEditable()) {
                HBox hBox = new HBox(new Label(""));
                hBox.setPrefHeight(40);
                wordHnadler.updateHeader(hBox, this.getLayoutX());
            }
        });
    }

    @Override
    public void validate(String answer) {
        square.setEffectiveChar(answer);
        setText(answer);
        switch (square.validate()) {
            case SUCCESS: {
                setStyle("-fx-background-color: #00C851");
                wordHnadler.updateSuccededSquares();
            }
            break;
            case FAILURE: {
                setStyle("-fx-background-color: #ff4444");
                wordHnadler.wordDone();
            }
            break;
        }
        setEditable(false);
        setFocusTraversable(false);
        focusNext();

    }
}
