package GuiHangAMan;

import KernelHangAMan.Propositions;
import KernelHangAMan.Square;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.List;

/**
 * Created by Mekhalfa Taki Eddine on 20/04/2017.
 */
public class PopositionsSquarePane extends ZeroSquarePane {

    List<String> propositions;
    GridPane buttonsPane;

    public PopositionsSquarePane(Square square) {
        super(square);
        getStyleClass().add("propositionsNode");

        setPrefColumnCount(1);
        renderButtons();

        focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue && isEditable()) {
                wordHnadler.updateHeader(buttonsPane, this.getLayoutX());
            }
        });



    }

    @Override
    public void handle(KeyEvent event) {
        event.consume();
        String answer = event.getCharacter().toUpperCase();
        boolean accepted = ((Propositions) square).getPropositions().contains(answer);
        if (Character.isAlphabetic(answer.charAt(0)) && accepted) {
            if (isEditable()) validate(answer);
        }
    }

    private void renderButtons(){
        buttonsPane = new GridPane();
        buttonsPane.setPrefHeight(40);
        buttonsPane.setHgap(1);
        buttonsPane.setAlignment(Pos.BOTTOM_CENTER);
        buttonsPane.addEventFilter(ActionEvent.ACTION, event -> {
            Button button = (Button) event.getTarget();
            String answer = button.getText();
            buttonsPane.setVisible(false);
            if (isEditable()) validate(answer);
        });

        int i = 0;
        for (String letter: ((Propositions) square).getPropositions()
             ) {
            Button button = new Button(letter);
            button.getStyleClass().add("propositionButton");
            buttonsPane.add(button,i++,0);
        }



    }


}
